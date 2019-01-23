package at.tuwien.ac.graph.newgraph;


import java.util.List;

/**
 * Created by root on 4/7/17.
 */
public class FVDInstance implements Comparable<FVDInstance> {
    private GraphNew graph;
    private int parameter;

    private int graphBestLB ;

    public int getGraphBestLB() {
        return graphBestLB;
    }

    public void setGraphBestLB(int graphBestLB) {
        this.graphBestLB = graphBestLB;
    }

    public FVDInstance(GraphNew graph, int parameter, int bestLB) {
        this.graph = graph;
        this.parameter = parameter;
        this.graphBestLB = bestLB ;
    }


    public GraphNew getGraph() {
        return graph;
    }

    public void setGraph(GraphNew graph) {
        this.graph = graph;
    }

    public int getParameter() {
        return parameter;
    }

    public void setParameter(int parameter) {
        this.parameter = parameter;
    }

    @Override
    public int compareTo(FVDInstance o) {
        Integer otherSize = o.getGraph().getSize();
        Integer thisSize = graph.getSize();
        return thisSize.compareTo(otherSize);
    }
}
