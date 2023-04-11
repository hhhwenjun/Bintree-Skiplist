import student.TestCase;

/**
 * Test case for skip node
 * 
 * @author Wenjun Han
 * @version 4.24.22
 *
 */
public class SkipNodeTest extends TestCase {

    private SkipNode<String, String> node;

    /**
     * set up cases
     */
    public void setUp() {
        node = new SkipNode<String, String>("1", "20", 3);
    }


    /**
     * test all getters in class
     */
    public void testGetters() {
        assertEquals("20", node.element());
        assertEquals("1", node.key());
        assertEquals(3, node.getLevel());
        assertEquals(4, node.forward().length);
        assertEquals("[1, 20]", node.toString());
    }
}
