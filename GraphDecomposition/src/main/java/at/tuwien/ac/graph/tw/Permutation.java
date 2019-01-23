package at.tuwien.ac.graph.tw;

public interface Permutation< D extends GraphInput.InputData> extends Algorithm<D> {

    /**
     * @return The computed permutation of the vertices.
     */
    public abstract NVertexOrder<D> getPermutation();

}