package implementations;

import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.StackADT;

public class MyStack<E> implements StackADT<E>, Serializable {

	private static final long serialVersionUID = 1L;
	private MyArrayList<E> list;

	public MyStack() {
		list = new MyArrayList<>();
	}

	@Override
	public void push(E toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException("Cannot push null onto the stack");
		}
		list.add(toAdd);
	}

	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		int index = list.size() - 1;
		return list.remove(index);
	}

	@Override
	public E peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return list.get(list.size() - 1);
	}

	@Override
	public void clear() {
		list.clear(); // Clears the stack
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty(); // Returns true if stack is empty
	}

	@Override
	public Object[] toArray() {
		Object[] returnArray = new Object[list.size()]; // Create an array with the same size as the stack
		int index = 0;

		// Copy elements from the list to the return array in reverse order (LIFO)
		for (int i = list.size() - 1; i >= 0; i--) {
			returnArray[index++] = list.get(i); // List is accessed in reverse order
		}

		return returnArray; // Return the array with LIFO order
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		if (holder == null) {
			throw new NullPointerException("Holder array cannot be null");
		}

		// If the provided array is smaller than the stack size, create a new one
		if (holder.length < size()) {
			// Create a new array with the required size if the provided array is too small
			holder = (E[]) java.lang.reflect.Array.newInstance(holder.getClass().getComponentType(), size());
		}

		// Copy elements from the stack (LIFO order) into the provided array
		for (int i = 0; i < size(); i++) {
			holder[i] = list.get(size() - 1 - i); // Insert elements in reverse order (LIFO)
		}

		// If the holder array is larger than the size of the stack, set the extra
		// elements to null
		if (holder.length > size()) {
			holder[size()] = null;
		}

		return holder; // Return the array
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) {
			throw new NullPointerException("Cannot search for null in the stack");
		}
		return list.contains(toFind); // Checks if an element exists in the stack
	}

	@Override
	public int search(Object toFind) {
		if (toFind == null) {
			throw new NullPointerException("Cannot search for null in the stack.");
		}

		// Manually search for the element in the list
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(toFind)) {
				// Convert to 1-based index from the top of the stack
				return list.size() - i;
			}
		}

		// If element is not found, return -1
		return -1;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int currentIndex = size() - 1; // Start from the top of the stack

			@Override
			public boolean hasNext() {
				return currentIndex >= 0; // Check if there are more elements to iterate
			}

			@Override
			public E next() {
				if (!hasNext()) {
					throw new NoSuchElementException("No more elements in the stack.");
				}
				return list.get(currentIndex--); // Return the element and move to the previous one
			}
		};
	}

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

		return true; // Returns true if both stacks are equal
	}

	@Override
	public int size() {
		return list.size(); // Returns the number of elements in the stack
	}

	@Override
	public boolean stackOverflow() {
		return false; // Since MyArrayList is dynamic, there is no stack overflow
	}
}
