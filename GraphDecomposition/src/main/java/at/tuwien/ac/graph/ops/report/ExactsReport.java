package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.dao.CoveredSimilarDao;
import at.tuwien.ac.graph.ops.dao.FinalHeuristicDao;
import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.dao.LBMinorWidthDao;
import at.tuwien.ac.graph.ops.dao.MDSTLBDao;
import at.tuwien.ac.graph.ops.dao.OneMaxDegDao;
import at.tuwien.ac.graph.ops.dao.SimpleLPDao;
import at.tuwien.ac.graph.ops.dao.SimpleSatDao;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;
import java.util.List;

/**
 * Created by e1528895 on 1/7/18.
 */
public class ExactsReport extends AllBoundsReport {

  String[] columns = {"Name", "$|V|$", "$|E|$", "FPT-LB", "LP-LB",
      "SAT-LB", "SAT-UB", "LP-UB", "FPT-UB"};

  InstanceManager instanceManager = new InstanceManager();
  LBMinorWidthDao minorDao = new LBMinorWidthDao();
  MDSTLBDao treeDao = new MDSTLBDao();
  OneMaxDegDao ubDao = new OneMaxDegDao();

  CoveredSimilarDao fvdDao = new CoveredSimilarDao();
  SimpleLPDao lpDao = new SimpleLPDao();
  static SimpleSatDao satDao = new SimpleSatDao();
  FinalHeuristicDao heuristicDao = new FinalHeuristicDao();

  List<Instance> allAscending;

  int rowCounter = 1;


  public ExactsReport() {
    allAscending = instanceManager.getAllAscending();

        /*for(Instance instance:allAscending){
            int size = minorDao.getExprimentByInstance(instance).getSize();
        }*/

  }

  @Override
  int getColumnCount() {
    return columns.length;
  }

  @Override
  int getRowCount() {
    return 45;
  }

  RowString rowString = null;

  @Override
  String getCellContent(int row, int column) {
    Instance instance = allAscending.get(row);

    if(column==3)
      rowString = makeRowString(instance);

    switch (column) {
      case -1:
        return String.valueOf(rowCounter++);
      case 0:
        return instance.getFileName();
      case 1:
        return String.valueOf(instance.getSize());
      case 2:
        return String.valueOf(instance.getEdgeSize());
      case -2:
        return String.valueOf(instance.getMaxDegree());
      case -3:
        return String.valueOf(instance.getMaxDegreeCount());
      //"FPT-LB", "LP-LB", "SAT-LB", "SAT_UB", "LP-UB", "FPT-UB"
      case 3:
        return rowString.fptLb;
      case 4:
        return rowString.LPLb;
      case 5:
        return rowString.satLb;
      case 6:
        return rowString.satUb;
      case 7:
        return rowString.LPUb;
      case 8:
        return rowString.fptUb;
    }
    return null;
  }

  private RowString makeRowString(Instance instance) {
    RowString rowString = new RowString();

    String satLBStr = getSatLB(instance);
    String lplbStr = getLPLB(instance);
    String fptlbStr = getFPTLB(instance);

    int satLb= -1;
    if(satLBStr.charAt(0)!='-')
      satLb=Integer.valueOf(satLBStr);

    int fptLb= -1 ;
    if(fptlbStr.charAt(0)!='-')
      fptLb=Integer.valueOf(fptlbStr);

    int lplb = -1 ;
    if(lplbStr.charAt(0)!='-')
      lplb = Integer.valueOf(lplbStr);

    int bestLbVal =satLb;
    if(bestLbVal<fptLb)
      bestLbVal=fptLb;
    if(bestLbVal<lplb)
      bestLbVal=lplb;

    String satUBStr = getSatUB(instance);
    String fptubStr = getFPTUB(instance);
    String lpubStr = getLPUB(instance);

    int satUb = instance.getSize();
    if(satUBStr.charAt(0)!='-')
      satUb = Integer.valueOf(satUBStr);

    int fptUb = instance.getSize();
    if(fptubStr.charAt(0)!='-')
      fptUb = Integer.valueOf(fptubStr);

    int lpUb = instance.getSize();
    if(lpubStr.charAt(0)!='-')
      lpUb = Integer.valueOf(lpubStr);

    int bestUbValue = satUb;
    if(fptUb<bestUbValue)
      bestUbValue = fptUb;
    if(lpUb<bestUbValue)
      bestUbValue = lpUb;

    rowString.fptLb = fptlbStr;
    rowString.LPLb = lplbStr;
    rowString.satLb = satLBStr;

    rowString.fptUb = fptubStr ;
    rowString.LPUb = lpubStr;
    rowString.satUb = satUBStr;

    if(bestLbVal>0){
      if(bestLbVal==fptLb)
        rowString.fptLb = emphasise(fptlbStr);
      if(bestLbVal==satLb)
        rowString.satLb = emphasise(satLBStr);
      if(bestLbVal==lplb)
        rowString.LPLb = emphasise(lplbStr);
    }

    if(bestUbValue<instance.getSize()){
      if(bestUbValue==fptUb)
        rowString.fptUb = emphasise(lpubStr);
      if(bestUbValue==satUb)
        rowString.satUb = emphasise(satUBStr);
      if(bestUbValue==lpUb)
        rowString.LPUb = emphasise(lpubStr);
    }

    return rowString;
  }


  @Override
  String getDescription() {
    return "";
  }

  @Override
  String getColumnName(int column) {
    return columns[column];
  }

  @Override
  String getTitle() {
    return "Obtained Bounf of all exact methods";
  }


  public static void main(String[] args) {
    new ExactsReport().generateReportIntoFile("exacts.tex");
    //new AllBoundsReport().generateReportIntoFile("bestBounds.tex");
    PersistenceManager.close();

  }


  class RowString {

    String satLb;
    String fptLb;
    String LPLb;
    String satUb;
    String fptUb;
    String LPUb;

  }
}
