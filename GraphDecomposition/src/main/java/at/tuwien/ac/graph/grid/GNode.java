package at.tuwien.ac.graph.grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;

/**
 * Created by e1528895 on 11/2/18.
 */
public class GNode extends JComponent {
  enum Status{NORMAL,FOCOUSED,FIXED}

  private int radius;
  private int row;
  private int column;
  Color normalColor = Color.blue;
  Color focusedColor = Color.red;
  Color fixedColor = Color.magenta;
  private boolean showText = true ;
  private boolean focoused = false;

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, radius, radius);
    //int vertex = row*h+column;
    g2.setColor(normalColor);
    if(focoused) {
      g.setColor(focusedColor);
    }
    g2.fill(circle);
  }
}
