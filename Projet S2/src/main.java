/**
 * Classe principale pour lancer le jeu
 * @author Allan
 * @version 1.0
 */
public class main {

	public static void main(String[] args) {
//		Test test=new Test();
//		test.testPerso(null);
		
		Launcher menu = new Launcher();		
		while(Launcher.getetat()){
			if(Launcher.getid() >= 0 && Launcher.getid() <5){ /* test 1 */
				Test test=new Test();
				test.testPerso(Launcher.getid());
			}
			System.out.flush();
		}
		GestionDuJeu gestion=new GestionDuJeu();
		boolean [] fin;
		do{
			fin=gestion.tourDuJoueur();
		}while(!fin[0]);

		Affichage victoire=new Affichage(fin);
		 
		System.exit(0);

	}
}
