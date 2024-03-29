/*
    Path finding by Dijkstra algorithm
    Ali Abdolazimi
    Mohammad Amirdoost
    Sajjad Moghayyad
*/

package gui;

import algo.DPAlgorithmTSP;
import models.UndirectedGraph;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TSPProblem extends JPanel {

    private UndirectedGraph graph;
    private UndirectedGraphPanel graphPanel;

    public TSPProblem(){
        super.setLayout(new BorderLayout());
        setGraphPanel();
    }

    private void setGraphPanel(){
        graph = new UndirectedGraph();
        graphPanel = new UndirectedGraphPanel(graph);
        graphPanel.setPreferredSize(new Dimension(4096, 4096));

        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(graphPanel);
        scroll.setPreferredSize(new Dimension(750, 500));
        scroll.getViewport().setViewPosition(new Point(0, 0));
        add(scroll, BorderLayout.CENTER);
        setTopPanel();
        setButtons();
    }

    private void setTopPanel() {
        JLabel info = new JLabel("DP TSP Visualiser by MAS");
        info.setForeground(new Color(230, 220, 250));
        JPanel panel = new JPanel();
        panel.setBackground(new Color(130, 50, 250));
        panel.add(info);
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(panel, BorderLayout.NORTH);
    }

    private void setButtons(){
        JButton run = new JButton();
        setupIcon(run, "run1");
        JButton reset = new JButton();
        setupIcon(reset, "reset");
        final JButton info = new JButton();
        setupIcon(info, "info");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(PathFindingDrawUtils.parseColor("#DDDDDD"));
        buttonPanel.add(reset);
        buttonPanel.add(run);
        buttonPanel.add(info);

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.reset();
            }
        });

        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Click on empty space to create new node\n" +
                        "Drag from node to node to create an edge\n" +
                        "Click on edges to set the weight\n\n" +
                        "Combinations:\n" +
                        "Right Click       :    Set node as source\n" +
                        "Ctrl  + Drag               :    Reposition Node\n" +
                        "Ctrl  + Shift + Click   :    Delete Node/Edge\n");
            }
        });

        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DPAlgorithmTSP DPAlgorithmTSP = new DPAlgorithmTSP(graph);
                try{
                    DPAlgorithmTSP.run();
                    graphPanel.setPath(DPAlgorithmTSP.getPath());
                } catch (IllegalStateException ise){
                    JOptionPane.showMessageDialog(null, ise.getMessage());
                }
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupIcon(JButton button, String img){
        try {
            Image icon = ImageIO.read(getClass().getResource(
                    "/resources/" + img + ".png"));
            ImageIcon imageIcon = new ImageIcon(icon);
            button.setIcon(imageIcon);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
