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

public class UndirectedGraph extends Graph<Edge> {
    @Override
    public boolean isNodeReachable(Node node){
        for(Edge edge : edges)
            if(node == edge.getSourceNode() || node == edge.getDestinationNode())
                return true;
        
        return false;
    }

    @Override
    public void addEdge(Edge new_edge){
        boolean added = false;
        for(Edge edge : edges){
            if(edge.equals(new_edge)){
                added = true;
                break;
            }
        }
        if(!added)
            edges.add(new_edge);
    }

    @Override
    public void deleteNode(Node node){
        List<Edge> delete = new ArrayList<>();
        for (Edge edge : edges){
            if(edge.hasNode(node)){
                delete.add(edge);
            }
        }
        for (Edge edge : delete){
            edges.remove(edge);
        }
        nodes.remove(node);
    }
}
