import javax.swing.JOptionPane;

/**
 * Classe hï¿½ritï¿½e de Case reprï¿½sentant les Rochers
 * @author Allan
 * @version 1.0
 */
public class CaseRocher extends Case{
	private int x,y;
	private boolean hidden;
	private boolean key;
	private boolean chest;
	/**
	 * Constructeur permettant d'attribuer l'ID du rocher ainsi que ses coordonnï¿½es
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
			JOptionPane.showOptionDialog(null, "Vous avez trouvé la clé ! Rendez vous au trésor afin de vous emparer de ses richesses !", "FELICITATION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
			p.setcle();
			this.key = false;
		}else if(this.chest && !p.getInventaireKey()){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez trouvé le coffre ! Il sera désormais visible par votre équipe !", "FELICITATION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
			this.setId(4);
		}else if(this.chest && p.getInventaireKey()){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez trouvé le coffre et vous avez la clé ! Vous avez donc ouvert le coffre avec succès et possédez maintenant ses richesses dans votre inventaire ! Gare au Voleurs !", "FELICITATION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
			this.setId(1);
			this.chest=false;
		}else{
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Rien ne se trouve sous ce Rocher... Continuez votre recherche !", "Qu'y à t'il sous ce rocher ?",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
		}
	}
	public void checkRocher(){
		
	}

}
