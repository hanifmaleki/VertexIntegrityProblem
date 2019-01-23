package at.tuwien.ac.graph.newgraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by e1528895 on 8/19/18.
 * This class is intended for finding all solutions of the problem
 */
public class BasicFVDSolver {

  //private List<List<Integer>> solutions = new ArrayList<>();

  public List<List<Integer>> getAllsolutionsOf(GraphNew graph, int c, int k){
    ArrayList<Integer> solution = new ArrayList<>();
    List<List<Integer>> solutions = new ArrayList<>();
    solutions.add(solution);
    if (graph.getSize()<=c)
      return solutions;

    if (k<1)
      return null;

    List<GraphNew> connectedComponents = GraphNewHelper.getConnectedComponentsHS(graph);

    if(connectedComponents.size()>1){
      List<List<Integer>> newSolutions;
      for(GraphNew component: connectedComponents){
        List<List<Integer>> componentSolutions = getAllsolutionsOf(component, c, k);
        for(List<Integer> sol: solutions){
          for(List<Integer> compSol: componentSolutions){
            compSol.addAll(sol);
          }
        }
      }
    }
    return solutions;
  }


}
