import java.util.ArrayList;


public class Batiment {
	protected ArrayList<Personnage>stockBatiment=new ArrayList<Personnage>();
	protected int batimentHealth=200,id=0;
	
	public void dommageBatiment(int deg){
		batimentHealth-=deg;
	}
	
	public int getBatimentHealth(){
		return batimentHealth;
	}
	
	/**
	 * Retourne la liste de Personnage du Batiment
	 */
	public ArrayList<Personnage> getStockBatiment(){
		return stockBatiment;
	}
	
	/**
	 * 
	 * @return le nombre de personnage vivant dans le batiment
	 */
	public int nbrVivantStock(){
		int cpt=0;
		for(Personnage perso:stockBatiment){
			if(!perso.getDeath())
				cpt++;
		}
		return cpt;
	}

	/**
	 * Permet a un personnage d'entrer dans le batiment
	 * @param p
	 */
	public void addPersoBatiment(Personnage p){
		stockBatiment.add(p);
	}

	/**
	 * 
	 * @return vrai s'il y a personnage mort dans le batiment
	 */
	public boolean persoMort(){
		for(Personnage perso : stockBatiment){
			if(perso.getDeath())
				return true;
		}
		return false;
	}

	/**
	 * Met l'attribut action et deplacement des personnages du batiment a faux pour eviter une boucle infini dans le jeu
	 */
	private void actionImpossible(){
		for(Personnage perso:stockBatiment){
			perso.setAction(false);
			perso.setDeplacement(false);
		}
	}

	/**
	 * Regarde s'il y a une place libre autour du batiment
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
	 * @return vrai s'il n'y a pas de case vide ou de personnage pouvant se deplacer autour du batiment
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
	 * Regenere l'energie des personnages dans le batiment 
	 */
	public void recupEnergie(){
		for(Personnage perso: stockBatiment){
			if(!perso.getDeath())
				perso.addEnergie();
		}
	}

	/**
	 * Permet a un personnage de sortir du batiment
	 */
	public void sortieBatiment(ile ileDuJeu, Plateau plateauDuJeu, int[][] tableauAffichage, int x, int y, Affichage affichage, int equipe){
		plateauDuJeu.refreshinfo(null,batimentHealth );
		
		if(stockBatiment.isEmpty()){
			//affichage d'un message si pas de personnage dans le batiment
			affichage.popUp(equipe, "Il n'y a pas de personnages.", "Attention" );
		}else if(this.placeLibre(y, x, ileDuJeu)){
			//S'il y a de la place autour du batiment
			Personnage persoSortant = null;
			ActionJoueur action= new ActionJoueur();
			int nbrVivantJouable=0, i=0;
			for(Personnage perso : stockBatiment){
				if(perso.sortiePossible(y, x, ileDuJeu))
					nbrVivantJouable++;
			}
			if(nbrVivantJouable>0){
				//S'il y a au moins un personnage vivant et jouable dans le batiment	
				Personnage [] listePerso= new Personnage[nbrVivantJouable];
				for(Personnage perso: stockBatiment){
					if(perso.sortiePossible(y, x, ileDuJeu)){
						listePerso[i]=perso;
						i++;
					}
				}
				persoSortant=(Personnage)affichage.popUpYesNo(equipe,"\nQuels personnage voulez-vous faire sortir ?\n\n", "Sortie de personnage",listePerso);  
				if(persoSortant!=null){
					//si le joueur a choisi de faire sortir un personnage	
					affichage.setVisibleActionPerso(true, null);
					int[] cordonnees = action.choixCaseSortie(plateauDuJeu, tableauAffichage, x, y, persoSortant);
					if(cordonnees[0]!=777){
						//si le joueur n'annule pas l'action	
						if(tableauAffichage[cordonnees[1]][cordonnees[0]]==15)
							ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].setPersonnageCourant(persoSortant);
						else
							persoSortant.recuperationStuff(true,false, 0, 0, cordonnees[0], cordonnees[1],ileDuJeu.getTableau(), affichage, equipe);
						persoSortant.setAction(false);
						stockBatiment.remove(persoSortant);
					}
				}
				if(this.sortieImpossible(y, x, ileDuJeu))
					//S'il n'y a plus de case vide ou de personnage pouvant se deplacer autour du batiment
					this.actionImpossible();
				//met les personnages dans le batiment comme sans action ni deplacement pour eviter une boucle infini dans le tour du joueur
			}else{
				affichage.popUp(equipe, "Il n'y a pas de personnages jouables", "Attention" );
			}
		}else{
			affichage.popUp(equipe, "Il n'y a pas de place libre pour pouvoir placer un personnage", "Attention" );
			this.actionImpossible();
		}
		affichage.setVisibleActionPerso(false,null);
	}

	public void setId(int id){
		this.id=id;
	}
	
	public int getId(){
		return id;
	}

	public String toString(){
		return "";
	}
}
