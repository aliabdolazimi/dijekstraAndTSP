package panelsComponent;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public abstract class AbstractPanel extends JPanel{
	
	private int Width,Height;
	private Color color;
	
	public AbstractPanel(int W,int H,Color c)
	{
		this.Width=W;
		this.Height=H;
		this.color=c;
		
		this.setPreferredSize(new Dimension(Width,Height));
		this.setBackground(color);
		this.setOpaque(true);
	}

}
