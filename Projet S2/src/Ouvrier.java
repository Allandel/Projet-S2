import java.util.Random;

public class Ouvrier extends Personnage{
	private boolean minage=false;
	private int resultMinage;
	
	Ouvrier(Joueur joueur) {
		super(joueur);
		setNom("Bertrand");
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
			affichage.popUp(equipe,"Votre ouvrier va tenter de miner ce rocher. Il sera donc indisponible pendant 2 tours", "TENTATIVE DE MINAGE" );
			this.setCompteur(3);
			this.minage=true;
			int cpt;
			Random random=new Random(6);
			cpt=random.nextInt();
			if(cpt==0){
				resultMinage=0;
			}else if(cpt==5){
				resultMinage=2;
				((CaseRocher)tableauIle[x][y]).setMinage((((CaseRocher)tableauIle[x][y]).getMinage())-2);
			}else{
				resultMinage=1;
				((CaseRocher)tableauIle[x][y]).setMinage((((CaseRocher)tableauIle[x][y]).getMinage())-1);
			}
		}if(!this.getObjetInventaire("Pioche")){
			affichage.popUp(equipe,"Votre mineur ne dispose plus de pioche... Retournez a une base pour en récupérer une.", "MINAGE IMPOSSIBLE" );
		}
		if(((CaseRocher)tableauIle[x][y]).getMinage()==0){
			affichage.popUp(equipe,"Ce rocher ne dispose plus de la matière que vous recherchez. Trouvez en un autre !", "MINAGE IMPOSSIBLE" );
		}
	}
			
	public void issueMinage(Affichage affichage, int equipe){
		if(resultMinage==0){
			affichage.popUp(equipe,"Votre minage à échoué...", "MINAGE RATER" );
			this.minage=false;
		}else if(resultMinage==2){
			affichage.popUp(equipe,"Votre minage à été brillant ! Vous avez récolter 2 pierre taillées !", "MINAGE BRILLANT");
			this.setObjetInventaire("Pierre");
			this.setObjetInventaire("Pierre");
			this.minage=false;
		}else{
			affichage.popUp(equipe,"Votre minage à reussi ! Vous avez récolter 1 pierre taillée", "MINAGE REUSSI");
			this.setObjetInventaire("Pierre");
			this.minage=false;
		}
	}
	
	public boolean getMinage(){
		return minage;
	}
}
