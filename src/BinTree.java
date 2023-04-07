
/**
 * The bin tree structure
 */
public class BinTree {

    private AirObject root;

    /**
     * The constructor of the tree
     */
    public BinTree() {
        root = new AirObject(0, 0, 0, 1024, 1024, 1024, 0); // default at level
                                                            // 0
    }


    /**
     * clear the tree
     */
    public void clear() {
        root = new AirObject(0, 0, 0, 1024, 1024, 1024, 0); // default at level
                                                            // 0
    }


    /**
     * Check if the tree is empty
     * 
     * @return True if the tree is only AirObject, if not empty, it should be
     *         leaf or internal airObject
     */
    public boolean isEmpty() {
        return (root instanceof AirObject);
    }


    /**
     * Insert content to the Bintree
     * Throws when the data is null
     * 
     * @param airObject
     * @throws IllegalArgumentException
     */
    public void insert(AirObject airObject) throws IllegalArgumentException {
        if (isEmpty()) {
            root = new LeafAirObject(0, 0, 0, 1024, 1024, 1024, 0);
            // current root is a leaf, only contain the newly inserted air
            // object
            LeafAirObject currRoot = (LeafAirObject)root;
            currRoot.addAirObject(airObject);
            root = currRoot;
        }
        else {
            root = recursiveInsert(root, airObject);
        }

    }


    // ----------------------------------------------------------
    /**
     * insert an air object recursively
     * 
     * @param curr
     *            the current node
     * @param insertObject
     *            Air object to insert
     * @return Air object for recursion
     * @throws IllegalArgumentException
     */
    public AirObject recursiveInsert(AirObject curr, AirObject insertObject)
        throws IllegalArgumentException {
        // base case, root either be leaf or internal node
        if (curr instanceof LeafAirObject) {
            LeafAirObject currRoot = (LeafAirObject)curr;
            if (shouldSplit(currRoot, insertObject)) {
                InternalAirObject internal = new InternalAirObject(currRoot
                    .getXorig(), currRoot.getYorig(), currRoot.getZorig(),
                    currRoot.getXwidth(), currRoot.getYwidth(), currRoot
                        .getZwidth(), currRoot.getLevel());
                AirObject[] currInsertedObjs = currRoot.getContainer();
                internal = splitHelper(internal);
                for (int i = 0; i < currRoot.getCurrNum(); i++) {
                    curr = recursiveInsert(internal, currInsertedObjs[i]);
                }
                curr = recursiveInsert(internal, insertObject);
            }
            else {
                // add to current leaf
                currRoot.addAirObject(insertObject);
                curr = currRoot;
            }
        }
        // it is internal node, keep going down
        else {
            InternalAirObject currInternal = (InternalAirObject)curr;
            int locX = insertObject.getXorig();
            int locY = insertObject.getYorig();
            int locZ = insertObject.getZorig();

            if (currInternal.getXwidth() < currInternal.getYwidth()) {
                // next split at Y
                if (locY < currInternal.getYorig() + currInternal.getYwidth()
                    / 2) {
                    currInternal.setLeft(recursiveInsert(currInternal.getLeft(),
                        insertObject));
                }
                else {
                    currInternal.setRight(recursiveInsert(currInternal
                        .getRight(), insertObject));
                }
            }
            else if (currInternal.getYwidth() < currInternal.getZwidth()) {
                // next split at Z
                if (locZ < currInternal.getZorig() + currInternal.getZwidth()
                    / 2) {
                    currInternal.setLeft(recursiveInsert(currInternal.getLeft(),
                        insertObject));
                }
                else {
                    currInternal.setRight(recursiveInsert(currInternal
                        .getRight(), insertObject));
                }
            }
            else {
                // next split at X
                if (locX < currInternal.getXorig() + currInternal.getXwidth()
                    / 2) {
                    currInternal.setLeft(recursiveInsert(currInternal.getLeft(),
                        insertObject));
                }
                else {
                    currInternal.setRight(recursiveInsert(currInternal
                        .getRight(), insertObject));
                }
            }
        }
        return curr;
    }


    private boolean shouldSplit(LeafAirObject leaf, AirObject insertObject) {
        if (leaf.getCurrNum() <= 3)
            return false;
        AirObject[] container = leaf.getContainer();
        int overlapCount = 0;
        for (int i = 0; i < leaf.getCurrNum(); i++) {
            AirObject currObject = container[i];
            if (overlap(currObject, insertObject)) {
                overlapCount++;
            }
        }

        return overlapCount < leaf.getCurrNum();
    }


    private boolean overlap(AirObject object1, AirObject object2) {
        int object1maxX = object1.getXorig() + object1.getXwidth();
        int object1minX = object1.getXorig();
        int object2maxX = object2.getXorig() + object2.getXwidth();
        int object2minX = object2.getXorig();

        int object1maxY = object1.getYorig() + object1.getYwidth();
        int object1minY = object1.getYorig();
        int object2maxY = object2.getYorig() + object2.getYwidth();
        int object2minY = object2.getYorig();

        int object1maxZ = object1.getZorig() + object1.getZwidth();
        int object1minZ = object1.getZorig();
        int object2maxZ = object2.getZorig() + object2.getZwidth();
        int object2minZ = object2.getZorig();

        return (object1maxX > object2minX || object1minX < object2maxX)
            && (object1maxY > object2minY || object1minY < object2maxY)
            && (object1maxZ > object2minZ || object1minZ < object2maxZ);
    }


    private InternalAirObject splitHelper(InternalAirObject currInternal) {

        int currLevel = currInternal.getLevel();
        int currX = currInternal.getXorig();
        int currY = currInternal.getYorig();
        int currZ = currInternal.getZorig();
        int xWidth = currInternal.getXwidth();
        int yWidth = currInternal.getYwidth();
        int zWidth = currInternal.getZwidth();

        // split to two leafs
        LeafAirObject leftWeight = new LeafAirObject(currX, currY, currZ,
            xWidth, yWidth, zWidth, currLevel + 1);
        LeafAirObject rightWeight = new LeafAirObject(currX, currY, currZ,
            xWidth, yWidth, zWidth, currLevel + 1);
        currInternal.setLeft(leftWeight);
        currInternal.setRight(rightWeight);

        if (xWidth < yWidth) {
            // split on y
            leftWeight.setyWidth(yWidth / 2);
            rightWeight.setyWidth(yWidth / 2);
            rightWeight.setyOrig(currY + yWidth / 2);
        }
        else if (yWidth < zWidth) {
            // split on z
            leftWeight.setzWidth(zWidth / 2);
            rightWeight.setzWidth(zWidth / 2);
            rightWeight.setzOrig(currZ + zWidth / 2);
        }
        else {
            // split on x
            leftWeight.setxWidth(xWidth / 2);
            rightWeight.setxWidth(xWidth / 2);
            rightWeight.setxOrig(currX + xWidth / 2);
        }
        return currInternal;
    }


    private boolean insideRect(
        int x,
        int y,
        int startX,
        int startY,
        int widthRange,
        int heightRange) {
        return x >= startX && y >= startY && x <= startX + widthRange
            && y <= startY + heightRange;
    }

//
// // ----------------------------------------------------------
// /**
// * counter to assist region search
// *
// * @param startX
// * X coordinate of box
// * @param startY
// * Y coordinate of box
// * @param widthRange
// * width of box
// * @param heightRange
// * height of box
// * @return counter
// */
// public int regionSearchCounter(
// int startX,
// int startY,
// int widthRange,
// int heightRange) {
//
// return regionSearchCounterHelper(root, startX, startY, widthRange,
// heightRange);
//
// }
//
//
// private int regionSearchCounterHelper(
// Node curr,
// int startX,
// int startY,
// int widthRange,
// int heightRange) {
//
// if (curr instanceof Leaf) {
// Leaf currLeaf = (Leaf)curr;
// if (insideRect(currLeaf.getLocX(), currLeaf.getLocY(), startX,
// startY, widthRange, heightRange)) {
// return 1;
// }
// return 0;
// }
// if (curr instanceof FlyWeight) {
// Node temp = new Node(0, startX, startY, widthRange, heightRange);
// if (overlap(curr, temp)) {
// return 1;
// }
// return 0;
// }
//
// Node temp = new Node(0, startX, startY, widthRange, heightRange);
// InternalNode currInternal = (InternalNode)curr;
// if (overlap(curr, temp)) {
//
// return 1 + regionSearchCounterHelper(currInternal.getLeft(), startX,
// startY, widthRange, heightRange) + regionSearchCounterHelper(
// currInternal.getRight(), startX, startY, widthRange,
// heightRange);
// }
// return 0;
//
// }
//
//
// private void regionSearchHelper(
// LinkedList<Leaf> results,
// int startX,
// int startY,
// int widthRange,
// int heightRange,
// Node curr) {
// if (curr instanceof Leaf) {
// Leaf currLeaf = (Leaf)curr;
// if (insideRect(currLeaf.getLocX(), currLeaf.getLocY(), startX,
// startY, widthRange, heightRange)) {
// results.append(currLeaf);
// }
// }
// else {
// Node temp = new Node(0, startX, startY, widthRange, heightRange);
// if (overlap(curr, temp)) {
// if (curr instanceof InternalNode) {
// InternalNode currInternal = (InternalNode)curr;
// regionSearchHelper(results, startX, startY, widthRange,
// heightRange, currInternal.getLeft());
// regionSearchHelper(results, startX, startY, widthRange,
// heightRange, currInternal.getRight());
// }
// }
// }
// }


    // ----------------------------------------------------------
    /**
     * remove a node
     * 
     * @param object
     *            Air object to remove
     */
    public void remove(AirObject object) {
        root = remove(root, object);
        root = garbageCollect(root);
    }


    /**
     * Recursively remove the node, will use find method to check if the node
     * exists
     * 
     * @param currNode
     *            current node
     * @param x
     *            X coordinate of node
     * @param y
     *            Y coordinate of node
     * @return
     */
    // check if we have the remove object in skiplist before we call the remove
    private AirObject remove(AirObject curr, AirObject removeObject) {
        if (curr instanceof LeafAirObject) {
            LeafAirObject currLeaf = (LeafAirObject)curr;
            currLeaf.removeAirObject(removeObject);
            if (currLeaf.isEmpty()) {
                AirObject flyWeight = new AirObject();
                curr = flyWeight;
            }
        }
        else {
            InternalAirObject currInternal = (InternalAirObject)curr;
            currInternal.setLeft(remove(currInternal.getLeft(), removeObject));
            currInternal.setRight(remove(currInternal.getRight(),
                removeObject));
        }
        return curr;
    }


    /**
     * Remove all the branches that are not necessary
     * 
     * @param currNode
     *            current node
     * @return
     */
    private AirObject garbageCollect(AirObject currNode) {
        if (currNode instanceof InternalAirObject) {
            InternalAirObject currInternal = (InternalAirObject)currNode;
            if (currInternal.getLeft() instanceof AirObject && currInternal
                .getRight() instanceof AirObject) {
                return new AirObject(currInternal.getXorig(), currInternal
                    .getYorig(), currInternal.getZorig(), currInternal
                        .getXwidth(), currInternal.getYwidth(), currInternal
                            .getZwidth(), currInternal.getLevel());
            }
            else if (currInternal.getLeft() instanceof AirObject && currInternal
                .getRight() instanceof LeafAirObject) {
                LeafAirObject currLeaf = (LeafAirObject)currInternal.getRight();
                currLeaf.setLevel(currInternal.getLevel());
                currLeaf.setxOrig(currInternal.getXorig());
                currLeaf.setyOrig(currInternal.getYorig());
                currLeaf.setzOrig(currInternal.getZorig());
                currLeaf.setxWidth(currInternal.getXwidth());
                currLeaf.setyWidth(currInternal.getYwidth());
                currLeaf.setzWidth(currInternal.getZwidth());
                return currLeaf;
            }
            else if (currInternal.getRight() instanceof AirObject
                && currInternal.getLeft() instanceof LeafAirObject) {
                LeafAirObject currLeaf = (LeafAirObject)currInternal.getLeft();
                currLeaf.setLevel(currInternal.getLevel());
                currLeaf.setxOrig(currInternal.getXorig());
                currLeaf.setyOrig(currInternal.getYorig());
                currLeaf.setzOrig(currInternal.getZorig());
                currLeaf.setxWidth(currInternal.getXwidth());
                currLeaf.setyWidth(currInternal.getYwidth());
                currLeaf.setzWidth(currInternal.getZwidth());
                return currLeaf;
            }
            else {
                currInternal.setLeft(garbageCollect(currInternal.getLeft()));
                currInternal.setRight(garbageCollect(currInternal.getRight()));
                if (!garbageCollectHelper(currInternal)) {
                    currNode = garbageCollect(currInternal);
                }
            }
        }
        return currNode;
    }


    private boolean garbageCollectHelper(InternalAirObject currInternal) {
        return !(currInternal.getLeft() instanceof AirObject && currInternal
            .getRight() instanceof AirObject) && !(currInternal
                .getLeft() instanceof LeafAirObject && currInternal
                    .getRight() instanceof AirObject) && !(currInternal
                        .getRight() instanceof LeafAirObject && currInternal
                            .getLeft() instanceof AirObject);

    }


    // ----------------------------------------------------------
    /**
     * preorder tree traversal for print
     * 
     * @return list
     */
    public LinkedList<AirObject> preorderTraverse() {
        LinkedList<AirObject> list = new LinkedList<>();
        preorderHelper(root, list);
        list.moveToStart();
        return list;
    }


    private void preorderHelper(AirObject currNode, LinkedList<AirObject> list) {
        list.append(currNode);
        if (currNode instanceof InternalAirObject) {
            InternalAirObject currInternal = (InternalAirObject)currNode;
            preorderHelper(currInternal.getLeft(), list);
            preorderHelper(currInternal.getRight(), list);
        }
    }

}
