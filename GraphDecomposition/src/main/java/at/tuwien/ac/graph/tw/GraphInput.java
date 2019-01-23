package at.tuwien.ac.graph.tw;

public interface GraphInput {

    public static class InputData {
        public InputData() {}
        public InputData( int id, String name ) {
            this.id = id;
            this.name = name;
        }
        public int id;
        public String name;
        public String toString() {
            return name;
        }
    }

    /**
     * Return a graph. Implementing classes will have additional
     * interface to give meaning to this call.
     *
     * @return A graph; entirely up to implementing classes what
     * to return.
     * @throws InputException
     */
    abstract public NGraph<InputData> get() throws InputException;

}