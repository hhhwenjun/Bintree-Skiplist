
public class Airplane extends AirObject {
    
    private String carrier;
    private int flightNum;
    private int engineNum;

    public Airplane(String name) {
        super(name);
    }
    
    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(int flightNum) {
        this.flightNum = flightNum;
    }

    public int getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(int engineNum) {
        this.engineNum = engineNum;
    }

}
