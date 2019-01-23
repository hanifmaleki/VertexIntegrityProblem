package at.tuwien.ac.graph.ops.expriments;

import at.tuwien.ac.graph.ops.report.Report;
import javax.persistence.*;
import java.util.List;

/**
 * Created by e1528895 on 10/15/17.
 */
@Entity
public class HeuristicPartialSolutionExperiment extends SimpleExpriment implements Comparable<HeuristicPartialSolutionExperiment>{
    private Integer lb ;
    private Integer ub ;

    @OneToMany(mappedBy="expriment", cascade= CascadeType.ALL)
    private List<VertexEntity> selectedVertices ;
    private int c ;


    public Integer getLb() {
        return lb;
    }

    public void setLb(Integer lb) {
        this.lb = lb;
    }

    public Integer getUb() {
        return ub;
    }

    public void setUb(Integer ub) {
        this.ub = ub;
    }

    public List<VertexEntity> getSelectedVertices() {
        return selectedVertices;
    }

    public void setSelectedVertices(List<VertexEntity> selectedVertices) {
        this.selectedVertices = selectedVertices;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }


    //public String getSeparationType() {
    //    return separationType;
    //}

    //public void setSeparationType(String separationType) {
    //    this.separationType = separationType;
    //}


    @Override
    public String toString() {
        return "Partial{" +
            "lb=" + lb +
            ", ub=" + ub +
            ", c=" + c +
            ", time=" + Report.millisecondsToFormat(getOperationDuration()) +
            '}';
    }

    @Override
    //Important: it is considered that the instances are same!!!
    public int compareTo(HeuristicPartialSolutionExperiment o) {
        return Integer.compare(getC(), o.getC());
    }


/*
    @ManyToOne
    @JoinColumn(name = "expriment_id")
    private HeuristicPartialSolutionExperiment parent ;

    //private String separationType;

    public HeuristicPartialSolutionExperiment getParent() {
        return parent;
    }

    public void setParent(HeuristicPartialSolutionExperiment parent) {
        this.parent = parent;
    }
*/

}
