/**
 * Classe repr�sentant une case
 * @author Allan
 * @version 1.0
 */
public class Case {
	private int id=0;
	private boolean accessible;
	private Personnage personnageCourant=null;
	
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
	
	public boolean isAccessible() {
		return accessible;
	}
	
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	
	/**
	 * Construit une case initialisant l'attribut accessible, key et chest a false
	 */
	public Case(){
		accessible =false;
	}
	public void setPersonnageCourant(Personnage p){
		this.personnageCourant = p;
		this.setId(6);
	}
	
	public void removePersonnageCourant(){
		this.personnageCourant = null;
		this.setId(0);
	}

	public Personnage getPersonnageCourant(){
		return personnageCourant;
	}

	public String toString(){
		if(personnageCourant ==null){
			return  " ";
		}
		return personnageCourant.toString();
	}
}
