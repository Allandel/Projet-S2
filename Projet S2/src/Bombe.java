import java.util.Random;

public class Bombe {
	private int x,y,compteur=2;
	
	public Bombe(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public boolean downCompteur(Case[][] tableauIle, Affichage affichage, int equipe){
		compteur--;
		if(compteur==0){
			this.explosion(tableauIle, affichage, equipe);
			tableauIle[this.x][this.y].setBombe(null);
			return true;
		}
		return false;
	 }
	
	public int getCompteur(){
		return compteur;
	}
	
	public void explosion(Case[][] tableauIle, Affichage affichage, int equipe){
		Random deg=new Random();
		int dommages=deg.nextInt(10)+20;
		for(int x=this.x-1;x<this.x+2;x++){
			for(int y=this.y-1;y<this.y+2;y++){
				if(tableauIle[x][y].getPersonnageCourant()!=null && tableauIle[x][y].getPersonnageCourant().getDeath()==false){
					tableauIle[x][y].getPersonnageCourant().perteEnergie(dommages, x,y, tableauIle, false, false,affichage, equipe);
				}
			}
		}
	}
	
	public void explosionObjet(Personnage p, Case[][] tableauIle, Affichage affichage, int equipe, Joueur [] joueur){
		if(tableauIle[this.x][this.y] instanceof CaseRocher){
			if(((CaseRocher)tableauIle[this.x][this.y]).getChest()){
				affichage.popUp(equipe,"Vous avez fait exploser le rocher sous lequel se trouvait le coffre !\nCelui ci sera désormais visible par tous !", "COFFRE DECOUVERT" );
				tableauIle[x][y].setId(4);
				joueur[0].coffreTrouve();
				joueur[1].coffreTrouve();
				
			}if(((CaseRocher)tableauIle[this.x][this.y]).getKey()){
				affichage.popUp(equipe,"Vous avez fait exploser le rocher sous lequel se trouvait la clé !\nCelle ci se trouve désormais dans votre inventaire", "CLE DECOUVERTE" );
				tableauIle[x][y]=new Case();
				tableauIle[x][y].setId(15);
				p.setObjetInventaire("Cle");
			}else{
				affichage.popUp(equipe,"Vous avez fait sauter le rocher, mais celui ci ne dissimulait rien...", "EXPLOSION ROCHER" );
				tableauIle[x][y]=new Case();
				tableauIle[x][y].setId(15);
			}
		}else if(((CaseNavire)tableauIle[this.x][this.y]).getId()!=joueur[equipe].getIdBateau()){
			Random ran=new Random();
			int deg=ran.nextInt(50)+10;
			affichage.popUp(equipe,"Vous avez fait exploser une bombe sur la coque du navire ennemi ! Vous avez infligé "+deg+" de dégats", "ATTAQUE DU NAVIRE");
			((CaseNavire)tableauIle[this.x][this.y]).dommageCoque(deg);
			if(((CaseNavire)tableauIle[this.x][this.y]).getCoqueHealth()<=0){
				String res;
				if(equipe==0){
					res="rouge";
				}else{
					res="bleu";
				}
				affichage.popUp(equipe,"Le bateau ennemi a coulé... L'équipe "+res+" ne pourra plus jamais rapporter le trésor à son bateau...", "LE  NAVIRE ENNEMI COULE");
				joueur[equipe].abandon(); 
			}
		}
	}
}	
