import java.io.FileNotFoundException;
import student.TestCase;

/**
 * @author Xuhui Zeng
 * @version 2023.03.26
 */
public class AirControlTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        AirControl recstore = new AirControl();
        AirControl controller = new AirControl();
    }


    /**
     * Get code coverage of the class declaration.
     * 
     * @throws FileNotFoundException
     */
    public void testRInit() throws FileNotFoundException {
        AirControl recstore = new AirControl();
        assertNotNull(recstore);
        try {
            AirControl.main(new String[] { "abc" });
        }
        catch (Exception e) {
            assertTrue(e instanceof FileNotFoundException);
        }
    }


    /**
     * Get code coverage for AirObject class
     * 
     * @throws FileNotFoundException
     */
    public void testAO() throws FileNotFoundException {
        AirControl controller = new AirControl();
        assertNotNull(controller);
        AirControl.main(new String[] { "Sample Input.txt" });
        assertTrue(contains(systemOut().getHistory(),
            "Error in rangeprint parameters: |z| is not less than |a|"));
        assertTrue(contains(systemOut().getHistory(),
            "(Airplane Air1 0 10 1 20 2 30 USAir 717 4)"));
        System.out.println("End of test Input***********************");
    }
}
