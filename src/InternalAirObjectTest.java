import student.TestCase;

/**
 * Test class for InternalAirObject
 * 
 * @author Xuhui
 * @version 2023.04.09
 */
public class InternalAirObjectTest extends TestCase {

    // ~ Fields ................................................................

    private InternalAirObject testObject1;
    private InternalAirObject testObject2;
    private InternalAirObject testObject3;

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * set up test case.
     */
    public void setUp() {
        testObject1 = new InternalAirObject("Apple");
        testObject2 = new InternalAirObject("Banana");
        testObject3 = new InternalAirObject("Orange");

        testObject1.setLeft(testObject2);
        testObject1.setRight(testObject3);
    }


    /**
     * Test getter and setter
     */
    public void testAll() {
        assertEquals(testObject2, testObject1.getLeft());
        assertEquals(testObject3, testObject1.getRight());
    }

}
