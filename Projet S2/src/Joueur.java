import java.util.ArrayList;

/**
 * Classe 
 * @author Allan
 * @version 1.0
 */
public class Joueur {
	private ArrayList<Personnage> equipe;
	private int id;
	/**
	 * Jalon2: Construit un tableau de personnage
	 */
	Joueur(int id){
		this.id=id;
	}
	
	public int getId(){
		return id;
	}

	public void addPerso(Personnage perso){
		equipe.add(perso);
	}
	
}
