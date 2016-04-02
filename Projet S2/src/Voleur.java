
public class Voleur extends Personnage {
	
	public Voleur(boolean equipe1){
		setNom("Bill");
		setType("Voleur");
		if(equipe1)
			setId(7);
		else
			setId(9);
		setEquipe(equipe1);
	}
	public String toString(){
		return "V";
	}
}
