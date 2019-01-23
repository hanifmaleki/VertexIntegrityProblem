package at.tuwien.ac.graph.ops.ui;

import at.tuwien.ac.graph.ops.dao.InstanceManager;
import at.tuwien.ac.graph.ops.instance.Instance;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * Created by e1528895 on 3/18/18.
 */
public class InstanceTableModel implements TableModel {

    InstanceManager instanceManager = new InstanceManager();
    List<Instance> instances ;
  private String[] columns ={"Name", "Order", "Edge", "Max_Deg", "MAX_DEG_COUNT"};

  public InstanceTableModel() {
    instances = instanceManager.getAllAscending();
  }

  @Override
  public int getRowCount() {
    return instances.size();
  }

  @Override
  public int getColumnCount() {
    return columns.length;
  }

  @Override
  public String getColumnName(int columnIndex) {
    return columns[columnIndex];
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return String.class;
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Instance instance = instances.get(rowIndex);
    switch (columnIndex){
      case 0: return instance.getFileName();
      case 1: return instance.getSize()+"";
      case 2: return instance.getEdgeSize()+"";
      case 3: return instance.getMaxDegree()+"";
      case 4: return String.valueOf(instance.getMaxDegreeCount());
    }
    return null;
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

  }

  @Override
  public void addTableModelListener(TableModelListener l) {

  }

  @Override
  public void removeTableModelListener(TableModelListener l) {

  }

  public Instance getSelectedInstance(int selectedRow) {
    return instances.get(selectedRow);
  }
}
