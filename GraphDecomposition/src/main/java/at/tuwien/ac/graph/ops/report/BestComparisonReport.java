package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.dao.FinalHeuristicDao;
import at.tuwien.ac.graph.ops.expriments.FVDSimilarCoveredExperiment;
import at.tuwien.ac.graph.ops.expriments.FinalHeuristicSolutionExperiment;
import at.tuwien.ac.graph.ops.expriments.SimpleLPExpriment;
import at.tuwien.ac.graph.ops.expriments.SimpleSatExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;
import java.util.List;

/**
 * Created by e1528895 on 1/7/18.
 */
public class BestComparisonReport extends AllBoundsReport {

  private static final String NAME_SAT = "SAT";
  private static final String NAME_LP = "LP";
  private static final String NAME_FPT = "FPT";
  String[] columns = { "Name", "$|V|$", "$|E|$", "BEST-NAME", "BEST-Bounds", "BEST-TIME",
      "HEURISTIC-BOUNDS", "HEURISTIC-TIME"};

    FinalHeuristicDao finalDao = new FinalHeuristicDao();

    List<Instance> allAscending;

    int rowCounter = 1;


    public BestComparisonReport() {
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

    private BESTStrings bestStrings = null;
    @Override
    String getCellContent(int row, int column) {
        Instance instance = allAscending.get(row);

        if(column==3)
          bestStrings = fillBests(instance);

      switch (column) {
            //case 0:
            //    return String.valueOf(rowCounter++);
            case 0:
                return instance.getFileName();
            case 1:
                return String.valueOf(instance.getSize());
            case 2:
                return String.valueOf(instance.getEdgeSize());
        //"BEST_NAME", "BEST-Bounds", "BEST-TIME", "HEURISTIC-BOUNDS", "HEURISTIC_TIME"
            case 3:
                return bestStrings.bestName ;
            case 4:
                return bestStrings.bestBounds;
            case 5:
                return bestStrings.bestTime;
            case 6:
                return bestStrings.heuristicBounds ;
            case 7:
                return bestStrings.heuristicTime;
        }
        return null;
    }

  private BESTStrings fillBests(Instance instance) {
    BESTStrings bestStrings = new BESTStrings();

    int satGap = getSatGap(instance);
    int fptGap = getFptGap(instance);
    int lpGap = getLPGap(instance);

    int minGap = fptGap;
    if(lpGap<minGap)
      minGap = lpGap;
    if(satGap< minGap)
      minGap = satGap;

    boolean satIsBest = minGap==satGap;
    boolean fptIsBest = minGap == fptGap;
    boolean lpIsBest = minGap == lpGap;

    if(fptIsBest){
      bestStrings.bestName =NAME_FPT;
      bestStrings.bestBounds = getFPTLB(instance);
      if(fptGap>0)
        bestStrings.bestBounds = getFPTLB(instance) +"-"+ getFPTUB(instance);
      bestStrings.bestTime = getFPTTimes(instance);
    } else if(lpIsBest){
      bestStrings.bestName =NAME_LP;
      bestStrings.bestBounds = getLPLB(instance);
      if(lpGap>0)
        bestStrings.bestBounds = bestStrings.bestBounds+"-"+getLPUB(instance);
      bestStrings.bestTime = getLPTimes(instance);
    } else if(satIsBest){
      bestStrings.bestName =NAME_SAT;
      bestStrings.bestBounds =getSatLB(instance);
      if(satGap>0)
        bestStrings.bestBounds = bestStrings.bestBounds +"-"+getSatUB(instance);
      bestStrings.bestTime = getSATTimes(instance);
    }else {
      bestStrings.bestName = "-";
      bestStrings.bestBounds = "-";
      bestStrings.bestTime = "-";
    }

    FinalHeuristicSolutionExperiment expriment = finalDao
        .getExprimentByInstance(instance);
    int heuristicLb = expriment.getcLowerBound();
    int heuristicUb = expriment.getC();
    int heuristicGap = heuristicUb - heuristicLb;
    bestStrings.heuristicBounds = String.valueOf(heuristicLb);
    if(heuristicGap>0)
      bestStrings.heuristicBounds = bestStrings.heuristicBounds + "-" + heuristicUb;
    bestStrings.heuristicTime = millisecondsToFormat(expriment.getOperationDuration());

    if(heuristicGap<minGap)
      bestStrings.heuristicBounds = emphasise(bestStrings.heuristicBounds);
    if(minGap<heuristicGap)
      bestStrings.bestBounds = emphasise(bestStrings.bestBounds);

    return bestStrings;
  }

  private String getSATTimes(Instance instance) {
    List<SimpleSatExperiment> experiments = satDao.getAllExprimentsByInstance(instance);
    Long totalTime=0L;
    for(SimpleSatExperiment experiment: experiments)
      if(experiment.getK()==experiment.getK())
        totalTime+=experiment.getOperationDuration();

    return Report.millisecondsToFormat(totalTime);
  }

  private String getFPTTimes(Instance instance) {
    List<FVDSimilarCoveredExperiment> experiments = fvdDao.getAllExprimentsByInstance(instance);
    Long totalTime=0L;
    for(FVDSimilarCoveredExperiment experiment: experiments)
      if(experiment.getK()==experiment.getK())
        totalTime+=experiment.getOperationDuration();

    return Report.millisecondsToFormat(totalTime);
  }

  private String getLPTimes(Instance instance) {
    List<SimpleLPExpriment> experiments = lpDao.getAllExprimentsByInstance(instance);
    Long totalTime=0L;
    for(SimpleLPExpriment experiment: experiments)
        totalTime+=experiment.getOperationDuration();

    return Report.millisecondsToFormat(totalTime);
  }



  private int getSatGap(Instance instance) {
    String lbStr = getSatLB(instance);
    String ubStr = getSatUB(instance);
    int lb =-1;
    if(lbStr.charAt(0)!='-')
      lb = Integer.valueOf(lbStr);

    int ub = instance.getSize()+1;
    if(ubStr.charAt(0)!='-')
      ub = Integer.valueOf(ubStr);


    return ub-lb;
  }

  private int getFptGap(Instance instance) {
    String lbStr = getFPTLB(instance);
    String ubStr = getFPTUB(instance);
    int lb =-1;
    if(lbStr.charAt(0)!='-')
      lb = Integer.valueOf(lbStr);

    int ub = instance.getSize()+1;
    if(ubStr.charAt(0)!='-')
      ub = Integer.valueOf(ubStr);


    return ub-lb;
  }

  private int getLPGap(Instance instance) {
    String lbStr = getLPLB(instance);
    String ubStr = getLPUB(instance);
    int lb =-1;
    if(lbStr.charAt(0)!='-')
      lb = Integer.valueOf(lbStr);

    int ub = instance.getSize()+1;
    if(ubStr.charAt(0)!='-')
      ub = Integer.valueOf(ubStr);


    return ub-lb;
  }

    @Override
        String getDescription () {
            return "";
        }

        @Override
        String getColumnName ( int column){
            return columns[column];
        }

        @Override
        String getTitle () {
            return "Obtained Bounf of all methods";
        }



    public static void main(String[] args) {
        new BestComparisonReport().generateReportIntoFile("exactvsheuristic.tex");
        //new AllBoundsReport().generateReportIntoFile("bestBounds.tex");
        PersistenceManager.close();

    }

    private class BESTStrings {
            String bestName;
            //String lb;
            //String ub;
            String bestBounds;
            String bestTime;

            String heuristicBounds;
            String heuristicTime;



    }
}