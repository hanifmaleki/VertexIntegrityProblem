package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.newgraph.GraphNew;
import at.tuwien.ac.graph.newgraph.GraphNewHelper;
import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.dao.SeparationMMDao;
import at.tuwien.ac.graph.ops.dao.VertexDao;
import at.tuwien.ac.graph.ops.expriments.SeparationMinimumSizeMinimumMaxExpriment;
import at.tuwien.ac.graph.ops.expriments.VertexEntity;
import at.tuwien.ac.graph.ops.instance.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by e1528895 on 8/3/17.
 */
public class SeparationMinMinReport extends Report {
    Logger logger = LoggerFactory.getLogger(SeparationMinMinReport.class);
    SeparationMMDao separationDao = new SeparationMMDao();
    VertexDao vertexDao = new VertexDao();
    String[] columns = {"Row", "Name", "Vertices", "Edges", "Max Deg", "\\#Max Deg", "OP_TIME", "Size", "Components size"};
    Hashtable<Instance, SeparationMinimumSizeMinimumMaxExpriment> hashtable = new Hashtable<>();
    List<Instance> instances = new ArrayList<>();
    private int rowCounter=1;
    InstanceManager instanceManager = new InstanceManager();

    public SeparationMinMinReport(){
        List<Instance> instanceList = new InstanceManager().getAll();
        for(Instance instance: instanceList) {
            SeparationMinimumSizeMinimumMaxExpriment expriment = separationDao.getExprimentByInstance(instance);
            if(expriment!=null) {
                hashtable.put(instance, expriment);
                instances.add(instance);
            }
        }

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
        SeparationMinimumSizeMinimumMaxExpriment expriment = hashtable.get(instance);
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
            case 6: return String.valueOf(expriment.getOperationDuration());
            case 7: return String.valueOf(expriment.getSize());
            case 8: return getComponentsStringOf(expriment, instance);


        }
        return null;
    }

    //TODO move to a better class
    private String getComponentsStringOf(SeparationMinimumSizeMinimumMaxExpriment expriment, Instance instance) {
        List<VertexEntity> separation = vertexDao.getVerticesOf(expriment);
        GraphNew graph = instanceManager.getGraph(instance);
        String string ="";
        for(VertexEntity entity: separation) {
            Integer vertex = entity.getVertex();
            graph = graph.removeVertexCopy(vertex);
        }
        List<GraphNew> connectedComponentsHS = GraphNewHelper.getConnectedComponentsHS(graph);

        Hashtable<Integer, Integer> componentTable = new Hashtable<>();
        for(GraphNew component: connectedComponentsHS){
            Integer key = component.getSize();
            Integer value = componentTable.get(key);
            if(value ==null)
                componentTable.put(key, 1);
            else
                componentTable.put(key, (value+1));
        }

        Set<Integer> keys = componentTable.keySet();
        List<Integer> sortedKeys = new ArrayList<>(keys);
        Collections.sort(sortedKeys, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });

        for(Integer key : sortedKeys){
            Integer value = componentTable.get(key);
            string += key ;
            if(value > 1)
                string += "("+value+")";
            string += ",";
        }

        return string;
    }

    @Override
    String getDescription() {
        return "Separation method is the method that try to find best subset of vertices which makes a large instance " +
                "split into some new components. In this method it is tried to minimize the size of the largest " +
                "component. As it is evident in the above table in some instances the number of the separation vertices is " +
                "relatively is relatively great. May be finding a smaller separation which has smaller number of " +
                "vertices is good idea.......";
    }

    @Override
    String getColumnName(int column) {
        return columns[column];
    }

    @Override
    String getTitle() {
        return "Separation Heuristic Method";
    }


    public static void main(String[] args) {
        new SeparationMinMinReport().generateReportIntoFile("separation2.tex");

    }
}
