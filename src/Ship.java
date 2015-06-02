import java.awt.Rectangle;



public class Ship {
	int x,y;
	int length;
	boolean orientation;
	boolean selected;
	public Ship(){
		this.x=0;
		this.y=0;
		this.length=0;
		this.selected=false;
		this.orientation=false;
	}
	public Ship(int length,int x,int y,boolean orientation){
		this.length=length;
		this.selected=false;
		this.orientation=false;
		this.x=x;
		this.y=y;
	}
}
