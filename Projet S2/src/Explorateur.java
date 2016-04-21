import javax.swing.JOptionPane;

/**
 * Classe heritee de personnage representant un explorateur
 * @author Allan
 * @version 1.0
 */
public class Explorateur extends Personnage{
	/**
	 * Constructeur d'Explorateur lui attribuant un nom, un id et une �quipe
	 */
	public Explorateur(boolean equipe1, Joueur joueur){
		super(equipe1, joueur);
		setNom("Bob");
		setType("Explorateur");
		if(equipe1)
			setId(6);
		else
			setId(10);
	}
	
	public void interactionRocher(int x, int y, Case[][] tableauIle){
		boolean key=((CaseRocher)tableauIle[x][y]).getKey(), hidden=((CaseRocher)tableauIle[x][y]).getHidden();
		boolean chest=((CaseRocher)tableauIle[x][y]).getChest(), chestTaken=((CaseRocher)tableauIle[x][y]).getChestTaken();
		boolean keyTaken=((CaseRocher)tableauIle[x][y]).getKeyTaken();
				
		if(key){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez trouve la cle ! Rendez vous au tresor afin de vous emparer de ses richesses !", "FELICITATION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
			this.setObjetInventaire("Cle");
			((CaseRocher)tableauIle[x][y]).setKeyTaken(true);
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
		}else if(chest && !this.getObjetInventaire("Cle")&& hidden){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez trouve le coffre ! Il sera desormais visible par votre equipe !", "FELICITATION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
			tableauIle[x][y].setId(4);
			((CaseRocher)tableauIle[x][y]).setHidden(false);
		}else if(chest && this.getObjetInventaire("Cle")){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez trouve le coffre et vous avez la cle ! Vous avez donc ouvert le coffre avec succes et possedez maintenant ses richesses dans votre inventaire ! Gare au Voleurs !", "FELICITATION",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
			tableauIle[x][y].setId(4);
			((CaseRocher)tableauIle[x][y]).setHidden(false);
			((CaseRocher)tableauIle[x][y]).setChestTaken(true);
			this.setObjetInventaire("Tresor");
		}else if(chest && !this.getObjetInventaire("Cle") && !hidden){
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
		super.perteEnergie(5, x,y, tableauIle);
	}

	
	public String toString(boolean console){
		return "E";
	}	
}
