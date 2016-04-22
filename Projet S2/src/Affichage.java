import java.awt.Color;
import java.util.ArrayList;


public class Affichage {

	private int[][] tableauAffichageJ1, tableauAffichageJ2;
	private ArrayList<int[][]> tableaux=new ArrayList<>();
	private ArrayList<Plateau> plateaux=new ArrayList<>();
	private String[] gifs = new String[]{"img/rocher.png","img/1.navire.png","img/2.navire.png","img/coffre.png","img/mer.png","img/1.explorateur.png","img/1.voleur.png","img/1.piegeur.png","img/1.guerrier.png","img/2.explorateur.png","img/2.voleur.png","img/2.piegeur.png","img/2.guerrier.png","img/cadavre.png"};
	private Plateau plateauDuJeuJ1, plateauDuJeuJ2;

	public Affichage(int [][] tableauAffichage, ile ileDuJeu, Joueur[] joueur){
		tableauAffichageJ1=new int [tableauAffichage.length][tableauAffichage.length];
		tableauAffichageJ2=new int [tableauAffichage.length][tableauAffichage.length];
		tableaux.add(tableauAffichageJ1);
		tableaux.add(tableauAffichageJ2);
		
		this.updateTableauAffichage(ileDuJeu, tableauAffichage);
		this.updateTableauAffichageJoueur(ileDuJeu,joueur[0], tableauAffichageJ1);
		this.updateTableauAffichageJoueur(ileDuJeu,joueur[1], tableauAffichageJ2);
		
		plateauDuJeuJ1= new Plateau(gifs,ileDuJeu.getTableau().length);
		plateauDuJeuJ2= new Plateau(gifs,ileDuJeu.getTableau().length);
		plateauDuJeuJ1.setTitle("Chasse au tresor");
		plateauDuJeuJ2.setTitle("Chasse au tresor");
		
		plateaux.add(plateauDuJeuJ1);
		plateaux.add(plateauDuJeuJ2);
		plateaux.get(0).setJeu(tableauAffichageJ1);
		plateaux.get(1).setJeu(tableauAffichageJ2);
	}

	/**
	 * Met � jour le TableauAffichage
	 */
	private void updateTableauAffichage(ile ileDuJeu,int[][] tableauAffichage){
		for(int i= 0; i<ileDuJeu.getTableau().length;i++){
			for(int j = 0; j<ileDuJeu.getTableau()[0].length;j++){
				tableauAffichage[i][j] = ileDuJeu.getTableau()[j][i].getId();
			}
		}
	}

	private void updateTableauAffichageJoueur(ile ileDuJeu, Joueur joueur, int[][] tableau){
		for(int i= 1; i<ileDuJeu.getTableau().length-1;i++){
			for(int j = 1; j<ileDuJeu.getTableau()[0].length-1;j++){
				if(ileDuJeu.getTableau()[j][i].getId()>5 && ileDuJeu.getTableau()[j][i].getPersonnageCourant().getEquipe()==joueur.getEquipe() || ileDuJeu.getTableau()[j][i].getId()==joueur.getIdBateau()){
					for(int x=i-1;x<i+2;x++){
						for(int y=j-1;y<j+2;y++){
							tableau[x][y] = ileDuJeu.getTableau()[y][x].getId();
						}
					}
				}
			}
		}
	} 

	/**
	 * Permet l'affichage du plateau de Jeu
	 */
	public void affichageDuJeu(ile ileDuJeu, int[][] tableauAffichage){
		System.out.println("\n"+ileDuJeu.toString());
		this.updateTableauAffichage(ileDuJeu, tableauAffichage);
		plateaux.get(0).setJeu(tableauAffichage);
		plateaux.get(0).affichage();
	}

	public void affichageDuJeuJoueur(ile ileDuJeu, int[][] tableauAffichage, Joueur joueur, int equipe){
		System.out.println("\n"+ileDuJeu.toString());
		this.updateTableauAffichageJoueur(ileDuJeu,joueur, tableaux.get(equipe));
		this.updateTableauAffichage(ileDuJeu, tableauAffichage);
		plateaux.get(equipe).setJeu(tableaux.get(equipe));
		plateaux.get(equipe).affichage();
	}

	public Plateau getPlateau(int equipe){
		return plateaux.get(equipe);
	}

	public void setHighlight(int[]cordonnees, int equipe){
		plateaux.get(equipe).setHighlight(cordonnees[0], cordonnees[1], Color.BLUE);
	}

}
