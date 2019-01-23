package at.tuwien.ac.graph.graph;

import java.util.*;

public class UndirectedGraph implements Graph {

    //	private List<Vertex> vertices = new ArrayList();
    //TODO update this tow data structures
    private HashMap<String, Vertex> vertexMap = new HashMap<>();
    private HashMap<Vertex, HashSet<Vertex>> adjacencyMap = new HashMap<>();

    private List<Edge> edges = new ArrayList();

    private int maxDegree = -1 ;

    public UndirectedGraph() {
    }

    //TODO maintain this array
    @Override
    public List<Vertex> getVertices() {
        return new ArrayList<Vertex>(vertexMap.values());
    }

    @Override
    public HashSet<Vertex> getNeighbors(Vertex vertex) {

        return adjacencyMap.get(vertex);
    }


    @Override
    public Vertex getVertexByName(String name) {
        return vertexMap.get(name);
    }

    @Override
    public Vertex addVertex(String name) {
        int index = vertexMap.size();
        Vertex vertex = new Vertex(name, index);
        vertexMap.put(name, vertex);
        adjacencyMap.put(vertex, new LinkedHashSet<>());
        return vertex;
    }

    public void addVertex(Vertex vertex){
        vertexMap.put(vertex.getName(), vertex);
        adjacencyMap.put(vertex, new LinkedHashSet<>());
    }

    @Override
    public int getMaxDegree() {
        if(maxDegree >-1)
            return maxDegree;
        List<HashSet<Vertex>> values = (List<HashSet<Vertex>>) adjacencyMap.values();
        int max = 0 ;
        for(HashSet<Vertex> set: values)
            if(set.size()>max)
                max=set.size();

        return max;
    }

    @Override
    public int getDegreeof(Vertex vertex) {
        HashSet<Vertex> vertices = adjacencyMap.get(vertex);
        return vertices.size();
    }

    @Override
    public HashMap<String, Vertex> getVertexMap() {
        return vertexMap;
    }
    @Override
    public void setVertexMap(HashMap<String, Vertex> vertexMap) {
        this.vertexMap = vertexMap;
    }

    @Override
    public HashMap<Vertex, HashSet<Vertex>> getAdjacencyMap() {
        return adjacencyMap;
    }

    @Override
    public void setAdjacencyMap(HashMap<Vertex, HashSet<Vertex>> adjacencyMap) {
        this.adjacencyMap = adjacencyMap;
    }

    @Override
    public Graph removeVertex(Vertex vertex) {
        Graph graph = new UndirectedGraph();
        HashMap<String, Vertex> vertexMap1 = new HashMap<>(this.vertexMap);
        vertexMap1.remove(vertex.getName());
        graph.setVertexMap(vertexMap1);
        HashMap<Vertex, HashSet<Vertex>> adjacencyMap1 = new HashMap<>(this.adjacencyMap);
        adjacencyMap1.remove(vertex);
        Collection<HashSet<Vertex>> values = adjacencyMap1.values();
        for(HashSet<Vertex> set: values)
            if(set.contains(vertex))
                set.remove(vertex);
        graph.setAdjacencyMap(adjacencyMap1);
        return graph;
    }

    @Override
    public void addEdge(Vertex from, Vertex to) {
        //TODO check if from and to are current vertices
        HashSet<Vertex> set = adjacencyMap.get(from);
        if (!set.contains(to))
            set.add(to);
        set = adjacencyMap.get(to);
        if (!set.contains(from))
            set.add(from);
        edges.add(new Edge(from, to));
        maxDegree = -1 ;
    }


	/*public UndirectedGraph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
		this.edges = edges;
	}

	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}*/

    @Override
    public String toString() {
        return "UndirectedGraph{" +
                "\nvertices=" + getVertices() +
                "\nedges=" + edges +
                '}'/*+"\n"+ adjacencyMap*/;
    }

    //TODO consider efficiency
    @Override
    public Graph getInducedGraph(List<Vertex> vertices) {
        Graph graph = new UndirectedGraph();
        HashSet<Vertex> set = new LinkedHashSet<>();
        for (Vertex vertex : vertices) {
            //graph.addVertex(vertex.getName());
            graph.addVertex(vertex);
            set.add(vertex);
            //List<Vertex> adjList = adjacencyMap.get(vertex);
        }
        for (Vertex vertex : vertices) {
            HashSet<Vertex> vertexHashSet = adjacencyMap.get(vertex);
            for (Vertex adjacent : vertexHashSet)
                if (set.contains(adjacent))
                    graph.addEdge(vertex, adjacent);

        }
        return graph;
    }

    //Here we assume that there is no edge between tow components
    //TODO some performance improvments needed
    public static Graph join(Graph h) {

        return null;
    }

    @Override
    public int getSize() {
        return vertexMap.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UndirectedGraph that = (UndirectedGraph) o;

        return adjacencyMap != null ? adjacencyMap.equals(that.adjacencyMap) : that.adjacencyMap == null;
    }

    @Override
    public int hashCode() {
        return adjacencyMap != null ? adjacencyMap.hashCode() : 0;
    }
}
