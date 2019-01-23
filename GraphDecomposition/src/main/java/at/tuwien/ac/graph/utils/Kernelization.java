package at.tuwien.ac.graph.utils;

import at.tuwien.ac.graph.graph.Graph;
import at.tuwien.ac.graph.graph.UndirectedGraph;
import at.tuwien.ac.graph.graph.Vertex;

import java.util.List;

/**
 * Created by root on 4/4/17.
 */
public class Kernelization {

    public static Graph preprocess(Graph graph, int k, int c){
        List<Graph> connectedComponents = IncidentGraphHelper.getConnectedComponents3(graph);
        int size = connectedComponents.size();
        if(size >1){
           int lbs[] = new int[size];
            int i = 0;
           for(Graph component: connectedComponents)
               lbs[i++] = getLowerBound(component, k , c);
           Graph reducedGraph = new UndirectedGraph();
           i=0;
           for(Graph component: connectedComponents) {
               int total = 0;
               for(int j=0; j < size; j++) {
                   if (j == i) continue;
                   total+=lbs[j];
               }
               Graph partial = preprocessConnectedComponent(component, k-total, c);
               reducedGraph = IncidentGraphHelper.join(reducedGraph, partial);
               i++;
           }
           return reducedGraph ;
        }else{
            return preprocessConnectedComponent(graph, k, c);
        }
    }

    private static Graph preprocessConnectedComponent(Graph component, int k, int c) {
        List<Vertex> vertices = component.getVertices();
        for(Vertex vertex: vertices)
            if(component.getDegreeof(vertex)> k+c){
                vertices.remove(vertex);
                Graph newGraph = component.getInducedGraph(vertices);
                return preprocess(newGraph, k-1, c);
            }
        return component ;

    }

    public static int getLowerBound(Graph graph, int k , int c){
        int n = graph.getSize();
        int delta = graph.getMaxDegree();
        double ceil = Math.ceil(((double) n) / ((double) (delta * c + 1)));
        return (int) ceil ;
    }
}
