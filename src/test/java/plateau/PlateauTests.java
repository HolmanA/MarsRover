package plateau;

import marsrover.grid.Plateau;
import org.junit.Assert;
import org.junit.Test;

/**
 * PlateauTests
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
public class PlateauTests {
    private Plateau plateau;
    @Test
    public void isValidSpace_shouldBeValid_whenInBounds() {
        plateau = new Plateau(5, 5);
        Assert.assertTrue(plateau.isValidSpace(2, 3));
    }

    @Test
    public void isValidSpace_shouldBeValid_whenAtUpperBound() {
        plateau = new Plateau(5, 5);
        Assert.assertTrue(plateau.isValidSpace(2, plateau.getHeight() - 1));
    }

    @Test
    public void isValidSpace_shouldBeValid_whenAtLowerBound() {
        plateau = new Plateau(5, 5);
        Assert.assertTrue(plateau.isValidSpace(3, 0));
    }

    @Test
    public void isValidSpace_shouldBeValid_whenAtRightBound() {
        plateau = new Plateau(5, 5);
        Assert.assertTrue(plateau.isValidSpace(plateau.getWidth() - 1, 2));
    }

    @Test
    public void isValidSpace_shouldBeValid_whenAtLeftBound() {
        plateau = new Plateau(5, 5);
        Assert.assertTrue(plateau.isValidSpace(0, 2));
    }

    @Test
    public void isValidSpace_shouldBeValid_whenInBottomLeftCorner() {
        plateau = new Plateau(5, 5);
        Assert.assertTrue(plateau.isValidSpace(0, 0));
    }

    @Test
    public void isValidSpace_shouldBeInvalid_whenXNegative() {
        plateau = new Plateau(5, 5);
        Assert.assertFalse(plateau.isValidSpace(-1, 2));
    }

    @Test
    public void isValidSpace_shouldBeInvalid_whenYNegative() {
        plateau = new Plateau(5, 5);
        Assert.assertFalse(plateau.isValidSpace(2, -1));
    }

    @Test
    public void isValidSpace_shouldBeInvalid_whenXAtWidth() {
        plateau = new Plateau(5, 5);
        Assert.assertFalse(plateau.isValidSpace(2, plateau.getWidth()));
    }

    @Test
    public void isValidSpace_shouldBeInvalid_whenYAtHeight() {
        plateau = new Plateau(5, 5);
        Assert.assertFalse(plateau.isValidSpace(plateau.getHeight(), 2));
    }

    @Test
    public void isValidSpace_shouldBeInvalid_whenXPastWidth() {
        plateau = new Plateau(5, 5);
        Assert.assertFalse(plateau.isValidSpace(2, plateau.getWidth() + 1));
    }

    @Test
    public void isValidSpace_shouldBeInvalid_whenYPastHeight() {
        plateau = new Plateau(5, 5);
        Assert.assertFalse(plateau.isValidSpace(plateau.getHeight() + 1, 2));
    }
}
