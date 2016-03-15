
public class Case {
	int id;
	public void setPersonnageCourant(Personnage p) {
	
	}
	
	int nbrVoisin(int [][]plateau,int x, int y){
		int nbr=0;
		
		if(plateau[x+1][y]!=0)
			nbr++;
		if(plateau[x-1][y]!=0)
			nbr++;
		if(plateau[x][y+1]!=0)
			nbr++;
		if(plateau[x][y-1]!=0)
			nbr++;
		
		return nbr;
	}
	
	boolean estAccessible(int [][]plateau,int x, int y){
		if(this.nbrVoisin(plateau, x, y)<4)
			return true;
		return false;
	}
}
