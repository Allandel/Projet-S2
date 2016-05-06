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
	private int taille, nbRocher, Rocherx, Rochery, colonneNavJ1=1, colonneNavJ2, ligneNavJ1, ligneNavJ2;
	private float proportion;
	private boolean accesNav1, accesNav2;
	/**
	 * Construit une ile puis la rempli en fonction de la proportion de Rochers choisie
	 * @param taille
	 * @param proportion
	 */
	ile(int taille, int proportion, Joueur[] joueur){
		this.proportion = proportion;
		this.taille= taille;
		colonneNavJ2=taille-2;
		do{
			initialiser(joueur);
			estAccessible(ligneNavJ1, 1);
			accesNav1 = accessible();
			resetAcces();
			estAccessible(ligneNavJ2, tableauIle.length-2);
			accesNav2 = accessible();
		}while(!accesNav1 || !accesNav2 );
	}

	/**
	 * Ile qui sert pour les tests
	 * @param test
	 */
	ile(boolean test, Joueur[] joueur){
		tableauIle = new Case[9][9];
		ligneNavJ1=5;
		colonneNavJ1=2;
		ligneNavJ2=5;
		colonneNavJ2=4;		

		for(int i = 0;i<9;i++){
			for(int j = 0;j<9;j++){
				tableauIle[i][j]= new Case();
			}
		}

		for(int i = 1;i<9;i++){
			tableauIle[1][i]= new CaseRocher();
		}

		for(int j=0;j<9;j+=8){
			for(int i = 0;i<9;i++){
				tableauIle[j][i].setId(5);
			}
		}

		for(int j=0;j<9;j+=8){
			for(int i = 1;i<9;i++){
				tableauIle[i][j].setId(5);
			}
		}

		tableauIle[7][1].setBatimentCourant(new Navire(2,5,2, joueur[0]));
		tableauIle[7][7].setBatimentCourant(new Navire(3,5,4, joueur[1]));
		((CaseRocher)tableauIle[1][3]).setChest(true);
		((CaseRocher)tableauIle[1][5]).setKey(true);
	}

	/**
	 * Cree un nouveau plateau de Case, puis le rempli de Navire et de Rochers.
	 */
	void initialiser(Joueur [] joueur){
		tableauIle = new Case[taille][taille];

		setZero();
		setMer();

		ligneNavJ1= random.nextInt(tableauIle.length-2)+1;
		ligneNavJ2= random.nextInt(tableauIle.length-2)+1;
		tableauIle[ligneNavJ1][1].setBatimentCourant(new Navire(2,ligneNavJ1,1, joueur[0]));
		tableauIle[ligneNavJ2][tableauIle.length-2].setBatimentCourant(new Navire(3,ligneNavJ2,tableauIle.length-2, joueur[0]));

		setRocher(taille,proportion);
		setKeyCoffre();	

	}


	/**
	 * Initialise la cle et le coffre sur le plateau de jeu
	 */
	void setKeyCoffre(){
		int cpt=0;
		do{
			Rocherx = random.nextInt(tableauIle.length-2)+1;
			Rochery = random.nextInt(tableauIle.length-2)+1;
			if(tableauIle[Rocherx][Rochery].getId()==17){
				tableauIle[Rocherx][Rochery]= new CaseRocher();
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
			if(tableauIle[Rocherx][Rochery].getId() == 17){
				tableauIle[Rocherx][Rochery]= new CaseRocher();
				cpt++;
			}
		}

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
	 * @return the ligneNavJ1
	 */
	public int getLigneNavJ1() {
		return ligneNavJ1;
	}
	/**
	 * @return the colonneNavJ1
	 */
	public int getColonneNavJ1() {
		return colonneNavJ1;
	}

	/**
	 * @return the ligneNavJ2
	 */
	public int getLigneNavJ2() {
		return ligneNavJ2;
	}
	/**
	 * @return the colonneNavJ2
	 */
	public int getColonneNavJ2() {
		return colonneNavJ2;
	}

	/**
	 * Defini  si le coffre et la cle sont accessibles depuis les coordonnees donnees
	 * @param x
	 * @param y
	 */
	void estAccessible(int x, int y){
		tableauIle[x][y].setAccessible(true);
		if(x>0){
			if(tableauIle[x-1][y].getId()==17 && !tableauIle[x-1][y].isAccessible()){
				estAccessible(x-1,y);
			}else{
				tableauIle[x-1][y].setAccessible(true);
			}
		}
		if(y<tableauIle.length-1){
			if(tableauIle[x][y+1].getId()==17 && !tableauIle[x][y+1].isAccessible()){
				estAccessible(x,y+1);
			}else{
				tableauIle[x][y+1].setAccessible(true);
			}
		}
		if(x<tableauIle.length-1){
			if(tableauIle[x+1][y].getId()==17 && !tableauIle[x+1][y].isAccessible()){
				estAccessible(x+1,y);
			}else{
				tableauIle[x+1][y].setAccessible(true);
			}
		}
		if(y>0){
			if(tableauIle[x][y-1].getId()==17 && !tableauIle[x][y-1].isAccessible()){
				estAccessible(x,y-1);
			}else{
				tableauIle[x][y-1].setAccessible(true);
			}
		}
	}
	/**
	 * 
	 * @returnun booleen en fonction de l'accessibilite du coffre et de la cle
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

