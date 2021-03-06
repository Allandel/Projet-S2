import java.util.Random;

public class Bombe {
	private int x,y,compteur=2;

	/**
	 * Constructeur de la bombe, set les coordonnees de la bombe
	 * @param x
	 * @param y
	 */
	public Bombe(int x, int y){
		this.x=x;
		this.y=y;
	}

	/**
	 * Diminue le temps restant avant l'explosion de la bombe 
	 * @param tableauIle
	 * @param affichage
	 * @param equipe
	 * @return vrai si le compteur est à 0
	 */
	public boolean downCompteur(Case[][] tableauIle, Affichage affichage, int equipe){
		compteur--;
		if(compteur==0){
			this.explosion(tableauIle, affichage, equipe);
			tableauIle[this.x][this.y].setBombe(null);
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return le compteur de temps
	 */
	public int getCompteur(){
		return compteur;
	}

	/**
	 * explosion de la bombe, fait des dommages aux alentours
	 * @param tableauIle
	 * @param affichage
	 * @param equipe
	 */
	public void explosion(Case[][] tableauIle, Affichage affichage, int equipe){
		Random deg=new Random();
		int dommages=deg.nextInt(10)+20;
		for(int x=this.x-1;x<this.x+2;x++){
			for(int y=this.y-1;y<this.y+2;y++){
				if(tableauIle[x][y].getPersonnageCourant()!=null && tableauIle[x][y].getPersonnageCourant().getDeath()==false){
					tableauIle[x][y].getPersonnageCourant().perteEnergie(dommages, x,y, tableauIle, true, false,affichage, equipe);
				}
			}
		}
	}

	/**
	 * Explosion d'un batiment
	 * @param p
	 * @param tableauIle
	 * @param affichage
	 * @param equipe
	 * @param joueur
	 */
	public void explosionObjet(Personnage p, Case[][] tableauIle, Affichage affichage, int equipe, Joueur [] joueur){
		if(tableauIle[this.x][this.y] instanceof CaseRocher){
			if(((CaseRocher)tableauIle[this.x][this.y]).getChest()){
				affichage.popUp(equipe,"Vous avez fait exploser le rocher sous lequel se trouvait le coffre !\nCelui ci sera désormais visible par tous !", "COFFRE DECOUVERT" );
				tableauIle[x][y].setId(4);
				joueur[0].coffreTrouve();
				joueur[1].coffreTrouve();

			}else if(((CaseRocher)tableauIle[this.x][this.y]).getKey()){
				affichage.popUp(equipe,"Vous avez fait exploser le rocher sous lequel se trouvait la clé !\nCelle ci se trouve désormais dans votre inventaire", "CLE DECOUVERTE" );
				tableauIle[x][y]=new Case();
				tableauIle[x][y].setId(17);
				p.setObjetInventaire("Cle");
			}else{
				affichage.popUp(equipe,"Vous avez fait sauter le rocher, mais celui ci ne dissimulait rien...", "EXPLOSION ROCHER" );
				tableauIle[x][y]=new Case();
				tableauIle[x][y].setId(17);
			}
		}else if(tableauIle[this.x][this.y].getId()!=joueur[equipe].getIdBateau()){
			Random ran=new Random();
			int deg=ran.nextInt(50)+10;
			affichage.popUp(equipe,"Vous avez fait exploser une bombe sur ce batiment ennemi ! Vous avez infligé "+deg+" de dégats", "ATTAQUE DU BATIMENT");
			tableauIle[this.x][this.y].getBatimentCourant().dommageBatiment(deg);
			if(tableauIle[this.x][this.y].getBatimentCourant().getBatimentHealth()<=0){
				affichage.popUp(equipe,"Le batiment ennemi est détruit. L'équipe "+(equipe+1)+" ne pourra plus l'utiliser !", "LE  BATIMENT ENNEMI EST DETRUIT");
				tableauIle[this.x][this.y].getBatimentCourant().destructionBatiment(tableauIle);
				if(tableauIle[this.x][this.y].getBatimentCourant() instanceof Navire)
					joueur[equipe].abandon(); 
			}
		}
	}
}	
