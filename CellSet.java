package WireWorld;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class CellSet implements Iterable<Cell> {

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

            set[x + y * this.getXSize()].setState(state);
    }

    public void createCellSetFromFile(String fileName) throws Exception {

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
                
                if(x > xMax)
                    xMax = x;
                if(y > yMax)
                    yMax = y;

                if(StateType.isStateType(words[0]))
                    this.setCellState(x, y, words[0]);
                else
                    throw new Exception("Wrong file format");
            }

        } catch (FileNotFoundException | NumberFormatException e1) {
            throw e1;
        } catch (Exception e2) {
            throw e2;
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

    public void generateNext() {

        CellSet copy = new CellSet();
        copy.initCellSet(xSize, ySize);
        for (Cell c : copy) {
            if (!this.getCellState(c.getX(), c.getY()).equals("Empty")) {
                    c.setState(this.getCellState(c.getX(), c.getY()));
            }
        }

        for (Cell c : copy) {

            switch (c.getState()) {
                case "Empty":
                    break;
                case "ElectronHead":
                    this.setCellState(c.getX(), c.getY(), "ElectronTail");
                    break;
                case "ElectronTail":
                    this.setCellState(c.getX(), c.getY(), "Conductor");
                    break;
                default:
                    int electronHeadNeighbours = 0;
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int x = i;
                            int y = j;
                            if (c.getX() + i < 0) {
                                x = copy.xSize - 1;
                            } else if (c.getX() + i >= this.xSize) {
                                x = 0;
                            }
                            if (c.getY() + j < 0) {
                                y = copy.ySize - 1;
                            } else if (c.getY() + j >= this.ySize) {
                                y = 0;
                            }
                            if (i == 0 && j == 0) {
                            } else {
                                if (copy.getCellState(c.getX() + x, c.getY() + y).equals("ElectronHead")) {
                                    electronHeadNeighbours++;
                                }
                            }
                        }
                    }
                    if (electronHeadNeighbours == 1 || electronHeadNeighbours == 2) {
                        this.setCellState(c.getX(), c.getY(), "ElectronHead");
                    } else {
                        this.setCellState(c.getX(), c.getY(), "Conductor");
                    }
                    break;
            }

        }
    }

    @Override
    public Iterator<Cell> iterator() {
        return new Iterator<Cell>() {

            private final Cell[] setCopy = new Cell[xSize * ySize];
            private int iterator = 0;

            {
                System.arraycopy(set, 0, setCopy, 0, xSize * ySize);
            }

            @Override
            public boolean hasNext() {
                return iterator < setCopy.length;
            }

            @Override
            public Cell next() {
                return setCopy[iterator++];
            }
        };
    }
}
