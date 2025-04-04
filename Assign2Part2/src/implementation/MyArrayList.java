package implementation;
import java.util.Arrays;
import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

public class MyArrayList<E> implements ListADT<E> {
	
	private static final long serialVersionUID = 1L;
    private E[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    
    @SuppressWarnings("unchecked")
    public MyArrayList() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    
    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

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

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
	    if (toAdd == null) throw new NullPointerException("List to add cannot be null.");

		Iterator<? extends E> it = toAdd.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
        return true;
	}

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

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) throw new NullPointerException("Element cannot be null.");
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index.");

        E oldElement = data[index];
        data[index] = toChange;
        return oldElement;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) throw new NullPointerException("Cannot search for null element.");

        for (int i = 0; i < size; i++) {
            if (data[i].equals(toFind)) return true;
        }
        return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) throw new NullPointerException("Target array cannot be null.");

        if (toHold.length < size) {
            return Arrays.copyOf(data, size, (Class<? extends E[]>) toHold.getClass());
        }
        System.arraycopy(data, 0, toHold, 0, size);
        if (toHold.length > size) toHold[size] = null;
        return toHold;
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
        System.arraycopy(data, 0, result, 0, size);
        return result;
	}

	@Override
	public Iterator<E> iterator() {
		return new MyArrayListIterator();
	}

	@Override
	public int size() {
	       return size;
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			data[i] = null;
		}
		size = 0;
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Cannot add null element.");
        ensureCapacity();
        data[size++] = toAdd;
        return true;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index.");
        return data[index];
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index.");

        E removedElement = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null; 
        return removedElement;
	}

	@Override
	public boolean isEmpty() {
        return size == 0;
	}
	
	private class MyArrayListIterator implements Iterator<E> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return data[cursor++];
        }
    }
	

}