package algo;

import java.util.*;
import models.*;

public class DPAlgorithmTSP {
  
    private final int N;
    private final int START_NODE;
    private final int FINISHED_STATE;
    
    private boolean safe = false;
    private String message = null;

    private UndirectedGraph graph;
    private int[][] distance;
    private Map<Integer, Node> nodes;
    private double minTourCost = Double.POSITIVE_INFINITY;

    private List<Node> path = new ArrayList<>();
    private boolean ranSolver = false;

    public DPAlgorithmTSP(UndirectedGraph graph) {
        this(graph.getSource(), graph);
    }

    public DPAlgorithmTSP(Node startNode, UndirectedGraph graph) {
        this.graph = graph;
        N = graph.getNodes().size();
        Map<Node, Integer> tempNodes = new HashMap<>();
        nodes = new HashMap<>();
        distance = new int[N][N];
        int i = 0; 
        for(Node node : graph.getNodes()){
            tempNodes.put(node, i);
            nodes.put(i, node);
            i++;
        }
        
        for(Edge edge : graph.getEdges()){
            i = tempNodes.get(edge.getSourceNode());
            int j = tempNodes.get(edge.getDestinationNode());
            distance[i][j] = distance[j][i] = edge.getWeight();
        }
        
        START_NODE = tempNodes.get(startNode);

        // Validate inputs.
        safe = evaluate(graph);

        // The finished state is when the finished state mask has all bits are set to
        // one (meaning all the tempNodes have been visited).
        FINISHED_STATE = (1 << N) - 1;
    }
    
    private boolean evaluate(UndirectedGraph graph){
        if(N <= 2){
            message = "TSP on 0, 1 or 2 nodes doesn't make sense.";
            return false;
        }

        if(N > 24){
            message = "Graph too large! A graph that size for the DP TSP problem with a time complexity of O(n^2*2^n) requires way too much computation for any modern home computer to handle";
            return false;
        }

        for(Node node : graph.getNodes()){
            if(!graph.isNodeReachable(node)){
                message = "Graph contains unreachable nodes";
                return false;
            }
        }
        
        int e = graph.getEdges().size();
        int n = graph.getNodes().size();
        if(e != n*(n-1)/2){
            message = "Graph for tsp problem must be a perfect graph!";
            return false;
        }

        return true;
    }

    // Returns the optimal tour for the traveling salesman problem.
    public List<Node> getPath(){
        if (!ranSolver) run();
        return path;
    }

    // Returns the minimal tour cost.
    public double getTourCost() {
        if (!ranSolver) run();
        return minTourCost;
    }
    

    public void run() {
        if(!safe) {
            throw new IllegalStateException(message);
        }
        
        // Run the solver    
        int state = 1 << START_NODE;
        Double[][] memo = new Double[N][1 << N];
        Integer[][] prev = new Integer[N][1 << N];
        minTourCost = tsp(START_NODE, state, memo, prev);

        // Regenerate path
        int index = START_NODE;
        while (true) {
            path.add(nodes.get(index));
            Integer nextIndex = prev[index][state];
            if (nextIndex == null) break;
            int nextState = state | (1 << nextIndex);
            state = nextState;
            index = nextIndex;
        }
        path.add(nodes.get(START_NODE));
        ranSolver = true;
        graph.setSolved(true);
    }

    private double tsp(int i, int state, Double[][] memo, Integer[][] prev) {
        // Done this tour. Return cost of going back to start node.
        if (state == FINISHED_STATE) return distance[i][START_NODE];

        // Return cached answer if already computed.
        if (memo[i][state] != null) return memo[i][state];

        double minCost = Double.POSITIVE_INFINITY;
        int index = -1;
        for (int next = 0; next < N; next++) {
            // Skip if the next node has already been visited.
            if ((state & (1 << next)) != 0) continue;

            int nextState = state | (1 << next);
            double newCost = distance[i][next] + tsp(next, nextState, memo, prev);
            if (newCost < minCost) {
                minCost = newCost;
                index = next;
            }
        }

        prev[i][state] = index;
        return memo[i][state] = minCost;
    }
}