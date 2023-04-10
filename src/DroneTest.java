import student.TestCase;

/**
 * Test class for Drone
 * 
 * @author Xuhui Zeng
 * @version 2023.04.08
 */
public class DroneTest extends TestCase {

    // ~ Fields ................................................................

    private Drone testObject1;
    private Drone testObject2;

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * Set up test case.
     */
    public void setUp() {
        testObject1 = new Drone("Orange");
        testObject2 = new Drone("Banana");

        testObject1.setBrand("DJI");
        testObject1.setEngineNum(100);
        testObject2.setBrand("Autel");
        testObject2.setEngineNum(200);
    }


    /**
     * Test getter and setter
     */
    public void testAll() {
        assertEquals("DJI", testObject1.getBrand());
        assertEquals(100, testObject1.getEngineNum());
        assertEquals("Drone", testObject1.getObjectType());

        assertEquals("Autel", testObject2.getBrand());
        assertEquals(200, testObject2.getEngineNum());
        assertEquals("Drone", testObject2.getObjectType());

        assertTrue(testObject1.compareTo(testObject2) > 0);
    }

}
