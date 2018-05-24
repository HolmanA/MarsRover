package marsrover.grid;

/**
 * Plateau class defines a concrete implementation of a Grid.
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
public class Plateau implements Grid {
    private int width;
    private int height;

    public Plateau(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean isValidSpace(int x, int y) {
        boolean xInbounds = (x >= 0 && x < width);
        boolean yInbounds = (y >= 0 && y < height);
        return (xInbounds && yInbounds);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
