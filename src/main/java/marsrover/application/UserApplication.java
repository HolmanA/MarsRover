package marsrover.application;

import marsrover.grid.Grid;
import marsrover.grid.GridParser;
import marsrover.grid.PlateauParser;
import marsrover.rover.MarsRoverParser;
import marsrover.rover.Rover;
import marsrover.rover.RoverParser;

import java.io.InputStream;
import java.util.Scanner;

/**
 * UserApplication prompts for and accepts user input specifying initial grid and rover values as well as the rover
 * command sequence. The start method of this class executes until the case-insensitive keyword "quit" is received from
 * the user.
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
class UserApplication implements Application {
    private Scanner scanner;
    private RoverParser roverParser;

    UserApplication(InputStream input) {
        scanner = new Scanner(input);
    }

    /**
     * Main application execution loop
     */
    @Override
    public void start() {
        System.out.println("Type 'quit' at any time to exit the program");

        Grid grid;
        while ((grid = promptAndGetGridDimensions()) == null) {
            System.err.println("Error: Invalid Input");
        }
        roverParser = new MarsRoverParser(grid);

        // Loop until quit command is received
        while (true) {
            Rover rover = promptAndGetNewRover();
            if (rover != null) {
                promptAndExecuteRoverCommands(rover);
            } else {
                System.err.println("Error: Invalid Input");
            }
        }
    }

    private Grid promptAndGetGridDimensions() {
        System.out.print("Enter the plateau max x and y coordinates [x y]: ");
        GridParser gridParser = new PlateauParser();
        return gridParser.createGrid(readLine());
    }

    private Rover promptAndGetNewRover() {
        System.out.print("Enter the initial rover coordinates followed by the initial heading [x y h]: ");
        return roverParser.createRover(readLine());
    }

    private void promptAndExecuteRoverCommands(Rover rover) {
        System.out.print("Enter the command sequence for the rover: ");
        roverParser.commandRover(rover, readLine());
        System.out.printf("Final rover position: %d %d %s\n", rover.getX(), rover.getY(), rover.getHeading().getToken());
    }

    /**
     * Reads in a single line from the Scanner. If the "QUIT" command was specified, the program terminates. Otherwise,
     * the line is returned.
     */
    private String readLine() {
        String line = scanner.nextLine();
        if (line.toUpperCase().equals("QUIT")) {
            scanner.close();
            System.out.println("Exiting...");
            System.exit(0);
        }
        return line;
    }
}
