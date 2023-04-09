import student.TestCase;

/**
 *  Test class for Balloon
 * 
 *  @author Xuhui Zeng
 *  @version 2023.04.08
 */
public class BalloonTest extends TestCase {
    
    //~ Fields ................................................................
    
    private Balloon testObject1;
    private Balloon testObject2;

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................

    /**
     * Set up test case.
     */
    public void setUp() {
        testObject1 = new Balloon("Apple");
        testObject2 = new Balloon("Banana");
        
        testObject1.setType("A");
        testObject1.setAscentRate(1);
        testObject2.setType("B");
        testObject2.setAscentRate(2);
    }
    
    /**
     * Test getter and setter
     */
    public void testAll() {
        assertEquals("A",testObject1.getType());
        assertEquals(1,testObject1.getAscentRate());
        assertEquals("Balloon",testObject1.getObjectType());
        
        assertEquals("B",testObject2.getType());
        assertEquals(2,testObject2.getAscentRate());
        assertEquals("Balloon",testObject2.getObjectType());
        
        assertTrue(testObject1.compareTo(testObject2) < 0);
    }
    
    
    
}
