package at.tuwien.ac.graph.ops.expriments;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by e1528895 on 8/29/17.
 */
@Entity
public class SimpleSatExperiment extends SimpleExpriment implements Comparable<SimpleSatExperiment>{
    private Integer c ;

    private Integer k ;

    private Integer fvdSize ;

    private Integer timeout ;

    private Boolean finished ;

    private String satDuration;

    @OneToMany(mappedBy="expriment", cascade= CascadeType.ALL)
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

    public Integer getFvdSize() {
        return fvdSize;
    }

    public void setFvdSize(Integer fvdSize) {
        this.fvdSize = fvdSize;
    }

    public List<VertexEntity> getSelectedVertices() {
        return selectedVertices;
    }

    public void setSelectedVertices(List<VertexEntity> selectedVertices) {
        this.selectedVertices = selectedVertices;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }


    @Override
    public int compareTo(SimpleSatExperiment o) {
        if(c!=o.c)
            return Integer.compare(c, o.c);
        return Integer.compare(k, o.k);
    }

    public void setSatDuration(String satDuration) {
        this.satDuration = satDuration;
    }

    public String getSatDuration() {
        return satDuration;
    }

    @Override
    public String toString() {
        return "SimpleSatExperiment{" +
                "c=" + c +
                ", k=" + k +
                ", fvdSize=" + fvdSize +
                ", timeout=" + timeout +
                ", finished=" + finished +
                ", satDuration='" + satDuration + '\'' +
                '}';
    }
}
