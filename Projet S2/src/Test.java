
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
		ileDuJeu=new ile(true);
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
		int [] cordonnees={0,0};
		int equipe=0;
		boolean [] gagner;
		boolean quitter=false;

		if(id==2){
			ileDuJeu.getTableau()[2][3].setPersonnageCourant(new Piegeur(joueur[0]));
			ileDuJeu.getTableau()[3][3].setPersonnageCourant(new Piegeur(joueur[1]));
		}else if(id==1){
			ileDuJeu.getTableau()[2][3].setPersonnageCourant(new Guerrier(joueur[0]));
			ileDuJeu.getTableau()[3][3].setPersonnageCourant(new Guerrier(joueur[1]));

		}else if(id==3){
			ileDuJeu.getTableau()[2][3].setPersonnageCourant(new Voleur(joueur[0]));
			ileDuJeu.getTableau()[3][3].setPersonnageCourant(new Voleur(joueur[1]));

		}else if(id==4){
			ileDuJeu.getTableau()[2][3].setPersonnageCourant(new Explorateur(joueur[0]));
			ileDuJeu.getTableau()[3][3].setPersonnageCourant(new Explorateur(joueur[1]));
		}

		persoTest[0]=ileDuJeu.getTableau()[2][3].getPersonnageCourant();
		persoTest[1]=ileDuJeu.getTableau()[3][3].getPersonnageCourant();

		affichage=new Affichage(tableauAffichage, ileDuJeu, joueur);
		GestionDuJeu gestion=new GestionDuJeu(ileDuJeu, tableauAffichage, affichage);
		while(!quitter){
			affichage.getPlateau().resetId();
			persoTest[equipe].setActionDeplacement(true);
			affichage.affichageDuJeuJoueur(ileDuJeu, tableauAffichage,joueur[equipe], equipe);
			while(joueur[equipe].actionPossible() && !quitter){
				cordonnees=action.choixCase(affichage.getPlateau(), tableauAffichage,ileDuJeu, joueur[equipe]);
				if(cordonnees[0]==999)
					//si le joueur decide de passer son tour
					joueur[equipe].passerTour();
				else if(cordonnees[0]==888){
					//si le joueur decide de quitter le test
					int decision=(int)affichage.popUpYesNo(equipe,"Désirez vous quitter le test ?", "Quitter le test?",null);
					if(decision==0)
						quitter=true;
				}else{
					affichage.setHighlight(cordonnees, equipe);
					if(tableauAffichage[cordonnees[1]][cordonnees[0]]>=6 && ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().actionOuDeplacement()){
						affichage.getPlateau().refreshinfo(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(),0);
						gagner=gestion.actionPerso(cordonnees[0],cordonnees[1],ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(), equipe, joueur[equipe],true);
					}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]==(equipe+2))
						ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getBatimentCourant().sortieBateau(ileDuJeu, affichage.getPlateau(), tableauAffichage, cordonnees[0], cordonnees[1], affichage, 0);

					affichage.affichageDuJeuJoueur(ileDuJeu, tableauAffichage,joueur[equipe], equipe);
				}
			}
			gestion.soinBateau(joueur[equipe]);
			equipe=1-equipe;
		}
		affichage.getPlateau().close();
		testEnCours=false;
	}

	public void testIle(){

	}
}
