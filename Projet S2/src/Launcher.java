import java.awt.BorderLayout;
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
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 * Permet l'affichage du menu principal du jeu
 */
public class Launcher extends JFrame{
	static int tailleCarte, pourcentageRocher;
	static boolean etat = true;
	static int id;
	static int nbrExplorateurJ1, nbrVoleurJ1, nbrGuerrierJ1, nbrPiegeurJ1;
	static int nbrExplorateurJ2, nbrVoleurJ2, nbrGuerrierJ2, nbrPiegeurJ2;
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
		JTabbedPane menuOnglet = new JTabbedPane();
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
		JButton Bregles = new JButton("Règles");
		JButton BTMove = new JButton("Deplacement");
		JButton BTVoleur = new JButton("Voleur");
		JButton BTGuerrier = new JButton("Guerrier");
		JButton BTPiegeur = new JButton("Piegeur");
		JButton BTEnergie = new JButton("Energie");
		JLabel LBTaille = new JLabel("Taille du plateau:");
		JLabel LBPourcent = new JLabel("Pourcentage de rochers:"
				+ "");
		JLabel LBExplorateur = new JLabel("Nombre d'Explorateur :");
		JLabel LBGuerrier = new JLabel("Nombre de Guerrier :");
		JLabel LBVoleur = new JLabel("Nombre de Voleur :");
		JLabel LBPiegeur = new JLabel("Nombre de Piegeur :");
		JLabel LB2Explorateur = new JLabel("Nombre d'Explorateur :");
		JLabel LB2Guerrier = new JLabel("Nombre de Guerrier :");
		JLabel LB2Voleur = new JLabel("Nombre de Voleur :");
		JLabel LB2Piegeur = new JLabel("Nombre de Piegeur :");
		
		final JSlider slidertaille = new JSlider();
		slidertaille.setMaximum(30);
		slidertaille.setMinimum(10);
		slidertaille.setValue(10);
		slidertaille.setPaintTicks(true);
		slidertaille.setPaintLabels(true);
		slidertaille.setMajorTickSpacing(5);
		slidertaille.setMinorTickSpacing(5);
		slidertaille.setSnapToTicks(true);
		slidertaille.setPreferredSize(new Dimension(200,25));
		final JSlider sliderpourcent = new JSlider();
		sliderpourcent.setMaximum(50);
		sliderpourcent.setMinimum(10);
		sliderpourcent.setValue(10);
		sliderpourcent.setPaintTicks(true);
		sliderpourcent.setPaintLabels(true);
		sliderpourcent.setMinorTickSpacing(5);
		sliderpourcent.setMajorTickSpacing(5);
		sliderpourcent.setSnapToTicks(true);
		
		final JSlider sliderNbrExplorateurJ1 = new JSlider();
		sliderNbrExplorateurJ1.setMaximum(4);
		sliderNbrExplorateurJ1.setMinimum(1);
		sliderNbrExplorateurJ1.setValue(1);
		sliderNbrExplorateurJ1.setPaintTicks(true);
		sliderNbrExplorateurJ1.setPaintLabels(true);
		sliderNbrExplorateurJ1.setMajorTickSpacing(1);
		sliderNbrExplorateurJ1.setMinorTickSpacing(1);
		sliderNbrExplorateurJ1.setSnapToTicks(true);
		sliderNbrExplorateurJ1.setPreferredSize(new Dimension(200,25));
		final JSlider sliderNbrGuerrierJ1 = new JSlider();
		sliderNbrGuerrierJ1.setMaximum(4);
		sliderNbrGuerrierJ1.setMinimum(1);
		sliderNbrGuerrierJ1.setValue(1);
		sliderNbrGuerrierJ1.setPaintTicks(true);
		sliderNbrGuerrierJ1.setPaintLabels(true);
		sliderNbrGuerrierJ1.setMajorTickSpacing(1);
		sliderNbrGuerrierJ1.setMinorTickSpacing(1);
		sliderNbrGuerrierJ1.setSnapToTicks(true);
		sliderNbrGuerrierJ1.setPreferredSize(new Dimension(200,25));
		final JSlider sliderNbrVoleurJ1 = new JSlider();
		sliderNbrVoleurJ1.setMaximum(4);
		sliderNbrVoleurJ1.setMinimum(1);
		sliderNbrVoleurJ1.setValue(1);
		sliderNbrVoleurJ1.setPaintTicks(true);
		sliderNbrVoleurJ1.setPaintLabels(true);
		sliderNbrVoleurJ1.setMajorTickSpacing(1);
		sliderNbrVoleurJ1.setMinorTickSpacing(1);
		sliderNbrVoleurJ1.setSnapToTicks(true);
		sliderNbrVoleurJ1.setPreferredSize(new Dimension(200,25));
		final JSlider sliderNbrPiegeurJ1 = new JSlider();
		sliderNbrPiegeurJ1.setMaximum(4);
		sliderNbrPiegeurJ1.setMinimum(1);
		sliderNbrPiegeurJ1.setValue(1);
		sliderNbrPiegeurJ1.setPaintTicks(true);
		sliderNbrPiegeurJ1.setPaintLabels(true);
		sliderNbrPiegeurJ1.setMajorTickSpacing(1);
		sliderNbrPiegeurJ1.setMinorTickSpacing(1);
		sliderNbrPiegeurJ1.setSnapToTicks(true);
		sliderNbrPiegeurJ1.setPreferredSize(new Dimension(200,25));
		
		final JSlider sliderNbrExplorateurJ2 = new JSlider();
		sliderNbrExplorateurJ2.setMaximum(4);
		sliderNbrExplorateurJ2.setMinimum(1);
		sliderNbrExplorateurJ2.setValue(1);
		sliderNbrExplorateurJ2.setPaintTicks(true);
		sliderNbrExplorateurJ2.setPaintLabels(true);
		sliderNbrExplorateurJ2.setMajorTickSpacing(1);
		sliderNbrExplorateurJ2.setMinorTickSpacing(1);
		sliderNbrExplorateurJ2.setSnapToTicks(true);
		sliderNbrExplorateurJ2.setPreferredSize(new Dimension(200,25));
		final JSlider sliderNbrGuerrierJ2 = new JSlider();
		sliderNbrGuerrierJ2.setMaximum(4);
		sliderNbrGuerrierJ2.setMinimum(1);
		sliderNbrGuerrierJ2.setValue(1);
		sliderNbrGuerrierJ2.setPaintTicks(true);
		sliderNbrGuerrierJ2.setPaintLabels(true);
		sliderNbrGuerrierJ2.setMajorTickSpacing(1);
		sliderNbrGuerrierJ2.setMinorTickSpacing(1);
		sliderNbrGuerrierJ2.setSnapToTicks(true);
		sliderNbrGuerrierJ2.setPreferredSize(new Dimension(200,25));
		final JSlider sliderNbrVoleurJ2 = new JSlider();
		sliderNbrVoleurJ2.setMaximum(4);
		sliderNbrVoleurJ2.setMinimum(1);
		sliderNbrVoleurJ2.setValue(1);
		sliderNbrVoleurJ2.setPaintTicks(true);
		sliderNbrVoleurJ2.setPaintLabels(true);
		sliderNbrVoleurJ2.setMajorTickSpacing(1);
		sliderNbrVoleurJ2.setMinorTickSpacing(1);
		sliderNbrVoleurJ2.setSnapToTicks(true);
		sliderNbrVoleurJ2.setPreferredSize(new Dimension(200,25));
		final JSlider sliderNbrPiegeurJ2 = new JSlider();
		sliderNbrPiegeurJ2.setMaximum(4);
		sliderNbrPiegeurJ2.setMinimum(1);
		sliderNbrPiegeurJ2.setValue(1);
		sliderNbrPiegeurJ2.setPaintTicks(true);
		sliderNbrPiegeurJ2.setPaintLabels(true);
		sliderNbrPiegeurJ2.setMajorTickSpacing(1);
		sliderNbrPiegeurJ2.setMinorTickSpacing(1);
		sliderNbrPiegeurJ2.setSnapToTicks(true);
		sliderNbrPiegeurJ2.setPreferredSize(new Dimension(200,25));
		
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
		onglet2.add(slidertaille);
		onglet2.add(LBPourcent);
		onglet2.add(sliderpourcent);
		onglet1.add(BJouer);
		onglet1.add(Bregles);
		onglet1.add(Bquitter);
		onglet5.add(BTEnergie);
		onglet5.add(BTGuerrier);
		onglet5.add(BTPiegeur);
		onglet5.add(BTVoleur);
		onglet5.add(BTMove);
		
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
				tailleCarte = slidertaille.getValue();
				pourcentageRocher = sliderpourcent.getValue();
				nbrExplorateurJ1=sliderNbrExplorateurJ1.getValue();
				nbrGuerrierJ1=sliderNbrGuerrierJ1.getValue();
				nbrVoleurJ1=sliderNbrVoleurJ1.getValue();
				nbrPiegeurJ1=sliderNbrPiegeurJ1.getValue();
				nbrExplorateurJ2=sliderNbrExplorateurJ2.getValue();
				nbrGuerrierJ2=sliderNbrGuerrierJ2.getValue();
				nbrVoleurJ2=sliderNbrVoleurJ2.getValue();
				nbrPiegeurJ2=sliderNbrPiegeurJ2.getValue();
				etat = false;
				dispose();
			}
		});
		Bquitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		BTEnergie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id = 1;
			}
		});
	
	BTGuerrier.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			id = 2;
		}
	});
	BTPiegeur.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			id = 3;
		}
	});
	BTVoleur.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			id = 4;
		}
	});
	BTMove.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			id = 5;
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
		
	/**
	 * 
	 * @return taille de la carte
	 */
	public static int getTaille(){
		return tailleCarte;
	}
	
	/**
	 * 
	 * @return le pourcentage de rocher
	 */
	public static int getPourcent(){
		return pourcentageRocher;
	}
	
	/**
	 * 
	 * @return l'etat
	 */
	public static boolean getetat(){
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

}
