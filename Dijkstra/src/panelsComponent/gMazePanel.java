package panelsComponent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import containerComponent.gButton;
import containerComponent.gLabel;
import containerComponent.gTextField;
import gui.MainWindow;
import gui.PathFindingProblem;
import gui.TSPProblem;
import java.awt.Dimension;
import javax.swing.JFrame;

public class gMazePanel extends AbstractPanel{
	
	private gLabel mouse,cheese,Maze;
	private gTextField T1,T2;
         public MainWindow s;
	private gButton OK;
	
	private gDiagramPanel DP;
	public gMazePanel(int w,int h,Color c)
	{
		super(w,h,c);
		
		Maze=new gLabel(370,20,"                         dijekstra Algorythm",Color.orange,15);
		
		
	
		
		OK=new gButton(120,30,"Dijekstra",Color.orange);
		OK.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{   
                             JFrame j = new JFrame();
                                j.setTitle("Path finding by Dijkstra Algorithm");

                                j.setSize(new Dimension(900, 600));
                                j.add(new PathFindingProblem());
                                j.setVisible(true);
                                s.EXIT();
                            
					}
		});
		
		this.add(Maze);
		
		
		this.add(OK);
                  
	}
	

	private boolean isCorrectNum(String Number)
	{
		int i;
		if(Number.length()==0 || Number.length()==1)
			return false;
		if(Number.charAt(0)==',' && Number.charAt(Number.length()-1)==',')
			return false;
		for(i=0;i<Number.length();i++)
			if(!(Number.charAt(i)>='0'&&Number.charAt(i)<='9' ||Number.charAt(i)==','))
					return false;
		return true;
	}


	public void sendMaze(gDiagramPanel diagram) {
		this.DP=diagram;
		
	}


	public gTextField getT1() {
		return T1;
	}


	public void setT1(gTextField t1) {
		T1 = t1;
	}


	public gTextField getT2() {
		return T2;
	}


	public void setT2(gTextField t2) {
		T2 = t2;
	}
	
	


}
