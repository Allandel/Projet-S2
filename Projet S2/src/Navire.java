
/**
 * Classe heritee de Case representant les navires
 * @author Allan
 * @version 1.0
 */
public class Navire extends Batiment {

	/**
	 * Construit un navire en lui attribuant l'ID donnee
	 * @param id
	 * @param x
	 * @param y
	 * @param joueur
	 */
	public Navire(int id, int x, int y, Joueur joueur){
		super(x, y, joueur);
		this.setId(id);
		this.type="Navire";
		batimentHealth=200;
	}

	public String toString(){
		return "N";
	}

}
