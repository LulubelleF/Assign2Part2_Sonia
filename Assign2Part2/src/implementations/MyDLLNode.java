package implementations;

import implementations.MyDLLNode;

/**
 * A node class used in the doubly linked list implementation (MyDLL).
 *
 * @param <E> The type of data stored in the node.
 */
public class MyDLLNode<E> {

    private E data;
    private MyDLLNode<E> prev;
    private MyDLLNode<E> next;

    /**
     * Constructor to create a node with given data.
     * 
     * @param data the data to store
     */
    public MyDLLNode(E data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public MyDLLNode<E> getPrev() {
        return prev;
    }

    public void setPrev(MyDLLNode<E> prev) {
        this.prev = prev;
    }

    public MyDLLNode<E> getNext() {
        return next;
    }

    public void setNext(MyDLLNode<E> next) {
        this.next = next;
    }
}  
