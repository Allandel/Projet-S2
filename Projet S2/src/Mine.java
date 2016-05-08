import java.util.Random;


public class Mine extends Batiment{

	/**
	 * Constructeur
	 * @param x
	 * @param y
	 * @param joueur
	 */
	Mine(int x, int y, Joueur joueur) {
		super(x, y, joueur);
		this.type="Mine";
		if(joueur.getEquipe())
			this.setId(24);
		else
			this.setId(25);
	}

	/**
	 * Minage automatique de la mine si elle contient un ouvrier
	 */
	public void minage(){
		if(this.ouvrierPresent()){
			Random rdm=new Random();
			int miner=rdm.nextInt(2)+2;
			joueur.setUpStockRessource(miner);
		}
	}
}
