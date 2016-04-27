/**
 * Classe principale pour lancer le jeu
 * @author Allan
 * @version 1.0
 */
public class main {

	public static void main(String[] args) {

		Launcher menu = new Launcher();		
		while(menu.getetat()){
			if(Launcher.getidTest() > 0 && Launcher.getidTest() <5){
				menu.setVisible(false);
				Test test=new Test();
				test.testPerso(Launcher.getidTest());
				Launcher.idTest=0;
				menu.setVisible(true);
			}
			if(Launcher.getidTest()==55){
				menu.setVisible(false);
				GestionDuJeu gestion=new GestionDuJeu(menu.getParametres());
				boolean [] fin;
				do{
					fin=gestion.tourDuJoueur();
				}while(!fin[0]);
				
				Affichage victoire=new Affichage(fin);
				menu.setVisible(true);
			}
			System.out.flush();
		}		 
	}
}
