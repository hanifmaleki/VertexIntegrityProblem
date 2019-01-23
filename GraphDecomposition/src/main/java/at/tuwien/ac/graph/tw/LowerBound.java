package at.tuwien.ac.graph.tw;

public interface LowerBound< D extends GraphInput.InputData> extends Algorithm<D> {

    /**
     * @return A valid lowerbound. See class documentation for
     * details.
     */
    public abstract int getLowerBound();

    public static interface Creator {
        public LowerBound<GraphInput.InputData> create();
    }

}