package at.tuwien.ac.graph.tw;

public interface UpperBound< D extends GraphInput.InputData> extends Algorithm<D> {

    /**
     * Returns the upperbound.
     *
     * @return A valid upperbound. If run() has not been called, Integer.MAX_VALUE is returned.
     */
    public abstract int getUpperBound();

}