package at.tuwien.ac.graph.bb;

import at.tuwien.ac.graph.newgraph.GraphNew;

/**
 * Created by e1528895 on 9/9/17.
 */
public class BAndBParameter {

    GraphNew graph ;

    int c ;

    Integer lastBranch ;

    Integer bbLb ;

    Integer bestBound ;

    public BAndBParameter(GraphNew graph, int c, Integer lastBranch, Integer bbLb, Integer bestBound) {
        this.graph = graph;
        this.c = c;
        this.lastBranch = lastBranch;
        if(bbLb<0)
            bbLb=0;
        this.bbLb = bbLb;
        this.bestBound = bestBound;
    }

    public GraphNew getGraph() {
        return graph;
    }

    public void setGraph(GraphNew graph) {
        this.graph = graph;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public Integer getLastBranch() {
        return lastBranch;
    }

    public void setLastBranch(Integer lastBranch) {
        this.lastBranch = lastBranch;
    }

    public Integer getBbLb() {
        return bbLb;
    }

    public void setBbLb(Integer bbLb) {
        this.bbLb = bbLb;
    }

    public Integer getBestBound() {
        return bestBound;
    }

    public void setBestBound(Integer bestBound) {
        this.bestBound = bestBound;
    }
}
