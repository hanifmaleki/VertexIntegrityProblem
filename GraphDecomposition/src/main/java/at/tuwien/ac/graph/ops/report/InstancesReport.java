package at.tuwien.ac.graph.ops.report;

import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.instance.Instance;
import at.tuwien.ac.graph.utils.PersistenceManager;
import com.sun.xml.internal.ws.api.server.InstanceResolver;

import java.util.List;

/**
 * Created by e1528895 on 10/27/17.
 */
public class InstancesReport extends Report {
    String[] columns = {"Row", "Name", "Type", "$|V|$", "$|E|$", "$\\Delta$", "$\\# \\Delta$"};

    InstanceManager instanceManager = new InstanceManager();

    List<Instance> allAscending = null;
    private int rowCounter=1;

    public InstancesReport(){
        allAscending = instanceManager.getAllAscending();
    }

    @Override
    int getColumnCount() {
        return columns.length;
    }

    @Override
    int getRowCount() {
        return allAscending.size();
    }

    @Override
    String getCellContent(int row, int column) {
        Instance instance = allAscending.get(row);

        switch (column) {
            case 0:
                return String.valueOf(rowCounter++);
            case 1:
                return instance.getFileName();
            case 2:
                return instance.getType();
            case 3:
                return String.valueOf(instance.getSize());
            case 4:
                return String.valueOf(instance.getEdgeSize());
            case 5:
                return String.valueOf(instance.getMaxDegree());
            case 6:
                return String.valueOf(instance.getMaxDegreeCount());
        }

            return null;

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
        return "Instances";
    }

    public static void main(String[] args) {
        new InstancesReport().generateReportIntoFile("instances.tex");
        PersistenceManager.close();
    }
}
