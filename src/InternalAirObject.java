/**
 * Specific AirProject class for internal node.
 * 
 * @author Xuhui Zeng
 * @version 2023.04.08
 */
public class InternalAirObject extends AirObject {

    private AirObject left;
    private AirObject right;

    // ----------------------------------------------------------
    /**
     * Create a new base InternalAirObject object.
     * 
     * @param name
     *            name of object
     */
    public InternalAirObject(String name) {
        super(name);
    }


    // ----------------------------------------------------------
    /**
     * Create a new InternalAirObject object.
     * 
     * @param xOrig
     *            x coordinate of origin point
     * @param yOrig
     *            y coordinate of origin point
     * @param zOrig
     *            z coordinate of origin point
     * @param xWidth
     *            width on x direction
     * @param yWidth
     *            width on y direction
     * @param zWidth
     *            width on z direction
     * @param level
     *            level of object
     */
    public InternalAirObject(
        int xOrig,
        int yOrig,
        int zOrig,
        int xWidth,
        int yWidth,
        int zWidth,
        int level) {
        super(xOrig, yOrig, zOrig, xWidth, yWidth, zWidth, level);
    }


    // ----------------------------------------------------------
    /**
     * Getter for left object
     * 
     * @return left
     */
    public AirObject getLeft() {
        return left;
    }


    /**
     * Setter for left object
     * 
     * @param left
     *            left object
     */
    public void setLeft(AirObject left) {
        this.left = left;
    }


    /**
     * Getter for right object
     * 
     * @return right right object
     */
    public AirObject getRight() {
        return right;
    }


    /**
     * Setter for right object
     * 
     * @param right
     *            right object
     */
    public void setRight(AirObject right) {
        this.right = right;
    }

}
