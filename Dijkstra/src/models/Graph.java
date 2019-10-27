/*
    Path finding by Dijkstra algorithm
    Ali Abdolazimi
    Mohammad Amirdoost
    Sajjad Moghayyad
*/

package models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Graph<T> {
    protected int count = 1;
    protected List<Node> nodes = new ArrayList<>();
    protected List<T> edges = new ArrayList<>();

    protected Node source;
    protected Node destination;

    protected boolean solved = false;

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setNodes(List<Node> nodes){
        this.nodes = nodes;
    }

    public List<Node> getNodes(){
        return nodes;
    }

    public void setEdges(List<T> edges){
        this.edges = edges;
    }

    public List<T> getEdges(){
        return edges;
    }

    public boolean isNodeReachable(Node node){return false;}

    public void setSource(Node node){
        if(nodes.contains(node))
            source = node;
    }

    public void setDestination(Node node){
        if(nodes.contains(node))
            destination = node;
    }

    public Node getSource(){
        return source;
    }

    public Node getDestination(){
        return destination;
    }

    public boolean isSource(Node node){
        return node == source;
    }

    public boolean isDestination(Node node){
        return node == destination;
    }

    public Node addNode(Point coord){
        Node node = new Node(coord);
        return addNode(node);
    }

    public Node addNode(Node node){
        node.setId(count++);
        nodes.add(node);
        if(node.getId()==1)
            source = node;
        return node;
    }

    public void addEdge(T new_edge){}

    public void deleteNode(Node node){}

    public void clear(){
        count = 1;
        nodes.clear();
        edges.clear();
        solved = false;

        source = null;
        destination = null;
    }

}
