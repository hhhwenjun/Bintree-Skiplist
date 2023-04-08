
public class Drone extends AirObject {
    private String brand;
    private int engineNum;

    public Drone(String name) {
        super(name);
    }
    
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(int engineNum) {
        this.engineNum = engineNum;
    }

    public String getObjectType() {
        return Drone;
    }
}
