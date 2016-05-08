import java.util.ArrayList;

/**
 * Cree un joueur 
 * @author Allan
 * @version 1.0
 */
public class Joueur {
	private ArrayList<Personnage> equipe = new ArrayList<Personnage>();
	private ArrayList<Batiment> batiments = new ArrayList<Batiment>();
	private boolean equipe1, coffreTrouve=false;
	private int idBateau=2, ligneBateau, colonneBateau, nbrVillage=0, niveauVillage=0, idFort=20, stockRessources=40;

	/**
	 * Construit un joueur en lui donnant un boolean correspondant a son equipe et l'id de son bateau
	 */
	Joueur(boolean equipe1){
		this.equipe1=equipe1;
		if(!equipe1){
			idBateau++;
			idFort++;
		}
	}
	
	/**
	 * 
	 * @return l'equipe de personnage du joueur
	 */
	public ArrayList<Personnage> getPersos(){
		return equipe;
	}

	/**
	 * Ajoute le batiment dans la liste de batiment du joueur
	 * @param batiment
	 */
	public void addBatiment(Batiment batiment){
		batiments.add(batiment);
	}
	
	/**
	 * 
	 * @return la liste de batiment du joueur
	 */
	public ArrayList<Batiment> getBatiment(){
		return batiments;
	}

	/**
	 * 
	 * @return l'id du fort du joueur
	 */
	public int getIdFort(){
		return idFort;
	}

	/**
	 * set l'id du fort du joueur
	 * @param idfort
	 */
	public void setIdFort(int idfort){
		this.idFort=idfort;
	}

	/**
	 * @return the ligneBateau
	 */
	public int getLigneBateau() {
		return ligneBateau;
	}
	
	/**
	 * 
	 * @return le nombre de village construit par le joueur
	 */
	public int getNbrVillage(){
		return nbrVillage;
	}
	
	/**
	 * change nbrVillage à 1
	 */
	public void addVillage(){
		nbrVillage=1;
	}

	/**
	 * Augmente le niveau du village du joueur
	 */
	public void incrNiveauVillage(){
		niveauVillage++;
	}
	
	/**
	 * 
	 * @return les stock de ressources du joueur
	 */
	public int getStockRessource(){
		return stockRessources;
	}
	
	/**
	 * diminue le stock du joueur
	 * @param stock
	 */
	public void setDownStockRessource(int stock){
		stockRessources-=stock;
	}
	
	/**
	 * Augmente le stock du joueur
	 * @param stock
	 */
	public void setUpStockRessource(int stock){
		stockRessources+=stock;
	}
	
	/**
	 * 
	 * @return le niveau du village du joueur
	 */
	public int getNiveauVillage(){
		return niveauVillage;
	}

	/**
	 * @return the colonneBateau
	 */
	public int getColonneBateau() {
		return colonneBateau;
	}

	/**
	 * Met en place le bateau du joueur dans l'ile
	 * @param ileDuJeu
	 */
	public void setBateau(ile ileDuJeu){
		if(equipe1){
			ligneBateau=ileDuJeu.getLigneNavJ1();
			colonneBateau=ileDuJeu.getColonneNavJ1();
		}else{
			ligneBateau=ileDuJeu.getLigneNavJ2();
			colonneBateau=ileDuJeu.getColonneNavJ2();
		}
	}

	/**
	 * 
	 * @return l'equipe du joueur
	 */
	public boolean getEquipe(){
		return equipe1;
	}

	/**
	 * Ajoute un personnage a l'equipe du joueur
	 * @param perso
	 */
	public void addPerso(Personnage perso){
		equipe.add(perso);
	}

	/**
	 * met la variable coffreTrouve a true
	 */
	public void coffreTrouve(){
		coffreTrouve=true;
	}

	/**
	 * 
	 * @return coffreTrouve
	 */
	public boolean getCoffreTrouve(){
		return coffreTrouve;
	}

	/**
	 * Dit si l'equipe vivante du joueur a encore une action ou un deplacement possible
	 * @return vrai si encore personnage avec une action
	 */
	public boolean actionPossible(){
		for(Personnage perso : equipe){
			if(perso.actionOuDeplacement() && !perso.getDeath())
				return true;
		}
		return false;
	}

	/**
	 * Remet toutes les actions et deplacement des personnages de l'equipe du joueur a true
	 * @param affichage
	 * @param equipe
	 */
	public void resetAction(Affichage affichage, int equipe){
		if(Test.testEnCours){
			if(this.equipe.get(this.equipe.size()-1).getCompteur()==0){
				this.equipe.get(this.equipe.size()-1).setActionDeplacement(true);
			}else{
				this.equipe.get(this.equipe.size()-1).downCompteur();
				if(this.equipe.get(this.equipe.size()-1) instanceof Ouvrier && this.equipe.get(this.equipe.size()-1).compteur==0 && ((Ouvrier) this.equipe.get(this.equipe.size()-1)).getUpgrade()){
					((Ouvrier)this.equipe.get(this.equipe.size()-1)).setUpgrade(false);
				}
			}
		}else{
			for(Personnage perso : this.equipe){
				if(perso.getCompteur()==0){
					perso.setActionDeplacement(true);
				}else{
					perso.downCompteur();
					if(perso instanceof Ouvrier && perso.compteur==0 && ((Ouvrier) perso).getUpgrade()){
						((Ouvrier)perso).setUpgrade(false);
					}
				}
			}
		}
	}
	
	/**
	 * Fait exploser les bombes du joueur
	 * @param tableauIle
	 * @param affichage
	 * @param equipe1
	 */
	private void ExplosionBombes(Case[][] tableauIle, Affichage affichage, int equipe1){
		for(Personnage perso : equipe){
			if(perso instanceof Piegeur){
				((Piegeur) perso).downCompteurBombe(tableauIle, affichage, equipe1);
			}
		}
	}

	/**
	 * Met toutes les actions et deplacement des personnages de l'equipe du joueur a false pour pouvoir passer son tour
	 */
	public void passerTour(){
		for(Personnage perso : equipe){
			perso.setActionDeplacement(false);
		}
	}

	/**
	 * 
	 * @return true si un personnage de l'equipe est vivant
	 */
	public boolean persoVivant(){
		for(Personnage perso: equipe){
			if(!perso.getDeath())
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @return le nombre de personnage vivant dans l'equipe du joueur
	 */
	public int nbrVivant(){
		int nbrVivant=0;
		for(Personnage perso: equipe){
			if(!perso.getDeath())
				nbrVivant++;
		}
		return nbrVivant;
	}

	/**
	 * 
	 * @return l'id du bateau du joueur
	 */
	public int getIdBateau() {
		return idBateau;
	}

	/**
	 * Tue tous les personnages du joueur s'il abandonne
	 */
	public void abandon(){
		for(Personnage perso: equipe){
			perso.setDeath(true);
		}
	}
	
	/**
	 * 
	 * @return un string listant les perso du joueur qui ont subi des dommages pendant le tour de l'adversaire
	 */
	public String persoAttaque(){
		String actionEnnemi="";
		int cpt=0;
		for(Personnage perso:equipe){
			if(perso.getEnergie()<perso.getEnergieTourPrecedent() || perso.getDeath()!=perso.getDeathTourPrecedent()){
				cpt++;
			}
		}
		if(cpt>0){
			for(Personnage perso:equipe){
				if(perso.getEnergie()<perso.getEnergieTourPrecedent() ){
					actionEnnemi+=""+perso.getType()+" "+perso.getNom()+" a reçu "+(perso.getEnergieTourPrecedent()-perso.getEnergie())+" dommages. \n";
					perso.setEnergieTourPrecedent(perso.getEnergie());
				}else if(perso.getDeath()!=perso.getDeathTourPrecedent()){
					actionEnnemi+=""+perso.getType()+" "+perso.getNom()+" est mort des dommages qu'il a reçu pendant ce tour.\n";
					perso.setDeathTourPrecedent(perso.getDeath());
				}
			}
		}
		return actionEnnemi;
	}

	/**
	 * 
	 * @return un string listant les perso du joueur a qui ont a volé des affaires pendnat le tour du joueur ennemi
	 */
	public String persoVole(){
		String actionEnnemi="";
		int cpt=0;
		for(Personnage perso:equipe){
			if(!perso.getInventaire().equals(perso.getInventaireTourPrecedent())){
				cpt++;
			}
		}
		if(cpt>0){
			for(Personnage perso:equipe){
				if(!perso.getInventaire().equals(perso.getInventaireTourPrecedent())){
					actionEnnemi+=""+perso.getType()+" "+perso.getNom()+" s'est fait voler";
					for(int i=0;i<perso.getInventaireTourPrecedent().size();i++){
						if(perso.nbrObjetInventaire(perso.getInventaireTourPrecedent().get(i), perso.getInventaire())<perso.nbrObjetInventaire(perso.getInventaireTourPrecedent().get(i), perso.getInventaireTourPrecedent())){
							actionEnnemi+=" "+perso.getInventaireTourPrecedent().get(i);
							perso.getInventaireTourPrecedent().remove(i);
						}
					}
					actionEnnemi+="\n";
				}
			}
		}
		return actionEnnemi;
	}

	/**
	 * Soigne les personnages du joueur qui sont dans des batiments
	 */
	private void soinPersoBatiment(){
		for(Batiment bat:batiments){
			bat.recupEnergie();
		}
	}
	
	/**
	 * Attaque les ennemis qui sont autour du fort du joueur
	 * @param tableauIle
	 * @param affichage
	 * @param equipe
	 */
	private void attaqueFort(Case[][] tableauIle, Affichage affichage, int equipe){
		for(Batiment bat: batiments){
			if(bat instanceof Fort)
				((Fort) bat).attaque(tableauIle, affichage, equipe);
		}
	}
	
	/**
	 * Action du joueur à la fin de son tour
	 * @param ileDuJeu
	 * @param affichage
	 * @param equipe
	 */
	public void actionFinPartie(ile ileDuJeu, Affichage affichage, int equipe){
		soinPersoBatiment();
		ExplosionBombes(ileDuJeu.getTableau(), affichage, equipe);
		attaqueFort(ileDuJeu.getTableau(), affichage, equipe);
	}
}
