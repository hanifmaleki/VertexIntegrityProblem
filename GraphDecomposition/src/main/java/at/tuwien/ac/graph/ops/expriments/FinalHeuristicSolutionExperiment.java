package at.tuwien.ac.graph.ops.expriments;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by e1528895 on 10/15/17.
 */
@Entity
public class FinalHeuristicSolutionExperiment extends SimpleExpriment{
    private int cLowerBound;
    private int kLowerBound ;
    private int c ;
    private int k ;

    @OneToMany(mappedBy="expriment", cascade= CascadeType.ALL)
    List<VertexEntity> selectedVertices ;

    public int getcLowerBound() {
        return cLowerBound;
    }

    public void setcLowerBound(int cLowerBound) {
        this.cLowerBound = cLowerBound;
    }

    public int getkLowerBound() {
        return kLowerBound;
    }

    public void setkLowerBound(int kLowerBound) {
        this.kLowerBound = kLowerBound;
    }

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

    public List<VertexEntity> getSelectedVertices() {
        return selectedVertices;
    }

    public void setSelectedVertices(List<VertexEntity> selectedVertices) {
        this.selectedVertices = selectedVertices;
    }

    @Override
    public String toString() {
        String fileName = "null";
        if(getInstance()!=null)
            fileName=getInstance().getFileName();
        return "FinalHeuristicSolutionExperiment{" +
                "cLowerBound=" + cLowerBound +
                ", kLowerBound=" + kLowerBound +
                ", c=" + c +
                ", k=" + k +
                ", name="+ fileName +
                '}';
    }
}
