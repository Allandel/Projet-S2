import java.util.ArrayList;

/**
 * Classe heritee de Case representant les navires
 * @author Allan
 * @version 1.0
 */
public class Navire extends Batiment {


	/**
	 * Construit un navire en lui attribuant l'ID donnee
	 * @param id
	 */
	public Navire(int id, int x, int y, Joueur joueur){
		super(x, y, joueur);
		this.setId(id);
	}

	public String toString(){
		return "N";
	}

}
