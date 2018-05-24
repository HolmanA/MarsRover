package marsrover.rover;

/**
 * Heading enumeration defines the cardinal directions of a compass.
 */
public enum Heading {
    NORTH('N'), EAST('E'), SOUTH('S'), WEST('W');

    private final char token;

    Heading(char token) {
        this.token = token;
    }

    /**
     * Returns the Heading value corresponding to the specified heading character token.
     */
    public static Heading getHeading(char token) {
        for (Heading heading : Heading.values()) {
            if (heading.token == Character.toUpperCase(token)) {
                return heading;
            }
        }
        throw new IllegalArgumentException("Illegal Header Token: " + token);
    }

    /**
     * Returns the heading 90* clockwise from the current heading.
     */
    public Heading getNext() {
        switch(this) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            default:
                return null;
        }
    }

    /**
     * Returns the heading 90* counterclockwise from the current heading.
     */
    public Heading getPrevious() {
        switch(this) {
            case NORTH:
                return WEST;
            case EAST:
                return NORTH;
            case SOUTH:
                return EAST;
            case WEST:
                return SOUTH;
            default:
                return null;
        }
    }

    public char getToken() {
        return token;
    }
}
