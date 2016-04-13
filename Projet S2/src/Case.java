import java.util.ArrayList;

import javax.swing.JOptionPane;


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
	 * Donne le Personnage Courant affili� a une case
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
}
