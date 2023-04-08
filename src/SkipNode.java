/**
 * Create the skip node from OpenDSA
 * 
 * @author Xuhui Zeng
 * @version 4.2.22
 *
 * @param <K>
 *            The key of pair
 * @param <E>
 *            The value of pair
 */
public class SkipNode<K extends Comparable<? super K>, E> {

    private KVPair<K, E> rec;
    private SkipNode<K, E>[] forward;
    private int level;

    /**
     * Constructor of skip node with level
     * 
     * @param key
     *            Key of node
     * @param elem
     *            Element of node
     * @param level
     *            Level number of node
     */
    @SuppressWarnings("unchecked")
    public SkipNode(K key, E elem, int level) {
        this.level = level;
        rec = new KVPair<>(key, elem);
        forward = new SkipNode[level + 1];
        for (int i = 0; i < level; i++) {
            forward[i] = null;
        }
    }


    /**
     * Getter for the element content
     * 
     * @return Element
     */
    public E element() {
        return rec.element();
    }


    /**
     * Getter for the level of nodes
     * 
     * @return Level
     */
    public int getLevel() {
        return level;
    }


    /**
     * Getter for the key content
     * 
     * @return The key
     */
    public K key() {
        return rec.key();
    }


    /**
     * Getter the forward node list
     * 
     * @return The node list
     */
    public SkipNode<K, E>[] forward() {
        return forward;
    }


    /**
     * Return the record content
     * 
     * @return The string of record
     */
    public String toString() {
        return rec.toString();
    }

}




/**
 * KV Pair class inside the skip list node
 * 
 * @author OpenDSA
 * @version 4.20.22
 *
 * @param <K>
 *            The key of the pair
 * @param <E>
 *            The element of the pair
 */
class KVPair<K extends Comparable<? super K>, E> {
    private K key;
    private E value;

    /**
     * Constructor for the KVPair
     * 
     * @param key
     *            The key
     * @param element
     *            The element content
     */
    public KVPair(K key, E element) {
        this.key = key;
        this.value = element;
    }


    /**
     * Getter for the element content
     * 
     * @return Element
     */
    public E element() {
        return value;
    }


    /**
     * Getter for the key content
     * 
     * @return The key
     */
    public K key() {
        return key;
    }


    /**
     * Return the pair information
     * 
     * @return The pair string
     */
    public String toString() {
        return "[" + key + ", " + value + "]";
    }

}
