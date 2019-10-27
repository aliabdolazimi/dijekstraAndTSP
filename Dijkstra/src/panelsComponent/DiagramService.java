package panelsComponent;

import java.awt.Color;

public interface DiagramService 
{
	public void DrawTabel(int Row,int Col);
	public void setLocationMaze(int x, int y, Color c);
	public void Clear();
	public int[] getXWall ();
	public int[] getYWall ();
	public int[] getStartPoint ();
	public int[] getEndPoint ();
	public void FillPath(int N[],Color c);
	

}
