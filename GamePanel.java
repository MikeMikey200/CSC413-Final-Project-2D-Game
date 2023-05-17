import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Custom component that is used to contain all the main information related to the game UI.
 * Load the resolution at 720x720 pixels.
 * Additionally, will pass information to the GameLogic to update and grab information from GameLogic
 * to update the user UI through function draw, and delayed by a 120-millisecond timer.
 * <p>
 * Initially will start the game at level 1.
 * */
public class GamePanel extends JPanel implements ActionListener {
    /* Modern screen resolution is pretty high so 16x16 is pretty small, so I scaled up the tile by a multiplier */
    static private final int originalTileSize = 16; // 16x16 tile
    static private final int scale = 3;
    static private final int tileSize = originalTileSize * scale; // 48x48 tile
    static private final int maxScreenCol = 15;
    static private final int maxScreenRow = 15;
    static private final int screenWidth = tileSize * maxScreenCol; // 720 pixels
    static private final int screenHeight = tileSize * maxScreenRow; // 720 pixels
    private boolean running = false;
    private Timer timer;
    private final int delay = 120;
    private final GameLogic gameLogic;
    private final GameKey gameKey;
    private int currentLevel;
    private static final int lastLevel = 4;
    private final JButton button;

    /**
     * Initialize gameKey to add in functionality of KeyListener
     *            gameLogic to add in the information and logic in regard to the levels
     *            JButton to add in a "Restart" button to allow the user to reset the current level
     * */
    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);

        gameKey = new GameKey();
        setFocusable(true);
        requestFocus();
        addKeyListener(gameKey);
        startGame();

        gameLogic = new GameLogic(maxScreenCol, maxScreenRow);
        gameLogic.startLevel(currentLevel);

        button = new JButton("Restart");
        button.addActionListener(this);
        button.setFocusable(false);
        button.setLayout(null);
        /* Set the button to the top middle of the screen */
        button.setLocation(screenWidth / 2, screenHeight / 2);

        add(button);
    }

    /**
     * Initialize the status of the game to running, timer with the delay, and the starting level to 1
     * */
    public void startGame() {
        /* Initiating the game */
        running = true;
        timer = new Timer(delay, this);
        timer.start();
        currentLevel = 1;
    }

    /**
     * painComponent object function caller for Graphics draw functionality
     * */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * While the game, update the UI according to the information from GameLogic for every block
     * to their respective color
     * */
    public void draw(Graphics g) {
        /* Draw the screen over and over whenever an action is performed through repaint() method
        * and pick a color or shape depending on the block type
        * */
        if (running) {
            for (int i = 0; i < maxScreenRow; i++) {
                for (int j = 0; j < maxScreenCol; j++) {
                    int type = gameLogic.getBlockType(i, j);
                    if (gameLogic.isSolution(i, j)) {
                        if (type == 3) {
                            g.setColor(colorPicker(type + 1));
                            g.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
                        } else if (type == 1) {
                            g.setColor(colorPicker(type));
                            g.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
                        } else {
                            g.setColor(colorPicker(3));
                            g.fillOval(i * tileSize, j * tileSize, tileSize, tileSize);
                        }
                    } else {
                        g.setColor(colorPicker(type));
                        g.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
                    }
                }
            }
        } else {
            win(g);
        }
    }

    /**
     * Pick a color depending on the block type and return that respective color object
     *
     * @param type a number represented by block type, see Block.java for information
     * */
    public Color colorPicker(int type) {
        return switch (type) {
            case 0 -> Color.BLACK;
            case 1 -> Color.BLUE;
            case 2 -> Color.WHITE;
            case 3 -> Color.RED;
            case 4 -> Color.GREEN;
            default -> null;
        };
    }

    /**
     * Check for several thing when given a movement input such as arrow UP, DOWN, LEFT, and RIGHT
     * and check for the instance of a WALL or a PUSHABLE OBJECT
     * and check for if the PLAYER will be moving out of bound
     * and check if there is more than one instance of the BLOCK
     * */
    public void checkCollision() {
        int type;
        int playerX = gameLogic.getPlayer().getX();
        int playerY = gameLogic.getPlayer().getY();
        switch (gameKey.getKey()) {
            case 'L' -> {
                if (playerX - 1 > -1) {
                    type = gameLogic.getBlockType(playerX - 1, playerY);
                    switch (type) {
                        case 0 -> gameLogic.updatePlayer(playerX, playerY, playerX - 1, playerY);
                        case 3 -> {
                            if (playerX - 2 > -1 && gameLogic.getBlockType(playerX - 2, playerY) == 0) {
                                gameLogic.pushBlock(playerX - 1, playerY, playerX - 2, playerY);
                                gameLogic.updatePlayer(playerX, playerY, playerX - 1, playerY);
                            }
                        }
                    }
                }
            }
            case 'R' -> {
                if (playerX + 1 < maxScreenCol) {
                    type = gameLogic.getBlockType(playerX + 1, playerY);
                    switch (type) {
                        case 0 -> gameLogic.updatePlayer(playerX, playerY, playerX + 1, playerY);
                        case 3 -> {
                            if (playerX + 2 < maxScreenCol && gameLogic.getBlockType(playerX + 2, playerY) == 0) {
                                gameLogic.pushBlock(playerX + 1, playerY, playerX + 2, playerY);
                                gameLogic.updatePlayer(playerX, playerY, playerX + 1, playerY);
                            }
                        }
                    }
                }
            }
            case 'U' -> {
                if (playerY - 1 > -1) {
                    type = gameLogic.getBlockType(playerX, playerY - 1);
                    switch (type) {
                        case 0 -> gameLogic.updatePlayer(playerX, playerY, playerX, playerY - 1);
                        case 3 -> {
                            if (playerY - 2 > -1 && gameLogic.getBlockType(playerX, playerY - 2) == 0) {
                                gameLogic.pushBlock(playerX, playerY - 1, playerX, playerY - 2);
                                gameLogic.updatePlayer(playerX, playerY, playerX, playerY - 1);
                            }
                        }
                    }
                }
            }
            case 'D' -> {
                if (playerY + 1 < maxScreenRow) {
                    type = gameLogic.getBlockType(playerX, playerY + 1);
                    switch (type) {
                        case 0 -> gameLogic.updatePlayer(playerX, playerY, playerX, playerY + 1);
                        case 3 -> {
                            if (playerY + 2 < maxScreenRow && gameLogic.getBlockType(playerX, playerY + 2) == 0) {
                                gameLogic.pushBlock(playerX, playerY + 1, playerX, playerY + 2);
                                gameLogic.updatePlayer(playerX, playerY, playerX, playerY + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Check if all SOLUTION are occupied by a PUSHABLE OBJECT
     * and then proceed to the next LEVEL
     * if at the last level print "You win!"
     * */
    public void checkSolution() {
        ArrayList<int[]> solutionPair = gameLogic.getSolution();

        for(int i = 0; i < solutionPair.size(); i++) {
            if (gameLogic.getBlockType(solutionPair.get(i)[0], solutionPair.get(i)[1]) != 3) {
                break;
            } else if (i == solutionPair.size() - 1) {
                if (currentLevel == lastLevel) {
                    running = false;
                } else {
                    gameLogic.startLevel(currentLevel + 1);
                    currentLevel++;
                }
            }
        }
    }

    /**
     * Write the win string on the panel
     * */
    public void win(Graphics g) {
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("You win!", (screenWidth - metrics.stringWidth("You win!")) / 2, screenHeight / 2);
    }

    /**
     * Whenever any action is performed, this method will repaint the panel, check for collisions and solution is met
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        /* Check for if button is pressed */
        if (e.getSource() == button) {
            gameLogic.startLevel(currentLevel);
        }

        if (running) {
            checkCollision();
            checkSolution();
        }
        repaint();

        /* Stop the game if the user has won */
        if (!running) {
            timer.stop();
        }
    }
}
