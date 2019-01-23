package at.tuwien.ac.graph.labeled;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by e1528895 on 8/22/17.
 */
public class LabeledGraphHelper {

    public List<LabeledGraph> getConnectedComponents(LabeledGraph graph) {
        List<GraphNew> components = GraphNewHelper.getConnectedComponentsHS(graph.getGraphNew());
        List<LabeledGraph> labeledGraphs = new ArrayList<>();
        for(GraphNew component: components){
            List<LabeledEdge> newEdges = new ArrayList<>();
            Set<Integer> labelSet = new HashSet<>();
            for(Integer vertex : component.getVertices()){
                List<Integer> neighbors = component.getNeighbors(vertex);
                for(Integer neighbor : neighbors){
                    if(neighbor> vertex){
                        LabeledEdge edge = graph.getEdge(vertex, neighbor);
                        newEdges.add(edge);
                        labelSet.addAll(edge.labels);
                    }
                }
            }
            LabeledGraph labeledGraph = new LabeledGraph(component.getVertices(), newEdges, graph.getLabelCount(), graph.getGraphInfo());
            labeledGraphs.add(labeledGraph);
        }
        return labeledGraphs ;
    }

    public static Set<Integer> getAppropriateConnectedComponent(LabeledGraph graph, int k) {
        List<Integer> vertices = new ArrayList<>();
        Set<Integer> labels = new HashSet<>();
        Set<Integer> candidateLabels = new HashSet<>();
        List<LabeledEdge> edges = new ArrayList<>();

        Integer vertex = graph.getVertices().get(0);
        vertices.add(vertex);
        int counter = 0;
        while (true) {
            vertex = vertices.get(counter++);
            List<Integer> neighbors = graph.getNeighbors(vertex);
            for (Integer adjacent : neighbors)
                if (!vertices.contains(adjacent)) {
                    vertices.add(adjacent);

                    LabeledEdge edge = graph.getEdge(vertex, adjacent);
                    edges.add(edge);
                    candidateLabels.addAll(edge.labels);
                    for (Integer label : edge.labels) {
                        boolean add = labels.add(label);
                        break;
                    }
                    if (vertices.size() >= k) {
                        for (Integer label : candidateLabels) {
                            labels.add(label);
                            if (labels.size() == k - 1)
                                break;
                        }
                        return labels;
                    }
                }

        }

    }


}
