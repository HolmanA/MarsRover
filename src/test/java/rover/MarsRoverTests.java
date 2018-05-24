package rover;

import marsrover.grid.CoordinatesOutOfBoundsException;
import marsrover.grid.Grid;
import marsrover.rover.Heading;
import marsrover.rover.MarsRover;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * MarsRoverTests
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
public class MarsRoverTests {
    private MarsRover rover;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void constructor_shouldThrowCoordinatesOutOfBoundsException_whenInvalidSpace() throws CoordinatesOutOfBoundsException {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(false);
        exception.expect(CoordinatesOutOfBoundsException.class);
        rover = new MarsRover(mockGrid, -1, 0, Heading.NORTH);
    }

    @Test
    public void constructor_shouldSetCoordinates_whenValidSpace() throws CoordinatesOutOfBoundsException {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        rover = new MarsRover(mockGrid, 5, 20, Heading.NORTH);
        Assert.assertEquals(5, rover.getX());
        Assert.assertEquals(20, rover.getY());
    }

    @Test
    public void constructor_shouldSetHeading_whenValidSpace() throws CoordinatesOutOfBoundsException {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        rover = new MarsRover(mockGrid, 4, 1, Heading.NORTH);
        rover.moveForward();
        Assert.assertEquals(Heading.NORTH, rover.getHeading());
    }

    @Test
    public void moveForward_shouldNotMove_whenInvalidSpace() throws CoordinatesOutOfBoundsException {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        rover = new MarsRover(mockGrid, 1, 1, Heading.NORTH);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(false);
        rover.moveForward();
        Assert.assertEquals(1, rover.getX());
        Assert.assertEquals(1, rover.getY());
    }

    @Test
    public void moveForward_shouldMoveNorth_whenHeadingNorthValidSpace() throws CoordinatesOutOfBoundsException {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        rover = new MarsRover(mockGrid, 1, 1, Heading.NORTH);
        rover.moveForward();
        Assert.assertEquals(1, rover.getX());
        Assert.assertEquals(2, rover.getY());
    }

    @Test
    public void moveForward_shouldMoveWest_whenHeadingWestValidSpace() throws CoordinatesOutOfBoundsException {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        rover = new MarsRover(mockGrid, 1, 1, Heading.WEST);
        rover.moveForward();
        Assert.assertEquals(0, rover.getX());
        Assert.assertEquals(1, rover.getY());
    }

    @Test
    public void moveForward_shouldMoveSouth_whenHeadingSouthValidSpace() throws CoordinatesOutOfBoundsException {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        rover = new MarsRover(mockGrid, 1, 1, Heading.SOUTH);
        rover.moveForward();
        Assert.assertEquals(1, rover.getX());
        Assert.assertEquals(0, rover.getY());
    }

    @Test
    public void moveForward_shouldMoveEast_whenHeadingEastValidSpace() throws CoordinatesOutOfBoundsException {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        rover = new MarsRover(mockGrid, 1, 1, Heading.EAST);
        rover.moveForward();
        Assert.assertEquals(2, rover.getX());
        Assert.assertEquals(1, rover.getY());
    }
}
