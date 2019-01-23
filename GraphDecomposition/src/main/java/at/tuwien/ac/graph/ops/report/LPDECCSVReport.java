package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.lp.SimpleLPDecision;
import at.tuwien.ac.graph.ops.dao.DecisionLPDao;
import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.expriments.SimpleLPDecisionExpriment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import java.util.Collections;
import java.util.List;

/**
 * Created by e1528895 on 12/22/17.
 */
public class LPDECCSVReport extends Report{

    String[] columns = {"Row", "Name", "Size", "Edges", "MAX", "MAX_COUNT", "C", "K", "Sol\\_size", "Finished", "Duration"};

    private final Instance instance;
    DecisionLPDao dao = new DecisionLPDao();
    List<SimpleLPDecisionExpriment> list;
    private int rowCounter=1;

    public LPDECCSVReport(Instance instance) {
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
        SimpleLPDecisionExpriment experiment = list.get(row);
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
                return String.valueOf(experiment.getK());
            case 8:
                return String.valueOf(experiment.getSolutionSize());
            case 9:
                return String.valueOf(experiment.getFinished());
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
        return "LP-DEC-CSV";
    }


    public static void main(String[] args) {
        InstanceManager manager = new InstanceManager();
        for(int i = 7; i <13; i++) {
            String name="Grid-"+i ;
            Instance instance = manager.getInstanceByName(name);
            LPDECCSVReport report = new LPDECCSVReport(instance);
            report.generateCSVFile(name+"-lpdec.csv");
        }
        PersistenceManager.close();
    }
}
