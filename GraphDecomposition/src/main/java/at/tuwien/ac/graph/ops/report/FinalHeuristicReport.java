package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.dao.FinalHeuristicDao;
import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.expriments.FinalHeuristicSolutionExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by e1528895 on 10/29/17.
 */
public class FinalHeuristicReport extends Report {

    String[] columns = {"R", "Name", "$|V|$", "$|E|$", "$\\Delta$", "$\\# \\Delta$", "UB", "K", "K\\_LB",  "C\\_LB", "OP\\_TIME"};

    InstanceManager instanceManager = new InstanceManager();
    FinalHeuristicDao dao = new FinalHeuristicDao();

    List<Instance> instances = new ArrayList<>();
    List<FinalHeuristicSolutionExperiment> experiments = new ArrayList<>();
    private int rowCounter = 1;

    public FinalHeuristicReport(){

        List<Instance> allAscending = instanceManager.getAllAscending();

        for(Instance instance : allAscending){
            FinalHeuristicSolutionExperiment experiment = dao.getExprimentByInstance(instance);
            if(experiment!=null){
                experiments.add(experiment);
                instances.add(instance);
            }
        }

    }

    @Override
    int getColumnCount() {
        return columns.length;
    }

    @Override
    int getRowCount() {
        return instances.size();
    }

    @Override
    String getCellContent(int row, int column) {
        Instance instance = instances.get(row);
        FinalHeuristicSolutionExperiment experiment = experiments.get(row);
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

            case 6:
                return String.valueOf(experiment.getC());

            case 7: return String.valueOf(experiment.getK());
            case 8:
                return String.valueOf(experiment.getkLowerBound());
            case 9:
                return String.valueOf(experiment.getcLowerBound());
            case 10:
                return millisecondsToFormat(experiment.getOperationDuration());
        }
        return "";
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
        return "Final Heuristic Report";
    }

    public static void main(String[] args) {
        new FinalHeuristicReport().generateReportIntoFile("final.tex");
        PersistenceManager.close();
    }
}
