import java.awt.Rectangle;
import student.TestCase;

/**
 * Test cases for skip list
 * 
 * @author Xuhui Zeng
 * @version 2023.04.11
 *
 */
public class SkipListTest extends TestCase {

    private SkipList<String, AirObject> list;
    private Airplane testObject1;
    private Balloon testObject2;
    private Bird testObject3;
    private Drone testObject4;

    /**
     * set up the test cases
     */
    public void setUp() {
        list = new SkipList<>();
        testObject1 = new Airplane("Apple");
        testObject1.setxOrig(10);
        testObject1.setyOrig(10);
        testObject1.setzOrig(10);
        testObject1.setxWidth(5);
        testObject1.setyWidth(5);
        testObject1.setzWidth(5);
        testObject1.setLevel(1);

        testObject2 = new Balloon("Banana");
        testObject2.setxOrig(20);
        testObject2.setyOrig(20);
        testObject2.setzOrig(20);
        testObject2.setxWidth(10);
        testObject2.setyWidth(10);
        testObject2.setzWidth(10);
        testObject2.setLevel(2);

        testObject3 = new Bird("Melon");
        testObject3.setxOrig(100);
        testObject3.setyOrig(100);
        testObject3.setzOrig(100);
        testObject3.setxWidth(50);
        testObject3.setyWidth(50);
        testObject3.setzWidth(50);
        testObject3.setLevel(5);

        testObject4 = new Drone("Orange");
        testObject4.setxOrig(200);
        testObject4.setyOrig(200);
        testObject4.setzOrig(200);
        testObject4.setxWidth(50);
        testObject4.setyWidth(50);
        testObject4.setzWidth(50);
        testObject4.setLevel(10);

    }


    /**
     * test the getter methods
     */
    public void testAirObjectGetters() {
        assertEquals("Apple", testObject1.getName());
        assertEquals(10, testObject1.getXorig());
        assertEquals(10, testObject1.getYorig());
        assertEquals(10, testObject1.getZorig());
        assertEquals(5, testObject1.getXwidth());
        assertEquals(5, testObject1.getYwidth());
        assertEquals(5, testObject1.getZwidth());
        assertEquals(1, testObject1.getLevel());
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

        list.insert(testObject1.getName(), testObject1);
        list.insert(testObject2.getName(), testObject2);
        list.insert(testObject3.getName(), testObject3);
        list.insert(testObject4.getName(), testObject4);
        assertEquals(4, list.getSize());
        AirObject removedRect = list.remove("Apple");
        assertEquals("Apple", removedRect.getName());
        assertEquals(10, removedRect.getXorig());
        assertEquals(3, list.getSize());
    }


    /**
     * test dump method
     */
    public void testDump() {

        list.insert(testObject1.getName(), testObject1);
        list.insert(testObject2.getName(), testObject2);
        list.insert(testObject3.getName(), testObject3);
        list.insert(testObject4.getName(), testObject4);
        list.dump();
        assertTrue(contains(systemOut().getHistory(),
            "(Airplane Apple 10 10 10 5 5 5 null 0 0)"));
    }


    /**
     * test find
     */
    public void testfind() {

        list.insert(testObject1.getName(), testObject1);
        list.insert(testObject2.getName(), testObject2);
        list.insert(testObject3.getName(), testObject3);
        list.insert(testObject4.getName(), testObject4);
        AirObject target = list.find("Banana");
        assertEquals("Banana", target.getName());
        assertEquals(20, target.getXorig());
    }
}
