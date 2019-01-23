package at.tuwien.ac.graph.grid;

import at.tuwien.ac.graph.utils.GFReader;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Created by e1528895 on 11/1/18.
 */
public class GridFrame extends JFrame {

  private final List<Integer> vertices;
  private final int w;
  private final int h;
  private final int c;
  private final int k;


  public GridFrame(int width, int height, int c, int k, List<Integer> vertices) {
    this.w= width ;
    this.h = height;
    this.c = c;
    this.vertices = vertices;
    this.k = k;

    setTitle("Grid ");
    add(new GridDrawer(w,h,c,k, vertices), BorderLayout.CENTER);
    setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    JButton export = new JButton("Export");
    export.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });
    this.add(export, BorderLayout.SOUTH);
    setVisible(true);
  }

  public static void main(String[] args) {
    Integer[] list = {2,6,10,12,14,16,18,20,22,24,26,28,31,34,38,39,41,42,46,49,52,54,56,58,60,62,64,66,68,70,74,78};
    new GridFrame(9,9,3,list.length,Arrays.asList(list));
  }
}
