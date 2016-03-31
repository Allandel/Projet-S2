import java.util.ArrayList;
public class Personnage{
	
	private int energie=100,x, y;
	private String nom;
	private int id;
	private ArrayList <String> inventaire=new ArrayList<String>();
	
	
	void setNom(String nom){
		this.nom=nom;
	}
	public String getNom(){
		return this.nom;
	}
	
	public void setCoordonnées(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	void deplacer(int x, int y){
	
	}
}
