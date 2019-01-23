package at.tuwien.ac.graph.utils;

import at.tuwien.ac.graph.graph.Graph;
import at.tuwien.ac.graph.graph.Vertex;
import at.tuwien.ac.graph.newgraph.GraphInfo;
import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.samples.CliqueCycle;

import java.util.*;

/**
 * Created by hanif on 3/20/17.
 */
public class IncidentGraphHelper {

    /*
    public static Graph getIncidentGraph(LP lp) {
        Graph graph = new UndirectedGraph();

        for (Variable variable : lp.getVaribales()) {
            Vertex vertex = new Vertex(variable.getName());
            graph.getVertices().add(vertex);
        }

        int counter = 1;
        for (Constraint constraint : lp.getConstraintList()) {
            Vertex vertex = new Vertex("c" + counter++);

            graph.getVertices().add(vertex);

            for (VariableCoefficient variableCoefficient : constraint.getVariableCoefficients()) {
                String varName = variableCoefficient.getVariable().getName();

                Vertex varVertex = graph.getVertexByName(varName);

//                Edge edge = new Edge(vertex, varVertex);

                graph.addEdge(vertex, varVertex);
            }
        }

        return graph;
    }*/

    /*public static List<Graph> getConnectedComponents(Graph graph) {
        List<Graph> graphs = new ArrayList<>();
        List<Vertex> vertices = graph.getVertices();
        while (!vertices.isEmpty()) {
            Vertex vertex = vertices.get(0);
            Queue<Vertex> queue = new LinkedList<Vertex>();
            queue.add(vertex);
            List<Vertex> componentVertices = new ArrayList<>();
            while (!queue.isEmpty()) {
                vertex = queue.remove();
                List<Vertex> neighbors = graph.getNeighbors(vertex);
                for(Vertex adjacent: neighbors) {
                    if(vertices.contains(adjacent))
                        queue.add(adjacent);
                }
                componentVertices.add(vertex);
                vertices.remove(vertex);
            }
            graphs.add(graph.getInducedGraph(componentVertices));
        }
        return graphs;
    }*/

    //public static long componentsMeasure = 0 ;
    /*public static List<Graph> getConnectedComponents2(Graph graph) {
        List<Graph> graphs = new ArrayList<>();
        List<Vertex> vertices = graph.getVertices();

        int size = vertices.size();
        boolean[] visited = new boolean[size];
        for(int i = 0; i < size; i++)
            visited[i]=false ;

        for(int i = 0; i < size; i++){
            if(!visited[i]){
                Vertex vertex = vertices.get(i);
                Queue<Vertex> queue = new LinkedList<Vertex>();
                visited[i] = true ;
                queue.add(vertex);
                List<Vertex> componentVertices = new ArrayList<>();
                while (!queue.isEmpty()) {
                    vertex = queue.remove();
                    List<Vertex> neighbors = graph.getNeighbors(vertex);
                    for(Vertex adjacent: neighbors) {
                        int j = vertices.indexOf(adjacent);
                        if(!visited[j]) {
                            queue.add(adjacent);
                            visited[j] = true ;
                        }
                    }
                    componentVertices.add(vertex);
                }
                //long current = System.currentTimeMillis();
                graphs.add(graph.getInducedGraph(componentVertices));
                //componentsMeasure+= (System.currentTimeMillis() - current);
            }



        }
        return graphs;
    }*/

    public static List<Graph> getConnectedComponents3(Graph graph) {
        List<Graph> graphs = new ArrayList<>();
        List<Vertex> vertices = graph.getVertices();
        HashSet<Vertex> visited = new LinkedHashSet<>();

        for(Vertex vertex : vertices)
            if(!visited.contains(vertex)){
                visited.add(vertex);
                Queue<Vertex> queue = new LinkedList<Vertex>();
                queue.add(vertex);
                List<Vertex> componentVertices = new ArrayList<>();
                while (!queue.isEmpty()) {
                    vertex = queue.remove();
                    HashSet<Vertex> neighbors = graph.getNeighbors(vertex);
                    for(Vertex adjacent: neighbors)
                        if(!visited.contains(adjacent)) {
                            queue.add(adjacent);
                            visited.add(adjacent);
                        }
                    componentVertices.add(vertex);
                }
                graphs.add(graph.getInducedGraph(componentVertices));
            }
        return graphs;
    }



    public static List<Vertex> getCommonVertices(List<Vertex> g, List<Vertex> vertexList) {
        List<Vertex> commonVertices = new ArrayList();
        for (Vertex vertex : vertexList) {

            if (g.contains(vertex))
                commonVertices.add(vertex);
        }
        return commonVertices;
    }

    //It is assumed that graph is connected and its size is more than k
    public static List<Vertex> getConnectedVertices(Graph graph, int k) {
        List<Vertex> vertices = new ArrayList<>();
        Vertex vertex = graph.getVertices().get(0);
        vertices.add(vertex);
        int counter = 0;
        while (true){
            vertex = vertices.get(counter++);
            HashSet<Vertex> neighbors = graph.getNeighbors(vertex);
            for(Vertex adjacent: neighbors)
                if(!vertices.contains(adjacent)){
                    vertices.add(adjacent);
                    if(vertices.size()>=k)
                        return vertices;
                }

        }

    }


    //TODO complete it
    //TODO Consider moving to graph class
    //Here we assume that there is no edge between tow components
    public static Graph join(Graph g, Graph h) {
        return null;
    }

    public static GraphNew getNewStructure(Graph graph){
        List<Vertex> vertices = graph.getVertices();
        GraphInfo info = new GraphInfo();
        for(Vertex vertex : vertices){
            info.addVertex(vertex.getIndex(), vertex.getName());
        }
        GraphNew graphNew = new GraphNew(graph.getSize(), info);
        for(Vertex vertex : vertices){
            HashSet<Vertex> neighbors = graph.getNeighbors(vertex);
            for(Vertex neighbor: neighbors)
                graphNew.addEdge(vertex.getIndex(), neighbor.getIndex());
        }
        return graphNew;
    }

}
