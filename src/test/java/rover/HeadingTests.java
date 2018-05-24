package rover;

import marsrover.rover.Heading;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * HeadingTests
 *
 * @author Andrew Holman
 * @version 1.0
 * @since 1.0
 */
public class HeadingTests {
    private Heading heading;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void getHeading_shouldReturnHeading_whenProvidedLowercaseToken() {
        heading = Heading.getHeading('w');
        Assert.assertEquals(Heading.WEST, heading);
    }

    @Test
    public void getHeading_shouldReturnHeading_whenProvidedUppercaseToken() {
        heading = Heading.getHeading('N');
        Assert.assertEquals(Heading.NORTH, heading);
    }

    @Test
    public void getHeading_shouldThrowIllegalArgumentException_whenProvidedIllegalToken() {
        exception.expect(IllegalArgumentException.class);
        Heading.getHeading('Z');
    }

    @Test
    public void getNext_shouldReturnFirst_whenLast() {
        Heading[] headings = Heading.values();
        heading = headings[headings.length - 1];
        Assert.assertEquals(headings[0], heading.getNext());
    }

    @Test
    public void getNext_shouldReturnSecond_whenFirst() {
        Heading[] headings = Heading.values();
        heading = headings[0];
        Assert.assertEquals(headings[1], heading.getNext());
    }

    @Test
    public void getPrevious_shouldReturnFirst_whenSecond() {
        Heading[] headings = Heading.values();
        heading = headings[1];
        Assert.assertEquals(headings[0], heading.getPrevious());
    }

    @Test
    public void getPrevious_shouldReturnLast_whenFirst() {
        Heading[] headings = Heading.values();
        heading = headings[0];
        Assert.assertEquals(headings[headings.length - 1], heading.getPrevious());
    }
}
