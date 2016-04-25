import javax.swing.JOptionPane;

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

	/**
	 * Initialise une ile avec un perso de chaque type de chaque equipe de chaque cote
	 * plus la mer en bas, des rocher en haut avec le coffre et la clé
	 */
	Test(){
		ileDuJeu=new ile(true);
		tableauAffichage=new int[7][7];

		ileDuJeu.getTableau()[1][1].setPersonnageCourant(new Explorateur(true, joueur[0]));
		ileDuJeu.getTableau()[2][1].setPersonnageCourant(new Guerrier(true, joueur[0]));
		ileDuJeu.getTableau()[3][1].setPersonnageCourant(new Voleur(true, joueur[0]));
		ileDuJeu.getTableau()[4][1].setPersonnageCourant(new Piegeur(true, joueur[0]));

		ileDuJeu.getTableau()[1][5].setPersonnageCourant(new Explorateur(false, joueur[1]));
		ileDuJeu.getTableau()[2][5].setPersonnageCourant(new Guerrier(false, joueur[1]));
		ileDuJeu.getTableau()[3][5].setPersonnageCourant(new Voleur(false, joueur[1]));
		ileDuJeu.getTableau()[4][5].setPersonnageCourant(new Piegeur(false, joueur[1]));

		joueur[0].passerTour();
		joueur[1].passerTour();
	}

	/**
	 * Lance le test concernant un personnage
	 * @param perso
	 */
	public void testPerso(int id){
		int [] cordonnees={0,0};
		int equipe=0;
		boolean [] gagner;
		boolean quitter=false;

		if(id==0){
			ileDuJeu.getTableau()[2][3].setPersonnageCourant(new Piegeur(true, joueur[0]));
			ileDuJeu.getTableau()[3][3].setPersonnageCourant(new Piegeur(false, joueur[1]));
		}else if(id==1){
			ileDuJeu.getTableau()[2][3].setPersonnageCourant(new Guerrier(true, joueur[0]));
			ileDuJeu.getTableau()[3][3].setPersonnageCourant(new Guerrier(false, joueur[1]));

		}else if(id==2){
			ileDuJeu.getTableau()[2][3].setPersonnageCourant(new Voleur(true, joueur[0]));
			ileDuJeu.getTableau()[3][3].setPersonnageCourant(new Voleur(false, joueur[1]));

		}else if(id==3){
			ileDuJeu.getTableau()[2][3].setPersonnageCourant(new Explorateur(true, joueur[0]));
			ileDuJeu.getTableau()[3][3].setPersonnageCourant(new Explorateur(false, joueur[1]));
		}
		
		persoTest[0]=ileDuJeu.getTableau()[2][3].getPersonnageCourant();
		persoTest[1]=ileDuJeu.getTableau()[3][3].getPersonnageCourant();

		affichage=new Affichage(tableauAffichage, ileDuJeu, joueur, true);
		GestionDuJeu gestion=new GestionDuJeu(ileDuJeu, tableauAffichage, affichage);
		while(!quitter){
			persoTest[equipe].setActionDeplacement(true);
			affichage.affichageDuJeuTest(ileDuJeu, tableauAffichage,joueur[equipe], equipe);
			while(joueur[equipe].actionPossible() && !quitter){
				cordonnees=action.choixCase(affichage.getPlateauTest(), tableauAffichage, joueur[equipe].getEquipe(),ileDuJeu);
				if(cordonnees[0]==999)
					//si le joueur decide de passer son tour
					joueur[equipe].passerTour();
				else if(cordonnees[0]==888){
					//si le joueur decide de quitter le test
					int decision=JOptionPane.showConfirmDialog(null,"Désirez vous quitter le test ?", "Quitter le test?", JOptionPane.YES_NO_OPTION);
					if(decision==0)
						quitter=true;
				}else{
					affichage.setHighlightTest(cordonnees, equipe);
					if(tableauAffichage[cordonnees[1]][cordonnees[0]]>=6 && ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().actionOuDeplacement()){
						gestion.refreshinfo(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(), affichage.getPlateauTest());
						gagner=gestion.actionPerso(cordonnees[0],cordonnees[1],ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(), equipe, joueur[equipe],true);
					}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]==(equipe+2))
						((CaseNavire)ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]]).sortieBateau(ileDuJeu, affichage.getPlateau(0), tableauAffichage, cordonnees[0], cordonnees[1]);

					affichage.affichageDuJeuTest(ileDuJeu, tableauAffichage,joueur[equipe], equipe);
				}
			}
			gestion.soinBateau(joueur[equipe]);
			equipe=1-equipe;
		}
		affichage.getPlateau(0).close();
	}

	public void testIle(){

	}
}
