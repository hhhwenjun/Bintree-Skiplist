import student.TestCase;

/**
 * Test class for bintree
 * 
 * @author Xuhui
 * @version 2023.04.09
 */
public class BinTreeTest extends TestCase {

    // ~ Fields ................................................................

    private BinTree testTree;
    private AirObject testObject1;
    private AirObject testObject2;
    private AirObject testObject3;
    private AirObject testObject4;
    private Pair<AirObject, AirObject> testPair;

    private LeafAirObject testLeaf1;
    private LeafAirObject testLeaf2;

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * set up for test tree
     */
    public void setUp() {
        testTree = new BinTree();

        testObject1 = new AirObject("Apple");
        testObject1.setxOrig(10);
        testObject1.setyOrig(10);
        testObject1.setzOrig(10);
        testObject1.setxWidth(5);
        testObject1.setyWidth(5);
        testObject1.setzWidth(5);
        testObject1.setLevel(1);

        testObject2 = new AirObject("Banana");
        testObject2.setxOrig(20);
        testObject2.setyOrig(20);
        testObject2.setzOrig(20);
        testObject2.setxWidth(10);
        testObject2.setyWidth(10);
        testObject2.setzWidth(10);
        testObject2.setLevel(2);

        testObject3 = new AirObject("Melon");
        testObject3.setxOrig(100);
        testObject3.setyOrig(100);
        testObject3.setzOrig(100);
        testObject3.setxWidth(50);
        testObject3.setyWidth(50);
        testObject3.setzWidth(50);
        testObject3.setLevel(5);

        testObject4 = new AirObject("Orange");
        testObject1.setxOrig(200);
        testObject1.setyOrig(200);
        testObject1.setzOrig(200);
        testObject1.setxWidth(50);
        testObject1.setyWidth(50);
        testObject1.setzWidth(50);
        testObject1.setLevel(10);

        testPair = new Pair<AirObject, AirObject>(testObject1, testObject2);

        testLeaf1 = new LeafAirObject(0, 0, 0, 1024, 1024, 1024, 0);
        testLeaf1.addAirObject(testObject1);

    }


    /**
     * Test pair class
     */
    public void testPair() {
        assertEquals(testObject1, testPair.getLeft());
        assertEquals(testObject2, testPair.getRight());

        testPair.setLeft(testObject3);
        testPair.setRight(testObject4);

        assertEquals(testObject3, testPair.getLeft());
        assertEquals(testObject4, testPair.getRight());
    }


    /**
     * Test shouldSplit()
     */
    public void recursiveInsert() {

        assertEquals(testLeaf1, testTree.recursiveInsert(testLeaf1,
            testObject2));
        assertEquals(testLeaf1, testTree.recursiveInsert(testLeaf1,
            testObject3));
        assertEquals(testLeaf1, testTree.recursiveInsert(testLeaf1,
            testObject4));

    }


    /**
     * Test insert
     */
    public void testInsert() {
        assertTrue(testTree.isEmpty());

        testTree.insert(testObject1);
        assertFalse(testTree.isEmpty());
        testTree.insert(testObject2);

        testTree.clear();
        assertTrue(testTree.isEmpty());
    }

}
