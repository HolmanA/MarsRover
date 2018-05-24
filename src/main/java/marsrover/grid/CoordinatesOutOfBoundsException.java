package marsrover.grid;

/**
 * CoordinatesOutOfBoundsException is thrown when initial rover coordinates are outside of the grid bounds.
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
public class CoordinatesOutOfBoundsException extends Exception {
    private Grid grid;
    private int x;
    private int y;

    public CoordinatesOutOfBoundsException(Grid grid, int x, int y) {
        this.grid = grid;
        this.x = x;
        this.y = y;
    }

    public int getGridWidth() {
        return grid.getWidth();
    }

    public int getGridHeight() {
        return grid.getHeight();
    }

    public int getRequestedX() {
        return x;
    }

    public int getRequestedY() {
        return y;
    }
}
