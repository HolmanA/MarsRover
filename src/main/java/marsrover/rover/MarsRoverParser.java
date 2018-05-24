package marsrover.rover;

import marsrover.grid.CoordinatesOutOfBoundsException;
import marsrover.grid.Grid;

import java.util.Scanner;

/**
 * MarsRoverParser handles parsing MarsRover initial values, creating new MarsRover objects, and parsing / executing
 * commands on Rovers.
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
public class MarsRoverParser implements RoverParser {
    private Grid grid;

    public MarsRoverParser(Grid grid) {
        this.grid = grid;
    }

    /**
     * Parses the provided String for Rover initial values and returns a new Rover object instantiated with those
     * values. If the String contains invalid initial values or is of an invalid format, null is returned.
     */
    @Override
    public Rover createRover(String line) {
        Scanner scanner = new Scanner(line);
        int x = (scanner.hasNextInt()) ? scanner.nextInt() : -1;
        int y = (scanner.hasNextInt()) ? scanner.nextInt() : -1;
        String headerToken = (scanner.hasNext()) ? scanner.next() : "";
        char h = (headerToken.length() == 1) ? headerToken.charAt(0) : '*';

        try {
            Heading heading = Heading.getHeading(h);
            return new MarsRover(grid, x, y, heading);
        } catch (IllegalArgumentException | CoordinatesOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Parses the provided String for Rover command tokens, executing any valid tokens found on the provided Rover
     * object.
     */
    @Override
    public void commandRover(Rover rover, String line) {
        // Ensure line is not null before parsing
        line = (line != null) ? line : "";
        for (int i = 0; i < line.length(); i++) {
            char c = Character.toUpperCase(line.charAt(i));
            switch (c) {
                case 'M':
                    rover.moveForward();
                    break;
                case 'R':
                    rover.rotateClockwise();
                    break;
                case 'L':
                    rover.rotateCounterClockwise();
                    break;
                default:
                    System.err.printf("Skipping unknown command '%s'\n", c);
            }
        }
    }
}
