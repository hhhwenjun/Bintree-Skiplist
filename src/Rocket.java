/**
 * Drone class that extends AirProject class.
 * 
 * @author Xuhui Zeng
 * @version 2023.04.08
 */
public class Rocket extends AirObject {

    private int ascentRate;
    /** printed with 2 decimal places */
    private double trajectory;

    // ----------------------------------------------------------
    /**
     * Create a new Rocket object.
     * 
     * @param name
     *            name of the rocket
     */
    public Rocket(String name) {
        super(name);
    }


    // ----------------------------------------------------------
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
     *            rate of ascending
     */
    public void setAscentRate(int ascentRate) {
        this.ascentRate = ascentRate;
    }


    /**
     * Getter for trajectory
     * 
     * @return trajectory
     */
    public double getTrajectory() {
        return trajectory;
    }


    /**
     * Setter for trajectory
     * 
     * @param trajectory
     *            trajectory of rocket
     */
    public void setTrajectory(double trajectory) {
        this.trajectory = trajectory;
    }


    /**
     * Getter for object type
     * 
     * @return Rocket
     */
    public String getObjectType() {
        return ROCKET;
    }

}
