package implementations;

import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.StackADT;

/**
 * A generic stack implementation using a dynamic array (MyArrayList) as the backing data structure.
 * <p>
 * This class follows the Last-In-First-Out (LIFO) principle and supports standard stack operations.
 *
 * @param <E> the type of elements held in this stack
 */
public class MyStack<E> implements StackADT<E>, Serializable {

    private static final long serialVersionUID = 1L;
    private MyArrayList<E> list;

    /**
     * Constructs an empty stack.
     */
    public MyStack() {
        list = new MyArrayList<>();
    }

    /**
     * Pushes an element onto the top of the stack.
     *
     * @param toAdd the element to be pushed onto the stack
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot push null onto the stack");
        }
        list.add(toAdd);
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int index = list.size() - 1;
        return list.remove(index);
    }

    /**
     * Retrieves, but does not remove, the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.get(list.size() - 1);
    }

    /**
     * Removes all elements from the stack.
     */
    @Override
    public void clear() {
        list.clear();
    }

    /**
     * Checks if the stack is empty.
     *
     * @return {@code true} if the stack contains no elements, otherwise {@code false}
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns an array containing all of the elements in this stack in LIFO order.
     *
     * @return an array containing all elements of the stack
     */
    @Override
    public Object[] toArray() {
        Object[] returnArray = new Object[list.size()];
        int index = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            returnArray[index++] = list.get(i);
        }
        return returnArray;
    }

    /**
     * Returns an array containing all of the elements in this stack in LIFO order.
     * The runtime type of the returned array is that of the specified array.
     *
     * @param holder the array into which the elements of the stack are to be stored
     * @return an array containing the elements of the stack
     * @throws NullPointerException if the specified array is null
     */
    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        if (holder == null) {
            throw new NullPointerException("Holder array cannot be null");
        }

        if (holder.length < size()) {
            holder = (E[]) java.lang.reflect.Array.newInstance(holder.getClass().getComponentType(), size());
        }

        for (int i = 0; i < size(); i++) {
            holder[i] = list.get(size() - 1 - i);
        }

        if (holder.length > size()) {
            holder[size()] = null;
        }

        return holder;
    }

    /**
     * Checks if the specified element exists in the stack.
     *
     * @param toFind the element to search for
     * @return {@code true} if the element exists in the stack, otherwise {@code false}
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) {
            throw new NullPointerException("Cannot search for null in the stack");
        }
        return list.contains(toFind);
    }

    /**
     * Returns the 1-based position of the specified element from the top of the stack.
     * Returns -1 if the element is not found.
     *
     * @param toFind the element to search for
     * @return the 1-based position from the top of the stack, or -1 if not found
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public int search(Object toFind) {
        if (toFind == null) {
            throw new NullPointerException("Cannot search for null in the stack.");
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(toFind)) {
                return list.size() - i;
            }
        }

        return -1;
    }

    /**
     * Returns an iterator over the elements in the stack in LIFO order.
     *
     * @return an iterator over the stack elements
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = size() - 1;

            @Override
            public boolean hasNext() {
                return currentIndex >= 0;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the stack.");
                }
                return list.get(currentIndex--);
            }
        };
    }

    /**
     * Compares the specified stack with this stack for equality.
     * Two stacks are equal if they contain the same elements in the same order.
     *
     * @param that the stack to compare with
     * @return {@code true} if the stacks are equal, otherwise {@code false}
     */
    @Override
    public boolean equals(StackADT<E> that) {
        if (that == null || that.size() != this.size()) {
            return false;
        }

        Iterator<E> thisIterator = this.iterator();
        Iterator<E> thatIterator = that.iterator();

        while (thisIterator.hasNext() && thatIterator.hasNext()) {
            if (!thisIterator.next().equals(thatIterator.next())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the number of elements currently in the stack.
     *
     * @return the size of the stack
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Indicates whether the stack has reached its capacity.
     * Since this implementation uses a dynamic array, it never overflows.
     *
     * @return {@code false} always, as dynamic arrays have no fixed limit
     */
    @Override
    public boolean stackOverflow() {
        return false;
    }
}
