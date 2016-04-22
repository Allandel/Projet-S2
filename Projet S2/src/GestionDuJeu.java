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
	private Affichage affichage;
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
		affichage= new Affichage(tableauAffichage, ileDuJeu, joueur);
		affichage.affichageDuJeuJoueur(ileDuJeu,tableauAffichage, joueur[0], 0);
	}
	
	
	
	/**
	 * Organise la succession d'action possible pour le joueur
	 */
	public boolean tourDuJoueur(){
		boolean gagner=false;
		int equipe=0;

		while(!gagner){
			joueur[equipe].resetAction();
			affichage.affichageDuJeuJoueur(ileDuJeu, tableauAffichage,joueur[equipe], equipe);
			while(joueur[equipe].actionPossible()){
				int [] cordonnees=action.choixCase(affichage.getPlateau(equipe), tableauAffichage, joueur[equipe].getEquipe(),ileDuJeu);

				affichage.setHighlight(cordonnees, equipe);
				
				if(tableauAffichage[cordonnees[1]][cordonnees[0]]>=6 && ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().getAction())
					gagner=this.actionPerso(cordonnees[0],cordonnees[1],ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(), equipe);
				else if(tableauAffichage[cordonnees[1]][cordonnees[0]]==(equipe+2))
					((CaseNavire)ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]]).sortieBateau(ileDuJeu, affichage.getPlateau(equipe), tableauAffichage, cordonnees[0], cordonnees[1]);

				affichage.affichageDuJeuJoueur(ileDuJeu, tableauAffichage,joueur[equipe], equipe);
			}
			this.soinBateau(joueur[equipe]);
			equipe=1-equipe;
			if(!gagner)
				gagner=this.equipeMorte();
		}
		return gagner;
	}
	/**
	 * Permet a un personnage d'entrer dans le bateau
	 * @param perso
	 * @param x
	 * @param y
	 */
	private boolean actionPerso(int x, int y, Personnage perso, int equipe){
		boolean gagner=false;
		int[] cordonnees = action.choixCase(ileDuJeu, affichage.getPlateau(equipe), tableauAffichage, x, y, perso);
		if(perso instanceof Explorateur && tableauAffichage[cordonnees[1]][cordonnees[0]] == 1 || tableauAffichage[cordonnees[1]][cordonnees[0]] == 4){
			((Explorateur)perso).interactionRocher(cordonnees[0], cordonnees[1], ileDuJeu.getTableau());
		}else if(perso instanceof Piegeur && cordonnees[0]==x && cordonnees[1]==y){
			((Piegeur)perso).pieger(cordonnees[0],cordonnees[1], ileDuJeu.getTableau());
		}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]==0){
			if(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPiege() && ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getTeamPiege()!=perso.getEquipe()){
				perso.mouvement(x, y, cordonnees[0], cordonnees[1], ileDuJeu.getTableau());
				perso.immobilisation();
			}else{
				perso.mouvement(x, y, cordonnees[0], cordonnees[1], ileDuJeu.getTableau());
			}
		}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]==perso.getIdBateau()){
			gagner=perso.entreeBateau(x, y, cordonnees[0], cordonnees[1], ileDuJeu.getTableau());
		}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]==14){
			perso.recuperationStuff(false,false, x,y,cordonnees[0],cordonnees[1], ileDuJeu.getTableau());
		}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]>5 && tableauAffichage[cordonnees[1]][cordonnees[0]]<14 && perso.getEquipe()==ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().getEquipe()){
			perso.echangeObjet(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant());
		}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]>5 && tableauAffichage[cordonnees[1]][cordonnees[0]]<14 && perso.getEquipe()!=ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().getEquipe() && perso instanceof Guerrier){
			((Guerrier) perso).attaque(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(), cordonnees[0], cordonnees[1], ileDuJeu.getTableau());
		}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]>5 && tableauAffichage[cordonnees[1]][cordonnees[0]]<12 && perso.getEquipe()!=ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().getEquipe() && perso instanceof Voleur){
			((Voleur) perso).volerObjet(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(), x, y, ileDuJeu.getTableau());
		}
		System.out.println(perso.getEnergie());
		return gagner;
	}
	
	private boolean equipeMorte(){
		if(joueur[0].persoVivant())
			if(joueur[1].persoVivant())
				return false;
		return true;
	}
	
	private void soinBateau(Joueur joueur){
		if(joueur.getEquipe())
			((CaseNavire)ileDuJeu.getTableau()[ileDuJeu.getNavJ1()][1]).recupEnergie();
		else
			((CaseNavire)ileDuJeu.getTableau()[ileDuJeu.getNavJ2()][tableauAffichage.length-2]).recupEnergie();
	}
}
