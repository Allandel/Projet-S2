import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Launcher extends JFrame{
	static int tailleCarte, pourcentageRocher;
	static boolean etat = true;

	public Launcher(){
		JPanel onglet1 =  new ImagePanel(new ImageIcon("img/carte.jpg").getImage());
		JPanel onglet2 = new JPanel();
		//Image fond = ;
		JTabbedPane menuOnglet = new JTabbedPane();
		GridLayout g = new GridLayout(3,3);
		onglet2.setLayout(g);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(500,250));
		this.setTitle("Treasure Hunt");
		this.setUndecorated(true);
		this.pack();
		this.setLocationRelativeTo(null);
		//		this.setLayout(g);
		JButton BJouer = new JButton("Jouer");
		JButton Bquitter = new JButton("Quitter");
		JLabel LBTaille = new JLabel("Taille du plateau:");
		JLabel LBPourcent = new JLabel("Pourcentage de rochers:"
				+ "");
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
		onglet2.add(LBTaille);
		onglet2.add(slidertaille);
		onglet2.add(LBPourcent);
		onglet2.add(sliderpourcent);
		onglet1.add(BJouer);
		onglet1.add(Bquitter);

		menuOnglet.add("Menu", onglet1);
		menuOnglet.add("Parametres", onglet2);
		this.getContentPane().add(menuOnglet);
		this.pack();
		this.setVisible(true);		
		BJouer.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent arg0){
				tailleCarte = slidertaille.getValue();
				pourcentageRocher = sliderpourcent.getValue();
				etat = false;
				dispose();
			}
		});
		Bquitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}

	public static int getTaille(){
		return tailleCarte;
	}
	public static int getPourcent(){
		return pourcentageRocher;
	}
	public static boolean getetat(){
		return etat;
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
