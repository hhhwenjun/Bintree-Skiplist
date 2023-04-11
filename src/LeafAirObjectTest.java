import static org.junit.Assert.*;
import student.TestCase;

/**
 * Test class for LeafAirObject
 * 
 * @author Xuhui
 * @version 2023.04.08
 */
public class LeafAirObjectTest extends TestCase {

    // ~ Fields ................................................................

    private LeafAirObject testObject1;
    private AirObject testObject2;
    private AirObject testObject3;
    private AirObject testObject4;
    private AirObject testObject5;

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * set up test case.
     */
    public void setUp() {
        testObject1 = new LeafAirObject(100, 200, 300, 10, 20, 30, 5);
        testObject2 = new AirObject("Apple");
        testObject3 = new AirObject("Banana");
        testObject4 = new AirObject("Melon");
        testObject5 = new AirObject("Orange");
    }


    /**
     * Test all methods
     */
    public void testAll() {

        assertTrue(testObject1.isEmpty());
        assertEquals(0, testObject1.getCurrNum());
        assertTrue(testObject1.underDefaultCapacity());

        testObject1.addAirObject(testObject2);
        assertFalse(testObject1.isEmpty());
        assertEquals(1, testObject1.getCurrNum());
        assertTrue(testObject1.underDefaultCapacity());
        AirObject[] testArray = new AirObject[10];
        testArray[0] = testObject2;
        assertArrayEquals(testArray, testObject1.getContainer());

        testObject1.addAirObject(testObject5);
        testObject1.addAirObject(testObject4);
        testArray[1] = testObject4;
        testArray[2] = testObject5;
        assertArrayEquals(testArray, testObject1.getContainer());
        assertEquals(3, testObject1.getCurrNum());

        testObject1.removeAirObject(testObject2);
        testArray[0] = testObject4;
        testArray[1] = testObject5;
        testArray[2] = null;
        assertArrayEquals(testArray, testObject1.getContainer());
        assertEquals(2, testObject1.getCurrNum());

        testObject1.addAirObject(testObject2);
        testObject1.addAirObject(testObject3);
        testObject1.addAirObject(testObject5);
        testObject1.addAirObject(testObject5);
        testObject1.addAirObject(testObject5);
        testObject1.addAirObject(testObject5);
        testObject1.addAirObject(testObject5);
        testObject1.addAirObject(testObject5);
        assertEquals(10, testObject1.getCurrNum());
        testObject1.addAirObject(testObject5);
        assertEquals(11, testObject1.getCurrNum());

    }

}
