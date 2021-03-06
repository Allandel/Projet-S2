import java.util.ArrayList;

/**
 * Classe permettant de creer des Personnages (Explorateurs / Voleurs / Piegeurs [prochainement])
 * @author Valentin
 * @version 1.1 
 */

public class Personnage{

	private int energie=100, id;
	protected int compteur=0;
	private int energieTourPrecedent=100;
	private String nom, type;
	private boolean death=false, action=true, deplacement=true, deathTourPrecedent=false;
	private ArrayList <String> inventaire=new ArrayList<String>(),inventaireTourPrecedent=new ArrayList<String>();
	protected Joueur joueur;

	/**
	 * Cree un personnage et lui specifie son equipe et l'id de son bateau suivant l'equipe
	 * @param equipe
	 * @param joueur
	 */
	Personnage(Joueur joueur){
		joueur.addPerso(this);
		this.joueur=joueur;
	}

	/**
	 * 
	 * @return l'energie que le personnage avait le tour precedent
	 */
	public int getEnergieTourPrecedent(){
		return energieTourPrecedent;
	}

	/**
	 * @return the deathTourPrecedent
	 */
	public boolean getDeathTourPrecedent() {
		return deathTourPrecedent;
	}

	/**
	 * @param deathTourPrecedent the deathTourPrecedent to set
	 */
	public void setDeathTourPrecedent(boolean deathTourPrecedent) {
		this.deathTourPrecedent = deathTourPrecedent;
	}

	/**
	 * Setter d'energieTourPrecedent
	 * @param setter
	 */
	public void setEnergieTourPrecedent(int setter){
		energieTourPrecedent=setter;
	}

	/**
	 * 
	 * @return s'il peut encore se deplacer
	 */
	public boolean getDeplacement(){
		return deplacement;
	}

	/**
	 * Change la valeur de deplacement
	 * @param deplacement
	 */
	public void setDeplacement(boolean deplacement){
		this.deplacement=deplacement;
	}

	/**
	 * Change la valeur de death
	 * @param death
	 */
	public void setDeath(boolean death){
		this.death=death;
	}

	/**
	 * 
	 * @return le joueur du personnage
	 */
	public Joueur getJoueur(){
		return joueur;
	}

	/**
	 * 
	 * @return vrai si le personnage peut faire une action ou un deplacement
	 */
	public boolean actionOuDeplacement(){
		if(action || deplacement)
			return true;
		return false;
	}

	/**
	 * @param nom the nom to set
	 */
	void setNom(String nom){
		this.nom=nom;
	}

	/**
	 * 
	 * @return true si le personnage peut encore faire une action
	 */
	public boolean getAction(){
		return action;
	}

	/**
	 * Change la valeur d'action
	 * @param action
	 */
	public void setAction(Boolean action){
		this.action=action;
	}

	/**
	 * 
	 * @return l'id du bateau du personnage
	 */
	public int getIdBateau(){
		return joueur.getIdBateau();
	}

	/**
	 * 
	 * @return l'id du fort de l'équipe du joueur
	 */
	public int getIdFort(){
		return joueur.getIdFort();
	}

	/**
	 * @param type the type to set
	 */
	void setType(String type){
		this.type=type;
	}
	/**
	 * @return the type
	 */
	public String getType(){
		return type;
	}
	/**
	 * @return the nom
	 */
	public String getNom(){
		return nom;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id){
		this.id=id;
	}
	/**
	 * @return the id
	 */
	public int getId(){
		return id;
	}
	/**
	 * Ajoute un objet à l'inventaire
	 * @param objet
	 */
	public void setObjetInventaire(String objet){
		inventaire.add(objet);
		inventaireTourPrecedent.add(objet);
	}

	/**
	 * Enleve un objet de l'inventaire et aussi de celui de l'inventaire du tour précédent en fonction de si c'était un vol ou pas
	 * @param objet
	 * @param vol
	 */
	public void removeObjetInventaire(String objet, boolean vol){
		inventaire.remove(objet);
		if(!vol)
			inventaireTourPrecedent.remove(objet);
	}
	/**
	 * @return the inventaire
	 */
	public ArrayList<String> getInventaire(){
		return this.inventaire;
	}
	/**
	 * @return the inventaireTourPrecedent
	 */
	public ArrayList<String> getInventaireTourPrecedent() {
		return inventaireTourPrecedent;
	}

	/**
	 * Explore l'inventaire a la recherche d'un objet precis
	 * @param objet
	 */
	public boolean getObjetInventaire(String objet){
		for (String test: inventaire) {
			if(test==null){
				return false;
			}
			if (test.compareTo(objet)==0){
				return true;
			}
		}
		return false;
	}

	/**
	 * Permet a un personnage de faire un echange avec un personnage de son equipe
	 * @param p
	 * @param affichage
	 * @param equipe
	 */
	public void echangeObjet(Personnage p, Affichage affichage, int equipe){
		String itemEchange;
		int decision=(int)affichage.popUpYesNo(equipe,"Désirez vous effectuer un échange avec ce membre de votre équipe ?", "Effectuer un echange ?",null);
		if (decision==0){
			if(!this.inventaire.isEmpty() || !p.inventaire.isEmpty()){
				if(!this.inventaire.isEmpty()){
					//=======DONS========
					String [] listeItem= new String[inventaire.size()];
					for(int cpt=0; cpt<inventaire.size(); cpt++){
						listeItem[cpt]=inventaire.get(cpt);
					}
					itemEchange=(String)affichage.popUpYesNo(equipe,"Quels Item voulez vous donner ?\n\n( Pour ne rien donner, cliquez sur annuler)", "DONNER ITEM",listeItem); 
					if(itemEchange!=null){
						p.setObjetInventaire(itemEchange);
						this.removeObjetInventaire(itemEchange,false);
						action=false;
					}
				}else{
					affichage.popUp(equipe,"Votre inventaire est vide, impossible de lui donner un objet", "ECHANGE IMPOSSIBLE" );
				}
				if(!inventairePlein(affichage,"Votre inventaire est plein, vous ne pouvez rien y ajouter.")){
					if(!p.inventaire.isEmpty()){
						//=======PRENDRE ITEM=======
						String [] listeItem2= new String[p.inventaire.size()];
						for(int cpt=0; cpt<p.inventaire.size(); cpt++){
							listeItem2[cpt]=p.inventaire.get(cpt);
						}
						itemEchange=(String)affichage.popUpYesNo(equipe,"Quels Item voulez vous prendre dans l'inventaire de votre coequipier ?\n\n( Pour ne rien prendre, cliquez sur annuler)", "PRENDRE ITEM",listeItem2); 
						if(itemEchange!=null){
							this.setObjetInventaire(itemEchange);
							p.removeObjetInventaire(itemEchange,false);
							action=false;
						}
					}else{
						affichage.popUp(equipe,"L'inventaire de votre coequipier est vide, impossible de lui prendre un objet", "ECHANGE IMPOSSIBLE" );
					}
				}
			}else{
				affichage.popUp(equipe,"Vos inventaires sont vides, impossible de faire des échanges", "ECHANGE IMPOSSIBLE" );

			}
		}

	}	

	/**
	 * Permet les deplacement des personnages
	 * @param xAvant
	 * @param yAvant
	 * @param xApres
	 * @param yApres
	 * @param tableauIle
	 * @param affichage
	 * @param equipe
	 */
	public void mouvement(int xAvant, int yAvant, int xApres, int yApres, Case [][] tableauIle, Affichage affichage, int equipe){
		tableauIle[xAvant][yAvant].removePersonnageCourant();
		tableauIle[xApres][yApres].setPersonnageCourant(this);
		if(tableauIle[xApres][yApres].getTeamPiege()!=(this.getIdBateau()-2))
			tableauIle[xApres][yApres].setPiege(false);
		perteEnergie(1, xApres,yApres, tableauIle, false, true, affichage, equipe);
	}

	/**
	 * Permet a un personnage de rentrer dans le bateau 
	 * @param xAvant
	 * @param yAvant
	 * @param xApres
	 * @param yApres
	 * @param tableauIle
	 * @param affichage
	 * @param equipe
	 * @return true si le personnage possède le tresor
	 */
	public boolean[] entreeBatiment(int xAvant, int yAvant, int xApres, int yApres, Case [][] tableauIle, Affichage affichage, int equipe){
		boolean victoire[]={false,false};

		if(joueur.nbrVivant()>tableauIle[xApres][yApres].getBatimentCourant().nbrVivantStock()+1 || tableauIle[xApres][yApres].getBatimentCourant() instanceof Fort|| tableauIle[xApres][yApres].getBatimentCourant() instanceof Mine || Test.testEnCours){
			int decision=(int)affichage.popUpYesNo(equipe,"Voulez vous vraiment rentrer votre personnage ?", "Rentrer",null);
			if (decision==0){
				tableauIle[xApres][yApres].getBatimentCourant().addPersoBatiment(this);
				tableauIle[xAvant][yAvant].removePersonnageCourant();
				this.recuperationStuff(false,true, xAvant,yAvant,xApres,yApres, tableauIle, affichage, equipe);
				perteEnergie(1, xApres,yApres, tableauIle, false,false, affichage, equipe);
				if(inventaire.contains("Tresor") && tableauIle[xApres][yApres].getBatimentCourant() instanceof Navire){
					victoire[0]=true;
					victoire[1]=joueur.getEquipe();
				}else if(!(tableauIle[xApres][yApres].getBatimentCourant() instanceof Mine)){
					if(this instanceof Guerrier && !inventaire.contains("Epee") && !inventairePlein(affichage, "Ce guerrier n'a plus de place dans son inventaire pour récupérer une épée.")){
						this.setObjetInventaire("Epee");
						affichage.popUp(equipe,"En retournant dans le batiment, votre Guerrier à récupére une épée ! Au combat !", "Recuperation d'une arme");
					}else if(this instanceof Piegeur && !inventaire.contains("Pelle") && !inventaire.contains("Bombe") && !inventairePlein(affichage, "Ce piegeur n'a plus de place dans son inventaire pour récupérer une pelle et des bombes.")){
						this.setObjetInventaire("Pelle");
						int cpt=0;
						while(inventaire.size()<6 && cpt<3){
							this.setObjetInventaire("Bombe");
							cpt++;
						}
					}else if(this instanceof Piegeur && !inventaire.contains("Pelle") && !inventairePlein(affichage, "Ce piegeur n'a plus de place dans son inventaire pour récupérer une pelle.")){
						this.setObjetInventaire("Pelle");
						affichage.popUp(equipe,"En retournant dans le batiment, votre Pigeur à récupére une pelle!", "Recuperation d'une pelle");
					}else if(this instanceof Piegeur && !inventaire.contains("Bombe") && !inventairePlein(affichage, "Ce piegeur n'a plus de place dans son inventaire pour récupérer une bombe.")){
						int cpt=0;
						do{
							this.setObjetInventaire("Bombe");
							cpt++;
						}while(inventaire.size()<6 && cpt<3);
						affichage.popUp(equipe,"En retournant dans le batiment, votre Pigeur à récupére une/des bombe(s)!", "Recuperation de bombe");
					}else if(this instanceof Ouvrier && !inventaire.contains("Pioche") && !inventairePlein(affichage, "Cet ouvrier n'a plus de place dans son inventaire pour récupérer une pioche.")){
						this.setObjetInventaire("Pioche");
						affichage.popUp(equipe,"En retournant dans le batiment, votre Ouvrier à récupére une pioche!", "Recuperation d'une pioche");
					}
				}
				if(victoire[0]==false){
					int cpt=this.viderInventaireDeRochers();
					joueur.setUpStockRessource(cpt);
					if(cpt>0){
						affichage.popUp(equipe,"En retournant dans ce batiment, votre Personnage à livrer "+cpt+" pierre(s) au stock de ressources", "Ajout de pierres");	
					}
				}

			}else if(tableauIle[xApres][yApres].getBatimentCourant() instanceof Navire)
				affichage.popUp(equipe,"Il faut au moins un personnage sur l'ile pour pouvoir rentrer au bateau.", "Entree impossible" );
		}
		return victoire;
	}


	/**
	 * Vide l'inventaire du joueur s'il contient des pierres
	 * @return le nombre de rocher de l'inventaire
	 */
	public int viderInventaireDeRochers(){
		int cpt=0;
		for(int i=0; i<6;i++){
			if(this.getObjetInventaire("Pierre")){
				this.removeObjetInventaire("Pierre", false);
				cpt++;
			}
		}
		return cpt;
	}


	/**
	 * Fait perdre de l'energie au personnage
	 * @param nrj
	 * @param x
	 * @param y
	 * @param tableauIle
	 * @param attaque
	 * @param deplacement
	 * @param affichage
	 * @param equipe
	 * @return true si le personnage meurt de la perte d'energie
	 */
	protected boolean perteEnergie(int nrj, int x, int y, Case[][] tableauIle, boolean attaque, boolean deplacement, Affichage affichage, int equipe){
		if(energie-nrj<=0){
			if(tableauIle[x][y].getId()!=2 && tableauIle[x][y].getId()!=3 && tableauIle[x][y].getId()<20 )
				tableauIle[x][y].setId(16);
			death=true;
			if(!attaque){
				//test pour eviter d'afficher du texte inutile	
				affichage.popUp(equipe,"Votre personnage etait a bout de force... Cette ultime action lui a coute la vie. Son inventaire se trouve desormais au sol et peut etre recuperer par n'importe quel personne", "VOTRE PERSONNAGE EST MORT" );
			}
			if(deplacement)
				this.deplacement=false;
			else
				action=false;
			return true;
		}else{
			energie-=nrj;
			if(!attaque)
				this.energieTourPrecedent=energie;
			if(deplacement)
				this.deplacement=false;
			else
				action=false;
			return false;
		}

	}

	/**
	 * Permet a un personnage de recuperer l'inventaire laisse au sol ou laisse dans le bateau par un personnage mort
	 * @param sortieBateau
	 * @param entreeBateau
	 * @param x
	 * @param y
	 * @param xApres
	 * @param yApres
	 * @param tableauIle
	 * @param affichage
	 * @param equipe
	 */
	public void recuperationStuff(boolean sortieBateau, boolean entreeBateau, int x, int y, int xApres, int yApres, Case[][] tableauIle, Affichage affichage, int equipe){
		String res="\n\n";

		if(!entreeBateau){
			if(!inventairePlein(affichage,"Votre inventaire est plein, vous ne pouvez rien y ajouter.")){
				if(tableauIle[xApres][yApres].getPersonnageCourant().getInventaire().isEmpty()){
					affichage.popUp(equipe,"Ce cadavre n'avait rien d'interessant...", "Rencontre avec un mort" );
				}else{
					for (int i=0; i<tableauIle[xApres][yApres].getPersonnageCourant().getInventaire().size();i++){
						this.setObjetInventaire(tableauIle[xApres][yApres].getPersonnageCourant().getInventaire().get(i));
						res+="+ "+tableauIle[xApres][yApres].getPersonnageCourant().getInventaire().get(i)+"\n";
					}
					affichage.popUp(equipe,"Vous avez recupere des objets sur le cadavre... Vous en aurez plus besoin que lui.\nVous avez recuperer :"+res, "Rencontre avec un mort" );
				}

				tableauIle[xApres][yApres].removePersonnageCourant();
				if(!sortieBateau)
					tableauIle[x][y].removePersonnageCourant();
				this.perteEnergie(1, xApres, yApres, tableauIle, false, true, affichage, equipe);				
				tableauIle[xApres][yApres].setPersonnageCourant(this);
				if(tableauIle[xApres][yApres].getPiege())
					this.immobilisation(affichage, equipe);
			}
		}else{
			if(tableauIle[xApres][yApres].getBatimentCourant().persoMort()){
				for(Personnage perso : tableauIle[xApres][yApres].getBatimentCourant().getStockBatiment()){
					if(perso.getDeath() && !perso.getInventaire().isEmpty()){
						for (int i=0; i<perso.getInventaire().size();i++){
							this.setObjetInventaire(perso.getInventaire().get(i));
							res+="+ "+perso.getInventaire().get(i)+"\n";
						}
						affichage.popUp(equipe,"Vous avez recupere des objets sur le cadavre... Vous en aurez plus besoin que lui.\nVous avez recuperer :"+res, "Rencontre avec un mort" );
					}
				}
			}
		}
	}

	/**
	 * Immobilise un personnage a cause d'un piege
	 * @param affichage
	 * @param equipe
	 */
	public void immobilisation(Affichage affichage, int equipe){
		affichage.popUp(equipe,"Votre personnage est tomber dans un piege adverse ! Il sera immobilise durant les 3 tours suivants et ne pourra effectuer aucune action !", "C'ETAIT UN PIEGE !" );
		this.compteur=3;
		action=false;
	}

	/**
	 * Test si un personnage peut sortir du bateau
	 * @param i
	 * @param j
	 * @param ileDuJeu
	 * @return true s'il peut sortir du bateau
	 */
	public boolean sortiePossible(int i, int j, ile ileDuJeu){
		if(!death && action){
			if(!(this instanceof Explorateur)){
				for(int x=i-1;x<i+2;x++){
					for(int y=j-1;y<j+2;y++){
						if(ileDuJeu.getTableau()[y][x].getId()==17 || ileDuJeu.getTableau()[y][x].getId()==16 || (ileDuJeu.getTableau()[y][x].getId()>5 && ileDuJeu.getTableau()[y][x].getId()<16 && ileDuJeu.getTableau()[y][x].getPersonnageCourant().getDeplacement()))
							return true;
					}
				}
			}else{
				if(ileDuJeu.getTableau()[j-1][i].getId()==17 || ileDuJeu.getTableau()[j-1][i].getId()==16 || (ileDuJeu.getTableau()[j-1][i].getId()>5 && ileDuJeu.getTableau()[j-1][i].getPersonnageCourant().getDeplacement())){
					return true;
				}else if(ileDuJeu.getTableau()[j+1][i].getId()==17 || ileDuJeu.getTableau()[j+1][i].getId()==16 || (ileDuJeu.getTableau()[j+1][i].getId()>5 && ileDuJeu.getTableau()[j+1][i].getPersonnageCourant().getDeplacement())){
					return true;
				} else if(ileDuJeu.getTableau()[j][i-1].getId()==17 || ileDuJeu.getTableau()[j][i-1].getId()==16 || (ileDuJeu.getTableau()[j][i-1].getId()>5 && ileDuJeu.getTableau()[j][i-1].getPersonnageCourant().getDeplacement())){
					return true;
				}else if(ileDuJeu.getTableau()[j][i+1].getId()==17 || ileDuJeu.getTableau()[j][i+1].getId()==16 || (ileDuJeu.getTableau()[j][i+1].getId()>5 && ileDuJeu.getTableau()[j][i+1].getPersonnageCourant().getDeplacement())){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * augmente l'energie du personnage
	 * @param heal
	 */
	public void addEnergie(int heal){
		if(energie<100){
			if(energie+heal<=100){
				energie+=heal;
			}else
				energie+=(100-energie);
		}
	}

	/**
	 * 
	 * @return l'energie du personnage
	 */
	public int getEnergie(){
		return energie;
	}

	/**
	 * Diminue le compteur de tour immobilise
	 */
	public void downCompteur(){
		compteur--;
	}

	/**
	 * 
	 * @return true si le personnage est mort
	 */
	public boolean getDeath(){return death;}

	/**
	 * 
	 * @return le nombre de tour ou le personnage est encore immobilise
	 */
	public int getCompteur(){
		return compteur;
	}

	/**
	 * Set le compteur d'immobilisation
	 * @param i
	 */
	public void setCompteur(int i){
		compteur=i;
	}

	/**
	 * ToString pour l'affichage dans le menu de sortie de bateau
	 */
	public String toString(){
		if(inventaire.contains("Cle"))
			return ""+this.getType()+" "+this.getNom()+"  [Energie:"+this.energie+"]  - Cle";
		else
			return ""+this.getType()+" "+this.getNom()+"  [Energie:"+this.energie+"]";
	}

	/**
	 * ToString pour l'affichage dans la console
	 * @param console
	 * @return un string
	 */
	public String toString(boolean console){
		return "";
	}

	/**
	 * Test si l'inventaire est plein ou non et affiche un message s'il est plein
	 * @param affichage
	 * @return true si inventaire plein
	 */
	protected boolean inventairePlein(Affichage affichage, String texte){
		if(inventaire.size()==6){
			affichage.popUp(joueur.getIdBateau()-2, texte, "Inventaire plein");
			return true;
		}else
			return false;
	}

	/**
	 * Set deplacement et action
	 * @param setter
	 */
	public void setActionDeplacement(boolean setter){
		this.setAction(setter);
		this.setDeplacement(setter);
	}

	/**
	 * Permet au perso d'abandonner un objet
	 * @param affichage
	 * @param equipe
	 */
	public void abandonnerObjet(Affichage affichage, int equipe){
		String itemAbandonne;
		String [] listeItem;
		if(inventaire.size()>1){
			listeItem= new String[inventaire.size()+1];
			listeItem[inventaire.size()]="Tout abandonner";
		}else
			listeItem= new String[inventaire.size()];

		for(int cpt=0; cpt<inventaire.size(); cpt++){
			listeItem[cpt]=inventaire.get(cpt);
		}

		itemAbandonne=(String)affichage.popUpYesNo(equipe,"Quels Item voulez vous abandonner ?\n\n( Pour ne rien donner, cliquez sur annuler)", "ABANDONNER ITEM",listeItem);		

		if(itemAbandonne!=null){
			if(itemAbandonne=="Tout abandonner"){
				inventaire.removeAll(inventaire);
				inventaireTourPrecedent.removeAll(inventaire);
			}else{
				this.removeObjetInventaire(itemAbandonne, false);
			}
		}
	}

	/**
	 * 
	 * @param objet
	 * @param list
	 * @return le nombre d'objet dans l'inventaire
	 */
	public int nbrObjetInventaire(String objet,ArrayList<String> list){
		int nbr=0;
		for(String obj:list){
			if(obj.equals(objet))
				nbr++;
		}
		return nbr;
	}
}
