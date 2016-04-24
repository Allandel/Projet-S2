import javax.swing.JOptionPane;


public class Test {
	private ActionJoueur action =new ActionJoueur();
	private ile ileDuJeu;
	private int[][] tableauAffichage;
	private Affichage affichage;
	private Joueur [] joueur = {new Joueur(true), new Joueur(false)};
	private Personnage [] persoTest;

	/**
	 * Initialise une ile avec un perso de chaque type de chaque equipe de chaque cote
	 * plus la mer en bas, des rocher en haut avec le coffre et la clé
	 * plus le perso a tester au milieu
	 */
	Test(){
		ileDuJeu=new ile(true);
		tableauAffichage=new int[6][6];
		
		int [] cordonnees={0,0};
		int equipe=0;
		boolean [] gagner;
		boolean quitter=false;
		
		ileDuJeu.getTableau()[1][0].setPersonnageCourant(new Explorateur(true, joueur[0]));
		ileDuJeu.getTableau()[2][0].setPersonnageCourant(new Guerrier(true, joueur[0]));
		ileDuJeu.getTableau()[3][0].setPersonnageCourant(new Voleur(true, joueur[0]));
		ileDuJeu.getTableau()[4][0].setPersonnageCourant(new Piegeur(true, joueur[0]));
		
		ileDuJeu.getTableau()[1][5].setPersonnageCourant(new Explorateur(false, joueur[1]));
		ileDuJeu.getTableau()[2][5].setPersonnageCourant(new Guerrier(false, joueur[1]));
		ileDuJeu.getTableau()[3][5].setPersonnageCourant(new Voleur(false, joueur[1]));
		ileDuJeu.getTableau()[4][5].setPersonnageCourant(new Piegeur(false, joueur[1]));
		
		joueur[0].passerTour();
		joueur[1].passerTour();
		
		affichage=new Affichage(tableauAffichage, ileDuJeu, joueur, true);
		GestionDuJeu gestion=new GestionDuJeu(ileDuJeu, tableauAffichage, affichage);
		while(!quitter){
			joueur[equipe].resetAction();
			affichage.affichageDuJeu(ileDuJeu, tableauAffichage,joueur[equipe], 0);
			while(joueur[equipe].actionPossible() && !quitter){
				cordonnees=action.choixCase(affichage.getPlateau(0), tableauAffichage, joueur[equipe].getEquipe(),ileDuJeu);

				if(cordonnees[0]==999)
					//si le joueur decide de passer son tour
					joueur[equipe].passerTour();
				else if(cordonnees[0]==888){
					//si le joueur decide d'abandonner	
					int decision=JOptionPane.showConfirmDialog(null,"Désirez vous quitter le test ?", "Quitter le test?", JOptionPane.YES_NO_OPTION);
					if(decision==0)
						quitter=true;
				}else{
					affichage.setHighlight(cordonnees, equipe);
					if(tableauAffichage[cordonnees[1]][cordonnees[0]]>=6 && ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().actionOuDeplacement()){
						gestion.refreshinfo(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(), affichage.getPlateau(0));
						gagner=gestion.actionPerso(cordonnees[0],cordonnees[1],ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(), equipe, joueur[equipe]);
					}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]==(equipe+2))
						((CaseNavire)ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]]).sortieBateau(ileDuJeu, affichage.getPlateau(0), tableauAffichage, cordonnees[0], cordonnees[1]);

					affichage.affichageDuJeu(ileDuJeu, tableauAffichage,joueur[equipe], 0);
				}
			}
			gestion.soinBateau(joueur[equipe]);
			equipe=1-equipe;
		}
	
	}

}
