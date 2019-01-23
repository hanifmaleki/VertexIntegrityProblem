package at.tuwien.ac.graph.ops.report;


import at.tuwien.ac.graph.ops.dao.FVDSymetryDao;
import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.expriments.FVDSymetryExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by e1528895 on 9/21/17.
 */
public class FVDSymmetryCSVReport extends Report {

    private final List<FVDSymetryExperiment> list;
    String[] columns = {"Row", "Name", "Size", "Edges", "MAX", "MAX_COUNT", "C", "K", "LB", "Solution", "Finished", "Duration"};

    private final Instance instance;
    FVDSymetryDao dao = new FVDSymetryDao();


    private int rowCounter=1;

    public FVDSymmetryCSVReport(Instance instance){
        this.instance = instance ;

        list = dao.getAllExprimentsByInstance(instance);
        Collections.sort(list);

    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public String getCellContent(int row, int column) {
        FVDSymetryExperiment experiment = list.get(row);

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
            //"C", "K", "Solution", "Finished", "Duration"
            case 6 :
                return String.valueOf(experiment.getC());
            case 7:
                return String.valueOf(experiment.getK());
            case 8:
                return String.valueOf(experiment.getLb());
            case 9:
                return String.valueOf(experiment.getFvdSize());
            case 10:
                return String.valueOf(experiment.getFinished());
            case 11:
                return String.valueOf(experiment.getOperationDuration());
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
        return "FVDS";
    }

    public static void main(String[] args) {
        InstanceManager manager = new InstanceManager();
        for(int i = 5; i <9; i++) {
            String name = "Grid-" + i;
            Instance instance = manager.getInstanceByName(name);
            if(instance==null)
                continue;
            FVDSymmetryCSVReport report = new FVDSymmetryCSVReport(instance);
            report.generateCSVFile(name + "-symmetry.csv");
        }
        PersistenceManager.close();
    }
}
