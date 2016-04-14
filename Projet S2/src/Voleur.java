import java.util.Random;

import javax.swing.JOptionPane;

/**
 * Classe heritee de Personnage creant un personnage de type voleur
 * @author Valentin
 * @version 1.1
 */
public class Voleur extends Personnage {
	/**
	 * Constructeur cr�ant un voleur avec un nom, un type, un ID en fonction du parametre equipe1 determinant son equipe.
	 * @param equipe1
	 */
	public Voleur(boolean equipe1){
		super(equipe1);
		setNom("Bill");
		setType("Voleur");
		if(equipe1)
			setId(7);
		else
			setId(10);
	}

	public String toString(boolean console){
		return "V";
	}

	public void volerObjet(Personnage p){
		Random random=new Random();
		if (this.equipe1!=p.equipe1){
			if(!p.getInventaire().isEmpty() && random.nextInt(4)==2 ){
				int objetVole=random.nextInt(p.getInventaire().size());
				getInventaire().add(p.getInventaire().get(objetVole));
				p.getInventaire().remove(objetVole);
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null, "Vous avez vol� : "+getInventaire().get(getInventaire().size()-1)+" avec un franc succ�s ! Vous �tes un fin voleur !", "VOL REUSSI",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
						null, options, options[0]);
			}else{
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null, "Vous avez �chou� votre vol... Peut etre aurez vous plus de chance la prochaine fois", "VOL ECHEC",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						null, options, options[0]);
			}
		}
	}

}
