package marsrover.rover;

/**
 * RoverParser
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
public interface RoverParser {
    Rover createRover(String line);
    void commandRover(Rover rover, String line);
}