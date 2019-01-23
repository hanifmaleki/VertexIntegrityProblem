package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.dao.SimpleSatDao;
import at.tuwien.ac.graph.ops.dao.VertexDao;
import at.tuwien.ac.graph.ops.expriments.SimpleSatExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by e1528895 on 8/30/17.
 */
public class AllSatReport extends Report {
    String[] columns = {"R", "Name", "$|V|$", "$|E|$", "$\\Delta$", "$\\# \\Delta$", "c", "k", "OP", "Size", "Timeout", "T", "Components size"};
    SimpleSatDao dao = new SimpleSatDao();
    InstanceManager instanceManager = new InstanceManager();
    VertexDao vertexDao = new VertexDao();
    List<SimpleSatExperiment> experimentList = new ArrayList<>();
    int rowCounter = 1 ;

    public AllSatReport(){

        List<Instance> instances = instanceManager.getAllAscending();
        for(Instance instance: instances){
            List<SimpleSatExperiment> expriments = dao.getAllExprimentsByInstance(instance);
            if(expriments.isEmpty())
                continue;
            Collections.sort(expriments);
            for(SimpleSatExperiment experiment : expriments){
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
        SimpleSatExperiment experiment = experimentList.get(row);
        Instance instance = experiment.getInstance();

        switch (column) {
            case 0:
                return String.valueOf(rowCounter++);
            case 1:
                 if((previousInstance ==null)||(!previousInstance.getFileName().equalsIgnoreCase(instance.getFileName()))) {
                     previousInstance = instance ;
                     return instance.getFileName();
                 }
                 return "";
            case 2:
                return String.valueOf(instance.getSize());
            case 3:
                return String.valueOf(instance.getEdgeSize());
            case 4:
                return String.valueOf(instance.getMaxDegree());
            case 5:
                return String.valueOf(instance.getMaxDegreeCount());
            case 6: return String.valueOf(experiment.getC());
            case 7: return String.valueOf(experiment.getK());
            case 8:
                return millisecondsToFormat(experiment.getOperationDuration());
            case 9:
                return String.valueOf(experiment.getFvdSize());
            case 10:
                return String.valueOf(experiment.getTimeout());
            case 11:
                if(experiment.getFinished())
                    return "1";
                return "0";
            case 12:
                return getComponentsStringOf(experiment, instance);

        }
        return "";
    }

    @Override
    String getDescription() {
        return "Sat Formula";
    }

    @Override
    String getColumnName(int column) {
        return columns[column];
    }

    @Override
    String getTitle() {
        return "SAT Formula for Connected Component Order";
    }

    public static void main(String[] args) {
        new AllSatReport().generateReportIntoFile("allsat.tex");
        PersistenceManager.close();
    }
}
