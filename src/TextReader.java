import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Read file and provide
 * 
 *
 */

public class TextReader {

    private BinTree tree;

    /**
     * Constructor of text reader
     */
    public TextReader() {
        tree = new BinTree();
    }


    /**
     * Read the file and output the answer
     * 
     * @param fileName
     *            The name of text file
     * @throws FileNotFoundException
     *             Throw when the file is not found
     */
    public void readFile(String fileName) throws FileNotFoundException {

        tree.clear();

        File newFile = new File(fileName);
        Scanner file = new Scanner(newFile);

        while (file.hasNextLine()) {
            String line = file.nextLine();
            line = line.trim();
            line = line.replaceAll("[\\n\\t]", " ");
            if (!line.equals("")) {
                String[] cleanStrings = formatter(line);

                try {
                    allocator(cleanStrings);
                }
                catch (Exception e) {
                    System.out.println(line);
                    System.out.println("ERROR! Unrecognized command: " + line);
                    continue;
                }
            }
        }
        file.close();
    }


    private boolean rangeHelper(int x, int y, int w, int h) {
        return x <= 1023 && y <= 1023 && x + w >= 0 && y + h >= 0;
    }


    /**
     * Method to allocate the data to corresponding operations
     * 
     * @param data
     *            The input clean data
     * @throws IllegalArgumentException
     *             If the operator does not follow by data
     */
    public void allocator(String[] data) throws IllegalArgumentException {

        // data may out of range but do not check all the time
        String operator = data[0];
        if (operator.equals("insert")) {
            int x = Integer.valueOf(data[1]);
            int y = Integer.valueOf(data[2]);

            String city = data[3];
            // already has the city w/o same name
            System.out.println(">insert " + x + " " + y + " " + city);
            if (tree.find(x, y)) {
                return;
            }
            tree.insert(x, y, city);
            
        }
        else if (operator.equals("remove")) {
            int x = Integer.valueOf(data[1]);
            int y = Integer.valueOf(data[2]);
            System.out.println(">remove " + x + " " + y);
            if (!tree.find(x, y)) {
                System.out.println(
                    "Record could not be removed. It does not exist.");
                return;
            }
            tree.remove(x, y);

        }
        else if (operator.equals("find")) {
            int x = Integer.valueOf(data[1]);
            int y = Integer.valueOf(data[2]);
            System.out.println(">find " + x + " " + y);
            if (!tree.find(x, y)) {
                System.out.println(
                    "Record could not be printed. It does not exist.");
            }
            else {
                String city = tree.findCity(x, y);

                System.out.println(city + " " + x + " " + y);
            }

        }
        else if (operator.equals("regionsearch")) {
            int x = Integer.valueOf(data[1]);
            int y = Integer.valueOf(data[2]);
            int w = Integer.valueOf(data[3]);
            int h = Integer.valueOf(data[4]);
            System.out.println(">regionsearch " + x + " " + y + " " + w + " "
                + h);
            if (!rangeHelper(x, y, w, h)) {
                System.out.println(
                    "The specified region is outside the world.");
                return;
            }
            else {
                LinkedList<Leaf> results = tree.regionSearch(x, y, w, h);
                int length = results.length();
                results.moveToStart();
                for (int i = 0; i < length; i++) {
                    System.out.println(results.getValue().getCity() + " "
                        + results.getValue().getLocX() + " " + results
                            .getValue().getLocY());
                    results.next();

                }

                System.out.println(tree.regionSearchCounter(x, y, w, h) + " "
                    + "nodes visited");
            }

        }
        // print
        else if (operator.equals("print")) {
            System.out.println(">print");
            LinkedList<Node> results = tree.preorderTraverse();
            int length = results.length();
            for (int i = 0; i < length; i++) {
                Node curr = results.getValue();
                if (curr instanceof InternalNode) {
                    System.out.println(printSpace(curr.getLevel()) + "I" + ", "
                        + curr.getX() + ", " + curr.getY() + ", " + curr
                            .getWidth() + ", " + curr.getHeight());
                }
                else if (curr instanceof FlyWeight) {
                    System.out.println(printSpace(curr.getLevel()) + "E" + ", "
                        + curr.getX() + ", " + curr.getY() + ", " + curr
                            .getWidth() + ", " + curr.getHeight());
                }
                else {
                    Leaf currNode = (Leaf)curr;
                    System.out.println(printSpace(currNode.getLevel())
                        + currNode.getCity() + ", " + currNode.getLocX() + ", "
                        + currNode.getLocY());
                }
                results.next();
            }
        }
        else {
            throw new IllegalArgumentException();
        }
    }


    private String printSpace(int spaceNum) {
        String space = "";
        for (int i = 0; i < spaceNum; i++) {
            space += " ";
            space += " ";
        }
        return space;
    }


    /**
     * Clean the data string and call cleanString method to clean 0's
     * 
     * @param line
     *            The raw line data
     * @return The cleaned string array data
     */
    public String[] formatter(String line) {
        String[] stringArray = line.split(" ");
        String strings = "";
        for (String string : stringArray) {
            string = string.trim();
            strings += string;
            strings += " ";
        }
        strings = strings.trim();
        String[] cleanStrings = strings.split("\\s+");
        return cleanStrings;
    }

}
