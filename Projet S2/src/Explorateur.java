/**
 * Classe h�rit�e de personnage repr�sentant un explorateur
 * @author Allan
 * @version 1.0
 */
public class Explorateur extends Personnage{
	/**
	 * Jalon2: Construit un Explorateur avec 100 energie
	 */
	public Explorateur(){
		setNom("Bob");
		setType("Explorateur");
		setId(6);
	}
	public String toString(){
		return "E";
	}
}
