package at.tuwien.ac.graph.tw;

import java.util.Iterator;

public abstract class NVertex< D > implements Iterable<NVertex<D>> {

    public D data;

    public NVertex() {}
    public NVertex( D d ) {
        data = d;
    }

    // ====== To be implemented by implementing classes

    public abstract <T> NVertex<T> newOfSameType( T d );

    public abstract boolean isNeighbor( NVertex<D> v );
    public abstract boolean ensureNeighbor( NVertex<D> v );
    public abstract void addNeighbor( NVertex<D> v ); // precond: v is not a neighbor of this.


    public abstract void removeNeighbor( NVertex<D> v );

    public abstract Iterator<NVertex<D>> getNeighbors();
    public abstract int getNumberOfNeighbors();

    public Iterator<NVertex<D>> iterator() {
        return getNeighbors();
    }

    public abstract NVertex<D> copy();

}