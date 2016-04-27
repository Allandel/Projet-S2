import java.util.Random;

public class Bombe {
	private int x,y,compteur=2;
	
	public Bombe(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
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
		int dommages=deg.nextInt(20)+20;
		for(int x=this.x-1;x<this.x+2;x++){
			for(int y=this.y-1;y<this.y+2;y++){
				if(tableauIle[x][y].getPersonnageCourant()!=null){
					tableauIle[x][y].getPersonnageCourant().perteEnergie(dommages, x,y, tableauIle, false, false,affichage, equipe);
				}
			}
		}
	}
	
	/*public void explosionObjet(Case[][] tableauIle){
		if(tableauIle[this.x][this.y] instanceof CaseRocher){
			if((CaseRocher)tableauIle[this.x][this.y].)
		}
	}*/
}
