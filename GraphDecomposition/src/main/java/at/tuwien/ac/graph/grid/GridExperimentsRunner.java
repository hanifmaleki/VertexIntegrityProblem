package at.tuwien.ac.graph.grid;


import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.ops.executors.SimpleSatExecutor;
import at.tuwien.ac.graph.ops.expriments.SimpleSatExperiment;
import at.tuwien.ac.graph.samples.Grid;
import at.tuwien.ac.graph.sat.SatAnswer;
import at.tuwien.ac.graph.utils.IncidentGraphHelper;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by e1528895 on 11/2/18.
 */
public class GridExperimentsRunner {

  public static void main(String[] args) {
    int gridSize = 7;
    int c = 11;
    int k = 11;

    GraphNew newStructure = IncidentGraphHelper.getNewStructure(new Grid(gridSize).getGraph());
    SimpleSatExperiment experiment = new SimpleSatExecutor()
        .doSimpleExperiment(newStructure, c, k, 600000);

    //SatAnswer experiment = GridSymmetrySatSolver
    //    .getFVDBySAT(gridSize, c, k, 600000);

    if(experiment.getSelectedVertices()!= null) {
      List<Integer> collect = experiment.getSelectedVertices().stream().map(ve -> {
        System.out.print(ve.getVertex() + "\t");
        return ve.getVertex();
      })
          .collect(Collectors.toList());
      System.out.println();
      GraphNewHelper.testAnsShowBackdoor(
          IncidentGraphHelper.getNewStructure(new Grid(gridSize).getGraph()), collect);
      new GridFrame(gridSize,gridSize,c,collect.size(), collect);
    }
  }

}
