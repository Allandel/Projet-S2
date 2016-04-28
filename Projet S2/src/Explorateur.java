import javax.swing.JOptionPane;

/**
 * Classe heritee de personnage representant un explorateur
 * @author Allan
 * @version 1.0
 */
public class Explorateur extends Personnage{
	/**
	 * Constructeur d'Explorateur lui attribuant un nom, un id, une equipe et l'ajoute dans l'equipe du joueur correspondant
	 */
	public Explorateur(Joueur joueur){
		super(joueur);
		setNom("Bob");
		setType("Explorateur");
		if(joueur.getEquipe())
			setId(6);
		else
			setId(10);
	}

	/**
	 * Interaction de l'explorateur et des rocher suivant le contenu du rocher
	 * @param x
	 * @param y
	 * @param tableauIle
	 * @param joueur
	 */
	public void interactionRocher(int x, int y, Case[][] tableauIle, Affichage affichage, int equipe){
		boolean key=((CaseRocher)tableauIle[x][y]).getKey();
		boolean chest=((CaseRocher)tableauIle[x][y]).getChest(), chestTaken=((CaseRocher)tableauIle[x][y]).getChestTaken();
		boolean keyTaken=((CaseRocher)tableauIle[x][y]).getKeyTaken();

		if(key){
			affichage.popUp(equipe,"Vous avez trouve la cle ! Rendez vous au tresor afin de vous emparer de ses richesses !", "FELICITATION" );
			this.setObjetInventaire("Cle");
			((CaseRocher)tableauIle[x][y]).setKeyTaken(true);
		}else if(keyTaken){
			affichage.popUp(equipe,"Autrefois ici se trouvait la cle. Vous devriez surveiller le coffre... Mais peut etre est il deja trop tard", "PRENEZ GARDE..." );
		}else if(chestTaken){
			affichage.popUp(equipe,"Une trace consequente indique l'ancienne presence du coffre. Volez vite le coffre a l 'adversaire !", "PRENEZ GARDE..." );
		}else if(chest && !this.getObjetInventaire("Cle")&& !joueur.getCoffreTrouve()){
			affichage.popUp(equipe,"Vous avez trouve le coffre ! Il sera desormais visible par votre equipe !", "FELICITATION" );
			tableauIle[x][y].setId(4);
			joueur.coffreTrouve();
		}else if(chest && this.getObjetInventaire("Cle")){
			affichage.popUp(equipe,"Vous avez trouve le coffre et vous avez la cle ! Vous avez donc ouvert le coffre avec succes et possedez maintenant ses richesses dans votre inventaire ! Gare au Voleurs !", "FELICITATION" );
			tableauIle[x][y].setId(4);
			((CaseRocher)tableauIle[x][y]).setChestTaken(true);
			joueur.coffreTrouve();
			this.setObjetInventaire("Tresor");
		}else if(chest && !this.getObjetInventaire("Cle") && joueur.getCoffreTrouve()){
			affichage.popUp(equipe,"Vous avez deja trouve le coffre... Ne restez pas ici ! Cherchez la cle avant que les adversaires la trouvent", "FELICITATION" );
		}else{
			affichage.popUp(equipe,"Rien ne se trouve sous ce Rocher... Continuez votre recherche !", "Qu'y a t'il sous ce rocher ?" );
		}
		super.perteEnergie(5, x,y, tableauIle, false,false, affichage, equipe);
	}


	public String toString(boolean console){
		return "E";
	}	
}
