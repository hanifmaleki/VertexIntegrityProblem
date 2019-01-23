package at.tuwien.ac.graph.tw;

/**
 * Created by root on 4/25/17.
 */
public interface Algorithm< D extends GraphInput.InputData> {

    /**
     * Every algorithm has a name. This is for identification towards the user.
     * @return Name of the algorithm.
     */
    public abstract String getName();

    /**
     * Sets the input the algorithm will run on.
     * @param g The graph in standard graph format.
     */
    public abstract void setInput(NGraph<D> g);

    /**
     * Does the actual computation of the algorithm. The result is remembered but not returned. Get it using the appropriate return function (getUpperbound(), getAllStartMaximumMinimumDegree(), etc.).<br />
     * Only works after setInput has been called.
     */
    public abstract void run();

}