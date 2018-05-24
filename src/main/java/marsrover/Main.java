package marsrover;

import marsrover.application.Application;
import marsrover.application.ApplicationFactory;

/**
 * Main entry point for program execution.
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) {
        String filename = (args.length > 0) ? args[0] : null;
        ApplicationFactory factory = new ApplicationFactory();
        Application application = factory.createApplication(filename);
        application.start();
    }
}
