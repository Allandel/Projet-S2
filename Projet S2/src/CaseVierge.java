/**
 * Classe héritée de Case représentant une case vierge
 * @author Allan
 * @version 1.0
 */
public class CaseVierge extends Case {
	Personnage personnageCourant;
	public CaseVierge(){
		super();
		id = 0;
		this.personnageCourant = null;
	}
	/**
	 * Jalon2: permet de placer un personnage dans une case vierge
	 * @param p
	 */
	public void setPersonnageCourant(Personnage p){
		this.personnageCourant = p;
	}
	/**
	 * Jalon2: permet de supprimer le personnage contenu dans la case
	 */
	public void removePersonnageCourant(){
		this.personnageCourant = null;
	}
	public String toString(){
		if(personnageCourant ==null){
			return  " ";
		}
		return personnageCourant.toString();
	}
}
