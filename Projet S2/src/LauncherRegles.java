import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.text.SimpleAttributeSet;

public class LauncherRegles extends JFrame {
	private JMenuBar menuBar = new JMenuBar();
	private JMenu but = new JMenu("But du jeu");
	private JMenu perso = new JMenu("Personnages");
	private JMenu envir = new JMenu("Environnement");
	private JMenu comm = new JMenu("Commandes");
	private JTextArea lol=new JTextArea(
			"                                             BIENVENUE DANS LE MANUEL DE LA CHASSE AU TRESOR\n\n\n"
			+ "Vous trouverez dans ce manuel toutes les explications necessaires afin de jouer convenablement une \npartie de Chasse au Trésor. "
			+ "Afin de vous déplacer dans le manuel, utilisez la barre de Menu du Haut qui vous \npermettra de trouver les information necessaires en quelques instants.\n\n"
			+ "Bonne Navigation !"
					,5,5);
	//=================================================================
	private JMenuItem itemperso1 = new JMenuItem("Explorateurs");
	private JMenuItem itemperso2 = new JMenuItem("Guerriers");
	private JMenuItem itemperso3 = new JMenuItem("Piegeurs");
	private JMenuItem itemperso4 = new JMenuItem("Guerriers");
	private JMenuItem itemperso5 = new JMenuItem("Ouvriers");
	//=================================================================
	private JMenuItem itemenvir1 = new JMenuItem("Herbe");
	private JMenuItem itemenvir2 = new JMenuItem("Rochers");
	private JMenuItem itemenvir3 = new JMenuItem("Océan");
	private JMenuItem itemenvir4 = new JMenuItem("Navire");
	private JMenuItem itemenvir5 = new JMenuItem("Coffre");
	private JMenuItem itemenvir6 = new JMenuItem("Forteresse");
	//==================================================================
	private JMenuItem itembut1 = new JMenuItem("Les Objetifs");
	//==================================================================
	private JMenuItem itemcomm1 = new JMenuItem("Les Commandes Basiques");
	private JMenuItem itemcomm2 = new JMenuItem("Les Commandes Avancées");

	public LauncherRegles(){
		this.setSize(700, 400);
		this.setLocationRelativeTo(null);
		lol.setEditable(false);
		//initialisation des menus      
		this.perso.add(itemperso1);
		this.perso.add(itemperso2);
		this.perso.add(itemperso3);
		this.perso.add(itemperso4);
		this.perso.add(itemperso5);
		this.envir.add(itemenvir1);  
		this.envir.add(itemenvir2);
		this.envir.add(itemenvir3);
		this.envir.add(itemenvir4);
		this.envir.add(itemenvir5);
		this.envir.add(itemenvir6);
		this.but.add(itembut1);
		this.comm.add(itemcomm1);
		this.comm.add(itemcomm2);
		itembut1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				lol.setText("                                                                         LES OBJECTIFS\n\n\n"
			+"Sur l'Ile au Trésor, différents choix s'offrent à vous pour obtenir la victoire finale ! Choisissez la meilleure \nstratégie parmis celles suivantes :\n\n"
			+"- Retrouver la clé et le coffre au trésor afin d'ouvrir le coffre et d'en découvrir son contenu ! Ammenez le trésor \n  sur votre Navire et la victoire sera à vous !\n"
			+"- Détruire l'équipe adverse et la réduire à néant. Soyez les derniers sur l'ile !\n\n"
			+"A vous de jouer ! Remportez la victoire à tout prix !");
			}        
		});
		itemperso1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				lol.setText("                                                                         L'EXPLORATEUR\n\n\n"
			+"CAPACITES\n\n"
			+"L'explorateur est un personnage clé du jeu. En effet, celui ci est l'unique personnage capable de soulever un\nrocher afin de regarder au dessous si une clé ou un trésor ne s'y trouve pas\n"
			+"Une fois la clé en sa possession, l'explorateur sera le seul personnage capable d'aller ouvrir le coffre et de\ns'emparer du trésor !\n\n"
			+"DEPLACEMENTS\n\n"
			+"L'Explorateur se déplace uniquement en croix, c'est a dire qu'il ne peut aller qu'au dessus, en dessous,\nà droite ou à gauche\n");
			}        
		});
		itemperso2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				lol.setText("                                                                         LES GUERRIERS\n\n\n"
			+"CAPACITES\n\n"
			+"Les Guerriers sont les seuls capables de frapper un autre personnage. Avec une épée, ou sans ils infligeront\nde nombreux dégats aux adversaires ! Mais prenez garde aux voleurs\n"
			+"car ils peuvent vous voler votre épée et vous priver de votre unique moyen de faire des dégats !\nSi vous perdez votre épée, vous pourrez en récupérer au Navire.\n\n"
			+"DEPLACEMENTS\n\n"
			+"Les Guerriers sont capables de se déplacer en diagonale et aussi d'attaquer en Diagonale.\nToutes les actions du Guerriers peuvent être faites en diagonale\n");
			}        
		});
		

		this.menuBar.add(but);
		this.menuBar.add(perso);
		this.menuBar.add(envir);
		this.menuBar.add(comm);
		this.setTitle("Manuel du Jeu");
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		this.add(lol);
	}
}