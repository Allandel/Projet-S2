import java.util.Random;

/**
 * Classe heritee de Personnage creant un personnage de type voleur
 * @author Valentin
 * @version 1.1
 */
public class Voleur extends Personnage {
	/**
	 * Constructeur cr�ant un voleur avec un nom, un type, un ID en fonction du parametre equipe1 determinant son equipe.
	 * @param joueur
	 */
	public Voleur(Joueur joueur){
		super(joueur);
		setNom("Bill");
		setType("Voleur");
		if(joueur.getEquipe())
			setId(7);
		else
			setId(12);
	}

	public String toString(boolean console){
		return "V";
	}

	/**
	 * Permet au voleur de voler un autre personnage
	 * @param p
	 * @param x
	 * @param y
	 * @param tableauIle
	 * @param affichage
	 * @param equipe
	 */
	public void volerObjet(Personnage p, int x, int y, Case[][] tableauIle, Affichage affichage, int equipe){
		Random random=new Random();
		if (joueur!=p.getJoueur()){
			if(!inventairePlein(affichage, "L'inventaire de votre voleur est plein, vous ne pouvez rien y ajouter.")){
				if(p.getInventaire().isEmpty() && random.nextInt(3)==2 ){
					affichage.popUp(equipe,"Vous avez reussi a mettre la main dans le sac de votre cible mais celui ci est vide.", "VOL IMPOSSIBLE" );
				}else if(!p.getInventaire().isEmpty() && random.nextInt(4)==2 ){
					int objetVole=random.nextInt(p.getInventaire().size());
					setObjetInventaire(p.getInventaire().get(objetVole));
					p.getInventaire().remove(objetVole);
					affichage.popUp(equipe,"Vous avez voler : "+getInventaire().get(getInventaire().size()-1)+" avec un franc succes ! Vous etes un fin voleur !", "VOL REUSSI" );
				}else{
					affichage.popUp(equipe,"Vous avez echoue votre vol... Peut etre aurez vous plus de chance la prochaine fois", "VOL ECHEC" );
				}
				super.perteEnergie(10, x,y, tableauIle, false, false, affichage, equipe);
			}
		}
	}
}
