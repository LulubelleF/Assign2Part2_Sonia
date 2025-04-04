package implementations;

/**
 * A node class used in the doubly linked list implementation (MyDLL).
 *
 * @param <E> The type of data stored in the node.
 */
public class MyDLLNode<E> {

    /** The data stored in this node */
    private E data;

    /** Reference to the previous node in the list */
    private MyDLLNode<E> prev;

    /** Reference to the next node in the list */
    private MyDLLNode<E> next;

    /**
     * Constructs a new node with the specified data.
     * The previous and next references are initialized to null.
     *
     * @param data the data to store in the node
     */
    public MyDLLNode(E data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    /**
     * Returns the data stored in this node.
     *
     * @return the data
     */
    public E getData() {
        return data;
    }

    /**
     * Sets the data stored in this node.
     *
     * @param data the new data to store
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Returns the previous node in the list.
     *
     * @return the previous node
     */
    public MyDLLNode<E> getPrev() {
        return prev;
    }

    /**
     * Sets the reference to the previous node.
     *
     * @param prev the previous node to link to
     */
    public void setPrev(MyDLLNode<E> prev) {
        this.prev = prev;
    }

    /**
     * Returns the next node in the list.
     *
     * @return the next node
     */
    public MyDLLNode<E> getNext() {
        return next;
    }

    /**
     * Sets the reference to the next node.
     *
     * @param next the next node to link to
     */
    public void setNext(MyDLLNode<E> next) {
        this.next = next;
    }
}
