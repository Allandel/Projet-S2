
/**
 * Permet le lancement de test des elements du jeu
 * @author louis
 *
 */
public class Test {
	private ActionJoueur action =new ActionJoueur();
	private ile ileDuJeu;
	private int[][] tableauAffichage;
	private Affichage affichage;
	private Joueur [] joueur = {new Joueur(true), new Joueur(false)},joueurTest = {new Joueur(true), new Joueur(false)};
	private Personnage [] persoTest = new Personnage [2];
	public static boolean testEnCours=false;

	/**
	 * Initialise une ile avec un perso de chaque type de chaque equipe de chaque cote
	 * plus la mer en bas, des rocher en haut avec le coffre et la clé
	 */
	Test(){
		ileDuJeu=new ile(true, joueur);
		tableauAffichage=new int[7][7];

		ileDuJeu.getTableau()[1][1].setPersonnageCourant(new Explorateur(joueur[0]));
		ileDuJeu.getTableau()[2][1].setPersonnageCourant(new Guerrier(joueur[0]));
		ileDuJeu.getTableau()[3][1].setPersonnageCourant(new Voleur(joueur[0]));
		ileDuJeu.getTableau()[4][1].setPersonnageCourant(new Piegeur(joueur[0]));

		ileDuJeu.getTableau()[1][5].setPersonnageCourant(new Explorateur(joueur[1]));
		ileDuJeu.getTableau()[2][5].setPersonnageCourant(new Guerrier(joueur[1]));
		ileDuJeu.getTableau()[3][5].setPersonnageCourant(new Voleur(joueur[1]));
		ileDuJeu.getTableau()[4][5].setPersonnageCourant(new Piegeur(joueur[1]));

		joueur[0].passerTour();
		joueur[1].passerTour();
	}

	/**
	 * Lance le test concernant un personnage
	 * @param perso
	 */
	public void testPerso(int id){
		testEnCours=true;
		boolean [] fin={false,false};

		if(id==2){
			ileDuJeu.getTableau()[2][3].setPersonnageCourant(new Piegeur(joueurTest[0]));
			ileDuJeu.getTableau()[3][3].setPersonnageCourant(new Piegeur(joueurTest[1]));
		}else if(id==1){
			ileDuJeu.getTableau()[2][3].setPersonnageCourant(new Guerrier(joueurTest[0]));
			ileDuJeu.getTableau()[3][3].setPersonnageCourant(new Guerrier(joueurTest[1]));

		}else if(id==3){
			ileDuJeu.getTableau()[2][3].setPersonnageCourant(new Voleur(joueurTest[0]));
			ileDuJeu.getTableau()[3][3].setPersonnageCourant(new Voleur(joueurTest[1]));

		}else if(id==4){
			ileDuJeu.getTableau()[2][3].setPersonnageCourant(new Explorateur(joueurTest[0]));
			ileDuJeu.getTableau()[3][3].setPersonnageCourant(new Explorateur(joueurTest[1]));
		}

		persoTest[0]=ileDuJeu.getTableau()[2][3].getPersonnageCourant();
		persoTest[1]=ileDuJeu.getTableau()[3][3].getPersonnageCourant();

		affichage=new Affichage(tableauAffichage, ileDuJeu, joueurTest);
		GestionDuJeu gestion=new GestionDuJeu(ileDuJeu, tableauAffichage, affichage, joueurTest);
		while(!fin[0]){
			gestion.tourDuJoueur();
		}
		testEnCours=false;
	}

	public void testIle(){

	}
}
