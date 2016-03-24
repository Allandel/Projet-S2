/**
 * Classe h�rit�e de Case repr�sentant les navires
 * @author Allan
 * @version 1.0
 */
public class CaseNavire extends Case {
	/**
	 * Construit un navire en lui attribuant l'ID donn�
	 * @param id
	 */
	public CaseNavire(int id){
		this.setId(id);
	}
	public String toString(){
		return "N";
	}
}
