import java.util.Random;


public class Fort extends Batiment{
	int niveau=1;
	protected int stockRessources=40;
	
	/**
	 * Construit un fort en lui attribuant l'ID donnee
	 * @param id
	 */
	public Fort(int x, int y, Joueur joueur){
		super(x, y, joueur);
		this.setId(joueur.getIdFort());
		batimentHealth=100;
		this.type="Fort";
		joueur.incrNiveauVillage();
		}

	public String toString(){
		return "F";
	}
	
	public void evolution(Affichage affichage, int equipe){
		if(niveau==1 && stockRessources>=10 && this.getPersonnage("Ouvrier")){
			niveau=2;
			batimentHealth=200;
			joueur.incrNiveauVillage();
			stockRessources-=10;
			if(equipe==0){
				this.setId(22);
				joueur.setIdFort(22);
			}else{
				this.setId(23);
				joueur.setIdFort(23);
			}
			affichage.popUp(equipe, "Votre village à évolué en Forteresse ! Votre base est désormais plus résistante et soigne mieux !", "Evolution au niveau 2" );
		}else if(niveau==2 && stockRessources>=30 && this.getPersonnage("Ouvrier")){
			niveau=3;
			batimentHealth=300;
			joueur.incrNiveauVillage();
			stockRessources-=30;
			affichage.popUp(equipe, "Votre Forteresse à évoluer ! Elle est désormais plus résistante et inflige plus de dégats alentours !", "Evolution au niveau 3" );
		}else{
			if((niveau==1 && stockRessources<=10)||(niveau==2 && stockRessources<=30)){
				affichage.popUp(equipe, "Vous n'avez pas assez de ressources", "Impossible d'évoluer");
			}else if(!this.getPersonnage("Ouvrier")){
				affichage.popUp(equipe, "Il vous faut un ouvrier au sein de votre ville pour évoluer !", "Impossible d'évoluer");
			}
		}
	}
	
	public void actionFort(int x, int y, ile ileDuJeu, Plateau plateauDuJeu, int[][] tableauAffichage, Affichage affichage, int equipe){
		plateauDuJeu.refreshinfo(null,this);
		String [] tab={"Sortir un Personnage","Monter le Niveau de la Forteresse"};
		String action=null;
		action=(String)affichage.popUpYesNo(equipe,"\nQue voulez vous faire ?\n\n[Cliquez sur annuler si vous ne voulez rien faire]\n", "Choix de l'action",tab);
		if(action!=null){
			if(action.compareTo("Sortir un Personnage")==0){
				this.sortieBatiment(ileDuJeu, affichage.getPlateau(), tableauAffichage, affichage, equipe);
			}else{
				((Fort)this).evolution(affichage, equipe);
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
		Random deg=new Random();
		int dommages=deg.nextInt(10)+20;
		for(int i=x-1;i<x+2;i++){
			for(int j=y-1;j<y+2;j++){
				if(tableauIle[i][j].getPersonnageCourant()!=null && tableauIle[i][j].getPersonnageCourant().getDeath()==false && tableauIle[i][j].getPersonnageCourant().getJoueur()!=joueur){
					tableauIle[i][j].getPersonnageCourant().perteEnergie(dommages, i,j, tableauIle, true, false,affichage, equipe);
				}
			}
		}
	}
}
