package plateau;

import marsrover.grid.Grid;
import marsrover.grid.PlateauParser;
import org.junit.Assert;
import org.junit.Test;

/**
 * PlateauParserTests
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
public class PlateauParserTests {
    private PlateauParser parser;
    @Test
    public void createPlateau_shouldReturnNull_whenWidthNegative() {
        parser = new PlateauParser();
        Grid grid = parser.createGrid("-1 5");
        Assert.assertNull(grid);
    }

    @Test
    public void createPlateau_shouldReturnNull_whenHeightNegative() {
        parser = new PlateauParser();
        Grid grid = parser.createGrid("5 -1");
        Assert.assertNull(grid);
    }

    @Test
    public void createPlateau_shouldReturnNull_whenWidthNotInteger() {
        parser = new PlateauParser();
        Grid grid = parser.createGrid("c 1");
        Assert.assertNull(grid);
    }

    @Test
    public void createPlateau_shouldReturnNull_whenHeightNotInteger() {
        parser = new PlateauParser();
        Grid grid = parser.createGrid("1 c");
        Assert.assertNull(grid);
    }

    @Test
    public void createPlateau_shouldReturnPlateau_whenValidParameter() {
        parser = new PlateauParser();
        Grid grid = parser.createGrid("5 9");
        Assert.assertEquals(6, grid.getWidth());
        Assert.assertEquals(10, grid.getHeight());
    }

    @Test
    public void createPlateau_shouldReturnPlateau_whenLargeValidParameter() {
        parser = new PlateauParser();
        Grid grid = parser.createGrid("500000000 900000000");
        Assert.assertEquals(500000001, grid.getWidth());
        Assert.assertEquals(900000001, grid.getHeight());
    }

    @Test
    public void createPlateau_shouldReturnPlateau_when0Parameters() {
        parser = new PlateauParser();
        Grid grid = parser.createGrid("0 0");
        Assert.assertEquals(1, grid.getWidth());
        Assert.assertEquals(1, grid.getHeight());
    }
}
