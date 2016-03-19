/**
 * Classe principale pour lancer le jeu
 * @author Allan
 * @version 1.0
 */
import javax.swing.JOptionPane;

public class main {
	
	public static void main(String[] args) {
		JOptionPane entreeTaille= new JOptionPane();
		boolean nb = false;
		String taille ;
		do{
		
		taille = entreeTaille.showInputDialog("Choisir la taille du plateau: ");
		if(taille.matches("[0-9]+")){
			nb = true;
		}
		
		}while(!nb);
		String proportion = entreeTaille.showInputDialog("Entrez la proportion de rochers: ");
		int taillenb = Integer.parseInt(taille);
		int proportionNb = Integer.parseInt(proportion);
		ile plateau = new ile(taillenb, proportionNb);
		int[][] plateauAffichage = new int[plateau.plateau.length][plateau.plateau.length];
		String[] gifs = new String[]{"img/rocher.png","img/1.navire.png","img/2.navire.png","img/coffre.png","img/mer.png"};
		SuperPlateau platjeu = new SuperPlateau(gifs,taillenb);
		System.out.println(plateau.toString());
		for(int i= 0; i<plateau.plateau.length;i++){
			for(int j = 0; j<plateau.plateau[0].length;j++){
				plateauAffichage[i][j] = plateau.plateau[j][i].id;
			}
		}
		platjeu.setJeu(plateauAffichage);
		platjeu.affichage();
		
	}

}
