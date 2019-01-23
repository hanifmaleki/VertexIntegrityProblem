package at.tuwien.ac.graph.tw;

public class MaximumMinimumDegreePlusMinD2 {

    protected NGraph<QuickBB.QuickBBData> graph;
    protected int lowerbound;

    /**
     * Starts out as a lowerbound of -infty; improved to the mindegree
     * lowerbound once run() is called.
     */
    public MaximumMinimumDegreePlusMinD2() {
        lowerbound = Integer.MIN_VALUE;
    }

    public String getName() {
        return "MMD+Min-d: Maximum Minimum Degree Plus min-d";
    }

    public void setInput(NGraph<QuickBB.QuickBBData> g) {
        graph = g.copy();
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
            NVertex<QuickBB.QuickBBData> minDegreeVertex = null;
            int min = Integer.MAX_VALUE;
            for(NVertex<QuickBB.QuickBBData> v : graph) {
                if( min > v.getNumberOfNeighbors() ) {
                    minDegreeVertex = v;
                    min = v.getNumberOfNeighbors();
                }
            }
            if( minDegreeVertex != null ){
                if( minDegreeVertex.getNumberOfNeighbors()>maxDegree) {
                    maxDegree = minDegreeVertex.getNumberOfNeighbors();
                }
                NVertex<QuickBB.QuickBBData> vertexToContractWith = null;
                int low = Integer.MAX_VALUE;
                for( NVertex<QuickBB.QuickBBData> other:  minDegreeVertex) {
                    if(low > other.getNumberOfNeighbors()) {
                        low = other.getNumberOfNeighbors();
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