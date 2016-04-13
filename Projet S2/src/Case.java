import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 * Classe representant une case
 * @author Allan
 * @version 1.0
 */
public class Case {
	private int id=0;
	private boolean accessible;
	private Personnage personnageCourant=null;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the accessible
	 */
	public boolean isAccessible() {
		return accessible;
	}
	/**
	 * @return the stockNavire
	 */
	public ArrayList getStockNavire(){
		return new ArrayList();
	}
	/**
	 * @param accessible the accessible to set
	 */
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	
	/**
	 * Construit une case initialisant l'attribut accessible, key et chest a false
	 */
	public Case(){
		accessible =false;
	}
	/**
	 * @param PersonnageCourant the PersonnageCourant to set
	 */
	public void setPersonnageCourant(Personnage p){
		this.personnageCourant = p;
		this.setId(p.getId());
	}
	/**
	 * Supprime un Personnage qui se trouve sur une Case
	 */
	public void removePersonnageCourant(){
		this.personnageCourant = null;
		this.setId(0);
	}
	
	/*public void epuisement(int nrj){
		if((getPersonnageCourant().getEnergie())-nrj<=0){
			setId(12);
			getPersonnageCourant().setDeath();
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Votre personnage �tait � bout de force... Cette ultime action lui a cout� la vie. Son inventaire se trouve d�sormais au sol et peut �tre r�cup�rer par n'importe quel personne", "VOTRE PERSONNE EST MORT",
			JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
			null, options, options[0]);
		}else{
			getPersonnageCourant().perteEnergie(nrj);
		}
	}*/
	/**
	 * Permet a un personnage d'entrer dans un bateau
	 * @param p
	 */
	public boolean entreeBateau(Personnage p){
		return false;
	}
	public String choisirSortieBateau(){
		return " ";
	}
	/**
	 * Donne le Personnage Courant affili� a une case
	 */
	public Personnage getPersonnageCourant(){
		return personnageCourant;
	}

	public String toString(){
		if(personnageCourant ==null){
			return  " ";
		}
		return personnageCourant.toString();
	}

	public void addPersoNavire(Personnage p){	}

	
	public void interactionRocher(Personnage p){}
	
	public void recuperationStuff(Personnage p){
		if(this.getPersonnageCourant().getInventaire().isEmpty()){
			removePersonnageCourant();
			setPersonnageCourant(p);
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Ce cadavre n'avait rien d'interessant...", "Rencontre avec un mort",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
		}else{
			String res="\n\n";
			for (int i=0; i<getPersonnageCourant().getInventaire().size();i++){
				p.getInventaire().add(this.getPersonnageCourant().getInventaire().get(i));
				res+="+ "+this.getPersonnageCourant().getInventaire().get(i)+"\n";
			}
			removePersonnageCourant();
			setPersonnageCourant(p);
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez r�cuperer des objets sur le cadavre... Vous en aurez plus besoin que lui.\nVous avez recuperer :"+res, "Rencontre avec un mort",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
			System.out.println(getPersonnageCourant().getInventaire());
		}
	}
}
