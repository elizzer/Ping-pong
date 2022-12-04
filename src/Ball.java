
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle{
	int xvel=3;
	int yvel=3;
	Random random;
	Ball(int x,int y,int dia){
		super(x,y,dia,dia);
		random = new Random();
		int randXdir = random.nextInt(2);
		if(randXdir==0) randXdir--;
		
		int randYdir = random.nextInt(2);
		if(randYdir==0) randYdir--;


	}
	public void sety(int y){
		yvel=y;
	}
	public void setx(int x){
		xvel=x;
	}
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.fillOval(x, y, width, height);

	}
	public void move(){
		x+=xvel;
		y+=yvel;
	}
}
