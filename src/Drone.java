/**
 * Drone class that extends AirProject class.
 * 
 * @author Xuhui Zeng
 * @version 2023.04.08
 */
public class Drone extends AirObject {
    private String brand;
    private int engineNum;

    // ----------------------------------------------------------
    /**
     * Create a new Drone object.
     * 
     * @param name
     *            name of drone
     */
    public Drone(String name) {
        super(name);
    }


    /**
     * Getter for brand
     * 
     * @return brand
     */
    public String getBrand() {
        return brand;
    }


    /**
     * Setter for brand
     * 
     * @param brand
     *            drone brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }


    /**
     * Getter for engineNum
     * 
     * @return engineNum
     */
    public int getEngineNum() {
        return engineNum;
    }


    /**
     * Setter for engineNum
     * 
     * @param engineNum
     *            engine number
     */
    public void setEngineNum(int engineNum) {
        this.engineNum = engineNum;
    }


    /**
     * Getter for object type
     * 
     * @return Drone
     */
    public String getObjectType() {
        return Drone;
    }
}
