package at.tuwien.ac.graph.tw;

import java.util.ArrayList;

public class AllStartMaximumMinimumDegree<D extends GraphInput.InputData> implements LowerBound<D> {

    protected NGraph<D> graph;
    protected int lowerbound;

    /**
     * Starts out as a lowerbound of -infty; improved to the mindegree
     * lowerbound once run() is called.
     */
    public AllStartMaximumMinimumDegree() {
        lowerbound = Integer.MIN_VALUE;
    }

    public String getName() {
        return "All Start Maximum Minimum Degree";
    }


    public void setInput(NGraph<D> g) {
        graph = g.copy( );
    }

    public void run() {
		/* MMD(Graph G) ::
		 * H = G
		 * maxmin = 0
		 * while H has at least two vertices do
		 * 	Select a vertex v from H that has minimum degree in H.
		 * 	maxmin = max( maxmin, dH(v) ).
		 * 	Remove v and its incident edges from H.
		 * end while
		 * return maxmin
		 */

        // initial value for finding minimum: higher than any degree

        int maxDegree = goRecursive(graph);
        if( maxDegree>lowerbound ) lowerbound = maxDegree;

    }

    private int goRecursive(NGraph<D> g) {
        //List with start vertices with min degree
        ArrayList<NVertex<D>> startVertices = new ArrayList<NVertex<D>>();

        int min = Integer.MAX_VALUE;
        for( NVertex<D> v : g ) {
            if( v.getNumberOfNeighbors() < min  &&  v.getNumberOfNeighbors() > 0) {
                startVertices.clear();
                startVertices.add(v);
                min = v.getNumberOfNeighbors();
            }
        }
        int maxDegree = 0;
        for(NVertex<D> startV : startVertices) {

            NGraph<D> graphcopy = g.copy();
            NVertex<D> minDegreeVertex = null;

            //Zoek de startV in de nieuwe graaf
            for(NVertex<D> newV : graphcopy) {
                if(newV.data == startV.data)
                    minDegreeVertex = newV;
            }


            if( minDegreeVertex != null ){
                if( minDegreeVertex.getNumberOfNeighbors()>maxDegree) {
                    maxDegree = minDegreeVertex.getNumberOfNeighbors();
                }
                graphcopy.removeVertex(minDegreeVertex);
            }

            int recResult = goRecursive(graphcopy);
            maxDegree = Math.max(maxDegree,recResult);
        }
        return maxDegree;
    }

    public int getLowerBound() {
        return lowerbound;
    }
}