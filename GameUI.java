import javax.swing.*;

/**
 * GUI for the 2D block pushing puzzle application.
 * Allows user to move using arrow keys: UP, DOWN, LEFT, and RIGHT
 * */
public class GameUI extends JFrame {

    /**
     * Initialize the UI with title "2D Puzzle Game" and all the components
     * such as GamePanel object
     * */
    public GameUI() {
        super("2D Puzzle Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);

        pack();

        /* Set game at the middle of the screen*/
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
