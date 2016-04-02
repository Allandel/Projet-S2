/**
 * Classe principale pour lancer le jeu
 * @author Allan
 * @version 1.0
 */
public class main {

	public static void main(String[] args) {
		GestionDuJeu gestion=new GestionDuJeu();

		while(true){
			gestion.tourDuJoueur();
		}
	
	}
}
