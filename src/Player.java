
public class Player {
	AI ai;
	Ship ship1 = new Ship(1);
	Ship ship2 = new Ship(3);
	Ship ship3 = new Ship(5);
	int[][] board = new int[5][5];
	public Player (AI ai){
		
		this.ai =ai;
	}
	public void initBoard(int[][] board){
        for(int row=0 ; row < 5 ; row++ )
            for(int column=0 ; column < 5 ; column++ )
                board[row][column]=-1;
    }
	private boolean shipObstruct(int x, int y, boolean orientation, int length) {
		 if(orientation){
			 if(x+length<=5){
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
			if(y+length<=5){
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
	public void arrangeBoard(int length){
		boolean orientation = getOrientation();
		//get orientation
		int x,y;
		//get x,y
		x=getRow();
		y=getColumn();
		while(shipObstruct(x,y,orientation,length)){
			//get x,y again
			//get orientation again
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
	private int getColumn() {
		// TODO Auto-generated method stub
		return 0;
	}
	private int getRow() {
		// TODO Auto-generated method stub
		return 0;
	}
	private boolean getOrientation() {
		// TODO Auto-generated method stub
		return false;
	}
	public void shoot(AI ai){
		int x=-1,y=-1;
		//Get Input for x and y, the coordinates to shoot at
		ai.board[x][y]=0;
	}
	
		
}

