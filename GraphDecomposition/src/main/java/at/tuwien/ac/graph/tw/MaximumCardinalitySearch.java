package at.tuwien.ac.graph.tw;

import java.util.Collections;

public class MaximumCardinalitySearch<D extends GraphInput.InputData> implements Permutation<D>, LowerBound<D> {

    protected NGraph<MCSData> graph;
    protected NVertexOrder<D> vertexOrder;
    private int lowerbound = Integer.MIN_VALUE;

    public MaximumCardinalitySearch() {
        vertexOrder = new NVertexOrder<D>();
    }

    public NVertexOrder<D> getPermutation() {
        return vertexOrder;
    }

    public String getName() {
        return "MCS; Maximum Cardinality Search Algorithm";
    }

    class MCSData extends GraphInput.InputData {
        public MCSData(NVertex old) {
            super( (Integer)old.data, ""+old.data );
        }
        int value;
        boolean visited;
        public NVertex original;
        public NVertex getOriginal() {return original;}
        public String toString() {
            String s = super.toString();
            s = s.concat( " (" + value + (visited?"; visited":"")+ ")" );
            return s;
        }
    }
    class MyConvertor implements NGraph.Convertor<D,MCSData> {
        public MCSData convert( NVertex<D> old ) {
            MCSData d = new MCSData( old );
            d.value = 0;
            d.visited = false;
            d.original = old;
            return d;
        }

    }
    public void setInput(NGraph<D> g) {
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

        //Output.present( graph, "MCS" );

        for( int i = graph.getNumberOfVertices()-1; i >= 0; --i ) {
            //Find unnumbered neigbhour with max weight
            int max = 0;
            NVertex<MCSData> z = null;
            for( NVertex<MCSData> v : graph ) {
                if( !v.data.visited && v.data.value>=max ) {
                    z = v;
                    max = v.data.value;
                }
            }
            if(z==null)
                continue;

            z.data.visited = true;
            vertexOrder.order.add(z.data.getOriginal());

            //w(y) = w(y) + 1 for all unnumbered neighbours of z;
            int k = 0;
            for( NVertex<MCSData> other : z ) {
                if( ! other.data.visited ) {
                    ++other.data.value;
                } else ++k;
            }
            if( lowerbound < k ) lowerbound = k;

            //Output.present( graph, "MCS" );
        }
        Collections.reverse(vertexOrder.order);
    }

    public int getLowerBound() {
        return lowerbound;
    }

}