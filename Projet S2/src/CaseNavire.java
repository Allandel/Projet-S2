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
		if(ileDuJeu.placeLibre(y, x)){
			Personnage persoSortant = null;
			ActionJoueur action= new ActionJoueur();
			int nbrVivantJouable=0, i=0;
			for(Personnage perso : stockNavire){
				if(perso.sortiePossible(y, x, ileDuJeu))
					nbrVivantJouable++;
			}
			if(!stockNavire.isEmpty() && nbrVivantJouable>0){

				Personnage [] listePerso= new Personnage[nbrVivantJouable];
				for(Personnage perso: stockNavire){
					if(perso.sortiePossible(y, x, ileDuJeu)){
						listePerso[i]=perso;
						i++;
					}
				}
				persoSortant=(Personnage)  JOptionPane.showInputDialog(null,"Quels personnage voulez-vous faire sortir du navire ?", "Sortie du navire", JOptionPane.QUESTION_MESSAGE, null, listePerso, listePerso[0]);
				if(persoSortant!=null){
					int[] cordonnees = action.choixCaseSortie(plateauDuJeu, tableauAffichage, x, y, persoSortant);

					if(tableauAffichage[cordonnees[1]][cordonnees[0]]==15)
						ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].setPersonnageCourant(persoSortant);
					else
						persoSortant.recuperationStuff(true,false, 0, 0, cordonnees[0], cordonnees[1],ileDuJeu.getTableau());
					persoSortant.setAction(false);
					stockNavire.remove(persoSortant);
				}
			}else{
				JOptionPane.showOptionDialog(null, "Il n'y a pas de personnages jouables dans le Navire", "Attention",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						null, options, options[0]);
			}
		}else{
			JOptionPane.showOptionDialog(null, "Il n'y a pas de place libre pour pouvoir placer un personnage", "Attention",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
			this.sortieImpossible();
		}

	}

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

	private void sortieImpossible(){
		for(Personnage perso:stockNavire){
			perso.setAction(false);
		}
	}
	
	public void recupEnergie(){
		for(Personnage perso: stockNavire){
			if(!perso.getDeath())
				perso.addEnergie();
		}
	}
}
