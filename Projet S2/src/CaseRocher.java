import javax.swing.JOptionPane;

/**
 * Classe heritee de Case representant les Rochers
 * @author Allan
 * @version 1.0
 */
public class CaseRocher extends Case{
	private int x,y;
	private boolean hidden, key, chest, keyTaken=false, chestTaken=false;
	
	/**
	 * Constructeur permettant d'attribuer l'ID du rocher ainsi que ses coordonnees
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
	/**
	 * Setter de Key
	 * @param setter
	 */
	public void setKey(boolean setter){
		this.key = setter; 
	}
	/**
	 * Setter de Chest
	 * @param setter
	 */
	public void setChest(boolean setter){
		chest =setter;
		hidden = true;
	}
	/**
	 * Permet d'interagir avec un rocher
	 * @param p
	 */
	public void interactionRocher(Personnage p){
		if(key){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez trouve la cle ! Rendez vous au tresor afin de vous emparer de ses richesses !", "FELICITATION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
			p.setObjetInventaire("Cle");
			keyTaken=true;
			key = false;
		}else if(keyTaken){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Autrefois ici se trouvait la cle. Vous devriez surveiller le coffre... Mais peut etre est il deja trop tard", "PRENEZ GARDE...",
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			null, options, options[0]);
		}else if(chestTaken){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Une trace consequente indique l'ancienne presence du coffre. Volez vite le coffre a l'adversaire !", "PRENEZ GARDE...",
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			null, options, options[0]);
		}else if(chest && !p.getObjetInventaire("Cle")&& hidden){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez trouve le coffre ! Il sera desormais visible par votre equipe !", "FELICITATION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
			setId(4);
			hidden=false;
		}else if(chest && p.getObjetInventaire("Cle")){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez trouve le coffre et vous avez la cle ! Vous avez donc ouvert le coffre avec succes et possedez maintenant ses richesses dans votre inventaire ! Gare au Voleurs !", "FELICITATION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
			setId(1);
			hidden=false;
			chest=false;
			chestTaken=true;
			p.setObjetInventaire("Tresor");
		}else if(chest && !p.getObjetInventaire("Cle") && !hidden){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez deja trouve le coffre... Ne restez pas ici ! Cherchez la cle avant que les adversaires la trouvent", "FELICITATION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
		}else{
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Rien ne se trouve sous ce Rocher... Continuez votre recherche !", "Qu'y a t'il sous ce rocher ?",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
		}
	}
	public void checkRocher(){
		
	}

}
