import java.util.ArrayList;
/**
 * Classe permettant de cr�er des Personnages (Explorateurs / Voleurs / Piegeurs [prochainement])
 * @author Valentin
 * @version 1.1 
 */

import javax.swing.JOptionPane;
public class Personnage{

	private int energie=100, id, idBateau;
	private String nom, type;
	protected ArrayList <String> inventaire=new ArrayList<String>();
	protected boolean equipe1;
	private boolean death=false;

	Personnage(boolean equipe){
		this.equipe1=equipe;
		if(equipe1)
			idBateau=2;
		else
			idBateau=3;
	}

	/**
	 * @param nom the nom to set
	 */
	void setNom(String nom){
		this.nom=nom;
	}

	public int getIdBateau(){
		return idBateau;
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
	 * Ajoute un objet � l'inventaire
	 * @param objet
	 */
	public void setObjetInventaire(String objet){
		inventaire.add(objet);
	}
	/**
	 * @return the inventaire
	 */
	public ArrayList<String> getInventaire(){
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
	 * Permet au voleur de d�rober un objet a l'�quipe adverse
	 * @param p
	 */
	public void echangeObjet(Personnage p){
		int decision;
		String itemEchange;
		decision=JOptionPane.showConfirmDialog(null,"Désirez vous effectuer un échange avec ce membre de votre équipe ?", "Effectuer un echange ?", JOptionPane.YES_NO_OPTION);
		if (decision==0){
			if(!this.inventaire.isEmpty()){
				String [] listeItem= new String[inventaire.size()];
				for(int cpt=0; cpt<inventaire.size(); cpt++){
					listeItem[cpt]=inventaire.get(cpt);
				}
				itemEchange=(String) JOptionPane.showInputDialog(null,"Quels Item voulez vous donner ?", "Boite d'�changes", JOptionPane.QUESTION_MESSAGE, null, listeItem, listeItem[0]);
				p.inventaire.add(itemEchange);
				this.inventaire.remove(itemEchange);
			}else{
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null, "Votre inventaire est vide, impossible de faire un �change", "ECHANGE IMPOSSIBLE",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
						null, options, options[0]);
			}
		}	
	}

	/**
	 * Permet les deplacement des personnages
	 * @param yApres
	 * @param xApres
	 * @param yAvant
	 * @param xAvant
	 * @param tableauIle
	 */
	public void mouvement(int xAvant, int yAvant, int xApres, int yApres, Case [][] tableauIle){
		tableauIle[xAvant][yAvant].removePersonnageCourant();
		tableauIle[xApres][yApres].setPersonnageCourant(this);
		perteEnergie(1, xApres,yApres, tableauIle);
	}

	public boolean entreeBateau(int xAvant, int yAvant, int xApres, int yApres, Case [][] tableauIle){
		int decision=JOptionPane.showConfirmDialog(null,"Voulez vous vraiment rentrer au Navire ?", "Rentrer au Navire", JOptionPane.YES_NO_OPTION);
		if (decision==0){
			((CaseNavire)tableauIle[xApres][yApres]).addPersoNavire(this);
			tableauIle[xAvant][yAvant].removePersonnageCourant();
			if(inventaire.contains("Tresor"))
				return true;
		}
		return false;
	}

	private void perteEnergie(int nrj, int x, int y, Case[][] tableauIle){
		if(energie-nrj<=0){
			tableauIle[x][y].setId(12);
			death=true;
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Votre personnage �tait � bout de force... Cette ultime action lui a cout� la vie. Son inventaire se trouve d�sormais au sol et peut �tre r�cup�rer par n'importe quel personne", "VOTRE PERSONNE EST MORT",
					JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
					null, options, options[0]);
		}else{
			energie-=nrj;
		}
	}
	
	public void recuperationStuff(boolean sortieBateau, int x, int y, int xApres, int yApres, Case[][] tableauIle){
		if(tableauIle[xApres][yApres].getPersonnageCourant().getInventaire().isEmpty()){
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Ce cadavre n'avait rien d'interessant...", "Rencontre avec un mort",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
		}else{
			String res="\n\n";
			for (int i=0; i<tableauIle[xApres][yApres].getPersonnageCourant().getInventaire().size();i++){
				this.getInventaire().add(tableauIle[xApres][yApres].getPersonnageCourant().getInventaire().get(i));
				res+="+ "+tableauIle[xApres][yApres].getPersonnageCourant().getInventaire().get(i)+"\n";
			}
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Vous avez r�cuperer des objets sur le cadavre... Vous en aurez plus besoin que lui.\nVous avez recuperer :"+res, "Rencontre avec un mort",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			null, options, options[0]);
			System.out.println(this.getInventaire());
		}
		
		tableauIle[xApres][yApres].removePersonnageCourant();
		if(!sortieBateau)
			tableauIle[x][y].removePersonnageCourant();
		tableauIle[xApres][yApres].setPersonnageCourant(this);
	}	
	
	public void addEnergie(){
		if(energie<100){
			energie+=10;
		}
	}

	public int getEnergie(){
		return energie;
	}

	public boolean getDeath(){return death;}
	
	public String toString(){
		return ""+this.getType()+" "+this.getNom();
	}
	
	public String toString(boolean console){
		return "";
	}
}
