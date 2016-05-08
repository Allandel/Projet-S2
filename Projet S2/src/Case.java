
/**
 * Classe representant une case
 * @author Allan
 * @version 1.0
 */
public class Case {
	private int id=17, piegeteam;
	private boolean accessible, piege=false;
	private Personnage personnageCourant=null;
	private Bombe bombe=null;
	private Batiment batimentCourant=null;

	/**
	 * Construit une case initialisant l'attribut accessible a false
	 */
	public Case(){
		accessible =false;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Set une bombe dans la case
	 * @param b
	 */
	public void setBombe(Bombe b){
		this.bombe=b;

	}

	/**
	 * @return the batimentCourant
	 */
	public Batiment getBatimentCourant() {
		return batimentCourant;
	}

	/**
	 * @param batimentCourant the batimentCourant to set
	 */
	public void setBatimentCourant(Batiment batimentCourant, boolean construction) {
		this.batimentCourant = batimentCourant;
		if(!construction)
			this.setId(batimentCourant.getId());
	}

	/**
	 * Enleve le batiment courant et réitinialise l'id de la case
	 */
	public void removeBatimentCourant(boolean construction){
		this.batimentCourant = null;
		if(!construction)
			this.setId(17);
	}

	/**
	 * 
	 * @return vrai s'il y a une bombe dans la case
	 */
	public boolean getBombe(){
		if(bombe!=null)
			return true;
		return false;
	}

	/**
	 * Met la valeur de piegeteam suivant l'equipe qui piege la case
	 * @param x
	 */
	public void setTeamPiege(int x){
		this.piegeteam=x;
	}

	/**
	 * 
	 * @return la valeur de l'equipe qui a piege la case
	 */
	public int getTeamPiege(){
		return piegeteam;
	}

	/**
	 * @param accessible the accessible to set
	 */
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

	/**
	 * @return the accessible
	 */
	public boolean isAccessible() {
		return accessible;
	}

	/**
	 * met un personnge dans une case et change l'id de la case
	 * @param p the PersonnageCourant to set
	 */
	public void setPersonnageCourant(Personnage p){
		this.personnageCourant = p;
		this.setId(p.getId());
	}
	
	/**
	 * Supprime un Personnage qui se trouve sur une Case et remet l'id d'une case vide
	 */
	public void removePersonnageCourant(){
		this.personnageCourant = null;
		this.setId(17);
	}

	/**
	 * Donne le Personnage Courant affilie a une case
	 */
	public Personnage getPersonnageCourant(){
		return personnageCourant;
	}

	/**
	 * Change la valeur de piege
	 * @param setter
	 */
	public void setPiege(boolean setter){
		piege=setter;
	}

	/**
	 * 
	 * @return si la case est piege ou non
	 */
	public boolean getPiege(){
		return piege;
	}

	/**
	 * return un string vide ou celui specifique au personnage si la case est occupe
	 */
	public String toString(){

		if(batimentCourant!=null)
			return batimentCourant.toString();
		else if(personnageCourant!=null)
			return personnageCourant.toString(true);
		return  " ";
	}

}
