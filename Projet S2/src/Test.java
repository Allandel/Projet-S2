
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
	private Joueur [] joueur = {new Joueur(true), new Joueur(false)};
	private Personnage [] persoTest = new Personnage [2];
	public static boolean testEnCours=false;

	/**
	 * Initialise une ile avec un perso de chaque type de chaque equipe de chaque cote
	 * plus la mer en bas, des rocher en haut avec le coffre et la clé
	 */
	Test(){
		ileDuJeu=new ile(true, joueur);
		tableauAffichage=new int[9][9];

		ileDuJeu.getTableau()[2][1].setPersonnageCourant(new Explorateur(joueur[0]));
		ileDuJeu.getTableau()[3][1].setPersonnageCourant(new Guerrier(joueur[0]));
		ileDuJeu.getTableau()[4][1].setPersonnageCourant(new Voleur(joueur[0]));
		ileDuJeu.getTableau()[5][1].setPersonnageCourant(new Piegeur(joueur[0]));
		ileDuJeu.getTableau()[6][1].setPersonnageCourant(new Ouvrier(joueur[0]));
		
		ileDuJeu.getTableau()[2][7].setPersonnageCourant(new Explorateur(joueur[1]));
		ileDuJeu.getTableau()[3][7].setPersonnageCourant(new Guerrier(joueur[1]));
		ileDuJeu.getTableau()[4][7].setPersonnageCourant(new Voleur(joueur[1]));
		ileDuJeu.getTableau()[5][7].setPersonnageCourant(new Piegeur(joueur[1]));
		ileDuJeu.getTableau()[6][7].setPersonnageCourant(new Ouvrier(joueur[1]));
		
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
			ileDuJeu.getTableau()[4][4].setPersonnageCourant(new Piegeur(joueur[0]));
			ileDuJeu.getTableau()[5][4].setPersonnageCourant(new Piegeur(joueur[1]));
		}else if(id==1){
			ileDuJeu.getTableau()[4][4].setPersonnageCourant(new Guerrier(joueur[0]));
			ileDuJeu.getTableau()[5][4].setPersonnageCourant(new Guerrier(joueur[1]));

		}else if(id==3){
			ileDuJeu.getTableau()[4][4].setPersonnageCourant(new Voleur(joueur[0]));
			ileDuJeu.getTableau()[5][4].setPersonnageCourant(new Voleur(joueur[1]));

		}else if(id==4){
			ileDuJeu.getTableau()[4][4].setPersonnageCourant(new Explorateur(joueur[0]));
			ileDuJeu.getTableau()[5][4].setPersonnageCourant(new Explorateur(joueur[1]));
		}else if(id==5){
			ileDuJeu.getTableau()[4][4].setPersonnageCourant(new Ouvrier(joueur[0]));
			ileDuJeu.getTableau()[5][4].setPersonnageCourant(new Ouvrier(joueur[1]));
		}


		persoTest[0]=ileDuJeu.getTableau()[2][3].getPersonnageCourant();
		persoTest[1]=ileDuJeu.getTableau()[3][3].getPersonnageCourant();

		affichage=new Affichage(tableauAffichage, ileDuJeu, joueur);
		GestionDuJeu gestion=new GestionDuJeu(ileDuJeu, tableauAffichage, affichage, joueur);
		while(!fin[0]){
			fin=gestion.tourDuJoueur();
		}
		testEnCours=false;
	}

	public void testIle(){

	}
}
