package at.tuwien.ac.graph.tw;

import java.util.HashSet;

public class MaximumMinimumDegreePlusLeastC<D extends GraphInput.InputData> implements LowerBound<D> {

    protected NGraph<D> graph;
    protected int lowerbound;

    /**
     * Starts out as a lowerbound of -infty; improved to the mindegree
     * lowerbound once run() is called.
     */
    public MaximumMinimumDegreePlusLeastC() {
        lowerbound = Integer.MIN_VALUE;
    }

    public String getName() {
        return "MMD+Least-c: Maximum Minimum Degree Plus least-c";
    }

    public void setInput(NGraph<D> g) {
        // This algorithm works straight on the standard Graph data,
        // so just remember it.
        graph = g.copy( );
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
        int numNodes = graph.getNumberOfVertices();
        for( int i=0; i<numNodes; ++i) {

            //Select a vertex from the graph that has minimum degree
            NVertex<D> minDegreeVertex = null;
            int min = Integer.MAX_VALUE;
            for(NVertex<D> v : graph) {
                if( min > v.getNumberOfNeighbors() ) {
                    minDegreeVertex = v;
                    min = v.getNumberOfNeighbors();
                }
            }
            if( minDegreeVertex != null ){
                //if the the maxdegree of the selected vertex > current high:
                //set maxdegree as new lowerbound
                if( minDegreeVertex.getNumberOfNeighbors()>maxDegree) {
                    maxDegree = minDegreeVertex.getNumberOfNeighbors();
                }

                NVertex<D> vertexToContractWith = null;

                HashSet<NVertex<D>> newStartVNeighbors = new HashSet<NVertex<D>>();
                for(NVertex<D> v : minDegreeVertex)
                    newStartVNeighbors.add(v);

                int sameN = Integer.MAX_VALUE;
                for( NVertex<D> other:  minDegreeVertex) {
                    //Loop over buren van buur
                    int same = 0;
                    for(NVertex<D> bb : other)
                        if(newStartVNeighbors.contains(bb))
                            ++same;

                    if(same<sameN) {
                        vertexToContractWith = other;
                        sameN = same;
                    }
                }
                if( vertexToContractWith!=null ) {
                    graph.contractEdge(minDegreeVertex,vertexToContractWith);
                }
            }
        }
        if( maxDegree>lowerbound ) lowerbound = maxDegree;

    }

    public int getLowerBound() {
        return lowerbound;
    }
}