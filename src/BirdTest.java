import student.TestCase;

/**
 * Test class for Bird
 * 
 * @author Xuhui Zeng
 * @version 2023.04.08
 */
public class BirdTest extends TestCase {

    // ~ Fields ................................................................

    private Bird testObject1;
    private Bird testObject2;

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * Set up test case.
     */
    public void setUp() {
        testObject1 = new Bird("Orange");
        testObject2 = new Bird("Banana");

        testObject1.setType("A");
        testObject1.setNumber(10);
        testObject2.setType("B");
        testObject2.setNumber(20);
    }


    /**
     * Test getter and setter
     */
    public void testAll() {
        assertEquals("A", testObject1.getType());
        assertEquals(10, testObject1.getNumber());
        assertEquals("Bird", testObject1.getObjectType());

        assertEquals("B", testObject2.getType());
        assertEquals(20, testObject2.getNumber());
        assertEquals("Bird", testObject2.getObjectType());

        assertTrue(testObject1.compareTo(testObject2) > 0);
    }

}
