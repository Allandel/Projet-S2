import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class ActionJoueur {

	ActionJoueur(){}

	public int [] choixCase(Plateau plateauDuJeu, int[][] tableauAffichage, Boolean equipe, ile ileDuJeu){
		int[] coordonnees =new int[2];
		int xEvent,yEvent;
		do{
			do{
				coordonnees=this.getCoordonneesClic(plateauDuJeu);
				xEvent=coordonnees[0];
				yEvent=coordonnees[1];
			}while(coordonnees[0]==777 || (coordonnees[0]!=888 && coordonnees[0]!=999 && (tableauAffichage[yEvent][xEvent]<2 || tableauAffichage[yEvent][xEvent]==5 || tableauAffichage[yEvent][xEvent]>=14)));
		}while(coordonnees[0]!=888 && coordonnees[0]!=999 && (tableauAffichage[yEvent][xEvent]!=2 && tableauAffichage[yEvent][xEvent]!=3 && ileDuJeu.getTableau()[xEvent][yEvent].getPersonnageCourant().getEquipe()!=equipe));
		return coordonnees;
	}

	public int [] choixCase(ile ileDuJeu, Plateau plateauDuJeu, int[][] tableauAffichage, int x, int y, Personnage perso){
		int[] coordonnees =new int[2];
		int xEvent,yEvent;

		for(int i=y-1;i<y+2;i++){
			for(int j=x-1;j<x+2;j++){
				if(perso instanceof Voleur || perso instanceof Guerrier){
					if(tableauAffichage[i][j]==15 && perso.getDeplacement())
						plateauDuJeu.setHighlight(j, i, Color.BLUE);	
					if(perso.getAction() && (tableauAffichage[i][j]==perso.getIdBateau() || (tableauAffichage[i][j]>5 && tableauAffichage[i][j]<14)))
						plateauDuJeu.setHighlight(j, i, Color.BLUE);
				}else if(perso instanceof Piegeur){
					if(perso.getDeplacement() && tableauAffichage[i][j]==15)
						plateauDuJeu.setHighlight(j, i, Color.BLUE);
					if(perso.getAction() && (tableauAffichage[i][j]==perso.getIdBateau() || (tableauAffichage[i][j]>5 && tableauAffichage[i][j]<15 && ileDuJeu.getTableau()[j][i].getPersonnageCourant().getEquipe()==perso.getEquipe())))
						plateauDuJeu.setHighlight(j, i, Color.BLUE);
				}else if(((i==(y-1) || i==(y+1)) && j==x) || ((j==(x-1) || j==(x+1)) && i==y)){
					if(perso.getAction() && (tableauAffichage[i][j]==1 || tableauAffichage[i][j]==perso.getIdBateau() || tableauAffichage[i][j]==4 || (tableauAffichage[i][j]<14 && tableauAffichage[i][j]>5 && ileDuJeu.getTableau()[j][i].getPersonnageCourant().getEquipe()==perso.getEquipe())))
						plateauDuJeu.setHighlight(j, i, Color.BLUE);
					else if(perso.getDeplacement() && (tableauAffichage[i][j]==15 || tableauAffichage[i][j]==14 ))
						plateauDuJeu.setHighlight(j, i, Color.BLUE);
				}
			}
		}

		if(perso instanceof Explorateur){
			do{
				do{
					coordonnees=this.getCoordonneesClic(plateauDuJeu);
					xEvent=coordonnees[0];
					yEvent=coordonnees[1];
				}while(coordonnees[0]!=777 && coordonnees[0]!=888 && coordonnees[0]!=999 && (!(((yEvent==(y-1) || yEvent==(y+1)) && xEvent==x) || ((xEvent==(x-1) || xEvent==(x+1)) && yEvent==y))));
			}while(coordonnees[0]!=777 && coordonnees[0]!=888 && coordonnees[0]!=999 && (tableauAffichage[yEvent][xEvent]!=1 && tableauAffichage[yEvent][xEvent]!=15 && tableauAffichage[yEvent][xEvent]!=perso.getIdBateau() && tableauAffichage[yEvent][xEvent]!=4 && tableauAffichage[yEvent][xEvent]!=14 && !(tableauAffichage[yEvent][xEvent]>5 && ileDuJeu.getTableau()[xEvent][yEvent].getPersonnageCourant().getEquipe()==perso.getEquipe())));
		}else if(perso instanceof Voleur || perso instanceof Guerrier){
			do{
				do{
					coordonnees=this.getCoordonneesClic(plateauDuJeu);
					xEvent=coordonnees[0];
					yEvent=coordonnees[1];
				}while(coordonnees[0]!=777 && coordonnees[0]!=888 && coordonnees[0]!=999 && ((x-xEvent)>1 || (xEvent-x)>1 || (y-yEvent)>1 || (yEvent-y)>1 || (x==xEvent && y==yEvent)));
			}while(coordonnees[0]!=777 && coordonnees[0]!=888 && coordonnees[0]!=999 && (tableauAffichage[yEvent][xEvent]!=15 && tableauAffichage[yEvent][xEvent]!=perso.getIdBateau() && tableauAffichage[yEvent][xEvent]<6 && tableauAffichage[yEvent][xEvent]!=14));
		}else if(perso instanceof Piegeur){
			do{
				do{
					coordonnees=this.getCoordonneesClic(plateauDuJeu);
					xEvent=coordonnees[0];
					yEvent=coordonnees[1];
				}while(coordonnees[0]!=777 && coordonnees[0]!=888 && coordonnees[0]!=999 && ((x-xEvent)>1 || (xEvent-x)>1 || (y-yEvent)>1 || (yEvent-y)>1));
			}while(coordonnees[0]!=777 && coordonnees[0]!=888 && coordonnees[0]!=999 && (tableauAffichage[yEvent][xEvent]!=15 && tableauAffichage[yEvent][xEvent]!=perso.getIdBateau() && tableauAffichage[yEvent][xEvent]<6 && tableauAffichage[yEvent][xEvent]!=14 && (tableauAffichage[yEvent][xEvent]>5 && ileDuJeu.getTableau()[xEvent][yEvent].getPersonnageCourant().getEquipe()==perso.getEquipe())));

		}
		return coordonnees;

	}

	public int [] choixCaseSortie(Plateau plateauDuJeu, int[][] tableauAffichage, int x, int y, Personnage perso){
		int[] coordonnees =new int[2];
		int xEvent,yEvent;

		for(int i=y-1;i<y+2;i++){
			for(int j=x-1;j<x+2;j++){
				if(perso instanceof Explorateur){
					if(((i==(y-1) || i==(y+1)) && j==x) || ((j==(x-1) || j==(x+1)) && i==y)){
						if(tableauAffichage[i][j]==15 || tableauAffichage[i][j]==14)
							plateauDuJeu.setHighlight(j, i, Color.BLUE);
					}
				}else{
					if(tableauAffichage[i][j]==15 || tableauAffichage[i][j]==14)
						plateauDuJeu.setHighlight(j, i, Color.BLUE);
				}

			}
		}

		if(perso instanceof Explorateur){
			do{
				do{
					coordonnees=this.getCoordonneesClic(plateauDuJeu);
					xEvent=coordonnees[0];
					yEvent=coordonnees[1];
				}while(coordonnees[0]!=777 && !(((yEvent==(y-1) || yEvent==(y+1)) && xEvent==x) || ((xEvent==(x-1) || xEvent==(x+1)) && yEvent==y)));
			}while(coordonnees[0]!=777 && tableauAffichage[yEvent][xEvent]!=15 && tableauAffichage[yEvent][xEvent]!=14 );
		}else if (perso instanceof Piegeur){
			do{
				do{
					coordonnees=this.getCoordonneesClic(plateauDuJeu);
					xEvent=coordonnees[0];
					yEvent=coordonnees[1];
				}while(coordonnees[0]!=777 && ((x-xEvent)>1 || (xEvent-x)>1 || (y-yEvent)>1 || (yEvent-y)>1));
			}while(coordonnees[0]!=777 && (tableauAffichage[yEvent][xEvent]!=15 && tableauAffichage[yEvent][xEvent]!=14));
		}else{
			do{
				do{
					coordonnees=this.getCoordonneesClic(plateauDuJeu);
					xEvent=coordonnees[0];
					yEvent=coordonnees[1];
				}while(coordonnees[0]!=777 && ((x-xEvent)>1 || (xEvent-x)>1 || (y-yEvent)>1 || (yEvent-y)>1 || (x==xEvent && y==yEvent)));
			}while(coordonnees[0]!=777 && (tableauAffichage[yEvent][xEvent]!=15 && tableauAffichage[yEvent][xEvent]!=14));
		}
		return coordonnees;
	}

	/**
	 * Recupere les coordonnees de clic 
	 */
	public int [] getCoordonneesClic(Plateau plateauDuJeu){
		int [] res= new int[2];
		int keyCode = 0;
		InputEvent event;

		do{
			event=  plateauDuJeu.waitEvent();	
			if(event instanceof KeyEvent)
				keyCode=((KeyEvent) event).getKeyCode() ;
		}while(!(event instanceof MouseEvent) && keyCode!=32 && keyCode!=27 && keyCode!=127);

		if(event instanceof MouseEvent){
			res[0]=plateauDuJeu.getX((MouseEvent) event) ;
			res[1]=plateauDuJeu.getY((MouseEvent) event) ;
		}else if(event instanceof KeyEvent){
			if(keyCode==32){
				res[0]=999;
				res[1]=999;
			}else if(keyCode==27){
				res[0]=888;
				res[1]=888;
			}else if(keyCode==127){
				res[0]=777;
				res[1]=777;
			}
		}
		return res;
	}

}
