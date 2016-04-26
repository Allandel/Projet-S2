import javax.swing.JOptionPane;

/**
 * Classe heritee de Personnage creant un personnage de type piegeur
 * @author louis
 *
 */
public class Piegeur extends Personnage{
	
	/**
	 * Constructeur creant un piegeur avec un nom, un type, un ID en fonction du parametre equipe1 determinant son equipe.
	 * @param equipe1
	 * @param joueur
	 */
	public Piegeur(boolean equipe1, Joueur joueur){
		super(equipe1, joueur);
		setNom("Paul");
		setType("Piegeur");
		if(equipe1)
			setId(8);
		else
			setId(12);
	}
	
	public String toString(boolean console){
		return "P";
	}
	
	/**
	 * Permet au personnage de pieger une case
	 * @param x
	 * @param y
	 * @param tableauIle
	 */
	public void pieger(int x, int y, Case[][] tableauIle, Affichage affichage, int equipe){
		int decision=(int)affichage.popUpYesNo(equipe,"Voulez vous pieger cette case ?","Poser un piege",null);
		if (decision==0){
			tableauIle[x][y].setPiege(true);
			if(this.equipe1){
				tableauIle[x][y].setTeamPiege(0);
			}else{
				tableauIle[x][y].setTeamPiege(1);
			}
			super.perteEnergie(20, x,y, tableauIle, false, false,affichage, equipe);
		}
	}
	
}
