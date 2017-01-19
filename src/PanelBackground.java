import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class PanelBackground extends JPanel implements ActionListener 
{
	
	private static final long serialVersionUID = 1L;
	int x, y;
	public Timer timer;
	private Image img1 ;
	public static int a = 0;
	
	PanelBackground(Image BackgroundImage) 
	{
		//setBackground(Color.BLUE);
		//setSize(I.getWidth(null), I.getHeight(null));
		this.img1 = BackgroundImage;
		
		x = -330;
		y = 0;
		timer = new Timer(1, this);
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
	}
}
	