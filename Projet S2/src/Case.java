
/**
 * Classe repr�sentant une case
 * @author Allan
 * @version 1.0
 */
public class Case {
	private int id;
	private boolean accessible;
	
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
	public void setPersonnageCourant(Personnage p) {

	}
	
	public void removePersonnageCourant(){
		
	}
	public boolean retourBateau(Personnage p){
		return false;
	}
	public void sortieBateau(){

	}

}
