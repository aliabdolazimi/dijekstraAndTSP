package containerComponent;

import java.awt.Dimension;

import javax.swing.JTextField;


public class gTextField extends JTextField{
	
	private int width,height;

	public gTextField(int w,int h)
	{
		this.width=w;
		this.height=h;
		this.setPreferredSize(new Dimension(width,height));
		
	}

}
