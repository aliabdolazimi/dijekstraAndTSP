package panelsComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


import javax.swing.BorderFactory;
import javax.swing.JComponent;

import shapeComponent.gRect;
import shapeComponent.gShape;



public class gDiagramPanel extends JComponent implements MouseListener,MouseMotionListener,DiagramService
{
	
	private int Width,Height;
	private Color color;
	
	private int X1,Y1;
	private boolean fill=true;
	
	
	private int row=0,col=0;
	private gShape shape[][] = new gShape[1000][1000];
	gShape x = null;
	
	public gDiagramPanel(int W,int H,Color c)
	{
		this.Width=W;
		this.Height=H;
		this.color=c;
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(Width,Height));
		this.setBackground(color);
		this.setLayout(new FlowLayout());
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setBorder(BorderFactory.createBevelBorder(1,Color.black,Color.black));
	}
	
	//************************************************* paintComponent
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		gInitialize(g);
		Graphics2D g2 = (Graphics2D) g;
		if(fill)
		{
			g2.setColor(Color.cyan);
			g2.setFont(new Font("Serif",Font.BOLD,70));
			g2.drawString("Welcome",90,100);
			g2.drawString("To",190,250);
			g2.drawString("Our Project",10,400);
		}
		
		int i,j;
		for(i=0;i<row;i++)
			for(j=0;j<col;j++)
				shape[i][j].Draw(g2);

	}
	
	//************************************************* gInitialize
	private void gInitialize(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(1, 1, getWidth() - 2,getHeight()-2);
		
		g.setColor(Color.black);
		g.drawRect(1,1,getWidth()-2,getHeight()-2);
		
	}
	

	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ DiagramService
	
	//************************************************* DrawTable
	@Override
	public void DrawTabel(int Row, int Col) 
	{
		this.fill=false;
		int i,j;
		int x1=0,y1=0,x2=getWidth()/Row,y2=getWidth()/Col;
		for(i=0;i<Row;i++)
		{
			for(j=0;j<Col;j++)
			{
				x=new gRect(x1,y1,x2,y2,1,Color.black,1,false,Color.black);
				shape[i][j]=x;
				x1+=getWidth()/Col;
				x2+=getWidth()/Col;
			}
			
			y1+=getWidth()/Row;
			y2+=getWidth()/Row;
			x1=0;
			x2=getWidth()/Row;	
		}	
		this.row=Row;
		this.col=Col;

		this.repaint();
	}

	//************************************************* SetLocationMaze
	@Override
	public void setLocationMaze(int x, int y, Color c) 
	{
		((gRect) shape[x][y]).setFilled(true);
		((gRect) shape[x][y]).setRectColor(c);
		
		this.repaint();
	}

	//************************************************* getXLocationWall
	@Override
	public int[] getXWall() 
	{
		int Wall[]=new int[20000];
		int cW=1;
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
				if(((gRect) shape[i][j]).getIsFilled() &&((gRect) shape[i][j]).getRectcolor()==Color.green)
					Wall[cW++]=i;
		Wall[0]=cW;
		return Wall;
	}

	//************************************************* getYLocationWall
	@Override
	public int[] getYWall() 
	{
		int Wall[]=new int[20000];
		int cW=1;
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
				if(((gRect) shape[i][j]).getIsFilled() &&((gRect) shape[i][j]).getRectcolor()==Color.green)
					Wall[cW++]=j;
		Wall[0]=cW;
		return Wall;
	}

	//************************************************* StartPointLocation
	@Override
	public int[] getStartPoint()
	{
		int Start[]=new int[2];
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
				if(((gRect) shape[i][j]).getIsFilled() &&((gRect) shape[i][j]).getRectcolor()==Color.red)
				{
					Start[0]=i;
					Start[1]=j;
				}
	
		return Start;
	}

	//************************************************* EndPointLocation
	@Override
	public int[] getEndPoint() 
	{
		int End[]=new int[2];
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
				if(((gRect) shape[i][j]).getIsFilled() &&((gRect) shape[i][j]).getRectcolor()==Color.blue)
				{
					End[0]=i;
					End[1]=j;
				}
	
		return End;
	}

	//************************************************* Path of problem
	@Override
	public void FillPath(int[] N, Color c)
	{
		int i=N[0];
		int j=N[1];
		((gRect) shape[i][j]).setFilled(true);
		((gRect) shape[i][j]).setBackcolor(c);
		repaint();
	}
	
	//************************************************* Clear
	@Override
	public void Clear() 
	{
		row=0;
		col=0;
		repaint();
	}
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ MouseMotionListener
	@Override
	public void mouseDragged(MouseEvent e) 
	{
        /////////////////////////////////Wall////////////////////////////////////////
		X1=e.getX();
		Y1=e.getY();
		int  i,j;
		for(i=0;i<row;i++)
			for(j=0;j<col;j++)
			{
				if(((gRect) shape[i][j]).Contains(X1, Y1)&&((gRect) shape[i][j]).getFilled()==false)
				{
					((gRect) shape[i][j]).setFilled(true);
					((gRect) shape[i][j]).setRectColor(Color.green);
					((gRect) shape[i][j]).setBordercolor(Color.black);;
				}
			}

		this.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		
		
	}
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ MouseListener
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		///////////////////////////////////////RemoveWall/////////////////////////////
		X1=e.getX();
		Y1=e.getY();
		int  i,j;
		for(i=0;i<row;i++)
			for(j=0;j<col;j++)
				if(((gRect) shape[i][j]).Contains(X1, Y1)&&((gRect) shape[i][j]).getFilled()==true)
				{
					((gRect) shape[i][j]).setFilled(false);
					((gRect) shape[i][j]).setRectColor(Color.white);
					
				}
					
	
		this.repaint();
	
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		
	}
	@Override
	public void mouseExited(MouseEvent e) 
	{
		
	}
	@Override
	public void mousePressed(MouseEvent e) 
	{

	}
	@Override
	public void mouseReleased(MouseEvent e) 
	{
	
	}
}
