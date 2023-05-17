import java.util.ArrayList;

/**
 * Information class that contains coordinates and types of the player, grid, solution, and levels
 */
public class GameLogic {
    private Player player;
    private final Block[][] grid;
    private int[][] solution;
    private final ArrayList<int[]> solutionPair;
    private final Levels levels;
    private final int row;
    private final int col;

    /**
     * Initializing levels to contain information of the current level
     *              solutionPair to contain the paired coordinate solution (X, Y)
     *              grid to convert levels to Block matrix
     *
     * @param row Rows of the game UI
     * @param col Columns of the game UI
     * */
    public GameLogic(int row, int col) {
        this.row = row;
        this.col = col;
        levels = new Levels(row, col);
        solutionPair = new ArrayList<>();
        grid = new Block[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                grid[i][j] = new Block(0);
            }
        }
    }

    /**
     * Return the block type within the given coordinate in the grid
     *
     * @param row Rows of the game UI
     * @param col Columns of the game UI
     *
     * @return Block object type
     * */
    public int getBlockType(int row, int col) {
        /* Return the type of the BLOCK object see Block class for info on the types */
        return grid[row][col].getType();
    }

    /**
     * Return the Player object
     *
     * @return Player object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Update the PLAYER to the new instance in the grid with newRow and newCol (X, Y)
     *
     * @param row Old row
     * @param col Old column
     * @param newRow New row
     * @param newCol New column
     */
    public void updatePlayer(int row, int col, int newRow, int newCol) {
        grid[row][col].setType(0);
        grid[newRow][newCol].setType(1);
        player.setX(newRow);
        player.setY(newCol);
    }

    /**
     * Update the PUSHABLE BLOCK to the new instance in the grid with newRow and newCol (X, Y)
     *
     * @param row Old row
     * @param col Old column
     * @param newRow New row
     * @param newCol New column
     */
    public void pushBlock(int row, int col, int newRow, int newCol) {
        grid[row][col].setType(0);
        grid[newRow][newCol].setType(3);
    }

    /**
     * Check if the given coordinate is a solution
     *
     * @param row Coordinate X on the grid
     * @param col Coordinate Y on the grid
     * @return If it is a solution true, otherwise false
     */
    public boolean isSolution(int row, int col) {
        return solution[row][col] == 1;
    }

    /**
     * Return solutionPair matrix
     *
     * @return ArrayList<int[]> solution matrix
     */
    public ArrayList<int[]> getSolution() {
        return solutionPair;
    }

    /**
     * Start the level with the given number to choose which level and solution to load with and populate the grid,
     * solutionPair, and the player location
     *
     * @param num Level number
     * */
    public void startLevel(int num) {
        solutionPair.clear();

        levels.loadLevel(num);
        levels.loadSolution(num);

        solution = levels.getSolution();

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                grid[i][j].setType(levels.getLevel()[i][j]);
                if (solution[i][j] == 1) {
                    solutionPair.add(new int[]{i, j});
                }
                if (grid[i][j].getType() == 1) {
                    player = new Player(i,j);
                }
            }
        }
    }
}
