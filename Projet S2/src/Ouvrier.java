import java.util.Random;

public class Ouvrier extends Personnage{
	private boolean minage=false;
	private int resultMinage;
	
	Ouvrier(Joueur joueur) {
		super(joueur);
		setNom("Bertrand");
		setType("Ouvrier");
		this.setObjetInventaire("Pioche");
		if(joueur.getEquipe())
			setId(10);
		else
			setId(15);
	}
	
	
	public void construireVillage(int x, int y, Case[][] tableauIle, Joueur joueur){
		if(joueur.getNbrVillage()<1){
			joueur.addVillage();
			tableauIle[x][y].setBatimentCourant(new Fort(15,x,y,joueur));
			tableauIle[x][y].getBatimentCourant().addPersoBatiment(this);
		}
	}
	
	public void minage(int x, int y, Case[][] tableauIle, Affichage affichage, int equipe){
		if(this.getObjetInventaire("Pioche") && ((CaseRocher)tableauIle[x][y]).getMinage()>0 && this.getInventaire().size()<6){
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
			}else{
				resultMinage=1;
			}

			for(int i=0;i<resultMinage;i++){
				if(this.getInventaire().size()+i<5){
					((CaseRocher)tableauIle[x][y]).setMinage((((CaseRocher)tableauIle[x][y]).getMinage())-1);
				}
			}
		}else{
			if(!this.getObjetInventaire("Pioche")){
				affichage.popUp(equipe,"Votre mineur ne dispose plus de pioche... Retournez a une base pour en récupérer une.", "MINAGE IMPOSSIBLE" );
			}else if(((CaseRocher)tableauIle[x][y]).getMinage()==0){
				affichage.popUp(equipe,"Ce rocher ne dispose plus de la matière que vous recherchez. Trouvez en un autre !", "MINAGE IMPOSSIBLE" );
			}else{
				affichage.popUp(equipe,"Votre inventaire est plein ! Videz le avant de miner !", "MINAGE IMPOSSIBLE" );
			}
		}
	}
			
	public void issueMinage(Affichage affichage, int equipe){
		if(resultMinage==0){
			affichage.popUp(equipe,"Votre minage à échoué...", "MINAGE RATER" );
			this.minage=false;
		}else if(resultMinage==2){
			affichage.popUp(equipe,"Votre minage à été brillant ! Vous avez récolter 2 pierre taillées !", "MINAGE BRILLANT");
			this.inventairePlein(affichage, "Votre inventaire ne peut pas supporter autant de pierres !\nVotre inventaire sera completé");
			for(int i=0; i<resultMinage;i++)
			if(this.getInventaire().size()<6){
				this.setObjetInventaire("Pierre");
			}
			this.minage=false;
		}else{
			affichage.popUp(equipe,"Votre minage à reussi ! Vous avez récolter 1 pierre taillée", "MINAGE REUSSI");
			this.minage=false;
		}
	}
	
	public boolean getMinage(){
		return minage;
	}
}
