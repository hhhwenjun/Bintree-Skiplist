/**
 * Balloon class that extends AirProject class.
 * 
 * @author Xuhui Zeng
 * @version 2023.04.08
 */
public class Balloon extends AirObject {

    private String type;
    private int ascentRate;

    // ----------------------------------------------------------
    /**
     * Create a new Balloon object.
     * 
     * @param name
     *            name of the balloon
     */
    public Balloon(String name) {
        super(name);
    }


    /**
     * Getter for type
     * 
     * @return type
     */
    public String getType() {
        return type;
    }


    /**
     * Setter for type
     * 
     * @param type
     *            balloon type
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * Getter for ascentRate
     * 
     * @return ascentRate
     */
    public int getAscentRate() {
        return ascentRate;
    }


    /**
     * Setter for ascentRate
     * 
     * @param ascentRate
     *            the rate of ascending
     */
    public void setAscentRate(int ascentRate) {
        this.ascentRate = ascentRate;
    }


    /**
     * Getter for object type
     * 
     * @return Balloon
     */
    public String getObjectType() {
        return BALLOON;
    }

}
