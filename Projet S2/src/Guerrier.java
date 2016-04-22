import java.util.Random;

import javax.swing.JOptionPane;

public class Guerrier extends Personnage{

	public Guerrier(boolean equipe1, Joueur joueur){
		super(equipe1, joueur);
		setNom("Hans");
		setType("Guerrier");
		setObjetInventaire("Epee");
		if(equipe1)
			setId(9);
		else
			setId(13);
	}

	public String toString(boolean console){
		return "G";
	}

	public void attaque(Personnage p,int x, int y, int xApres, int yApres, Case[][] tableauIle){
		Random random=new Random();
		if (this.getObjetInventaire("Epee")){
			int degat=5*random.nextInt(7);
			if(!p.perteEnergie(degat, xApres, yApres, tableauIle, true)){
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null, "Vous avez inflige "+degat+" points de degats a votre cible", "ATTAQUE",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
						null, options, options[0]);
			}else{
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null, "Vous avez  reussi a tuer votre cible", "ATTAQUE",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
						null, options, options[0]);
			}
		}else{
			int degat=random.nextInt(7);
			if(!p.perteEnergie(degat, xApres, yApres, tableauIle, true)){
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null, "Vous combattez à main nues... Vous avez infliger "+degat+" points de degats a votre cible", "ATTAQUE",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
						null, options, options[0]);
			}else{
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null, "Vous avez  reussi a tuer votre cible", "ATTAQUE",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
						null, options, options[0]);
			}
		}
		super.perteEnergie(10, x,y, tableauIle, false);
	}
}
