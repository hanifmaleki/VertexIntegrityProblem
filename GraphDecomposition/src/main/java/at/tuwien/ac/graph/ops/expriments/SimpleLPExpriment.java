package at.tuwien.ac.graph.ops.expriments;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by e1528895 on 8/26/17.
 */
@Entity
public class SimpleLPExpriment extends SimpleExpriment implements Comparable<SimpleLPExpriment>{

    private int c ;

    private int k;

    private int timeout ;

    private int lb ;

    @OneToMany(mappedBy="expriment", cascade= CascadeType.ALL)
    private List<VertexEntity> selectedVertices;

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public List<VertexEntity> getSelectedVertices() {
        return selectedVertices;
    }

    public void setSelectedVertices(List<VertexEntity> selectedVertices) {
        this.selectedVertices = selectedVertices;
    }

    public int getLb() {
        return lb;
    }

    public void setLb(int lb) {
        this.lb = lb;
    }

    @Override
    public int compareTo(SimpleLPExpriment o) {
        return Integer.compare(c, o.getC());
    }

    @Override
    public String toString() {
        return "SimpleLPExpriment{" +
                "operationDuration=" + getOperationDuration() +
                ", duration=" + getOperationDuration()/1000 +
                ", c=" + c +
                ", k=" + k +
                ", timeout=" + timeout +
                ", lb=" + lb +
                '}';
    }
}
