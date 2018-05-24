package marsrover.rover;

/**
 * Rover
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
public interface Rover {
    void moveForward();
    void rotateClockwise();
    void rotateCounterClockwise();
    int getX();
    int getY();
    Heading getHeading();
}