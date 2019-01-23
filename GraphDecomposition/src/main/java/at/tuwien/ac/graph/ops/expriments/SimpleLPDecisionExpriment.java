package at.tuwien.ac.graph.ops.expriments;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by e1528895 on 8/26/17.
 */
@Entity
public class SimpleLPDecisionExpriment extends SimpleExpriment implements Comparable<SimpleLPDecisionExpriment>{

    private int c ;

    private int k;

    private int timeout ;

    private Integer solutionSize;

    private Boolean finished ;

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

    public Integer getSolutionSize() {
        return solutionSize;
    }

    public void setSolutionSize(Integer solutionSize) {
        this.solutionSize = solutionSize;
    }

    @Override
    public int compareTo(SimpleLPDecisionExpriment o) {
        return Integer.compare(c, o.getC());
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "SimpleLPDecisionExpriment{" +
                "c=" + c +
                ", k=" + k +
                ", timeout=" + timeout +
                ", solutionSize=" + solutionSize +
                ", finished=" + finished +
                " operationDuration=" + getOperationDuration()/1000 +
                '}';
    }
}
