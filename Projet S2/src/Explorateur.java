/**
 * Classe h�rit�e de personnage repr�sentant un explorateur
 * @author Allan
 * @version 1.0
 */
public class Explorateur extends Personnage{
	/**
	 * Jalon2: Construit un Explorateur avec 100 energie
	 */
	public Explorateur(boolean equipe1){
		setNom("Bob");
		setType("Explorateur");
		if(equipe1)
			setId(6);
		else
			setId(8);
		setEquipe(equipe1);
	}
	public String toString(){
		return "E";
	}
}
