/**
 * Air traffic control general object type interface
 * All tracked objects must have a bounding prism and a name
 * We use plain AirObject as a flyweight
 * 
 * @author Xuhui Zeng
 * @version 2023.04.08
 * 
 */

public class AirObject implements Comparable<AirObject> {

    private int xOrig;
    private int yOrig;
    private int zOrig;

    private int xWidth;
    private int yWidth;
    private int zWidth;

    private int level;

    private String name; // Name for this AirObject
    /**
     * Default null string
     */
    protected static final String AIROBJECT = "null";
    /**
     * String for Balloon
     */
    protected static final String BALLOON = "Balloon";
    /**
     * String for Rocket
     */
    protected static final String ROCKET = "Rocket";
    /**
     * String for Drone
     */
    protected static final String DRONE = "Drone";
    /**
     * String for Bird
     */
    protected static final String BIRD = "Bird";
    /**
     * String for Airplane
     */
    protected static final String AIRPLANE = "Airplane";

    /**
     * Constructor for base AirObject
     * 
     * @param name
     *            The object's name
     *
     */
    public AirObject(String name) {
        this.name = name;
    }


    /**
     * Default constructor to create an empty flyweight.
     */
    public AirObject() {
        this.level = 0; // empty flyweight
    }


    /**
     * Create a new AirObject object.
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
    public AirObject(
        int xOrig,
        int yOrig,
        int zOrig,
        int xWidth,
        int yWidth,
        int zWidth,
        int level) {

        this.level = level;
        this.xOrig = xOrig;
        this.yOrig = yOrig;
        this.zOrig = zOrig;
        this.xWidth = xWidth;
        this.yWidth = yWidth;
        this.zWidth = zWidth;
    }


    /**
     * Getter for x origin
     * 
     * @return x origin
     */
    public int getXorig() {
        return xOrig;
    }


    /**
     * Getter for x width
     * 
     * @return x width
     */
    public int getXwidth() {
        return xWidth;
    }


    /**
     * Getter for y origin
     * 
     * @return y origin
     */
    public int getYorig() {
        return yOrig;
    }


    /**
     * Getter for y width
     * 
     * @return y width
     */

    public int getYwidth() {
        return yWidth;
    }


    /**
     * Getter for z origin
     * 
     * @return z origin
     */
    public int getZorig() {
        return zOrig;
    }


    /**
     * Getter for z width
     * 
     * @return z width
     */

    public int getZwidth() {
        return zWidth;
    }


    /**
     * Getter for name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Setter for xOrig
     * 
     * @param xOrig
     *            x origin
     */
    public void setxOrig(int xOrig) {
        this.xOrig = xOrig;
    }


    /**
     * Setter for yOrig
     * 
     * @param yOrig
     *            y origin
     */
    public void setyOrig(int yOrig) {
        this.yOrig = yOrig;
    }


    /**
     * Setter for zOrig
     * 
     * @param zOrig
     *            z origin
     */
    public void setzOrig(int zOrig) {
        this.zOrig = zOrig;
    }


    /**
     * Setter for xWidth
     * 
     * @param xWidth
     *            x width
     */
    public void setxWidth(int xWidth) {
        this.xWidth = xWidth;
    }


    /**
     * Setter for yWidth
     * 
     * @param yWidth
     *            y width
     */
    public void setyWidth(int yWidth) {
        this.yWidth = yWidth;
    }


    /**
     * Setter for zWidth
     * 
     * @param zWidth
     *            z width
     */
    public void setzWidth(int zWidth) {
        this.zWidth = zWidth;
    }


    /**
     * Getter for level
     * 
     * @return level
     */
    public int getLevel() {
        return level;
    }


    /**
     * Setter for level
     * 
     * @param level
     *            level of object
     */
    public void setLevel(int level) {
        this.level = level;
    }


    /**
     * Compare against a (name) String.
     *
     * @param it
     *            The String to compare to
     * 
     * @return Standard values for compareTo
     */
    public int compareTo(AirObject it) {
        return name.compareTo(it.getName());
    }


    /**
     * Use it as a fly weight
     * 
     * @return null string
     */
    public String getObjectType() {
        return AIROBJECT;
    }
}
