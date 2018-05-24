package marsrover.grid;

/**
 * GridParser
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
public interface GridParser {
    Grid createGrid(String line);
}
