import java.util.ArrayList;


public class Batiment {
	protected ArrayList<Personnage>stockBatiment=new ArrayList<Personnage>();
	protected int batimentHealth=100,id,x,y, heal=10;
	protected Joueur joueur;
	protected String type;

	/**
	 * Constructeur vide
	 */
	Batiment(){

	}

	/**
	 * Constructeur de batiment
	 * @param x
	 * @param y
	 * @param joueur
	 */
	Batiment(int x, int y, Joueur joueur){
		this.x=x;
		this.y=y;
		this.joueur=joueur;
		joueur.addBatiment(this);
	}

	/**
	 * 
	 * @return le type du batiment
	 */
	public String getType(){
		return type;
	}

	/**
	 * Fait des dommages au batiment
	 * @param deg
	 */
	public void dommageBatiment(int deg){
		batimentHealth-=deg;
	}

	/**
	 * 
	 * @return la vie du batiment
	 */
	public int getBatimentHealth(){
		return batimentHealth;
	}

	/**
	 * Retourne la liste de Personnage du Batiment
	 */
	public ArrayList<Personnage> getStockBatiment(){
		return stockBatiment;
	}

	/**
	 * 
	 * @return le joueur du batiment
	 */
	public Joueur getJoueur(){
		return joueur;
	}

	/**
	 * @return true si le batiment contient un ouvrier
	 */
	public boolean ouvrierPresent(){
		for(Personnage perso : stockBatiment){
			if(perso.getType().equals("Ouvrier")){
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @return Le personnage désigné par son type en paramètre.
	 */
	public Personnage getPersonnageListe(String type){
		if(!stockBatiment.isEmpty()){
			for(Personnage perso : stockBatiment){
				if(perso.getType().compareTo(type)==0){
					return perso;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @return le nombre de personnage vivant dans le batiment
	 */
	public int nbrVivantStock(){
		int cpt=0;
		for(Personnage perso:stockBatiment){
			if(!perso.getDeath())
				cpt++;
		}
		return cpt;
	}

	/**
	 * Permet a un personnage d'entrer dans le batiment
	 * @param p
	 */
	public void addPersoBatiment(Personnage p){
		stockBatiment.add(p);
	}

	/**
	 * 
	 * @return vrai s'il y a personnage mort dans le batiment
	 */
	public boolean persoMort(){
		for(Personnage perso : stockBatiment){
			if(perso.getDeath())
				return true;
		}
		return false;
	}

	/**
	 * Met l'attribut action et deplacement des personnages du batiment a faux pour eviter une boucle infini dans le jeu
	 */
	private void actionImpossible(){
		for(Personnage perso:stockBatiment){
			perso.setAction(false);
			perso.setDeplacement(false);
		}
	}

	/**
	 * Regarde s'il y a une place libre autour du batiment
	 * @param i
	 * @param j
	 * @param ileDuJeu
	 * @return vrai si une case est vide
	 */
	private boolean placeLibre(ile ileDuJeu){
		for(int i=x-1;i<x+2;i++){
			for(int j=y-1;j<y+2;j++){
				if(ileDuJeu.getTableau()[i][j].getId()==16 || ileDuJeu.getTableau()[i][j].getId()==17)
					return true;
			}
		}
		return false;
	}

	/**
	 * Regarde si une case est vide ou si le personnage qui l'occupe peut sortir
	 * @param ileDuJeu
	 * @return vrai s'il n'y a pas de case vide ou de personnage pouvant se deplacer autour du batiment
	 */
	public boolean sortieImpossible(ile ileDuJeu){
		for(int i=x-1;i<x+2;i++){
			for(int j=y-1;j<y+2;j++){
				if(ileDuJeu.getTableau()[i][j].getId()==16 || ileDuJeu.getTableau()[i][j].getId()==17 || (ileDuJeu.getTableau()[i][j].getId()>5 && ileDuJeu.getTableau()[i][j].getId()<16 && ileDuJeu.getTableau()[i][j].getPersonnageCourant().getDeplacement()))
					return false;
			}
		}
		return true;
	}

	/**
	 * Regenere l'energie des personnages dans le batiment 
	 */
	public void recupEnergie(){
		for(Personnage perso: stockBatiment){
			if(!perso.getDeath())
				perso.addEnergie(heal);
		}
	}

	/**
	 * Permet a un personnage de sortir du batiment
	 * @param ileDuJeu
	 * @param plateauDuJeu
	 * @param tableauAffichage
	 * @param affichage
	 * @param equipe
	 */
	public void sortieBatiment(ile ileDuJeu, Plateau plateauDuJeu, int[][] tableauAffichage, Affichage affichage, int equipe){
		plateauDuJeu.refreshinfo(null,this);

		if(stockBatiment.isEmpty()){
			//affichage d'un message si pas de personnage dans le batiment
			affichage.popUp(equipe, "Il n'y a pas de personnages.", "Attention" );
		}else if(!this.sortieImpossible(ileDuJeu)){
			//S'il y a de la place autour du batiment
			Personnage persoSortant = null;
			ActionJoueur action= new ActionJoueur();
			int nbrVivantJouable=0, i=0;
			for(Personnage perso : stockBatiment){
				if(perso.sortiePossible(y, x, ileDuJeu))
					nbrVivantJouable++;
			}
			if(nbrVivantJouable>0){
				if(this.placeLibre(ileDuJeu)){
					//S'il y a au moins un personnage vivant et jouable dans le batiment	
					Personnage [] listePerso= new Personnage[nbrVivantJouable];
					for(Personnage perso: stockBatiment){
						if(perso.sortiePossible(y, x, ileDuJeu)){
							listePerso[i]=perso;
							i++;
						}
					}
					persoSortant=(Personnage)affichage.popUpYesNo(equipe,"\nQuels personnage voulez-vous faire sortir ?\n\n", "Sortie de personnage",listePerso);  
					if(persoSortant!=null){
						//si le joueur a choisi de faire sortir un personnage	
						affichage.setVisibleActionPerso(true, null);
						int[] cordonnees = action.choixCaseSortie(plateauDuJeu, tableauAffichage, x, y, persoSortant);
						if(cordonnees[0]!=777){
							//si le joueur n'annule pas l'action	
							if(tableauAffichage[cordonnees[1]][cordonnees[0]]==17)
								ileDuJeu.getTableau()[cordonnees[0]][cordonnees[1]].setPersonnageCourant(persoSortant);
							else
								persoSortant.recuperationStuff(true,false, 0, 0, cordonnees[0], cordonnees[1],ileDuJeu.getTableau(), affichage, equipe);
							persoSortant.setAction(false);
							stockBatiment.remove(persoSortant);
						}
					}
				}else
					affichage.popUp(equipe, "Il n'y a pas de place libre pour pouvoir placer un personnage", "Attention" );
			}else{
				affichage.popUp(equipe, "Il n'y a pas de personnages jouables", "Attention" );
			}
		}else{
			affichage.popUp(equipe, "Il n'y a pas de place libre pour pouvoir placer un personnage", "Attention" );
			this.actionImpossible();
		}
		affichage.setVisibleActionPerso(false,null);
	}

	/**
	 * Set l'id du batiment
	 * @param id
	 */
	public void setId(int id){
		this.id=id;
	}

	/**
	 * 
	 * @return l'id du batiment
	 */
	public int getId(){
		return id;
	}

	public String toString(){
		return "";
	}

	/**
	 * Tue les personnages situés dans le batiment lors de sa destruction
	 * @param tableauIle
	 */
	public void destructionBatiment(Case[][] tableauIle){
		for(Personnage perso: stockBatiment){
			perso.setDeath(true);
		}
		tableauIle[x][y].removeBatimentCourant(false);
	}
}
