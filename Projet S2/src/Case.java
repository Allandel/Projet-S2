
/**
 * Classe representant une case
 * @author Allan
 * @version 1.0
 */
public class Case {
	private int id=15;
	private boolean accessible;
	private Personnage personnageCourant=null;
	private boolean piege=false;
	private int piegeteam;
	private Bombe bombe;
	
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
	 * @return the accessible
	 */
	public boolean isAccessible() {
		return accessible;
	}
	
	public void setBombe(Bombe b){
		this.bombe=b;
		
	}
	
	public boolean getBombe(){
		if(bombe!=null){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @param accessible the accessible to set
	 */
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	
	/**
	 * Construit une case initialisant l'attribut accessible a false
	 */
	public Case(){
		accessible =false;
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
		this.setId(15);
	}
	
	/**
	 * Donne le Personnage Courant affilie a une case
	 */
	public Personnage getPersonnageCourant(){
		return personnageCourant;
	}
	
	/**
	 * Change la valeur de piege
	 * @param x
	 */
	public void setPiege(boolean x){
		piege=x;
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
		if(personnageCourant==null){
			return  " ";
		}
		return personnageCourant.toString(true);
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
	
}
