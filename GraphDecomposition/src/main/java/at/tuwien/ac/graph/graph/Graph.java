package at.tuwien.ac.graph.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by root on 4/1/17.
 */
public interface Graph {
    //TODO maintain this array
    List<Vertex> getVertices();

    HashSet<Vertex> getNeighbors(Vertex vertex);

    Vertex getVertexByName(String name);

    Vertex addVertex(String name);

    void addEdge(Vertex from, Vertex to);

    //TODO consider efficiency
    Graph getInducedGraph(List<Vertex> vertices);

    int getSize();

    void addVertex(Vertex vertex);

    int getMaxDegree();

    int getDegreeof(Vertex vertex);

    HashMap<String, Vertex> getVertexMap();

    void setVertexMap(HashMap<String, Vertex> vertexMap);

    HashMap<Vertex, HashSet<Vertex>> getAdjacencyMap();

    void setAdjacencyMap(HashMap<Vertex, HashSet<Vertex>> adjacencyMap);

    Graph removeVertex(Vertex vertex);
}
