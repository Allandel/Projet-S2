import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;


public class ActionJoueur {

	ActionJoueur(){}

	public int [] choixCase(Plateau plateauDuJeu, int[][] tableauAffichage){
		int[] coordonnees =new int[2];
		int xEvent,yEvent;

		do{
			coordonnees=this.getCoordonneesClic(plateauDuJeu);
			xEvent=coordonnees[0];
			yEvent=coordonnees[1];
		}while(tableauAffichage[yEvent][xEvent]<2 || tableauAffichage[yEvent][xEvent]==5 || tableauAffichage[yEvent][xEvent]==12);
		
		return coordonnees;
	}
	
	
	public int [] choixCase(ile ileDuJeu, Plateau plateauDuJeu, int[][] tableauAffichage, int x, int y, Personnage perso){
		int[] coordonnees =new int[2];
		int xEvent,yEvent;
		
		if(perso instanceof Explorateur){
			do{
				do{
					coordonnees=this.getCoordonneesClic(plateauDuJeu);
					xEvent=coordonnees[0];
					yEvent=coordonnees[1];
				}while(!(((yEvent==(y-1) || yEvent==(y+1)) && xEvent==x) || ((xEvent==(x-1) || xEvent==(x+1)) && yEvent==y)));
			}while(tableauAffichage[yEvent][xEvent]>1 && tableauAffichage[yEvent][xEvent]!=perso.getIdBateau() && tableauAffichage[yEvent][xEvent]!=4 && tableauAffichage[yEvent][xEvent]!=12 && !(tableauAffichage[yEvent][xEvent]>5 && ileDuJeu.getTableau()[xEvent][yEvent].getPersonnageCourant().getEquipe()==perso.getEquipe()));
		}else if(perso instanceof Voleur){
			do{
				do{
					coordonnees=this.getCoordonneesClic(plateauDuJeu);
					xEvent=coordonnees[0];
					yEvent=coordonnees[1];
				}while((x-xEvent)>1 || (xEvent-x)>1 || (y-yEvent)>1 || (yEvent-y)>1 || (x==xEvent && y==yEvent));
			}while(tableauAffichage[yEvent][xEvent]!=0 && tableauAffichage[yEvent][xEvent]!=perso.getIdBateau() && tableauAffichage[yEvent][xEvent]<6 && tableauAffichage[yEvent][xEvent]!=12);
		}
		return coordonnees;
	}
	/**
	 * Recupere les coordonnees de clic 
	 */
	public int [] getCoordonneesClic(Plateau plateauDuJeu){
		int [] res= new int[2];
		InputEvent event;

		do{
			event=  plateauDuJeu.waitEvent();	
		}while(!(event instanceof MouseEvent));

		res[0]=plateauDuJeu.getX((MouseEvent) event) ;
		res[1]=plateauDuJeu.getY((MouseEvent) event) ;
		return res;
	}

}
