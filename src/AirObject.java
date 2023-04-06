/**
 * Air traffic control general object type interface
 * All tracked objects must have a bounding prism and a name
 * *
 * 
 * @author {Your Name Here}
 * @version {Put Something Here}
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
     * Constructor for base AirObject
     * 
     * @param name
     *            The object's name
     *
     */
    public AirObject(String name) {
        this.name = name;
    }
    
    public AirObject() {
        this.level = 0; // empty flyweight
    }
    
    public AirObject(int xOrig, int yOrig, int zOrig, int xWidth, int yWidth, int zWidth, int level) {
        
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


    public void setxOrig(int xOrig) {
        this.xOrig = xOrig;
    }


    public void setyOrig(int yOrig) {
        this.yOrig = yOrig;
    }


    public void setzOrig(int zOrig) {
        this.zOrig = zOrig;
    }


    public void setxWidth(int xWidth) {
        this.xWidth = xWidth;
    }


    public void setyWidth(int yWidth) {
        this.yWidth = yWidth;
    }


    public void setzWidth(int zWidth) {
        this.zWidth = zWidth;
    }
    
    
    public int getLevel() {
        return level;
    }


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
}
