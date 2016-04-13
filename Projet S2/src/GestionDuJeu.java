import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
/**
 * Classe permettant la gestion du jeu en organisant les tours de jeu et les possibilites
 * @author Valentin
 * @version 1.1 
 */
public class GestionDuJeu {
	private ile ileDuJeu;
	private int[][] tableauAffichage;
	private String[] gifs = new String[]{"img/rocher.png","img/1.navire.png","img/2.navire.png","img/coffre.png","img/mer.png","img/1.explorateur.png","img/1.voleur.png","img/1.piegeur.png","img/2.explorateur.png","img/2.voleur.png","img/2.piegeur.png","img/cadavre.png"};
	private Plateau plateauDuJeu;
	/**
	 * Constructeur initialisant le plateau de jeu de base
	 */
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
		this.setPlateauDujeu();
		this.affichageDuJeu();
	}
	/**
	 * Met � jour le TableauAffichage
	 */
	private void updateTableauAffichage(){
		for(int i= 0; i<ileDuJeu.getTableau().length;i++){
			for(int j = 0; j<ileDuJeu.getTableau()[0].length;j++){
				tableauAffichage[i][j] = ileDuJeu.getTableau()[j][i].getId();
			}
		}
	}
	/**
	 * Initialise le Plateau de jeu avec son nom et le tableau de gifs associes au gifs presents sur le terrain
	 */
	private void setPlateauDujeu(){
		plateauDuJeu = new Plateau(gifs,ileDuJeu.getTableau().length);
		plateauDuJeu.setTitle("Chasse au tresor");
		plateauDuJeu.setJeu(tableauAffichage);
	}
	/**
	 * Permet l'affichage du plateau de Jeu
	 */
	private void affichageDuJeu(){
		System.out.println("\n"+ileDuJeu.toString());
		this.updateTableauAffichage();
		plateauDuJeu.setJeu(tableauAffichage);
		plateauDuJeu.affichage();
	}
	/**
	 * Recupere les coordonnees de clic 
	 */
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
	/**
	 * Organise la succession d'action possible pour le joueur
	 */
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
	/**
	 * Permet a un personnage d'entrer dans le bateau
	 * @param perso
	 * @param x
	 * @param y
	 */
	private void actionPerso(int x, int y, Personnage perso){
		int[] coordonnees = new int[2];
		int xEvent=0,yEvent=0;

		//	if (!perso.getDeath()){
		for(int i=y-1;i<y+2;i++){
			for(int j=x-1;j<x+2;j++){
				if(perso instanceof Voleur){
					if(tableauAffichage[i][j]==0 ||tableauAffichage[i][j]==perso.getIdBateau() || tableauAffichage[i][j]>5)
						plateauDuJeu.setHighlight(j, i, Color.BLUE);	
				}else if(tableauAffichage[i][j]<2 || tableauAffichage[i][j]==perso.getIdBateau() || tableauAffichage[i][j]==4 || tableauAffichage[i][j]==12){
					if(((i==(y-1) || i==(y+1)) && j==x) || ((j==(x-1) || j==(x+1)) && i==y))
						plateauDuJeu.setHighlight(j, i, Color.BLUE);
				}
			}
		}

		/*
		 * || ileDuJeu.getTableau()[j][i].getPersonnageCourant().getEquipe()==perso.getEquipe()
		 * retourne un null pointer exception si il n'y a pas de perso dans la case
		 */


		if(perso instanceof Explorateur){
			do{
				do{
					coordonnees=this.getCoordonneesClic();
					xEvent=coordonnees[0];
					yEvent=coordonnees[1];
				}while(!(((yEvent==(y-1) || yEvent==(y+1)) && xEvent==x) || ((xEvent==(x-1) || xEvent==(x+1)) && yEvent==y)));
			}while(tableauAffichage[yEvent][xEvent]>1 && tableauAffichage[yEvent][xEvent]!=perso.getIdBateau() && tableauAffichage[yEvent][xEvent]!=4 && tableauAffichage[yEvent][xEvent]!=12);

			if(tableauAffichage[yEvent][xEvent] == 1 || tableauAffichage[yEvent][xEvent] == 4){
				((Explorateur)perso).interactionRocher(xEvent, yEvent, ileDuJeu.getTableau());
			}
		}else if(perso instanceof Voleur){
			do{
				do{
					coordonnees=this.getCoordonneesClic();
					xEvent=coordonnees[0];
					yEvent=coordonnees[1];
				}while((x-xEvent)>1 || (xEvent-x)>1 || (y-yEvent)>1 || (yEvent-y)>1 || (x==xEvent && y==yEvent));
			}while(tableauAffichage[yEvent][xEvent]!=0 && tableauAffichage[yEvent][xEvent]!=perso.getIdBateau() && tableauAffichage[yEvent][xEvent]<5 && tableauAffichage[yEvent][xEvent]!=12);
		}
		//}

		if(tableauAffichage[yEvent][xEvent]==0){
			perso.mouvement(x, y, xEvent, yEvent, ileDuJeu.getTableau());
		}else if(tableauAffichage[yEvent][xEvent]==perso.getIdBateau()){
			perso.entreeBateau(x, y, xEvent, yEvent, ileDuJeu.getTableau());
		}else if(tableauAffichage[yEvent][xEvent]==12){
			perso.recuperationStuff(x,y,xEvent,yEvent, ileDuJeu.getTableau());
		}else if(tableauAffichage[yEvent][xEvent]>5 && tableauAffichage[yEvent][xEvent]<12 && perso.getEquipe()==ileDuJeu.getTableau()[xEvent][yEvent].getPersonnageCourant().getEquipe()){
			perso.echangeObjet(ileDuJeu.getTableau()[xEvent][yEvent].getPersonnageCourant());
		}else if(tableauAffichage[yEvent][xEvent]>5 && tableauAffichage[yEvent][xEvent]<12 && perso.getEquipe()!=ileDuJeu.getTableau()[xEvent][yEvent].getPersonnageCourant().getEquipe() && perso instanceof Voleur){
			((Voleur) perso).volerObjet(ileDuJeu.getTableau()[xEvent][yEvent].getPersonnageCourant());
		}
		System.out.println(perso.getEnergie());
	}
}
