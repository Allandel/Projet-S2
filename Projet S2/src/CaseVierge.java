
public class CaseVierge extends Case {
	Personnage personnageCourant;
	
	public CaseVierge(){
		id = 0;
		this.personnageCourant = null;
	}
	public void setPersonnageCourant(Personnage p){
		this.personnageCourant = p;
	}
	public void removePersonnageCourant(){
		this.personnageCourant = null;
	}
	public String toString(){
		if(personnageCourant ==null){
			return  " ";
		}
		return personnageCourant.toString();
	}
}
