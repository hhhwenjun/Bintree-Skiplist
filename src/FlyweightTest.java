import student.TestCase;

/**
 * Test class for Flyweight
 * 
 * @author Xuhui
 * @version 2023.04.09
 */
public class FlyweightTest extends TestCase {

    // ~ Fields ................................................................

    private Flyweight testObject1;

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * set up test case.
     */
    public void setUp() {
        testObject1 = new Flyweight(100, 200, 300, 10, 20, 30, 5);
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
    }

}
