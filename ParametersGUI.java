package WireWorld;

import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class ParametersGUI extends JFrame {

    JButton set, setDefault;
    JTextField fileTextField, generationsTextField;
    JLabel fileLabel, generationsLabel;

    private int numberOfGenerations;
    private final int deafultNumberOfGenerations;
    private String inputFile;
    private final String defaultInputFile;

    public ParametersGUI(int deafultNumberOfGenerations, String defaultInputFile) {

        this.deafultNumberOfGenerations = deafultNumberOfGenerations;
        this.defaultInputFile = defaultInputFile;

        //defaults for frame
        this.setSize(400, 220);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Set simulation parameters");

        //contains all components
        JPanel thePanel = new JPanel();
        //vertical arrangement of elements
        thePanel.setLayout(new BoxLayout(thePanel, BoxLayout.Y_AXIS));
        thePanel.setBorder(BorderFactory.createEmptyBorder(10, WIDTH + 10, ABORT, HEIGHT + 10));

        //contains buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 140, 10, 10));

        fileLabel = new JLabel("Input file");
        fileTextField = new JTextField("", 15);
        generationsLabel = new JLabel("Number of generations");
        generationsTextField = new JTextField("", 15);
        set = new JButton("      set       ");
        setDefault = new JButton("set default");

        thePanel.add(fileLabel);
        thePanel.add(fileTextField);
        thePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        thePanel.add(generationsLabel);
        thePanel.add(generationsTextField);

        buttonPanel.add(set);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(setDefault);

        thePanel.add(buttonPanel);

        ListenForButton lForButton = new ListenForButton();
        set.addActionListener(lForButton);
        setDefault.addActionListener(lForButton);

        this.add(thePanel);
        this.setVisible(true);

        fileTextField.requestFocus();
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
                    
                    if (fileTextField.getText().equals("") && generationsTextField.getText().equals("")) {
                        JOptionPane.showMessageDialog(ParametersGUI.this, "Please enter the right info or choose \"Set defalut\" button", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (fileTextField.getText().equals("")) {

                        inputFile = defaultInputFile;
                        numberOfGenerations = Integer.parseInt(generationsTextField.getText());
                        ParametersGUI.super.dispose();

                    } else if (generationsTextField.getText().equals("")) {

                        inputFile = "..\\WireWorld\\src\\main\\java\\WireWorld\\in_out_files\\" + fileTextField.getText();

                        if (!(new File(inputFile).exists())) {
                            throw new Exception(fileTextField.getText() + "does not exist");
                        }

                        numberOfGenerations = deafultNumberOfGenerations;
                        ParametersGUI.super.dispose();

                    } else {

                        inputFile = "..\\WireWorld\\src\\main\\java\\WireWorld\\in_out_files\\" + fileTextField.getText();

                        if (!(new File(inputFile).exists())) {
                            throw new Exception(fileTextField.getText() + "does not exist");
                        }

                        numberOfGenerations = Integer.parseInt(generationsTextField.getText());
                        ParametersGUI.super.dispose();
                    }

                } catch (NumberFormatException ex1) {
                    JOptionPane.showMessageDialog(ParametersGUI.this, "Please enter the right number of generations", "Error", JOptionPane.ERROR_MESSAGE);
                    numberOfGenerations = deafultNumberOfGenerations;
                } catch (Exception ex2) {
                    JOptionPane.showMessageDialog(ParametersGUI.this, ex2.getLocalizedMessage() + "\n Make sure that you put " + fileTextField.getText() + " file in in_out_files folder \n and insert correct file extension.", "Error", JOptionPane.ERROR_MESSAGE);
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
