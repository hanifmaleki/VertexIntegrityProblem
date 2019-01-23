package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.dao.SimpleLPDao;
import at.tuwien.ac.graph.ops.dao.VertexDao;
import at.tuwien.ac.graph.ops.expriments.SimpleLPExpriment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by e1528895 on 8/30/17.
 */
public class AllSimpleLPReport extends Report {
    String[] columns = {"R", "Name", "$|V|$", "$|E|$", "$\\Delta$", "$\\# \\Delta$", "c", "\\#BG", "OP", "LB", "UB", "Timeout", "Components size"};
    SimpleLPDao dao = new SimpleLPDao();
    InstanceManager instanceManager = new InstanceManager();
    VertexDao vertexDao = new VertexDao();
    List<SimpleLPExpriment> experimentList = new ArrayList<>();
    int rowCounter = 1 ;

    public AllSimpleLPReport(){

        List<Instance> instances = instanceManager.getAllAscending();
        for(Instance instance: instances){
            List<SimpleLPExpriment> expriments = dao.getAllExprimentsByInstance(instance);
            if(expriments.isEmpty())
                continue;
            Collections.sort(expriments);
            for(SimpleLPExpriment experiment : expriments){
                experiment.setInstance(instance);
                experimentList.add(experiment);
            }
        }
    }

    @Override
    int getColumnCount() {
        return columns.length;
    }

    @Override
    int getRowCount() {
        return experimentList.size();
    }
    Instance previousInstance = null ;
    @Override
    String getCellContent(int row, int column) {
        SimpleLPExpriment experiment = experimentList.get(row);
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
            case 7:
                int bagCount = 2 * instance.getSize() / experiment.getC();
                return String.valueOf(bagCount);
            case 8:
                return millisecondsToFormat(experiment.getOperationDuration());
            case 9: return String.valueOf(experiment.getLb());
            case 10:
                return String.valueOf(experiment.getK());
            case 11:
                return String.valueOf(experiment.getTimeout());
            case 12:
                return getComponentsStringOf(experiment, instance);

        }
        return "";
    }

    @Override
    String getDescription() {
        return "LP Formula";
    }

    @Override
    String getColumnName(int column) {
        return columns[column];
    }

    @Override
    String getTitle() {
        return "Linear Formula for Connected Component Order";
    }

    public static void main(String[] args) {
        new AllSimpleLPReport().generateReportIntoFile("alllp.tex");
        PersistenceManager.close();
    }
}
