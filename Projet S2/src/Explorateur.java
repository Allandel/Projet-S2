/**
 * Classe heritee de personnage representant un explorateur
 * @author Allan
 * @version 1.0
 */
public class Explorateur extends Personnage{
	/**
	 * Constructeur d'Explorateur lui attribuant un nom, un id et une �quipe
	 */
	public Explorateur(boolean equipe1){
		super(equipe1);
		setNom("Bob");
		setType("Explorateur");
		if(equipe1)
			setId(6);
		else
			setId(9);
	}
	public String toString(){
		return "E";
	}
}
