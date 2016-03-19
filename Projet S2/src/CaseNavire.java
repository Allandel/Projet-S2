/**
 * Classe héritée de Case représentant les navires
 * @author Allan
 * @version 1.0
 */
public class CaseNavire extends Case {
	/**
	 * Construit un navire en lui attribuant l'ID donné
	 * @param id
	 */
	public CaseNavire(int id){
		this.id = id;
	}
	public String toString(){
		return "N";
	}
}
