/**
 * Classe permettant de gï¿½nï¿½rer le plateau de jeu
 * @author Allan
 * @version 1.0
 */
import java.util.Random;
public class ile {

	private Case[][] plateau;
	private Case CaseCoffre, CaseCle;
	private Random random = new Random();
	private int taille, nbRocher;
	private int Rocherx, Rochery;
	private int NavJ1, NavJ2;
	private float proportion;
	private boolean accesNav1, accesNav2;
	/**
	 * Construit une ile puis la rempli en fonction de la proportion de Rochers choisie
	 * @param taille
	 * @param proportion
	 */
	ile(int taille, int proportion){
		this.proportion = proportion;
		this.taille= taille;
		do{
			initialiser();
			estAccessible(NavJ1, 1);
			accesNav1 = accessible();
			resetAcces();
			estAccessible(NavJ2, plateau.length-2);
			accesNav2 = accessible();
		}while(!accesNav1 || !accesNav2 );
	}
	/**
	 * Crï¿½e un nouveau plateau de Case, puis le rempli de Navire et de Rochers.
	 */
	void initialiser(){
		plateau = new Case[taille][taille];
		
		setZero();
		setMer();
		
		NavJ1= random.nextInt(plateau.length-2)+1;
		NavJ2= random.nextInt(plateau.length-2)+1;
		plateau[NavJ1][1]= new CaseNavire(2);
		plateau[NavJ2][plateau.length-2]= new CaseNavire(3);
		
		setRocher(taille,proportion);
		setKeyCoffre();	
	}
	
	
	
	void setKeyCoffre(){
		int cpt=0;
		do{
			Rocherx = random.nextInt(plateau.length-2)+1;
			Rochery = random.nextInt(plateau.length-2)+1;
			if(plateau[Rocherx][Rochery].getId()==0){
				plateau[Rocherx][Rochery]= new CaseRocher(Rocherx,Rochery);
				if(cpt==0){
					((CaseRocher) plateau[Rocherx][Rochery]).setChest(true);
					CaseCoffre = plateau[Rocherx][Rochery];
				}
				if(cpt==1){
					((CaseRocher) plateau[Rocherx][Rochery]).setKey(true);
					CaseCle = plateau[Rocherx][Rochery];
				}
				cpt++;
			}
		}while(cpt<2);
	}
	
	void setZero(){
		for(int i = 0;i<plateau.length;i++){
			for(int j = 0;j<plateau.length;j++){
				plateau[i][j]= new CaseVierge();
			}
		}
	}
	
	void setMer(){
		for(int i = 0; i<plateau.length;i++){
			plateau[0][i].setId(5);
			plateau[plateau.length-1][i].setId(5);
			plateau[i][0].setId(5);
			plateau[i][plateau.length-1].setId(5);
		}
	}
	
	void setRocher(int taille, float proportion){
		int cpt=0;
		nbRocher = (int)((taille)*(taille-2)*proportion)/100;
		while(cpt<nbRocher){
			Rocherx = random.nextInt(plateau.length-2)+1;
			Rochery = random.nextInt(plateau.length-2)+1;
			if(plateau[Rocherx][Rochery].getId() == 0){
				plateau[Rocherx][Rochery]= new CaseRocher(Rocherx,Rochery);
				cpt++;
			}
		}

	}
	
	void setPersonnage1(Personnage v){//A faire en récursif si approuvé
		if(plateau[NavJ1+1][1].getId()==0){
			plateau[NavJ1+1][1].setPersonnageCourant(v);
			v.setCoordonnées(NavJ1+1, 1);
		}else if(plateau[NavJ1+1][2].getId()==0){
			plateau[NavJ1+1][2].setPersonnageCourant(v);
			v.setCoordonnées(NavJ1+1, 2);
		}else if(plateau[NavJ1][2].getId()==0){
			plateau[NavJ1][2].setPersonnageCourant(v);
			v.setCoordonnées(NavJ1, 2);
		}else if (plateau[NavJ1-1][2].getId()==0){
			plateau[NavJ1-1][2].setPersonnageCourant(v);
			v.setCoordonnées(NavJ1-1, 2);
		}else if(plateau[NavJ1-1][1].getId()==0){
			plateau[NavJ1-1][1].setPersonnageCourant(v);
			v.setCoordonnées(NavJ1-1, 1);
		}
	}
	
	public boolean mouvement(int x, int y, Personnage p){//Mouvement provisoire (Peut être à déplacer dans Personnage.java si possible)
		if((x==p.getX()+1&& y==p.getY()&& plateau[x][y].getId()==0)||(x==p.getX()&& y==p.getY()+1&& plateau[x][y].getId()==0)||(x==p.getX()-1&& y==p.getY()&& plateau[x][y].getId()==0)||(x==p.getX()&& y==p.getY()-1&& plateau[x][y].getId()==0)){
			plateau[x][y].setPersonnageCourant(p);
			plateau[p.getX()][p.getY()].removePersonnageCourant();
			return true;
		}
		return false;
	}
	/**
	 * @return the caseCoffre
	 */
	public Case getCaseCoffre() {
		return CaseCoffre;
	}
	/**
	 * @return the caseCle
	 */
	public Case getCaseCle() {
		return CaseCle;
	}
	/**
	 * @return the navJ1
	 */
	public int getNavJ1() {
		return NavJ1;
	}
	/**
	 * @return the navJ2
	 */
	public int getNavJ2() {
		return NavJ2;
	}
	/**
	 * Dï¿½fini  si le coffre et la clï¿½ sont accessibles depuis les coordonnï¿½es donnï¿½es
	 * @param x
	 * @param y
	 */
	void estAccessible(int x, int y){
		plateau[x][y].setAccessible(true);
		if(x>0){
			if(plateau[x-1][y].getId()==0 && !plateau[x-1][y].isAccessible()){
				estAccessible(x-1,y);
			}else{
				plateau[x-1][y].setAccessible(true);
			}
		}
		if(y<plateau.length-1){
			if(plateau[x][y+1].getId()==0 && !plateau[x][y+1].isAccessible()){
				estAccessible(x,y+1);
			}else{
				plateau[x][y+1].setAccessible(true);
			}
		}
		if(x<plateau.length-1){
			if(plateau[x+1][y].getId()==0 && !plateau[x+1][y].isAccessible()){
				estAccessible(x+1,y);
			}else{
				plateau[x+1][y].setAccessible(true);
			}
		}
		if(y>0){
			if(plateau[x][y-1].getId()==0 && !plateau[x][y-1].isAccessible()){
				estAccessible(x,y-1);
			}else{
				plateau[x][y-1].setAccessible(true);
			}
		}
	}
	/**
	 * Retourne un boolï¿½en en fonction de l'accessibilitï¿½ du coffre et de la clï¿½
	 * @return
	 */
	boolean accessible(){
		if(CaseCoffre.isAccessible() && CaseCle.isAccessible()){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Rï¿½initialise tous les boolï¿½ens accessible a false
	 */
	void resetAcces(){
		for(int i=0;i<plateau.length-1;i++){
			for(int j =0;j<plateau.length-1;j++){
				plateau[i][j].setAccessible(false);
			}
		}
		CaseCoffre.setAccessible(false);
		CaseCle.setAccessible(false);
	}

	Case[][] getPlateau(){
		return plateau;
	}
	
	public String toString(){
		String borne ="";
		for(int i =0;i<plateau.length;i++){
			borne+="+---";
		}
		borne+="+";
		String res="";
		for(int i = 0;i<plateau.length;i++){
			res+=borne+"\n";
			for(int j = 0;j<plateau[0].length;j++){
				res+= "| "+plateau[i][j]+" ";
			}
			res+="|\n";
		}
		return res+borne;
	}
}
