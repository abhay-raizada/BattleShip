import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player {
	AI ai;
	Ship ship1 = new Ship(1);
	Ship ship2 = new Ship(3);
	Ship ship3 = new Ship(5);
	int[][] board = new int[5][5];
	Rectangle[][] rect = new Rectangle[5][5];
	Rectangle[] Ships = new Rectangle[3];
	public Player (AI ai){
		
		this.ai =ai;
		initShips();
	}
	public void initShips(){
		for(int i=0;i<3;i++){
			Ships[i]= new Rectangle(100,100+10*i,10,10);
		}
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
	public void drawSelection(Graphics2D g){
		g.fill(Ships[0]);
		g.fill(Ships[1]);
		g.fill(Ships[2]);
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
	public void shoot(AI ai,int x,int y){
		//Get Input for x and y, the coordinates to shoot at
		if(ai.board[x][y]==1)
			ai.board[x][y]=0;
		else if(ai.board[x][y]==-1){
			ai.board[x][y]=2;
		}
		System.out.println(x+","+y+" shot!");
	}
	public void draw(Graphics2D g,int boardX,int boardY,int cellW,int cellH,int padding){
		 
		 for(int row=0 ; row < 5 ; row++ ){
	            
	            for(int column=0 ; column < 5 ; column++ ){
	                if(board[row][column]==-1){
	                	g.setColor(Color.YELLOW);
	                	rect[row][column] = new Rectangle(boardX+row*(cellW+padding),boardY+column*(cellH+padding),cellW,cellH);
	                    g.fill(rect[row][column]);
	                }else if(board[row][column]==0){
	                	g.setColor(Color.RED);
	                	rect[row][column] = new Rectangle(boardX+row*(cellW+padding),boardY+column*(cellH+padding),cellW,cellH);
	                    g.fill(rect[row][column]);
	                    //Systeg.fillRect(boardX+row*(cellW+padding),boardY+column*(cellH+padding),cellW,cellH);m.out.print("\t"+"*");
	                }else if(board[row][column]==1){
	                	g.setColor(Color.GREEN);
	                	rect[row][column] = new Rectangle(boardX+row*(cellW+padding),boardY+column*(cellH+padding),cellW,cellH);
	                    g.fill(rect[row][column]);
	                    //System.out.print("\t"+"X");
	                }
	                else if(board[row][column]==2){
	                	g.setColor(Color.PINK);
	                	rect[row][column] = new Rectangle(boardX+row*(cellW+padding),boardY+column*(cellH+padding),cellW,cellH);
	                    g.fill(rect[row][column]);
	                    //System.out.print("\t"+"X");
	                }
	                
	            }
	            //System.out.println();
	        }
	}
		
}

