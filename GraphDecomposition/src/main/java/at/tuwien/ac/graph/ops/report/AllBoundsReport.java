package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.dao.*;
import at.tuwien.ac.graph.ops.expriments.*;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by e1528895 on 1/7/18.
 */
public class AllBoundsReport extends Report {

    String[] columns = {"Row", "Name", "Size", "Edges", "MAX", "MAX-COUNT", "TREE-LB", "MINOR-LB", "FPT-LB", "LP-LB", "SAT-LB", "HEURISTIC-LB"
            , "HEURISTIC-UB", "SAT_UB", "LP-UB", "FPT-UB", "Max-Deg-UB"};

    InstanceManager instanceManager = new InstanceManager();
    LBMinorWidthDao minorDao = new LBMinorWidthDao();
    MDSTLBDao treeDao = new MDSTLBDao();
    OneMaxDegDao ubDao = new OneMaxDegDao();

    CoveredSimilarDao fvdDao = new CoveredSimilarDao();
    static SimpleLPDao lpDao = new SimpleLPDao();
    static SimpleSatDao satDao = new SimpleSatDao();
    FinalHeuristicDao heuristicDao = new FinalHeuristicDao();

    List<Instance> allAscending;

    int rowCounter = 1;


    public AllBoundsReport() {
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

    @Override
    String getCellContent(int row, int column) {
        Instance instance = allAscending.get(row);

        switch (column) {
            case 0:
                return String.valueOf(rowCounter++);
            case 1:
                return instance.getFileName();
            case 2:
                return String.valueOf(instance.getSize());
            case 3:
                return String.valueOf(instance.getEdgeSize());
            case 4:
                return String.valueOf(instance.getMaxDegree());
            case 5:
                return String.valueOf(instance.getMaxDegreeCount());
            //"TREE-LB", "MINOR-LB", "FPT-LB", "LP-LB", "SAT-LB", "HEURISTIC-LB"
            case 6:
                int lowerBound = treeDao.getExprimentByInstance(instance).getLowerBound();
                return String.valueOf(lowerBound);
            case 7:
                int size = minorDao.getExprimentByInstance(instance).getSize();
                return String.valueOf(size);
            //case 8:
            //    size = ubDao.getExprimentByInstance(instance).getSize();
            //    return String.valueOf(size);
            case 8:
                return getFPTLB(instance);
            case 9:
                return getLPLB(instance);
            case 10:
                return getSatLB(instance);
            case 11:
                return getHeuristicLB(instance);
            //,"HEURISTIC-UB", "SAT_UB", "LP-UB", "FPT-UB", "Max-Deg-UB"};
            case 12:
                return getHeuristicUB(instance);
            case 13:
                return getSatUB(instance);
            case 14:
                return getLPUB(instance);
            case 15:
                return getFPTUB(instance);
            case 16:
                size = ubDao.getExprimentByInstance(instance).getSize();
                return String.valueOf(size);
        }
        return null;
    }

    String getHeuristicLB(Instance instance) {
        FinalHeuristicSolutionExperiment expriment = heuristicDao.getExprimentByInstance(instance);
        if(expriment==null)
            return "-";
        return String.valueOf(expriment.getcLowerBound());
    }

    String getHeuristicUB(Instance instance) {
        FinalHeuristicSolutionExperiment expriment = heuristicDao.getExprimentByInstance(instance);
        if(expriment==null)
            return "-";
        //return String.valueOf(expriment.getK());
        return String.valueOf(expriment.getC());
    }

    public static String getSatLB(Instance instance) {
        List<SimpleSatExperiment> experiments = satDao.getAllExprimentsByInstance(instance);
        int max=Integer.MIN_VALUE;
        boolean contained = false ;
        for(SimpleSatExperiment experiment:experiments){
            Integer c = experiment.getC();
            if(experiment.getK()!=c)
                continue;
            Boolean updateMax = experiment.getFinished() && experiment.getFvdSize()==null
                    && c > max;
            if(updateMax) {
                max=c;
                contained=true;
            }
        }
        if(!contained)
            return "-";

        return String.valueOf(max+1);
    }

    String getSatUB(Instance instance) {
        List<SimpleSatExperiment> experiments = satDao.getAllExprimentsByInstance(instance);
        int min=Integer.MAX_VALUE;
        boolean contained = false ;
        for(SimpleSatExperiment experiment:experiments){
            Integer c = experiment.getC();
            //if(experiment.getK()!=c)
            //    continue;
            Boolean updateMax = experiment.getFinished() && experiment.getFvdSize()!=null
                    && c < min;
            if(updateMax) {
                min = c;
                contained=true;
            }
        }
        if(!contained)
            return "-";

        return String.valueOf(min);
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


    String getLPLB(Instance instance) {
        List<SimpleLPExpriment> experiments = lpDao.getAllExprimentsByInstance(instance);
        if(experiments.isEmpty())
            return "-";
        int max = Integer.MIN_VALUE;
        for(SimpleLPExpriment expriment: experiments){
            int lb = expriment.getLb();
            int c = expriment.getC();
            if(lb> c)
                lb= c;
            if(lb>max)
                max=lb ;
        }
        return String.valueOf(max);
    }

    static String getLPUB(Instance instance) {
        List<SimpleLPExpriment> experiments = lpDao.getAllExprimentsByInstance(instance);
        if(experiments.isEmpty())
            return "-";
        int min = Integer.MAX_VALUE;
        for(SimpleLPExpriment expriment: experiments){
            int ub = expriment.getK();
            int c = expriment.getC();
            if(c> ub)
                ub= c;
            if(min>ub)
                min=ub ;
        }
        return String.valueOf(min);
    }

    String getFPTLB(Instance instance) {
        List<FVDSimilarCoveredExperiment> experiments = fvdDao.getAllExprimentsByInstance(instance);
        int max = Integer.MIN_VALUE;
        boolean filled = false ;
        for(FVDSimilarCoveredExperiment experiment: experiments){
            int lb = experiment.getLb();
            if(lb>max) {
                max = lb;
                filled = true;
            }

        }
        if(!filled)
            return "-";
        return String.valueOf(max);
    }

    String getFPTUB(Instance instance) {
        List<FVDSimilarCoveredExperiment> experiments = fvdDao.getAllExprimentsByInstance(instance);
        int min = Integer.MAX_VALUE;
        boolean filled = false ;
        for(FVDSimilarCoveredExperiment experiment: experiments){
            Integer ub = experiment.getFvdSize();
            Integer c = experiment.getC();
            if(ub==null)
                continue;
            //if(ub>c)
            if((c<min)&&(ub<=c)) {
                min = c;
                filled = true;
            }

        }
        if(!filled)
            return "-";
        return String.valueOf(min);
    }

    public static void main(String[] args) {
        new AllBoundsReport().generateCSVFile("bestBounds.csv");
        //new AllBoundsReport().generateReportIntoFile("bestBounds.tex");
        PersistenceManager.close();

    }
}