package marsrover.application;

import java.io.IOException;
import java.io.InputStream;

/**
 * ApplicationFactory provides a Factory Method and various direct constructors used to create Application objects.
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
public class ApplicationFactory {
    /**
     * Factory method returning either a FileApplication or UserApplication based on the existence of the
     * specified file.
     */
    public Application createApplication(String filename) {
        if (filename != null) {
            try {
                return createFileApplication(filename);
            } catch (IOException e) {
                System.err.printf("Unable to load file '%s', defaulting to user input\n", filename);
                return createUserApplication(System.in);
            }
        } else {
            return createUserApplication(System.in);
        }
    }

    public Application createFileApplication(String filename) throws IOException {
        return new FileApplication(filename);
    }

    public Application createUserApplication(InputStream inputStream) {
        return new UserApplication(inputStream);
    }
}
