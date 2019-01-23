package at.tuwien.ac.graph.ops.expriments;

import javax.persistence.Entity;

/**
 * Created by root on 5/15/17.
 */
@Entity
public class SeparationThresholdExperiment extends  Separation{


    private int threshold;

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public String toString() {
        return "SeparationThresholdExperiment{" +
                "threshold=" + threshold +
                ", size=" + getSize() +
                '}';
    }
}
