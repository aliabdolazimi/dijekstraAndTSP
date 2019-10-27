/*
    Path finding by Dijkstra algorithm
    Ali Abdolazimi
    Mohammad Amirdoost
    Sajjad Moghayyad
*/

import gui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {  }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        JFrame j = new JFrame();
        j.setTitle("Path finding by Dijkstra Algorithm");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setSize(new Dimension(500, 187));
        j.setLocation((screenSize.width-187)/2, (screenSize.height-500)/2);
        j.setResizable(false);
        j.add(new MainWindow());
        j.setVisible(true);
    }
}
