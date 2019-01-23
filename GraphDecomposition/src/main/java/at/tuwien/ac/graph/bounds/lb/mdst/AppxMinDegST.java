package at.tuwien.ac.graph.bounds.lb.mdst;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by root on 7/28/17.
 * Try to calculate minimum degree spanning tree by means of an
 * approximation algorithm ...
 */

//TODO this algorithm has not been implemented completely
public class AppxMinDegST {
    public static GraphNew pruneLeavesPreprocess(GraphNew graph){
        List<Integer> vertices = graph.getVertices();
        for(Integer vertex : vertices){
            if(graph.getDegreeof(vertex)==1){
                Integer parent = graph.getNeighbors(vertex).get(0);
                int leaveCounter = 0 ;
                List<Integer> neighbors = graph.getNeighbors(parent);
                List<Integer> removingList = new ArrayList<>();
                for(Integer neighbor: neighbors){
                    if(graph.getDegreeof(neighbor)==1){
                        leaveCounter++;
                        if(leaveCounter>2)
                            removingList.add(neighbor);
                    }
                }
                for(Integer v : removingList)
                    graph = graph.removeVertexCopy(v);
            }
        }
        return graph;
    }

    public static GraphNew makeTopDeownImprovment(GraphNew originalGraph, GraphNew tree, GraphNew component){
        int k = tree.getMaxDegree();
        List<Integer> maxDegreeList = tree.getMaxDegreeList();
        //TODO investigate it
        GraphNew forest = tree;
        for(Integer vertex : maxDegreeList)
            forest = forest.removeVertexCopy(vertex);

        List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(forest);
        for(GraphNew component1 : connectedComponentsHS)
            for(GraphNew component2: connectedComponentsHS) {
                if (component1.equals(component2))
                    continue;
                for(Integer vertex1: component1.getVertices())
                    for(Integer vertex2: component2.getVertices())
                        if(originalGraph.getNeighbors(vertex1).contains(vertex2)){
                            List<Integer> shortestPath = GraphNewHelper.getShortestPath(tree, vertex1, vertex2);
                            //find vertex of max degree in the cycle
                            Integer maxDegVertex = null;
                            for(Integer vertex : shortestPath)
                                if(tree.getDegreeof(vertex)>k-1){
                                    maxDegVertex = vertex;
                                    break;
                                }

                            //if it is possible remove one edge
                            Integer maxNeighbor = null;
                            if(tree.getDegreeof(vertex1)>k-2){
                                //GraphNew newTree = makeTopDeownImprovment(originalGraph, component1);
                                if(tree.getMaxDegree()==k-1)
                                    return null;

                            }
                            //TODO same for component2

                            //ADD(VERTEX1,VERTEX2) to the tree
                            // remove one edge from the maxDegreeVertex
                            return tree;
                        }

            }
        //We have a optimal(approximately) solution
        return null ;

    }


    public static void makeButtomUpImprovement(GraphNew originalGraph, GraphNew tree/*we need good and bad vertices here*/){
        int k = tree.getMaxDegree();
        Set<Integer> badVertices = new LinkedHashSet<>();
        Set<Integer> goodVertices = new LinkedHashSet<>();
        List<Integer> maxDegreeList = tree.getMaxDegreeList();
        GraphNew forest = tree;
        for(Integer vertex : maxDegreeList)
            forest = forest.removeVertexCopy(vertex);

        maxDegreeList = forest.getMaxDegreeList();
        for(Integer vertex : maxDegreeList)
            forest = forest.removeVertexCopy(vertex);

        List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(forest);
        for(GraphNew component1 : connectedComponentsHS)
            for(GraphNew component2: connectedComponentsHS) {
                if (component1.equals(component2))
                    continue;
                for(Integer vertex1: component1.getVertices())
                    for(Integer vertex2: component2.getVertices())
                        if(originalGraph.getNeighbors(vertex1).contains(vertex2)) {
                            List<Integer> shortestPath = GraphNewHelper.getShortestPath(tree, vertex1, vertex2);
                            //find vertex v of max degree in the cycle
                            int max = k-2;
                            Integer maxDegVertex = null;
                            for(Integer vertex : shortestPath)
                                if(tree.getDegreeof(vertex)>max){
                                    max = tree.getDegreeof(vertex);
                                    maxDegVertex = vertex;
                                }

                            if (max==k){
                                //TODO complete it
                                //remove on edge of max deg vertex
                                //add(vertex1,vertex2) to the tree
                            }
                            if (max== k-1){
                                //TODO complete it
                                //mark all vertices in the shortest path as good one
                            }
                            //restart the algorithm
                        }
            }
        //arrive here: the tree is the optimal(approximately) one
    }
}
