import java.util.ArrayList;

/**
 * Classe 
 * @author Allan
 * @version 1.0
 */
public class Joueur {
	private ArrayList<Personnage> equipe = new ArrayList<Personnage>();
	private boolean equipe1;
	/**
	 * Jalon2: Construit un tableau de personnage
	 */
	Joueur(boolean equipe1){
		this.equipe1=equipe1;
	}

	public boolean getEquipe(){
		return equipe1;
	}

	public void addPerso(Personnage perso){
		equipe.add(perso);
	}

	public boolean actionPossible(){
		for(Personnage perso : equipe){
			if(perso.getAction() && !perso.getDeath())
				return true;
		}
		return false;
	}

	public void resetAction(){
		for(Personnage perso : equipe){
			if(perso.getCompteur()==0){
				perso.setAction(true);
			}else{
				perso.setCompteur();
				System.out.println(perso.getCompteur());
			}
		}
	}
}
