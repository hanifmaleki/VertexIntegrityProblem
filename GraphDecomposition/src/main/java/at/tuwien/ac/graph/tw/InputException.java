package at.tuwien.ac.graph.tw;

public class InputException extends Exception {

    /**
     * Generated ID for serialization.
     */
    private static final long serialVersionUID = 638210276339222780L;

    /**
     * The exception that is being
     */
    public Exception originalException;

    /**
     * Remembers an original <code>Exception</code>.
     * @param e The original
     */
    public InputException( Exception e ) {
        originalException = e;
    }

    public String toString() {
        return originalException.toString();
    }

}