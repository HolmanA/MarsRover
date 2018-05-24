package marsrover.grid;

import java.util.Scanner;

/**
 * PlateauParser handles parsing Plateau initial values and returning a new Grid object.
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
public class PlateauParser implements GridParser {
    @Override
    public Grid createGrid(String line) {
        Scanner scanner = new Scanner(line);
        int maxX = (scanner.hasNextInt()) ? scanner.nextInt() : -1;
        int maxY = (scanner.hasNextInt()) ? scanner.nextInt() : -1;
        scanner.close();

        if (maxX >= 0 && maxY >= 0) {
            return new Plateau(maxX + 1, maxY + 1);
        }
        return null;
    }
}
