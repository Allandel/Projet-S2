import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Classe qui permet la recuperation des clics et des touches que le joueur presse
 * @author louis
 *
 */
public class ActionJoueur {

	ActionJoueur(){}

	/**
	 * Permet de choisir la 1ere case ou faire une action. N'accepte que des cases avec un personnage ou un bateau
	 * @param plateauDuJeu
	 * @param tableauAffichage
	 * @param equipe
	 * @param ileDuJeu
	 * @return le tableau contenant le x et le y des coordonnees selectionnees
	 */
	public int [] choixCase(Plateau plateauDuJeu, int[][] tableauAffichage, ile ileDuJeu, Joueur joueur){
		int[] coordonnees =new int[2];
		int xEvent,yEvent;
		do{
			do{
				coordonnees=this.getCoordonneesClic(plateauDuJeu,999,999, joueur);
				xEvent=coordonnees[0];
				yEvent=coordonnees[1];
			}while(coordonnees[0]==777 || (coordonnees[0]!=888 && coordonnees[0]!=999 && (tableauAffichage[yEvent][xEvent]<2 || tableauAffichage[yEvent][xEvent]==5 || tableauAffichage[yEvent][xEvent]>=14)));
		}while(coordonnees[0]!=888 && coordonnees[0]!=999 && (tableauAffichage[yEvent][xEvent]==5 && (tableauAffichage[yEvent][xEvent]!=2 && tableauAffichage[yEvent][xEvent]!=3 && ileDuJeu.getTableau()[xEvent][yEvent].getPersonnageCourant().getJoueur()!=joueur)));
		return coordonnees;
	}

	/**
	 * Permet de choisir la 2eme case pour une action, une fois un personnage selectionné
	 * Met en couleur les cases ou l'action est faisable suivant le type de personnage a deplace
	 * N'accepte que des deplacements specifiques a chaque perso
	 * @param ileDuJeu
	 * @param plateauDuJeu
	 * @param tableauAffichage
	 * @param x
	 * @param y
	 * @param perso
	 * @return  le tableau contenant le x et le y des coordonnees selectionnees
	 */
	public int [] choixCase(ile ileDuJeu, Plateau plateauDuJeu, int[][] tableauAffichage, int x, int y, Personnage perso){
		int[] coordonnees =new int[2];
		int xEvent,yEvent;

		for(int i=y-1;i<y+2;i++){
			for(int j=x-1;j<x+2;j++){
				if(perso instanceof Voleur || perso instanceof Guerrier){
					if(perso.getDeplacement() && (tableauAffichage[i][j]==15 || tableauAffichage[i][j]==perso.getIdBateau()))
						plateauDuJeu.setHighlight(j, i, Color.BLUE);	
					if(perso.getAction() && tableauAffichage[i][j]>5 && tableauAffichage[i][j]<14)
						plateauDuJeu.setHighlight(j, i, Color.BLUE);
				}else if(perso instanceof Piegeur){
					if(perso.getDeplacement() && (tableauAffichage[i][j]==perso.getIdBateau() || tableauAffichage[i][j]==15))
						plateauDuJeu.setHighlight(j, i, Color.BLUE);
					if(perso.getAction() && tableauAffichage[i][j]>5 && tableauAffichage[i][j]<15 && ileDuJeu.getTableau()[j][i].getPersonnageCourant().getJoueur()==perso.getJoueur()|| (tableauAffichage[i][j]>0 && tableauAffichage[i][j]<4))
						plateauDuJeu.setHighlight(j, i, Color.BLUE);
				}else if(((i==(y-1) || i==(y+1)) && j==x) || ((j==(x-1) || j==(x+1)) && i==y)){
					if(perso.getAction() && (tableauAffichage[i][j]==1 || tableauAffichage[i][j]==4 || (tableauAffichage[i][j]<14 && tableauAffichage[i][j]>5 && ileDuJeu.getTableau()[j][i].getPersonnageCourant().getJoueur()==perso.getJoueur())))
						plateauDuJeu.setHighlight(j, i, Color.BLUE);
					else if(perso.getDeplacement() && (tableauAffichage[i][j]==15 || tableauAffichage[i][j]==14 || tableauAffichage[i][j]==perso.getIdBateau()))
						plateauDuJeu.setHighlight(j, i, Color.BLUE);
				}
			}
		}

		if(perso instanceof Explorateur){
			do{
				do{
					coordonnees=this.getCoordonneesClic(plateauDuJeu,x,y,null);
					xEvent=coordonnees[0];
					yEvent=coordonnees[1];
				}while(coordonnees[0]!=777 && coordonnees[0]!=888 && coordonnees[0]!=999 && (!(((yEvent==(y-1) || yEvent==(y+1)) && xEvent==x) || ((xEvent==(x-1) || xEvent==(x+1)) && yEvent==y))));
			}while(coordonnees[0]!=777 && coordonnees[0]!=888 && coordonnees[0]!=999 && (tableauAffichage[yEvent][xEvent]!=1 && tableauAffichage[yEvent][xEvent]!=15 && tableauAffichage[yEvent][xEvent]!=perso.getIdBateau() && tableauAffichage[yEvent][xEvent]!=4 && tableauAffichage[yEvent][xEvent]!=14 && !(tableauAffichage[yEvent][xEvent]>5 && ileDuJeu.getTableau()[xEvent][yEvent].getPersonnageCourant().getJoueur()==perso.getJoueur())));
		}else if(perso instanceof Voleur || perso instanceof Guerrier){
			do{
				do{
					coordonnees=this.getCoordonneesClic(plateauDuJeu,x,y,null);
					xEvent=coordonnees[0];
					yEvent=coordonnees[1];
				}while(coordonnees[0]!=777 && coordonnees[0]!=888 && coordonnees[0]!=999 && ((x-xEvent)>1 || (xEvent-x)>1 || (y-yEvent)>1 || (yEvent-y)>1 || (x==xEvent && y==yEvent)));
			}while(coordonnees[0]!=777 && coordonnees[0]!=888 && coordonnees[0]!=999 && (tableauAffichage[yEvent][xEvent]!=15 && tableauAffichage[yEvent][xEvent]!=perso.getIdBateau() && tableauAffichage[yEvent][xEvent]<6 && tableauAffichage[yEvent][xEvent]!=14));
		}else if(perso instanceof Piegeur){
			do{
				do{
					coordonnees=this.getCoordonneesClic(plateauDuJeu,x,y,null);
					xEvent=coordonnees[0];
					yEvent=coordonnees[1];
				}while(coordonnees[0]!=777 && coordonnees[0]!=888 && coordonnees[0]!=999 && ((x-xEvent)>1 || (xEvent-x)>1 || (y-yEvent)>1 || (yEvent-y)>1));
			}while(coordonnees[0]!=777 && coordonnees[0]!=888 && coordonnees[0]!=999 && (tableauAffichage[yEvent][xEvent]!=15 && tableauAffichage[yEvent][xEvent]!=perso.getIdBateau() && tableauAffichage[yEvent][xEvent]<6 && tableauAffichage[yEvent][xEvent]!=14 && (tableauAffichage[yEvent][xEvent]>5 && ileDuJeu.getTableau()[xEvent][yEvent].getPersonnageCourant().getJoueur()==perso.getJoueur())));

		}
		return coordonnees;

	}

	/**
	 * Permet de choisir la case ou placer le personnage qui sort du bateau
	 * Met en couleur les cases ou c'est possible en fonction du type de personnage choisie
	 * @param plateauDuJeu
	 * @param tableauAffichage
	 * @param x
	 * @param y
	 * @param perso
	 * @return  le tableau contenant le x et le y des coordonnees selectionnees
	 */
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
					coordonnees=this.getCoordonneesClic(plateauDuJeu,x,y,null);
					xEvent=coordonnees[0];
					yEvent=coordonnees[1];
				}while(coordonnees[0]!=777 && !(((yEvent==(y-1) || yEvent==(y+1)) && xEvent==x) || ((xEvent==(x-1) || xEvent==(x+1)) && yEvent==y)));
			}while(coordonnees[0]!=777 && tableauAffichage[yEvent][xEvent]!=15 && tableauAffichage[yEvent][xEvent]!=14 );
		}else if (perso instanceof Piegeur){
			do{
				do{
					coordonnees=this.getCoordonneesClic(plateauDuJeu,x,y,null);
					xEvent=coordonnees[0];
					yEvent=coordonnees[1];
				}while(coordonnees[0]!=777 && ((x-xEvent)>1 || (xEvent-x)>1 || (y-yEvent)>1 || (yEvent-y)>1));
			}while(coordonnees[0]!=777 && (tableauAffichage[yEvent][xEvent]!=15 && tableauAffichage[yEvent][xEvent]!=14));
		}else{
			do{
				do{
					coordonnees=this.getCoordonneesClic(plateauDuJeu,x,y,null);
					xEvent=coordonnees[0];
					yEvent=coordonnees[1];
				}while(coordonnees[0]!=777 && ((x-xEvent)>1 || (xEvent-x)>1 || (y-yEvent)>1 || (yEvent-y)>1 || (x==xEvent && y==yEvent)));
			}while(coordonnees[0]!=777 && (tableauAffichage[yEvent][xEvent]!=15 && tableauAffichage[yEvent][xEvent]!=14));
		}
		return coordonnees;
	}

	/**
	 * boucle tant que l'action ne correspond pas à certaines touchent avec une action particuliere dans le jeu
	 * @param plateauDuJeu
	 * @return un tableau de coordonnees
	 */
	public int [] getCoordonneesClic(Plateau plateauDuJeu, int x, int y, Joueur joueur){
		int [] res= new int[2];
		int keyCode = 0;
		InputEvent event;

		do{
			event=  plateauDuJeu.waitEvent(1500);	
			if(event instanceof KeyEvent && ((((KeyEvent) event).getKeyCode()!=96 && (x!=999 || ((KeyEvent) event).getKeyCode()==32 || ((KeyEvent) event).getKeyCode()==27 || ((KeyEvent) event).getKeyCode()==127)) || (joueur!=null && ((KeyEvent) event).getKeyCode()==96)))
				keyCode=((KeyEvent) event).getKeyCode() ;
			if(plateauDuJeu.getId()==1)
				keyCode=32;
			else if(plateauDuJeu.getId()==2)
				keyCode=27;
			else if(plateauDuJeu.getId()==3)
				keyCode=127;
		}while(!(event instanceof MouseEvent) && keyCode!=32 && keyCode!=27 && keyCode!=127 && (keyCode<97 && keyCode>105) && keyCode!=96);

		if(event instanceof MouseEvent){
			res[0]=plateauDuJeu.getX((MouseEvent) event) ;
			res[1]=plateauDuJeu.getY((MouseEvent) event) ;
		}else if(event instanceof KeyEvent || plateauDuJeu.getId()>0){
			if(keyCode==32){
				res[0]=999;
				res[1]=999;
			}else if(keyCode==27){
				res[0]=888;
				res[1]=888;
			}else if(keyCode==127){
				res[0]=777;
				res[1]=777;
			}else if(keyCode==96){
				res[0]=joueur.getLigneBateau();
				res[1]=joueur.getColonneBateau();
			}else if(keyCode==97){
				res[0]=x+1;
				res[1]=y-1;
			}else if(keyCode==98){
				res[0]=x+1;
				res[1]=y;
			}else if(keyCode==99){
				res[0]=x+1;
				res[1]=y+1;
			}else if(keyCode==100){
				res[0]=x;
				res[1]=y-1;
			}else if(keyCode==101){
				res[0]=x;
				res[1]=y;
			}else if(keyCode==102){
				res[0]=x;
				res[1]=y+1;
			}else if(keyCode==103){
				res[0]=x-1;
				res[1]=y-1;
			}else if(keyCode==104){
				res[0]=x-1;
				res[1]=y;
			}else if(keyCode==105){
				res[0]=x-1;
				res[1]=y+1;
			}
		}
		return res;
	}

}
