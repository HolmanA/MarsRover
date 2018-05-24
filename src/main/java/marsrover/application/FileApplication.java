package marsrover.application;

import marsrover.grid.Grid;
import marsrover.grid.GridParser;
import marsrover.grid.PlateauParser;
import marsrover.rover.MarsRoverParser;
import marsrover.rover.Rover;
import marsrover.rover.RoverParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * FileApplication reads input from a file specifying initial grid and rover values as well as the rover command
 * sequence. The start method of this class executes until it reaches EOF.
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
class FileApplication implements Application {
    private BufferedReader reader;
    private GridParser gridParser;

    /**
     * Attempts to construct a FileApplication with a BufferReader reading from the specified file. Throws an
     * IOException if there is an issue opening the file.
     */
    FileApplication(String filename) throws IOException {
        reader = new BufferedReader(new FileReader(new File(filename)));
        gridParser = new PlateauParser();
    }

    /**
     * Main application execution loop
     */
    @Override
    public void start() {
        try {
            String line = reader.readLine();
            Grid grid = gridParser.createGrid(line);
            RoverParser roverParser = new MarsRoverParser(grid);
            while ((line = reader.readLine()) != null) {
                Rover rover = roverParser.createRover(line);
                if (rover != null) {
                    roverParser.commandRover(rover, reader.readLine());
                    System.out.printf("%d %d %s\n", rover.getX(), rover.getY(), rover.getHeading().getToken());
                } else {
                    System.err.println("Error: Initial rover position unreadable, skipping to next rover");
                }
            }
        } catch (IOException e) {
            System.err.println("Fatal error while reading from file");
            System.err.println("Exiting...");
            System.exit(-1);
        }
    }
}
