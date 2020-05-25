package WireWorld;

import java.io.IOException;

//single cell in set
public class Cell {

    private int x;
    private int y;
    private StateType state;

    public Cell(int x, int y, String state) throws IOException {

        this.x = x;
        this.y = y;

        if (StateType.isStateType(state)) {
            this.state = StateType.valueOf(state);
        } else {
            throw new IOException(state + ": Not state type");
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setState(String state) {
        this.state = StateType.valueOf(state);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getState() {
        return this.state.toString();
    }
}
