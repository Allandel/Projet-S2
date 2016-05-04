import java.util.Random;


public class Fort extends Batiment{

	/**
	 * Construit un fort en lui attribuant l'ID donnee
	 * @param id
	 */
	public Fort(int id, int x, int y, Joueur joueur){
		super(x, y, joueur);
		this.setId(id);
		}

	public String toString(){
		return "F";
	}
	
	/**
	 * Attaque les perso ennemi autour du fort
	 * @param tableauIle
	 * @param affichage
	 * @param equipe
	 */
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
