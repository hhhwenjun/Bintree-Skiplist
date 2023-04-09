import student.TestCase;

/**
 * Test class for Airplane
 * 
 * @author Xuhui Zeng
 * @version 2023.04.08
 */
public class AirplaneTest extends TestCase {

    // ~ Fields ................................................................

    private Airplane testObject1;
    private Airplane testObject2;

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * Set up test case.
     */
    public void setUp() {
        testObject1 = new Airplane("Apple");
        testObject2 = new Airplane("Banana");

        testObject1.setCarrier("UA");
        testObject1.setFlightNum(10);
        testObject1.setEngineNum(100);

        testObject2.setCarrier("AA");
        testObject2.setFlightNum(20);
        testObject2.setEngineNum(200);
    }


    /**
     * Test getter and setter
     */
    public void testAll() {

        assertEquals("UA", testObject1.getCarrier());
        assertEquals(10, testObject1.getFlightNum());
        assertEquals(100, testObject1.getEngineNum());
        assertEquals("Airplane", testObject1.getObjectType());

        assertEquals("AA", testObject2.getCarrier());
        assertEquals(20, testObject2.getFlightNum());
        assertEquals(200, testObject2.getEngineNum());
        assertEquals("Airplane", testObject2.getObjectType());

        assertTrue(testObject1.compareTo(testObject2) < 0);
    }

}
