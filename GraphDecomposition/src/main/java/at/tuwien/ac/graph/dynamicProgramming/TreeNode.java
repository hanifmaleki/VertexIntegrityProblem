package at.tuwien.ac.graph.dynamicProgramming;

import at.tuwien.ac.graph.newgraph.GraphNew;
import java.util.Set;

/**
 * Created by e1528895 on 2/9/18.
 */
public class TreeNode {
    Set<Integer> bag ;
    private TreeNode child;
    private GraphNew correspondingGraph;
    Set<Record> records ;


    public void setBag(Set<Integer> bag) {
        this.bag = bag;
    }


    public void setChild(TreeNode child) {
        this.child = child;
    }

    public TreeNode getChild() {
        return child;
    }

    public Set<Integer> getBag() {
        return bag;
    }

    public GraphNew getCorrespondingGraph() {
        return correspondingGraph;
    }

    public void setCorrespondingGraph(GraphNew correspondingGraph) {
        this.correspondingGraph = correspondingGraph;
    }

  public Set<Record> getRecords() {
    return records;
  }

  public void setRecords(Set<Record> records) {
    this.records = records;
  }
}
