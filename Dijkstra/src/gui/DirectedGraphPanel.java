/*
    Path finding by Dijkstra algorithm
    Ali Abdolazimi
    Mohammad Amirdoost
    Sajjad Moghayyad
*/

package gui;

import models.DirectedEdge;
import models.DirectedGraph;
import models.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;


public class DirectedGraphPanel extends JPanel implements MouseListener, MouseMotionListener {

    private PathFindingDrawUtils drawUtils;

    private DirectedGraph graph;

    private Node selectedNode = null;
    private Node hoveredNode = null;
    private DirectedEdge hoveredEdge = null;

    private java.util.List<Node> path = null;

    private Point cursor;

    public DirectedGraphPanel(DirectedGraph graph){
        this.graph = graph;

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setPath(List<Node> path) {
        this.path = path;
        hoveredEdge = null;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        drawUtils = new PathFindingDrawUtils(graphics2d);

        if(graph.isSolved()){
            drawUtils.drawPath(path);
        }

        if(selectedNode != null && cursor != null){
            DirectedEdge e = new DirectedEdge(selectedNode, new Node(cursor));
            drawUtils.drawEdge(e);
        }

        for(DirectedEdge edge : graph.getEdges()){
            if(edge == hoveredEdge)
                drawUtils.drawHoveredEdge(edge);
            drawUtils.drawEdge(edge);
        }

        for(Node node : graph.getNodes()){
            if(node == selectedNode || node == hoveredNode)
                drawUtils.drawHalo(node);
            if(graph.isSource(node))
                drawUtils.drawSourceNode(node);
            else if(graph.isDestination(node))
                drawUtils.drawDestinationNode(node);
            else
                drawUtils.drawNode(node);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        Node selected = null;
        for(Node node : graph.getNodes()) {
            if(PathFindingDrawUtils.isWithinBounds(e, node.getCoord())){
                selected = node;
                break;
            }
        }

        if(selected!=null) {
            if(e.isControlDown() && e.isShiftDown()){
                graph.deleteNode(selected);
                graph.setSolved(false);
                repaint();
                return;
            } else if(e.isControlDown() && graph.isSolved()){
                path = selected.getPath();
                repaint();
                return;
            } else if(e.isShiftDown()){
                if(SwingUtilities.isLeftMouseButton(e)){
                    if(!graph.isDestination(selected))
                        graph.setSource(selected);
                    else
                        JOptionPane.showMessageDialog(null, "Destination can't be set as Source");
                } else if(SwingUtilities.isRightMouseButton(e)) {
                    if(!graph.isSource(selected))
                        graph.setDestination(selected);
                    else
                        JOptionPane.showMessageDialog(null, "Source can't be set as Destination");
                }else
                    return;

                graph.setSolved(false);
                repaint();
                return;
            }
        }

        if(hoveredEdge!=null){
            if(e.isControlDown() && e.isShiftDown()){
                graph.getEdges().remove(hoveredEdge);
                hoveredEdge = null;
                graph.setSolved(false);
                repaint();
                return;
            }

            String input1 = JOptionPane.showInputDialog("Enter weight for " + hoveredEdge.toString(1)
                                                        + " : ");
            String input2 = JOptionPane.showInputDialog("Enter weight for " + hoveredEdge.toString(2)
                                                        + " : ");
            try {
                int weight1 = Integer.parseInt(input1);
                int weight2 = Integer.parseInt(input2);
                if (weight1 > 0 && weight2 > 0) {
                    hoveredEdge.setWeight(weight1, weight2);
                    graph.setSolved(false);
                    repaint();
                } else if(weight1 == 0 || weight2 == 0){
                    hoveredEdge.setWeight(weight1 == 0 ? Integer.MAX_VALUE : weight1, weight2 == 0 ? Integer.MAX_VALUE : weight2);
                    graph.setSolved(false);
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Weight should be positive");
                }
            } catch (NumberFormatException nfe) {}
            return;
        }

        for(Node node : graph.getNodes()) {
            if(PathFindingDrawUtils.isOverlapping(e, node.getCoord())){
                JOptionPane.showMessageDialog(null, "Overlapping Node can't be created");
                return;
            }
        }

        if (e.getX() + PathFindingDrawUtils.getRadius() >= this.getBounds().width || 
                e.getY() + PathFindingDrawUtils.getRadius() >= this.getBounds().height ||
                e.getX() - PathFindingDrawUtils.getRadius() <= 0 ||
                e.getY() - PathFindingDrawUtils.getRadius() <= 0 )
            return;
        
        graph.addNode(e.getPoint());
        graph.setSolved(false);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (Node node : graph.getNodes()) {
            if(selectedNode !=null && node!= selectedNode && PathFindingDrawUtils.isWithinBounds(e, node.getCoord())){
                DirectedEdge new_edge = new DirectedEdge(selectedNode, node);
                graph.addEdge(new_edge);
                graph.setSolved(false);
            }
        }
        selectedNode = null;
        hoveredNode = null;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        hoveredNode = null;

        for (Node node : graph.getNodes()) {
            if(selectedNode ==null && PathFindingDrawUtils.isWithinBounds(e, node.getCoord())){
                selectedNode = node;
            } else if(PathFindingDrawUtils.isWithinBounds(e, node.getCoord())) {
                hoveredNode = node;
            }
        }

        if(selectedNode !=null){
            if(e.isControlDown()){
                selectedNode.setCoord(e.getX(), e.getY());
                cursor = null;
                repaint();
                return;
            }

            cursor = new Point(e.getX(), e.getY());
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        if(e.isControlDown()){
            hoveredNode = null;
            for (Node node : graph.getNodes()) {
                if(PathFindingDrawUtils.isWithinBounds(e, node.getCoord())) {
                    hoveredNode = node;
                }
            }
        }

        hoveredEdge = null;

        for (DirectedEdge edge : graph.getEdges()) {
            if(PathFindingDrawUtils.isOnEdge(e, edge)) {
                hoveredEdge = edge;
            }
        }

        repaint();
    }

    public void reset(){
        graph.clear();
        selectedNode = null;
        hoveredNode = null;
        hoveredEdge = null;
        repaint();
    }
}
