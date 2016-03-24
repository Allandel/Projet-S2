/**
 * Classe repr�sentant une case
 * @author Allan
 * @version 1.0
 */
public class Case {
	public boolean isAccessible() {
		return accessible;
	}
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	int id;
	boolean accessible;
	boolean key;
	boolean chest;
	/**
	 * Construit une case initialisant l'attribut accessible, key et chest a false
	 */
	public Case(){
		accessible =false;
		key = false;
		chest = false;
	}
	public void setPersonnageCourant(Personnage p) {
	
	}

}
