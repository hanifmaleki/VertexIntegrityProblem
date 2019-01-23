package at.tuwien.ac.graph.bounds.lb.mdst;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 7/28/17.
 * This class calculate minimum degree spanning tree of a given graph
 * and calculate deletion backdoor lower bound based on the obtained tree
 * Since the more efficient code is available in MinimumDegreeSpanningTreeLB.java this code is not used more
 */
public class MDST_OLD {

    public static GraphNew getMinimumDegreeSpanningTree(GraphNew graph){
        List<Integer> vertices = graph.getVertices();
        GraphNew tree = new GraphNew(graph.getN(), graph.getGraphInfo());
        ArrayList<Integer> verTree = new ArrayList<Integer>();
        verTree.add( vertices.get( 0 ) );
        while (verTree.size() < vertices.size() ) {
            Integer nextV = null;
            Integer preV = null;
            int mindeg = Integer.MAX_VALUE;
            for ( Integer v : verTree ) {
                if ( mindeg > tree.getNeighbors( v ).size() ) {
                    for (Integer n: graph.getNeighbors(v)) {
                        if (!verTree.contains(n)) {
                            nextV = n;
                            preV =v;
                            mindeg = tree.getNeighbors( v ).size();
                        }
                    }
                }
            }
            tree.addEdge( preV, nextV );
            tree.addEdge( nextV, preV );
            verTree.add( nextV );
        }
        return tree;
    }

    /*public static int getLowerBound(GraphNew graph, int c ){
        List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(graph);
        int sum = 0;
        for(GraphNew component :connectedComponentsHS)
            sum += MinimumDegreeSpanningTreeLB.getLBFromDegree( getMSTDEG( component ), component.getSize(), c );
        return sum;
    }*/

    public static Integer getMSTDEG(GraphNew graph){
        if(graph.getSize()==1)
            return 0;
        return getMinimumDegreeSpanningTree( graph ).getMaxDegree();
    }

}
