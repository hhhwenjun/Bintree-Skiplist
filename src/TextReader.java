import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Read file and provide
 * 
 * @author Xuhui Zeng
 * @version 2023.04.08
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
        return obj.getXorig() <= 1024 && obj.getYorig() <= 1024 && obj
            .getZorig() <= 1024 && obj.getXorig() + obj.getXwidth() >= 0 && obj
                .getYorig() + obj.getYwidth() >= 0 && obj.getZorig() + obj
                    .getZwidth() >= 0 && obj.getXorig() + obj
                        .getXwidth() <= 1024 && obj.getYorig() + obj
                            .getYwidth() <= 1024 && obj.getZorig() + obj
                                .getZwidth() <= 1024;

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
            String objName = data[2];

            // bad boxes
            String badBox = "Bad box (" + addObj.getXorig() + " " + addObj
                .getYorig() + " " + addObj.getZorig() + " " + addObj.getXwidth()
                + " " + addObj.getYwidth() + " " + addObj.getZwidth() + ").";
            if (!widthPositive(addObj)) {
                System.out.println(badBox + " All widths must be positive.");
                return;
            }

            if (!rangeHelper(addObj)) {
                System.out.println(badBox
                    + " All boxes must be entirely within the world box.");
                return;
            }

            // already has the object w/ same name
            if (skiplist.find(objName) != null) {
                System.out.println("Duplicate object names not permitted: |"
                    + objName + "|");
                return;
            }

            tree.insert(addObj);
            skiplist.insert(objName, addObj);
            System.out.println(objName + " has been added to the database");

        }
        else if (operator.equals("delete")) {
            String objName = data[1];

            // object not found
            AirObject removeObj = skiplist.find(objName);
            if (removeObj == null) {
                System.out.println("Object |" + objName
                    + "| not in the database");
                return;
            }
            tree.remove(removeObj);
            skiplist.remove(objName);
            System.out.println("Deleted |" + objName + "| from the database");

        }
        // find the object
        else if (operator.equals("print") && data[1].equals("object")) {

            String objName = data[2];

            // object not found
            AirObject findObj = skiplist.find(objName);
            if (findObj == null) {
                System.out.println("|" + objName
                    + "| does not exist in the database");
                return;
            }

            LinkedList<String> airInfo = SkipList.getAirInfo(findObj);

            System.out.println("Found: " + getObjInfo(airInfo));

        }
        else if (operator.equals("intersect")) {
            int x = Integer.valueOf(data[1]);
            int y = Integer.valueOf(data[2]);
            int z = Integer.valueOf(data[3]);
            int xWidth = Integer.valueOf(data[4]);
            int yWidth = Integer.valueOf(data[5]);
            int zWidth = Integer.valueOf(data[6]);
            AirObject rangeBox = new AirObject(x, y, z, xWidth, yWidth, zWidth,
                0); // no level, empty rangeBox representation

            if (!rangeHelper(rangeBox)) {
                System.out.println(
                    "All boxes must be entirely within the world box.");
                return;
            }
            if (!widthPositive(rangeBox)) {
                System.out.println("All widths must be positive.");
                return;
            }
            System.out.println("The following objects intersect (" + x + " " + y
                + " " + z + " " + xWidth + " " + yWidth + " " + zWidth + "):");
            LinkedList<AirObject> results = tree.intersectRangeSearch(rangeBox);
            int visitedNum = tree.intersectRegionSearchCounter(rangeBox);
            int length = results.length();
            results.moveToStart();
            if (!results.isEmpty()) {
                for (int i = 0; i < length; i++) {
                    LinkedList<String> currObjInfo = SkipList.getAirInfo(results
                        .getValue());
                    System.out.println(getObjInfo(currObjInfo));
                    results.next();
                }
            }
            System.out.println(visitedNum
                + " nodes were visited in the bintree");

        }
        else if (operator.equals("collisions")) {
            System.out.println(
                "The following collisions exist in the database:");
            LinkedList<Pair<AirObject, AirObject>> collisionList = tree
                .getCollisions();
            collisionList.moveToStart();
            if (collisionList.isEmpty()) {
                return;
            }
<<<<<<< HEAD
            while (!collisionList.isAtEnd()) {
=======
            for (int i = 0; i < collisionList.length(); i++) {
>>>>>>> c02d9ab (final edition)
                Pair<AirObject, AirObject> objPair = collisionList.getValue();
                LinkedList<String> pair1 = SkipList.getAirInfo(objPair
                    .getLeft());
                LinkedList<String> pair2 = SkipList.getAirInfo(objPair
                    .getRight());
                System.out.println("(" + getObjInfo(pair1) + ")" + " and " + "("
                    + getObjInfo(pair2) + ")");
                collisionList.next();
            }
        }
        // print
        else if (operator.equals("print")) {
            if (data[1].equals("skiplist")) {
                // skip list dump
                skiplist.dump();
            }
            else if (data[1].equals("bintree")) {
                System.out.println("Bintree dump:");
                LinkedList<AirObject> traverseResults = tree.preorderTraverse();
                traverseResults.moveToStart();
                int num = 0;
<<<<<<< HEAD
                while (!traverseResults.isAtEnd()) {
=======
                int numOfObjList = traverseResults.length();
                for (int i = 0; i < numOfObjList; i++) {
>>>>>>> c02d9ab (final edition)
                    AirObject curr = traverseResults.getValue();
                    if (curr instanceof InternalAirObject) {
                        System.out.println(printSpace(curr.getLevel()) + "I");
                        num++;
                    }
                    else {
                        LeafAirObject currLeaf = (LeafAirObject)curr;
                        if (currLeaf.getCurrNum() == 0) {
                            System.out.println(printSpace(curr.getLevel())
                                + "E");
                            num++;
                        }
                        else {
                            // get the object contents in the leaf
                            System.out.println(printSpace(curr.getLevel())
                                + "Leaf with " + currLeaf.getCurrNum()
                                + " objects:");
                            num++;
                            AirObject[] objList = currLeaf.getContainer();
                            for (int j = 0; j < currLeaf.getCurrNum(); j++) {
                                AirObject currLeafObj = objList[j];
                                LinkedList<String> currObjInfo = SkipList
                                    .getAirInfo(currLeafObj);

                                System.out.println(printSpace(curr.getLevel())
                                    + "(" + getObjInfo(currObjInfo) + ")");
                            }
                        }
                    }
                    traverseResults.next();
                }
                System.out.println(num + " bintree nodes printed");
            }
        }
        else if (operator.equals("rangeprint")) {
            String start = data[1];
            String end = data[2];
            if (start.compareTo(end) > 0) {
                System.out.println("Error in rangeprint parameters: |" + start
                    + "| is not less than |" + end + "|");
                return;
            }
            // range print with skip list
            LinkedList<AirObject> results = tree.preorderTraverse();
            results.moveToStart();
            LinkedList<AirObject> traverseResults = getAllObjects(results);
            traverseResults.moveToStart();
            AirObject[] objArray = transferToArray(traverseResults);
            sort(objArray);
            System.out.println("Found these records in the range |" + start
                + "| to |" + end + "|");
            for (int i = 0; i < objArray.length; i++) {
                if (objArray[i].getName().compareTo(start) >= 0 && objArray[i]
                    .getName().compareTo(end) <= 0) {
                    // print the object out
                    LinkedList<String> currObjInfo = SkipList.getAirInfo(
                        objArray[i]);
                    System.out.println(getObjInfo(currObjInfo));
                }
            }

        }
        else {
            throw new IllegalArgumentException();
        }

    }


<<<<<<< HEAD
=======
    private LinkedList<AirObject> getAllObjects(LinkedList<AirObject> results) {
        LinkedList<AirObject> objects = new LinkedList<>();
        objects.moveToStart();
        results.moveToStart();
        for (int i = 0; i < results.length(); i++) {
            if (results.getValue() instanceof LeafAirObject) {
                LeafAirObject currLeaf = (LeafAirObject)results.getValue();
                if (!currLeaf.isEmpty()) {
                    AirObject[] container = currLeaf.getContainer();
                    for (int j = 0; j < currLeaf.getCurrNum(); j++) {
                        objects.append(container[j]);
                    }
                } 
            }            
            results.next();
        }
        return objects;
    }


>>>>>>> c02d9ab (final edition)
    private String getObjInfo(LinkedList<String> currObjInfo) {
        String info = "";
        currObjInfo.moveToStart();
        int num = currObjInfo.length();
        for (int i = 0; i < num; i++) {
            info += currObjInfo.getValue();
            info += " ";
            currObjInfo.next();
        }
        info = info.trim();
        return info;
    }


    /**
     * Bubble sort
     */
    private void sort(AirObject[] container) {
        int numOfObject = container.length;
        for (int i = 0; i < numOfObject - 1; i++) {
            for (int j = 0; j < numOfObject - i - 1; j++) {
                if (container[j].compareTo(container[j + 1]) > 0) {
                    // swap
                    AirObject temp = container[j];
                    container[j] = container[j + 1];
                    container[j + 1] = temp;
                }
            }
        }
    }


    private AirObject[] transferToArray(LinkedList<AirObject> list) {
        int num = list.length();
        AirObject[] objArray = new AirObject[num];
        list.moveToStart();
        for (int i = 0; i < list.length(); i++) {
            objArray[i] = list.getValue();
            list.next();
        }
        return objArray;
    }


    private AirObject addHelper(String[] addData) {

        switch (addData[1]) {
            case "balloon":
                Balloon balloon = new Balloon(addData[2]); // set name here
                balloon.setxOrig(Integer.valueOf(addData[3]));
                balloon.setyOrig(Integer.valueOf(addData[4]));
                balloon.setzOrig(Integer.valueOf(addData[5]));
                balloon.setxWidth(Integer.valueOf(addData[6]));
                balloon.setyWidth(Integer.valueOf(addData[7]));
                balloon.setzWidth(Integer.valueOf(addData[8]));
                balloon.setType(addData[9]);
                balloon.setType(addData[10]);
                return balloon;
            case "airplane":
                Airplane airplane = new Airplane(addData[2]); // set name here
                airplane.setxOrig(Integer.valueOf(addData[3]));
                airplane.setyOrig(Integer.valueOf(addData[4]));
                airplane.setzOrig(Integer.valueOf(addData[5]));
                airplane.setxWidth(Integer.valueOf(addData[6]));
                airplane.setyWidth(Integer.valueOf(addData[7]));
                airplane.setzWidth(Integer.valueOf(addData[8]));
                airplane.setCarrier(addData[9]);
                airplane.setFlightNum(Integer.valueOf(addData[10]));
                airplane.setEngineNum(Integer.valueOf(addData[11]));
                return airplane;
            case "rocket":
                Rocket rocket = new Rocket(addData[2]); // set name here
                rocket.setxOrig(Integer.valueOf(addData[3]));
                rocket.setyOrig(Integer.valueOf(addData[4]));
                rocket.setzOrig(Integer.valueOf(addData[5]));
                rocket.setxWidth(Integer.valueOf(addData[6]));
                rocket.setyWidth(Integer.valueOf(addData[7]));
                rocket.setzWidth(Integer.valueOf(addData[8]));
                rocket.setAscentRate(Integer.valueOf(addData[9]));
                rocket.setTrajectory(Double.valueOf(addData[10]));
                return rocket;
            case "drone":
                Drone drone = new Drone(addData[2]); // set name here
                drone.setxOrig(Integer.valueOf(addData[3]));
                drone.setyOrig(Integer.valueOf(addData[4]));
                drone.setzOrig(Integer.valueOf(addData[5]));
                drone.setxWidth(Integer.valueOf(addData[6]));
                drone.setyWidth(Integer.valueOf(addData[7]));
                drone.setzWidth(Integer.valueOf(addData[8]));
                drone.setBrand(addData[9]);
                drone.setEngineNum(Integer.valueOf(addData[10]));
                return drone;
            case "bird":
                Bird bird = new Bird(addData[2]); // set name here
                bird.setxOrig(Integer.valueOf(addData[3]));
                bird.setyOrig(Integer.valueOf(addData[4]));
                bird.setzOrig(Integer.valueOf(addData[5]));
                bird.setxWidth(Integer.valueOf(addData[6]));
                bird.setyWidth(Integer.valueOf(addData[7]));
                bird.setzWidth(Integer.valueOf(addData[8]));
                bird.setType(addData[9]);
                bird.setNumber(Integer.valueOf(addData[10]));
                return bird;
        }
        return null;
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
