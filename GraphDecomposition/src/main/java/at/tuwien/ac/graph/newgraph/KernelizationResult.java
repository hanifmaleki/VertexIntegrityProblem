package at.tuwien.ac.graph.newgraph;

import java.util.List;

/**
 * Created by e1528895 on 8/16/17.
 */
public class KernelizationResult {
    private List<FVDInstance> fvdInstanceList ;
    private List<Integer> removedVertices ;

    public KernelizationResult(List<FVDInstance> fvdInstanceList, List<Integer> removedVertices) {
        this.fvdInstanceList = fvdInstanceList;
        this.removedVertices = removedVertices;
    }

    public List<FVDInstance> getFvdInstanceList() {
        return fvdInstanceList;
    }

    public void setFvdInstanceList(List<FVDInstance> fvdInstanceList) {
        this.fvdInstanceList = fvdInstanceList;
    }

    public List<Integer> getRemovedVertices() {
        return removedVertices;
    }

    public void setRemovedVertices(List<Integer> removedVertices) {
        this.removedVertices = removedVertices;
    }


}
