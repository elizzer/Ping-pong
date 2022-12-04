
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Padel extends Rectangle{

	int id;
	int yvel;
	int padelVel=10;

	Padel(int x,int y,int w,int h,int id){
		super(x,y,w,h);
		this.id=id;
	}

	public void KeyPressed(KeyEvent e){
		
		switch(id){
			case 1:
				if(e.getKeyCode()==KeyEvent.VK_W){
					this.setY(-padelVel);
					move();
				}
				if(e.getKeyCode()==KeyEvent.VK_S){
					this.setY(padelVel);
					move();
				}
				break;
			
			case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP){
				this.setY(-padelVel);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN){
				this.setY(padelVel);
				move();
			}
			break;
		}
	}
	public void KeyReleased(KeyEvent e){
		switch(id){
			case 1:
				if(e.getKeyCode()==KeyEvent.VK_W){
					this.setY(0);
					move();
				}
				if(e.getKeyCode()==KeyEvent.VK_S){
					this.setY(0);
					move();
				}
				break;
			
			case 2:
				if(e.getKeyCode()==KeyEvent.VK_UP){
					this.setY(0);
					move();
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN){
					this.setY(0);
					move();
				}
				break;
		}
	}
	public void setY(int y){
		
		yvel=y;
	}
	public void move(){
		y=y+yvel;
	}
	public void draw(Graphics g){
		if(id==1){
			g.setColor(Color.green);
		}
		else{
			g.setColor(Color.red);
		}

		g.fillRect(x, y, width, height);
	}

}
