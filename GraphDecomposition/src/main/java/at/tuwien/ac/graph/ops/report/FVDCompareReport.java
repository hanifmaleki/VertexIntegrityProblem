package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.dao.*;
import at.tuwien.ac.graph.ops.expriments.CoveredVerticesExperiment;
import at.tuwien.ac.graph.ops.expriments.FVDSimilarCoveredExperiment;
import at.tuwien.ac.graph.ops.expriments.FVDSimilarExperiment;
import at.tuwien.ac.graph.ops.expriments.SimpleFVDExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import java.util.*;

/**
 * Created by e1528895 on 9/21/17.
 */
public class FVDCompareReport extends Report {

    String[] columns = {"Row", "Name", "Size", "Edges", "MAX", "MAX_COUNT", "C", "Simple", "Similar", "Covered", "Both"};

    private final Instance instance;
    SimpleFVDDao simpleDao = new SimpleFVDDao();
    FVDSimilarDao similarDao = new FVDSimilarDao();
    CoveredVerticesDao coverDao = new CoveredVerticesDao();
    CoveredSimilarDao bothDao = new CoveredSimilarDao();

    Hashtable<Integer, SimpleFVDExperiment> simpleTable = new Hashtable<>();
    Hashtable<Integer, FVDSimilarExperiment> similarTable = new Hashtable<>();
    Hashtable<Integer, CoveredVerticesExperiment> coveredTable = new Hashtable<>();
    Hashtable<Integer, FVDSimilarCoveredExperiment> bothTable = new Hashtable<>();

    List<Integer> cList ;
    private int rowCounter=1;

    public FVDCompareReport(Instance instance){
        this.instance = instance ;
        Set<Integer> cSet = new HashSet<>();
        List<SimpleFVDExperiment> simples = simpleDao.getAllExprimentsByInstance(instance);
        for(SimpleFVDExperiment experiment : simples){
            Integer c = experiment.getC();
            cSet.add(c);
            simpleTable.put(c, experiment);
        }

        List<FVDSimilarExperiment> similars = similarDao.getAllExprimentsByInstance(instance);
        for(FVDSimilarExperiment experiment : similars){
            Integer c = experiment.getC();
            cSet.add(c);
            similarTable.put(c, experiment);
        }

        List<CoveredVerticesExperiment> covers = coverDao.getAllExprimentsByInstance(instance);
        for(CoveredVerticesExperiment experiment : covers){
            Integer c = experiment.getC();
            cSet.add(c);
            coveredTable.put(c, experiment);
        }

        List<FVDSimilarCoveredExperiment> boths = bothDao.getAllExprimentsByInstance(instance);
        for(FVDSimilarCoveredExperiment experiment : boths){
            Integer c = experiment.getC();
            cSet.add(c);
            bothTable.put(c, experiment);
        }

        cList = new ArrayList(cSet);
        Collections.sort(cList);

    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public int getRowCount() {
        return cList.size();
    }

    @Override
    public String getCellContent(int row, int column) {
        Integer c = cList.get(row);

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
            case 6 :
                return String.valueOf(c);
            case 7:
                SimpleFVDExperiment simple = simpleTable.get(c);
                if (simple == null)
                    return null;
                return String.valueOf(simple.getOperationDuration());
            case 8:
                FVDSimilarExperiment similar = similarTable.get(c);
                if (similar == null)
                    return null;
                return String.valueOf(similar.getOperationDuration());
            case 9:
                CoveredVerticesExperiment covered = coveredTable.get(c);
                if (covered == null)
                    return null;
                return String.valueOf(covered.getOperationDuration());
            case 10:
                FVDSimilarCoveredExperiment both = bothTable.get(c);
                if (both == null)
                    return null;
                return String.valueOf(both.getOperationDuration());
            default:
                return null;
        }
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public String getTitle() {
        return "Comparison of the Various FVD Method";
    }

    public static void main(String[] args) {
        InstanceManager manager = new InstanceManager();
        for(int i = 8; i <13; i++) {
            String name = "Grid-modern-" + i;
            Instance instance = manager.getInstanceByName(name);
            FVDCompareReport report = new FVDCompareReport(instance);
            report.generateCSVFile(name + ".csv");
        }
        PersistenceManager.close();
    }
}
