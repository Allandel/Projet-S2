import javax.swing.JOptionPane;

/**
 * Classe principale pour lancer le jeu
 * @author Allan
 * @version 1.0
 */
public class main {

	public static void main(String[] args) {

		Menu menu = new Menu();	
		menu.menuPrincipal();
		while(menu.getetat()){
			if(menu.getidTest() > 0 && menu.getidTest() <5){
				menu.setVisible(false);
				Test test=new Test();
				test.testPerso(menu.getidTest());
				menu.setIdtest(0);
				menu.setVisible(true);
			}else if(menu.getidTest()==55 && menu.parametresValide()){
				menu.setVisible(false);
				GestionDuJeu gestion=new GestionDuJeu(menu.getParametres());
				boolean [] fin;
				do{
					fin=gestion.tourDuJoueur();
				}while(!fin[0]);
				menu.setIdtest(0);
				Affichage victoire=new Affichage(fin);
				menu.setVisible(true);
			}else if(menu.getidTest()==55){
				menu.setIdtest(0);
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null, "Il faut au moins un personnage dans chaque équipe pour pouvoir joueur.", "Quantite de personnage invalide",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null, options, options[0]);
			}
				
			System.out.flush();
		}		 
	}
}
