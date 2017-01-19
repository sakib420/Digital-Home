import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MovingClassInClient extends JPanel implements ActionListener 
{
	static int x, y;
	public Timer timer;
	private Image img1,img2 ;
	public static int a = 0;
	
	MovingClassInClient(Image I,Image M) 
	{
		//setBackground(Color.BLUE);
		//setSize(I.getWidth(null), I.getHeight(null));
		this.img1 = I;
		this.img2 = M;
		x = -180;
		y = 0;
		timer = new Timer(10, this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(a==0)
		{
			x += 1;
		y += 1;
		}
		else
			x--;
		if(x==-40)
		{
			x--;
			a=1;
			//timer.stop();
		}
		if(x==-180)
			a=0;
		repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img1, 0, 0, null);
		g.drawImage(img2, x, 20, null);
		
	}
}
	