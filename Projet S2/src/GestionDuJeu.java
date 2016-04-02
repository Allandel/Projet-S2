import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class GestionDuJeu {
	private ile ileDuJeu;
	private int[][] tableauAffichage;
	private String[] gifs = new String[]{"img/rocher.png","img/1.navire.png","img/2.navire.png","img/coffre.png","img/mer.png","img/1.explorateur.png","img/1.voleur.png"};
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
		System.out.println("\n"+ileDuJeu.toString());
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

		res[0]=plateauDuJeu.getX((MouseEvent) event) ;
		res[1]=plateauDuJeu.getY((MouseEvent) event) ;
		return res;
	}

	public void tourDuJoueur(){
		int[] coordonnees =new int[2];
		int xEvent,yEvent;

		do{
			coordonnees=this.getCoordonneesClic();
			xEvent=coordonnees[0];
			yEvent=coordonnees[1];
		}while(tableauAffichage[yEvent][xEvent]<2 || tableauAffichage[yEvent][xEvent]==5);

		plateauDuJeu.setHighlight(xEvent, yEvent, Color.BLUE);

		if(tableauAffichage[yEvent][xEvent]>=6){ //ajouter l'id du voleur quand on aura fini le voleur
			this.actionPerso(xEvent,yEvent,ileDuJeu.getTableau()[xEvent][yEvent].getPersonnageCourant());
		}

		this.affichageDuJeu();
	}

	private void actionPerso(int x, int y, Personnage perso){
		int[] coordonnees = new int[2];
		int xEvent=0,yEvent=0;

		for(int i=y-1;i<y+2;i++){
			for(int j=x-1;j<x+2;j++){
				if(perso instanceof Explorateur){
					if((tableauAffichage[i][j]<3 && (((i==(y-1) || i==(y+1)) && j==x) || ((j==(x-1) || j==(x+1)) && i==y))))
						plateauDuJeu.setHighlight(j, i, Color.BLUE);
				}else
					plateauDuJeu.setHighlight(j, i, Color.BLUE);
			}
		}

		if(perso instanceof Explorateur){
			do{
				coordonnees=this.getCoordonneesClic();
				xEvent=coordonnees[0];
				yEvent=coordonnees[1];
			}while(!(((yEvent==(y-1) || yEvent==(y+1)) && xEvent==x) || ((xEvent==(x-1) || xEvent==(x+1)) && yEvent==y)) || tableauAffichage[yEvent][xEvent]>2);

			if(tableauAffichage[yEvent][xEvent] == 1 )
				ileDuJeu.getTableau()[xEvent][yEvent].interactionRocher(perso);

		}else if(perso instanceof Voleur){
			do{
				coordonnees=this.getCoordonneesClic();
				xEvent=coordonnees[0];
				yEvent=coordonnees[1];
			}while((x-xEvent)>1 || (xEvent-x)>1 || (y-yEvent)>1 || (yEvent-y)>1 || (tableauAffichage[yEvent][xEvent]!=0 && tableauAffichage[yEvent][xEvent]!=2));
		}

		if(tableauAffichage[yEvent][xEvent]==0)
			ileDuJeu.mouvement(x,y,xEvent, yEvent, perso);
		else if(tableauAffichage[yEvent][xEvent]==2){
			if(ileDuJeu.getTableau()[xEvent][yEvent].entreeBateau(perso));
			ileDuJeu.getTableau()[x][y].removePersonnageCourant();
		}

		for(int i=y-1;i<y+2;i++){
			for(int j=x-1;j<x+2;j++){
				plateauDuJeu.resetHighlight(j, i);
			}
		}

	}
}
