package at.tuwien.ac.graph.grid;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Created by e1528895 on 11/1/18.
 */
public class GridDrawer extends JPanel {

  private final List<Integer> vertices;
  private final int w;
  private final int h;
  private final int c;
  private final int k;

  public GridDrawer(int width, int height, int c, int k, List<Integer> vertices){
    this.w= width ;
    this.h = height;
    this.c = c;
    this.vertices = vertices;
    this.k=k;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    int height = this.getHeight();
    int width = this.getWidth();
    int value1 = height / (2 * w);
    int value2 = width / (2 * h);
    int radius = Math.min(value1,value2);
    g.setFont(new Font("TimesRoman", Font.PLAIN, radius/5));
    g2.setStroke(new BasicStroke(radius/20));
    int offset = radius/2 ;
    int length=radius;
    int counter=0;
    for(int i=0; i < w;i++) {
      for (int j = 0; j < h; j++) {
        int x = 10 + j * radius * 2;
        int y = 10 + i * radius * 2;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, radius, radius);
        int vertex = i*h+j;
        g2.setColor(Color.blue);
        if(vertices.contains(vertex))
          g.setColor(Color.red);
        g2.fill(circle);
        g2.setColor(Color.BLACK);
        String str = "" + vertex ;//+ "(" + i+","+j + ")";
        g2.drawString(str, x+offset-10, y+offset);
        counter++;
        g2.setColor(Color.LIGHT_GRAY);
        if(j<h-1){
          g2.drawLine(x +2*offset, y + offset,
              x +2*offset+length, y + offset);
        }
        if(i<w-1) {
          g.drawLine(x+offset, y+2*offset, x+offset, y+length+2*offset);
        }
      }

    }
    g2.setFont(new Font("TimesRoman", Font.BOLD, 20));
    g2.setColor(Color.BLACK);
    String str = "Grid("+ w+","+h+")&& c="+c;
    g2.drawString(str, 4*width/5, 4*height/5);
    str = "c="+c+ "& k="+k;
    g2.drawString(str, 4*width/5, 4*height/5+20);

  }

}
