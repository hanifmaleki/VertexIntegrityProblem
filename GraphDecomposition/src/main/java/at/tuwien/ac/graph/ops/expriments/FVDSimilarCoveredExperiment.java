package at.tuwien.ac.graph.ops.expriments;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by e1528895 on 9/17/17.
 */
@Entity
public class FVDSimilarCoveredExperiment extends SimpleExpriment implements Comparable<FVDSimilarCoveredExperiment>{
    private Integer c ;

    private Integer k ;

    private  Integer timeout ;

    private Integer fvdSize ;

    private Boolean finished ;

    private int lb ;

    private int ub ;

    @OneToMany(mappedBy="expriment", cascade= CascadeType.REFRESH)
    private List<VertexEntity> selectedVertices;


    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getFvdSize() {
        return fvdSize;
    }

    public void setFvdSize(Integer fvdSize) {
        this.fvdSize = fvdSize;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
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

    public int getUb() {
        return ub;
    }

    public void setUb(int ub) {
        this.ub = ub;
    }

    @Override
    public int compareTo(FVDSimilarCoveredExperiment o) {
        if(c!=o.c)
            return Integer.compare(c, o.c);
        return Integer.compare(k, o.k);
    }

    @Override
    public String toString() {
        return "SimpleFVDExperiment{" +
                "c=" + c +
                ", k=" + k +
                ", timeout=" + timeout +
                ", fvdSize=" + fvdSize +
                ", finished=" + finished +
                ", lb=" + lb +
                ", ub=" + ub +
                ", duration=" + getOperationDuration() +
                '}';
    }
}
