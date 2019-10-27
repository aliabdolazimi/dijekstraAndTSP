package containerComponent;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class gButton extends JButton
{
	
	private int width,height;
	private String Name;
	private  Color color;
	
	public gButton(int w,int h,String N,Color c)
	{
		this.width=w;
		this.height=h;
		this.Name=N;
		this.color=c;
		
		this.setPreferredSize(new Dimension(width, height));
		this.setText(Name);
		this.setBackground(color);
		this.setOpaque(true);
		
	}
	
	public gButton(int w,int h,String N)
	{
		this.width=w;
		this.height=h;
		this.Name=N;
		
		this.setPreferredSize(new Dimension(width, height));
		this.setText(Name);
		this.setOpaque(true);
		
	}

}
