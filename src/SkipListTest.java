import java.awt.Rectangle;
import student.TestCase;

/**
 * Test cases for skip list and rectangle
 * @author Wenjun Han
 * @version 4.23.22
 *
 */
public class SkipListTest extends TestCase {
    
    private SkipList<String, RectangleInfo> list;
    private RectangleInfo rect;
    
    /**
     * set up the test cases
     */
    public void setUp() {
        list = new SkipList<>();
        rect = new RectangleInfo("arect", 10, 10, 5, 4);
    }
    
    /**
     * test the getter methods in rectangle info class
     */
    public void testRectangleGetters() {
        assertEquals("arect", rect.getName());
        assertEquals(10, rect.getX());
        assertEquals(10, rect.getY());
        assertEquals(5, rect.getWidth());
        assertEquals(4, rect.getHeight());
        assertEquals("10, 10, 5, 4", rect.printRectangleDim());
        assertEquals("arect, 10, 10, 5, 4", rect.printRectangle());
        assertTrue(rect.getRectangle() instanceof Rectangle);
    }
    
    /**
     * test the getters of skip list
     */
    public void testGetters() {
        assertEquals(-1, list.getLevel());
        assertEquals(0, list.getSize());
    }
    
    /**
     * test insert and remove of skip list
     */
    public void testInsertRemove() {
        RectangleInfo rect1 = new RectangleInfo("arect", 10, 10, 5, 4);
        RectangleInfo rect2 = new RectangleInfo("brect", 10, 10, 5, 4);
        RectangleInfo rect3 = new RectangleInfo("crect", 10, 10, 5, 4);
        RectangleInfo rect4 = new RectangleInfo("drect", 10, 3, 5, 10);
        list.insert(rect.getName(), rect);
        list.insert(rect1.getName(), rect1);
        list.insert(rect2.getName(), rect2);
        list.insert(rect3.getName(), rect3);
        list.insert(rect4.getName(), rect4);
        assertEquals(5, list.getSize());
        RectangleInfo removedRect = list.remove("drect");
        assertEquals("drect", removedRect.getName());
        assertEquals(10, removedRect.getHeight());
        assertEquals(4, list.getSize());
    }
    
    /**
     * test dump method
     */
    public void testDump() {
        RectangleInfo rect1 = new RectangleInfo("arect", 10, 10, 5, 4);
        RectangleInfo rect2 = new RectangleInfo("brect", 10, 10, 5, 4);
        RectangleInfo rect3 = new RectangleInfo("crect", 10, 10, 5, 4);
        RectangleInfo rect4 = new RectangleInfo("drect", 10, 3, 5, 10);
        list.insert(rect.getName(), rect);
        list.insert(rect1.getName(), rect1);
        list.insert(rect2.getName(), rect2);
        list.insert(rect3.getName(), rect3);
        list.insert(rect4.getName(), rect4);
        list.dump();
        assertTrue(contains(systemOut().getHistory(), "SkipList size is: 5"));
        assertTrue(contains(systemOut().getHistory(), "(drect, 10, 3, 5, 10)"));
    }
    
    /**
     * test find
     */
    public void testfind() {
        RectangleInfo rect1 = new RectangleInfo("arect", 10, 10, 5, 4);
        RectangleInfo rect2 = new RectangleInfo("brect", 10, 10, 5, 4);
        RectangleInfo rect3 = new RectangleInfo("crect", 10, 10, 5, 4);
        RectangleInfo rect4 = new RectangleInfo("drect", 10, 3, 5, 10);
        list.insert(rect.getName(), rect);
        list.insert(rect1.getName(), rect1);
        list.insert(rect2.getName(), rect2);
        list.insert(rect3.getName(), rect3);
        list.insert(rect4.getName(), rect4);
        RectangleInfo target = list.find("brect");
        assertEquals("brect", target.getName());
        assertEquals(5, target.getWidth());
    }
}
