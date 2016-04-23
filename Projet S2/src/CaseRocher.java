
/**
 * Classe heritee de Case representant les Rochers
 * @author Allan
 * @version 1.0
 */
public class CaseRocher extends Case{
	private boolean key, chest, keyTaken=false, chestTaken=false;

	/**
	 * Constructeur permettant d'attribuer l'ID du rocher ainsi que ses coordonnees
	 * @param x
	 * @param y
	 */
	public CaseRocher(int x, int y){
		super();
		this.setId(1);
	}
	/**
	 * Retourne l'initiale de l'objet contenu dans la case
	 */
	public String toString(){
		if(key==true){
			return "K";
		}else if (chest == true){
			return "C";
		}else{
			return "R";
		}
	}
	/**
	 * Setter de Key
	 * @param setter
	 */
	public void setKey(boolean setter){
		this.key = setter; 
	}
	/**
	 * Setter de Chest
	 * @param setter
	 */
	public void setChest(boolean setter){
		chest =setter;
	}

	/**
	 * @param keyTaken the keyTaken to set
	 */
	public void setKeyTaken(boolean keyTaken) {
		this.keyTaken = keyTaken;
		key=!keyTaken;
	}
	/**
	 * @param chestTaken the chestTaken to set
	 */
	public void setChestTaken(boolean chestTaken) {
		this.chestTaken = chestTaken;
		chest=!chestTaken;
	}

	public boolean getKey(){
		return key;
	}

	public boolean getChest(){
		return chest;
	}

	public boolean getKeyTaken(){
		return keyTaken;
	}

	public boolean getChestTaken(){
		return chestTaken;
	}

}
