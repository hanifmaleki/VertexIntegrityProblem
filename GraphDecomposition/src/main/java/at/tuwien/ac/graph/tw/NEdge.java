package at.tuwien.ac.graph.tw;

public class NEdge<D> {

    public NEdge( NVertex<D> a, NVertex<D> b ) {
        this.a = a;
        this.b = b;
    }
    public NVertex<D> a;
    public NVertex<D> b;

    public int hashCode() {
        return a.hashCode() ^ b.hashCode();
    }
    public boolean equals( Object o ) {
        if( o instanceof NEdge ) {
            NEdge e = (NEdge)o;
            return (a==e.a&&b==e.b) || (a==e.b&&b==e.a);
        } else return false;
    }

}
