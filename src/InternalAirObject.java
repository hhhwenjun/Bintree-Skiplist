
public class InternalAirObject extends AirObject {
    
    private AirObject left;
    private AirObject right;

    public InternalAirObject(String name) {
        super(name);
    }
    
    public InternalAirObject(int xOrig, int yOrig, int zOrig, int xWidth, int yWidth, int zWidth, int level) {
        super(xOrig, yOrig, zOrig, xWidth, yWidth, zWidth, level);
    }

    public AirObject getLeft() {
        return left;
    }

    public void setLeft(AirObject left) {
        this.left = left;
    }

    public AirObject getRight() {
        return right;
    }

    public void setRight(AirObject right) {
        this.right = right;
    }
    
}
