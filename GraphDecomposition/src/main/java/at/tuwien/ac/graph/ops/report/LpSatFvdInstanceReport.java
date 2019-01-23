package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.dao.*;
import at.tuwien.ac.graph.ops.expriments.FVDSimilarCoveredExperiment;
import at.tuwien.ac.graph.ops.expriments.SimpleLPDecisionExpriment;
import at.tuwien.ac.graph.ops.expriments.SimpleSatExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;

import java.util.*;

/**
 * Created by e1528895 on 9/25/17.
 */
public class LpSatFvdInstanceReport extends Report {

    String[] columns = {"R", "c", "FVD\\_TIME", "S\\_TIME", "LP\\_TIME"};

    SimpleSatDao satDao = new SimpleSatDao();
    DecisionLPDao lpDao = new DecisionLPDao();
    CoveredSimilarDao fvdDao = new CoveredSimilarDao();
    InstanceManager instanceManager = new InstanceManager();
    Hashtable<Integer, SimpleSatExperiment> satTable = new Hashtable<>();
    Hashtable<Integer, SimpleLPDecisionExpriment> lpTable = new Hashtable<>();
    Hashtable<Integer, FVDSimilarCoveredExperiment> fvdTable = new Hashtable<>();
    List<Integer> cList ;
    int rowNumber = 1 ;

    public LpSatFvdInstanceReport(Instance instance){
        Set<Integer> cSet = new HashSet<>();

        List<SimpleSatExperiment> sats = satDao.getAllExprimentsByInstance(instance);
        for(SimpleSatExperiment experiment:sats){
            Integer c = experiment.getC();
            satTable.put(c, experiment);
            cSet.add(c);
        }

        List<SimpleLPDecisionExpriment> lps = lpDao.getAllExprimentsByInstance(instance);
        for(SimpleLPDecisionExpriment experiment:lps){
            Integer c = experiment.getC();
            lpTable.put(c, experiment);
            cSet.add(c);
        }

        List<FVDSimilarCoveredExperiment> fvds = fvdDao.getAllExprimentsByInstance(instance);
        for(FVDSimilarCoveredExperiment experiment:fvds){
            Integer c = experiment.getC();
            fvdTable.put(c, experiment);
            cSet.add(c);
        }

        cList = new ArrayList<>();
        cList.addAll(cSet);
        Collections.sort(cList);
    }

    @Override
    int getColumnCount() {
        return columns.length;
    }

    @Override
    int getRowCount() {
        return cList.size();
    }

    @Override
    String getCellContent(int row, int column) {
        Integer c = cList.get(row);

        switch(column){
            case 0 : return String.valueOf(rowNumber++);
            case 1 : return String.valueOf(c);
            case 2 :
                FVDSimilarCoveredExperiment fvdExperiment = fvdTable.get(c);
                if(fvdExperiment==null)
                    return "-1";
                return String.valueOf(fvdExperiment.getOperationDuration());
            case 3 :
                SimpleSatExperiment satExperiment = satTable.get(c);
                if(satExperiment==null)
                    return "-1";
                return String.valueOf(satExperiment.getOperationDuration());
            case 4 :
                SimpleLPDecisionExpriment lpExperiment = lpTable.get(c);
                if(lpExperiment==null)
                    return "-1";
                return String.valueOf(lpExperiment.getOperationDuration());

        }
        return null;
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
        return "";
    }

    public static void main(String[] args) {
        InstanceManager instanceManager = new InstanceManager();
        //Instance instance = instanceManager.getInstanceByName("noswot.mps");
        Instance instance = instanceManager.getInstanceByName("ns1766074.mps");
        new LpSatFvdInstanceReport(instance).generateCSVFile("ns1766074-exact.csv");
    }
}
