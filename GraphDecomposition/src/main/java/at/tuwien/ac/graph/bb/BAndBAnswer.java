package at.tuwien.ac.graph.bb;

import java.util.List;

/**
 * Created by e1528895 on 9/9/17.
 */
public class BAndBAnswer {

    enum Status {SIMPLE, BOUND_PRUNED, SYMMETRY_NULL, STOP_FLAG}

    List<Integer> vertices ;

    Status status ;

    public BAndBAnswer(List<Integer> vertices) {
        this.vertices = vertices;
    }

    public List<Integer> getVertices() {
        return vertices;
    }

    public void setVertices(List<Integer> vertices) {
        this.vertices = vertices;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
