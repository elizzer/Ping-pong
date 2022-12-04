
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle {
	static int WIDTH;
	static int HEIGHT;
	int p1;
	int p2;
	Score(int w,int h){
		Score.HEIGHT=h;
		Score.WIDTH=w;
	}
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.PLAIN,60));
		g.drawLine(WIDTH/2,0,WIDTH/2,HEIGHT);
		g.drawString(String.valueOf(p1), WIDTH/2-100, 50);
		g.drawString(String.valueOf(p2), WIDTH/2+100, 50);
		System.out.println("Score "+p1+" "+p2);
	}
}
