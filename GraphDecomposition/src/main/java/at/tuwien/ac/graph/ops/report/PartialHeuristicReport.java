package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.dao.HeuristicPartialDao;
import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.expriments.HeuristicPartialSolutionExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import java.util.*;

/**
 * Created by e1528895 on 10/16/17.
 */
public class PartialHeuristicReport extends Report {

    String[] columns = {"R", "Name", "$|V|$", "$|E|$", "$\\Delta$", "$\\# \\Delta$", "c", "LB", "UB", "OP\\_TIME"};

    HeuristicPartialDao partialDao = new HeuristicPartialDao();
    InstanceManager instanceManager = new InstanceManager();
    //Hashtable<Instance, List<HeuristicPartialSolutionExperiment>> table = new Hashtable<Instance, List<HeuristicPartialSolutionExperiment>>();
    //List<Instance> instanceList = new ArrayList<>();
    int rowCounter=1;
    List<HeuristicPartialSolutionExperiment> all ;

    public PartialHeuristicReport(){
        all = partialDao.getAll();
        Collections.sort(all, new Comparator<HeuristicPartialSolutionExperiment>() {
            @Override
            public int compare(HeuristicPartialSolutionExperiment o1, HeuristicPartialSolutionExperiment o2) {
                if(o1.getInstance()!=o2.getInstance())
                    return o1.getInstance().compareTo(o2.getInstance());

                return o1.compareTo(o2);
            }
        });

    }
    @Override
    int getColumnCount() {
        return columns.length;
    }

    @Override
    int getRowCount() {
        return all.size();
    }


    Instance previousInstance = null ;
    @Override
    String getCellContent(int row, int column) {
        HeuristicPartialSolutionExperiment experiment = all.get(row);
        Instance instance = experiment.getInstance();
        boolean b = (previousInstance == null) || (!previousInstance.getFileName().equalsIgnoreCase(instance.getFileName()));
        switch (column) {
            case 0:
                return String.valueOf(rowCounter++);
            case 1:
                if(b)
                    return instance.getFileName();
                return "";
            case 2:
                if(b)
                    return String.valueOf(instance.getSize());
                return "";
            case 3:
                if(b)
                    return String.valueOf(instance.getEdgeSize());
                return "";
            case 4:
                if(b)
                    return String.valueOf(instance.getMaxDegree());
                return"";
            case 5:
                if(b) {
                    previousInstance = instance ;
                    return String.valueOf(instance.getMaxDegreeCount());
                }
                return "";
            case 6:
                return String.valueOf(experiment.getC());

            case 7: return String.valueOf(experiment.getLb());
            case 8:
                return String.valueOf(experiment.getUb());
            case 9:
                return millisecondsToFormat(experiment.getOperationDuration());
        }
        return "";

    }

    @Override
    String getDescription() {
        return "The heuristic for different instance with various C";
    }

    @Override
    String getColumnName(int column) {
        return columns[column];
    }

    @Override
    String getTitle() {
        return "Partial Heuristic Report";
    }

    public static void main(String[] args) {
        new PartialHeuristicReport().generateReportIntoFile("partial.tex");
        PersistenceManager.close();
    }
}
