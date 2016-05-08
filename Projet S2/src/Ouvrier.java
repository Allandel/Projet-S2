import java.util.Random;

public class Ouvrier extends Personnage{
	private boolean upgrade=false;

	/**
	 * Construit un ouvrier
	 * @param joueur
	 */
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

	/**
	 * Construit un village sur la case selectionnée
	 * @param x
	 * @param y
	 * @param tableauIle
	 * @param joueur
	 */
	public void construireVillage(int x, int y, Case[][] tableauIle, Joueur joueur){
		if(joueur.getNbrVillage()<1){
			joueur.addVillage();
			this.viderInventaireDeRochers();
			tableauIle[x][y].removePersonnageCourant(); 
			if(joueur.getEquipe()){
				Fort bleu=new Fort(x,y,joueur);
				tableauIle[x][y].setBatimentCourant(bleu);
			}else{
				Fort rouge=new Fort(x,y,joueur);
				tableauIle[x][y].setBatimentCourant(rouge);	
			}
			tableauIle[x][y].getBatimentCourant().addPersoBatiment(this);
		}
	}
	
	public int viderInventaireDeRochers(){
		int cpt=0;
		for(int i=0; i<6;i++){
			if(this.getObjetInventaire("Pierre")){
			this.removeObjetInventaire("Pierre", false);
			cpt++;
			}
		}
		return cpt;
	}

	
	public void commencerUpgrade(){
			upgrade=true;
			this.setCompteur(4);
	}
	
	public void setUpgrade(boolean a){
		upgrade=a;
	}
	
	public boolean getUpgrade(){
		return upgrade;
	}
	
	public void construireMine(int x, int y, Case[][] tableauIle, Joueur joueur){
	}
	
	/**
	 * 
	 * @return le nombre de pierre présente dans l'inventaire du perso
	 */
	public int nbPierre(){
		int cpt=0;
		for(String test : getInventaire()){
			if(test.equals("Pierre")){
				cpt++;
			}
		}
		return cpt;
	}
	
	/**
	 * Permet de miner un rocher
	 * @param x
	 * @param y
	 * @param tableauIle
	 * @param affichage
	 * @param equipe
	 */
	public void minage(int x, int y, Case[][] tableauIle, Affichage affichage, int equipe){
		if(this.getObjetInventaire("Pioche") && ((CaseRocher)tableauIle[x][y]).getMinage()>0 && this.getInventaire().size()<6){
			int cpt;
			int resultMinage;
			Random random=new Random(7);
			cpt=random.nextInt();
			if(cpt==0){
				resultMinage=0;
				affichage.popUp(equipe,"Votre minage à échoué...", "MINAGE RATER" );
				super.perteEnergie(10, x,y, tableauIle, false, false,affichage, equipe);
			}else if(cpt>=5){
				resultMinage=2;
				affichage.popUp(equipe,"Votre minage à été brillant ! Vous avez récolter 2 pierre taillées !", "MINAGE BRILLANT");
				this.inventairePlein(affichage, "Votre inventaire ne peut pas supporter autant de pierres !\nVotre inventaire sera completé");
				super.perteEnergie(10, x,y, tableauIle, false, false,affichage, equipe);
			}else{
				resultMinage=1;
				affichage.popUp(equipe,"Votre minage à reussi ! Vous avez récolter 1 pierre taillée", "MINAGE REUSSI");
				super.perteEnergie(10, x,y, tableauIle, false, false,affichage, equipe);
				
			}

			for(int i=0;i<resultMinage;i++){
				if(this.getInventaire().size()+i<5){
					((CaseRocher)tableauIle[x][y]).setMinage((((CaseRocher)tableauIle[x][y]).getMinage())-1);
				}
				if(this.getInventaire().size()<6){
					this.setObjetInventaire("Pierre");
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

	/**
	 * Affiche la liste des actions possible de l'ouvrier
	 * @param x
	 * @param y
	 * @param tableauIle
	 * @param affichage
	 * @param equipe
	 * @param joueur
	 */
	public void actionOuvrier(int x, int y, Case[][] tableauIle, Affichage affichage, int equipe, Joueur joueur){
		if(tableauIle[x][y].getId()==1){
			this.minage(x, y, tableauIle, affichage, equipe);
		}else{
			if(this.nbPierre()==5){
				if(joueur.getNbrVillage()==0){
					int decision=(int)affichage.popUpYesNo(equipe,"Voulez vous créer votre village ici ?\n\n(Attention, ce choix est irréversible)", "Créer un village",null);
					if (decision==0){
						construireVillage(x,y, tableauIle, joueur);
						super.perteEnergie(30, x,y, tableauIle, false, false,affichage, equipe);
					}
				}else if(joueur.getNiveauVillage()>=2){
					int decision=(int)affichage.popUpYesNo(equipe,"Voulez vous créer une Mine ici ?\n\n(Attention, ce choix est irréversible)", "Créer un village",null);
					if (decision==0){
						construireMine(x,y,tableauIle,joueur);
						super.perteEnergie(20, x,y, tableauIle, false, false,affichage, equipe);
					}
				}	
			}else{
				affichage.popUp(equipe,"Vous n'avez pas assez de pierres ! Impossible de construire quoi que ce soit", "CONSTRUCTION IMPOSSIBLE" );
			}
		}
	}	
}
