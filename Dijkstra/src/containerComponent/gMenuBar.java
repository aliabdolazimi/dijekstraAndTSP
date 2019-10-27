package containerComponent;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenuBar;

public class gMenuBar extends JMenuBar{
	
	private int Width,Height;
	private Color color;
	
	public gMenuBar(int W,int H,Color c)
	{
		this.Width=W;
		this.Height=H;
		this.color=c;
		
		this.setPreferredSize(new Dimension(Width,Height));
		this.setBackground(color);
		this.setOpaque(true);
	}
}
