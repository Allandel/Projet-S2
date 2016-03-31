/**
 * Classe permettant de g�n�rer le plateau de jeu
 * @author Allan
 * @version 1.0
 */
import java.util.Random;
public class ile {

	private Case[][] plateauIle;
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
			estAccessible(NavJ2, plateauIle.length-2);
			accesNav2 = accessible();
		}while(!accesNav1 || !accesNav2 );
		this.setPersonnage1(new Explorateur());
	}
	/**
	 * Cr�e un nouveau plateau de Case, puis le rempli de Navire et de Rochers.
	 */
	void initialiser(){
		plateauIle = new Case[taille][taille];

		setZero();
		setMer();

		NavJ1= random.nextInt(plateauIle.length-2)+1;
		NavJ2= random.nextInt(plateauIle.length-2)+1;
		plateauIle[NavJ1][1]= new CaseNavire(2);
		plateauIle[NavJ2][plateauIle.length-2]= new CaseNavire(3);

		setRocher(taille,proportion);
		setKeyCoffre();	
		
	}



	void setKeyCoffre(){
		int cpt=0;
		do{
			Rocherx = random.nextInt(plateauIle.length-2)+1;
			Rochery = random.nextInt(plateauIle.length-2)+1;
			if(plateauIle[Rocherx][Rochery].getId()==0){
				plateauIle[Rocherx][Rochery]= new CaseRocher(Rocherx,Rochery);
				if(cpt==0){
					((CaseRocher) plateauIle[Rocherx][Rochery]).setChest(true);
					CaseCoffre = plateauIle[Rocherx][Rochery];
				}
				if(cpt==1){
					((CaseRocher) plateauIle[Rocherx][Rochery]).setKey(true);
					CaseCle = plateauIle[Rocherx][Rochery];
				}
				cpt++;
			}
		}while(cpt<2);
	}

	void setZero(){
		for(int i = 0;i<plateauIle.length;i++){
			for(int j = 0;j<plateauIle.length;j++){
				plateauIle[i][j]= new Case();
			}
		}
	}

	void setMer(){
		for(int i = 0; i<plateauIle.length;i++){
			plateauIle[0][i].setId(5);
			plateauIle[plateauIle.length-1][i].setId(5);
			plateauIle[i][0].setId(5);
			plateauIle[i][plateauIle.length-1].setId(5);
		}
	}

	void setRocher(int taille, float proportion){
		int cpt=0;
		nbRocher = (int)((taille)*(taille-2)*proportion)/100;
		while(cpt<nbRocher){
			Rocherx = random.nextInt(plateauIle.length-2)+1;
			Rochery = random.nextInt(plateauIle.length-2)+1;
			if(plateauIle[Rocherx][Rochery].getId() == 0){
				plateauIle[Rocherx][Rochery]= new CaseRocher(Rocherx,Rochery);
				cpt++;
			}
		}

	}

	void setPersonnage1(Personnage v){
		int signe, xPlus, yPlus=0;	
		boolean vivant=false;

		do{
			signe=random.nextInt(2);
			xPlus=random.nextInt(2);
			if(xPlus==0)
				yPlus=1;
			
			if(signe==0){
				if(plateauIle[NavJ1-xPlus][1+yPlus].getId()==0){
					plateauIle[NavJ1-xPlus][1+yPlus].setPersonnageCourant(v);
					vivant=true;
				}
			}else{
				if(plateauIle[NavJ1+xPlus][1+yPlus].getId()==0){
					plateauIle[NavJ1+xPlus][1+yPlus].setPersonnageCourant(v);
					vivant=true;
				}
			}
		}while(!vivant);
	}

	public void mouvement(int xAvant, int yAvant, int xApres, int yApres, Personnage p){//Mouvement provisoire (Peut �tre � d�placer dans Personnage.java si possible)
		plateauIle[xAvant][yAvant].removePersonnageCourant();
		plateauIle[xApres][yApres].setPersonnageCourant(p);
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
	 * D�fini  si le coffre et la cl� sont accessibles depuis les coordonn�es donn�es
	 * @param x
	 * @param y
	 */
	void estAccessible(int x, int y){
		plateauIle[x][y].setAccessible(true);
		if(x>0){
			if(plateauIle[x-1][y].getId()==0 && !plateauIle[x-1][y].isAccessible()){
				estAccessible(x-1,y);
			}else{
				plateauIle[x-1][y].setAccessible(true);
			}
		}
		if(y<plateauIle.length-1){
			if(plateauIle[x][y+1].getId()==0 && !plateauIle[x][y+1].isAccessible()){
				estAccessible(x,y+1);
			}else{
				plateauIle[x][y+1].setAccessible(true);
			}
		}
		if(x<plateauIle.length-1){
			if(plateauIle[x+1][y].getId()==0 && !plateauIle[x+1][y].isAccessible()){
				estAccessible(x+1,y);
			}else{
				plateauIle[x+1][y].setAccessible(true);
			}
		}
		if(y>0){
			if(plateauIle[x][y-1].getId()==0 && !plateauIle[x][y-1].isAccessible()){
				estAccessible(x,y-1);
			}else{
				plateauIle[x][y-1].setAccessible(true);
			}
		}
	}
	/**
	 * Retourne un bool�en en fonction de l'accessibilit� du coffre et de la cl�
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
	 * R�initialise tous les bool�ens accessible a false
	 */
	void resetAcces(){
		for(int i=0;i<plateauIle.length-1;i++){
			for(int j =0;j<plateauIle.length-1;j++){
				plateauIle[i][j].setAccessible(false);
			}
		}
		CaseCoffre.setAccessible(false);
		CaseCle.setAccessible(false);
	}

	Case[][] getPlateau(){
		return plateauIle;
	}

	public String toString(){
		String borne ="";
		for(int i =0;i<plateauIle.length;i++){
			borne+="+---";
		}
		borne+="+";
		String res="";
		for(int i = 0;i<plateauIle.length;i++){
			res+=borne+"\n";
			for(int j = 0;j<plateauIle[0].length;j++){
				res+= "| "+plateauIle[i][j]+" ";
			}
			res+="|\n";
		}
		return res+borne;
	}
}
