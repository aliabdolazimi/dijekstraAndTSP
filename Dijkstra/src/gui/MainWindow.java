/*
    Path finding by Dijkstra algorithm
    Ali Abdolazimi
    Mohammad Amirdoost
    Sajjad Moghayyad
*/

package gui;
import containerComponent.gMenu;
import containerComponent.gMenuBar;
import containerComponent.gMenuItem;
import panelsComponent.gDiagramPanel;
import panelsComponent.gMainPanel;
import panelsComponent.gMazePanel;
import panelsComponent.gTabelPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;

public class MainWindow extends JFrame{
        public  MainWindow s ;	
	private static  final int       Width=520;
	private static  final int       Height=690;
	
	///////////////////////////////////////Menus/////////////////////////////////////
	private gMenuBar menubar;
	private gMenu File;
	private gMenu Help;
	
	private gMenuItem New;
	private gMenuItem Start;
	private gMenuItem Exit;
	private gMenuItem About;
	private gMenuItem ClearWall;
	
	
	/////////////////////////////////////Panels///////////////////////////////////////
	private gMainPanel main;
	private gDiagramPanel Diagram;
	private gTabelPanel Tabel;
	private gMazePanel Maze;
	
	public MainWindow() 
	{
		this.setTitle("** Dijekstra And TSP Prject **");
		this.setBounds(420,25,Width,Height);
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		//************************ Panels**************************
		main=new gMainPanel(Width-20,120,new Color(0,64,0));
		Tabel=new gTabelPanel(240,110,new Color(0,128,128));
                Tabel.s = this;
		Maze=new gMazePanel(240,110,new Color(0,128,128));
	        Maze.s=this;
                Diagram=new gDiagramPanel(Width-20,Height-195,Color.white);
		
		Tabel.sendTabel(Diagram);
		Maze.sendMaze(Diagram);
		//****************************************
		
		//************************ Menu ************************** 
		menubar=new gMenuBar(Width,25,Color.white);
       		
		File=new gMenu(60,25,"File",menubar.getBackground());
	    
		Exit=new gMenuItem(100,20,Color.white,"Exit");
		Exit.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int x=JOptionPane.showConfirmDialog(null,"Do you want to Exit ????", "Exit",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (x==JOptionPane.NO_OPTION)
					return;
				System.exit(0);
			}
		});
		File.add(Exit);
		File.addSeparator();
		
		
		
		
		Help=new gMenu(60,25,"Help",menubar.getBackground());
		About=new gMenuItem(57,25,Color.white,"About");
        About.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, " Project Name : Dijekstra And TSP \n by : 1-Ali ABdolazimi \n2-Sajjad Mogayyad \n3-Mohammad AMirDost\n Professor Name : Dr.Tanha ");
			}
		});
		
		Help.add(About);
		
		menubar.add(File);
		menubar.add(Help);
		//***************************************************
		main.add(Tabel);
                
		main.add(Maze);
		this.add(main);
		this.add(Diagram);
		this.setJMenuBar(menubar);
		this.setVisible(true);
		
	}
	

	private int[] ParsToInt(String S)
	{
		int n=0,x;
		int N[]=new int[2];
		for(x=0;x<S.length();x++)
		{
			if(S.charAt(x)!=',')
				n=n*10+S.charAt(x)-'0';
			if(S.charAt(x)==',')
			{
				N[0]=n;
				n=0;
				continue;
			}
		}
		N[1]=n;
		return N;
	}
	
	private int StrToInt1(String S){
		int n=0,x;
		for(x=0;x<S.length();x++)
			n=n*10+S.charAt(x)-'0';
		return n;
	}
        public void EXIT()  {   
          this.setVisible(false);
        }
	
	///////////////////////////////////////Main///////////////////////////////////////
	public static void main(String[] args) {
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		MainWindow s = new MainWindow();
                
	}

   

}
