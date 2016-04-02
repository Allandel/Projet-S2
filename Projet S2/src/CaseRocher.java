import javax.swing.JOptionPane;

/**
 * Classe h�rit�e de Case repr�sentant les Rochers
 * @author Allan
 * @version 1.0
 */
public class CaseRocher extends Case{
	private int x,y;
	private boolean hidden, key, chest;
	
	/**
	 * Constructeur permettant d'attribuer l'ID du rocher ainsi que ses coordonn�es
	 * @param x
	 * @param y
	 */
	public CaseRocher(int x, int y){
		super();
		this.setId(1);
		this.x = x;
		this.y = y;
	}
	/**
	 * Retourne l'initiale de l'objet contenu dans la case
	 */
	public String toString(){
		if(key==true){
			return "K";
		}else if (chest == true){
			return "C";
		}else{
			return "R";
		}
	}
	public void setKey(boolean setter){
		this.key = setter; 
	}
	public void setChest(boolean setter){
		chest =setter;
		hidden = true;
	}
	public void interactionRocher(Personnage p){
		if(key){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez trouv� la cl� ! Rendez vous au tr�sor afin de vous emparer de ses richesses !", "FELICITATION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
			p.setObjetInventaire("clé");
			key = false;
		}else if(chest && !p.getObjetInventaire("clé")&& hidden){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez trouv� le coffre ! Il sera d�sormais visible par votre �quipe !", "FELICITATION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
			setId(4);
			hidden=false;
			chest=false;
			p.setObjetInventaire("trésor");
		}else if(chest && p.getObjetInventaire("clé")){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez trouv� le coffre et vous avez la cl� ! Vous avez donc ouvert le coffre avec succ�s et poss�dez maintenant ses richesses dans votre inventaire ! Gare au Voleurs !", "FELICITATION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
			setId(4);
			hidden=false;
			chest=false;
			p.setObjetInventaire("trésor");
		}else if(chest && !p.getObjetInventaire("clé") && !hidden){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez d�j� trouv� le coffre... Ne restez pas ici ! Cherchez la cl� avant que les adversaires la trouvent", "FELICITATION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
		}else if(!chest && !hidden && !p.getObjetInventaire("trésor")){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Quelqu'un a déjà trouvé le trésor ! Vite, dépeches toi d'aller lui voler avant qu'il ne se sauve avec !", "ATTENTION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
		}else{
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Rien ne se trouve sous ce Rocher... Continuez votre recherche !", "Qu'y � t'il sous ce rocher ?",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
		}
	}
	public void checkRocher(){
		
	}

}
