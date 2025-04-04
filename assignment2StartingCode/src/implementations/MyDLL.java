package implementations;

import java.util.NoSuchElementException;
import utilities.Iterator;
import utilities.ListADT;

/**
 * A custom doubly linked list implementation of the ListADT interface.
 * 
 * @param <E> The type of elements stored in the list.
 */
public class MyDLL<E> implements ListADT<E> {

	private static final long serialVersionUID = 1L;

	private MyDLLNode<E> head;
	private MyDLLNode<E> tail;
	private int size;

	/**
	 * Constructs an empty doubly linked list.
	 */
	public MyDLL() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Adds an element to the end of the list.
	 * 
	 * @param toAdd The element to add.
	 * @return true if the element was added successfully.
	 * @throws NullPointerException if the element is null.
	 */
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException("Null elements not allowed.");
		}
		add(size, toAdd);
		return true;
	}

	/**
	 * Adds an element at the specified index in the list.
	 * 
	 * @param index The index at which the element should be added.
	 * @param toAdd The element to add.
	 * @return true if added successfully.
	 * @throws NullPointerException if the element is null.
	 * @throws IndexOutOfBoundsException if index is out of bounds.
	 */
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if (toAdd == null) {
			throw new NullPointerException("Cannot add null to list.");
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

		MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

		if (index == 0) {
			newNode.setNext(head);
			if (head != null) {
				head.setPrev(newNode);
			}
			head = newNode;
			if (size == 0) {
				tail = newNode;
			}
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

	/**
	 * Retrieves the element at the specified index.
	 * 
	 * @param index The index of the element to retrieve.
	 * @return The element at the specified index.
	 * @throws IndexOutOfBoundsException if index is out of bounds.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

		MyDLLNode<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getData();
	}

	/**
	 * Removes the element at the specified index.
	 * 
	 * @param index The index of the element to remove.
	 * @return The removed element.
	 * @throws IndexOutOfBoundsException if index is out of bounds.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

		MyDLLNode<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}

		if (current.getPrev() != null) {
			current.getPrev().setNext(current.getNext());
		} else {
			head = current.getNext();
		}

		if (current.getNext() != null) {
			current.getNext().setPrev(current.getPrev());
		} else {
			tail = current.getPrev();
		}

		size--;
		return current.getData();
	}

	/**
	 * Removes the first occurrence of the specified element.
	 * 
	 * @param toRemove The element to remove.
	 * @return The removed element, or null if not found.
	 * @throws NullPointerException if the element is null.
	 */
	@Override
	public E remove(E toRemove) throws NullPointerException {
		if (toRemove == null) {
			throw new NullPointerException("Cannot remove null.");
		}

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

	/**
	 * Adds all elements from the specified list to the current list.
	 * 
	 * @param toAdd The list of elements to add.
	 * @return true if elements were added.
	 * @throws NullPointerException if the provided list is null.
	 */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException("Cannot add from null list.");
		}

		for (int i = 0; i < toAdd.size(); i++) {
			this.add(toAdd.get(i));
		}

		return true;
	}

	/**
	 * Removes all elements from the list.
	 */
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Replaces the element at the specified index with a new value.
	 * 
	 * @param index The index to set the value at.
	 * @param toChange The new value.
	 * @return The old value at the specified index.
	 * @throws NullPointerException if the new value is null.
	 * @throws IndexOutOfBoundsException if index is out of bounds.
	 */
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if (toChange == null) {
			throw new NullPointerException("Cannot set null value.");
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

		MyDLLNode<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		E oldData = current.getData();
		current.setData(toChange);
		return oldData;
	}

	/**
	 * Checks if the list contains the specified element.
	 * 
	 * @param toFind The element to search for.
	 * @return true if the element is found, false otherwise.
	 * @throws NullPointerException if the element is null.
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) {
			throw new NullPointerException("Cannot search for null.");
		}

		MyDLLNode<E> current = head;
		while (current != null) {
			if (current.getData().equals(toFind)) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	/**
	 * Returns an array containing all elements in this list in proper sequence.
	 *
	 * @param toHold the array into which the elements are to be stored.
	 * @return an array containing the elements of the list.
	 * @throws NullPointerException if the specified array is null.
	 */
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		if (toHold == null) {
			throw new NullPointerException("Provided array cannot be null.");
		}
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

	/**
	 * Returns an Object array containing all elements in this list.
	 * 
	 * @return an array containing all elements.
	 */
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

	/**
	 * Returns an iterator over the elements in this list.
	 * 
	 * @return an iterator.
	 */
	@Override
	public Iterator<E> iterator() {
		return new DLLIterator();
	}

	/**
	 * Iterator implementation for the doubly linked list.
	 */
	private class DLLIterator implements Iterator<E> {
		private MyDLLNode<E> current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E data = current.getData();
			current = current.getNext();
			return data;
		}
	}

	/**
	 * Returns the number of elements in the list.
	 * 
	 * @return the list size.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Checks if the list is empty.
	 * 
	 * @return true if the list has no elements.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
}
