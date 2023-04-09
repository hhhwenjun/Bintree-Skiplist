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
    private SkipList<String, AirObject> skiplist;

    /**
     * Constructor of text reader
     */
    public TextReader() {
        tree = new BinTree();
        skiplist = new SkipList<>();
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
        skiplist.clear();

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


    private boolean rangeHelper(AirObject obj) {
        return obj.getXorig() <= 1023 && obj.getYorig() <= 1023 && obj
            .getZorig() <= 1023 && obj.getXorig() + obj.getXwidth() >= 0 && obj
                .getYorig() + obj.getYwidth() >= 0 && obj.getZorig() + obj
                    .getZwidth() >= 0;

    }


    private boolean widthPositive(AirObject obj) {
        return obj.getXwidth() >= 0 && obj.getYwidth() >= 0 && obj
            .getZwidth() >= 0;
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
        if (operator.equals("add")) {
            AirObject addObj = addHelper(data);
            String objName = data[1];

            // bad boxes
            String badBox = "Bad box (" + addObj.getXorig() + " " + addObj
                .getYorig() + " " + addObj.getZorig() + " " + addObj.getXwidth()
                + " " + addObj.getYwidth() + " " + addObj.getZwidth() + ").";
            if (!rangeHelper(addObj)) {
                System.out.println(badBox
                    + " All boxes must be entirely within the world box.");
            }
            if (!widthPositive(addObj)) {
                System.out.println(badBox
                    + " All widths must be positive.");
            }

            // already has the object w/ same name
            if (skiplist.find(objName) != null) {
                System.out.println("Duplicate object names not permitted: |" + objName + "|");
                return;
            }

            tree.insert(addObj);
            skiplist.insert(data[1], addObj);
            System.out.println(objName + " has been added to the database");

        }
        else if (operator.equals("delete")) {
            String objName = data[1];

            // object not found
            AirObject removeObj = skiplist.find(objName);
            if (removeObj == null) {
                System.out.println("Object |" + objName + "| not in the database");
                return;
            }
            tree.remove(removeObj);
            System.out.println("Deleted |" + objName + "| from the database");

        }
        // find the object
        else if (operator.equals("print") && data[1].equals("object")) {

            String objName = data[1];

            // object not found
            AirObject findObj = skiplist.find(objName);
            if (findObj == null) {
                System.out.println("|" + objName + "| does not exist in the database");
                return;
            }

            LinkedList<String> airInfo = SkipList.getAirInfo(findObj);
            String info = "";
            airInfo.moveToStart();
            while(!airInfo.isAtEnd()) {
                info += airInfo.getValue();
                info += " ";
            }
            info.trim();
            System.out.println("Found: " + info);

        }
        // TODO: intersect and other print operations
        else if (operator.equals("intersect")) {
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


    private AirObject addHelper(String[] addData) {
        AirObject createObj = new AirObject(addData[2]); // set name here
        createObj.setxOrig(Integer.valueOf(addData[3]));
        createObj.setyOrig(Integer.valueOf(addData[4]));
        createObj.setzOrig(Integer.valueOf(addData[5]));
        createObj.setxWidth(Integer.valueOf(addData[6]));
        createObj.setyWidth(Integer.valueOf(addData[7]));
        createObj.setzWidth(Integer.valueOf(addData[8]));
        switch (addData[1]) {
            case "ballon":
                Balloon balloon = (Balloon)createObj;
                balloon.setType(addData[9]);
                balloon.setType(addData[10]);
                createObj = balloon;
                break;
            case "airplane":
                Airplane airplane = (Airplane)createObj;
                airplane.setCarrier(addData[9]);
                airplane.setFlightNum(Integer.valueOf(addData[10]));
                airplane.setEngineNum(Integer.valueOf(addData[11]));
                createObj = airplane;
                break;
            case "rocket":
                Rocket rocket = (Rocket)createObj;
                rocket.setAscentRate(Integer.valueOf(addData[9]));
                rocket.setTrajectory(Double.valueOf(addData[10]));
                createObj = rocket;
                break;
            case "drone":
                Drone drone = (Drone)createObj;
                drone.setBrand(addData[9]);
                drone.setEngineNum(Integer.valueOf(addData[10]));
                createObj = drone;
                break;
            case "bird":
                Bird bird = (Bird)createObj;
                bird.setType(addData[9]);
                bird.setNumber(Integer.valueOf(addData[10]));
                createObj = bird;
                break;
        }
        return createObj;
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
