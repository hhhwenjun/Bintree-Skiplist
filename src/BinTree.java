/**
 * The bin tree structure
 * 
 * @author Xuhui Zeng
 * @version 2023.04.08
 * 
 */
public class BinTree {

    private AirObject root;

    /**
     * The constructor of the tree
     */
    public BinTree() {
        root = new LeafAirObject(0, 0, 0, 1024, 1024, 1024, 0); // default at
                                                                // level
        // 0
    }


    /**
     * clear the tree
     */
    public void clear() {
        root = new LeafAirObject(0, 0, 0, 1024, 1024, 1024, 0); // default at
                                                                // level
        // 0
    }


    /**
     * Check if the tree is empty
     * 
     * @return True if the tree is only AirObject, if not empty, it should be
     *         leaf or internal airObject
     */
    public boolean isEmpty() {
        if (root instanceof InternalAirObject)
            return false;
        LeafAirObject rootLeaf = (LeafAirObject)root;
        return rootLeaf.getCurrNum() == 0;
    }


    /**
     * Insert content to the Bintree
     * Throws when the data is null
     * 
     * @param airObject
     *            a general AirProject
     * @throws IllegalArgumentException
     */
    public void insert(AirObject airObject) throws IllegalArgumentException {
        if (isEmpty()) {
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

            if (overlap(currInternal.getLeft(), insertObject)) {
                currInternal.setLeft(recursiveInsert(currInternal.getLeft(),
                    insertObject));
            }
            if (overlap(currInternal.getRight(), insertObject)) {
                currInternal.setRight(recursiveInsert(currInternal.getRight(),
                    insertObject));
            }
        }
        return curr;
    }


    private boolean shouldSplit(LeafAirObject leaf, AirObject insertObject) {
        if (leaf.getCurrNum() < 3)
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

        return object1maxX > object2minX && object1minX < object2maxX
            && object1maxY > object2minY && object1minY < object2maxY
            && object1maxZ > object2minZ && object1minZ < object2maxZ;
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


    // ----------------------------------------------------------
    /**
     * Search intersect range
     * 
     * @param rangeBox
     *            intersect range box
     * 
     * @return results
     */
    public LinkedList<AirObject> intersectRangeSearch(AirObject rangeBox) {
        LinkedList<AirObject> results = new LinkedList<>();

        regionSearchHelper(results, rangeBox, root);
        results.moveToStart();

        return results;
    }


    private void regionSearchHelper(
        LinkedList<AirObject> results,
        AirObject box,
        AirObject curr) {

        if (curr instanceof LeafAirObject) {
            LeafAirObject currLeaf = (LeafAirObject)curr;
            AirObject[] container = currLeaf.getContainer();
            for (int i = 0; i < currLeaf.getCurrNum(); i++) {
                if (overlap(container[i], box)) {
                    boolean found = false;
                    AirObject currObj = container[i];
                    results.moveToStart();
                    if (results.length() > 0) {
                        for (int k = 0; k < results.length(); k++) {
                            if (results.getValue().compareTo(currObj) == 0) {
                                found = true;
                            }
                            results.next();
                        }
                        results.moveToEnd();
                    }
                    if (!found)
                        results.append(currObj);
                }
            }
        }
        else {
            // internal object
            if (overlap(curr, box)) {
                InternalAirObject currInternal = (InternalAirObject)curr;
                regionSearchHelper(results, box, currInternal.getLeft());
                regionSearchHelper(results, box, currInternal.getRight());
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Counter for intersect region search
     * 
     * @param rangeBox
     *            intersect range box
     * 
     * @return counter
     */
    public int intersectRegionSearchCounter(AirObject rangeBox) {

        return regionSearchCounterHelper(rangeBox, root);

    }


    private int regionSearchCounterHelper(AirObject box, AirObject curr) {

        if (curr instanceof LeafAirObject) {
            LeafAirObject currLeaf = (LeafAirObject)curr;
            if (overlap(box, currLeaf)) {
                return 1;
            }
            return 0;
        }
        // else internal
        InternalAirObject currInternal = (InternalAirObject)curr;
        if (overlap(box, currInternal)) {

            return 1 + regionSearchCounterHelper(box, currInternal.getLeft())
                + regionSearchCounterHelper(box, currInternal.getRight());
        }
        return 0;

    }


    /**
     * get all the collisions in the bin tree
     * 
     * @return List of collision objects
     */
    public LinkedList<Pair<AirObject, AirObject>> getCollisions() {
        LinkedList<Pair<AirObject, AirObject>> results = new LinkedList<>();
        collisionHelper(root, results);
        return results;
    }


    private void collisionHelper(
        AirObject currNode,
        LinkedList<Pair<AirObject, AirObject>> list) {

        if (currNode instanceof InternalAirObject) {
            InternalAirObject currInternal = (InternalAirObject)currNode;
            collisionHelper(currInternal.getLeft(), list);
            collisionHelper(currInternal.getRight(), list);
        }
        else {
            // this is a leaf air object
            LeafAirObject currLeaf = (LeafAirObject)currNode;
            AirObject[] container = currLeaf.getContainer();
            for (int i = 0; i < currLeaf.getCurrNum(); i++) {
                for (int j = i + 1; j < currLeaf.getCurrNum(); j++) {
                    if (overlap(container[i], container[j])) {
                        // check if duplicate
                        list.moveToStart();
                        boolean found = false;

                        if (list.length() > 0) {
                            for (int k = 0; k < list.length(); k++) {
                                Pair<AirObject, AirObject> pair = list
                                    .getValue();
                                if (pair.getLeft().compareTo(container[i]) == 0
                                    && pair.getRight().compareTo(
                                        container[j]) == 0) {
                                    found = true;
                                }
                                list.next();
                            }
                            list.moveToEnd();
                        }
                        if (!found) {
                            list.append(new Pair<>(container[i], container[j]));
                        }
                    }
                }
            }
        }
    }


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
        // base case
        if (curr instanceof LeafAirObject) {
            LeafAirObject currLeaf = (LeafAirObject)curr;
            if (!currLeaf.isEmpty() && currLeaf.findObject(removeObject)) {
                currLeaf.removeAirObject(removeObject);
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
            if (currInternal.getLeft() instanceof LeafAirObject && currInternal
                .getRight() instanceof LeafAirObject) {
                LeafAirObject left = (LeafAirObject)currInternal.getLeft();
                LeafAirObject right = (LeafAirObject)currInternal.getRight();
                if (left.isEmpty() && right.isEmpty()) {
                    return new LeafAirObject(currInternal.getXorig(),
                        currInternal.getYorig(), currInternal.getZorig(),
                        currInternal.getXwidth(), currInternal.getYwidth(),
                        currInternal.getZwidth(), currInternal.getLevel());
                }
                else if (left.isEmpty() && !right.isEmpty()) {
                    LeafAirObject currLeaf = (LeafAirObject)currInternal
                        .getRight();
                    currLeaf.setLevel(currInternal.getLevel());
                    currLeaf.setxOrig(currInternal.getXorig());
                    currLeaf.setyOrig(currInternal.getYorig());
                    currLeaf.setzOrig(currInternal.getZorig());
                    currLeaf.setxWidth(currInternal.getXwidth());
                    currLeaf.setyWidth(currInternal.getYwidth());
                    currLeaf.setzWidth(currInternal.getZwidth());
                    return currLeaf;
                }
                else if (!left.isEmpty() && right.isEmpty()) {
                    LeafAirObject currLeaf = (LeafAirObject)currInternal
                        .getLeft();
                    currLeaf.setLevel(currInternal.getLevel());
                    currLeaf.setxOrig(currInternal.getXorig());
                    currLeaf.setyOrig(currInternal.getYorig());
                    currLeaf.setzOrig(currInternal.getZorig());
                    currLeaf.setxWidth(currInternal.getXwidth());
                    currLeaf.setyWidth(currInternal.getYwidth());
                    currLeaf.setzWidth(currInternal.getZwidth());
                    return currLeaf;
                }
            }
            else {
                currInternal.setLeft(garbageCollect(currInternal.getLeft()));
                currInternal.setRight(garbageCollect(currInternal.getRight()));
                if (currInternal.getLeft() instanceof LeafAirObject
                    && currInternal.getRight() instanceof LeafAirObject) {
                    if (!garbageCollectHelper((LeafAirObject)currInternal
                        .getLeft(), (LeafAirObject)currInternal.getRight())) {
                        currNode = garbageCollect(currInternal);
                    }
                }
            }
        }
        return currNode;
    }


    private boolean garbageCollectHelper(
        LeafAirObject left,
        LeafAirObject right) {
        return !(left.isEmpty() && right.isEmpty()) && !(!left.isEmpty()
            && right.isEmpty()) && !(!right.isEmpty() && left.isEmpty());

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


    private void preorderHelper(
        AirObject currNode,
        LinkedList<AirObject> list) {
        list.append(currNode);
        if (currNode instanceof InternalAirObject) {
            InternalAirObject currInternal = (InternalAirObject)currNode;
            preorderHelper(currInternal.getLeft(), list);
            preorderHelper(currInternal.getRight(), list);
        }
    }
}




// -------------------------------------------------------------------------
/**
 * Support class for pair
 * 
 * @param <K>
 *            generic type
 * @param <V>
 *            generic type
 * 
 * @author Xuhui Zeng
 * @version 2023.04.08
 */
class Pair<K, V> {

    /**
     * left object
     */
    private K left;
    /**
     * right object
     */
    private V right;

    /**
     * Create a new Pair object.
     * 
     * @param left
     *            left object
     * @param right
     *            right object
     */
    public Pair(K left, V right) {
        this.left = left;
        this.right = right;
    }


    /**
     * Getter for left object
     * 
     * @return left
     */
    public K getLeft() {
        return left;
    }


    /**
     * Getter for right object
     * 
     * @return right
     */
    public V getRight() {
        return right;
    }


    /**
     * Setter for left object
     * 
     * @param left
     *            left object
     */
    public void setLeft(K left) {
        this.left = left;
    }


    /**
     * Setter for right object
     * 
     * @param right
     *            right object
     */
    public void setRight(V right) {
        this.right = right;
    }
}
