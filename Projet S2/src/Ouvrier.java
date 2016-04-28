
public class Ouvrier extends Personnage{

	Ouvrier(Joueur joueur) {
		super(joueur);
		setNom("Pierre");
		setType("Ouvrier");
	/*	if(joueur.getEquipe())
			setId();
		else
			setId();*/
	}
	
	public void construireVillage(Joueur joueur){
		if(joueur.getNbrVillage()<1){
			
		}
	}
}
