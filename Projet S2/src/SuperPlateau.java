public class SuperPlateau {
	Plateau plateau;

	SuperPlateau(String[] gifs,int taille){
		plateau = new Plateau(gifs,taille);
	}
	public void affichage(){
		plateau.affichage();
	}
	public void setJeu(int[][] jeu){
		plateau.setJeu(jeu);
	}
	public int[][] getJeu(){
		return this.plateau.getJeu();
	}
}
