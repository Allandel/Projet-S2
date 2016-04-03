	/**
	 * Classe heritee de Personnage creant un personnage de type voleur
	 * @author Valentin
	 * @version 1.1
	 */
public class Voleur extends Personnage {
	/**
	 * Constructeur créant un voleur avec un nom, un type, un ID en fonction du parametre equipe1 determinant son equipe.
	 * @param equipe1
	 */
	public Voleur(boolean equipe1){
		setNom("Bill");
		setType("Voleur");
		if(equipe1){
			setId(7);
			setEquipe(equipe1);
		}else{
			setId(10);
			setEquipe(equipe1);
		}
	}
	public String toString(){
		return "V";
	}
}
