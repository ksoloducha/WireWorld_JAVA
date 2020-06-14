package WireWorld.windows;

import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class ParametersGUI extends JFrame {

    JButton set, setDefault;
    JTextField fileTextField, generationsTextField;
    JLabel generationsLabel, fileLabel, myFileLabel;
    JList fileList;
    JScrollPane scroller;

    String[] files = {"default_file.txt", "big_file.txt", "direct_test.txt", "structures_test.txt"};

    private int numberOfGenerations;
    private final int deafultNumberOfGenerations;
    private String inputFile;
    private final String defaultInputFile;

    public ParametersGUI(int deafultNumberOfGenerations, String defaultInputFile) {

        this.deafultNumberOfGenerations = deafultNumberOfGenerations;
        this.defaultInputFile = defaultInputFile;

        //defaults for frame
        this.setSize(400, 310);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Set simulation parameters");

        //contains all components
        JPanel thePanel = new JPanel();
        thePanel.setBorder(BorderFactory.createEmptyBorder(10, WIDTH + 10, ABORT, HEIGHT + 10));

        //creating panel elements
        fileLabel = new JLabel("Input file");

        //creating a List Box
        fileList = new JList(files);
        //define size of each cell
        fileList.setFixedCellHeight(30);
        fileList.setFixedCellWidth(220);

        myFileLabel = new JLabel("  My input file");
        fileTextField = new JTextField("", 15);

        generationsLabel = new JLabel("Number of generations");
        generationsTextField = new JTextField("", 15);

        set = new JButton("      set       ");
        setDefault = new JButton("set default");

        //adding elements to panel
        thePanel.add(fileLabel);
        thePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        thePanel.add(fileList);
        thePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        thePanel.add(myFileLabel);
        thePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        thePanel.add(fileTextField);
        thePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        thePanel.add(generationsLabel);
        thePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        thePanel.add(generationsTextField);
        thePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        thePanel.add(set);
        thePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        thePanel.add(setDefault);

        ListenForButton lForButton = new ListenForButton();
        set.addActionListener(lForButton);
        setDefault.addActionListener(lForButton);

        this.add(thePanel);
        this.setVisible(true);
    }

    public String getInputFileName() {
        return this.inputFile;
    }

    public int getNumberOfGenerations() {
        return this.numberOfGenerations;
    }

    private class ListenForButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == set) {

                try {
                    //user can insert both parameters, one or choose default values
                    if(! fileList.isSelectionEmpty() && fileTextField.getText().equals("")){

                        inputFile = "..\\WireWorld_JAVA-master\\src\\main\\java\\WireWorld\\in_files\\" + fileList.getSelectedValue();

                        if (!(new File(inputFile).exists())) {
                            throw new Exception(fileList.getSelectedValue() + " does not exist");
                        }

                        if(! generationsTextField.getText().equals(""))
                            numberOfGenerations = Integer.parseInt(generationsTextField.getText());
                        else
                            numberOfGenerations = deafultNumberOfGenerations;

                        ParametersGUI.super.dispose();
                    }
                    else if (fileTextField.getText().equals("") && generationsTextField.getText().equals("")) {
                        JOptionPane.showMessageDialog(ParametersGUI.this, "Please enter the right info or choose \"Set defalut\" button", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (fileTextField.getText().equals("")) {

                        inputFile = defaultInputFile;
                        numberOfGenerations = Integer.parseInt(generationsTextField.getText());
                        ParametersGUI.super.dispose();

                    } else if (generationsTextField.getText().equals("")) {

                        inputFile = "..\\WireWorld_JAVA-master\\src\\main\\java\\WireWorld\\in_files\\" + fileTextField.getText();

                        if (!(new File(inputFile).exists())) {
                            throw new Exception(fileTextField.getText() + " does not exist");
                        }

                        numberOfGenerations = deafultNumberOfGenerations;
                        ParametersGUI.super.dispose();

                    } else {

                        inputFile = "..\\WireWorld_JAVA-master\\src\\main\\java\\WireWorld\\in_files\\" + fileTextField.getText();

                        if (!(new File(inputFile).exists())) {
                            throw new Exception(fileTextField.getText() + " does not exist");
                        }

                        numberOfGenerations = Integer.parseInt(generationsTextField.getText());
                        ParametersGUI.super.dispose();
                    }

                } catch (NumberFormatException ex1) {
                    JOptionPane.showMessageDialog(ParametersGUI.this, "Please enter the right number of generations", "Error", JOptionPane.ERROR_MESSAGE);
                    numberOfGenerations = deafultNumberOfGenerations;
                } catch (Exception ex2) {
                    JOptionPane.showMessageDialog(ParametersGUI.this, ex2.getLocalizedMessage() + "\n Make sure that you put chosen file in in_files folder \n and insert correct file extension.", "Error", JOptionPane.ERROR_MESSAGE);
                    inputFile = defaultInputFile;

                }

            } else if (e.getSource() == setDefault) {

                inputFile = defaultInputFile;
                numberOfGenerations = deafultNumberOfGenerations;
                ParametersGUI.super.dispose();
            }
        }
    }
}
