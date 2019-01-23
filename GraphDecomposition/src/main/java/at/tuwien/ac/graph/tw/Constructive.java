package at.tuwien.ac.graph.tw;

public interface Constructive<D extends GraphInput.InputData> extends Algorithm<D> {
    /**
     * Returns the found tree decomposition.<br/>
     * This method does <b>not</b> compute the tree decomposition; you must first call run().
     * @return The computed tree decomposition.
     */
    public abstract NGraph<NTDBag<D>> getDecomposition();
}