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
	private int [] parametres;

	/**
	 * Genere la gestion du jeu en intialisation l'ile, le tableau d'affichage et l'affichage pour le test
	 * @param ileDujeu
	 * @param tableauAffichage
	 * @param affichage
	 */
	public GestionDuJeu(ile ileDujeu, int[][] tableauAffichage, Affichage affichage){
		this.ileDuJeu=ileDujeu;
		this.tableauAffichage=tableauAffichage;
		this.affichage=affichage;
	}

	/**
	 * Constructeur initialisant le plateau de jeu de base
	 */
	public GestionDuJeu(int [] parametres){
		this.parametres=parametres;
		ileDuJeu = new ile(parametres[0], parametres[1]);
		this.initialisationEquipe(ileDuJeu);
		tableauAffichage = new int[ileDuJeu.getTableau().length][ileDuJeu.getTableau().length];
		affichage= new Affichage(tableauAffichage, ileDuJeu, joueur);
		affichage.affichageDuJeuJoueur(ileDuJeu,tableauAffichage, joueur[0], 0);
	}
	
	/**
	 * Organise la succession d'action possible pour le joueur
	 * Fini quand un des joueur est rentre dans le bateau avec le tresor
	 * ou quand un joueur n'a plus de personnage vivant
	 * ou quand un joueur decide d'abandonner
	 */
	public boolean [] tourDuJoueur(){
		boolean [] gagner={false,false};
		int equipe=0 ;

		while(!gagner[0]){
			joueur[equipe].resetAction(affichage, equipe);
			affichage.actionDebutTour(equipe, joueur, ileDuJeu, tableauAffichage);
		
			while(joueur[equipe].actionPossible() && !gagner[0]){
				affichage.getPlateau().resetId();
				int [] cordonnees=action.choixCase(affichage.getPlateau(), tableauAffichage,ileDuJeu, joueur[equipe]);
				
				if(cordonnees[0]==999)
					//si le joueur decide de passer son tour
					joueur[equipe].passerTour();
				else if(cordonnees[0]==888){
					//si le joueur decide d'abandonner	
					int decision=(int)affichage.popUpYesNo(equipe,"Désirez vous abandonner la partie ?", "Abandonner la partie ?",null);
					if(decision==0)
						joueur[equipe].abandon();
				}else{
					affichage.setHighlight(cordonnees, equipe);
					if(tableauAffichage[cordonnees[1]][cordonnees[0]]>=6 && ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().actionOuDeplacement()){
						affichage.getPlateau().refreshinfo(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant());
						gagner=this.actionPerso(cordonnees[0],cordonnees[1],ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(), equipe, joueur[equipe],false);
					}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]==(equipe+2))
						((CaseNavire)ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]]).sortieBateau(ileDuJeu, affichage.getPlateau(), tableauAffichage, cordonnees[0], cordonnees[1], affichage, equipe);

					affichage.affichageDuJeuJoueur(ileDuJeu, tableauAffichage,joueur[equipe], equipe);
				}
			}
			this.soinBateau(joueur[equipe]);
			this.joueur[equipe].ExplosionBombes(ileDuJeu.getTableau(), affichage, equipe);
			equipe=1-equipe;
			try {
				Thread.sleep(1000) ;	// Cette instruction - en plus du délai induit - permet à Swing de traiter les événements GUI 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!gagner[0])
				gagner=this.equipeMorte();
		}
		affichage.close();
		return gagner;
	}


	/**
	 * Donne le choix des actions possible pour le personnage selectionne
	 * @param x
	 * @param y
	 * @param perso
	 * @param equipe
	 * @param joueur
	 * @return vrai si le personnage rentre dans le bateau avec le tresor
	 */
	boolean [] actionPerso(int x, int y, Personnage perso, int equipe, Joueur joueur, boolean test){
		boolean []gagner={false,false};
		int[] cordonnees;

			affichage.getPlateau().resetId();
			affichage.setVisibleActionPerso(true, perso);
			cordonnees = action.choixCase(ileDuJeu, affichage.getPlateau(), tableauAffichage, x, y, perso);
			if(cordonnees[0]==999){
				//si le joueur decide de passer son tour
				joueur.passerTour();
			}else if(cordonnees[0]==888){
				//si le joueur decide d'abandonner
				int decision=JOptionPane.showConfirmDialog(null,"Désirez vous abandonner la partie ?", "Abandonner la partie ?", JOptionPane.YES_NO_OPTION);
				if(decision==0)
					joueur.abandon();
			}else if(cordonnees[0]==666){
				perso.abandonnerObjet(affichage, equipe);
			}else if(cordonnees[0]!=777){
				//si le joueur n'annule pas sa selection	
				if(perso.getDeplacement()){
					//si le perso peut se deplacer	
					if(tableauAffichage[cordonnees[1]][cordonnees[0]]==15){
						if(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPiege() && ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getTeamPiege()!=equipe){
							perso.mouvement(x, y, cordonnees[0], cordonnees[1], ileDuJeu.getTableau(), affichage, equipe);
							perso.immobilisation(affichage, equipe);
						}else{
							perso.mouvement(x, y, cordonnees[0], cordonnees[1], ileDuJeu.getTableau(), affichage, equipe);
						}
					}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]==perso.getIdBateau()){
						gagner=perso.entreeBateau(x, y, cordonnees[0], cordonnees[1], ileDuJeu.getTableau(),affichage, equipe);
					}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]==14){
						perso.recuperationStuff(false,false, x,y,cordonnees[0],cordonnees[1], ileDuJeu.getTableau(), affichage, equipe);
					}
				}
				if(perso.getAction()){
					//si le perso peut faire une action	
					if(perso instanceof Explorateur && tableauAffichage[cordonnees[1]][cordonnees[0]] == 1 || tableauAffichage[cordonnees[1]][cordonnees[0]] == 4){
						((Explorateur)perso).interactionRocher(cordonnees[0], cordonnees[1], ileDuJeu.getTableau(), affichage, equipe);
					}else if(perso instanceof Piegeur && ((cordonnees[0]==x && cordonnees[1]==y)||(tableauAffichage[cordonnees[1]][cordonnees[0]]>0 && tableauAffichage[cordonnees[1]][cordonnees[0]]<4))){
						((Piegeur)perso).pieger(perso, cordonnees[0],cordonnees[1], ileDuJeu.getTableau(), affichage, equipe, this.joueur);
					}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]>5 && tableauAffichage[cordonnees[1]][cordonnees[0]]<14 && perso.getJoueur()==ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().getJoueur()){
						perso.echangeObjet(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(), affichage, equipe);
					}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]>5 && tableauAffichage[cordonnees[1]][cordonnees[0]]<14 && perso.getJoueur()!=ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().getJoueur() && perso instanceof Guerrier){
						((Guerrier) perso).attaque(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(),x,y, cordonnees[0], cordonnees[1], ileDuJeu.getTableau(), affichage, equipe);
					}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]>5 && tableauAffichage[cordonnees[1]][cordonnees[0]]<14 && perso.getJoueur()!=ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().getJoueur() && perso instanceof Voleur){
						((Voleur) perso).volerObjet(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(), x, y, ileDuJeu.getTableau(), affichage, equipe);
					}
				}
			}
			affichage.getPlateau().refreshinfo(perso);
			affichage.setVisibleActionPerso(false,perso);
		return gagner;
	}

	/**
	 * Verifie si l'equipe du joueur est morte
	 * @return vrai si l'equipe est morte
	 */
	private boolean [] equipeMorte(){
		boolean [] gagner={false,!joueur[0].getEquipe()};
		if(!joueur[0].persoVivant()){
			gagner[0]=true;
			gagner[1]=joueur[1].getEquipe();
		}else if(!joueur[1].persoVivant()){
			gagner[0]=true;	
			gagner[1]=joueur[0].getEquipe();
		}
		return gagner;
	}
	/**
	 * Soigne les personnages dans le bateau du joueur dont c'est le tour
	 * @param joueur
	 */
	void soinBateau(Joueur joueur){
		if(joueur.getEquipe())
			((CaseNavire)ileDuJeu.getTableau()[ileDuJeu.getLigneNavJ1()][ileDuJeu.getColonneNavJ1()]).recupEnergie();
		else
			((CaseNavire)ileDuJeu.getTableau()[ileDuJeu.getLigneNavJ2()][ileDuJeu.getColonneNavJ2()]).recupEnergie();
	}

	/**
	 * Initialise les personnages dans le bateau de chaque joueur
	 * @param ileDuJeu
	 */
	private void initialisationEquipe(ile ileDuJeu){
		for(int i=0;i<parametres[2];i++){
			Explorateur paul =new Explorateur(joueur[0]);
		}
		for(int i=0;i<parametres[3];i++){
			Voleur jean = new Voleur(joueur[0]);
		}
		for(int i=0;i<parametres[4];i++){
			Guerrier mar=new Guerrier(joueur[0]);
		}
		for(int i=0;i<parametres[5];i++){
			Piegeur marc =new Piegeur(joueur[0]);
		}
		

		for(int i=0;i<parametres[6];i++){
			Explorateur paul =new Explorateur(joueur[1]);
		}
		for(int i=0;i<parametres[7];i++){
			Voleur jean = new Voleur(joueur[1]);
		}
		for(int i=0;i<parametres[8];i++){
			Guerrier mar=new Guerrier(joueur[1]);
		}
		for(int i=0;i<parametres[9];i++){
			Piegeur marc =new Piegeur(joueur[1]);
		}
		

		for(Joueur player: joueur){
			player.setBateau(ileDuJeu);
			for(Personnage perso: player.getPersos()){
				((CaseNavire)ileDuJeu.getTableau()[player.getLigneBateau()][player.getColonneBateau()]).addPersoNavire(perso);
			}
		}

	}
}
