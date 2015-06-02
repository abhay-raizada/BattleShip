import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class battleShip extends Canvas implements MouseListener {
	JPanel panel;

	int height= 600;
	int width = 800;
	boolean running =false;
	AI ai = new AI();
	Player p = new Player(ai);
	BufferStrategy strategy ;
	boolean menu =true;
	boolean setBoard = true;
	Rectangle StartGame = new Rectangle(365,500,100,30);
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
		addMouseListener(this);
		container.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	public static void main(String[] args){
		battleShip game =  new battleShip();
		game.run();
	}
	public void drawMenu(Graphics2D g1){
		
		g1.setColor(new Color(40, 71, 88));
    	g1.fillRect(0,0,800,600);
    	g1.setColor(new Color(255, 255, 255));
    	g1.fill(StartGame);
    	g1.setColor(new Color(40, 71, 88));
    	g1.setFont(new Font("Arial",Font.BOLD,15));
    	g1.drawString("Start Game", 365, 520);
	}
    public void run(){
  
    	try{
    		while(true){
    			createBufferStrategy(2);
				strategy = getBufferStrategy();
				Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
				update();
    			render(g);
    			Thread.sleep(10);
    			g.dispose();
				strategy.show();
    		}
    	}catch (InterruptedException e){
    		
    	}
    	
    }
    public void update(){
    
    }
    public void render(Graphics2D g){
    	if(menu){
			drawMenu(g);
		}
		else if(running){
			drawRunning(g);
		}
    }
    public void drawRunning(Graphics2D g){
    	g.setColor(new Color(40, 71, 88));
		g.fillRect(0,0,800,600);
		g.setColor(new Color(255, 255, 255));
		g.fillRect(width/2, 0, 20, height);
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.setColor(new Color(255, 255, 255));
		g.drawString("Player Board", 75, 30);
		g.drawString("Opponent Board", 475, 30);
		ai.draw(g, 475, 150, 50, 50, 10);
		//System.out.println("YO!");
		
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
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x=e.getX();
		int y=e.getY();
		if(menu)
		{
			menuClick(x,y);
		}
		else if(running)
		{	
		    shootClick(x,y);
		}
	    //System.out.print("mouseClicked!");
	}
	public void shootClick(int x,int y){
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				if(ai.rect[i][j].contains(x,y)){
					p.shoot(ai,i,j);
				}
			}
		}
	}
	public void menuClick(int x, int y){
		if(StartGame.contains(x,y)){
			menu=false;
			running = true;

		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}

   
