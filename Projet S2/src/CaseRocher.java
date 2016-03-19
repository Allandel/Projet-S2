/**
 * Classe h�rit�e de Case repr�sentant les Rochers
 * @author Allan
 * @version 1.0
 */
public class CaseRocher extends Case{
	int x,y;
	boolean hidden;
	/**
	 * Constructeur permettant d'attribuer l'ID du rocher ainsi que ses coordonn�es
	 * @param x
	 * @param y
	 */
	public CaseRocher(int x, int y){
		super();
		id = 1;
		this.x = x;
		this.y = y;
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
	public void setKey(boolean setter){
		this.key = setter; 
	}
	public void setChest(boolean setter){
		this.chest =setter;
		hidden = true;
	}

}
