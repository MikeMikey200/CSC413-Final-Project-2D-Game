/**
 * Information class that contain the coordinate of the player with the methods to
 * give the coordinate and set the coordinate
 */
public class Player {
    private int x;
    private int y;

    /**
     * Initialize the player coordinate (X, Y)
     *
     * @param x Set X coordinate
     * @param y Set Y coordinate
     */
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Return X coordinate
     *
     * @return X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Return Y coordinate
     *
     * @return Y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Set X coordinate
     *
     * @param x Setting the coordinate for X
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set Y coordinate
     *
     * @param x Setting the coordinate for Y
     */
    public void setY(int y) {
        this.y = y;
    }
}
