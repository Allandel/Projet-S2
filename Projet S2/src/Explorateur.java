/**
 * Classe heritee de personnage representant un explorateur
 * @author Allan
 * @version 1.0
 */
public class Explorateur extends Personnage{
	/**
	 * Constructeur d'Explorateur lui attribuant un nom, un id et une équipe
	 */
	public Explorateur(boolean equipe1){
		setNom("Bob");
		setType("Explorateur");
		if(equipe1){
			setId(6);
			setEquipe(equipe1);
		}else{
			setId(9);
			setEquipe(equipe1);
		}
	}
	public String toString(){
		return "E";
	}
}
