/**
 * Flyweight class extend AirObject
 * 
 * @author Xuhui
 * @version 2023.04.08
 */
public class Flyweight extends AirObject {

    /**
     * Default constructor
     */
    public Flyweight() {
        super();
    }


    /**
     * Create a new Flyweight object.
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
    public Flyweight(
        int xOrig,
        int yOrig,
        int zOrig,
        int xWidth,
        int yWidth,
        int zWidth,
        int level) {
        super(xOrig, yOrig, zOrig, xWidth, yWidth, zWidth, level);
    }
}
