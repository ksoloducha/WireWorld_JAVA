package WireWorld;

import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.geom.*;
import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

public class GraphicWindow extends JFrame {

    //max frame size
    private final int xMax = 1300;
    private final int yMax = 700;
    private double scale = 1;
    CellSet myCellSet;
    private int numberOfGenerations = 0;
    private int constantNumberOfGenerations = 0;

    public GraphicWindow(CellSet myCellSet, int numberOfGenerations) {

        this.myCellSet = myCellSet;
        this.numberOfGenerations = numberOfGenerations;
        this.constantNumberOfGenerations = numberOfGenerations;
        this.setScale();
        this.setTitle("Wire World Simulation");
        //defines how frame closes
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new Draw(), BorderLayout.CENTER);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.black);

        //execute code after delay
        //attribute is corePoolSize - the number of threads to keep in the pool
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        //execute RepaintTheWindow method, initial delay, subsequent delay in miliseconds
        executor.scheduleAtFixedRate(new RepaintTheWindow(this), 0L, 200L, TimeUnit.MILLISECONDS);
    }

    //scales cell set's size to fit in frame
    private void setScale() {

        int xSize = this.myCellSet.getXSize();
        int ySize = this.myCellSet.getYSize();

        double scaleX = (double) xMax / (double) xSize;
        double scaleY = (double) yMax / (double) ySize;

        this.scale = (scaleX > 1 || scaleY > 1) ? Math.min(scaleX, scaleY) : Math.max(scaleX, scaleY);

        this.setSize((int) Math.ceil(this.scale * xSize) + 15, (int) Math.ceil(this.scale * ySize) + 40);
    }

    //continually redraw the screen while other code continues to execute
    private class RepaintTheWindow implements Runnable {

        private final GraphicWindow theGraphicWindow;

        public RepaintTheWindow(GraphicWindow theGraphicWindow) {
            this.theGraphicWindow = theGraphicWindow;
        }

        @Override
        public void run() {
            int n = constantNumberOfGenerations - numberOfGenerations;
            if(n <= constantNumberOfGenerations){
                try {
                    myCellSet.saveCellSetToFile("..\\WireWorld_JAVA-master\\src\\main\\java\\WireWorld\\out_files\\output_" + n + ".txt");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }
            this.theGraphicWindow.repaint();
        }
    }

    //specifies what to draw in frame
    private class Draw extends JComponent {

        @Override
        public void paint(Graphics g) {

            //base class that allows for drawing on components
            Graphics2D graph2 = (Graphics2D) g;

            //preferences for rendering
            //KEY_ANTIALIASING reduces artifacts on shapes, VALUE_ANTIALIAS_ON will clean up the edges
            graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            for (Cell c : myCellSet) {

                //rectangle represents each cell
                Shape drawCell = new Rectangle2D.Double(c.getX() * scale, c.getY() * scale, scale, scale);

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
            if (numberOfGenerations-- > 0) {
                myCellSet.generateNext();
            }
        }
    }
}
