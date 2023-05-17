/**
 * Block object containing a number that will dictate whether it is nothing, player, wall,
 * pushable object, or a solution.
 * <p>
 * Types
 * 0: Nothing
 * 1: Player
 * 2: Wall
 * 3: Pushable object
 * 4: Solution
 * */
public class Block {

    private int type;

    /**
     * Initialize type to the given number
     *
     * @param i Set type to i
     */
    public Block(int i) {
        type = i;
    }

    /**
     * Return the type of the block
     *
     * @return The type of the block
     */
    public int getType() {
         return type;
    }

    /**
     * Set the type to the given number
     *
     * @param type Set type to type
     */
    public void setType(int type) {
        this.type = type;
    }
}
