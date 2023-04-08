
public class Balloon extends AirObject {

    private String type;
    private int ascentRate;

    public Balloon(String name) {
        super(name);
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAscentRate() {
        return ascentRate;
    }

    public void setAscentRate(int ascentRate) {
        this.ascentRate = ascentRate;
    }
    
    public String getObjectType() {
        return Balloon;
    }

}
