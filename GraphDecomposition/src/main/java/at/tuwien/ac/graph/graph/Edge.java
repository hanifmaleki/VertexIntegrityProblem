package at.tuwien.ac.graph.graph;

public class Edge {
	
	private Vertex from ;
	private Vertex to ;

    public Edge(Vertex from, Vertex to) {
        this.from = from ;

        this.to = to;
    }

    public Vertex getFrom() {
        return from;
    }

    public Vertex getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "E{" +
                 from.getName() +
                ", " + to.getName() +
                '}';
    }
}
