package at.tuwien.ac.graph.ops.ui;

import at.tuwien.ac.graph.ops.dao.HeuristicPartialDao;
import at.tuwien.ac.graph.ops.expriments.HeuristicPartialSolutionExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import java.util.ArrayList;
import java.util.Comparator;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Created by e1528895 on 3/18/18.
 */
public class HeuristicTableModel extends AbstractTableModel{

  String[] columns = {"c", "lb", "upperbound"};

  HeuristicPartialDao partialDao = new HeuristicPartialDao();

  List<HeuristicPartialSolutionExperiment> experimentList;

  public HeuristicTableModel(Instance instance) {
    System.out.println(partialDao.getAllExprimentsByInstance(instance).size());
    experimentList  = new ArrayList<>();
    for(int c=1; c <instance.getSize(); c++){
      HeuristicPartialSolutionExperiment experiment = partialDao.getExperiment(instance, c);
      if(experiment==null)
        continue;
      experimentList.add(experiment);
      System.out.println(experiment);
    }
    experimentList.sort(new Comparator<HeuristicPartialSolutionExperiment>() {
      @Override
      public int compare(HeuristicPartialSolutionExperiment o1,
          HeuristicPartialSolutionExperiment o2) {
        return Integer.compare(o1.getC(), o2.getC());
      }
    });
  }

  @Override
  public int getRowCount() {
    return experimentList.size();
  }

  @Override
  public int getColumnCount() {
    return columns.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    HeuristicPartialSolutionExperiment experiment = experimentList.get(rowIndex);
    switch(columnIndex){
      case 0: return String.valueOf(experiment.getC());
      case 1: return String.valueOf(experiment.getLb());
      case 2:return String.valueOf(experiment.getUb());
    }
    return null;
  }

  @Override
  public String getColumnName(int column) {
    return columns[column];
  }

  public HeuristicPartialSolutionExperiment getSelectedExperiment(int selectedRow) {
    return experimentList.get(selectedRow);
  }
}
