package at.tuwien.ac.graph.bounds.lb;

import at.tuwien.ac.graph.newgraph.GraphInfo;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.OldFVDSolver;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by root on 4/17/17.
 * This class has been implemented in order to obtain some numerical lower bounds on the important graphs
 * It tries to generate random graph and calculate fvd of it. This method does not seem very promising ...
 */
public class LowerBoundCalculator {

    public static int n = 40 ; //Number ef vertices

    public static int c = 10 ;

    public static int k = c ;

    public static int maxAllowedEdge = (k+c-1)*n/2;//n * (n-1) / 2;

    public static int minAllowedEdge = 0;

    private static GraphInfo graphInfo = null;
    private static ArrayList<Integer> vertexList = null;
    private static int exprimentCount = 1000;

    private static List<Edge> initialPossibleEdges(){
        List<Edge> edgeList = new ArrayList<>();
        for(int i=0; i < n ; i++)
            for(int j=i+1; j< n; j++) {
                Edge edge = new Edge(new Integer(i), new Integer(j));
                edgeList.add(edge);
            }
        return edgeList;
    }


    public static void generateAndRunInstances() {
        int minEdge = Integer.MAX_VALUE;
        int minMaxDegree = 0;

        int maxEdge= Integer.MIN_VALUE;
        int maxMaxDegree = 0;
        int counter = 0;
        while (counter++<exprimentCount) {
            ArrayList<Integer>[] adjList = getAdjList();
            GraphNew graph = new GraphNew(getGraphInfo(), getVertexList(), adjList);

            OldFVDSolver.globalGraph = graph;
            long time = System.currentTimeMillis();
            //List<vertex> fvd = FVDSolver.getFVD(inducedGraph, inducedGraph.getVertices(), k, c, new LinkedHashSet<>());
            OldFVDSolver.callCount=0;
            OldFVDSolver.componentsMeasure=0;
            OldFVDSolver.inducedGraphMeasure=0;
            OldFVDSolver.connectivityMeasure=0;

            List<Integer> fvd = OldFVDSolver.getFVD(graph, new LinkedHashSet<>(getVertexList()), k, c, new LinkedHashSet<>(), graph.getSize());

            int edgeSize = graph.getEdgeSize();
            int maxDegree = graph.getMaxDegree();
            if(fvd!=null) {
                if(maxEdge< edgeSize) {
                    maxEdge = edgeSize;
                    maxMaxDegree = maxDegree;
                }
            }
            if(fvd==null){
                if(minEdge > edgeSize) {
                    minEdge = edgeSize;
                    minMaxDegree = maxDegree;
                }
            }
            System.out.println(edgeSize /2+"\t"+ maxDegree +"\t"+ fvd);

        }
            System.out.println("Maximum positive "+maxEdge/2+"\t"+maxMaxDegree+"\t\tMinimum negaive "+ minEdge/2+"\t"+minMaxDegree);
    }

    private static ArrayList<Integer>[] getAdjList() {
        ArrayList<Integer>[] arrayLists = new ArrayList[n];

        for(int i=0; i < n; i++)
            arrayLists[i] = new ArrayList<Integer>();

        List<Edge> edgeList = initialPossibleEdges();

        double random = Math.random();
        random = random*(maxAllowedEdge-minAllowedEdge)+minAllowedEdge ;


        int edgeCount = (int) random;


        for(int i=0; i < edgeCount; i++){
            while(true) {
                if(edgeList.size()==0)
                    break;
                random = Math.random();
                random *= edgeList.size();

                int index = (int) random;

                Edge edge = edgeList.remove(index);
                if(arrayLists[edge.from].size()==k+c)
                    continue;
                if(arrayLists[edge.to].size()==k+c)
                    continue;
                arrayLists[edge.from].add(edge.to);
                arrayLists[edge.to].add(edge.from);
                break;
            }
        }

        return arrayLists;
    }

    private static GraphInfo getGraphInfo() {
        if(graphInfo!= null)
            return graphInfo;
        GraphInfo graphInfo = new GraphInfo();
        for(int i = 0; i < n; i++)
            graphInfo.addVertex(new Integer(i), (""+i));
        return graphInfo;
    }

    private static ArrayList<Integer> getVertexList() {
        if(vertexList!=null)
            return vertexList;
        vertexList = new ArrayList<>(n);
        for(int i=0; i < n; i++)
            vertexList.add(i, new Integer(i));
        return vertexList;
    }

    static class Edge{
        private Integer from;

        private Integer to;

        public Edge(Integer from, Integer to) {
            this.from = from;
            this.to = to;
        }

        public Integer getFrom() {
            return from;
        }

        public void setFrom(Integer from) {
            this.from = from;
        }

        public Integer getTo() {
            return to;
        }

        public void setTo(Integer to) {
            this.to = to;
        }
    }
}
