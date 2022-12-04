
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable,KeyListener{
	
	static final int WIDTH=1000;
	static final int HEIGHT = (int) (WIDTH*(0.5555));
	static final Dimension SCREEN = new Dimension(WIDTH,HEIGHT);
	static final int BALL_DIAMETER=20;
	static final int PADEL_HEIGHT=100;
	static final int PADEL_WIDTH=25;
	static boolean GAME_OVER=false;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Padel padel1;
	Padel padel2;
	Ball ball;
	Score score;

	
	GamePanel(){
		newPadel();
		newBall();
		score = new Score(WIDTH,HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(this);
		this.setPreferredSize(SCREEN);
		gameThread= new Thread(this);
		gameThread.start();
	}
	public void newBall(){
		ball = new Ball((WIDTH/2)-BALL_DIAMETER/2,HEIGHT/2-BALL_DIAMETER/2,BALL_DIAMETER);
	}
	public void newPadel(){
		padel1 = new Padel(0,(HEIGHT/2)-(PADEL_HEIGHT/2),PADEL_WIDTH,PADEL_HEIGHT,1);
		padel2 = new Padel(WIDTH-(PADEL_WIDTH),(HEIGHT/2)-(PADEL_HEIGHT/2),PADEL_WIDTH,PADEL_HEIGHT,2);
	}
	public void paint(Graphics g){
		// System.out.println("paint");
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0,0,null);
	}
	public void draw(Graphics g){
		padel1.draw(g);
		padel2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	public void move(){
		padel1.move();
		padel2.move();
		ball.move();
	}
	public void checkCollision(){
		//check collision for the padels
		if(padel1.y<=0){
			padel1.y=0;
		}
		else if(padel1.y>=HEIGHT-padel1.height){
			padel1.y=HEIGHT-padel1.height;
		}
		if(padel2.y<=0){
			padel2.y=0;
		}
		else if(padel2.y>=HEIGHT-padel2.height){
			padel2.y=HEIGHT-padel2.height;
		}

		//check the collision for the ball

		if(ball.y<0 || ball.y>(HEIGHT-BALL_DIAMETER)){
			ball.yvel=-1*ball.yvel;
		}
		else if(ball.intersects(padel1)){
			ball.xvel=-1*ball.xvel;
		}
		else if(ball.intersects(padel2)){
			ball.xvel=-1*ball.xvel;
		}

		//check for win and reset the game
		if(ball.x<0){
			score.p2++;
			newBall();
			newPadel();
			try{
				Thread.sleep(200);
			}
			catch(InterruptedException e){

			}
		}
		else if(ball.x>WIDTH-BALL_DIAMETER){
			score.p1++;
			newBall();
			newPadel();
			try{
				Thread.sleep(200);
			}
			catch(InterruptedException e){
				
			}
		}
	}
	public void run(){
		long prevTime = System.nanoTime();
		double fps = 60.0;
		double ns = 1000000000/fps;
		double delta =0;
		while(!GAME_OVER){
			long now = System.nanoTime();
			delta +=(now-prevTime)/ns;
			prevTime=now;
			if(delta>=1){
				move();
				checkCollision();
				repaint();
				delta--;
				// System.out.println("Test");
			}
		}

	}

	// public class AL extends KeyAdapter{
	// 	public void KeyPressed(KeyEvent e){
	// 	System.out.println("move");

	// 		padel1.KeyPressed(e);
	// 		padel2.KeyPressed(e);
	// 	}
	// 	public void KeyReleased(KeyEvent e){
	// 		padel1.KeyReleased(e);
	// 		padel2.KeyReleased(e);
	// 	}
	// }	
	
	public void keyPressed(KeyEvent e){
		System.out.println("Key pressed");
		padel1.KeyPressed(e);
		padel2.KeyPressed(e);
	}
	public void keyReleased(KeyEvent e){
		System.out.println("Key realeased");
		padel1.KeyReleased(e);
		padel2.KeyReleased(e);
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
