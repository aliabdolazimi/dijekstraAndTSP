package containerComponent;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenuItem;

public class gMenuItem extends JMenuItem{
	
	private int Width,Height;
	private Color color;
	private String Name;
	
	public gMenuItem(int W,int H,Color c,String N)
	{
		this.Width=W;
		this.Height=H;
		this.color=c;
		this.Name=N;
		
		
		this.setPreferredSize(new Dimension(Width,Height));
		this.setBackground(color);
		this.setText(Name);
		this.setOpaque(true);
		
	}
	
	public gMenuItem(int W,int H,Color c)
	{
		this.Width=W;
		this.Height=H;
		this.color=c;
		
		this.setPreferredSize(new Dimension(Width,Height));
		this.setBackground(color);
		this.setOpaque(true);
		
	}
	
	//////////////////// setTextToItem ///////////////////////////
	public void setTextToItem(String S)
	{
		this.setText(S);
	}

}
