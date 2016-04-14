import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Classe heritee de Case representant les navires
 * @author Allan
 * @version 1.0
 */
public class CaseNavire extends Case {
	private ArrayList<Personnage>stockNavire=new ArrayList<Personnage>();

	/**
	 * Construit un navire en lui attribuant l'ID donnee
	 * @param id
	 */
	public CaseNavire(int id){
		this.setId(id);
	}
	/**
	 * Retourne la liste de Personnage du Navire
	 */
	public ArrayList<Personnage> getStocknavire(){
		return stockNavire;
	}

	/**
	 * Permet a un personnage d'entrer dans le bateau
	 * @param p
	 */
	public void addPersoNavire(Personnage p){
		stockNavire.add(p);
	}

	/**
	 * Permet a un personnage de sortir du bateau dans le bateau
	 */
	public Personnage choisirSortieBateau(){
		Personnage persoSortant = null;
		
		if(!stockNavire.isEmpty()){
			Personnage [] listePerso= new Personnage[stockNavire.size()];
			for(int cpt=0; cpt<stockNavire.size();cpt++){
				listePerso[cpt]=stockNavire.get(cpt);
			}
			persoSortant=(Personnage) JOptionPane.showInputDialog(null,"Quels personnage voulez-vous faire sortir du navire ?", "Sortie du navire", JOptionPane.QUESTION_MESSAGE, null, listePerso, listePerso[0]);
			System.out.println(persoSortant);
		}else{
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Il n'y a pas de personnages dans le Navire", "Attention",
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			null, options, options[0]);
		}
		return persoSortant;
	}
	
	public String toString(){
		return "N";
	}
}
