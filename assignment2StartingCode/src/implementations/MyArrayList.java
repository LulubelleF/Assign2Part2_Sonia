package implementations;

import java.util.NoSuchElementException;
import utilities.Iterator;
import utilities.ListADT;

/**
 * A custom implementation of a resizable array-based list.
 * This class implements the ListADT interface.
 *
 * @param <E> the type of elements in this list
 */
public class MyArrayList<E> implements ListADT<E> {

	private static final long serialVersionUID = 1L;
	private E[] data;
	private int size;
	private static final int DEFAULT_CAPACITY = 10;

	/**
	 * Constructs an empty list with an initial capacity of 10.
	 */
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		data = (E[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}

	/**
	 * Ensures there is enough capacity to add new elements.
	 * Doubles the array size if full.
	 */
	private void ensureCapacity() {
		if (size == data.length) {
			E[] newData = (E[]) new Object[data.length * 2];
			for (int i = 0; i < size; i++) {
				newData[i] = data[i];
			}
			data = newData;
		}
	}

	/**
	 * Adds an element at a specific index.
	 *
	 * @param index the index to insert the element
	 * @param toAdd the element to add
	 * @return true if added successfully
	 * @throws NullPointerException     if the element is null
	 * @throws IndexOutOfBoundsException if index is out of bounds
	 */
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if (toAdd == null) throw new NullPointerException("Cannot add null element.");
		if (index < 0 || index > size) throw new IndexOutOfBoundsException("Invalid index.");

		ensureCapacity();
		System.arraycopy(data, index, data, index + 1, size - index);
		data[index] = toAdd;
		size++;
		return true;
	}

	/**
	 * Adds all elements from another list to this list.
	 *
	 * @param toAdd the list of elements to add
	 * @return true if added successfully
	 * @throws NullPointerException if the input list is null
	 */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		if (toAdd == null) throw new NullPointerException("List to add cannot be null.");
		Iterator<? extends E> it = toAdd.iterator();
		while (it.hasNext()) {
			add(it.next());
		}
		return true;
	}

	/**
	 * Removes the first occurrence of a given element.
	 *
	 * @param toRemove the element to remove
	 * @return the removed element or null if not found
	 * @throws NullPointerException if the input is null
	 */
	@Override
	public E remove(E toRemove) throws NullPointerException {
		if (toRemove == null) throw new NullPointerException("Cannot remove null element.");
		for (int i = 0; i < size; i++) {
			if (data[i].equals(toRemove)) {
				return remove(i);
			}
		}
		return null;
	}

	/**
	 * Replaces the element at the specified position.
	 *
	 * @param index the index to replace
	 * @param toChange the new element
	 * @return the element previously at the index
	 * @throws NullPointerException if the new element is null
	 * @throws IndexOutOfBoundsException if index is invalid
	 */
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if (toChange == null) throw new NullPointerException("Element cannot be null.");
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index.");

		E oldElement = data[index];
		data[index] = toChange;
		return oldElement;
	}

	/**
	 * Checks if the list contains a specific element.
	 *
	 * @param toFind the element to search for
	 * @return true if found, false otherwise
	 * @throws NullPointerException if the element is null
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) throw new NullPointerException("Cannot search for null element.");
		for (int i = 0; i < size; i++) {
			if (data[i].equals(toFind)) return true;
		}
		return false;
	}

	/**
	 * Copies the elements of this list into the provided array.
	 *
	 * @param toHold the destination array
	 * @return the filled array
	 * @throws NullPointerException if the destination array is null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E[] toArray(E[] toHold) throws NullPointerException {
		if (toHold == null) throw new NullPointerException("Target array cannot be null.");
		if (toHold.length < size) {
			toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
		}
		System.arraycopy(data, 0, toHold, 0, size);
		if (toHold.length > size) toHold[size] = null;
		return toHold;
	}

	/**
	 * Returns the elements of this list as an Object array.
	 *
	 * @return an array containing all elements
	 */
	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		System.arraycopy(data, 0, result, 0, size);
		return result;
	}

	/**
	 * Returns an iterator over the elements in this list.
	 *
	 * @return an iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new MyArrayListIterator();
	}

	/**
	 * Returns the number of elements in the list.
	 *
	 * @return current size
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Clears all elements in the list.
	 */
	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			data[i] = null;
		}
		size = 0;
	}

	/**
	 * Adds an element to the end of the list.
	 *
	 * @param toAdd the element to add
	 * @return true if added
	 * @throws NullPointerException if the element is null
	 */
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if (toAdd == null) throw new NullPointerException("Cannot add null element.");
		ensureCapacity();
		data[size++] = toAdd;
		return true;
	}

	/**
	 * Returns the element at the specified index.
	 *
	 * @param index the index of the element
	 * @return the element at index
	 * @throws IndexOutOfBoundsException if index is invalid
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index.");
		return data[index];
	}

	/**
	 * Removes the element at the specified index.
	 *
	 * @param index the index of the element
	 * @return the removed element
	 * @throws IndexOutOfBoundsException if index is invalid
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index.");
		E removedElement = data[index];
		System.arraycopy(data, index + 1, data, index, size - index - 1);
		data[--size] = null;
		return removedElement;
	}

	/**
	 * Checks if the list is empty.
	 *
	 * @return true if list has no elements
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Inner class for iterating over MyArrayList elements.
	 */
	private class MyArrayListIterator implements Iterator<E> {
		private int cursor = 0;

		/**
		 * Checks if the iteration has more elements.
		 *
		 * @return true if more elements are available
		 */
		@Override
		public boolean hasNext() {
			return cursor < size;
		}

		/**
		 * Returns the next element in the iteration.
		 *
		 * @return the next element
		 * @throws NoSuchElementException if no more elements
		 */
		@Override
		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			return data[cursor++];
		}
	}
}
