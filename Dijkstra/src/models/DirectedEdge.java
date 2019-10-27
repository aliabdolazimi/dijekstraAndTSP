/*
    Path finding by Dijkstra algorithm
    Ali Abdolazimi
    Mohammad Amirdoost
    Sajjad Moghayyad
*/

package models;

public class DirectedEdge extends Edge {
    private int weight1_2 = 1;
    private int weight2_1 = Integer.MAX_VALUE;

    public DirectedEdge(Node source, Node destination) {
        super(source, destination);
    }

    public void setWeight(int weight1, int weight2){
        this.weight1_2 = weight1;
        this.weight2_1 = weight2;
    }

    public int getWeight(Node source){
        if(this.source == source)
            return weight1_2;
        else
            return weight2_1;
    }
    
    public int getWeight(int source){
        if(source == 1)
            return weight1_2;
        else
            return weight2_1;
    }

    public boolean equals(DirectedEdge edge) {
        return (source ==edge.source && destination ==edge.destination) || (source ==edge.destination && destination ==edge.source) ;
    }
    
    public String toString(int source) {
        if(source == 1)
            return getSourceNode().getId() + " - "
                   + getDestinationNode().getId();
        else
            return getDestinationNode().getId() + " - "
                   + getSourceNode().getId();
    }
}
