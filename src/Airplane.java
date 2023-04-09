/**
 * Airplane class that extends AirProject class.
 * 
 * @author Xuhui Zeng
 * @version 2023.04.08
 */
public class Airplane extends AirObject {

    private String carrier;
    private int flightNum;
    private int engineNum;

    /**
     * Create a new Airplane object.
     * 
     * @param name
     *            Name of the airplane
     */
    public Airplane(String name) {
        super(name);
    }


    /**
     * Getter for carrier info.
     * 
     * @return carrier
     */
    public String getCarrier() {
        return carrier;
    }


    /**
     * Setter for carrier info.
     * 
     * @param carrier
     *            name of carrier
     */
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }


    /**
     * Getter for flightNum
     * 
     * @return flightNum
     */
    public int getFlightNum() {
        return flightNum;
    }


    /**
     * Setter for flightNum
     * 
     * @param flightNum
     *            flight number
     */
    public void setFlightNum(int flightNum) {
        this.flightNum = flightNum;
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
     * Getter for object type.
     * 
     * @return Airplane
     */
    public String getObjectType() {
        return AIRPLANE;
    }

}
