/**
 * Classe principale pour lancer le jeu
 * @author Allan
 * @version 1.0
 */
public class main {

	public static void main(String[] args) {

		Launcher menu = new Launcher();		
		while(menu.getetat()){
			if(Launcher.getid() >= 0 && Launcher.getid() <5){ /* test 1 */
				menu.setVisible(false);
				Test test=new Test();
				test.testPerso(Launcher.getid());
				Launcher.id=5;
				menu.setVisible(true);
			}
			if(Launcher.getid()==55){
				menu.setVisible(false);
				GestionDuJeu gestion=new GestionDuJeu(menu.getParametres());
				boolean [] fin;
				do{
					fin=gestion.tourDuJoueur();
				}while(!fin[0]);
				
				Affichage victoire=new Affichage(fin);
				Launcher.id=5;
				menu.setVisible(true);
			}
			System.out.flush();
		}		 
		System.exit(0);

	}
}
