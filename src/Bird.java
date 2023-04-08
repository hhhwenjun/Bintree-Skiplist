
public class Bird extends AirObject {  

    private String type;
    private int number;

    public Bird(String name) {
        super(name);
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public String getObjectType() {
        return Bird;
    }

}
