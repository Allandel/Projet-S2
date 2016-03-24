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
		SuperPlateau platjeu = new SuperPlateau(gifs,taillenb);
		System.out.println(plateau.toString());
		for(int i= 0; i<plateau.getPlateau().length;i++){
			for(int j = 0; j<plateau.getPlateau()[0].length;j++){
				plateauAffichage[i][j] = plateau.getPlateau()[j][i].getId();
			}
		}
		platjeu.setJeu(plateauAffichage);
		platjeu.affichage();
		event=  plateau[equipe].waitEvent(20000);
		if (event instanceof MouseEvent) {
			int x = getX((MouseEvent) event) ;
			int y = getY((MouseEvent) event) ;
			System.out.println(x+y);
		}
		
	}

}
