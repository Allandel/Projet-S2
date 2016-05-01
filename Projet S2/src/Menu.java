import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Permet l'affichage du menu principal du jeu
 */
public class Menu extends JFrame{
	private int tailleCarte, pourcentageRocher,idTest=0;
	private int nbrExplorateurJ1, nbrVoleurJ1, nbrGuerrierJ1, nbrPiegeurJ1;
	private int nbrExplorateurJ2, nbrVoleurJ2, nbrGuerrierJ2, nbrPiegeurJ2;
	private boolean etat = true;
	private JTabbedPane menuOnglet;
	private JTextArea lol;

	public Menu(){}

	/**
	 * Affiche le menu principal du jeu
	 * Permet de recuperer la taille et le pourcentage de rocher
	 */
	public void menuPrincipal(){
		int nbrPointJ1=10,nbrPointJ2=10;

		JPanel onglet1 =  new ImagePanel(new ImageIcon("img/carte.jpg").getImage());
		JPanel onglet2 = new JPanel();
		JPanel onglet3 = new JPanel();
		JPanel onglet4 = new JPanel();
		JPanel onglet5 = new JPanel();
		menuOnglet = new JTabbedPane();
		GridLayout g = new GridLayout(3,3);
		GridLayout g2 = new GridLayout(4,3);
		onglet2.setLayout(g);
		onglet3.setLayout(g2);
		onglet4.setLayout(g2);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(500,250));
		this.setTitle("Treasure Hunt");
		this.setUndecorated(true);
		this.pack();
		this.setLocationRelativeTo(null);

		JButton BJouer = new JButton("Jouer");
		JButton Bquitter = new JButton("Quitter");
		JButton Bregles = new JButton("Regles");
		JButton BTVoleur = new JButton("Voleur");
		JButton BTGuerrier = new JButton("Guerrier");
		JButton BTPiegeur = new JButton("Piegeur");
		JButton BTExplorateur = new JButton("Explorateur");
		JLabel LBTaille = new JLabel("Taille du plateau:");
		JLabel LBPourcent = new JLabel("Pourcentage de rochers:");
		JLabel LBExplorateur = new JLabel("Nombre d'Explorateur :");
		JLabel LBGuerrier = new JLabel("Nombre de Guerrier :");
		JLabel LBVoleur = new JLabel("Nombre de Voleur :");
		JLabel LBPiegeur = new JLabel("Nombre de Piegeur :");
		JLabel LB2Explorateur = new JLabel("Nombre d'Explorateur :");
		JLabel LB2Guerrier = new JLabel("Nombre de Guerrier :");
		JLabel LB2Voleur = new JLabel("Nombre de Voleur :");
		JLabel LB2Piegeur = new JLabel("Nombre de Piegeur :");

		final JSlider sliderTaille = new JSlider(10,30);
		setSlider(sliderTaille,5,10);
		final JSlider sliderPourcent = new JSlider(10,50);
		setSlider(sliderPourcent, 5,10);

		final JSlider sliderNbrExplorateurJ1 = new JSlider(0,4);
		setSlider(sliderNbrExplorateurJ1, 1,0);
		final JSlider sliderNbrGuerrierJ1 = new JSlider(0,4);
		setSlider(sliderNbrGuerrierJ1,1,0);
		final JSlider sliderNbrVoleurJ1 = new JSlider(0,4);
		setSlider(sliderNbrVoleurJ1, 1,0);
		final JSlider sliderNbrPiegeurJ1 = new JSlider(0,4);
		setSlider(sliderNbrPiegeurJ1, 1,0);

		final JSlider sliderNbrExplorateurJ2 = new JSlider(0,4);
		setSlider(sliderNbrExplorateurJ2, 1,0);
		final JSlider sliderNbrGuerrierJ2 = new JSlider(0,4);
		setSlider(sliderNbrGuerrierJ2, 1,0);
		final JSlider sliderNbrVoleurJ2 = new JSlider(0,4);
		setSlider(sliderNbrVoleurJ2, 1,0);
		final JSlider sliderNbrPiegeurJ2 = new JSlider(0,4);
		setSlider(sliderNbrPiegeurJ2,1,0);

		onglet3.add(LB2Explorateur);
		onglet3.add(sliderNbrExplorateurJ1);
		onglet3.add(LB2Guerrier);
		onglet3.add(sliderNbrGuerrierJ1);
		onglet3.add(LB2Voleur);
		onglet3.add(sliderNbrVoleurJ1);
		onglet3.add(LB2Piegeur);
		onglet3.add(sliderNbrPiegeurJ1);
		onglet4.add(LBExplorateur);
		onglet4.add(sliderNbrExplorateurJ2);
		onglet4.add(LBGuerrier);
		onglet4.add(sliderNbrGuerrierJ2);
		onglet4.add(LBVoleur);
		onglet4.add(sliderNbrVoleurJ2);
		onglet4.add(LBPiegeur);
		onglet4.add(sliderNbrPiegeurJ2);
		onglet2.add(LBTaille);
		onglet2.add(sliderTaille);
		onglet2.add(LBPourcent);
		onglet2.add(sliderPourcent);
		onglet1.add(BJouer);
		onglet1.add(Bregles);
		onglet1.add(Bquitter);
		onglet5.add(BTGuerrier);
		onglet5.add(BTPiegeur);
		onglet5.add(BTVoleur);
		onglet5.add(BTExplorateur);
		menuOnglet.add("Menu", onglet1);
		menuOnglet.add("Parametres", onglet2);
		menuOnglet.add("Parametres Joueur 1", onglet3);
		menuOnglet.add("Parametres Joueur 2", onglet4);
		menuOnglet.add("Tests", onglet5);
		this.getContentPane().add(menuOnglet);
		this.pack();
		this.setVisible(true);		

		sliderNbrExplorateurJ1.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				sliderNbrExplorateurJ1.getValue();
				//				Enregistrer la valeur initiale, la metrre à jour à chaque changement
				//				Comparer si changement plus grand ou petit et mettre à jour la taille 
				//				maxi des autres en fonction
			}
		});

		BJouer.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent arg0){
				tailleCarte = sliderTaille.getValue();
				pourcentageRocher = sliderPourcent.getValue();
				nbrExplorateurJ1=sliderNbrExplorateurJ1.getValue();
				nbrGuerrierJ1=sliderNbrGuerrierJ1.getValue();
				nbrVoleurJ1=sliderNbrVoleurJ1.getValue();
				nbrPiegeurJ1=sliderNbrPiegeurJ1.getValue();
				nbrExplorateurJ2=sliderNbrExplorateurJ2.getValue();
				nbrGuerrierJ2=sliderNbrGuerrierJ2.getValue();
				nbrVoleurJ2=sliderNbrVoleurJ2.getValue();
				nbrPiegeurJ2=sliderNbrPiegeurJ2.getValue();
				idTest=55;
			}
		});
		Bquitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		BTGuerrier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idTest = 1;
			}
		});
		BTPiegeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idTest = 2;
			}
		});
		BTVoleur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idTest = 3;
			}
		});
		BTExplorateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idTest = 4;
			}
		});

		Bregles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idTest=44;
			}	
		});
	}

	/**
	 * Initialise les slider du menu
	 * @param slider
	 * @param min
	 * @param max
	 * @param value
	 * @param spacing
	 */
	private void setSlider(JSlider slider, int spacing, int value){
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setValue(value);
		slider.setMajorTickSpacing(spacing);
		slider.setMinorTickSpacing(spacing);
		slider.setSnapToTicks(true);
	}

	/**
	 * 
	 * @return l'etat
	 */
	public boolean getetat(){
		return etat;
	}

	/**
	 * 
	 * @return l'id du test choisi
	 */
	public int getidTest(){
		return idTest;
	}

	/**
	 * 
	 * @param setter
	 */
	public void setIdtest(int setter){
		idTest=setter;
	}

	class ImagePanel extends JPanel {
		private Image img;

		public ImagePanel(String img) {
			this(new ImageIcon(img).getImage());
		}

		public ImagePanel(Image img) {
			this.img = img;
			Dimension size = new Dimension(300,200);
			setPreferredSize(size);
			setMinimumSize(size);
			setMaximumSize(size);
			setSize(size);
		}
		public void paintComponent(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	/**
	 * 
	 * @return le talbeau de parametre du jeu lance
	 */
	public int[] getParametres(){
		int [] parametres={tailleCarte, pourcentageRocher,nbrExplorateurJ1, nbrVoleurJ1, nbrGuerrierJ1, nbrPiegeurJ1, nbrExplorateurJ2, nbrVoleurJ2, nbrGuerrierJ2, nbrPiegeurJ2};
		return parametres;
	}

	/**
	 * Affichage des regles du jeu
	 */
	public void menuRegle(){
		JFrame menuRegle=new JFrame();
		JMenuBar menuBar = new JMenuBar();
		JMenu but = new JMenu("But du jeu");
		JMenu perso = new JMenu("Personnages");
		JMenu envir = new JMenu("Environnement");
		JMenu comm = new JMenu("Commandes");
		lol=new JTextArea(
				"                                             BIENVENUE DANS LE MANUEL DE LA CHASSE AU TRESOR\n\n\n"
						+ "Vous trouverez dans ce manuel toutes les explications necessaires afin de jouer convenablement une \npartie de Chasse au Trésor. "
						+ "Afin de vous déplacer dans le manuel, utilisez la barre de Menu du Haut qui vous \npermettra de trouver les information necessaires en quelques instants.\n\n"
						+ "Bonne Navigation !"
						,5,5);
		//=================================================================
		JMenuItem itemperso1 = new JMenuItem("Explorateurs");
		JMenuItem itemperso2 = new JMenuItem("Guerriers");
		JMenuItem itemperso3 = new JMenuItem("Piegeurs");
		JMenuItem itemperso4 = new JMenuItem("Voleurs");
		JMenuItem itemperso5 = new JMenuItem("Ouvriers");
		//=================================================================
		JMenuItem itemenvir1 = new JMenuItem("Herbe");
		JMenuItem itemenvir2 = new JMenuItem("Rochers");
		JMenuItem itemenvir3 = new JMenuItem("Océan");
		JMenuItem itemenvir4 = new JMenuItem("Navire");
		JMenuItem itemenvir5 = new JMenuItem("Coffre");
		JMenuItem itemenvir6 = new JMenuItem("Forteresse");
		//==================================================================
		JMenuItem itembut1 = new JMenuItem("Les Objetifs");
		//==================================================================
		JMenuItem itemcomm1 = new JMenuItem("Les Commandes Basiques");
		JMenuItem itemcomm2 = new JMenuItem("Les Commandes Avancées");

		menuRegle.setSize(700, 400);
		menuRegle.setLocationRelativeTo(null);
		lol.setEditable(false);
		//initialisation des menus      
		perso.add(itemperso1);
		perso.add(itemperso2);
		perso.add(itemperso3);
		perso.add(itemperso4);
		perso.add(itemperso5);
		envir.add(itemenvir1);  
		envir.add(itemenvir2);
		envir.add(itemenvir3);
		envir.add(itemenvir4);
		envir.add(itemenvir5);
		envir.add(itemenvir6);
		but.add(itembut1);
		comm.add(itemcomm1);
		comm.add(itemcomm2);
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
						+"Les Guerriers sont capables de se déplacer en diagonale et aussi d'interagir en Diagonale.");
			}        
		});
		itemperso3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				lol.setText("                                                                         LES PIEGEURS\n\n\n"
						+"CAPACITES\n\n"
						+"Les piegeurs disposent de plusieurs capacités très utiles au groupe :\n\n"
						+"- Ils sont capables de poser des pièges à l'aide d'une pelle. Ils seront visibles par votre équipe uniquement qui\n immobiliseront les personnages adverses durant 3 tours (Clic gauche sur lui même)\n\n"
						+"- Ils peuvent poser des bombes au sol qui, au bout d'un compte a rebours, explose dans un rayon d'une case, imposant\n de lourd dégâts a quiconque se trouverait au alentours.(Clic Gauche sur lui même)\n\n"
						+"- Ils peuvent aussi poser des bombes dans des rochers afin de les détruire ou bien dans le Navire ennemi\n afin de le couler ! (Clic gauche sur le rocher ou navire)"
						+"\n\nDEPLACEMENTS\n\n"
						+"Les Piegeurs sont capables de se déplacer en diagonale et aussi d'interagir en Diagonale.");
			}        
		});
		itemperso4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				lol.setText("                                                                         LES VOLEURS\n\n\n"
						+"CAPACITES\n\n"
						+"Les voleurs sont capables de dérober des objets aux personnages adverses dans un rayon de 1 case.\nCette fonctionnalité peut permettre a votre équipe de priver temporairement un joueur adverse des\nfonctionnalité d'un personnage clé ! En effet, privez le Guerrier de son épée, et il sera bien moins dangereux !\nPrivez Le piégeur de sa pelle ou de ses bombes, et il ne sera plus capable de vous poser\ndes bâtons dans les roues ! \n"
						+"\n\nDEPLACEMENTS\n\n"
						+"Les Voleurs sont capables de se déplacer en diagonale et aussi d'interagir en Diagonale.");
			}        
		});
		itemperso5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				lol.setText("                                                                         LES OUVRIERS\n\n\n"
						+"CAPACITES\n\n"
						+"Les ouvriers sont des personnages spéciaux disposant de capacités de support très importantes par\nrapport au autres personnages. Ce personnage est le seul capable de :\n\n"
						+"- Récolter des ressources avec du Minage. Ces ressources pourraient s'avérer cruciales pour permettre\naux autre personnages de devenir plus puissants !\n\n"
						+"- Créer un Village, puis une Forteresse à l'aide des ressources nécessaires à l'amélioration.\n\n"
						+"Ainsi, en utilisant l'ouvrier prudemment afin de le garder en vie, vous serez capables de monter\nvotre propre cité et tout les avantages qui en découlent !"
						+"\n\nDEPLACEMENTS\n\n"
						+"Les Ouvriers sont capables de se déplacer en diagonale et aussi d'interagir en Diagonale.");
			}        
		});
		itemenvir1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				lol.setText("                                                                         L'HERBE\n\n\n"
						+"DESCRIPTION\n\n"
						+"L'herbe est le terrain par défaut de l’île."
						+"\n\nINTERACTIONS POSSIBLES\n\n"
						+"- Mouvement (Tout les personnages)\n- Minage (Ouvrier)");
			}        
		});
		itemenvir2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				lol.setText("                                                                         LES ROCHERS\n\n\n"
						+"DESCRIPTION\n\n"
						+"Les Rochers constituent la cachette de la clé du trésor et du coffre au trésor, ou de rien du tout."
						+"\n\nINTERACTIONS POSSIBLES\n\n"
						+"- Regarder sous le rocher (Explorateur)\n- Explosion du Rocher (Piégeur)");
			}        
		});
		itemenvir3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				lol.setText("                                                                         L'OCEAN\n\n\n"
						+"DESCRIPTION\n\n"
						+"L'océan entoure l'île au trésor ! Les personnages ne peuvent que la contempler…"
						+"\n\nINTERACTIONS POSSIBLES\n\n"
						+"- Aucune (X)");
			}        
		});
		itemenvir4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				lol.setText("                                                                         NAVIRE\n\n\n"
						+"DESCRIPTION\n\n"
						+"Le Navire est le refuge des Personnages ! Il vous permet de régénérer votre énergie et de récupérer\nles outils que vous vous êtes fait dérober !"
						+"\n\nINTERACTIONS POSSIBLES\n\n"
						+"- Montée a bord (Tout les Personnages Alliés)\n- Sortir un personnage (Clic Gauche souris)\n- Exploser la Coque ennemie (Piégeur)");
			}        
		});
		itemenvir5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				lol.setText("                                                                         COFFRE\n\n\n"
						+"DESCRIPTION\n\n"
						+"Le coffre renferme le trésor ! Pour l'ouvrir, il vous faudra la clé !"
						+"\n\nINTERACTIONS POSSIBLES\n\n"
						+"- Ouvrir le Coffre (Explorateur – Avec la clé)");
			}        
		});
		itemenvir6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				lol.setText("                                                                         FORTERESSE\n\n\n"
						+"DESCRIPTION\n\n"
						+"La Forteresse constitue un refuge pour vos alliés ! En plus de régénérer la vie de vos personnages\nplus rapidement, elle leur permet d'autres actions très intéressantes :"
						+"\n\nINTERACTIONS POSSIBLES\n\n"
						+"- Revente d'Item au Marché de contrebande\n- Achat d'Item au Marché de contrebande\n- Stockage d'Item dans un Entrepot");
			}        
		});
		itemcomm1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				lol.setText("                                                                    COMMANDES BASIQUES\n\n\n"
						+"Les interactions basiques avec le jeu se font à l'aide de la souris.\n\n"
						+"SORTIR UN PERSONNAGE DU NAVIRE : Clic gauche sur le Navire allié puis Clic gauche sur l'une des\n                                                         cases bleutée.\n\nMOUVEMENT D'UN PERSONNAGE : Clic gauche sur un personnage puis Clic gauche sur l'une des cases de type\n                                                    \"Herbe\" bleutée\n\nACTION D'UN PERSONNAGE : Clic gauche sur un personnage puis clic gauche sur l'une des cases bleuté autre\n                                              que une case de type herbe\n\nPASSER SON TOUR : Clic gauche sur le bouton d'interaction «Passer son tour»en bas au droite du menu\n                               d'information.\n\nDESELECTIONNER UN PERSONNAGE : Clic gauche sur le bouton d'interaction «Retour»en bas au droite du menu\n                                                           d'information.\n\nABANDONNER UNE PARTIE : Clic gauche sur le bouton d'interaction «Abandonner»en bas au droite du menu\n                                           d'information.");
			}        
		});
		itemcomm2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				lol.setText("                                                                    COMMANDES AVANCEES\n\n\n"
						+"Les interaction avancées ajoutent le clavier à la souris :\n\n"
						+"");
			}        
		});
		menuRegle.add(menuBar);
		menuBar.add(but);
		menuBar.add(perso);
		menuBar.add(envir);
		menuBar.add(comm);
		menuRegle.setTitle("Manuel du Jeu");
		menuRegle.setJMenuBar(menuBar);
		menuRegle.setVisible(true);
		menuRegle.add(lol);
	}

	/**
	 * 
	 * @return true si chaque equipe a au moins un personnage
	 */
	public boolean parametresValide(){
		int nbrJ1=0,nbrJ2=0;

		for(int x=2;x<6;x++){
			nbrJ1+=this.getParametres()[x];
		}

		for(int x=6;x<10;x++){
			nbrJ2+=this.getParametres()[x];
		}

		if(nbrJ1>0 && nbrJ2>0)
			return true;
		return false;
	}
}
