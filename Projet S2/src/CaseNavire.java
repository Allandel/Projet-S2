/**
 * Classe hï¿½ritï¿½e de Case reprï¿½sentant les navires
 * @author Allan
 * @version 1.0
 */
import java.util.*;
import javax.swing.*;
public class CaseNavire extends Case {
	ArrayList<Personnage>stockNavire=new ArrayList<Personnage>();
	int decision;
	/**
	 * Construit un navire en lui attribuant l'ID donnï¿½
	 * @param id
	 */
	public CaseNavire(int id){
		this.setId(id);
	}
	public boolean retourBateau(Personnage p){
		decision=JOptionPane.showConfirmDialog(null,"Voulez vous vraiment rentrer au Navire ?", "Rentrer au Navire", JOptionPane.YES_NO_OPTION);
		if (decision==0){
			this.stockNavire.add(p);
			return true;
		}
		return false;
	}
	public void sortieBateau(){
		if(!stockNavire.isEmpty()){
			// Créer une liste graphique ou selectionner l'un des personnages présent dans stockNavire
		}else{
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Il n'y a pas de personnages dans le Navire", "Attention",
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			null, options, options[0]);
		}
	}
	public String toString(){
		return "N";
	}
}
