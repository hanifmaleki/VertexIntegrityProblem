package at.tuwien.ac.graph.tw;

public class Ramachandramurthi< D extends GraphInput.InputData>implements LowerBound<D> {

    protected NGraph<D> graph;
    protected int lowerbound;


    /**
     * Starts out as a lowerbound of -infty; improved to the mindegree
     * lowerbound once run() is called.
     */
    public Ramachandramurthi() {
        lowerbound = Integer.MIN_VALUE;
    }


    public String getName() {
        return "Ramachandramurthi";
    }


    public void setInput(NGraph<D> g) {
        graph = g.copy(  );
    }


    /**
     * Method runs the algorithm and sets the lowerbound.
     *
     */
    public void run() {
		/* Ramachandramurthi, when
		 * G = (V;E) is a clique, the number of vertices minus 1; and when G is not
		 * a clique, the minimum over all pairs of non-adjacent vertices v, w, of the
		 * maximum of the degree of v and w.
		 */

        //Check if the graph is a clique
        boolean clique = true;
        for(NVertex<D> v : graph)
            if(v.getNumberOfNeighbors() != graph.getNumberOfVertices()-1)
                clique = false;
        if(clique) {
            lowerbound = graph.getNumberOfVertices()-1;
            return;
        }

        //Create a n*n matrix. Each value (a,b) is true iff there is a edge between a and b
        boolean [][] m = new boolean [graph.getNumberOfVertices()][graph.getNumberOfVertices()];

        //Check the edges
        for(NEdge e: graph.edges()) {

            Integer from = (Integer) e.a.data;
            Integer to = (Integer) e.b.data;
            m[from][to] = true;
            m[to][from] = true;
        }

        //Find the min max degree
        int minDegree = graph.getNumberOfVertices();
        for(int i=0; i<graph.getNumberOfVertices(); ++i) {
            for(int j=0; j<graph.getNumberOfVertices(); ++j) {
                if(i != j) {
                    NVertex<D> v1 = graph.getVertex(i);
                    NVertex<D> v2 = graph.getVertex(j);

                    if(!m[i][j]) {
                        //Non-adjacent
                        int max;
                        if(v1.getNumberOfNeighbors() > v2.getNumberOfNeighbors())
                            max = v1.getNumberOfNeighbors();
                        else
                            max = v2.getNumberOfNeighbors();
                        if( max < minDegree ) minDegree = max;
                    }
                }
            }
        }

        // don't lower the lower bound if we already know a
        // better one.
        if( minDegree>lowerbound ) lowerbound = minDegree;

    }

    public int getLowerBound() {
        return lowerbound;
    }
}