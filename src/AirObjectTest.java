import student.TestCase;

/**
 * Test class for AirObject
 * 
 * @author Xuhui Zeng
 * @version 2023.04.08
 */
public class AirObjectTest extends TestCase {

    // ~ Fields ................................................................

    private AirObject testObject1;
    private AirObject testObject2;
    private AirObject testObject3;
    private AirObject testObject4;

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * set up test case.
     */
    public void setUp() {
        testObject1 = new AirObject(100, 200, 300, 10, 20, 30, 5);
        testObject2 = new AirObject();
        testObject3 = new AirObject("Airplane");
        testObject4 = new AirObject("Drone");

    }


    // ----------------------------------------------------------
    /**
     * Test getter and setter
     */
    public void testConstructor() {

        assertEquals(100, testObject1.getXorig());
        assertEquals(200, testObject1.getYorig());
        assertEquals(300, testObject1.getZorig());
        assertEquals(10, testObject1.getXwidth());
        assertEquals(20, testObject1.getYwidth());
        assertEquals(30, testObject1.getZwidth());
        assertEquals(5, testObject1.getLevel());
        assertEquals(0, testObject2.getLevel());
        assertEquals("null", testObject1.getObjectType());
        assertEquals("null", testObject2.getObjectType());

        testObject1.setxOrig(150);
        assertEquals(150, testObject1.getXorig());
        testObject1.setyOrig(250);
        assertEquals(250, testObject1.getYorig());
        testObject1.setzOrig(350);
        assertEquals(350, testObject1.getZorig());
        testObject1.setxWidth(15);
        assertEquals(15, testObject1.getXwidth());
        testObject1.setyWidth(25);
        assertEquals(25, testObject1.getYwidth());
        testObject1.setzWidth(35);
        assertEquals(35, testObject1.getZwidth());
        testObject1.setLevel(6);
        assertEquals(6, testObject1.getLevel());

    }


    /**
     * Test methods for name
     */
    public void testName() {
        assertEquals("Airplane", testObject3.getName());
        assertEquals("Drone", testObject4.getName());
        assertTrue(testObject3.compareTo(testObject4) < 0);

    }

}
