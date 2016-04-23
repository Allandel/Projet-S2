import java.util.Random;

import javax.swing.JOptionPane;

public class Piegeur extends Personnage{
	
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
	
	public void pieger(int x, int y, Case[][] tableauIle){
		int decision;
		decision=JOptionPane.showConfirmDialog(null,"Voulez vous pieger cette case ?","Poser un piege", JOptionPane.YES_NO_OPTION);
		if (decision==0){
			tableauIle[x][y].setPiege(true);
			if(this.equipe1){
				tableauIle[x][y].setTeamPiege(0);
			}else{
				tableauIle[x][y].setTeamPiege(1);
			}
			super.perteEnergie(20, x,y, tableauIle, false, false);
		}
	}
	
}
