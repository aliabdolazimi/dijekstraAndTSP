/*
    Path finding by Dijkstra algorithm
    Ali Abdolazimi
    Mohammad Amirdoost
    Sajjad Moghayyad
*/

package models;

public class Edge {
    protected Node source;
    protected Node destination;
    private int weight = 1;

    public Edge(Node source, Node destination){
        this.source = source;
        this.destination = destination;
    }

    public Node getSourceNode(){
        return source;
    }

    public Node getDestinationNode(){
        return destination;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public int getWeight(){
        return weight;
    }

    public boolean hasNode(Node node){
        return source==node || destination==node;
    }

    public boolean equals(Edge edge) {
        return (source ==edge.source && destination ==edge.destination) || (source ==edge.destination && destination ==edge.source) ;
    }
    
    public String toString() {
        return getSourceNode().getId() + " - "
               + getDestinationNode().getId();
    }
}
