import java.io.*;

/**
 * Information class that contain the current level information with the methods to
 * load level and solution
 */
public class Levels {
    private int[][] level;
    private int[][] solution;
    private final int col;
    private final int row;

    /**
     * Initialize the required columns and rows for the file loading process
     *
     * @param col Columns of the game UI
     * @param row Rows of the game UI
     */
    public Levels(int col, int row) {
        this.col = col;
        this.row = row;
    }

    /**
     * Loading method for level from the folder Levels by the specified num to load that .txt file
     *
     * @param num Number for the game level
     */
    public void loadLevel(int num) {
        try {
            level = new int[col][row];
            String str = ".\\src\\Levels\\level" + num + ".txt";
            File file = new File(str);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(str));
                String line = reader.readLine();
                if (line != null) {
                    String[] tokens;
                    int i = 0;
                    while (line != null) {
                        tokens = line.split(",");
                        for (int j = 0; j < tokens.length; j++) {
                            level[j][i] = Integer.parseInt(tokens[j]);
                        }
                        i++;
                        line = reader.readLine();
                    }
                }
                reader.close();
            } else {
                System.out.println("Level file doesn't exit!");
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    /**
     * Loading method for solution from the folder Solutions by the specified num to load that .txt file
     *
     * @param num Number for the game level
     */
    public void loadSolution(int num) {
        try {
            solution = new int[col][row];
            String str = ".\\src\\Solutions\\solution" + num + ".txt";
            File file = new File(str);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(str));
                String line = reader.readLine();
                if (line != null) {
                    String[] tokens;
                    int i = 0;
                    while (line != null) {
                        tokens = line.split(",");
                        for (int j = 0; j < tokens.length; j++) {
                            solution[j][i] = Integer.parseInt(tokens[j]);
                        }
                        i++;
                        line = reader.readLine();
                    }
                }
            } else {
                System.out.println("Solution file doesn't exist!");
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    /**
     * Return the level matrix
     *
     * @return 2D integer array
     */
    public int[][] getLevel() {
        return level;
    }

    /**
     * Return the solution matrix
     *
     * @return 2D integer array
     */
    public int[][] getSolution() {
        return solution;
    }
}
