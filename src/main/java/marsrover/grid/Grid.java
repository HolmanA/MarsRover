package marsrover.grid;

/**
 * Grid
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
public interface Grid {
    boolean isValidSpace(int x, int y);
    int getWidth();
    int getHeight();
}
