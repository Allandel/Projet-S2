import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Sert a l'affichage du jeu
 * @author louis
 *
 */
public class Affichage {

	private int[][] tableauAffichageJ1, tableauAffichageJ2;
	private ArrayList<int[][]> tableaux=new ArrayList<>();
	private Plateau  plateauDuJeu;
	private String[] images = new String[]{ "img/rocher.jpg",			//id 1
			"img/1.navire.jpg",			//id 2
			"img/2.navire.jpg",			//id 3
			"img/coffre.jpg",			//id 4
			"img/mer.jpg",				//id 5
			"img/1.explorateur.jpg",	//id 6
			"img/1.voleur.jpg",			//id 7
			"img/1.piegeur.jpg",		//id 8
			"img/1.guerrier.jpg",		//id 9
			"img/2.explorateur.jpg",	//id 10
			"img/2.voleur.jpg",			//id 11
			"img/2.piegeur.jpg",		//id 12
			"img/2.guerrier.jpg",		//id 13
			"img/death.jpg",			//id 14
			"img/herbe.jpg",			//id 15
			"img/piege.jpg",			//id 16
			"img/bombe.jpg"				//id 17
	};			

	/**
	 * Affiche un message disant le gagnant du jeu à la fin de la partie
	 * @param gagner
	 */
	public Affichage(boolean [] gagner){
		String gagnant="";
		if(gagner[1])
			gagnant="Joueur 1 ";
		else
			gagnant="Joueur 2 ";
		Object[] options = { "OK" };
		JOptionPane.showOptionDialog(null, "Bravo ! Le "+gagnant+"a gagne la partie", "Victoire",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);

	}

	/**
	 * Affichage du jeu dans le test
	 * @param tableauAffichage
	 * @param ileDuJeu
	 * @param joueur
	 * @param test
	 */
	public Affichage(int [][] tableauAffichage, ile ileDuJeu, Joueur[] joueur, boolean test){
		tableauAffichageJ1=new int [tableauAffichage.length][tableauAffichage[1].length];
		tableaux.add(tableauAffichageJ1);

		plateauDuJeu= new Plateau(images,ileDuJeu.getTableau().length);

		this.updateTableauAffichage(ileDuJeu, tableauAffichageJ1);

		plateauDuJeu.setJeu(tableauAffichageJ1);
		System.out.println("\n"+ileDuJeu.toString());
	}

	/**
	 * Initialise et permet l'affichage du plateau de jeu pour chaque joueur
	 * @param tableauAffichage
	 * @param ileDuJeu
	 * @param joueur
	 */
	public Affichage(int [][] tableauAffichage, ile ileDuJeu, Joueur[] joueur){
		tableauAffichageJ1=new int [tableauAffichage.length][tableauAffichage[1].length];
		tableauAffichageJ2=new int [tableauAffichage.length][tableauAffichage[1].length];
		tableaux.add(tableauAffichageJ1);
		tableaux.add(tableauAffichageJ2);

		plateauDuJeu= new Plateau(images,ileDuJeu.getTableau().length);
		plateauDuJeu.setTitle("Chasse au tresor");

		this.updateTableauAffichage(ileDuJeu, tableauAffichage);

		if(Test.testEnCours)
			plateauDuJeu.setJeu(tableauAffichage);
		else{
			this.updateTableauAffichageJoueur(ileDuJeu,joueur[0], tableauAffichageJ1, 0);
			this.updateTableauAffichageJoueur(ileDuJeu,joueur[1], tableauAffichageJ2, 1);
			plateauDuJeu.setJeu(tableauAffichageJ1);
		}
	}

	/**
	 * Met a jour le TableauAffichage
	 * @param ileDuJeu
	 * @param tableauAffichage
	 */
	private void updateTableauAffichage(ile ileDuJeu,int[][] tableauAffichage){
		for(int i= 0; i<ileDuJeu.getTableau().length;i++){
			for(int j = 0; j<ileDuJeu.getTableau()[1].length;j++){
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

				if(ileDuJeu.getTableau()[j][i].getId()>5 && ileDuJeu.getTableau()[j][i].getId()<14 && ileDuJeu.getTableau()[j][i].getPersonnageCourant().getJoueur()==joueur || ileDuJeu.getTableau()[j][i].getId()==joueur.getIdBateau()){
					for(int x=i-1;x<i+2;x++){
						for(int y=j-1;y<j+2;y++){
							if(ileDuJeu.getTableau()[y][x].getId()==4 && !joueur.getCoffreTrouve())
								tableau[x][y] = 1;
							else if(ileDuJeu.getTableau()[y][x].getPiege() && ileDuJeu.getTableau()[y][x].getId()==15 && (ileDuJeu.getTableau()[y][x].getTeamPiege()==equipe || Test.testEnCours))
								tableau[x][y]=16;
							else if(ileDuJeu.getTableau()[y][x].getBombe() && (ileDuJeu.getTableau()[y][x].getPersonnageCourant()==null || ileDuJeu.getTableau()[y][x].getPersonnageCourant().getDeath()==true))
								tableau[x][y]=17;
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
				plateauDuJeu.setHighlight(i, j, Color.BLACK);
			}
		}
		for(int i= 1; i<ileDuJeu.getTableau().length-1;i++){
			for(int j = 1; j<ileDuJeu.getTableau()[0].length-1;j++){
				if(ileDuJeu.getTableau()[j][i].getId()>5 && ileDuJeu.getTableau()[j][i].getId()<14 && ileDuJeu.getTableau()[j][i].getPersonnageCourant().getJoueur()==joueur || ileDuJeu.getTableau()[j][i].getId()==joueur.getIdBateau()){
					for(int x=i-1;x<i+2;x++){
						for(int y=j-1;y<j+2;y++){
							plateauDuJeu.resetHighlight(y, x);
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
		if(Test.testEnCours)
			equipe=0;
		for(int i= 1; i<ileDuJeu.getTableau().length-1;i++){
			for(int j = 1; j<ileDuJeu.getTableau()[0].length-1;j++){
				if(ileDuJeu.getTableau()[j][i].getId()>5 && ileDuJeu.getTableau()[j][i].getId()<14 && ileDuJeu.getTableau()[j][i].getPersonnageCourant().getJoueur()==joueur && ileDuJeu.getTableau()[j][i].getPersonnageCourant().actionOuDeplacement()){
					plateauDuJeu.setHighlight(j, i, Color.YELLOW);
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
		this.updateTableauAffichageJoueur(ileDuJeu,joueur, tableaux.get(equipe),equipe);
		this.updateTableauAffichage(ileDuJeu, tableauAffichage);
		System.out.println("\n"+ileDuJeu.toString());

		if(Test.testEnCours){
			this.updateTableauAffichageJoueur(ileDuJeu,joueur, tableauAffichage,equipe);
			plateauDuJeu.setJeu(tableauAffichage);
		}else{
			if(equipe==0)
				plateauDuJeu.setJeu(tableauAffichageJ1);
			else
				plateauDuJeu.setJeu(tableauAffichageJ2);
			this.brouillard(ileDuJeu, equipe, joueur);
		}
		this.affichagePersoActionnable(ileDuJeu, equipe, joueur);
	}

	/**
	 * 
	 * @param equipe
	 * @return le plateau specifique au joueur
	 */
	public Plateau getPlateau(){
		return plateauDuJeu;
	}

	/**
	 * Highlight la coordonnees du plateau du joueur
	 * @param cordonnees
	 * @param equipe
	 */
	public void setHighlight(int[]cordonnees, int equipe){
		plateauDuJeu.setHighlight(cordonnees[0], cordonnees[1], Color.BLUE);
	}

	public void popUp(int equipe, String texte, String titre){
		Object[] optionNull = { "OK" };
		plateauDuJeu.popUp(texte, titre, optionNull);
	}

	public Object popUpYesNo(int equipe, String texte, String titre, Object[] listeItem){
		return plateauDuJeu.popUpYesNo(texte, titre, listeItem);
	}

	public void close(){
			plateauDuJeu.close();
	}

	public void masquer(){
		plateauDuJeu.masquer();
	}
}
