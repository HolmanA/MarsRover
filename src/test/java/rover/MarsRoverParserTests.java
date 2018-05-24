package rover;

import marsrover.grid.Grid;
import marsrover.rover.MarsRoverParser;
import marsrover.rover.Rover;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * MarsRoverParserTests
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
public class MarsRoverParserTests {
    private MarsRoverParser parser;
    @Test
    public void createRover_shouldReturnNull_whenHeadingInvalid() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);

        parser = new MarsRoverParser(mockGrid);
        Rover rover = parser.createRover("2 3 z");

        Assert.assertNull(rover);
    }

    @Test
    public void createRover_shouldReturnNull_whenHeadingTooLong() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);

        parser = new MarsRoverParser(mockGrid);
        Rover rover = parser.createRover("2 3 NORTH");

        Assert.assertNull(rover);
    }

    @Test
    public void createRover_shouldReturnNull_whenLineEmpty() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);

        parser = new MarsRoverParser(mockGrid);
        Rover rover = parser.createRover("");

        Assert.assertNull(rover);
    }

    @Test
    public void createRover_shouldReturnNull_whenLineTooShort() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);

        parser = new MarsRoverParser(mockGrid);
        Rover rover = parser.createRover("1 2");

        Assert.assertNull(rover);
    }

    @Test
    public void createRover_shouldReturnNull_whenLineTooLong() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);

        parser = new MarsRoverParser(mockGrid);
        Rover rover = parser.createRover("1 2 N extra input");

        Assert.assertNotNull(rover);
    }

    @Test
    public void createRover_shouldReturnNull_whenCharsInWidth() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);

        parser = new MarsRoverParser(mockGrid);
        Rover rover = parser.createRover("1b 5 N");

        Assert.assertNull(rover);
    }

    @Test
    public void createRover_shouldReturnNull_whenCharsInHeight() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);

        parser = new MarsRoverParser(mockGrid);
        Rover rover = parser.createRover("5 1b N");

        Assert.assertNull(rover);
    }

    @Test
    public void createRover_shouldReturnRover_whenValidInput() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);

        parser = new MarsRoverParser(mockGrid);
        Rover rover = parser.createRover("1 2 N");

        Assert.assertNotNull(rover);
    }

    @Test
    public void commandRover_shouldNotMove_whenLineNull() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        Rover mockRover = mock(Rover.class);

        parser = new MarsRoverParser(mockGrid);
        parser.commandRover(mockRover, null);

        verify(mockRover, never()).moveForward();
        verify(mockRover, never()).rotateClockwise();
        verify(mockRover, never()).rotateCounterClockwise();
    }

    @Test
    public void commandRover_shouldNotMove_whenLineEmpty() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        Rover mockRover = mock(Rover.class);

        parser = new MarsRoverParser(mockGrid);
        parser.commandRover(mockRover, "");

        verify(mockRover, never()).moveForward();
        verify(mockRover, never()).rotateClockwise();
        verify(mockRover, never()).rotateCounterClockwise();
    }

    @Test
    public void commandRover_shouldNotMove_whenNoValidCommands() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        Rover mockRover = mock(Rover.class);

        parser = new MarsRoverParser(mockGrid);
        parser.commandRover(mockRover, "dftjh");

        verify(mockRover, never()).moveForward();
        verify(mockRover, never()).rotateClockwise();
        verify(mockRover, never()).rotateCounterClockwise();
    }

    @Test
    public void commandRover_shouldMoveForward_whenValidUpperCaseMCommands() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        Rover mockRover = mock(Rover.class);

        parser = new MarsRoverParser(mockGrid);
        parser.commandRover(mockRover, "MMMMMMM");

        verify(mockRover, times(7)).moveForward();
        verify(mockRover, never()).rotateClockwise();
        verify(mockRover, never()).rotateCounterClockwise();
    }

    @Test
    public void commandRover_shouldRotateClockwise_whenValidUpperCaseRCommands() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        Rover mockRover = mock(Rover.class);

        parser = new MarsRoverParser(mockGrid);
        parser.commandRover(mockRover, "RRRRRRR");

        verify(mockRover, never()).moveForward();
        verify(mockRover, times(7)).rotateClockwise();
        verify(mockRover, never()).rotateCounterClockwise();
    }

    @Test
    public void commandRover_shouldRotateCounterclockwise_whenValidUpperCaseLCommands() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        Rover mockRover = mock(Rover.class);

        parser = new MarsRoverParser(mockGrid);
        parser.commandRover(mockRover, "LLLLLLL");

        verify(mockRover, never()).moveForward();
        verify(mockRover, never()).rotateClockwise();
        verify(mockRover, times(7)).rotateCounterClockwise();
    }

    @Test
    public void commandRover_shouldMoveForward_whenValidLowerCaseMCommands() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        Rover mockRover = mock(Rover.class);

        parser = new MarsRoverParser(mockGrid);
        parser.commandRover(mockRover, "mmmmmmm");

        verify(mockRover, times(7)).moveForward();
        verify(mockRover, never()).rotateClockwise();
        verify(mockRover, never()).rotateCounterClockwise();
    }

    @Test
    public void commandRover_shouldRotateClockwise_whenValidLowerCaseRCommands() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        Rover mockRover = mock(Rover.class);

        parser = new MarsRoverParser(mockGrid);
        parser.commandRover(mockRover, "rrrrrrr");

        verify(mockRover, never()).moveForward();
        verify(mockRover, times(7)).rotateClockwise();
        verify(mockRover, never()).rotateCounterClockwise();
    }

    @Test
    public void commandRover_shouldRotateCounterclockwise_whenValidLowerCaseLCommands() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        Rover mockRover = mock(Rover.class);

        parser = new MarsRoverParser(mockGrid);
        parser.commandRover(mockRover, "lllllll");

        verify(mockRover, never()).moveForward();
        verify(mockRover, never()).rotateClockwise();
        verify(mockRover, times(7)).rotateCounterClockwise();
    }

    @Test
    public void commandRover_shouldMoveForward_whenMCommandAndInvalidCommands() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        Rover mockRover = mock(Rover.class);

        parser = new MarsRoverParser(mockGrid);
        parser.commandRover(mockRover, "MdfMtM");

        verify(mockRover, times(3)).moveForward();
        verify(mockRover, never()).rotateClockwise();
        verify(mockRover, never()).rotateCounterClockwise();
    }

    @Test
    public void commandRover_shouldRotateClockwise_whenRCommandAndInvalidCommands() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        Rover mockRover = mock(Rover.class);

        parser = new MarsRoverParser(mockGrid);
        parser.commandRover(mockRover, "dRjRfjRf");

        verify(mockRover, never()).moveForward();
        verify(mockRover, times(3)).rotateClockwise();
        verify(mockRover, never()).rotateCounterClockwise();
    }

    @Test
    public void commandRover_shouldRotateCounterclockwise_whenLCommandAndInvalidCommands() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        Rover mockRover = mock(Rover.class);

        parser = new MarsRoverParser(mockGrid);
        parser.commandRover(mockRover, "dLfLfLjLj");

        verify(mockRover, never()).moveForward();
        verify(mockRover, never()).rotateClockwise();
        verify(mockRover, times(4)).rotateCounterClockwise();
    }

    @Test
    public void commandRover_shouldMove_whenValidCombinationOfCommands() {
        Grid mockGrid = mock(Grid.class);
        when(mockGrid.isValidSpace(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        Rover mockRover = mock(Rover.class);

        parser = new MarsRoverParser(mockGrid);
        parser.commandRover(mockRover, "MmRLrllRm");

        verify(mockRover, times(3)).moveForward();
        verify(mockRover, times(3)).rotateClockwise();
        verify(mockRover, times(3)).rotateCounterClockwise();
    }
}
