import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * La classe Plateau permet d'afficher un plateau de Jeu carré
 * sur lequel sont disposés des images représentant les éléments du jeu
 * Les images sont toutes de même taille et carrées. Optionellement, on peut y associer 
 * une zone d'affichage de texte et caturer les entrées (souris / clavier) de l'utilisateur.
 * @author M2103-Team 
 * 
 */
public class Plateau {
	private Menu menu;
	private static boolean defaultVisibility = true ;
	private JFrame window ;
	private GraphicPane graphic ;
	private ConsolePane console ;
	private JPanel infos=new JPanel(), infosPerso=new JPanel(), actionPartie=new JPanel(),inventairePerso=new JPanel();
	private JLabel nomPerso = new JLabel("Nom : "),recupNomPerso = new JLabel("  "),typePerso = new JLabel("Type : "),recupTypePerso = new JLabel("  ");
	private JLabel energie = new JLabel("Energie : "),recupenergie = new JLabel("  ");
	private JLabel inventaire  = new JLabel("Inventaire"), niveau=new JLabel("Niveau : "), recupNiveau=new JLabel(),JLvide=new JLabel(), stockRessource=new JLabel("Ressources : "), recupStockRessource=new JLabel();
	private JButton passageDuTour=new JButton("Passer son tour"), abandon, annulerSelection=new JButton("Retour"), aide=new JButton("Aide"), lacherObjet=new JButton("Lacher objet");
	private int id;
	private ArrayList<JLabel> objetIventaire=new ArrayList<JLabel>();
	private String[] imageinventaire= new String[]{"inventaire_vide.png","key.jpeg","sword.png","treasure.png","Bombe.png","pelle.png","Pioche.png","Pierre.png"};

	/**
	 *  Attribut ou est enregistré un événement observé. Cet attribut est
	 * initialisé à null au début de la scrutation et rempli par l'événement observé 
	 * par les deux listeners (mouseListener et keyListener). 
	 * cf {@link java.awt.event.InputEvent}.
	 */
	private InputEvent currentEvent = null ;
	/**
	 * Classe interne MouseListener. Quand instanciée et associée à un JPanel, elle
	 * répond à un événement souris en stockant cet événement dans l'attribut 
	 * {@link #currentEvent}.
	 * @author place
	 *
	 */
	private class Mouse implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent event) {
			currentEvent = event ;
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {	}
		@Override
		public void mouseExited(MouseEvent arg0) { }
		@Override
		public void mousePressed(MouseEvent arg0) { }
		@Override
		public void mouseReleased(MouseEvent arg0) { }
	}
	/**
	 * Classe interne keyListener. Quand instanciée et associée à une JFrame, elle
	 * répond à un événement clavier en le stockant dans la variable {@link #currentEvent}.
	 * @author place
	 *
	 */
	private class Key implements KeyListener {
		@Override
		public void keyPressed(KeyEvent event) {
			currentEvent = event ;
		}
		@Override
		public void keyReleased(KeyEvent e) {
		}
		@Override
		public void keyTyped(KeyEvent e) {
		}
	}
	/**
	 * Détermine la visibilité par défaut des plateaux construits. La valeur initiale est true : 
	 * tout plateau construit est immédiatement affiché.
	 * @param defaultValue vrai si tout plateau est affiché immédiatement 
	 */
	public static void setDefaultVisibility(boolean defaultValue) {
		defaultVisibility = defaultValue ;
	}
	/**
	 * Construit un plateau de jeu vide de dimension taille x taille.
	 * Initialement, les cellules sont vides. Le constructeur demande la fourniture
	 * d'un catalogue d'images gif ou png. La fermeture de la fenêtre provoque uniquement le
	 * masquage de celle-ci. La destruction complète doit être faite explicitement par programme via 
	 * la méthode {@link #close()}.
	 * @param gif tableau 1D des chemins des fichiers des différentes images affichées.
	 * @param taille dimension (en nombre de cellules) d'un côté du plateau.
	 */
	public Plateau(String[] gif,int taille){
		this(gif, taille, false) ;
	}
	/**
	 * Construit un plateau de jeu vide de dimension taille x taille aec une éventuelle zone de texte associée.
	 * Initialement, les cellules sont vides. Le constructeur demande la fourniture
	 * d'un catalogue d'images gif ou png.
	 * @param gif tableau 1D des chemins des fichiers des différentes images affichées.
	 * @param taille Dimension (en nombre de cellules) d'un côté du plateau.
	 *        <b>La taille fixée ici est la taille par défaut (plateau carré)</b> 
	 *        (gardé pour raison de compatibilité.
	 *        Le plateau s'ajustera en fonction des dimensions du tableau jeu.
	 * @param withTextArea Indique si une zone de texte doit être affichée.
	 */
	public Plateau(String[] gif,int taille, boolean withTextArea){
		// Instancie la fenetre principale et et les deux composants.

		JPanel vide=new JPanel();

		vide.setLayout(new GridLayout(2,1));
		infos.setLayout(new BorderLayout());
		actionPartie.setLayout(new GridLayout(5,1));
		inventairePerso.setLayout(new GridLayout(2,3));
		infosPerso.setLayout(new GridLayout(6,2));

		infos.add(vide,BorderLayout.CENTER);
		infos.add(actionPartie,BorderLayout.SOUTH);
		infos.add(infosPerso,BorderLayout.NORTH);
		vide.add(inventairePerso, BorderLayout.NORTH);

		this.setObjetIventaire();

		window = new JFrame() ;
		graphic = new GraphicPane(gif, taille) ;
		console = null ;
		window.setPreferredSize(new Dimension(taille*46+150, taille*46));
		// Caractéristiques initiales pour la fenetre.
		window.setTitle("Plateau de jeu ("+taille+"X"+taille+")");
		window.setLocationRelativeTo(null);
		window.setLayout(new BorderLayout());
		// La fermeture de la fenetre ne fait que la cacher. 
		// cf Javadoc setDefaultCloseOperation
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		if(Test.testEnCours)
			abandon=new JButton("Quitter le test");
		else
			abandon=new JButton("Abandonner la partie");

		actionPartie.add(lacherObjet);
		actionPartie.add(annulerSelection);
		actionPartie.add(passageDuTour);
		actionPartie.add(aide);
		actionPartie.add(abandon);

		// Ajout des composants à la fenetre
		infosPerso.add(stockRessource);
		infosPerso.add(recupStockRessource);
		infosPerso.add(typePerso);
		infosPerso.add(recupTypePerso);
		infosPerso.add(nomPerso);
		infosPerso.add(recupNomPerso);
		infosPerso.add(energie);
		infosPerso.add(recupenergie);
		infosPerso.add(inventaire);
		infosPerso.add(JLvide);
		infosPerso.add(niveau);
		infosPerso.add(recupNiveau);
		
		window.getContentPane().add(infos, BorderLayout.EAST);
		window.getContentPane().add(graphic, BorderLayout.WEST);
		if (withTextArea) {
			console = new ConsolePane() ;
			window.getContentPane().add(console) ;
		}
		resizeFromGraphic() ;

		// Affichage effectif 
		window.setVisible(defaultVisibility);
		// Ajout des listeners.
		graphic.addMouseListener(new Mouse());
		window.addKeyListener(new Key()) ;
		currentEvent = null ;
		menu=new Menu();

		passageDuTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id=1;
			}
		});
		abandon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id=2;
			}
		});
		annulerSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id=3;
			}
		});
		lacherObjet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id=4;
			}
		});
		aide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu.menuRegle();
			}
		});
		annulerSelection.setVisible(false);
		lacherObjet.setVisible(false);
		recupNiveau.setVisible(false);
	}

	/**
	 * Change la visibilité du bouton annuler
	 * @param setter
	 */
	public void setVisibleBouttonAnnuler(boolean setter){
		annulerSelection.setVisible(setter);
	}

	/**
	 * Change la visibilité du bouton lacher
	 * @param setter
	 */
	public void setVisibleBouttonLacher(boolean setter){
		lacherObjet.setVisible(setter);
	}

	/**
	 * @return l'id du buton selectionné
	 */
	public int getId(){
		return id;
	}

	/**
	 * remet l'id à 0
	 */
	public void resetId(){
		id=0;
	}

	/**
	 * Méthode permettant de placer les éléments sur le plateau. Le tableau doit être  
	 * de même taille que la dimension déclarée via le constructeur.
	 * @param jeu tableau 2D représentant le plateau  
	 * la valeur numérique d'une cellule désigne l'image correspondante
	 * dans le tableau des chemins (décalé de un, 0 désigne une case vide)
	 */
	public void setJeu(int[][] jeu){
		graphic.setJeu(jeu) ;	// Délégué au composant graphic.
		resizeFromGraphic() ;
	}
	/**
	 * Retourne le tableau d'entiers représentant le plateau
	 * @return le tableau d'entiers
	 */
	public int[][] getJeu(){
		return graphic.getJeu() ;	// Délégué au composant graphic.
	}
	/**
	 * Demande l'affichage du plateau de jeu. 
	 * Si la fenêtre était cachée, elle redevient visible.
	 */
	public void affichage(){ 
		window.setVisible(true);	// Révèle la fenêtre.
		window.repaint(); 			// Délégué à Swing (appelle indirectement graphic.paintComponent via Swing)
	}
	/**
	 * Détermine le titre de la fenetre associée.
	 * @param title Le titre à afficher.
	 */
	public void setTitle(String title) {
		window.setTitle(title) ;
	}
	/**
	 * Provoque le masquage du plateau.
	 * Le plateau est conservé en mémoire et peut être réaffiché par {@link #affichage()}.
	 */
	public void masquer() {
		window.setVisible(false);
	}
	/**
	 * Provoque la destruction du plateau. 
	 * L'arrêt du programme est conditionné par la fermeture de tous les plateaux ouverts.
	 */
	public void close() {
		window.dispose();
	}
	/**
	 * prépare l'attente d'événements Swing (clavier ou souris).
	 * Appelé par waitEvent() et waitEvent(int). 
	 */
	private void prepareWaitEvent() {
		currentEvent = null ;	// Annule tous les événements antérieurs
		window.requestFocusInWindow() ;
		affichage() ;	// Remet à jour l'affichage (peut être optimisé)
	}
	/**
	 * Attends (au maximum timeout ms) l'apparition d'une entrée (souris ou clavier).
	 * 
	 * @param timeout La durée maximale d'attente.
	 * @return L'événement observé si il y en a eu un, <i>null</i> sinon.
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/InputEvent.html">java.awt.event.InputEvent</a>
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseEvent.html">java.awt.event.MouseEvent</a>
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html">java.awt.event.KeyEvent</a>
	 */
	public InputEvent waitEvent(int timeout) {
		int time = 0 ;
		prepareWaitEvent() ;
		while ((currentEvent == null) && (time < timeout)) {
			try {
				Thread.sleep(100) ;	// Cette instruction - en plus du délai induit - permet à Swing de traiter les événements GUI 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time += 100 ;
		}
		return currentEvent ;
	}
	/**
	 * Attends (indéfiniment) un événement clavier ou souris. 
	 * Pour limiter le temps d'attente (timeout) voir {@link #waitEvent(int)}.
	 * 
	 * @return L'événement observé.
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/InputEvent.html">java.awt.event.InputEvent</a>
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseEvent.html">java.awt.event.MouseEvent</a>
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html">java.awt.event.KeyEvent</a>
	 */
	public InputEvent waitEvent() {
		prepareWaitEvent() ;
		while (currentEvent == null) {
			Thread.yield() ;	// Redonne la main à Swing pour gérer les événements
		}
		return currentEvent ;
	}
	/**
	 * Calcule la ligne de la case ciblée par un mouseEvent.
	 * Attention: il est possible si l'utilsateur agrandi la fenêtre que le clic
	 * se produise à l'extérieur du plateau. Cette configuration n'est pas détectée par cette méthode
	 * qui retourne alors une valeur hors des limites. 
	 *
	 * @param event L'évenement souris capturé.
	 * @return le numéro de la colonne ciblée (0 à taille-1)
	 */
	public int getX(MouseEvent event) {
		return graphic.getX(event) ;
	}
	/**
	 * Calcule la colonne de la case ciblée par un mouseEvent.
	 * Attention: il est possible si l'utilsateur agrandi la fenêtre que le clic
	 * se produise à l'extérieur du plateau. Cette configuration n'est pas détectée par cette méthode
	 * qui retourne alors une valeur hors des limites. 
	 *
	 * @param event L'évenement souris capturé.
	 * @return le numéro de la colonne ciblée (0 à taille-1)
	 */
	public int getY(MouseEvent event) { 	
		return graphic.getY(event) ;
	}
	/**
	 * Affiche un message dans la partie texte du plateau.
	 * Si le plateau a été construit sans zone texte, cette méthode est sans effet.
	 * Cela provoque aussi le déplacement du scroll vers l'extrémité basse de façon 
	 * à rendre le texte ajouté visible. On ajoute automatiquement une fin de ligne 
	 * de telle sorte que le message est seul sur sa ligne.
	 * @param message Le message à afficher.
	 */
	public void println(String message) {
		if (console != null) {
			console.println(message) ;
		}
	}
	// Note la taille initiale est calculée d'après la taille du graphique.
	private void resizeFromGraphic() {
		Dimension dim = graphic.getGraphicSize() ;
		if (console == null) {
			dim.height += 10 ;
		} else {
			dim.height += 150 ;
		}
		window.getContentPane().setPreferredSize(dim) ;
		window.pack() ;
	}
	/**
	 * Efface la surbrillance pour toutes les cellules du plateau. 
	 */
	public void clearHighlight() {
		if (graphic != null) {
			graphic.clearHighlight();
		}
	}
	/**
	 * Place une cellule en surbrillance. La surbrillance provoque la superposition d'un carré translucide 
	 * de la couleur précisée. 
	 * Les cellules peuvent revenir à leur état normal:
	 * <ul>
	 * <li>globalement par un appel à {@link #clearHighlight()}</li>
	 * <li>individuellement par un appel à {@link #resetHighlight(int, int)}</li>
	 * </ul>
	 * @param x La ligne de la cellule.
	 * @param y La colonne de la cellule.
	 * @param color La couleur du carré affiché.
	 */
	public void setHighlight(int x, int y, Color color) {
		if (graphic != null) {
			graphic.setHighlight(x, y, color);
		}
	}
	/**
	 * Efface la surbrillance pour une cellule du plateau. La cellule est déterminée par
	 * son numéro de ligne et de colonne. Si la cellule n'était pas en surbrillance, 
	 * cette méthode est sans effet.
	 * @param x Numéro de ligne de la cellule à affecter.
	 * @param y Numéro de colonne de la cellule à affecter.
	 */
	public void resetHighlight(int x, int y) {
		if (graphic != null) {
			graphic.resetHighlight(x, y);
		}
	}
	/**
	 * Permet de savoir si une cellule est actuellement en surbrillance.
	 * @param x Le numéro de ligne de la cellule.
	 * @param y Le numéro de colonne de la cellule.
	 * @return true si la cellule est actuellement en surbrillance.
	 */
	public boolean isHighlight(int x, int y) {
		return graphic.isHighlight(x, y) ;
	}
	/**
	 * Efface l'affichage de tout texte dans la partie graphique.
	 */
	public void clearText() {
		graphic.clearText() ;
	}
	/**
	 * Demande l'affichage d'un texte dans une case. Le texte est centré horizontalement et verticalement. Ecrit en Color.BLACK.
	 * @param x Le numéro de ligne de la cellule où apparaît le texte.
	 * @param y Le numéro de colonne de la cellule où apparaît le texte.
	 * @param msg les texte à afficher.
	 */
	public void setText(int x, int y, String msg) {
		graphic.setText(x, y, msg) ;		
	}

	/**
	 * affiche l'inventaire du personnage
	 * @param perso
	 */
	private void affichageInventaire(Personnage perso){
		for(int cpt=0;cpt<objetIventaire.size();cpt++){
			if(cpt<perso.getInventaire().size())
				this.addImageIcon(objetIventaire.get(cpt),perso.getInventaire().get(cpt));
			else
				this.addImageIcon(objetIventaire.get(cpt),"Vide");
		}
	}

	/**
	 * set l'image de l'objet passé en parametre dans l'inventaire
	 * @param label
	 * @param objet
	 */
	private void addImageIcon(JLabel label, String objet){
		int nbImages=imageinventaire.length;
		ImageIcon[] images=new ImageIcon[nbImages];	
		for (int i=0;i<nbImages;i++){
			java.net.URL imageURL = Plateau.class.getResource(imageinventaire[i]);
			   if (imageURL != null) {
			      images[i] = new ImageIcon(imageURL);
			   } else { // Traitement  si image non trouvée
			      System.err.println("Image : '" + imageinventaire[i]+ "' non trouvée") ;
			      images[i] = null ;
			   }
		}
		label.setVisible(true);
		if(objet.equals("Vide"))
			label.setIcon(images[0]);
		else if(objet.equals("Cle"))
			label.setIcon(images[1]);
		else if(objet.equals("Epee"))
			label.setIcon(images[2]);
		else if(objet.equals("Tresor"))
			label.setIcon(images[3]);
		else if(objet.equals("Bombe"))
			label.setIcon(images[4]);
		else if(objet.equals("Pelle"))
			label.setIcon(images[5]);
		else if(objet.equals("Pioche"))
			label.setIcon(images[6]);
		else if(objet.equals("Pierre"))
			label.setIcon(images[7]);
	}

	/**
	 * Affichage specifique à l'inventaire vide
	 */
	private void inventaireVide(){
		for(int cpt=0;cpt<objetIventaire.size();cpt++){
			this.addImageIcon(objetIventaire.get(cpt),"Vide");
		}
	}

	/**
	 * Cache l'inventaire
	 */
	private void hideInventaire(){
		for(int cpt=0;cpt<objetIventaire.size();cpt++){
			objetIventaire.get(cpt).setVisible(false);
		}
	}

	/**
	 * Initialise les jlabel de l'inventaire
	 */
	private void setObjetIventaire(){
		for(int i=0;i<6;i++){
			JLabel objet=new JLabel();
			inventairePerso.add(objet);
			objetIventaire.add(objet);
			objet.setVisible(true);
		}
	}

	/**
	 * refrsh des info suivant si c'est un personnage ou un batiment.
	 * Mettre à null perso si c'est le bateau qui est refresh
	 * @param perso
	 * @param batiment
	 */
	public void refreshinfo(Personnage perso, Batiment batiment){
		if(perso!=null){
			if(perso.getInventaire().isEmpty())
				inventaireVide();
			else
				this.affichageInventaire(perso);
			this.affichagePanel();
			recupNomPerso.setText(perso.getNom());
			recupTypePerso.setText(perso.getType());
			recupenergie.setText(""+perso.getEnergie());
			niveau.setVisible(false);
			recupNiveau.setVisible(false);
			recupStockRessource.setText(""+perso.getJoueur().getStockRessource());
		}else{
			recupTypePerso.setText(batiment.getType());
			recupenergie.setText(""+batiment.batimentHealth);
			if(batiment instanceof Fort){
				recupNiveau.setText(""+((Fort)batiment).getNiveau());
				niveau.setVisible(true);
				recupNiveau.setVisible(true);
			}else{
				niveau.setVisible(false);
				recupNiveau.setVisible(false);
			}
			recupStockRessource.setText(""+batiment.getJoueur().getStockRessource());
			nomPerso.setVisible(false);
			inventairePerso.setVisible(false);
			inventaire.setVisible(false);
			recupNomPerso.setVisible(false);
		}
	}

	/**
	 * Affichage d'un JOptionPane centré sur le plateau sans retour
	 * @param texte
	 * @param titre
	 * @param options
	 */
	public void popUp(String texte, String titre, Object[] options){
		JOptionPane.showOptionDialog(window, texte, titre,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null, options, options[0]);
	}

	/**
	 * Affichage d'un JOptionPane centré sur le plateau avec un retour
	 * @param texte
	 * @param titre
	 * @param listeItem
	 * @return l'objet selectionné
	 */
	public Object popUpYesNo(String texte, String titre, Object []listeItem){
		Object object;
		if(listeItem==null)
			object=JOptionPane.showConfirmDialog(window,texte,titre, JOptionPane.YES_NO_OPTION);
		else
			object=JOptionPane.showInputDialog(window,texte, titre, JOptionPane.QUESTION_MESSAGE , null,listeItem, listeItem[0]);
		return object;

	}

	/**
	 * Reset les infos des panel d'information
	 */
	public void resetInfo(){
		this.hideInventaire();
		recupNomPerso.setText("");
		recupTypePerso.setText("");
		recupenergie.setText("");
		niveau.setVisible(false);
		recupNiveau.setText("");
		recupStockRessource.setText("");
		this.affichagePanel();
	}

	/**
	 * affiche les panels d'information
	 */
	private void affichagePanel(){
		nomPerso.setVisible(true);
		inventaire.setVisible(true);
		inventairePerso.setVisible(true);
		recupNomPerso.setVisible(true);
	}
}
