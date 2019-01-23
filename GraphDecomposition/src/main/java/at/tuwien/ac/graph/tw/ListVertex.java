package at.tuwien.ac.graph.tw;

import java.util.ArrayList;
import java.util.Iterator;

public class ListVertex< D > extends NVertex<D> {

    public ArrayList<NVertex<D>> neighbors;

    public <T> ListVertex<T> newOfSameType( T d ) {
        return new ListVertex<T>( d );
    }

    public ListVertex() { init(); }
    public ListVertex( D d ) { super(d); init(); }
    private void init() {
        neighbors = new ArrayList<NVertex<D>>();
    }

    @Override
    public boolean isNeighbor(NVertex<D> v) {
        return neighbors.contains( v );
    }
    @Override
    public boolean ensureNeighbor(NVertex<D> v) {
        if( !isNeighbor(v) ) {
            neighbors.add( v );
            return true;
        } else return false;
    }
    @Override
    public void addNeighbor(NVertex<D> v) {
        neighbors.add( v );
    }


    @Override
    public Iterator<NVertex<D>> getNeighbors() {
        return neighbors.iterator();
    }
    @Override
    public void removeNeighbor(NVertex<D> v) {
        neighbors.remove(v);
    }

    public ListVertex<D> copy() {
        return new ListVertex<D>(data);
    }

    @Override
    public int getNumberOfNeighbors() {
        return neighbors.size();
    }

}