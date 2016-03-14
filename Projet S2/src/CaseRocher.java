
public class CaseRocher extends Case{
	int x,y;
	private boolean key=false;
	private boolean chest= false;
	public CaseRocher(int x, int y){
		id = 1;
	}
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
	}
	public void getKey() {
		// TODO Auto-generated method stub
		if ( key== true ) {
			System.out.println("-----------6-");
		}
	}
}
