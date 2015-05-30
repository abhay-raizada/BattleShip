import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class battleShip extends Canvas {
	JFrame frame;
	JPanel panel;
	BufferStrategy strategy ;
	int height= 600;
	int width = 800;
	boolean running =false;
	AI ai = new AI();
	public battleShip(){
		JFrame container = new JFrame("Battleship");
		panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(1200,height));
		panel.setLayout(null);
		setBounds(0,0,width,height);
		panel.add(this);
		setIgnoreRepaint(true);
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
	}
    public static void main(String[] args) {
       
        //System.out.println();
        //showBoard(ai.board);
        battleShip game = new battleShip();
        game.run();
    }
    public void run(){
    	createBufferStrategy(2);
		strategy = getBufferStrategy();
    	Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
    	g.setColor(new Color(40, 71, 88));
    	g.fillRect(0,0,800,600);
    	g.setColor(new Color(15, 82, 20));
    	g.fillRect(width/2, 0, 20, height);
    	g.setFont(new Font("Arial",30, 30));
    	g.setFont(new Font("Arial",Font.BOLD,30));
    	g.setColor(Color.GREEN);
    	g.drawString("Player Board", 75, 30);
    	g.drawString("Opponent Board", 475, 30);
    	drawBoardPlayer(g);
    	drawBoardOpponent(g,ai);
    	while(running){
    		
    	}
    	g.dispose();
    	strategy.show();
    }
    
    
    
    private void drawBoardOpponent(Graphics2D g,AI ai) {
    	int boardX=475,boardY=150,cellW=50,cellH=50,padding=10;
    	for(int row=0 ; row < 5 ; row++ ){
            
            for(int column=0 ; column < 5 ; column++ ){
                if(ai.board[row][column]==-1){
                	g.setColor(Color.YELLOW);
                    g.fillRect(boardX+row*(cellW+padding),boardY+column*(cellH+padding),cellW,cellH);
                }else if(ai.board[row][column]==0){
                	g.setColor(Color.RED);
                	g.fillRect(boardX+row*(cellW+padding),boardY+column*(cellH+padding),cellW,cellH);
                    //System.out.print("\t"+"*");
                }else if(ai.board[row][column]==1){
                	g.setColor(Color.GREEN);
                    g.fillRect(boardX+row*(cellW+padding),boardY+column*(cellH+padding),cellW,cellH);
                    //System.out.print("\t"+"X");
                }
                
            }
            System.out.println();
        }

	}
	private void drawBoardPlayer(Graphics2D g) {
		
    }
	public static void showBoard(int[][] board){
        System.out.println("\t1 \t2 \t3 \t4 \t5");
        System.out.println();
        
        for(int row=0 ; row < 5 ; row++ ){
            System.out.print((row+1)+"");
            for(int column=0 ; column < 5 ; column++ ){
                if(board[row][column]==-1){
                    System.out.print("\t"+"~");
                }else if(board[row][column]==0){
                    System.out.print("\t"+"*");
                }else if(board[row][column]==1){
                    System.out.print("\t"+"X");
                }
                
            }
            System.out.println();
        }

    }


}

   
