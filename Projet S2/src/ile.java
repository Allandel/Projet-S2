/**
 * Classe permettant de g�n�rer le plateau de jeu
 * @author Allan
 * @version 1.0
 */
import java.util.Random;
public class ile {

	private Case[][] plateau;
	private Case tmp, CaseCoffre, CaseCle;
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
	 * Cr�e un nouveau plateau de Case, puis le rempli de Navire et de Rochers.
	 */
	void initialiser(){
		int cpt =  0 ;
		plateau = new Case[taille][taille];
		for(int i = 0;i<plateau.length;i++){
			for(int j = 0;j<plateau.length;j++){
				plateau[i][j]= new CaseVierge();
			}
		}
		for(int i = 0; i<plateau.length;i++){
			plateau[0][i].setId(5);
			plateau[plateau.length-1][i].setId(5);
			plateau[i][0].setId(5);
			plateau[i][plateau.length-1].setId(5);
		}
		NavJ1= random.nextInt(plateau.length-2)+1;
		NavJ2= random.nextInt(plateau.length-2)+1;
		plateau[NavJ1][1]= new CaseNavire(2);
		plateau[NavJ2][plateau.length-2]= new CaseNavire(3);
		nbRocher = (int)((taille)*(taille-2)*proportion)/100;
		while(cpt<nbRocher){
			Rocherx = random.nextInt(plateau.length-2)+1;
			Rochery = random.nextInt(plateau.length-2)+1;
			if(plateau[Rocherx][Rochery].getId() == 0){
				plateau[Rocherx][Rochery]= new CaseRocher(Rocherx,Rochery);
				cpt++;
			}

		}
		cpt=0;
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
	/**
	 * D�fini  si le coffre et la cl� sont accessibles depuis les coordonn�es donn�es
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
