/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Class to override the methods of AbstracrtSequentialList.
 * 
 * @author devinmowry
 * @param <E> for any element type
 *
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	/** The list node at the front of the list */
	private ListNode front;
	/** The list node at the back of the list */
	private ListNode back;
	/** The size of the list */
	private int size;

	/** The linked list constructor which sets everything appropriately */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkedListIterator(index);
	}
	
	@Override
	public void add(int index, E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		super.add(index, element);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public E set(int index, E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		return super.set(index, element);
	}
	
	

	private class ListNode {
		/** The data for this list node */
		public E data;
		/** The next list node */
		public ListNode next;
		/** The previous list node */
		public ListNode prev;

		/**
		 * Constructor for ListNode with only data field
		 * 
		 * @param data the data for this list node
		 */
		public ListNode(E data) {
			this.data = data;
			this.prev = null;
			this.next = null;
		}

		/**
		 * Constructor for ListNode with data, next, and prev fields
		 * 
		 * @param data the data for the list node
		 * @param prev the pointer to the previous list node
		 * @param next the pointer to the next list node
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}

	private class LinkedListIterator implements ListIterator<E> {
		/** The previous list node */
		public ListNode previous;
		/** The next list node */
		public ListNode next;
		/** The index of the previous list node */
		public int previousIndex;
		/** The index of the next list node */
		public int nextIndex;
		/** The last retrieved list node */
		public ListNode lastRetrieved;

		public LinkedListIterator(int index) {
			if (index < 0 || index > size) {
				throw new IndexOutOfBoundsException();
			}
			previous = front;
			next = front.next;
			for (int i = 0; i < index; i++) {
				previous = next;
				next = previous.next;
			}
			previousIndex = index - 1;
			nextIndex = index;
			lastRetrieved = null;
		}

		@Override
		public boolean hasNext() {
			return next.data != null;
		}

		@Override
		public E next() {
			if (next.data == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = next;
			previous = next;
			next = previous.next;
			previousIndex++;
			nextIndex++;
			return previous.data;
		}

		@Override
		public boolean hasPrevious() {
			return previous.data != null;
		}

		@Override
		public E previous() {
			if (previous.data == null) {
				throw new NoSuchElementException();
			}
			previous = previous.prev;
			next = next.prev;
			previousIndex--;
			nextIndex--;
			lastRetrieved = next;
			return next.data;
		}

		@Override
		public int nextIndex() {
			return nextIndex;
		}

		@Override
		public int previousIndex() {
			return previousIndex;
		}

		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			if (lastRetrieved == front) {
				front = lastRetrieved.next;
			} else {
				lastRetrieved.prev.next = lastRetrieved.next;
			}
			lastRetrieved.prev = lastRetrieved.next;
			size--;
		}

		@Override
		public void set(E e) {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			if (e == null) {
				throw new NullPointerException();
			}
			lastRetrieved.data = e;
		}

		@Override
		public void add(E e) { // this is correct
			ListNode current = front;
			if (e == null) {
				throw new NullPointerException();
			}
			ListNode newElement = new ListNode(e, previous, next);
			next.prev = newElement;
			previous.next = newElement;
			previous = newElement;
			previousIndex++;
			nextIndex++;
			size++;
			lastRetrieved = null;
		}
	}
}
