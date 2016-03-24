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
		InputEvent event;
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
		ile plateau = new ile(taillenb, proportionNb);
		int[][] plateauAffichage = new int[plateau.getPlateau().length][plateau.getPlateau().length];
		String[] gifs = new String[]{"img/rocher.png","img/1.navire.png","img/2.navire.png","img/coffre.png","img/mer.png"};
		Plateau [] platjeu = new Plateau[3];
		platjeu[0] = new Plateau(gifs,taillenb);
		platjeu[0].close();
		platjeu[1] = new Plateau(gifs,taillenb);
		platjeu[1].close();
		platjeu[2] = new Plateau(gifs,taillenb);
		platjeu[2].close();
		
		System.out.println(plateau.toString());
		for(int i= 0; i<plateau.getPlateau().length;i++){
			for(int j = 0; j<plateau.getPlateau()[0].length;j++){
				plateauAffichage[i][j] = plateau.getPlateau()[j][i].getId();
			}
		}
		platjeu[0].setJeu(plateauAffichage);		
		platjeu[0].affichage();
		int x,y;
		do{
		event=  platjeu[0].waitEvent();
		x=platjeu[0].getX((MouseEvent) event) ;
		y=platjeu[0].getY((MouseEvent) event) ;
		}while(x!=plateau.getNavJ1() || y!=1);
		platjeu[0].close();
	}

}
