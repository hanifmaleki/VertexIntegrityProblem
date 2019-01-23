package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.expriments.LBMinorWidthExpriment;
import at.tuwien.ac.graph.ops.expriments.MDSTLBExpriment;
import at.tuwien.ac.graph.ops.expriments.OneMaxDegreeUB;
import at.tuwien.ac.graph.ops.expriments.SeparationMinimumMaxComponentExpriment;
import at.tuwien.ac.graph.ops.dao.*;
import at.tuwien.ac.graph.ops.instance.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by e1528895 on 8/3/17.
 */
public class BoundSeparationReport extends Report {
    Logger logger = LoggerFactory.getLogger(BoundSeparationReport.class);
    SeparationMinMaxCompDao separationMinMaxCompDao = new SeparationMinMaxCompDao();
    VertexDao vertexDao = new VertexDao();
    String[] columns = {"Row", "Name", "nodes", "Edges", "M\\_Deg", "\\#M_D", "LB\\_Min","LB\\_MDST", "UB", "Sep", "Separation Components"};
    Hashtable<Instance, SeparationMinimumMaxComponentExpriment> hashtable = new Hashtable<>();
    List<Instance> instances = new ArrayList<>();
    private int rowCounter=1;
    InstanceManager instanceManager = new InstanceManager();
    LBMinorWidthDao lbMinorWidthDao = new LBMinorWidthDao();
    MDSTLBDao mdstlbDao = new MDSTLBDao();
    OneMaxDegDao oneMaxDegDao = new OneMaxDegDao();

    public BoundSeparationReport(){
        List<Instance> instanceList = new InstanceManager().getAll();
        for(Instance instance: instanceList) {
            SeparationMinimumMaxComponentExpriment expriment = separationMinMaxCompDao.getExprimentByInstance(instance);
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
        SeparationMinimumMaxComponentExpriment expriment = hashtable.get(instance);




        switch (column) {
            case 0:
                return String.valueOf(rowCounter++);
            case 1:
                return instance.getFileName() ;
            case 2:
                return String.valueOf(instance.getSize());
            case 3:
                return String.valueOf(instance.getEdgeSize());
            case 4:
                return String.valueOf(instance.getMaxDegree());
            case 5:
                return String.valueOf(instance.getMaxDegreeCount());
            case 6:
                LBMinorWidthExpriment minor = lbMinorWidthDao.getExprimentByInstance(instance);
                if(minor==null)
                    return null ;
                return String.valueOf(minor.getSize());
            case 7:MDSTLBExpriment mst = mdstlbDao.getExprimentByInstance(instance);
                    if(mst==null)
                        return null;
                    return String.valueOf(mst.getLowerBound());
            case 8:
                OneMaxDegreeUB ub = oneMaxDegDao.getExprimentByInstance(instance);
                if(ub==null)
                    return null;
                return String.valueOf(ub.getSize());
            case 9: return String.valueOf(expriment.getSize());
            case 10: return getComponentsStringOf(expriment, instance);

        }
        return null;
    }




    @Override
    String getDescription() {
        return "Separation method is the method that try to find best subset of vertices which makes a large instance " +
                "split into some new components. In this method it is tried to minimize the size of the largest " +
                "component. together with the bounds that obtained from the other methods we can limit our solution and " +
                "approach closer to the optimal solution. But for each instance we should deal in different ways. The " +
                "following table shows the results ";
    }

    @Override
    String getColumnName(int column) {
        return columns[column];
    }

    @Override
    String getTitle() {
        return "A Comparison between Bounds and Separation Method";
    }


    public static void main(String[] args) {
        new BoundSeparationReport().generateReportIntoFile("allinone.tex");

    }
}
