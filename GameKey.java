import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KeyListener class to detect user arrow keys input
 */
public class GameKey implements KeyListener {
    private char key = 'N';

    /**
     * Return the current key character
     *
     * @return Current character key pressed
     */
    public char getKey() {
        return key;
    }

    /**
     * Do absolutely nothing
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Detect inputs of arrow keys from the user: UP, DOWN, LEFT, and RIGHT
     * Currently only support arrow keys not WASD
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> key = 'L';
            case KeyEvent.VK_RIGHT -> key = 'R';
            case KeyEvent.VK_UP -> key = 'U';
            case KeyEvent.VK_DOWN -> key = 'D';
        }
    }

    /**
     * Stop the mobility of the player by setting the key character to an unknown character
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        key = 'N';
    }
}
