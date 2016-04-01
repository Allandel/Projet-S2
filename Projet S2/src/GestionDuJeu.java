import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class GestionDuJeu {
	private ile ileDuJeu;
	private int[][] tableauAffichage;
	private String[] gifs = new String[]{"img/rocher.png","img/1.navire.png","img/2.navire.png","img/coffre.png","img/mer.png","img/1.explorateur.png"};
	private Plateau plateauDuJeu;

	public GestionDuJeu(){
		int longueurLigne, proportionNb;
		JOptionPane selectionTaille= new JOptionPane();
		String ligne, proportion;
		boolean choisi=false;

		do{
			ligne= selectionTaille.showInputDialog("Choisir la taille du plateau, 10 minimum : ");
			if(ligne.matches("[0-9]+")){
				longueurLigne= Integer.parseInt(ligne);
				if(longueurLigne>=10)
					choisi = true;
			}
		}while(!choisi);

		choisi=false;

		do{
			proportion = selectionTaille.showInputDialog("Entrez la proportion de rochers: ");
			if(proportion.matches("[0-9]+")){
				choisi = true;
			}	
		}while(!choisi);

		longueurLigne = Integer.parseInt(ligne);
		proportionNb = Integer.parseInt(proportion);
		ileDuJeu = new ile(longueurLigne, proportionNb);
		tableauAffichage = new int[ileDuJeu.getTableau().length][ileDuJeu.getTableau().length];

		this.updateTableauAffichage();
		this.setPlateau();
		this.affichageDuJeu();
	}

	private void updateTableauAffichage(){
		for(int i= 0; i<ileDuJeu.getTableau().length;i++){
			for(int j = 0; j<ileDuJeu.getTableau()[0].length;j++){
				tableauAffichage[i][j] = ileDuJeu.getTableau()[j][i].getId();
			}
		}
	}

	private void setPlateau(){
		plateauDuJeu = new Plateau(gifs,ileDuJeu.getTableau().length);
		plateauDuJeu.setTitle("Chasse au trésor");
		plateauDuJeu.setJeu(tableauAffichage);
	}

	private void affichageDuJeu(){
		System.out.println(ileDuJeu.toString());
		this.updateTableauAffichage();
		plateauDuJeu.setJeu(tableauAffichage);
		plateauDuJeu.affichage();
	}

	private int [] getCoordonneesClic(){
		int [] res= new int[2];
		InputEvent event;

		do{
			event=  plateauDuJeu.waitEvent();	
		}while(!(event instanceof MouseEvent));

		if (event instanceof MouseEvent) {
			res[0]=plateauDuJeu.getX((MouseEvent) event) ;
			res[1]=plateauDuJeu.getY((MouseEvent) event) ;
		}
		return res;
	}

	public void tourDuJoueur(){
		int[] coordonnees1 =new int[2], coordonnees2 = new int[2];
		int xEvent1,yEvent1, xEvent2, yEvent2;

		do{
			coordonnees1=this.getCoordonneesClic();
			xEvent1=coordonnees1[0];
			yEvent1=coordonnees1[1];
		}while(tableauAffichage[yEvent1][xEvent1]<2 || tableauAffichage[yEvent1][xEvent1]==5);
		
		plateauDuJeu.setHighlight(xEvent1, yEvent1, Color.BLUE);
		plateauDuJeu.affichage();
		
		if(tableauAffichage[yEvent1][xEvent1]==6){
		}
	}

	private void deplacement(){
		
	}
}
