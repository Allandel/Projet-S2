import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Classe heritee de Personnage creant un personnage de type piegeur
 * @author louis
 *
 */
public class Piegeur extends Personnage{
	ArrayList<Bombe>listeBombe=new ArrayList<Bombe>();
	
	
	/**
	 * Constructeur creant un piegeur avec un nom, un type, un ID en fonction du parametre equipe1 determinant son equipe.
	 * @param equipe1
	 * @param joueur
	 */
	public Piegeur(Joueur joueur){
		super(joueur);
		setNom("Paul");
		setType("Piegeur");
		super.setObjetInventaire("Pelle");
		super.setObjetInventaire("Bombe");
		super.setObjetInventaire("Bombe");
		super.setObjetInventaire("Bombe");
		if(joueur.getEquipe())
			setId(8);
		else
			setId(13);
	}
	
	public String toString(boolean console){
		return "P";
	}
	
	/**
	 * Permet au personnage de pieger une case
	 * @param x
	 * @param y
	 * @param tableauIle
	 */
	public void pieger(Personnage p,int x, int y, Case[][] tableauIle, Affichage affichage, int equipe, Joueur [] joueur){
		if(tableauIle[x][y].getId()>0 && tableauIle[x][y].getId()<4){
			int decision =(int)affichage.popUpYesNo(equipe,"Voulez vous faire exploser cet objet ?", "POSER UNE BOMBE", null );
			if(decision==0){
				this.poserBombe(p, x, y, tableauIle, affichage, equipe,joueur);
				super.perteEnergie(20, x,y, tableauIle, false, false,affichage, equipe);
			}
		}else if(!tableauIle[x][y].getBombe() && !tableauIle[x][y].getPiege()){
			String [] tab={"Pieger la case","Poser une bombe"};
			String action=null;
			action=(String)affichage.popUpYesNo(equipe,"\nQue voulez vous faire ?\n\n[Cliquez sur annuler si vous ne voulez rien faire]\n", "Choix de l'action",tab);
			if(action!=null){

				if (action.compareTo("Pieger la case")==0 && this.getObjetInventaire("Pelle")){
					tableauIle[x][y].setPiege(true);
					if(joueur[equipe].getEquipe()){
						tableauIle[x][y].setTeamPiege(0);
					}else{
						tableauIle[x][y].setTeamPiege(1);
					}
					super.perteEnergie(20, x,y, tableauIle, false, false,affichage, equipe);
				}else if(action.compareTo("Poser une bombe")==0 && this.getObjetInventaire("Bombe")){
					super.perteEnergie(20, x,y, tableauIle, false, false,affichage, equipe);
					this.poserBombe(p, x, y, tableauIle, affichage, equipe, joueur);
					this.getInventaire().remove(this.getInventaire().indexOf("Bombe"));					
				}else if(!this.getObjetInventaire("Bombe")){
					affichage.popUp(equipe,"Vous n'avez plus de bombes... Retournez au bateau pour en récupérer.", "PAS D'ACTION POSSIBLE");
				}else{
					affichage.popUp(equipe,"Vous n'avez plus de pelle, vous ne pouvez donc pas creuser de trou.\nRetournez au bateau pour en récuperer une.", "PAS D'ACTION POSSIBLE");
				}
			}
		}else{
			affichage.popUp(equipe,"Un piege ou une bombe a deja été posé ici", "PAS D'ACTION POSSIBLE");
		}
	}
	
	public void poserBombe(Personnage p,int x, int y, Case[][] tableauIle, Affichage affichage, int equipe, Joueur [] joueur){
		if(tableauIle[x][y] instanceof CaseRocher || tableauIle[x][y].getBatimentCourant() instanceof Navire){
			Bombe b=new Bombe(x,y);
			this.removeObjetInventaire("Bombe",false);
			b.explosionObjet(p,tableauIle, affichage, equipe, joueur);
		}else{
		Bombe b=new Bombe(x,y);
		tableauIle[x][y].setBombe(b);
		listeBombe.add(b);
		}
	}
	
	public void downCompteurBombe(Case[][] tableauIle, Affichage affichage, int equipe){
		int indexBombe=0;
		if(!listeBombe.isEmpty()){
			for(Bombe bombe:listeBombe){
				if(bombe.downCompteur(tableauIle, affichage, equipe)){
					indexBombe=listeBombe.indexOf(bombe);
				}
			}
			if(listeBombe.get(indexBombe).getCompteur()==0){
				listeBombe.remove(indexBombe);
			}
		}
	}
	
	
}
