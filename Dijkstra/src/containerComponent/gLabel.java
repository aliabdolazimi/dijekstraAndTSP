package containerComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

public class gLabel extends JLabel{
	
	private int Width,Height;
	private String Name;
	private Color color;
	private int font;
	
	public gLabel(int W,int H,String N,Color c,int f)
	{
		this.Width=W;
		this.Height=H;
		this.Name=N;
		this.color=c;
		this.font=f;
		
		this.setPreferredSize(new Dimension(Width,Height));
		this.setBackground(color);
		this.setOpaque(true);
		this.setFont(new Font("Tahoma",0,font));
		this.setText(Name);
	}
	
	public gLabel(int W,int H,Color c)
	{
		this.Width=W;
		this.Height=H;
		this.color=c;
		
		this.setPreferredSize(new Dimension(Width,Height));
		this.setBackground(color);
		this.setOpaque(true);
		this.setFont(new Font("Tahoma",0,13));
	}
	public gLabel(int W,int H,String N)
	{
		this.Width=W;
		this.Height=H;
		this.Name=N;
		
		this.setPreferredSize(new Dimension(Width,Height));
		this.setOpaque(true);
		this.setFont(new Font("Tahoma",0,13));
		this.setText(Name);
	}
	////////////////// setTextToLabel///////////////////////////////
	public void setTextToLabel(String S){
		this.setText(S);
	}

}
