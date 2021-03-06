import java.util.Random;


public class Fort extends Batiment{
	private int niveau=1;

	protected int dmg=10;
	protected boolean upgrade=false;

	/**
	 * Construit un fort au coordonnees données
	 * @param x
	 * @param y
	 * @param joueur
	 */
	public Fort(int x, int y, Joueur joueur){
		super(x, y, joueur);
		this.setId(joueur.getIdFort());
		this.type="Fort";
		joueur.incrNiveauVillage();
	}

	public String toString(){
		return "F";
	}

	/**
	 * 
	 * @return le niveau actuel du fort
	 */
	public int getNiveau(){
		return niveau;
	}

	public void  evolution(Affichage affichage, int equipe, ile ileDuJeu){
		Personnage perso = getPersonnageListe("Ouvrier");
		if(niveau==1 && joueur.getStockRessource()>=10 && this.ouvrierPresent()){
			int decision=(int)affichage.popUpYesNo(equipe,"Voulez vous vraiment améliorer votre Village en Forteresse ?\n\n(Attention, l'amélioration immobilisera votre ouvrier pour 2 tours)", "Améliorer votre village",null);
			if (decision==0){
				((Ouvrier)perso).setUpgrade(true);
				perso.compteur=2;
				upgrade=true;
				perso.setActionDeplacement(false);
			}
		}else if(niveau==2 && joueur.getStockRessource()>=30 && this.ouvrierPresent()){
			int decision=(int)affichage.popUpYesNo(equipe,"Voulez vous améliorer votre Forteresse au niveau 3 ?\n\n(Attention, l'amélioration immobilisera votre ouvrier pendant 3 tours)", "Créer un village",null);
			if (decision==0){
				((Ouvrier)perso).setUpgrade(true);
				perso.compteur=3;
				upgrade=true;
				perso.setActionDeplacement(false);
			}
		}else{
			if((niveau==1 && joueur.getStockRessource()<=10)){
				affichage.popUp(equipe, "Vous n'avez pas assez de ressources, il vous en faut encore "+(10-joueur.getStockRessource()), "Impossible d'évoluer");
			}else if((niveau==2 && joueur.getStockRessource()<=30)){
				affichage.popUp(equipe, "Vous n'avez pas assez de ressources, il vous en faut encore "+(30-joueur.getStockRessource()), "Impossible d'évoluer");
			}else if(!this.ouvrierPresent()){
				affichage.popUp(equipe, "Il vous faut un ouvrier au sein de votre ville pour évoluer !", "Impossible d'évoluer");
			}

		}
	}
/**
 * Permet d'augmenter le niveau du fort
 * @param equipe
 * @param ileDuJeu
 */
public String evolutionFinale(int equipe, ile ileDuJeu){
	if(niveau==1 && joueur.getStockRessource()>=10 && this.ouvrierPresent()){
		niveau=2;
		batimentHealth=200;
		joueur.incrNiveauVillage();
		joueur.setDownStockRessource(10);
		heal=20;
		if(equipe==0){
			this.setId(22);
			joueur.setIdFort(22);
			ileDuJeu.getTableau()[x][y].setId(22);
		}else{
			this.setId(23);
			joueur.setIdFort(23);
			ileDuJeu.getTableau()[x][y].setId(23);
		}
		return "Votre village a été amélioré en Forteresse ! Votre base est désormais plus résistante et soigne mieux !";
		}else if(niveau==2 && joueur.getStockRessource()>=30 && this.ouvrierPresent()){
			niveau=3;
			dmg=20;
			batimentHealth=300;
			joueur.incrNiveauVillage();
			joueur.setDownStockRessource(30);
			return "Votre Forteresse a été amélioré! Elle est désormais plus résistante et inflige plus de dégats alentours !";
		}
	return "";
	}

	/**
	 * Affichage des actions possible avec le fort quand on le selectionne
	 * @param x
	 * @param y
	 * @param ileDuJeu
	 * @param plateauDuJeu
	 * @param tableauAffichage
	 * @param affichage
	 * @param equipe
	 */
	public void actionFort(int x, int y, ile ileDuJeu, Plateau plateauDuJeu, int[][] tableauAffichage, Affichage affichage, int equipe){
		plateauDuJeu.refreshinfo(null,this);
		if(niveau==3)
			this.sortieBatiment(ileDuJeu, affichage.getPlateau(), tableauAffichage, affichage, equipe);
		else{
			String [] tab={"Sortir un Personnage","Monter le Niveau de la Forteresse"};
			String action=null;
			action=(String)affichage.popUpYesNo(equipe,"\nQue voulez vous faire ?\n\n[Cliquez sur annuler si vous ne voulez rien faire]\n", "Choix de l'action",tab);
			if(action!=null){
				if(action.compareTo("Sortir un Personnage")==0){
					this.sortieBatiment(ileDuJeu, affichage.getPlateau(), tableauAffichage, affichage, equipe);
				}else{
					if(upgrade==false){
						this.evolution(affichage, equipe, ileDuJeu);
					}else{
						affichage.popUp(equipe, "Votre Fort est déja en évolution, veuillez patienter encore un peu !", "Evolution en cours");
					}
				}
			}
		}
		plateauDuJeu.refreshinfo(null,this);
	}


	/**
	 * Attaque les perso ennemi autour du fort
	 * @param tableauIle
	 * @param affichage
	 * @param equipe
	 */
	public void attaque(Case[][] tableauIle, Affichage affichage, int equipe){
		if(niveau>1){
			Random deg=new Random();
			int dommages=deg.nextInt(10)+dmg;
			for(int i=x-1;i<x+2;i++){
				for(int j=y-1;j<y+2;j++){
					if(tableauIle[i][j].getPersonnageCourant()!=null && tableauIle[i][j].getPersonnageCourant().getDeath()==false && tableauIle[i][j].getPersonnageCourant().getJoueur()!=joueur){
						tableauIle[i][j].getPersonnageCourant().perteEnergie(dommages, i,j, tableauIle, true, false,affichage, equipe);
					}
				}
			}
		}
	}
}
