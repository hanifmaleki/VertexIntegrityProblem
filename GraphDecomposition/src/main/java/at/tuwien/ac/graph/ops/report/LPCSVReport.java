package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.dao.DecisionLPDao;
import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.dao.SimpleLPDao;
import at.tuwien.ac.graph.ops.expriments.SimpleLPDecisionExpriment;
import at.tuwien.ac.graph.ops.expriments.SimpleLPExpriment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import java.util.Collections;
import java.util.List;

/**
 * Created by e1528895 on 12/22/17.
 */
public class LPCSVReport extends Report{

    String[] columns = {"Row", "Name", "Size", "Edges", "MAX", "MAX_COUNT", "C", "LB", "Sol\\_size", "Timeout", "Duration"};

    private final Instance instance;
    SimpleLPDao dao = new SimpleLPDao();
    List<SimpleLPExpriment> list;
    private int rowCounter=1;

    public LPCSVReport(Instance instance) {
        this.instance = instance;

        list = dao.getAllExprimentsByInstance(instance);

        Collections.sort(list);

    }


    @Override
    int getColumnCount() {
        return columns.length;
    }

    @Override
    int getRowCount() {
        return list.size();
    }

    @Override
    String getCellContent(int row, int column) {
        SimpleLPExpriment experiment = list.get(row);
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
            //{"Row", "Name", "Size", "Edges", "MAX", "MAX_COUNT", "C", "K", "Sol\\_size", "Finished", "Duration"};
            case 6 :
                return String.valueOf(experiment.getC());
            case 7:
                return String.valueOf(experiment.getLb());
            case 8:
                return String.valueOf(experiment.getK());
            case 9:
                return String.valueOf(experiment.getTimeout());
            case 10:
                return String.valueOf(experiment.getOperationDuration());
            default:
                return null;
        }
    }

    @Override
    String getDescription() {
        return null;
    }

    @Override
    String getColumnName(int column) {
        return columns[column];
    }

    @Override
    String getTitle() {
        return "LP-CSV";
    }


    public static void main(String[] args) {
        InstanceManager manager = new InstanceManager();
        //String bestName="mik.250-1-100.1.mps" ;
        //Instance instance = manager.getInstanceByName(bestName);
        //LPCSVReport report = new LPCSVReport(instance);
        //report.generateCSVFile(bestName+"-lp.csv");
        for(int i = 5; i <11; i++) {
            String name="btree-"+i ;
            Instance instance = manager.getInstanceByName(name);
            LPCSVReport report = new LPCSVReport(instance);
            report.generateCSVFile(name+"-lp.csv");
        }
        PersistenceManager.close();
    }
}
