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
	 * Genere la gestion du jeu en intialisation l'ile, le tableau d'affichage et l'affichage
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
	public GestionDuJeu(){
		int longueurLigne, proportionNb;
		longueurLigne = Launcher.getTaille();
		proportionNb = Launcher.getPourcent();
		ileDuJeu = new ile(longueurLigne, proportionNb);
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
		int equipe=0, equipeAffichage;

		while(!gagner[0]){
			equipeAffichage=equipe+1;
			joueur[equipe].resetAction();
			affichage.affichageDuJeuJoueur(ileDuJeu, tableauAffichage,joueur[equipe], equipe);
			affichage.popUp(equipe,"Tour du Joueur "+equipeAffichage, "Tour du Joueur");
			while(joueur[equipe].actionPossible() && !gagner[0]){
				affichage.getPlateau(equipe).resetId();
				int [] cordonnees=action.choixCase(affichage.getPlateau(equipe), tableauAffichage, joueur[equipe].getEquipe(),ileDuJeu);

				if(cordonnees[0]==999)
					//si le joueur decide de passer son tour
					joueur[equipe].passerTour();
				else if(cordonnees[0]==888){
					//si le joueur decide d'abandonner	
					int decision=JOptionPane.showConfirmDialog(null,"Désirez vous abandonner la partie ?", "Abandonner la partie ?", JOptionPane.YES_NO_OPTION);
					if(decision==0)
						joueur[equipe].abandon();
				}else{
					affichage.setHighlight(cordonnees, equipe);
					if(tableauAffichage[cordonnees[1]][cordonnees[0]]>=6 && ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().actionOuDeplacement()){
						affichage.getPlateau(equipe).refreshinfo(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant());
						gagner=this.actionPerso(cordonnees[0],cordonnees[1],ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(), equipe, joueur[equipe],false);
					}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]==(equipe+2))
						((CaseNavire)ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]]).sortieBateau(ileDuJeu, affichage.getPlateau(equipe), tableauAffichage, cordonnees[0], cordonnees[1], affichage, equipe);

					affichage.affichageDuJeuJoueur(ileDuJeu, tableauAffichage,joueur[equipe], equipe);
				}
			}
			this.soinBateau(joueur[equipe]);
			equipe=1-equipe;
			if(!gagner[0])
				gagner=this.equipeMorte();
		}
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

		if(!test){
			affichage.getPlateau(equipe).resetId();
			affichage.getPlateau(equipe).setVisibleBouttonAnnuler(true);
			cordonnees= action.choixCase(ileDuJeu, affichage.getPlateau(equipe), tableauAffichage, x, y, perso);
		}else{
			affichage.getPlateau(0).resetId();
			affichage.getPlateau(0).setVisibleBouttonAnnuler(true);
			cordonnees = action.choixCase(ileDuJeu, affichage.getPlateau(0), tableauAffichage, x, y, perso);
		}
		if(cordonnees[0]==999){
			//si le joueur decide de passer son tour
			joueur.passerTour();
		}else if(cordonnees[0]==888){
			//si le joueur decide d'abandonner
			int decision=JOptionPane.showConfirmDialog(null,"Désirez vous abandonner la partie ?", "Abandonner la partie ?", JOptionPane.YES_NO_OPTION);
			if(decision==0)
				joueur.abandon();
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
					gagner=perso.entreeBateau(x, y, cordonnees[0], cordonnees[1], ileDuJeu.getTableau(),affichage, joueur, equipe);
				}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]==14){
					perso.recuperationStuff(false,false, x,y,cordonnees[0],cordonnees[1], ileDuJeu.getTableau(), affichage, equipe);
				}
			}
			if(perso.getAction()){
				//si le perso peut faire une action	
				if(perso instanceof Explorateur && tableauAffichage[cordonnees[1]][cordonnees[0]] == 1 || tableauAffichage[cordonnees[1]][cordonnees[0]] == 4){
					((Explorateur)perso).interactionRocher(cordonnees[0], cordonnees[1], ileDuJeu.getTableau(), joueur, affichage, equipe);
				}else if(perso instanceof Piegeur && cordonnees[0]==x && cordonnees[1]==y){
					((Piegeur)perso).pieger(cordonnees[0],cordonnees[1], ileDuJeu.getTableau(), affichage, equipe);
				}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]>5 && tableauAffichage[cordonnees[1]][cordonnees[0]]<14 && perso.getEquipe()==ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().getEquipe()){
					perso.echangeObjet(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(), affichage, equipe);
				}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]>5 && tableauAffichage[cordonnees[1]][cordonnees[0]]<14 && perso.getEquipe()!=ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().getEquipe() && perso instanceof Guerrier){
					((Guerrier) perso).attaque(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(),x,y, cordonnees[0], cordonnees[1], ileDuJeu.getTableau(), affichage, equipe);
				}else if(tableauAffichage[cordonnees[1]][cordonnees[0]]>5 && tableauAffichage[cordonnees[1]][cordonnees[0]]<14 && perso.getEquipe()!=ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant().getEquipe() && perso instanceof Voleur){
					((Voleur) perso).volerObjet(ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].getPersonnageCourant(), x, y, ileDuJeu.getTableau(), affichage, equipe);
				}
			}
		}
		if(!test){
			affichage.getPlateau(equipe).refreshinfo(perso);
			affichage.getPlateau(equipe).setVisibleBouttonAnnuler(false);
		}else{
			affichage.getPlateau(0).refreshinfo(perso);
			affichage.getPlateau(0).setVisibleBouttonAnnuler(false);
		}	
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
		for(int i=0;i<Launcher.nbrExplorateurJ1;i++){
			Explorateur paul =new Explorateur(true, joueur[0]);
		}
		for(int i=0;i<Launcher.nbrGuerrierJ1;i++){
			Guerrier mar=new Guerrier(true, joueur[0]);
		}
		for(int i=0;i<Launcher.nbrPiegeurJ1;i++){
			Piegeur marc =new Piegeur(true, joueur[0]);
		}
		for(int i=0;i<Launcher.nbrVoleurJ1;i++){
			Voleur jean = new Voleur(true, joueur[0]);
		}

		for(int i=0;i<Launcher.nbrExplorateurJ2;i++){
			Explorateur paul =new Explorateur(false, joueur[1]);
		}
		for(int i=0;i<Launcher.nbrGuerrierJ2;i++){
			Guerrier mar=new Guerrier(false, joueur[1]);
		}
		for(int i=0;i<Launcher.nbrPiegeurJ2;i++){
			Piegeur marc =new Piegeur(false, joueur[1]);
		}
		for(int i=0;i<Launcher.nbrVoleurJ2;i++){
			Voleur jean = new Voleur(false, joueur[1]);
		}

		for(Joueur player: joueur){
			player.setBateau(ileDuJeu);
			for(Personnage perso: player.getPersos()){
				((CaseNavire)ileDuJeu.getTableau()[player.getLigneBateau()][player.getColonneBateau()]).addPersoNavire(perso);
			}
		}

	}
}
