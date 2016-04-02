import javax.swing.JOptionPane;

/**
 * Classe h�rit�e de Case repr�sentant les Rochers
 * @author Allan
 * @version 1.0
 */
public class CaseRocher extends Case{
	private int x,y;
	private boolean hidden;
	private boolean key;
	private boolean chest;
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
		this.chest =setter;
		hidden = true;
	}
	public void interactionRocher(Personnage p){
		if(this.key){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez trouv� la cl� ! Rendez vous au tr�sor afin de vous emparer de ses richesses !", "FELICITATION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
			p.setcle();
			this.key = false;
		}else if(this.chest && !p.getInventaireKey()){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez trouv� le coffre ! Il sera d�sormais visible par votre �quipe !", "FELICITATION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
			this.setId(4);
		}else if(this.chest && p.getInventaireKey()){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez trouv� le coffre et vous avez la cl� ! Vous avez donc ouvert le coffre avec succ�s et poss�dez maintenant ses richesses dans votre inventaire ! Gare au Voleurs !", "FELICITATION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
			this.setId(1);
			this.chest=false;
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
