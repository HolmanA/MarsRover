package marsrover.rover;

import marsrover.grid.CoordinatesOutOfBoundsException;
import marsrover.grid.Grid;

/**
 * MarsRover class defines a concrete implementation of a Rover.
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
public class MarsRover implements Rover {
    private int x;
    private int y;
    private Heading heading;
    private Grid grid;

    /**
     * Constructor specifying an initial rover position, heading, and the Grid that this MarsRover is placed on.
     */
    public MarsRover(Grid grid, int x, int y, Heading heading) throws CoordinatesOutOfBoundsException {
        this.grid = grid;
        this.heading = heading;

        if (grid.isValidSpace(x, y)) {
            this.x = x;
            this.y = y;
        } else {
            throw new CoordinatesOutOfBoundsException(grid, x, y);
        }
    }

    /**
     * Moves the rover forward one grid space in the direction of the current heading provided the space is valid.
     */
    @Override
    public void moveForward() {
        int newX = x;
        int newY = y;
        switch (heading) {
            case NORTH:
                newY++;
                break;
            case EAST:
                newX++;
                break;
            case SOUTH:
                newY--;
                break;
            case WEST:
                newX--;
                break;
        }
        if (grid.isValidSpace(newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    @Override
    public void rotateClockwise() {
        heading = heading.getNext();
    }

    @Override
    public void rotateCounterClockwise() {
        heading = heading.getPrevious();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Heading getHeading() {
        return heading;
    }
}
