import java.awt.Color;
import javax.swing.JOptionPane;
/**
 * Classe permettant la gestion du jeu en organisant les tours de jeu et les possibilites
 * @author Valentin
 * @version 1.1 
 */
public class GestionDuJeu {
	private ActionJoueur action =new ActionJoueur();
	private ile ileDuJeu;
	private int[][] tableauAffichage;
	private String[] gifs = new String[]{"img/rocher.png","img/1.navire.png","img/2.navire.png","img/coffre.png","img/mer.png","img/1.explorateur.png","img/1.voleur.png","img/1.piegeur.png","img/2.explorateur.png","img/2.voleur.png","img/2.piegeur.png","img/cadavre.png"};
	private Plateau plateauDuJeu;
	private Joueur [] joueur = {new Joueur(true), new Joueur(false)};
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
		ileDuJeu = new ile(longueurLigne, proportionNb, joueur);
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
	 * Organise la succession d'action possible pour le joueur
	 */
	public boolean tourDuJoueur(){
		boolean gagner=false;
		int equipe=0, cpt=0;

		while(cpt<4){
			joueur[equipe].resetAction();
			while(joueur[equipe].actionPossible()){
				int [] cordonnees=action.choixCase(plateauDuJeu, tableauAffichage, joueur[equipe].getEquipe(),ileDuJeu);

				plateauDuJeu.setHighlight(cordonnees[0], cordonnees[1], Color.BLUE);

				if(tableauAffichage[cordonnees[1]][cordonnees[0]]>=6 && ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().getAction())
					gagner=this.actionPerso(cordonnees[0],cordonnees[1],ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant());
				else if(tableauAffichage[cordonnees[1]][cordonnees[0]]==(equipe+2))
					((CaseNavire)ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]]).sortieBateau(ileDuJeu, plateauDuJeu, tableauAffichage, cordonnees[0], cordonnees[1]);

				this.affichageDuJeu();
			}
			equipe=1-equipe;
			cpt++;
		}
		return gagner;
	}
	/**
	 * Permet a un personnage d'entrer dans le bateau
	 * @param perso
	 * @param x
	 * @param y
	 */
	private boolean actionPerso(int x, int y, Personnage perso){
		boolean gagner=false;
		int[] cordonnees = action.choixCase(ileDuJeu, plateauDuJeu, tableauAffichage, x, y, perso);

		if(perso instanceof Explorateur && tableauAffichage[cordonnees[1]][cordonnees[0]] == 1 || tableauAffichage[cordonnees[1]][cordonnees[0]] == 4){
			((Explorateur)perso).interactionRocher(cordonnees[0], cordonnees[1], ileDuJeu.getTableau());
		}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]==0){
			perso.mouvement(x, y, cordonnees[0], cordonnees[1], ileDuJeu.getTableau());
		}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]==perso.getIdBateau()){
			gagner=perso.entreeBateau(x, y, cordonnees[0], cordonnees[1], ileDuJeu.getTableau());
		}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]==12){
			perso.recuperationStuff(false,false, x,y,cordonnees[0],cordonnees[1], ileDuJeu.getTableau());
		}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]>5 && tableauAffichage[cordonnees[1]][cordonnees[0]]<12 && perso.getEquipe()==ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().getEquipe()){
			perso.echangeObjet(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant());
		}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]>5 && tableauAffichage[cordonnees[1]][cordonnees[0]]<12 && perso.getEquipe()!=ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().getEquipe() && perso instanceof Voleur){
			((Voleur) perso).volerObjet(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(), x, y, ileDuJeu.getTableau());
		}
		System.out.println(perso.getEnergie());
		return gagner;
	}
}
