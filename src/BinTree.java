
/**
 * The bin tree structure
 */
public class BinTree {

    private Node root;

    /**
     * The constructor of the tree
     */
    public BinTree() {
        root = new FlyWeight(0, 0, 0, 1024, 1024); // default at level 0
    }


    /**
     * clear the tree
     */
    public void clear() {
        root = new FlyWeight(0, 0, 0, 1024, 1024); // default at level 0
    }


    /**
     * Check if the tree is empty
     * 
     * @return True if the tree is empty, false if not
     */
    public boolean isEmpty() {
        return (root instanceof FlyWeight);
    }


    /**
     * Insert content to the Bintree
     * Throws when the data is null
     * 
     * @param city
     *            name of the city
     * @param locX
     *            X coordinate of city
     * @param locY
     *            Y coordinate of city
     * @throws IllegalArgumentException
     */
    public void insert(int locX, int locY, String city)
        throws IllegalArgumentException {
        if (isEmpty()) {
            root = new Leaf(0, city, 0, 0, locX, locY, root.getWidth(), root
                .getHeight());
        }
        else {
            root = recursiveInsert(root, city, locX, locY);
        }

    }


    // ----------------------------------------------------------
    /**
     * insert a city node recursively
     * 
     * @param currNode
     *            the current node
     * @param city
     *            the name of the city
     * @param locX
     *            X coordinate of city
     * @param locY
     *            Y coordinate of city
     * @return currNode
     * @throws IllegalArgumentException
     */
    public Node recursiveInsert(Node currNode, String city, int locX, int locY)
        throws IllegalArgumentException {
        // base case
        if (currNode instanceof FlyWeight) {
            return new Leaf(currNode.getLevel(), city, currNode.getX(), currNode
                .getY(), locX, locY, currNode.getWidth(), currNode.getHeight());
        }
        // it is internal node, keep going down
        if (currNode instanceof InternalNode) {
            InternalNode currInternal = (InternalNode)currNode;
            if (currInternal.getHeight() == currInternal.getWidth()) {
                // next split at width
                if (locX < currInternal.getX() + currInternal.getWidth() / 2) {
                    currInternal.setLeft(recursiveInsert(currInternal.getLeft(),
                        city, locX, locY));
                }
                else {
                    currInternal.setRight(recursiveInsert(currInternal
                        .getRight(), city, locX, locY));
                }
            }
            else {
                // next split at height
                if (locY < currInternal.getY() + currInternal.getHeight() / 2) {
                    currInternal.setLeft(recursiveInsert(currInternal.getLeft(),
                        city, locX, locY));
                }
                else {
                    currInternal.setRight(recursiveInsert(currInternal
                        .getRight(), city, locX, locY));
                }
            }

        }
        // it is a leaf node, need to split and going down, conflict
        else {
            Leaf currLeaf = (Leaf)currNode;
            if (matchNode(locX, locY, currLeaf)) {
                throw new IllegalArgumentException();
            }
            InternalNode currInternal = new InternalNode(currNode.getLevel(),
                currNode.getX(), currNode.getY(), currNode.getWidth(), currNode
                    .getHeight());

            currInternal = splitHelper(currInternal);
            currInternal = insertHelper(currInternal, city, locX, locY);
            currInternal = insertHelper(currInternal, currLeaf.getCity(),
                currLeaf.getLocX(), currLeaf.getLocY());

            return currInternal;

        }
        return currNode;
    }


    private InternalNode splitHelper(InternalNode currInternal) {
        int currLevel = currInternal.getLevel();
        int currX = currInternal.getX();
        int currY = currInternal.getY();
        int currWidth = currInternal.getWidth();
        int currHeight = currInternal.getHeight();

        // split to two flyweights
        FlyWeight leftWeight = new FlyWeight(currLevel + 1, currX, currY,
            currWidth, currHeight);
        FlyWeight rightWeight = new FlyWeight(currLevel + 1, currX, currY,
            currWidth, currHeight);
        currInternal.setLeft(leftWeight);
        currInternal.setRight(rightWeight);

        if (currWidth == currHeight) {
            // split on width
            leftWeight.setWidth(currWidth / 2);
            rightWeight.setWidth(currWidth / 2);
            rightWeight.setX(currX + currWidth / 2);
        }
        else {
            // split on height
            leftWeight.setHeight(currHeight / 2);
            rightWeight.setHeight(currHeight / 2);
            rightWeight.setY(currY + currHeight / 2);
        }

        return currInternal;
    }


    // ----------------------------------------------------------
    /**
     * helper method for insert
     * 
     * @param currInternal
     *            the current internal node
     * @param city
     *            the name of the city
     * @param locX
     *            X coordinate of city
     * @param locY
     *            Y coordinate of city
     * @return currInternal
     */
    public InternalNode insertHelper(
        InternalNode currInternal,
        String city,
        int locX,
        int locY) {
        int halfHeight = currInternal.getHeight() / 2;
        int halfWidth = currInternal.getWidth() / 2;
        if (currInternal.getHeight() == currInternal.getWidth()) {

            if (currInternal.getX() + halfWidth > locX) {
                // left node
                currInternal.setLeft(recursiveInsert(currInternal.getLeft(),
                    city, locX, locY));
            }
            else {
                currInternal.setRight(recursiveInsert(currInternal.getRight(),
                    city, locX, locY));
            }
        }
        else {
            if (currInternal.getY() + halfHeight > locY) {
                // left node
                currInternal.setLeft(recursiveInsert(currInternal.getLeft(),
                    city, locX, locY));
            }
            else {
                currInternal.setRight(recursiveInsert(currInternal.getRight(),
                    city, locX, locY));
            }
        }

        return currInternal;
    }


    // ----------------------------------------------------------
    /**
     * Find if a match node
     * 
     * @param x
     *            X coordinate of node
     * @param y
     *            Y coordinate of node
     * @param node
     *            leaf node
     * @return if node matched
     */
    public boolean matchNode(int x, int y, Leaf node) {
        return x == node.getLocX() && y == node.getLocY();
    }


    // ----------------------------------------------------------
    /**
     * find root node
     * 
     * @param x
     *            X coordinate of node
     * @param y
     *            Y coordinate of node
     * @return root node
     */
    public boolean find(int x, int y) {
        return find(root, x, y);
    }


    // ----------------------------------------------------------
    /**
     * Find city
     * 
     * @param x
     *            X coordinate of city
     * @param y
     *            Y coordinate of city
     * @return city name
     */
    public String findCity(int x, int y) {
        LinkedList<String> cityName = new LinkedList<>();
        findCityName(root, x, y, cityName);
        return cityName.getValue();
    }


    private void findCityName(
        Node currNode,
        int x,
        int y,
        LinkedList<String> cityName) {

        if (currNode instanceof Leaf) {
            if (matchNode(x, y, (Leaf)currNode)) {
                Leaf currLeaf = (Leaf)currNode;
                cityName.append(currLeaf.getCity());
            }
            else
                return;
        }
        else if (currNode instanceof FlyWeight) {
            return;
        }
        else {
            InternalNode currInternal = (InternalNode)currNode;
            findCityName(currInternal.getLeft(), x, y, cityName);
            findCityName(currInternal.getRight(), x, y, cityName);
        }
    }


    private boolean find(Node currNode, int x, int y) {

        if (currNode instanceof Leaf) {
            return (matchNode(x, y, (Leaf)currNode));

        }
        else if (currNode instanceof FlyWeight) {
            return false;
        }
        else {
            InternalNode currInternal = (InternalNode)currNode;
            return find(currInternal.getLeft(), x, y) || find(currInternal
                .getRight(), x, y);
        }
    }


    // ----------------------------------------------------------
    /**
     * region search method
     * 
     * @param startX
     *            X coordinate of box
     * @param startY
     *            Y coordinate of box
     * @param widthRange
     *            width of box
     * @param heightRange
     *            height of box
     * @return results
     */
    public LinkedList<Leaf> regionSearch(
        int startX,
        int startY,
        int widthRange,
        int heightRange) {
        LinkedList<Leaf> results = new LinkedList<>();

        regionSearchHelper(results, startX, startY, widthRange, heightRange,
            root);
        results.moveToStart();

        return results;
    }


    private boolean overlap(Node node1, Node node2) {
        int xOverlap = Math.min(node1.getX() + node1.getWidth(), node2.getX()
            + node2.getWidth()) - Math.max(node1.getX(), node2.getX());
        int yOverlap = Math.min(node1.getY() + node1.getHeight(), node2.getY()
            + node2.getHeight()) - Math.max(node1.getY(), node2.getY());
        return xOverlap > 0 && yOverlap > 0;

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


    // ----------------------------------------------------------
    /**
     * counter to assist region search
     * 
     * @param startX
     *            X coordinate of box
     * @param startY
     *            Y coordinate of box
     * @param widthRange
     *            width of box
     * @param heightRange
     *            height of box
     * @return counter
     */
    public int regionSearchCounter(
        int startX,
        int startY,
        int widthRange,
        int heightRange) {

        return regionSearchCounterHelper(root, startX, startY, widthRange,
            heightRange);

    }


    private int regionSearchCounterHelper(
        Node curr,
        int startX,
        int startY,
        int widthRange,
        int heightRange) {

        if (curr instanceof Leaf) {
            Leaf currLeaf = (Leaf)curr;
            if (insideRect(currLeaf.getLocX(), currLeaf.getLocY(), startX,
                startY, widthRange, heightRange)) {
                return 1;
            }
            return 0;
        }
        if (curr instanceof FlyWeight) {
            Node temp = new Node(0, startX, startY, widthRange, heightRange);
            if (overlap(curr, temp)) {
                return 1;
            }
            return 0;
        }

        Node temp = new Node(0, startX, startY, widthRange, heightRange);
        InternalNode currInternal = (InternalNode)curr;
        if (overlap(curr, temp)) {

            return 1 + regionSearchCounterHelper(currInternal.getLeft(), startX,
                startY, widthRange, heightRange) + regionSearchCounterHelper(
                    currInternal.getRight(), startX, startY, widthRange,
                    heightRange);
        }
        return 0;

    }


    private void regionSearchHelper(
        LinkedList<Leaf> results,
        int startX,
        int startY,
        int widthRange,
        int heightRange,
        Node curr) {
        if (curr instanceof Leaf) {
            Leaf currLeaf = (Leaf)curr;
            if (insideRect(currLeaf.getLocX(), currLeaf.getLocY(), startX,
                startY, widthRange, heightRange)) {
                results.append(currLeaf);
            }
        }
        else {
            Node temp = new Node(0, startX, startY, widthRange, heightRange);
            if (overlap(curr, temp)) {
                if (curr instanceof InternalNode) {
                    InternalNode currInternal = (InternalNode)curr;
                    regionSearchHelper(results, startX, startY, widthRange,
                        heightRange, currInternal.getLeft());
                    regionSearchHelper(results, startX, startY, widthRange,
                        heightRange, currInternal.getRight());
                }
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * remove a node
     * 
     * @param x
     *            X coordinate of node
     * @param y
     *            Y coordinate of node
     */
    public void remove(int x, int y) {
        root = remove(root, x, y);
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
    private Node remove(Node currNode, int x, int y) {
        if (currNode instanceof Leaf) {
            if (matchNode(x, y, (Leaf)currNode)) {
                return new FlyWeight(currNode.getLevel(), currNode.getX(),
                    currNode.getY(), currNode.getWidth(), currNode.getHeight());
            }
        }
        else if (currNode instanceof FlyWeight) {
            return currNode;
        }
        else {
            InternalNode currInternal = (InternalNode)currNode;
            currInternal.setLeft(remove(currInternal.getLeft(), x, y));
            currInternal.setRight(remove(currInternal.getRight(), x, y));
        }
        return currNode;
    }


    /**
     * Remove all the branches that are not necessary
     * 
     * @param currNode
     *            current node
     * @return
     */
    private Node garbageCollect(Node currNode) {
        if (currNode instanceof InternalNode) {
            InternalNode currInternal = (InternalNode)currNode;
            if (currInternal.getLeft() instanceof FlyWeight && currInternal
                .getRight() instanceof FlyWeight) {
                return new FlyWeight(currInternal.getLevel(), currInternal
                    .getX(), currInternal.getY(), currInternal.getWidth(),
                    currInternal.getHeight());
            }
            else if (currInternal.getLeft() instanceof FlyWeight && currInternal
                .getRight() instanceof Leaf) {
                Leaf currLeaf = (Leaf)currInternal.getRight();
                currLeaf.setLevel(currInternal.getLevel());
                currLeaf.setX(currInternal.getX());
                currLeaf.setY(currInternal.getY());
                currLeaf.setWidth(currInternal.getWidth());
                currLeaf.setHeight(currInternal.getHeight());
                return currLeaf;
            }
            else if (currInternal.getRight() instanceof FlyWeight
                && currInternal.getLeft() instanceof Leaf) {
                Leaf currLeaf = (Leaf)currInternal.getLeft();
                currLeaf.setLevel(currInternal.getLevel());
                currLeaf.setX(currInternal.getX());
                currLeaf.setY(currInternal.getY());
                currLeaf.setWidth(currInternal.getWidth());
                currLeaf.setHeight(currInternal.getHeight());
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


    private boolean garbageCollectHelper(InternalNode currInternal) {
        return !(currInternal.getLeft() instanceof FlyWeight && currInternal
            .getRight() instanceof FlyWeight) && !(currInternal
                .getLeft() instanceof Leaf && currInternal
                    .getRight() instanceof FlyWeight) && !(currInternal
                        .getRight() instanceof Leaf && currInternal
                            .getLeft() instanceof FlyWeight);
        
    }


    // ----------------------------------------------------------
    /**
     * preorder tree traversal for print
     * 
     * @return list
     */
    public LinkedList<Node> preorderTraverse() {
        LinkedList<Node> list = new LinkedList<>();
        preorderHelper(root, list);
        list.moveToStart();
        return list;
    }


    private void preorderHelper(Node currNode, LinkedList<Node> list) {
        list.append(currNode);
        if (currNode instanceof InternalNode) {
            InternalNode currInternal = (InternalNode)currNode;
            preorderHelper(currInternal.getLeft(), list);
            preorderHelper(currInternal.getRight(), list);
        }
    }

}
