/**
 * Specific AirProject class for leaf node.
 * 
 * @author Xuhui Zeng
 * @version 2023.04.08
 */
public class LeafAirObject extends AirObject {

    // the container can only have 3 items by default, except special cases
    private AirObject[] container;
    private int numOfObject;

    // ----------------------------------------------------------
    /**
     * Create a new LeafAirObject object.
     * 
     * @param xOrig
     *            x coordinate of origin point
     * @param yOrig
     *            y coordinate of origin point
     * @param zOrig
     *            z coordinate of origin point
     * @param xWidth
     *            width on x direction
     * @param yWidth
     *            width on y direction
     * @param zWidth
     *            width on z direction
     * @param level
     *            level of object
     */
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


    /**
     * Bubble sort
     */
    private void sort() {
        for (int i = 0; i < numOfObject - 1; i++) {
            for (int j = 0; j < numOfObject - i - 1; j++) {
                if (container[j].compareTo(container[j + 1]) > 0) {
                    // swap
                    AirObject temp = container[j];
                    container[j] = container[j + 1];
                    container[j + 1] = temp;
                }
            }
        }
    }


    /**
     * Add an AirProject to the container
     * 
     * @param airObject
     *            instance of AirProject
     */
    public void addAirObject(AirObject airObject) {
        enlargeCapacity();
        container[numOfObject] = airObject;
        numOfObject++;
        sort();
    }


    /**
     * Remove an AirProject from the container
     * 
     * @param airObject
     *            instance of AirProject
     */
    public void removeAirObject(AirObject airObject) {
        String targetName = airObject.getName();
        int index = 0;
        for (int i = 0; i < numOfObject; i++) {
            if (targetName.equals(container[i].getName())) {
                index = i;
                break;
            }
        }
        numOfObject--;
        AirObject lastObject = container[numOfObject];
        container[index] = lastObject;
        container[numOfObject] = null;
        sort();
    }


    // ----------------------------------------------------------
    /**
     * Find an object
     * @param airObject air object
     * @return whether the object is found
     */
    public boolean findObject(AirObject airObject) {
        String targetName = airObject.getName();
        for (int i = 0; i < numOfObject; i++) {
            if (targetName.equals(container[i].getName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * Determine if the container is empty
     * 
     * @return getCurrNum() == 0
     */
    public boolean isEmpty() {
        return getCurrNum() == 0;
    }


    /**
     * Getter for the container
     * 
     * @return container
     */
    public AirObject[] getContainer() {
        return container;
    }


    /**
     * Getter for the current number of projects in the container
     * 
     * @return numOfObject
     */
    public int getCurrNum() {
        return numOfObject;
    }


    /**
     * Determine if current number of projects is under default capacity
     * 
     * @return numOfObject <= 3
     */
    public boolean underDefaultCapacity() {
        return numOfObject <= 3;
    }


    /**
     * Provate helper method to enlarge capacity of container
     */
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
