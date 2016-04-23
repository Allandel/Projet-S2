import java.util.ArrayList;

/**
 * Classe 
 * @author Allan
 * @version 1.0
 */
public class Joueur {
	private ArrayList<Personnage> equipe = new ArrayList<Personnage>();
	private boolean equipe1, coffreTrouve=false;
	private int idBateau, ligneBateau, colonneBateau;
	/**
	 * Jalon2: Construit un tableau de personnage
	 */
	Joueur(boolean equipe1){
		this.equipe1=equipe1;
		if(equipe1)
			idBateau=2;
		else
			idBateau=3;
	}

	public ArrayList<Personnage> getPersos(){
		return equipe;
	}

	/**
	 * @return the ligneBateau
	 */
	public int getLigneBateau() {
		return ligneBateau;
	}

	/**
	 * @return the colonneBateau
	 */
	public int getColonneBateau() {
		return colonneBateau;
	}

	public void setBateau(ile ileDuJeu){
		if(equipe1){
			ligneBateau=ileDuJeu.getLigneNavJ1();
			colonneBateau=ileDuJeu.getColonneNavJ1();
		}else{
			ligneBateau=ileDuJeu.getLigneNavJ2();
			colonneBateau=ileDuJeu.getColonneNavJ2();
		}
	}
	
	public boolean getEquipe(){
		return equipe1;
	}

	public void addPerso(Personnage perso){
		equipe.add(perso);
	}

	public void coffreTrouve(){
		coffreTrouve=true;
	}

	public boolean getCoffreTrouve(){
		return coffreTrouve;
	}

	public boolean actionPossible(){
		for(Personnage perso : equipe){
			if(perso.actionOuDeplacement() && !perso.getDeath())
				return true;
		}
		return false;
	}

	public void resetAction(){
		for(Personnage perso : equipe){
			if(perso.getCompteur()==0){
				perso.setAction(true);
				perso.setDeplacement(true);
			}else{
				perso.setCompteur();
			}
		}
	}

	public void passerTour(){
		for(Personnage perso : equipe){
			perso.setAction(false);
			perso.setDeplacement(false);
		}
	}

	public boolean persoVivant(){
		for(Personnage perso: equipe){
			if(!perso.getDeath())
				return true;
		}
		return false;
	}

	public int nbrVivant(){
		int nbrVivant=0;

		for(Personnage perso: equipe){
			if(!perso.getDeath())
				nbrVivant++;
		}
		return nbrVivant;
	}

	public int getIdBateau() {
		return idBateau;
	}

}
