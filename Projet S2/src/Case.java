import java.util.ArrayList;


/**
 * Classe representant une case
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
	/**
	 * @return the accessible
	 */
	public boolean isAccessible() {
		return accessible;
	}
	/**
	 * @return the stockNavire
	 */
	public ArrayList getStockNavire(){
		return new ArrayList();
	}
	/**
	 * @param accessible the accessible to set
	 */
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	
	/**
	 * Construit une case initialisant l'attribut accessible, key et chest a false
	 */
	public Case(){
		accessible =false;
	}
	/**
	 * @param PersonnageCourant the PersonnageCourant to set
	 */
	public void setPersonnageCourant(Personnage p){
		this.personnageCourant = p;
		this.setId(p.getId());
	}
	/**
	 * Supprime un Personnage qui se trouve sur une Case
	 */
	public void removePersonnageCourant(){
		this.personnageCourant = null;
		this.setId(0);
	}
	/**
	 * Permet a un personnage d'entrer dans un bateau
	 * @param p
	 */
	public boolean entreeBateau(Personnage p){
		return false;
	}
	public String choisirSortieBateau(){
		return " ";
	}
	/**
	 * Donne le Personnage Courant affilié a une case
	 */
	public Personnage getPersonnageCourant(){
		return personnageCourant;
	}

	public String toString(){
		if(personnageCourant ==null){
			return  " ";
		}
		return personnageCourant.toString();
	}
	
	public void interactionRocher(Personnage p){}
}
