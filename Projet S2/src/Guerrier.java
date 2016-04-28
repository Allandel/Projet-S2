import java.util.Random;

import javax.swing.JOptionPane;
/**
 * Classe heritee de personnage representant un explorateur
 * @author louis
 *
 */
public class Guerrier extends Personnage{

	/**
	 * Constructeur lui attribuant le nom, type, son epee, son equipe et son id
	 * @param equipe1
	 * @param joueur
	 */
	public Guerrier(Joueur joueur){
		super(joueur);
		setNom("Hans");
		setType("Guerrier");
		setObjetInventaire("Epee");
		setObjetInventaire("Epee");
		setObjetInventaire("Epee");
		setObjetInventaire("Epee");
		setObjetInventaire("Epee");
		setObjetInventaire("Epee");
		if(joueur.getEquipe())
			setId(9);
		else
			setId(13);
	}

	public String toString(boolean console){
		return "G";
	}

	/**
	 * Permet d'attaquer un autre personnage
	 * @param p
	 * @param x
	 * @param y
	 * @param xApres
	 * @param yApres
	 * @param tableauIle
	 */
	public void attaque(Personnage p,int x, int y, int xApres, int yApres, Case[][] tableauIle, Affichage affichage, int equipe){
		Random random=new Random();
		if (this.getObjetInventaire("Epee")){
			//il fait plus de dommage s'il a une epee	
			int degat=5*random.nextInt(7);
			if(!p.perteEnergie(degat, xApres, yApres, tableauIle, true, false, affichage, equipe)){
				//Verifie que le personnage ne meurt pas de la perte d'energie
				affichage.popUp(equipe,"Vous avez inflige "+degat+" points de degats a votre cible", "ATTAQUE" );
			}else{
				affichage.popUp(equipe,"Vous avez  reussi a tuer votre cible", "ATTAQUE" );
			}
		}else{
			//fait moins de dommage comme le guerrier se bat avec ses poings	
			int degat=random.nextInt(7);
			if(!p.perteEnergie(degat, xApres, yApres, tableauIle, true, false,affichage, equipe)){
				//Verifie que le personnage ne meurt pas de la perte d'energie
				affichage.popUp(equipe,"Vous combattez à main nues... Vous avez infliger "+degat+" points de degats a votre cible", "ATTAQUE" );
			}else{
				affichage.popUp(equipe,"Vous avez  reussi a tuer votre cible", "ATTAQUE" );
			}
		}
		super.perteEnergie(10, x,y, tableauIle, false, false,affichage, equipe);
	}
}
