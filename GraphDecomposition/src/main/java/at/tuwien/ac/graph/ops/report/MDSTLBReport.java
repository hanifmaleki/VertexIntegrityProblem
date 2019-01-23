package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.expriments.MDSTLBExpriment;
import at.tuwien.ac.graph.ops.dao.MDSTLBDao;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.ops.dao.InstanceManager;

import java.util.*;

/**
 * Created by root on 8/1/17.
 */
public class MDSTLBReport extends Report {
    List<Instance> instances = null;
    Hashtable<Instance, MDSTLBExpriment> exprimentHashtable = null ;
    InstanceManager instanceManager = new InstanceManager();

    String[] columns = {"Row", "Name", "Vertices", "Edges", "Max Deg", "\\#Max Deg", "C", "LB", "Time"};
    private int rowCounter=1;

    public MDSTLBReport(){

        List<MDSTLBExpriment> mdstlbExpriments = new MDSTLBDao().getAll();
        exprimentHashtable = new Hashtable<>();
        for(MDSTLBExpriment expriment: mdstlbExpriments) {
            Instance instance = instanceManager.getById(expriment.getInstance().getId());
            exprimentHashtable.put(instance, expriment);
        }

        instances = new ArrayList<>(exprimentHashtable.keySet());
        Collections.sort(instances);

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
        MDSTLBExpriment expriment = exprimentHashtable.get(instance);
        switch (column){
            case 0 : return String.valueOf(rowCounter++);
            case 1 : return instance.getFileName();
            case 2 : return String.valueOf(instance.getSize());
            case 3 : return String.valueOf(instance.getEdgeSize());
            case 4 : return String.valueOf(instance.getMaxDegree());
            case 5 : return String.valueOf(instance.getMaxDegreeCount());
            case 6 : return String.valueOf(expriment.getC());
            case 7 : return String.valueOf(expriment.getLowerBound());
            case 8 : return String.valueOf(expriment.getOperationDuration());
        }
        return null;
    }

    @Override
    String getDescription() {
        return "This report describe the calculating lower bound based on extracting minimum maximum degree spanning tree " +
                "from the samples. The experiments is done in an incremental manner .i.e. start from c=1 and increase c until" +
                " obtaining a lower bound which is not greater than c for the instance. The following table shows the results...";
    }

    @Override
    String getColumnName(int column) {
        return columns[column];
    }

    @Override
    String getTitle() {
        return "Minimum Max Degree Spanning Tree Lower Bound";
    }
}
