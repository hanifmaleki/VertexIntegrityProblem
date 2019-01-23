package at.tuwien.ac.graph.tw;

import java.util.Collections;

public class AllStartMaximumCardinalitySearch<D extends GraphInput.InputData> implements Permutation<D>, LowerBound<D> {

    protected NGraph<MCSData> graph;
    protected NGraph<D> originalGraph;
    protected NVertexOrder<D> vertexOrder;
    private int lowerbound = Integer.MIN_VALUE;

    public AllStartMaximumCardinalitySearch() {
        vertexOrder = new NVertexOrder<D>();
    }

    public NVertexOrder<D> getPermutation() {
        return vertexOrder;
    }

    public String getName() {
        return "All Start MCS;  All Start Maximum Cardinality Search Algorithm";
    }

    class MCSData extends GraphInput.InputData {
        public MCSData(NVertex old) {super( (Integer)old.data, old.data+"" );	}
        int value;
        boolean visited;
        public NVertex<D> original;
        public NVertex<D> getOriginal() {return original;}
    }
    class MyConvertor implements NGraph.Convertor<D,MCSData> {
        public MCSData convert( NVertex old ) {
            MCSData d = new MCSData( old );
            d.value = 0;
            d.visited = false;
            d.original = old;
            return d;
        }

    }
    public void setInput(NGraph<D> g) {
        originalGraph = g;
        graph = g.copy(  new MyConvertor() );
    }

    //TODO Make O(n)-implementation

    public void run() {
		/*
		 * Algorithm MaximumCardinalitySearch - MCS
		 * Input: A graph G.
		 * Output: An elimination ordering ? of G.
		 * begin
		 * for all vertices v in G do w(v) = 0;
		 * for i = n downto 1 do
		 * 	Choose an unnumbered vertex z of maximum weight; ?(z) = i;
		 * 	for all unnumbered vertices y ? N(z) do w(y) = w(y) + 1;
		 * end
		 */
        int bestUpperBound = Integer.MAX_VALUE;
        for( int startVertex=0; startVertex<graph.getNumberOfVertices(); ++startVertex ){

            for(NVertex<MCSData> v : graph){
                v.data.visited = false;
                v.data.value = 0;
            }

            NVertexOrder<D> order =  new NVertexOrder<D>();

            //Set start vertex
            NVertex<MCSData> first = graph.getVertex(startVertex);
            //Update neigbours
            updateNeighbours(first);
            //Add first vertex to the permuation
            order.order.add( first.data.getOriginal() );


            for( int i = graph.getNumberOfVertices()-2; i >= 0; --i ) {
                //Find unnumbered neigbhour with max weight
                int max = 0;
                NVertex<MCSData> z = null;
                for( NVertex<MCSData> v : graph ) {
                    if( !v.data.visited && v.data.value>=max ) {
                        z = v;
                        max = v.data.value;
                    }
                }
                z.data.visited = true;
                order.order.add(z.data.getOriginal());

                //w(y) = w(y) + 1 for all unnumbered neighbours of z;
                int k = 0;
                for( NVertex<MCSData> other : z ) {
                    if( ! other.data.visited ) {
                        ++other.data.value;
                    } else ++k;
                }
                if( lowerbound < k ) lowerbound = k;
            }
            //Reverse the permuation list
            Collections.reverse(order.order);

            //Get the treedecomposition from the given ordering
            PermutationToTreeDecomposition<D> pttd = new PermutationToTreeDecomposition<D>(order);
            pttd.setInput(originalGraph);
            pttd.run();
            int newUpper = pttd.getUpperBound();
            //Compare the new upperbound with the best upperbound so far.
            if( bestUpperBound > newUpper) {
                vertexOrder = order;
                bestUpperBound = newUpper;
            }
        }
    }

    public void updateNeighbours(NVertex<MCSData> v) {
        v.data.visited = true;
        for( NVertex<MCSData> other : v ) {
            if( !other.data.visited ) {
                ++other.data.value;
            }
        }
    }

    public int getLowerBound() {
        return lowerbound;
    }

}