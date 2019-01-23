package at.tuwien.ac.graph.ops.ui;

import at.tuwien.ac.graph.ops.dao.FinalHeuristicDao;
import at.tuwien.ac.graph.ops.dao.HeuristicPartialDao;
import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.dao.SeparationDao;
import at.tuwien.ac.graph.ops.dao.SimpleLPDao;
import at.tuwien.ac.graph.ops.expriments.HeuristicPartialSolutionExperiment;
import at.tuwien.ac.graph.ops.expriments.Separation;
import at.tuwien.ac.graph.ops.expriments.SimpleLPExpriment;
import at.tuwien.ac.graph.ops.instance.Instance;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by e1528895 on 3/18/18.
 */
public class HeuristicTreeReport extends JFrame {

  private JTree tree;
  FinalHeuristicDao finalHeuristicDao;
  HeuristicPartialDao partialDao ;

  public HeuristicTreeReport(HeuristicPartialSolutionExperiment experiment, Instance instance) {
    DefaultMutableTreeNode root = makeTree(experiment, instance, "");

    //create the tree by passing in the root node
    tree = new JTree(root);
    add(tree);

    this.setTitle(instance.getFileName());
    this.pack();
    this.setVisible(true);
  }

  SimpleLPDao lpDao = new SimpleLPDao();
  SeparationDao separationDao = new SeparationDao();
  InstanceManager instanceManager = new InstanceManager();
  HeuristicPartialDao heuristicPartialDao = new HeuristicPartialDao();
  private DefaultMutableTreeNode makeTree(HeuristicPartialSolutionExperiment experiment, Instance instance, String prefix) {


    SimpleLPExpriment lpExprience = lpDao
        .getBestExprience(instance, experiment.getC());
    if (lpExprience == null) {
      System.out.println("Error: can not find lp for "+ experiment);
      return null;
    }
    List<Separation> allExprimentsByInstance = separationDao.getAllExprimentsByInstance(instance);
    //create the root node
    DefaultMutableTreeNode root = new DefaultMutableTreeNode(prefix + experiment.toString());
    //create the child nodes
    DefaultMutableTreeNode lpNode = new DefaultMutableTreeNode(lpExprience);
    //add the child nodes to the root node
    root.add(lpNode);
    for(Separation separation: allExprimentsByInstance){
      DefaultMutableTreeNode sepNode = new DefaultMutableTreeNode(separation);
      List<Instance> subinstances = instanceManager.getSubinstances(instance, separation);
      if(subinstances==null)
        continue;
      int counter = 0 ;
      for(Instance subInstance: subinstances) {
        counter++;
        HeuristicPartialSolutionExperiment childExperiment = heuristicPartialDao
            .getExperiment(subInstance, experiment.getC());
        if (childExperiment == null)
          continue;
        String subPrefix = counter + "-" ;
        DefaultMutableTreeNode node = makeTree(childExperiment, subInstance,
            subPrefix);
        sepNode.add(node);
      }
      if(!sepNode.isLeaf())
      root.add(sepNode);
    }
    return root ;

  }


}
