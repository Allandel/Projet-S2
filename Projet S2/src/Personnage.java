import java.util.ArrayList;
public class Personnage{
	
	private int energie=100;
	private String nom, type;
	private int id;
	private ArrayList <String> inventaire=new ArrayList<String>();
	
	
	void setNom(String nom){
		this.nom=nom;
	}

	
	void setType(String type){
		this.type=type;
	}
	
	public String getType(){
		return type;
	}
	
	public String getNom(){
		return nom;
	}
	
	public void setId(int id){
		this.id=id;
	}

	public int getId(){
		return id;
	}
	
	void deplacer(int x, int y){
	
	}
	public void setcle(){
		inventaire.add("Cle");
	}
	public ArrayList getInventaire(){
		return this.inventaire;
	}
}
