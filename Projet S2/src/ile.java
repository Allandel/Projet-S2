/**
 * Classe permettant de generer le plateau de jeu
 * @author Allan
 * @version 1.0
 */
import java.util.Random;
public class ile {

	private Case[][] tableauIle;
	private Case CaseCoffre, CaseCle;
	private Random random = new Random();
	private int taille, nbRocher, Rocherx, Rochery, NavJ1, NavJ2;
	private float proportion;
	private boolean accesNav1, accesNav2;
	/**
	 * Construit une ile puis la rempli en fonction de la proportion de Rochers choisie
	 * @param taille
	 * @param proportion
	 */
	ile(int taille, int proportion, Joueur [] joueur){
		this.proportion = proportion;
		this.taille= taille;
		do{
			initialiser();
			estAccessible(NavJ1, 1);
			accesNav1 = accessible();
			resetAcces();
			estAccessible(NavJ2, tableauIle.length-2);
			accesNav2 = accessible();
		}while(!accesNav1 || !accesNav2 );
		this.setPersonnage(new Piegeur(true, joueur[0]),true);
		this.setPersonnage(new Voleur(true, joueur[0]),true);
		this.setPersonnage(new Guerrier(false, joueur[1]),false);
		this.setPersonnage(new Guerrier(false, joueur[1]),false);
	}
	
	/**
	 * Cree un nouveau plateau de Case, puis le rempli de Navire et de Rochers.
	 */
	void initialiser(){
		tableauIle = new Case[taille][taille];

		setZero();
		setMer();

		NavJ1= random.nextInt(tableauIle.length-2)+1;
		NavJ2= random.nextInt(tableauIle.length-2)+1;
		tableauIle[NavJ1][1]= new CaseNavire(2);
		tableauIle[NavJ2][tableauIle.length-2]= new CaseNavire(3);

		setRocher(taille,proportion);
		setKeyCoffre();	

	}


	/**
	 * Initialise la cl� et le coffre sur le plateau de jeu
	 */
	void setKeyCoffre(){
		int cpt=0;
		do{
			Rocherx = random.nextInt(tableauIle.length-2)+1;
			Rochery = random.nextInt(tableauIle.length-2)+1;
			if(tableauIle[Rocherx][Rochery].getId()==0){
				tableauIle[Rocherx][Rochery]= new CaseRocher(Rocherx,Rochery);
				if(cpt==0){
					((CaseRocher) tableauIle[Rocherx][Rochery]).setChest(true);
					CaseCoffre = tableauIle[Rocherx][Rochery];
				}
				if(cpt==1){
					((CaseRocher) tableauIle[Rocherx][Rochery]).setKey(true);
					CaseCle = tableauIle[Rocherx][Rochery];
				}
				cpt++;
			}
		}while(cpt<2);
	}
	/**
	 * Initialise les Case Neutres
	 */
	void setZero(){
		for(int i = 0;i<tableauIle.length;i++){
			for(int j = 0;j<tableauIle.length;j++){
				tableauIle[i][j]= new Case();
			}
		}
	}
	/**
	 * Initialise la mer
	 */
	void setMer(){
		for(int i = 0; i<tableauIle.length;i++){
			tableauIle[0][i].setId(5);
			tableauIle[tableauIle.length-1][i].setId(5);
			tableauIle[i][0].setId(5);
			tableauIle[i][tableauIle.length-1].setId(5);
		}
	}
	/**
	 * Initialise les Rochers
	 * @param taille
	 * @param proportion
	 */
	void setRocher(int taille, float proportion){
		int cpt=0;
		nbRocher = (int)((taille)*(taille-2)*proportion)/100;
		while(cpt<nbRocher){
			Rocherx = random.nextInt(tableauIle.length-2)+1;
			Rochery = random.nextInt(tableauIle.length-2)+1;
			if(tableauIle[Rocherx][Rochery].getId() == 0){
				tableauIle[Rocherx][Rochery]= new CaseRocher(Rocherx,Rochery);
				cpt++;
			}
		}

	}

	/**
	 * Initialise un Personnage aux cot�s du Bateau de son �quipe
	 * @param v
	 * @param equipe1
	 */
	void setPersonnage(Personnage v, boolean equipe1){
		int signe, xPlus, yPlus, cpt=0;	
		boolean vivant=false;

		do{
			yPlus=0;
			signe=random.nextInt(2);
			xPlus=random.nextInt(2);
			if(xPlus==0)
				yPlus=1;

			if(equipe1){
				if(signe==0){
					if(tableauIle[NavJ1-xPlus][1+yPlus].getId()==0){
						tableauIle[NavJ1-xPlus][1+yPlus].setPersonnageCourant(v);
						vivant=true;
					}
				}else{
					if(tableauIle[NavJ1+xPlus][1+yPlus].getId()==0){
						tableauIle[NavJ1+xPlus][1+yPlus].setPersonnageCourant(v);
						vivant=true;
					}
				}
			}else{
				if(signe==0){
					if(tableauIle[NavJ2-xPlus][taille-2-yPlus].getId()==0){
						tableauIle[NavJ2-xPlus][taille-2-yPlus].setPersonnageCourant(v);
						vivant=true;
					}
				}else{
					if(tableauIle[NavJ2+xPlus][taille-2-yPlus].getId()==0){
						tableauIle[NavJ2+xPlus][taille-2-yPlus].setPersonnageCourant(v);
						vivant=true;
					}
				}
			}
			cpt++;
		}while(!vivant && cpt<10);
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
	 * Defini  si le coffre et la cle sont accessibles depuis les coordonnees donnees
	 * @param x
	 * @param y
	 */
	void estAccessible(int x, int y){
		tableauIle[x][y].setAccessible(true);
		if(x>0){
			if(tableauIle[x-1][y].getId()==0 && !tableauIle[x-1][y].isAccessible()){
				estAccessible(x-1,y);
			}else{
				tableauIle[x-1][y].setAccessible(true);
			}
		}
		if(y<tableauIle.length-1){
			if(tableauIle[x][y+1].getId()==0 && !tableauIle[x][y+1].isAccessible()){
				estAccessible(x,y+1);
			}else{
				tableauIle[x][y+1].setAccessible(true);
			}
		}
		if(x<tableauIle.length-1){
			if(tableauIle[x+1][y].getId()==0 && !tableauIle[x+1][y].isAccessible()){
				estAccessible(x+1,y);
			}else{
				tableauIle[x+1][y].setAccessible(true);
			}
		}
		if(y>0){
			if(tableauIle[x][y-1].getId()==0 && !tableauIle[x][y-1].isAccessible()){
				estAccessible(x,y-1);
			}else{
				tableauIle[x][y-1].setAccessible(true);
			}
		}
	}
	/**
	 * Retourne un booleen en fonction de l'accessibilite du coffre et de la cle
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
	 * Reinitialise tous les booleens accessible a false
	 */
	void resetAcces(){
		for(int i=0;i<tableauIle.length-1;i++){
			for(int j =0;j<tableauIle.length-1;j++){
				tableauIle[i][j].setAccessible(false);
			}
		}
		CaseCoffre.setAccessible(false);
		CaseCle.setAccessible(false);
	}
	/**
	 * @return the tableauIle
	 */
	Case[][] getTableau(){
		return tableauIle;
	}

	public String toString(){
		String borne ="";
		for(int i =0;i<tableauIle.length;i++){
			borne+="+---";
		}
		borne+="+";
		String res="";
		for(int i = 0;i<tableauIle.length;i++){
			res+=borne+"\n";
			for(int j = 0;j<tableauIle[0].length;j++){
				res+= "| "+tableauIle[i][j].toString()+" ";
			}
			res+="|\n";
		}
		return res+borne;
	}
}
