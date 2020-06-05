package WireWorld;

import java.io.*;
import java.util.*;

public class CellSet implements Iterable<Cell> {

    //set of all cells on the board
    private Cell[] set;
    //board size
    private int xSize;
    private int ySize;

    //initializes new set (size x, y) with empty cells only
    public void initCellSet(int x, int y) {
        this.xSize = x;
        this.ySize = y;
        this.set = new Cell[x * y];

        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                try {
                    this.set[i + j * x] = new Cell(i, j, "Empty");
                } catch (IOException e) {
                    //there is state "Empty" among possible cell states
                }
            }
        }
    }

    //get board size
    public int getXSize() {
        return this.xSize;
    }

    public int getYSize() {
        return this.ySize;
    }

    //get string representation of cell state (placed on (x, y) position)
    public String getCellState(int x, int y) {
        return set[x + y * this.xSize].getState();
    }

    //set state of cell placed on (x, y) position
    public void setCellState(int x, int y, String state) {

        set[x + y * this.xSize].setState(state);
    }

    //create cell set based on information in given file
    public void createCellSetFromFile(String fileName) throws Exception {

        try {
            //Scanner for reading file and LineNumberReader for defining wrong file format
            Scanner readFile = new Scanner(new File(fileName));
            LineNumberReader lineNumber = new LineNumberReader(new FileReader(fileName));

            //first line (or first two lines) of file should contain board size
            if (readFile.hasNextInt()) {
                this.xSize = readFile.nextInt();
            } else {
                throw new Exception("Wrong file format at line " + (lineNumber.getLineNumber() + 1));
            }

            if (readFile.hasNextInt()) {
                this.ySize = readFile.nextInt();
            } else {
                throw new Exception("Wrong file format at line " + (lineNumber.getLineNumber() + 1));
            }

            readFile.nextLine();

            this.initCellSet(this.xSize, this.ySize);

            while (readFile.hasNextLine()) {

                //each line should contain information: <state_type>: <x_pos>, <y_pos>
                String[] words = readFile.nextLine().split("[,: ]+");
                lineNumber.readLine();

                if (StateType.isStateType(words[0])){
                    if (words.length != 3){
                        throw new Exception("Wrong file format at line " + (lineNumber.getLineNumber() + 1));
                    }
                }
                if (StructureType.isStructureType(words[0])){
                    if (words.length != 4 && words.length != 3){
                        throw new Exception("Wrong file format at line " + (lineNumber.getLineNumber() + 1));
                    }
                }

                int x = Integer.parseInt(words[1]);
                int y = Integer.parseInt(words[2]);
                int z = 0;
                if(words.length == 4){
                    z = Integer.parseInt(words[3]);
                    if (z < 0 || z > 3){
                        throw new Exception("Wrong file format at line " + (lineNumber.getLineNumber() + 1));
                    }
                }
                int[] tab;

                if (x >= this.xSize) {
                    throw new Exception("Wrong file format at line " + (lineNumber.getLineNumber() + 1));
                }
                if (y >= this.ySize) {
                    throw new Exception("Wrong file format at line " + (lineNumber.getLineNumber() + 1));
                }

                if (StateType.isStateType(words[0]) || StructureType.isStructureType(words[0])) {
                     if (StateType.isStateType(words[0])) {
                        this.setCellState(x, y, words[0]);
                     }
                     if (StructureType.isStructureType(words[0])) {

                        tab = StructureType.GetSetupStructureInCellSet(x, y, z, words[0]);

                        for (int i = 0; i < tab.length; i=i+3 ){
                            if (tab[i] >= this.xSize) {
                                throw new Exception("Wrong file format at line " + (lineNumber.getLineNumber() + 1));
                            }
                            if (tab[i+1] >= this.ySize) {
                                throw new Exception("Wrong file format at line " + (lineNumber.getLineNumber() + 1));
                            }
                            this.setCellState(tab[i], tab[i+1], StateType.getStringState(tab[i+2]));
                        }
                    }
                } else {
                    throw new Exception("Wrong file format at line " + (lineNumber.getLineNumber() + 1));
                }
            }

        } catch (FileNotFoundException | NumberFormatException e1) {
            throw e1;
        } catch (Exception e2) {
            throw e2;
        }
    }

    public void saveCellSetToFile(String fileName) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {

            //if file exists, clean it
            writer.flush();
            //first line contains board size
            writer.write(this.getXSize() + " " + this.getYSize() + "\n");

            //each line contains information: <state_type>: <x_pos>, <y_pos>
            for (Cell c : this.set) {
                if (!c.getState().equals("Empty")) {
                    writer.write(c.getState() + ": " + c.getX() + ", " + c.getY() + "\n");
                } else {
                }
            }
        }
    }

    //creates new generation (Silverman rules, Moore's neighborhood)
    public void generateNext() {

        //set's copy to check neighbors's states
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
                    //empty cell remains empty
                    break;
                case "ElectronHead":
                    //electron head becomes electron tail
                    this.setCellState(c.getX(), c.getY(), "ElectronTail");
                    break;
                case "ElectronTail":
                    //electron tail becomes conductor
                    this.setCellState(c.getX(), c.getY(), "Conductor");
                    break;
                default:
                    //cell becomes electron head when exactly 1 or 2 of it's neighbor's are electron heads
                    int electronHeadNeighbours = 0;
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int x = i;
                            int y = j;
                            //connecting board'sends
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
                        //in any other case cell becomes conductor
                        this.setCellState(c.getX(), c.getY(), "Conductor");
                    }
                    break;
            }

        }
    }

    //to iterate over cells in cellset
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
