import java.util.Random;


public class Fort extends Batiment{
	private int x,y;
	
	/**
	 * Construit un navire en lui attribuant l'ID donnee
	 * @param id
	 */
	public Fort(int id, int x, int y, Joueur joueur){
		this.setId(id);
		this.x=x;
		this.y=y;
		this.joueur=joueur;
	}

	public String toString(){
		return "F";
	}

	public void attaque(Case[][] tableauIle, Affichage affichage, int equipe){
		Random deg=new Random();
		int dommages=deg.nextInt(10)+20;
		for(int i=x-1;i<x+2;i++){
			for(int j=y-1;j<y+2;j++){
				if(tableauIle[i][j].getPersonnageCourant()!=null && tableauIle[i][j].getPersonnageCourant().getDeath()==false && tableauIle[i][j].getPersonnageCourant().getJoueur()!=joueur){
					tableauIle[i][j].getPersonnageCourant().perteEnergie(dommages, i,j, tableauIle, true, false,affichage, equipe);
				}
			}
		}
	}
}
