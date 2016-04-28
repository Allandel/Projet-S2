import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Classe heritee de Personnage creant un personnage de type piegeur
 * @author louis
 *
 */
public class Piegeur extends Personnage{
	ArrayList<Bombe>listeBombe=new ArrayList<Bombe>();
	
	/**
	 * Constructeur creant un piegeur avec un nom, un type, un ID en fonction du parametre equipe1 determinant son equipe.
	 * @param equipe1
	 * @param joueur
	 */
	public Piegeur(Joueur joueur){
		super(joueur);
		setNom("Paul");
		setType("Piegeur");
		if(joueur.getEquipe())
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
			if(joueur.getEquipe()){
				tableauIle[x][y].setTeamPiege(0);
			}else{
				tableauIle[x][y].setTeamPiege(1);
			}
			super.perteEnergie(20, x,y, tableauIle, false, false,affichage, equipe);
		}else{
			this.poserBombe(x, y, tableauIle, affichage, equipe);
		}
	}
	
	public void poserBombe(int x, int y, Case[][] tableauIle, Affichage affichage, int equipe){
		int decision=(int)affichage.popUpYesNo(equipe,"Voulez vous poser une bombe ?","Poser une bombe",null);
		if (decision==0){
			Bombe b=new Bombe(x,y);
			tableauIle[x][y].setBombe(b);
			listeBombe.add(b);
			super.perteEnergie(20, x,y, tableauIle, false, false,affichage, equipe);
		}
	}
	public void downCompteurBombe(Case[][] tableauIle, Affichage affichage, int equipe){
		int indexBombe=0;
		if(!listeBombe.isEmpty()){
			for(Bombe bombe:listeBombe){
				if(bombe.downCompteur(tableauIle, affichage, equipe)){
					indexBombe=listeBombe.indexOf(bombe);
				}
			}
			if(listeBombe.get(indexBombe).getCompteur()==0){
				listeBombe.remove(indexBombe);
			}
		}
	}
	
	
}
