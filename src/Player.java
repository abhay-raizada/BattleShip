import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player {
	AI ai;
	int shotsHit=0;
	int[][] board = new int[5][5];
	Rectangle[][] selectBoard = new Rectangle[5][5];
	Rectangle Play = new Rectangle(400,500,70,30);
	boolean playable=false;
	boolean[] ShipSelected = new boolean[3];
	boolean[] ShipDrawn = new boolean[3];
	boolean[] ShipOrient = new boolean[3];
	Rectangle[][] rect = new Rectangle[5][5];
	Rectangle[] Ships = new Rectangle[3];
	Rectangle[] setOrient = new Rectangle[3];
	public Player (AI ai){
		
		this.ai =ai;
		initBoard();
	}
	public void Hit(){
		shotsHit++;
	}
	public void initBoard(){
        for(int row=0 ; row < 5 ; row++ )
            for(int column=0 ; column < 5 ; column++ )
                board[row][column]=-1;
        for(int i=0;i<3;i++){
			Ships[i]= new Rectangle(50,50+35*i,15,15);
		}
        for(int i=0;i<3;i++){
			setOrient[i]= new Rectangle(200,50+35*i,15,15);
		}
        for(int i=0;i<3;i++){
			ShipOrient[i]= false;
		}
        for(int i=0;i<3;i++){
			ShipDrawn[i]= false;
		}

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
		g.setColor(new Color(40, 71, 88));
		g.fillRect(0,0,800,600);
		for(int i=0;i<3;i++){
			g.setColor(Color.RED);
			g.fill(Ships[i]);
			g.setColor(Color.WHITE);
			g.drawString("Ship of length"+(5-2*i),80 ,62+35*i );
		}
		for(int i=0;i<3;i++){
			if(ShipSelected[i]==true){
				if(ShipOrient[i] == true){
					g.setColor(Color.WHITE);
					g.fill(setOrient[i]);
					g.drawString("Horizontal", 220,62+35*i );
				}
				else{
					g.setColor(Color.BLACK);
					g.fill(setOrient[i]);
					g.setColor(Color.WHITE);
					g.drawString("Vertical", 220,62+35*i );
				}
			}
		}
		if(ShipSelected[0]||ShipSelected[1]||ShipSelected[2]){
			drawSelectionBoard(g, 175, 150, 50, 50, 10,true);
		}
		else{
			drawSelectionBoard(g, 175, 150, 50, 50, 10,false);
		}
		if(gamePlayable()){
			g.setColor(new Color(255, 255, 255));
			g.fill(Play);
			g.setColor(new Color(40, 71, 88));
		 	g.setFont(new Font("Arial",Font.BOLD,20));
			g.drawString("PLAY", 405, 520);
			
		}
		
		
	}
	private boolean gamePlayable() {
		int flag=0;
		for(int i=0;i<3;i++){
			if(ShipDrawn[i])
				flag++;
		}
		if(flag==3){
			//System.out.println("true");
			playable=true;
			return true;
			
		}
		else{
			//System.out.println("false");
			//System.out.println("Ship1:"+Boolean.toString(ShipDrawn[0])+"Ship2:"+Boolean.toString(ShipDrawn[1])+"Ship1:"+Boolean.toString(ShipDrawn[2]));
			return false;
		}	
	}
	public void arrangeBoard(int j,boolean orientation,int x,int y){
		int length=0;
		if(ShipDrawn[j]!=true){
			if(j==0){
				length =5;
				
			}
			if(j==1){
				length =3;
				
			}
			else if(j==2){
				length =1;
				
			}
			
			if(!shipObstruct(x,y,orientation,length)){
				if(orientation){
					//Horizontal
					for(int i=x;i<x+length;i++){
						ShipDrawn[j]=true;
						board[i][y]=1;
					}
				}
				if(!orientation){
				//Vertical
					for(int i=y;i<y+length;i++){
						ShipDrawn[j]=true;
						board[x][i]=1;
					}
				}
			}
		}
		//System.out.println("Arrange");
		//System.out.println(board[x][y]);
	}

	public void shoot(AI ai,int x,int y){
		//Get Input for x and y, the coordinates to shoot at
		if(ai.board[x][y]==1){
			ai.board[x][y]=0;
			Hit();
		}
		else if(ai.board[x][y]==-1){
			ai.board[x][y]=2;
		}
		//System.out.println(x+","+y+" shot!");
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
	public void drawSelectionBoard(Graphics2D g,int boardX,int boardY,int cellW,int cellH,int padding,boolean selected){
		 if(selected){
		     for(int row=0 ; row < 5 ; row++ ){
	             for(int column=0 ; column < 5 ; column++ ){
	                if(board[row][column]==-1){
	                	g.setColor(Color.YELLOW);
	                	selectBoard[row][column] = new Rectangle(boardX+row*(cellW+padding),boardY+column*(cellH+padding),cellW,cellH);
	                    g.fill(rect[row][column]);
	                
	                
	                }
	                if(board[row][column]==1){
	                	g.setColor(Color.RED);
	                	selectBoard[row][column] = new Rectangle(boardX+row*(cellW+padding),boardY+column*(cellH+padding),cellW,cellH);
	                    g.fill(rect[row][column]);
	                
	                
	                }

	            //System.out.println();
	            }
		     }
		 }
		 else{
			 for(int row=0 ; row < 5 ; row++ ){
	             for(int column=0 ; column < 5 ; column++ ){
	                if(board[row][column]==-1){
	                	g.setColor(Color.PINK);
	                	rect[row][column] = new Rectangle(boardX+row*(cellW+padding),boardY+column*(cellH+padding),cellW,cellH);
	                    g.fill(rect[row][column]);
	                }
		
	             }
			 }
		 }
	}
}

