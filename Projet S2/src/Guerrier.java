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

	public void attaque(Personnage p, int x, int y, Case[][] tableauIle){
		Random random=new Random();
		if (this.equipe1!=p.equipe1 && this.getObjetInventaire("Epee")){
			int degat=5*random.nextInt(7);
			p.perteEnergie(degat, x, y, tableauIle);
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez infliger "+degat+" points de degats a votre cible", "ATTAQUE",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, options, options[0]);

		}
	}
}
