/*
    Path finding by Dijkstra algorithm
    Ali Abdolazimi
    Mohammad Amirdoost
    Sajjad Moghayyad
*/

package models;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph extends Graph<DirectedEdge> {
    @Override
    public boolean isNodeReachable(Node node){
        boolean isSource= false;
        boolean isdestination = false;
        for(DirectedEdge edge : edges){
            if((node == edge.getSourceNode() && edge.getWeight(1) != Integer.MAX_VALUE) || (node == edge.getDestinationNode() && edge.getWeight(2) != Integer.MAX_VALUE))
                isSource = true;
            if((node == edge.getSourceNode() && edge.getWeight(2) != Integer.MAX_VALUE) || (node == edge.getDestinationNode() && edge.getWeight(1) != Integer.MAX_VALUE))
                isdestination = true;
            if(isSource && isdestination)
                break;
        }
        
        return isSource && isdestination;
    }

    @Override
    public void addEdge(DirectedEdge new_edge){
        boolean added = false;
        for(DirectedEdge edge : edges){
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
        List<DirectedEdge> delete = new ArrayList<>();
        for (DirectedEdge edge : edges){
            if(edge.hasNode(node)){
                delete.add(edge);
            }
        }
        for (DirectedEdge edge : delete){
            edges.remove(edge);
        }
        nodes.remove(node);
    }
}
