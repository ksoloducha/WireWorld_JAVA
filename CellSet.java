package WireWorld;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CellSet {

    private Cell[] set;
    private int xSize;
    private int ySize;

    public void initCellSet(int x, int y) {
        this.xSize = x;
        this.ySize = y;
        this.set = new Cell[x * y];

        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                try {
                    this.set[i + j * x] = new Cell(i, j, "Empty");
                } catch (IOException e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }
    }

    public void setXSize(int x) {
        this.xSize = x;
    }

    public void setYSize(int y) {
        this.ySize = y;
    }

    public int getXSize() {
        return this.xSize;
    }

    public int getYSize() {
        return this.ySize;
    }

    public String getCellState(int x, int y) {
        return set[x + y * this.getXSize()].getState();
    }

    public void setCellState(int x, int y, String state) {

        try {
            set[x + y * this.getXSize()].setState(state);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void createCellSetFromFile(String fileName) {

        try {
            Scanner readFile = new Scanner(new File(fileName));

            int xMax, yMax;

            if (!readFile.hasNextInt()) {
                throw new Exception("Wrong file format");
            } else {
                xMax = readFile.nextInt();
            }

            if (!readFile.hasNextInt()) {
                throw new Exception("Wrong file format");
            } else {
                yMax = readFile.nextInt();
            }

            readFile.nextLine();

            this.initCellSet(xMax, yMax);

            while (readFile.hasNextLine()) {
                String[] words = readFile.nextLine().split("[,: ]+");

                if (words.length != 3) {
                    throw new Exception("Wrong file format");
                }

                int x = Integer.parseInt(words[1]);
                int y = Integer.parseInt(words[2]);

                this.setCellState(x, y, words[0]);
            }

        } catch (FileNotFoundException | NumberFormatException e1) {
            System.out.println(e1.getLocalizedMessage());
        } catch (Exception e2) {
            System.out.println(e2.getLocalizedMessage());
        }
    }

    public void saveCellSetToFile(String fileName) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {

            writer.flush();
            writer.write(this.getXSize() + " " + this.getYSize() + "\n");

            for (Cell c : this.set) {
                if (!c.getState().equals("Empty")) {
                    writer.write(c.getState() + ": " + c.getX() + ", " + c.getY() + "\n");
                } else {
                }
            }
        }
    }
}
