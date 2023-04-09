import student.TestCase;

/**
 * Test class for Rocket
 * 
 * @author Xuhui Zeng
 * @version 2023.04.08
 */
public class RocketTest extends TestCase {

    // ~ Fields ................................................................

    private Rocket testObject1;
    private Rocket testObject2;

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * Set up test case.
     */
    public void setUp() {
        testObject1 = new Rocket("Orange");
        testObject2 = new Rocket("Banana");

        testObject1.setAscentRate(10);
        testObject1.setTrajectory(10.24);
        testObject2.setAscentRate(20);
        testObject2.setTrajectory(20.48);
    }


    /**
     * Test getter and setter
     */
    public void testAll() {
        assertEquals(10, testObject1.getAscentRate());
        assertEquals(10.24, testObject1.getTrajectory(), 0.01);
        assertEquals("Rocket", testObject1.getObjectType());

        assertEquals(20, testObject2.getAscentRate());
        assertEquals(20.48, testObject2.getTrajectory(), 0.01);
        assertEquals("Rocket", testObject2.getObjectType());

        assertTrue(testObject1.compareTo(testObject2) > 0);
    }

}
