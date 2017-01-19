import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MovingCarInClient extends JPanel implements ActionListener 
{
	int x, y;
	public Timer timer;
	private Image img1,img2 ;
	public static int a = 0;
	
	MovingCarInClient(Image BackgroundImage,Image MovingImage) 
	{
		//setBackground(Color.BLUE);
		//setSize(I.getWidth(null), I.getHeight(null));
		this.img1 = BackgroundImage;
		this.img2 = MovingImage;
		x = -330;
		y = 0;
		timer = new Timer(5, this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(x==-330)
			timer.stop();
			x += 1;
		    y += 1;
		
		if(x==80)
		{
			timer.stop();
			
		}
		
		//repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img1, 0, 0, null);
		g.drawImage(img2, x, 260, null);
		
	}
}
	