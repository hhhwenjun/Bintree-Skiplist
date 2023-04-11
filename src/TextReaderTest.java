import student.TestCase;

public class TextReaderTest extends TestCase {
    
    private TextReader reader;
    
    public void setUp() {
        reader = new TextReader();
    }
    
    public void testReadException() {
        try {
            reader.readFile("SampleInput3.txt");
        }
        catch (Exception e) {
            assertTrue(contains(systemOut().getHistory(),
                "ERROR! Unrecognized command: 8843 add balloon balloon B1 10 11 11 21 12 31 hot_air 15 16"));
        }
        assertTrue(contains(systemOut().getHistory(),
            "Bad box (0 -10 1 20 2 30). All boxes must be entirely within the world box."));
        assertTrue(contains(systemOut().getHistory(),
            "Bad box (0 10 1 20000 2 30). All boxes must be entirely within the world box."));
        assertTrue(contains(systemOut().getHistory(),
            "Bad box (0 10 1 -200 2 30). All widths must be positive."));
    }
    
    public void testRemove() {
        try {
            reader.readFile("Sample Input2.txt");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(contains(systemOut().getHistory(),
            "(Airplane Air2 100 1010 101 924 2 900 Delta 17 2)"));
        assertTrue(contains(systemOut().getHistory(),
            "pterodactyl has been added to the database"));
        assertTrue(contains(systemOut().getHistory(),
            "|MVPwatch| does not exist in the database"));
        assertTrue(contains(systemOut().getHistory(),
            "Deleted |pterodactyl| from the database"));
        assertTrue(contains(systemOut().getHistory(),
            "Deleted |Air1| from the database"));
        assertTrue(contains(systemOut().getHistory(),
            "Deleted |Air2| from the database"));
        assertTrue(contains(systemOut().getHistory(),
            "E"));
        assertTrue(contains(systemOut().getHistory(),
            "1 nodes were visited in the bintree"));
    }
}
