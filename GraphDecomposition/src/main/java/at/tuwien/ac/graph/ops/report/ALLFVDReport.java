package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.dao.SimpleFVDDao;
import at.tuwien.ac.graph.ops.dao.VertexDao;
import at.tuwien.ac.graph.ops.expriments.SimpleFVDExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by e1528895 on 9/3/17.
 */
public class ALLFVDReport extends Report {

    String[] columns = {"R", "Name", "$|V|$", "$|E|$", "$\\Delta$", "$\\# \\Delta$", "c", "k", "OP", "Size", "Timeout", "T", "Components size"};
    SimpleFVDDao dao = new SimpleFVDDao();
    InstanceManager instanceManager = new InstanceManager();
    VertexDao vertexDao = new VertexDao();
    List<SimpleFVDExperiment> experimentList = new ArrayList<>();
    int rowCounter = 1 ;

    public ALLFVDReport(){
        List<Instance> instances = instanceManager.getAllAscending();
        for(Instance instance: instances){
            List<SimpleFVDExperiment> expriments = dao.getAllExprimentsByInstance(instance);
            if(expriments.isEmpty())
                continue;
            Collections.sort(expriments);
            for(SimpleFVDExperiment experiment : expriments){
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
        SimpleFVDExperiment experiment = experimentList.get(row);
        Instance instance = experiment.getInstance();
        boolean b = (previousInstance == null) || (!previousInstance.getFileName().equalsIgnoreCase(instance.getFileName()));

        switch (column) {
            case 0:
                return String.valueOf(rowCounter++);
            case 1:
                if (b)
                    return instance.getFileName();
                return "";
            case 2:
                if (b)
                    return String.valueOf(instance.getSize());
                return "";
            case 3:
                if (b)
                    return String.valueOf(instance.getEdgeSize());
                return "";
            case 4:
                if (b)
                    return String.valueOf(instance.getMaxDegree());
                return "";
            case 5:
                if (b) {
                    previousInstance = instance;
                    return String.valueOf(instance.getMaxDegreeCount());
                }
                return "";

            case 6:
                return String.valueOf(experiment.getC());
            case 7:
                return String.valueOf(experiment.getK());
            case 8:
                return millisecondsToFormat(experiment.getOperationDuration());
            case 9:
                return String.valueOf(experiment.getFvdSize());
            case 10:
                return String.valueOf(experiment.getTimeout());
            case 11:
                if (experiment.getFinished())
                    return "1";
                return "0";
            case 12:
                return getComponentsStringOf(experiment, instance);

        }
        return null;
    }

    @Override
    String getDescription() {
        return "Fixed Parameter Algorithm for Component Order Connectivity Probelm";
    }

    @Override
    String getColumnName(int column) {
        return columns[column];
    }

    @Override
    String getTitle() {
        return "Fixed Parameter Algorithm for Component Order Connectivity Probelm";
    }

    public static void main(String[] args) {
        new ALLFVDReport().generateReportIntoFile("allfvd.tex");
        PersistenceManager.close();
    }
}
