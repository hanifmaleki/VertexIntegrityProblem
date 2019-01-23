package at.tuwien.ac.graph.ops.ui;

import at.tuwien.ac.graph.ops.dao.FinalHeuristicDao;
import at.tuwien.ac.graph.ops.dao.OneMaxDegDao;
import at.tuwien.ac.graph.ops.expriments.FinalHeuristicSolutionExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by e1528895 on 3/18/18.
 */
public class InstancesTable extends JFrame implements ListSelectionListener, ActionListener {

  private static final int ACTION_HEURISTIC_TREE = 1;
  private OneMaxDegDao ubDao = new OneMaxDegDao();
  FinalHeuristicDao finalHeuristicDao = new FinalHeuristicDao();

  private InstanceTableModel instanceTableModel;
  JTable table ;
  JPanel info ;
  JLabel nameNameLabel;
  JLabel ubNameLabel;
  JLabel nameValueLabel;
  JLabel ubValueLabel ;
  JLabel heuristicLBNameLabel;
  JLabel heuristicLBValueLabel;
  JLabel heuristicUBNameLabel;
  JLabel heuristicUBValueLabel;

  JButton heuristicButton;


  public InstancesTable() {

    instanceTableModel = new InstanceTableModel();
    table = new JTable(instanceTableModel);

    add (new JScrollPane(table));
    table.getSelectionModel().addListSelectionListener(this);
    initialInfoPanel();

    JPanel buttonPanel = new JPanel();
    heuristicButton = new JButton("Heuristic Results");
    heuristicButton.addActionListener(this);
    heuristicButton.setActionCommand(String.valueOf(ACTION_HEURISTIC_TREE));
    buttonPanel.add(heuristicButton);
    add(buttonPanel, BorderLayout.SOUTH);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("All Instances");
    this.pack();
    this.setVisible(true);
  }

  private void initialInfoPanel() {
    info = new JPanel();

    nameNameLabel = new JLabel("name");
    ubNameLabel = new JLabel("upper bound");
    heuristicLBNameLabel = new JLabel("Heurstic lb");
    heuristicUBNameLabel = new JLabel("Heuristic ub");
    nameValueLabel = new JLabel();
    ubValueLabel = new JLabel();
    heuristicLBValueLabel = new JLabel();
    heuristicUBValueLabel = new JLabel();
    info.setLayout(new GridLayout(0, 2));
    info.add(nameNameLabel);
    info.add(nameValueLabel);
    info.add(ubNameLabel);
    info.add(ubValueLabel);
    info.add(heuristicLBNameLabel);
    info.add(heuristicLBValueLabel);
    info.add(heuristicUBNameLabel);
    info.add(heuristicUBValueLabel);
    info.setPreferredSize(new Dimension(200,200));
    add (info, BorderLayout.EAST);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new InstancesTable();
      }
    });
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    if (e.getSource() == table.getSelectionModel()&&e.getValueIsAdjusting()) {
      int selectedRow = table.getSelectedRow();
      Instance instance = instanceTableModel.getSelectedInstance(selectedRow);
      updateInfoPanel(instance);
    }
  }

  private void updateInfoPanel(Instance instance) {
    nameValueLabel.setText(instance.getFileName());
    ubValueLabel.setText(String.valueOf(ubDao.getExprimentByInstance(instance).getSize()));
    FinalHeuristicSolutionExperiment finalHeuristic = finalHeuristicDao
        .getExprimentByInstance(instance);
    if(finalHeuristic!=null) {
      heuristicLBValueLabel.setText(String.valueOf(finalHeuristic.getcLowerBound()));
      heuristicUBValueLabel.setText(String.valueOf(finalHeuristic.getK()));
    }else{
      heuristicLBValueLabel.setText("");
      heuristicUBValueLabel.setText("");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton source = (JButton) e.getSource();
    int selectedRow = table.getSelectedRow();
    if(selectedRow<0)
      return;
    Instance instance = instanceTableModel.getSelectedInstance(selectedRow);
    int command = Integer.parseInt(source.getActionCommand());
    switch (command){
      case ACTION_HEURISTIC_TREE:
        HeuristicTableFrame heuristicTableFrame = new HeuristicTableFrame(instance);
        heuristicTableFrame.setEnabled(true);
    }
  }
}
