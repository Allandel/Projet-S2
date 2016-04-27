
public class Ouvrier extends Personnage{

	Ouvrier(boolean equipe, Joueur joueur) {
		super(equipe, joueur);
		setNom("Pierre");
		setType("Ouvrier");
	/*	if(equipe1)
			setId();
		else
			setId();*/
	}
	
	public void construireVillage(Joueur joueur){
		if(joueur.getNbrVillage()<1){
			
		}
	}
}
