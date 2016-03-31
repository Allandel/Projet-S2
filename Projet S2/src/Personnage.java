import java.util.ArrayList;
public class Personnage{
	
	private int energie=100;
	private String nom;
	private int id;
	private ArrayList <String> inventaire=new ArrayList<String>();
	
	
	void setNom(String nom){
		this.nom=nom;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	void deplacer(int x, int y){
	
	}
}
