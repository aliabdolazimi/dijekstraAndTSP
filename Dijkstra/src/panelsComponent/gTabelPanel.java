package panelsComponent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import containerComponent.gButton;
import containerComponent.gLabel;
import containerComponent.gTextField;
import gui.MainWindow;
import gui.TSPProblem;
import java.awt.Dimension;
import javax.swing.JFrame;

public class gTabelPanel extends AbstractPanel{
	
	private gLabel row,column,Tabel;
        public MainWindow s;
	private gTextField T1,T2;

	private int cDP=500;
	private gDiagramPanel DP;

	private gButton OK;
	public gTabelPanel(int w,int h,Color c)
	{
		super(w,h,c);
		Tabel=new gLabel(375,20,"                           TSP Algorythm",Color.orange,15);
		
		OK=new gButton(120,30,"TSP",Color.orange);
	
                OK.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
                               JFrame j = new JFrame();
                                j.setTitle("Path finding by TSP Algorithm");
                                j.setSize(new Dimension(900, 600));
                                j.add(new TSPProblem());
                                j.setVisible(true);
                                s.EXIT();
                        }
		});
		this.add(Tabel);
		this.add(OK);
                
	}
	
	public void sendTabel(gDiagramPanel DP)
	{
		this.DP=DP;
	}
	
	private boolean isCorrectNum(String Number)
	{
		int i;
		if(Number.length()==0)
			return false;
		for(i=0;i<Number.length();i++)
			if(!(Number.charAt(i)>='0'&&Number.charAt(i)<='9'))
					return false;
		return true;
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
