package containerComponent;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenu;

public class gMenu extends JMenu{
	
	private int Width,Height;
	private Color color;
	private String Name;
	
	public gMenu(int W,int H,Color c)
	{
		this.Width=W;
		this.Height=H;
		this.color=c;
		
		this.setPreferredSize(new Dimension(Width,Height));
		this.setBackground(color);
		this.setOpaque(true);
	}
	
	public gMenu(int W,int H,String N,Color c)
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
	
	public void setTextToMenu(String S)
	{
		this.setText(S);
	}
	
	public void setColorToMenu(Color c)
	{
		this.setBackground(c);
	}

}
