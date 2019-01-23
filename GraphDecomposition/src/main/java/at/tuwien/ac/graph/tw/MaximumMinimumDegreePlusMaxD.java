package at.tuwien.ac.graph.tw;

public class MaximumMinimumDegreePlusMaxD<D extends GraphInput.InputData> implements LowerBound<D> {

    protected NGraph<D> graph;
    protected int lowerbound;

    /**
     * Starts out as a lowerbound of -infty; improved to the mindegree
     * lowerbound once run() is called.
     */
    public MaximumMinimumDegreePlusMaxD() {
        lowerbound = Integer.MIN_VALUE;
    }

    public String getName() {
        return "MMD+Max-d: Maximum Minimum Degree Plus max-d";
    }

    public void setInput(NGraph<D> g) {
        graph = g.copy(  );
    }

    public void run() {
		/* MMD(Graph G) ::
		 * H = G
		 * maxmin = 0
		 * while H has at least two vertices do
		 * 	Select a vertex v from H that has minimum degree in H.
		 * 	maxmin = max( maxmin, dH(v) ).
		 * 	Contract v with the neighbour with lowest degree
		 * end while
		 * return maxmin
		 */

        // initial value for finding minimum: higher than any degree
        int maxDegree = 0;

        for( int i=0; i<graph.getNumberOfVertices(); ++i) {
            NVertex<D> minDegreeVertex = null;
            int min = Integer.MAX_VALUE;
            for(NVertex<D> v : graph) {
                if( min > v.getNumberOfNeighbors() ) {
                    minDegreeVertex = v;
                    min = v.getNumberOfNeighbors();
                }
            }
            if( minDegreeVertex != null ){
                if( minDegreeVertex.getNumberOfNeighbors()>maxDegree) {
                    maxDegree = minDegreeVertex.getNumberOfNeighbors();
                }
                NVertex<D> vertexToContractWith = null;
                int high = Integer.MIN_VALUE;
                for( NVertex<D> other:  minDegreeVertex) {
                    if(high < other.getNumberOfNeighbors()) {
                        high = other.getNumberOfNeighbors();
                        vertexToContractWith = other;
                    }
                }
                if( vertexToContractWith!=null )
                    graph.contractEdge(minDegreeVertex,vertexToContractWith);
            }

        }
        if( maxDegree>lowerbound )
            lowerbound = maxDegree;

    }

    public int getLowerBound() {
        return lowerbound;
    }
}