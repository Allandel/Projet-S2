import java.util.Random;

public class Ouvrier extends Personnage{
	private int cptMineur=0;

	Ouvrier(Joueur joueur) {
		super(joueur);
		setNom("Pierre");
		setType("Ouvrier");
		this.setObjetInventaire("Pioche");
	/*	if(joueur.getEquipe())
			setId();
		else
			setId();*/
	}
	
	public void construireVillage(Joueur joueur){
		if(joueur.getNbrVillage()<1){
			
		}
	}
	
	public void minage(int x, int y, Case[][] tableauIle, Affichage affichage, int equipe){
		if(this.getObjetInventaire("Pioche") && ((CaseRocher)tableauIle[x][y]).getMinage()>0){
			if(cptMineur==0){
				cptMineur=3;
			}
			if(cptMineur==1){
				int cpt;
				Random random=new Random(6);
				cpt=random.nextInt();
				if(cpt==0){
					affichage.popUp(equipe,"Votre minage à échoué...", "MINAGE RATER" );
					cptMineur=0;
				}else if(cpt==6){
					affichage.popUp(equipe,"Votre minage à été brillant ! Vous avez récolter 2 pierre taillées !", "MINAGE BRILLANT");
					this.setObjetInventaire("Pierre");
					this.setObjetInventaire("Pierre");
					((CaseRocher)tableauIle[x][y]).setMinage((((CaseRocher)tableauIle[x][y]).getMinage())-2);
				}else{
					affichage.popUp(equipe,"Votre minage à reussi ! Vous avez récolter 1 pierre taillée", "MINAGE REUSSI");
					this.setObjetInventaire("Pierre");
					((CaseRocher)tableauIle[x][y]).setMinage((((CaseRocher)tableauIle[x][y]).getMinage())-1);
				}
			}
		}
		if(!this.getObjetInventaire("Pioche")){
			affichage.popUp(equipe,"Votre mineur ne dispose plus de pioche... Retournez a une base pour en récupérer une.", "MINAGE IMPOSSIBLE" );
		}
		if(((CaseRocher)tableauIle[x][y]).getMinage()==0){
			affichage.popUp(equipe,"Ce rocher ne dispose plus de la matière que vous recherchez. Trouvez en un autre !", "MINAGE IMPOSSIBLE" );
		}
	}
}
