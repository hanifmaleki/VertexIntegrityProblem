package at.tuwien.ac.graph.bounds.lb.mdst;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by root on 5/4/17.
 * This class calculate minimum degree spanning tree of a given graph
 * and calculate deletion backdoor lower bound based on the obtained tree
 */
public class MinimumDegreeSpanningTreeLB {

    Logger logger = LoggerFactory.getLogger(MinimumDegreeSpanningTreeLB.class);



    public GraphNew getMinimumDegreeSpanningTreeEff(GraphNew graph){
        //logger.debug("Start calculating Minimem Max Degree SPT for graph with size "+graph.getSize() );
        List<Integer> vertices = graph.getVertices();
        GraphNew tree = new GraphNew(graph.getN(), graph.getGraphInfo());
        PriorityQueue<VertexNeighbor> heap = new PriorityQueue<>();
        //Set<Integer> verTree = new LinkedHashSet<>();
        boolean[] visited = new boolean[graph.getN()];
        //verTree.add( vertices.get( 0 ) );
        visited[vertices.get( 0 )] = true ;
        heap.add(new VertexNeighbor(vertices.get(0), 0));
        int numberOfSelectedVertices = 1 ;
        while (numberOfSelectedVertices < vertices.size() ) {
            Integer nextV = null;
            Integer preV = null;
            VertexNeighbor vertexNeighbor = null;
            do{
                vertexNeighbor = heap.remove();
                Integer vertex = vertexNeighbor.vertex;
                for(Integer neighbor: graph.getNeighbors(vertex)){
                    //if(!verTree.contains(neighbor)){
                    if(!visited[neighbor]){
                        nextV = neighbor;
                        preV =vertex;
                        break;
                    }
                }
            }while(preV==null);
            //add new vertex and edge
            tree.addEdge( preV, nextV );
            tree.addEdge( nextV, preV );
            //verTree.add( nextV );
            visited[nextV]= true;

            //add new elements in heap
            heap.add(new VertexNeighbor(nextV, 1));
            boolean flag = false;
            for(Integer neighbor : graph.getNeighbors(preV))
                //if(!verTree.contains(neighbor)){
                if(visited[neighbor]){
                    flag = true;
                    break ;
                }

            if(flag){
                vertexNeighbor.neighborCount++;
                heap.add(vertexNeighbor);
            }

            numberOfSelectedVertices++;
            visited[nextV] = true;

        }
        return tree;
    }


    private Integer getMaxDegreeOfMDST(GraphNew graph){
        if(graph.getSize()==1)
            return 0;
        return getMinimumDegreeSpanningTreeEff( graph ).getMaxDegree();
    }




    public int getLowerBoundEff(GraphNew graph, int c){
        List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(graph);
        int sum = 0;
        for(GraphNew component :connectedComponentsHS)
            sum += getLBFromDegree( getMaxDegreeOfMDST( component ), component.getSize(), c );
        return sum;
    }

    public int getLBFromDegree(int degree, int n, int c) {
        if ( degree== 0 )
            return 0;

        return (int)Math.ceil( ((double)n)/(((double)c*degree+1)) );
    }




    private class VertexNeighbor implements Comparable<VertexNeighbor>{
        Integer vertex;
        int neighborCount ;

        public VertexNeighbor(Integer vertex, int neighborCount) {
            this.vertex = vertex;
            this.neighborCount = neighborCount;
        }

        @Override
        public int compareTo(VertexNeighbor o) {
            return Integer.compare(this.neighborCount, o.neighborCount);
        }
    }
}
