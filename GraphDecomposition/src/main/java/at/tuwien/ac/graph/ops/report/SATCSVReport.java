package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.dao.*;
import at.tuwien.ac.graph.ops.expriments.*;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import java.util.*;

/**
 * Created by e1528895 on 9/21/17.
 */
public class SATCSVReport extends Report {

    String[] columns = {"Row", "Name", "Size", "Edges", "MAX", "MAX_COUNT", "C", "K", "Sol\\_size", "Finished", "Duration"};

    private final Instance instance;
    SimpleSatDao dao = new SimpleSatDao();

    List<SimpleSatExperiment> experiments = null;

    List<Integer> cList ;
    private int rowCounter=1;

    public SATCSVReport(Instance instance){
        this.instance = instance ;

        experiments = dao.getAllExprimentsByInstance(instance);

        Collections.sort(experiments);

    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public int getRowCount() {
        return experiments.size();
    }

    @Override
    public String getCellContent(int row, int column) {
        SimpleSatExperiment experiment = experiments.get(row);

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
                return String.valueOf(experiment.getFvdSize());
            case 9:
                return String.valueOf(experiment.getFinished());
            case 10:
                return String.valueOf(experiment.getSatDuration());
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
        return "SAT CSV Report";
    }

    public static void main(String[] args) {
        InstanceManager manager = new InstanceManager();
        //Instance instance = manager.getInstanceByName("mik.250-1-100.1.mps");
        //SATCSVReport report = new SATCSVReport(instance);
        //report.generateCSVFile("mik-sat.csv");
        for(int i = 5; i <11; i++) {
            String name="btree-"+i;
            Instance instance = manager.getInstanceByName(name);
            SATCSVReport report = new SATCSVReport(instance);
            report.generateCSVFile(name+"-sat.csv");
        }

        PersistenceManager.close();
    }
}
