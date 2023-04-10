/**
 * Bird class that extends AirProject class.
 * 
 * @author Xuhui Zeng
 * @version 2023.04.08
 */
public class Bird extends AirObject {

    private String type;
    private int number;

    // ----------------------------------------------------------
    /**
     * Create a new Bird object.
     * 
     * @param name
     *            name of bird
     */
    public Bird(String name) {
        super(name);
    }


    // ----------------------------------------------------------
    /**
     * Getter for type
     * 
     * @return bird type
     */
    public String getType() {
        return type;
    }


    /**
     * Setter for type
     * 
     * @param type
     *            bird type
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * Getter for number
     * 
     * @return number
     */
    public int getNumber() {
        return number;
    }


    /**
     * Setter for number
     * 
     * @param number
     *            bird number
     */
    public void setNumber(int number) {
        this.number = number;
    }


    /**
     * Getter for object type
     * 
     * @return Bird
     */
    public String getObjectType() {
        return BIRD;
    }

}
