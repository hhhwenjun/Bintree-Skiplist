
public class Rocket extends AirObject {
    
    private int ascentRate;
    /** printed with 2 decimal places*/
    private double trajectory;

    public Rocket(String name) {
        super(name);
    }

    public int getAscentRate() {
        return ascentRate;
    }

    public void setAscentRate(int ascentRate) {
        this.ascentRate = ascentRate;
    }

    public double getTrajectory() {
        return trajectory;
    }

    public void setTrajectory(double trajectory) {
        this.trajectory = trajectory;
    }
    
    public String getObjectType() {
        return Rocket;
    }
    
}
