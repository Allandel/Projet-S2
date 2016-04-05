import java.util.ArrayList;
/**
 * Classe permettant de créer des Personnages (Explorateurs / Voleurs / Piegeurs [prochainement])
 * @author Valentin
 * @version 1.1 
 */
import java.util.Random;

import javax.swing.JOptionPane;
public class Personnage{
	
	private int energie=100;
	private String nom, type;
	private int id;
	private ArrayList <String> inventaire=new ArrayList<String>();
	private boolean equipe1;
	private boolean death=false;
	/**
	 * @param nom the nom to set
	 */
	void setNom(String nom){
		this.nom=nom;
	}
	/**
	 * @param equipe the equipe1 to set
	 */
	public void setEquipe(boolean equipe){
		equipe1=equipe;
	}
	/**
	 * @return the equipe1
	 */
	public boolean getEquipe(){
		return equipe1;
	}
	/**
	 * @param type the type to set
	 */
	void setType(String type){
		this.type=type;
	}
	/**
	 * @return the type
	 */
	public String getType(){
		return type;
	}
	/**
	 * @return the nom
	 */
	public String getNom(){
		return nom;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id){
		this.id=id;
	}
	/**
	 * @return the id
	 */
	public int getId(){
		return id;
	}
	/**
	 * Ajoute un objet à l'inventaire
	 * @param objet
	 */
	public void setObjetInventaire(String objet){
		inventaire.add(objet);
	}
	/**
	 * @return the inventaire
	 */
	public ArrayList getInventaire(){
		return this.inventaire;
	}
	/**
	 * Explore l'inventaire a la recherche d'un objet precis
	 * @param objet
	 */
	public boolean getObjetInventaire(String objet){
		for (String test: inventaire) {
			if (test.compareTo(objet)==0){
				return true;
			}
		}
		return false;
	}
	/**
	 * Permet au voleur de dérober un objet a l'équipe adverse
	 * @param p
	 */
	public void volerObjet(Personnage p){
		Random random=new Random();
		if (this.equipe1!=p.equipe1){
			if(!p.inventaire.isEmpty() && random.nextInt(4)==2 ){
				int objetVole=random.nextInt(p.inventaire.size());
				inventaire.add(p.inventaire.get(objetVole));
				p.inventaire.remove(objetVole);
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null, "Vous avez volé : "+inventaire.get(inventaire.size()-1)+" avec un franc succès ! Vous êtes un fin voleur !", "VOL REUSSI",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);
			}else{
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null, "Vous avez échoué votre vol... Peut etre aurez vous plus de chance la prochaine fois", "VOL ECHEC",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0]);
			}
		}else{
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "On ne vole pas d'objet a son equipe ! Concentrez vous sur les personnages adverses !", "TRAITRE !",
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			null, options, options[0]);
		}
	}
	public void perteEnergie(int nrj){
		this.energie-=nrj;
	}
	public void addEnergie(){
		if(this.energie<100){
			this.energie+=10;
		}
	}
	public int getEnergie(){
		return energie;
	}
	public void setEnergie(int nrj){
		this.energie=nrj;
	}
	public void setDeath(){this.death=true;}
	public boolean getDeath(){return this.death;}
}
