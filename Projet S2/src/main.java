/**
 * Classe principale pour lancer le jeu
 * @author Allan
 * @version 1.0
 */
public class main {

	public static void main(String[] args) {
		Launcher menu = new Launcher();		
		while(Launcher.getetat()){
			System.out.flush();
		}
		GestionDuJeu gestion=new GestionDuJeu();
		boolean fin;
		do{
			fin=gestion.tourDuJoueur();
		}while(!fin);
		
		System.exit(0);
	}
}
