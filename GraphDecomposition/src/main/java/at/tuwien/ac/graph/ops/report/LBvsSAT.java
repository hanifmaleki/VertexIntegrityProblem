package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.dao.LBMinorWidthDao;
import at.tuwien.ac.graph.ops.dao.MDSTLBDao;
import at.tuwien.ac.graph.ops.expriments.LBMinorWidthExpriment;
import at.tuwien.ac.graph.ops.expriments.MDSTLBExpriment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by root on 8/1/17.
 */
public class LBvsSAT extends Report {
  Logger logger = LoggerFactory.getLogger(LBvsSAT.class);
  InstanceManager instanceManager = new InstanceManager();
  MDSTLBDao mdstlbDao = new MDSTLBDao();
  LBMinorWidthDao lbMinorWidthDao = new LBMinorWidthDao();

  Hashtable<Instance, LBMinorWidthExpriment> hashTable1 = new Hashtable<>();
  Hashtable<Instance, MDSTLBExpriment> hashTable2 = new Hashtable<>();
  List<Instance> instances = null;

  //String[] columns = {"Row", "Name", "Vertices", "Edges", "Max Deg", "\\#Max Deg", "BEST\\_LB",
  //    "SAT\\_LB"};
  String[] columns = {"Name", "$|V|$", "$|E|$", "BEST\\_LB", "SAT\\_LB"};
  private int rowCounter = 1;

  public LBvsSAT() {
    List<LBMinorWidthExpriment> lbMinorWidthExprimentList = lbMinorWidthDao.getAll();
    for (LBMinorWidthExpriment expriment : lbMinorWidthExprimentList) {
      Instance instance = instanceManager.getById(expriment.getInstance().getId());
      hashTable1.put(instance, expriment);
    }

    List<MDSTLBExpriment> mdstlbExpriments = mdstlbDao.getAll();
    for (MDSTLBExpriment expriment : mdstlbExpriments) {
      Instance instance = instanceManager.getById(expriment.getInstance().getId());
      hashTable2.put(instance, expriment);
    }

    Set<Instance> instanceSet = new HashSet<>(hashTable1.keySet());
    instanceSet.addAll(hashTable2.keySet());

    instances = new ArrayList<>(instanceSet);

    Collections.sort(instances);

  }

  @Override
  int getColumnCount() {
    return columns.length;
  }

  @Override
  int getRowCount() {
    return instances.size();
  }

  StringPair pair = null;
  @Override
  String getCellContent(int row, int column) {
    Instance instance = instances.get(row);
    if(column==3)
      pair = getStringPair(instance);
    switch (column) {
      //case 0:
      //  return String.valueOf(rowCounter++);
      case 0:
        return instance.getFileName();
      case 1:
        return String.valueOf(instance.getSize());
      case 2:
        return String.valueOf(instance.getEdgeSize());
      //case 4:
      //  return String.valueOf(instance.getMaxDegree());
      //case 5:
      //  return String.valueOf(instance.getMaxDegreeCount());
      case 3:
        return pair.bestLB;
      case 4:
        return pair.SATLB;


    }
    return null;
  }

  private StringPair getStringPair(Instance instance) {
    StringPair pair = new StringPair();
    LBMinorWidthExpriment widthExp = hashTable1.get(instance);
    MDSTLBExpriment stExp = hashTable2.get(instance);

    Integer widthExpSize = null;
    if (widthExp != null) {
      widthExpSize = widthExp.getSize();
    }
    Integer stLowerBound = null;
    if (stExp != null) {
      stLowerBound = stExp.getLowerBound();
    }

    Integer bestLB = null ;

    if(widthExpSize!=null)
      bestLB = widthExpSize;
    if(stLowerBound!=null){
      if((bestLB==null)||(stLowerBound>widthExpSize))
        bestLB = stLowerBound;
    }


    String bestStr = "--";
    if (bestLB != null) {
      bestStr = String.valueOf(bestLB);
    }

    String satLB = AllBoundsReport.getSatLB(instance);
    if(satLB.charAt(0)!='-'){
      Integer sLB = Integer.valueOf(satLB);
      if(sLB>bestLB)
        satLB = emphasise(satLB);
      else
        bestStr = emphasise(bestStr) ;
    }

    pair.SATLB = satLB;
    pair.bestLB =bestStr;
    return pair;
  }

  @Override
  String getDescription() {
    return
        "This report presents a comparison between the best hwuristic lowerbound that are obtained from two methods "
            +
            "MinorTreeWidth and Minimum Max Degree Spanning tree and lowerbound obtained from SAT encoding. The results"
            +
            " is presented in the following table.";
  }

  @Override
  String getColumnName(int column) {
    return columns[column];
  }

  @Override
  String getTitle() {
    return "The Comparison of Minimum Max Degree Lower Bound and MinorWidth Lower Bound";
  }

  public static void main(String[] args) {
    new LBvsSAT().generateReportIntoFile("lbvssat.tex");
    PersistenceManager.close();
  }

  class StringPair{
    String bestLB;
    String SATLB;
  }
}
