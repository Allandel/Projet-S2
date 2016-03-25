/**
 * Classe principale pour lancer le jeu
 * @author Allan
 * @version 1.0
 */
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class main {

	public static void main(String[] args) {
		JOptionPane entreeTaille= new JOptionPane();
		boolean nb = false;
		String taille, proportion ;
		int taillenb, proportionNb;
		Personnage Explo1=new Explorateur();
		InputEvent event;
		boolean findujeu=false;//temporaire afin de faire tourner les déplacements.
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
		ileDuJeu.setPersonnage1(Explo1);
		int[][] plateauAffichage = new int[ileDuJeu.getPlateau().length][ileDuJeu.getPlateau().length];
		int[][] plateauAffichageJ1 = new int[ileDuJeu.getPlateau().length][ileDuJeu.getPlateau().length];
		int[][] plateauAffichageJ2 = new int[ileDuJeu.getPlateau().length][ileDuJeu.getPlateau().length];
		String[] gifs = new String[]{"img/rocher.png","img/1.navire.png","img/2.navire.png","img/coffre.png","img/mer.png","img/1.explorateur.png"};
		int navJ1=ileDuJeu.getNavJ1(), navJ2=ileDuJeu.getNavJ2();

		System.out.println(ileDuJeu.toString());
		for(int i= 0; i<ileDuJeu.getPlateau().length;i++){
			for(int j = 0; j<ileDuJeu.getPlateau()[0].length;j++){
				plateauAffichage[i][j] = ileDuJeu.getPlateau()[j][i].getId();
			}
		}

		for(int i=0;i<3;i++){
			for(int cpt=navJ1-1;cpt<navJ1+2;cpt++){
				plateauAffichageJ1[i][cpt]=plateauAffichage[i][cpt];
			}
		}

		for(int i=taillenb-3;i<taillenb;i++){
			for(int cpt=navJ2-1;cpt<navJ2+2;cpt++){
				plateauAffichageJ2[i][cpt]=plateauAffichage[i][cpt];
			}
		}

		Plateau [] platjeu = new Plateau[2];
		platjeu[0] = new Plateau(gifs,taillenb);
		platjeu[0].close();
		platjeu[1] = new Plateau(gifs,taillenb);
		platjeu[1].close();
		platjeu[0].setJeu(plateauAffichage);		
		platjeu[0].affichage();

		int x=0,y=0;

		do{	
			do{
				event=  platjeu[0].waitEvent();
				if (event instanceof MouseEvent) {
					x=platjeu[0].getX((MouseEvent) event) ;
					y=platjeu[0].getY((MouseEvent) event) ;
				}
			}while(x!=Explo1.getX() || y!=Explo1.getY());
			event=platjeu[0].waitEvent();
			if(event instanceof MouseEvent){
				x=platjeu[0].getX((MouseEvent) event);
				y=platjeu[0].getY((MouseEvent) event);
				if(ileDuJeu.mouvement(x,y,Explo1)){
					Explo1.setCoordonnées(x, y);
					for(int i= 0; i<ileDuJeu.getPlateau().length;i++){
						for(int j = 0; j<ileDuJeu.getPlateau()[0].length;j++){
							plateauAffichage[i][j] = ileDuJeu.getPlateau()[j][i].getId();

						}
					}
					platjeu[0].setJeu(plateauAffichage);
				}
			}

		}while(findujeu!=true);
		/*
		int x=0,y=0;
		do{
			event=  platjeu[0].waitEvent();
			if (event instanceof MouseEvent) {
				x=platjeu[0].getX((MouseEvent) event) ;
				y=platjeu[0].getY((MouseEvent) event) ;
			}
		}while(x!=ileDuJeu.getNavJ1() || y!=1);
		platjeu[0].close();*/

	}
}
