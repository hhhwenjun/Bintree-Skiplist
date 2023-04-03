import java.util.Random;

/**
 * Skip list class from OpenDSA
 * 
 * @author Xuhui Zeng, OpenDSA
 * @version 4.1.22
 * @param <K>
 *            Key of node
 * @param <E>
 *            Element of node
 *
 */
public class SkipList<K extends Comparable<K>, E> {

    private SkipNode<K, E> head;
    private int level;
    private int size;
    static private Random ran = new Random(); // Hold the Random class object

    /**
     * Constructor of the skip list
     */
    public SkipList() {
        head = new SkipNode<>(null, null, 0);
        level = -1;
        size = 0;
    }


    /**
     * Getter of list level
     * 
     * @return Level of list
     */
    public int getLevel() {
        return level;
    }


    /**
     * Getter of list size
     * 
     * @return Size of list
     */
    public int getSize() {
        return size;
    }


    /**
     * Search the element with key
     * 
     * @param key
     *            Key of node
     * @return The content of the target node
     */
    public E find(K key) {
        SkipNode<K, E> x = head; // Dummy header node
        for (int i = level; i >= 0; i--) {
            while ((x.forward()[i] != null) && (x.forward()[i].key().compareTo(
                key) < 0)) {
                x = x.forward()[i];
            }
        }
        x = x.forward()[0]; // Move to actual record, if it exists
        if ((x != null) && (x.key().compareTo(key) == 0)) {
            return x.element();
        }
        else {
            return null;
        }
    }


    /**
     * Randomly select a number of level
     * 
     * @return Level number
     */
    public int randomLevel() {
        int lev;
        for (lev = 0; Math.abs(ran.nextInt()) % 2 == 0; lev++) {
            // Do nothing
        }
        return lev;
    }


    /**
     * Insert a key, element pair into the skip list
     * 
     * @param key
     *            Key of pair
     * @param elem
     *            Element of pair
     * 
     */
    @SuppressWarnings("unchecked")
    public void insert(K key, E elem) {
        int newLevel = randomLevel(); // New node's level
        if (newLevel > level) { // If new node is deeper
            adjustHead(newLevel); // adjust the header
        }
        // Track end of level
        SkipNode<K, E>[] update = new SkipNode[level + 1];
        SkipNode<K, E> x = head;
        for (int i = level; i >= 0; i--) {
            while ((x.forward()[i] != null) && (x.forward()[i].key().compareTo(
                key) < 0)) {
                x = x.forward()[i];
            }
            update[i] = x;
        }
        x = new SkipNode<K, E>(key, elem, newLevel);
        for (int i = 0; i <= newLevel; i++) {
            x.forward()[i] = update[i].forward()[i];
            update[i].forward()[i] = x;
        }
        size++;
    }


    /**
     * Adjust the head of the skip list
     * 
     * @param newLevel
     *            The new level number
     */
    private void adjustHead(int newLevel) {
        SkipNode<K, E> temp = head;
        head = new SkipNode<K, E>(null, null, newLevel);
        for (int i = 0; i <= level; i++) {
            head.forward()[i] = temp.forward()[i];
        }
        level = newLevel;
    }


    /**
     * Remove the node in the skip list
     * 
     * @param key
     *            The key of the node
     * @return The deleted skip node
     */
    public E remove(K key) {
        @SuppressWarnings("unchecked")
        SkipNode<K, E>[] update = new SkipNode[level + 1];
        SkipNode<K, E> x = head;
        for (int i = level; i >= 0; i--) {
            while ((x.forward()[i] != null) && (x.forward()[i].key().compareTo(
                key) < 0)) {
                x = x.forward()[i];
            }
            update[i] = x;
        }
        E removedElement = null;
        if (x.forward()[0] != null && x.forward()[0].key().compareTo(
            key) == 0) {
            removedElement = x.forward()[0].element();
            link(update, x.forward()[0]);
            size--;
        }
        return removedElement;
    }


    /**
     * Re-link the nodes after modification
     * 
     * @param update
     *            The update array from the remove method
     * @param curr
     *            Current node location
     */
    public void link(SkipNode<K, E>[] update, SkipNode<K, E> curr) {
        for (int i = 0; i <= curr.getLevel(); i++) {
            update[i].forward()[i] = curr.forward()[i];
            curr.forward()[i] = null;
        }
    }


    /**
     * Print out the content within the list
     */
    public void dump() {
        SkipNode<K, E> x = head;
        System.out.println("SkipList dump:");
        // print the first head node
        System.out.println("Node has depth " + x.forward().length + ", "
            + "Value (" + x.key() + ")");
        while (x.forward()[0] != null) {
            x = x.forward()[0];
            RectangleInfo element = (RectangleInfo)x.element(); // x.getLevel()
            System.out.println("Node has depth " + x.forward().length + ", "
                + "Value (" + element.getName() + ", " + element
                    .printRectangleDim() + ")");

        }
        System.out.println("SkipList size is: " + size);
    }

}
