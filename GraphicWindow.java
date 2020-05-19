package WireWorld;

import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.geom.*;

public class GraphicWindow extends JFrame {

    private final int xMax = 1300;
    private final int yMax = 700;
    private double scale = 1;
    CellSet myCellSet;

    public GraphicWindow(CellSet myCellSet) {

        this.myCellSet = myCellSet;
        this.setScale();
        this.setTitle("Wire World Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new Draw(), BorderLayout.CENTER);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.black);
    }

    private void setScale() {

        int xSize = this.myCellSet.getXSize();
        int ySize = this.myCellSet.getYSize();

        double scaleX = (double) xMax / (double) xSize;
        double scaleY = (double) yMax / (double) ySize;

        this.scale = (scaleX > 1 || scaleY > 1) ? Math.min(scaleX, scaleY) : Math.max(scaleX, scaleY);

        this.setSize((int) Math.ceil(this.scale * xSize) + 15, (int) Math.ceil(this.scale * ySize) + 40);
    }

    private class Draw extends JComponent {

        @Override
        public void paint(Graphics g) {

            Graphics2D graph2 = (Graphics2D) g;

            graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int x, y;
            x = y = 0;

            for (Cell c : myCellSet) {

                Shape drawCell = new Rectangle2D.Double(c.getX() * scale, c.getY() * scale, scale, scale);

                if (c.getX() > x) {
                    x = c.getX();
                }
                if (c.getY() > y) {
                    y = c.getY();
                }

                switch (c.getState()) {
                    case "Empty":
                        break;
                    case "ElectronHead":
                        graph2.setColor(Color.blue);
                        graph2.fill(drawCell);
                        graph2.setColor(Color.black);
                        graph2.draw(drawCell);
                        break;
                    case "ElectronTail":
                        graph2.setColor(Color.red);
                        graph2.fill(drawCell);
                        graph2.setColor(Color.black);
                        graph2.draw(drawCell);
                        break;
                    case "Conductor":
                        graph2.setColor(Color.yellow);
                        graph2.fill(drawCell);
                        graph2.setColor(Color.black);
                        graph2.draw(drawCell);
                        break;
                }
            }
        }
    }
}
