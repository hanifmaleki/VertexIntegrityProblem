package at.tuwien.ac.graph.ops.ui;

import at.tuwien.ac.graph.ops.expriments.HeuristicPartialSolutionExperiment;
import at.tuwien.ac.graph.ops.instance.Instance;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


/**
 * Created by e1528895 on 3/18/18.
 */
public class HeuristicTableFrame extends JFrame implements ActionListener{
  private static final int ACTION_SHOW_TREE = 1 ;

  private JTable table ;
  private HeuristicTableModel model;
  private Instance instance ;
  public HeuristicTableFrame(Instance instance) {
    this.instance = instance;
    model =new HeuristicTableModel(instance);
    table = new JTable(model);

    add(new JScrollPane(table));

    JPanel buttonPanel = new JPanel();
    JButton treeButton = new JButton("Show Tree");
    treeButton.setActionCommand(String.valueOf(ACTION_SHOW_TREE));
    treeButton.addActionListener(this);
    buttonPanel.add(treeButton);

    add(buttonPanel, BorderLayout.SOUTH);

    this.setTitle("Heuristics of Instance "+ instance.getFileName());
    this.pack();
    this.setVisible(true);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    JButton button = (JButton) e.getSource();
    int action = Integer.parseInt(button.getActionCommand());
    switch (action){
      case ACTION_SHOW_TREE:
        HeuristicPartialSolutionExperiment experiment = model
            .getSelectedExperiment(table.getSelectedRow());
        new HeuristicTreeReport(experiment, instance).setEnabled(true);
    }


  }
}
