import java.util.Random;

public class AI {

	 int[][] board = new int[5][5];
	 Random r = new Random();
	 Player p;
	 int width=5, height =5;
	 public void initBoard(){
	        for(int row=0 ; row < height ; row++ )
	            for(int column=0 ; column < width ; column++ )
	                board[row][column]=-1;
	 }
	 public void arrangeBoard(int length){
			int x,y;
			x=r.nextInt(width);
			y=r.nextInt(height);
			boolean orientation;
			orientation=r.nextBoolean();
			while(shipObstruct(x,y,orientation,length)){
				x=r.nextInt(width);
				y=r.nextInt(height);
				orientation = r.nextBoolean();
				//System.out.println("fuck you"+"x:"+x+"y:"+y+"orient:"+orientation);
			}
			if(orientation){
				//Horizontal
				for(int i=x;i<x+length;i++){
					board[i][y]=1;
				}
			}
			if(!orientation){
				//Vertical
				for(int i=y;i<y+length;i++){
					board[x][i]=1;
				}
			}
			
	 }
	 private boolean shipObstruct(int x, int y, boolean orientation, int length) {
		 if(orientation){
			 if(x+length<=width){
				 for(int i=x;i<x+length;i++){
					 if (board[i][y]==1)
						 return true;
		      }
		  }
		  else{
				return true;
		  }
		 }
		if(!orientation){
			if(y+length<=height){
			for(int i=y;i<y+length;i++){
				if (board[x][i]==1)
					return true;
			}
		}
			else{
				return true;
			}
		}
		return false;
	}
	public void shoot(Player p){
		    int x=r.nextInt(width),y=r.nextInt(height);
			//Get Input for x and y, the coordinates to shoot at
			p.board[x][y]=0;
		}
	 public AI(){
		 initBoard();
		 arrangeBoard(5);
		 arrangeBoard(3);
		 arrangeBoard(1);
	 }
			
}
