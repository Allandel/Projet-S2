/**
 * Classe principale pour lancer le jeu
 * @author Allan
 * @version 1.0
 */
import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class main {

	public static void main(String[] args) {
		JOptionPane entreeTaille= new JOptionPane();
		boolean nb = false;
		String taille, proportion ;
		int taillenb, proportionNb;
		InputEvent event;
		boolean findujeu=false;//temporaire afin de faire tourner les d�placements.
		do{

			taille = entreeTaille.showInputDialog("Choisir la taille du plateau, 10 minimum : ");
			if(taille.matches("[0-9]+")){
				taillenb = Integer.parseInt(taille);
				if(taillenb>=10)
					nb = true;
			}

		}while(!nb);

		nb=false;

		do{
			proportion = entreeTaille.showInputDialog("Entrez la proportion de rochers: ");
			if(proportion.matches("[0-9]+")){
				nb = true;
			}	
		}while(!nb);

		taillenb = Integer.parseInt(taille);
		proportionNb = Integer.parseInt(proportion);
		ile ileDuJeu = new ile(taillenb, proportionNb);

		int[][] plateauAffichage = new int[ileDuJeu.getPlateau().length][ileDuJeu.getPlateau().length];
		String[] gifs = new String[]{"img/rocher.png","img/1.navire.png","img/2.navire.png","img/coffre.png","img/mer.png","img/1.explorateur.png"};

		System.out.println(ileDuJeu.toString());
		for(int i= 0; i<ileDuJeu.getPlateau().length;i++){
			for(int j = 0; j<ileDuJeu.getPlateau()[0].length;j++){
				plateauAffichage[i][j] = ileDuJeu.getPlateau()[j][i].getId();
			}
		}


		Plateau platjeu = new Plateau(gifs,taillenb);
		platjeu.setTitle("Chasse au trésor");
		platjeu.setJeu(plateauAffichage);
		platjeu.affichage();

		int xEvent1,yEvent1, xEvent2, yEvent2, cpt=0;

		do{
			xEvent1=0;
			yEvent1=0;
			xEvent2=0;
			yEvent2=0;
			do{
				platjeu.resetHighlight(xEvent1, yEvent1);
				event=  platjeu.waitEvent();
				if (event instanceof MouseEvent) {
					xEvent1=platjeu.getX((MouseEvent) event) ;
					yEvent1=platjeu.getY((MouseEvent) event) ;
				}
				platjeu.setHighlight(xEvent1, yEvent1, Color.BLUE);
				System.out.print("1");
				System.out.println(" - "+plateauAffichage[yEvent1][xEvent1]);
			}while(plateauAffichage[yEvent1][xEvent1]==0 || plateauAffichage[yEvent1][xEvent1]==1 || plateauAffichage[yEvent1][xEvent1]==5);

			System.out.println("2");

			if(plateauAffichage[yEvent1][xEvent1]==2){
				ileDuJeu.getPlateau()[xEvent1][yEvent1].sortieBateau();
			}

			if(plateauAffichage[yEvent1][xEvent1]==6){
				if(plateauAffichage[yEvent1+1][xEvent1]==0)
					platjeu.setHighlight(xEvent1, yEvent1+1, Color.blue);
				if(plateauAffichage[yEvent1-1][xEvent1]==0)
					platjeu.setHighlight(xEvent1, yEvent1-1, Color.blue);
				if(plateauAffichage[yEvent1][xEvent1+1]==0)
					platjeu.setHighlight(xEvent1+1, yEvent1, Color.blue);
				if(plateauAffichage[yEvent1][xEvent1-1]==0)
					platjeu.setHighlight(xEvent1-1, yEvent1, Color.blue);
				do{	
					event=platjeu.waitEvent();

					if(event instanceof MouseEvent){
						xEvent2=platjeu.getX((MouseEvent) event);
						yEvent2=platjeu.getY((MouseEvent) event);
					}
					System.out.println("3");
				}while((xEvent1-xEvent2)>1 || (yEvent1-yEvent2)>1 || (xEvent2-xEvent1)>1 || (yEvent2-yEvent1)>1);
				System.out.println("4");
				
				if(plateauAffichage[yEvent2][xEvent2]==0)
					ileDuJeu.mouvement(xEvent1,yEvent1,xEvent2, yEvent2, ileDuJeu.getPlateau()[xEvent1][yEvent1].getPersonnageCourant());
				if(plateauAffichage[yEvent2][xEvent2]==2){
					ileDuJeu.getPlateau()[xEvent2][yEvent2].entreeBateau(ileDuJeu.getPlateau()[xEvent1][yEvent1].getPersonnageCourant());
					ileDuJeu.getPlateau()[xEvent1][yEvent1].removePersonnageCourant();
				}
				for(int i= 0; i<ileDuJeu.getPlateau().length;i++){
					for(int j = 0; j<ileDuJeu.getPlateau()[0].length;j++){
						plateauAffichage[i][j] = ileDuJeu.getPlateau()[j][i].getId();
					}
				}
				platjeu.setJeu(plateauAffichage);
				platjeu.affichage();
				System.out.println(ileDuJeu.toString());
				cpt++;
			}
		}while(cpt!=10);
	}
}