package WireWorld;

import WireWorld.windows.ParametersGUI;
import WireWorld.windows.GraphicWindow;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;

public class WireWorld {

    private static String inputFileName = "..\\WireWorld_JAVA-master\\src\\main\\java\\WireWorld\\in_files\\default_file.txt";
    private static int numberOfGenerations;

    public static void main(String[] args) {

        CellSet cellSet = new CellSet();

        ParametersGUI gui = new ParametersGUI(50, inputFileName);

        WindowListener listener = new WindowAdapter() {
            public void windowClosed(WindowEvent evt) {

                inputFileName = gui.getInputFileName();
                numberOfGenerations = gui.getNumberOfGenerations();

                try {
                    cellSet.createCellSetFromFile(inputFileName);

                    try {
                        cellSet.saveCellSetToFile("..\\WireWorld_JAVA-master\\src\\main\\java\\WireWorld\\out_files\\output_0.txt");
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(gui, e.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }

                    GraphicWindow graphicWindow = new GraphicWindow(cellSet, numberOfGenerations);

                } catch (FileNotFoundException | NumberFormatException e1) {
                    JOptionPane.showMessageDialog(gui, e1.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);

                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(gui, e2.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }
        };

        gui.addWindowListener(listener);
    }
}
