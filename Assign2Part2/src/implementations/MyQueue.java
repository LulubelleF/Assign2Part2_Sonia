package implementations;

import exceptions.EmptyQueueException;
import utilities.Iterator;
import utilities.QueueADT;

/**
 * A custom queue implementation using a doubly linked list.
 *
 * @param <E> The type of elements stored in the queue.
 */
public class MyQueue<E> implements QueueADT<E> {
	private static final long serialVersionUID = 1L;

	private MyDLL<E> list;

	/**
	 * Constructs an empty queue.
	 */
	public MyQueue() {
		list = new MyDLL<>();
	}

	@Override
	public void enqueue(E toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException("Cannot add null to the queue.");
		}
		list.add(toAdd);
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Queue is empty.");
		}
		return list.remove(0);
	}

	@Override
	public E peek() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Queue is empty.");
		}
		return list.get(0);
	}

	@Override
	public void dequeueAll() {
		list.clear();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		return list.contains(toFind);
	}

	@Override
	public int search(E toFind) throws NullPointerException {
		if (toFind == null) {
			throw new NullPointerException("Cannot search for null.");
		}
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(toFind)) {
				return i + 1; // 1-based position
			}
		}
		return -1;
	}

	@Override
	public boolean equals(QueueADT<E> that) {
		if (that == null || this.size() != that.size()) {
			return false;
		}

		Object[] thisArray = this.toArray();
		Object[] thatArray = that.toArray();

		for (int i = 0; i < thisArray.length; i++) {
			if (!thisArray[i].equals(thatArray[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		return list.toArray(holder);
	}

	@Override
	public boolean isFull() {
		return false; // MyDLL is dynamically sized
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}
}
