package implementations;

import utilities.ListADT;
import utilities.Iterator;
import java.util.NoSuchElementException;

/**
 * A custom doubly linked list implementation.
 *
 * @param <E> The type of elements in this list.
 */
public class MyDLL<E> implements ListADT<E> {
	
	private static final long serialVersionUID = 1L;

    private MyDLLNode<E> head;
    private MyDLLNode<E> tail;
    private int size;

    public MyDLL() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null)
            throw new NullPointerException("Null elements not allowed.");
        add(size, toAdd);
        return true;
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null)
            throw new NullPointerException("Cannot add null to list.");
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Invalid index.");

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

        if (index == 0) {
            newNode.setNext(head);
            if (head != null) head.setPrev(newNode);
            head = newNode;
            if (size == 0) tail = newNode;
        } else if (index == size) {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        } else {
            MyDLLNode<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            MyDLLNode<E> prevNode = current.getPrev();
            prevNode.setNext(newNode);
            newNode.setPrev(prevNode);
            newNode.setNext(current);
            current.setPrev(newNode);
        }

        size++;
        return true;
    }


    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index.");

        MyDLLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index.");

        MyDLLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        if (current.getPrev() != null) current.getPrev().setNext(current.getNext());
        else head = current.getNext();

        if (current.getNext() != null) current.getNext().setPrev(current.getPrev());
        else tail = current.getPrev();

        size--;
        return current.getData();
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null)
            throw new NullPointerException("Cannot remove null.");

        MyDLLNode<E> current = head;
        int index = 0;

        while (current != null) {
            if (current.getData().equals(toRemove)) {
                return remove(index);
            }
            current = current.getNext();
            index++;
        }

        return null;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null)
            throw new NullPointerException("Cannot add from null list.");

        for (int i = 0; i < toAdd.size(); i++) {
            this.add(toAdd.get(i));
        }

        return true;
    }
    
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null)
            throw new NullPointerException("Cannot set null value.");
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index.");

        MyDLLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        E oldData = current.getData();
        current.setData(toChange);
        return oldData;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null)
            throw new NullPointerException("Cannot search for null.");

        MyDLLNode<E> current = head;
        while (current != null) {
            if (current.getData().equals(toFind))
                return true;
            current = current.getNext();
        }
        return false;
    }

    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null)
            throw new NullPointerException("Provided array cannot be null.");
        if (toHold.length < size) {
            toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
        }

        MyDLLNode<E> current = head;
        for (int i = 0; i < size; i++) {
            toHold[i] = current.getData();
            current = current.getNext();
        }

        return toHold;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        MyDLLNode<E> current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.getData();
            current = current.getNext();
        }
        return array;
    }

    @Override
    public Iterator<E> iterator() {
        return new DLLIterator();
    }

    private class DLLIterator implements Iterator<E> {
        private MyDLLNode<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E data = current.getData();
            current = current.getNext();
            return data;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
