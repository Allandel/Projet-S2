
public class Voleur extends Personnage {
	
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
