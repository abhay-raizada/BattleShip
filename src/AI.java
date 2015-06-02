import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class AI {
	 int shotsHit=0;
	 int[][] board = new int[5][5];
	 Random r = new Random();
	 Player p;
	 int width=5, height =5;
	 Rectangle[][] rect = new Rectangle[5][5];
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
	public void Hit(){
		shotsHit++;
	}
	public void shoot(Player p){
		    int x=r.nextInt(5),y=r.nextInt(5);
		    try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    while(p.board[x][y]==0||p.board[x][y]==2){
		    	x=r.nextInt(5);y=r.nextInt(5);
		    }
		    
		    if(p.board[x][y]==1){
				p.board[x][y]=0;
		    	Hit();
		    }	
			else if(p.board[x][y]==-1){
				p.board[x][y]=2;
			}
		}
	 public AI(){
		 initBoard();
		 arrangeBoard(5);
		 arrangeBoard(3);
		 arrangeBoard(1);
		 for(int i=0;i<5;i++){
			 for(int j=0;j<5;j++){
				 
			 }
		 }
		 
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
	                	g.setColor(Color.YELLOW);
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
