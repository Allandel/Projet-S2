import java.util.ArrayList;
import java.util.Random;


public class Fort extends Batiment{
	int niveau=1;
	protected ArrayList<String> stockRessources=new ArrayList<String>();
	
	/**
	 * Construit un fort en lui attribuant l'ID donnee
	 * @param id
	 */
	public Fort(int id, int x, int y, Joueur joueur){
		super(x, y, joueur);
		this.setId(id);
		joueur.setIdFort(id);
		batimentHealth=100;
		this.type="Fort";
		joueur.incrNiveauVillage();
		}

	public String toString(){
		return "F";
	}
	
	public void evolution(Affichage affichage, int equipe){
		if(niveau==1 && stockRessources.size()>=10){
			niveau=2;
			batimentHealth=200;
			joueur.incrNiveauVillage();
			for(int i=0; i<10;i++){
				stockRessources.remove(0);
			}
			this.setId(15);
			affichage.popUp(equipe, "Votre village à évolué en Forteresse ! Votre base est désormais plus résistante et ", "Evolution au niveau 2" );
		}else if(niveau==2 && stockRessources.size()>=30){
			niveau=3;
			batimentHealth=300;
			joueur.incrNiveauVillage();
			for(int i=0; i<30;i++){
				stockRessources.remove(0);
			}
			affichage.popUp(equipe, "Votre Forteresse à évoluer ! Elle est désormais plus résistante et inflige plus de dégats alentours !", "Evolution au niveau 3" );
		}
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
