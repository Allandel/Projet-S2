import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Sert a l'affichage du jeu
 * @author louis
 *
 */
public class Affichage {

	private int[][] tableauAffichageJ1, tableauAffichageJ2;
	private ArrayList<int[][]> tableaux=new ArrayList<>();
	private ArrayList<Plateau> plateaux=new ArrayList<>();
	private String[] images = new String[]{"img/rocher.jpg","img/1.navire.jpg","img/2.navire.jpg","img/coffre.jpg","img/mer.jpg",
			"img/1.explorateur.jpg","img/1.voleur.jpg","img/1.piegeur.jpg","img/1.guerrier.jpg",
			"img/2.explorateur.jpg","img/2.voleur.jpg","img/2.piegeur.jpg","img/2.guerrier.jpg",
			"img/death.jpg","img/herbe.jpg","img/piege.jpg"};
	private Plateau plateauDuJeuJ1, plateauDuJeuJ2;

	
	public Affichage(boolean [] gagner){
		String gagnant="";
		if(gagner[1])
			gagnant="Joueur 1 ";
		else
			gagnant="Joueur 2 ";
		Object[] options = { "OK" };
		JOptionPane.showOptionDialog(null, "Bravo ! Le "+gagnant+"a gagne la partie", "Victoire",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0]);
	
	}
	
	/**
	 * Initialise et permet l'affichage du plateau de jeu pour chaque joueur
	 * @param tableauAffichage
	 * @param ileDuJeu
	 * @param joueur
	 */
	public Affichage(int [][] tableauAffichage, ile ileDuJeu, Joueur[] joueur){
		tableauAffichageJ1=new int [tableauAffichage.length][tableauAffichage.length];
		tableauAffichageJ2=new int [tableauAffichage.length][tableauAffichage.length];
		tableaux.add(tableauAffichageJ1);
		tableaux.add(tableauAffichageJ2);

		plateauDuJeuJ1= new Plateau(images,ileDuJeu.getTableau().length);
		plateauDuJeuJ2= new Plateau(images,ileDuJeu.getTableau().length);
		plateauDuJeuJ1.setTitle("Chasse au tresor");
		plateauDuJeuJ2.setTitle("Chasse au tresor");

		plateaux.add(plateauDuJeuJ1);
		plateaux.add(plateauDuJeuJ2);

		this.updateTableauAffichage(ileDuJeu, tableauAffichage);
		this.updateTableauAffichageJoueur(ileDuJeu,joueur[0], tableauAffichageJ1, 0);
		this.updateTableauAffichageJoueur(ileDuJeu,joueur[1], tableauAffichageJ2, 1);

		plateaux.get(0).setJeu(tableauAffichageJ1);
		plateaux.get(1).setJeu(tableauAffichageJ2);
	}

	/**
	 * Met a jour le TableauAffichage
	 * @param ileDuJeu
	 * @param tableauAffichage
	 */
	private void updateTableauAffichage(ile ileDuJeu,int[][] tableauAffichage){
		for(int i= 0; i<ileDuJeu.getTableau().length;i++){
			for(int j = 0; j<ileDuJeu.getTableau()[0].length;j++){
				tableauAffichage[i][j] = ileDuJeu.getTableau()[j][i].getId();
			}
		}
	}

	/**
	 * Met à jour le tableau d'affichage specifique au joueur
	 * Ne met a jour que ce qui est dans le champ de vision d'un personnage du joueur
	 * @param ileDuJeu
	 * @param joueur
	 * @param tableau
	 * @param equipe
	 */
	private void updateTableauAffichageJoueur(ile ileDuJeu, Joueur joueur, int[][] tableau, int equipe){
		for(int i= 1; i<ileDuJeu.getTableau().length-1;i++){
			for(int j = 1; j<ileDuJeu.getTableau()[0].length-1;j++){

				if(ileDuJeu.getTableau()[j][i].getId()>5 && ileDuJeu.getTableau()[j][i].getId()<14 && ileDuJeu.getTableau()[j][i].getPersonnageCourant().getEquipe()==joueur.getEquipe() || ileDuJeu.getTableau()[j][i].getId()==joueur.getIdBateau()){
					for(int x=i-1;x<i+2;x++){
						for(int y=j-1;y<j+2;y++){
							if(ileDuJeu.getTableau()[y][x].getId()==4 && !joueur.getCoffreTrouve())
								tableau[x][y] = 1;
							else if(ileDuJeu.getTableau()[y][x].getPiege() && ileDuJeu.getTableau()[y][x].getId()==15 && ileDuJeu.getTableau()[y][x].getTeamPiege()==equipe)
								tableau[x][y]=16;
							else
								tableau[x][y] = ileDuJeu.getTableau()[y][x].getId();
						}
					}
				}
			}
		}
	} 
 
	/**
	 * Brouillard de gueurre
	 * Grise les cases qui ne sont pas dans le champ de vision du joueur
	 * @param ileDuJeu
	 * @param equipe
	 * @param joueur
	 */
	private void brouillard(ile ileDuJeu, int equipe, Joueur joueur){
		for(int i= 1; i<ileDuJeu.getTableau().length-1;i++){
			for(int j = 1; j<ileDuJeu.getTableau()[0].length-1;j++){
				plateaux.get(equipe).setHighlight(i, j, Color.BLACK);
			}
		}
		for(int i= 1; i<ileDuJeu.getTableau().length-1;i++){
			for(int j = 1; j<ileDuJeu.getTableau()[0].length-1;j++){
				if(ileDuJeu.getTableau()[j][i].getId()>5 && ileDuJeu.getTableau()[j][i].getId()<14 && ileDuJeu.getTableau()[j][i].getPersonnageCourant().getEquipe()==joueur.getEquipe() || ileDuJeu.getTableau()[j][i].getId()==joueur.getIdBateau()){
					for(int x=i-1;x<i+2;x++){
						for(int y=j-1;y<j+2;y++){
							plateaux.get(equipe).resetHighlight(y, x);
						}
					}
				}
			}
		}

	}

	/**
	 * Highlight les personnages du joueur qui ont encore la possibilite de se deplacer ou faire une action
	 * @param ileDuJeu
	 * @param equipe
	 * @param joueur
	 */
	private void affichagePersoActionnable(ile ileDuJeu, int equipe, Joueur joueur){
		for(int i= 1; i<ileDuJeu.getTableau().length-1;i++){
			for(int j = 1; j<ileDuJeu.getTableau()[0].length-1;j++){
				if(ileDuJeu.getTableau()[j][i].getId()>5 && ileDuJeu.getTableau()[j][i].getId()<14 && ileDuJeu.getTableau()[j][i].getPersonnageCourant().getEquipe()==joueur.getEquipe() && ileDuJeu.getTableau()[j][i].getPersonnageCourant().actionOuDeplacement()){
					plateaux.get(equipe).setHighlight(j, i, Color.YELLOW);
				}
			}
		}
	}
	
	/**
	 * Affiche le tableau de jeu specifique au joueur
	 * @param ileDuJeu
	 * @param tableauAffichage
	 * @param joueur
	 * @param equipe
	 */
	public void affichageDuJeuJoueur(ile ileDuJeu, int[][] tableauAffichage, Joueur joueur, int equipe){
		System.out.println("\n"+ileDuJeu.toString());
		this.updateTableauAffichageJoueur(ileDuJeu,joueur, tableaux.get(equipe),equipe);
		this.updateTableauAffichage(ileDuJeu, tableauAffichage);
		plateaux.get(equipe).setJeu(tableaux.get(equipe));
		plateaux.get(equipe).affichage();
		this.brouillard(ileDuJeu, equipe, joueur);
		this.affichagePersoActionnable(ileDuJeu, equipe, joueur);
	}
	/**
	 * retourne le plateau specifique au joueur
	 * @param equipe
	 * @return
	 */
	public Plateau getPlateau(int equipe){
		return plateaux.get(equipe);
	}

	/**
	 * Highlight la coordonnees du plateau du joueur
	 * @param cordonnees
	 * @param equipe
	 */
	public void setHighlight(int[]cordonnees, int equipe){
		plateaux.get(equipe).setHighlight(cordonnees[0], cordonnees[1], Color.BLUE);
	}
}
