package at.tuwien.ac.graph.tw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ListGraph<D> extends NGraph< D > {

    /**
     * <p>The vertex list for this graph is stored in an ArrayList</p>
     * <p>TODO This would be nicer as a protected field, but right now
     * it is convenient to have it public.</p>
     */
    public ArrayList<NVertex<D>> vertices;

    public ListGraph() {
        vertices = new ArrayList<NVertex<D>>();
    }

    @Override
    public NVertex<D> getVertex(int i) {
        return vertices.get(i);
    }

    @Override
    public Iterator<NVertex<D>> getVertices() {
        return vertices.iterator();
        //return new BorkIterator<NVertex<D>>( vertices );
    }

    public int getNumberOfVertices() {
        return vertices.size();
    }

    @Override
    public void addVertex(NVertex<D> v) {
        vertices.add( v );
    }

    @Override
    public void removeVertex(NVertex<D> v) {
        vertices.remove( v );
        for(NVertex<D> ov : v)
            ov.removeNeighbor(v);
    }

    @Override
    public void setVertices(ArrayList<NVertex<D>> vs) {
        vertices = vs;
    }

    @Override
    public NGraph<D> copy() {

        NGraph<D> newG = new ListGraph<D>();

        HashMap<NVertex<D>, NVertex<D>> oldToNew = new HashMap<NVertex<D>, NVertex<D>>();

        for( NVertex<D> v : this ) {
            NVertex<D> nv = v.newOfSameType( v.data );
            newG.addVertex( nv );
            oldToNew.put( v, nv );
        }
        int size = getNumberOfVertices();
        for( int i=0; i<size; ++i ) {
            NVertex<D> v = vertices.get(i);
            for( NVertex<D> n : v ) {
                oldToNew.get(v).addNeighbor(oldToNew.get(n));
            }
        }

        return newG;

    }

}