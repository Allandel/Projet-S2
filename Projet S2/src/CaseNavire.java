/**
 * Classe h�rit�e de Case repr�sentant les navires
 * @author Allan
 * @version 1.0
 */
import java.util.*;
import javax.swing.*;
public class CaseNavire extends Case {
	private ArrayList<Personnage>stockNavire=new ArrayList<Personnage>();
	private int decision;
	private String persoSortant;
	/**
	 * Construit un navire en lui attribuant l'ID donn�
	 * @param id
	 */
	public CaseNavire(int id){
		this.setId(id);
		stockNavire.add(new Explorateur());
	}
	public void entreeBateau(Personnage p){
		decision=JOptionPane.showConfirmDialog(null,"Voulez vous vraiment rentrer au Navire ?", "Rentrer au Navire", JOptionPane.YES_NO_OPTION);
		if (decision==0){
			this.stockNavire.add(p);
		}
	}
	public void sortieBateau(){
		if(!stockNavire.isEmpty()){
			int cpt=0;
			JOptionPane pane=new JOptionPane();
			String rang="";
			String [] listePerso= new String[stockNavire.size()];
			for(Personnage perso : stockNavire){
				listePerso[cpt]=perso.getType()+" "+perso.getNom();
				cpt++;
			}
			rang=(String) JOptionPane.showInputDialog(null,"Quels personnage voulez-vous faire sortir du navire ?", "Sortie du navire", JOptionPane.QUESTION_MESSAGE, null, listePerso, listePerso[0]);
			
			//persoSortant=JOptionPane.showInputDialog(listePerso+"\n Choisir le personnage à faire sortir :");
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
