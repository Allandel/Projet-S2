import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Classe heritee de Case representant les navires
 * @author Allan
 * @version 1.0
 */
public class CaseNavire extends Case {
	private ArrayList<Personnage>stockNavire=new ArrayList<Personnage>();


	/**
	 * Construit un navire en lui attribuant l'ID donnee
	 * @param id
	 */
	public CaseNavire(int id){
		this.setId(id);
	}

	/**
	 * Retourne la liste de Personnage du Navire
	 */
	public ArrayList<Personnage> getStocknavire(){
		return stockNavire;
	}

	/**
	 * 
	 * @return le nombre de personnage vivant dans le navire
	 */
	public int nbrVivantStock(){
		int cpt=0;
		for(Personnage perso:stockNavire){
			if(!perso.getDeath())
				cpt++;
		}
		return cpt;
	}

	/**
	 * Permet a un personnage d'entrer dans le bateau
	 * @param p
	 */
	public void addPersoNavire(Personnage p){
		stockNavire.add(p);
	}

	/**
	 * Permet a un personnage de sortir du bateau dans le bateau
	 */
	public void sortieBateau(ile ileDuJeu, Plateau plateauDuJeu, int[][] tableauAffichage, int x, int y){
		Object[] options = { "OK" };
		if(stockNavire.isEmpty()){
			//affichage d'un message si pas de personnage dans le navire
			JOptionPane.showOptionDialog(null, "Il n'y a pas de personnages dans le Navire", "Attention",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
		}else if(this.placeLibre(y, x, ileDuJeu)){
			//S'il y a de la place autour du bateau
			Personnage persoSortant = null;
			ActionJoueur action= new ActionJoueur();
			int nbrVivantJouable=0, i=0;
			for(Personnage perso : stockNavire){
				if(perso.sortiePossible(y, x, ileDuJeu))
					nbrVivantJouable++;
			}
			if(nbrVivantJouable>0){
				//S'il y a au moins un personnage vivant et jouable dans le bateau	
				Personnage [] listePerso= new Personnage[nbrVivantJouable];
				for(Personnage perso: stockNavire){
					if(perso.sortiePossible(y, x, ileDuJeu)){
						listePerso[i]=perso;
						i++;
					}
				}
				persoSortant=(Personnage)  JOptionPane.showInputDialog(null,"Quels personnage voulez-vous faire sortir du navire ?", "Sortie du navire", JOptionPane.QUESTION_MESSAGE, null, listePerso, listePerso[0]);
				if(persoSortant!=null){
					//si le joueur a choisi de faire sortir un personnage	
					int[] cordonnees = action.choixCaseSortie(plateauDuJeu, tableauAffichage, x, y, persoSortant);
					if(cordonnees[0]!=777){
						//si le joueur n'annule pas l'action	
						if(tableauAffichage[cordonnees[1]][cordonnees[0]]==15)
							ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].setPersonnageCourant(persoSortant);
						else
							persoSortant.recuperationStuff(true,false, 0, 0, cordonnees[0], cordonnees[1],ileDuJeu.getTableau());
						persoSortant.setAction(false);
						stockNavire.remove(persoSortant);
					}
				}
				if(this.sortieImpossible(y, x, ileDuJeu))
					//S'il n'y a plus de case vide ou de personnage pouvant se deplacer autour du bateau
					this.actionImpossible();
				//met les personnages dans le bateau comme sans action ni deplacement pour eviter une boucle infini dans le tour du joueur
			}else{
				JOptionPane.showOptionDialog(null, "Il n'y a pas de personnages jouables dans le Navire", "Attention",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						null, options, options[0]);
			}
		}else{
			JOptionPane.showOptionDialog(null, "Il n'y a pas de place libre pour pouvoir placer un personnage", "Attention",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
			this.actionImpossible();
		}

	}

	/**
	 * 
	 * @return vrai s'il y a personnage mort dans le navire
	 */
	public boolean persoMort(){
		for(Personnage perso : stockNavire){
			if(perso.getDeath())
				return true;
		}
		return false;
	}

	public String toString(){
		return "N";
	}

	/**
	 * Met l'attribut action et deplacement des personnages du navire a faux pour eviter une boucle infini dans le jeu
	 */
	private void actionImpossible(){
		for(Personnage perso:stockNavire){
			perso.setAction(false);
			perso.setDeplacement(false);
		}
	}

	/**
	 * Regarde s'il y a une place libre autour du bateau
	 * @param i
	 * @param j
	 * @param ileDuJeu
	 * @return vrai si une case est vide
	 */
	private boolean placeLibre(int i, int j, ile ileDuJeu){
		for(int x=i-1;x<i+2;x++){
			for(int y=j-1;y<j+2;y++){
				if(ileDuJeu.getTableau()[y][x].getId()==15)
					return true;
			}
		}
		return false;
	}

	/**
	 * Regarde si une case est vide ou si le personnage qui l'occupe peut sortir
	 * @param i
	 * @param j
	 * @param ileDuJeu
	 * @return vrai s'il n'y a pas de case vide ou de personnage pouvant se deplacer autour du bateau
	 */
	public boolean sortieImpossible(int i, int j, ile ileDuJeu){
		for(int x=i-1;x<i+2;x++){
			for(int y=j-1;y<j+2;y++){
				if(ileDuJeu.getTableau()[y][x].getId()==15 || (ileDuJeu.getTableau()[y][x].getId()>5 && ileDuJeu.getTableau()[y][x].getId()<15 && ileDuJeu.getTableau()[y][x].getPersonnageCourant().getDeplacement()))
					return false;
			}
		}
		return true;
	}

	/**
	 * Regenere l'energie des personnages dans le navire 
	 */
	public void recupEnergie(){
		for(Personnage perso: stockNavire){
			if(!perso.getDeath())
				perso.addEnergie();
		}
	}
}
