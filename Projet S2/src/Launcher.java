import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;

/**
 * Permet l'affichage du menu principal du jeu
 */
public class Launcher extends JFrame{
	private int tailleCarte, pourcentageRocher;
	private int nbrExplorateurJ1, nbrVoleurJ1, nbrGuerrierJ1, nbrPiegeurJ1;
	private int nbrExplorateurJ2, nbrVoleurJ2, nbrGuerrierJ2, nbrPiegeurJ2;
	private boolean etat = true;
	static int id=5;
	private JTabbedPane menuOnglet;
	/**
	 * Affiche le menu principal du jeu
	 * Permet de recuperer la taille et le pourcentage de rocher
	 */
	public Launcher(){
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

		final JSlider sliderTaille = new JSlider();
		setSlider(sliderTaille, 10 ,30, 10, 5);
		final JSlider sliderPourcent = new JSlider();
		setSlider(sliderPourcent, 10 ,50, 10, 5);

		final JSlider sliderNbrExplorateurJ1 = new JSlider();
		setSlider(sliderNbrExplorateurJ1, 1 ,4, 1, 1);
		final JSlider sliderNbrGuerrierJ1 = new JSlider();
		setSlider(sliderNbrGuerrierJ1, 1 ,4, 1, 1);
		final JSlider sliderNbrVoleurJ1 = new JSlider();
		setSlider(sliderNbrVoleurJ1, 1 ,4, 1, 1);
		final JSlider sliderNbrPiegeurJ1 = new JSlider();
		setSlider(sliderNbrPiegeurJ1, 1 ,4, 1, 1);

		final JSlider sliderNbrExplorateurJ2 = new JSlider();
		setSlider(sliderNbrExplorateurJ2, 1 ,4, 1, 1);
		final JSlider sliderNbrGuerrierJ2 = new JSlider();
		setSlider(sliderNbrGuerrierJ2, 1 ,4, 1, 1);
		final JSlider sliderNbrVoleurJ2 = new JSlider();
		setSlider(sliderNbrVoleurJ2, 1 ,4, 1, 1);
		final JSlider sliderNbrPiegeurJ2 = new JSlider();
		setSlider(sliderNbrPiegeurJ2, 1 ,4, 1, 1);

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
				id=55;
			}
		});
		Bquitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		BTGuerrier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id = 1;
			}
		});
		BTPiegeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id = 0;
			}
		});
		BTVoleur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id = 2;
			}
		});
		BTExplorateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id = 3;
			}
		});

		Bregles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame regles = new JFrame();
				regles.setTitle("Regles");
				regles.setLocationRelativeTo(null);
				Icon img = new ImageIcon("img/regles.jpg");
				JLabel img2 = new JLabel();

				img2.setIcon(img);
				regles.setSize(1000, 664);
				regles.add(img2);
				regles.setVisible(true);
			}	

		});
	}

	private void setSlider(JSlider slider, int min ,int max, int value, int spacing){
		slider.setMaximum(max);
		slider.setMinimum(min);
		slider.setValue(value);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
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
	public static int getid(){
		return id;
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

	public int[] getParametres(){
		int [] parametres={tailleCarte, pourcentageRocher,nbrExplorateurJ1, nbrVoleurJ1, nbrGuerrierJ1, nbrPiegeurJ1, nbrExplorateurJ2, nbrVoleurJ2, nbrGuerrierJ2, nbrPiegeurJ2};
		return parametres;
	}
}
