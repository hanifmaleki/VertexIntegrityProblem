package at.tuwien.ac.graph.ops.expriments;

import at.tuwien.ac.graph.ops.expriments.SimpleExpriment;

import javax.persistence.Entity;

/**
 * Created by root on 7/28/17.
 */
@Entity
public class MDSTLBExpriment extends SimpleExpriment{
    private int lowerBound ;
    private int c;
    private int maximumDegree;

    public int getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getC() {
        return c;
    }

    public int getMaximumDegree() {
        return maximumDegree;
    }

    public void setMaximumDegree(int maximumDegree) {
        this.maximumDegree = maximumDegree;
    }
}
