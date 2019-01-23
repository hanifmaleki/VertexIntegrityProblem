package at.tuwien.ac.graph.tw;

import java.util.ArrayList;

public class NVertexOrder< D > {

    public NVertexOrder() {
        order = new ArrayList<NVertex<D>>();
    }
    public NVertexOrder( int size ) {
        order = new ArrayList<NVertex<D>>( size );
    }
    public NVertexOrder( NVertexOrder<D> other ) {
        order = new ArrayList<NVertex<D>>( other.order );
    }

    public ArrayList<NVertex<D>> order;

    public String toString() {
        String s = "";
        for( NVertex<D> v : order) {
            s = s.concat( " " + v.data.toString() );
        }
        return s;
    }
}