import java.util.ArrayList;

/**
 * Cree un joueur 
 * @author Allan
 * @version 1.0
 */
public class Joueur {
	private ArrayList<Personnage> equipe = new ArrayList<Personnage>();
	private boolean equipe1, coffreTrouve=false;
	private int idBateau, ligneBateau, colonneBateau;
	/**
	 * Construit un joueur en lui donnant un boolean correspondant a son equipe et l'id de son bateau
	 */
	Joueur(boolean equipe1){
		this.equipe1=equipe1;
		if(equipe1)
			idBateau=2;
		else
			idBateau=3;
	}
	/**
	 * 
	 * @return l'equipe de personnage du joueur
	 */
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

	/**
	 * Met en place le bateau du joueur dans l'ile
	 * @param ileDuJeu
	 */
	public void setBateau(ile ileDuJeu){
		if(equipe1){
			ligneBateau=ileDuJeu.getLigneNavJ1();
			colonneBateau=ileDuJeu.getColonneNavJ1();
		}else{
			ligneBateau=ileDuJeu.getLigneNavJ2();
			colonneBateau=ileDuJeu.getColonneNavJ2();
		}
	}
	
	/**
	 * 
	 * @return l'equipe du joueur
	 */
	public boolean getEquipe(){
		return equipe1;
	}

	/**
	 * Ajoute un personnage a l'equipe du joueur
	 * @param perso
	 */
	public void addPerso(Personnage perso){
		equipe.add(perso);
	}

	/**
	 * met la variable coffreTrouve a true
	 */
	public void coffreTrouve(){
		coffreTrouve=true;
	}

	/**
	 * 
	 * @return coffreTrouve
	 */
	public boolean getCoffreTrouve(){
		return coffreTrouve;
	}

	/**
	 * Dit si l'equipe vivante du joueur a encore une action ou un deplacement possible
	 * @return
	 */
	public boolean actionPossible(){
		for(Personnage perso : equipe){
			if(perso.actionOuDeplacement() && !perso.getDeath())
				return true;
		}
		return false;
	}

	/**
	 * Remet toutes les actions et deplacement des personnages de l'equipe du joueur a true
	 */
	public void resetAction(){
		for(Personnage perso : equipe){
			if(perso.getCompteur()==0){
				perso.setActionDeplacement(true);
			}else{
				perso.setCompteur();
			}
		}
	}

	/**
	 * Met toutes les actions et deplacement des personnages de l'equipe du joueur a false pour pouvoir passer son tour
	 */
	public void passerTour(){
		for(Personnage perso : equipe){
			perso.setActionDeplacement(false);
		}
	}

	/**
	 * 
	 * @return true si un personnage de l'equipe est vivant
	 */
	public boolean persoVivant(){
		for(Personnage perso: equipe){
			if(!perso.getDeath())
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @return le nombre de personnage vivant dans l'equipe du joueur
	 */
	public int nbrVivant(){
		int nbrVivant=0;
		for(Personnage perso: equipe){
			if(!perso.getDeath())
				nbrVivant++;
		}
		return nbrVivant;
	}
	
	/**
	 * 
	 * @return l'id du bateau du joueur
	 */
	public int getIdBateau() {
		return idBateau;
	}
	
	/**
	 * Tue tous les personnages du joueur s'il abandonne
	 */
	public void abandon(){
		for(Personnage perso: equipe){
			perso.setDeath(true);
		}
	}
	
}
