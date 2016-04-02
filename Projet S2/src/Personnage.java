import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
public class Personnage{
	
	private int energie=100;
	private String nom, type;
	private int id;
	private ArrayList <String> inventaire=new ArrayList<String>();
	private boolean equipe1;
	
	void setNom(String nom){
		this.nom=nom;
	}

	public void setEquipe(boolean equipe){
		equipe1=equipe;
	}
	
	public boolean getEquipe(){
		return equipe1;
	}
	
	void setType(String type){
		this.type=type;
	}
	
	public String getType(){
		return type;
	}
	
	public String getNom(){
		return nom;
	}
	
	public void setId(int id){
		this.id=id;
	}

	public int getId(){
		return id;
	}
	
	public void setObjetInventaire(String objet){
		inventaire.add(objet);
	}

	public ArrayList getInventaire(){
		return this.inventaire;
	}
	public boolean getObjetInventaire(String objet){
		for (String test: inventaire) {
			if (test.compareTo(objet)==0){
				return true;
			}
		}
		return false;
	}
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
}
