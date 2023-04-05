
public class LeafAirObject extends AirObject {

    // the container can only have 3 items by default, except special cases
    private AirObject[] container;
    private int numOfObject;

    public LeafAirObject(
        int xOrig,
        int yOrig,
        int zOrig,
        int xWidth,
        int yWidth,
        int zWidth,
        int level) {
        super(xOrig, yOrig, zOrig, xWidth, yWidth, zWidth, level);
        container = new AirObject[10];
        numOfObject = 0;
    }


    public void addAirObject(AirObject airObject) {
        enlargeCapacity();
        container[numOfObject] = airObject;
        numOfObject++;
    }
    
    public AirObject[] getContainer() {
        return container;
    }


    public int getCurrNum() {
        return numOfObject;
    }


    public boolean underDefaultCapacity() {
        return numOfObject <= 3;
    }


    private void enlargeCapacity() {
        if (getCurrNum() == container.length) {
            AirObject[] newContainer = new AirObject[container.length * 2];
            for (int i = 0; i < container.length; i++) {
                newContainer[i] = container[i];
            }
            container = newContainer;
        }
    }

}