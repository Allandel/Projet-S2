/**
 * Classe héritée de personnage représentant un explorateur
 * @author Allan
 * @version 1.0
 */
public class Explorateur extends Personnage{
	/**
	 * Jalon2: Construit un Explorateur avec 100 energie
	 */
	public Explorateur(){
		this.energie = 100;
		this.nom = "Explorateur";
	}
	public String toString(){
		return "E";
	}
}
