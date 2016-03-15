import java.util.Random;
public class ile {
	int cpt =  0 ;
	Case[][] plateau;
//	Case[][] TblRocher;
	Random random = new Random();
	int Rocherx, Rochery;
	int NavJ1, NavJ2;
	int randomK, randomC;
	float proportion;
	ile(int taille, int proportion){
		this.proportion = proportion;
//		TblRocher = new Case[10][10];
		plateau = new Case[taille][taille];
		randomK = random.nextInt(plateau.length);
		randomC = random.nextInt(plateau.length);
		initialiser();
		while(randomK == randomC){
			randomK = random.nextInt(plateau.length);
		}
	}
	private void initialiser() {
		for(int i = 0;i<plateau.length;i++){
			for(int j = 0;j<plateau[0].length;j++){
				plateau[i][j] = new CaseVierge();
				
			}

		}
		while(cpt<plateau.length*(proportion/10)+2){
			Rocherx = random.nextInt(plateau.length-2)+1;
			Rochery = random.nextInt(plateau.length-2)+1;
			
			if(plateau[Rocherx][Rochery].id == 0){
				if(cpt == randomK){
					plateau[Rocherx][Rochery]= new CaseRocher(Rocherx, Rochery);
					((CaseRocher) plateau[Rocherx][Rochery]).setKey(true);
				}else if(cpt ==randomC) {
					plateau[Rocherx][Rochery]= new CaseRocher(Rocherx,Rochery);
					((CaseRocher) plateau[Rocherx][Rochery]).setChest(true);
				}else{
					plateau[Rocherx][Rochery] = new CaseRocher(Rocherx, Rochery);
				}
				cpt++;
			}
		}
		NavJ1 = random.nextInt(plateau.length-2)+1;
		NavJ2 = random.nextInt(plateau.length-2)+1;
		plateau[NavJ1][0]= new CaseNavire(2);
		plateau[NavJ2][plateau.length-1]= new CaseNavire(3);
	}

	public String toString(){
		String res="";
		for(int i = 0;i<plateau.length;i++){
			res+="+---+---+---+---+---+---+---+---+---+---+"+"\n";
			for(int j = 0;j<plateau[0].length;j++){
					res+= "| "+plateau[i][j]+" ";
			}
			res+="|\n";
		}
		return res+"+---+---+---+---+---+---+---+---+---+---+";
	}
}
