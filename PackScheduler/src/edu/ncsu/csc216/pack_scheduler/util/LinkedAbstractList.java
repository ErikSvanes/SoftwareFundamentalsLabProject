/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * The linked list class
 * 
 * @author drewb
 *
 * @param <E> Element of the linkedlist
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** The front of the list */
	ListNode front;
	/** The size of the list */
	int size;
	/** The capacity of the list */
	int capacity;
	/** Points to the last node in the list */
	ListNode back;

	/**
	 * The constructor for a linked abstract list
	 * 
	 * @param capacity the capacity of the list
	 */
	public LinkedAbstractList(int capacity) {
		front = null;
		back = null;
		size = 0;
		setCapacity(capacity);
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * This method adds the element parameter to the specified index in the list
	 * 
	 * @param idx     the index to add the element to
	 * @param element the element to add to the list
	 */
	public void add(int idx, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (idx > size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (size == capacity) {
			throw new IllegalArgumentException("size equals capacity");
		}
		ListNode current = front;
		while (current != null) {
			if (element.equals(current.data)) {
				throw new IllegalArgumentException("No duplicate elements.");
			}
			current = current.next;
		}
		if (front == null) {
			front = new ListNode(element);
			// should reference the same thing
			back = front;
			size++;
			return;
		} else if (idx == 0) {
			front = new ListNode(element, front);
			size++;
			return;
		}
		// adding constant time case for adding to back of list, avoid traversal
		else if (idx == size) {
			back.next = new ListNode(element);
			back = back.next;
			size++;
			return;
		}
		current = front;
		for (int i = 0; i < idx - 1; i++) {
			current = current.next;
		}
		ListNode savedReference = current.next;
		current.next = new ListNode(element);
		current.next.next = savedReference;
		size++;
	}

	/**
	 * This method gets the element at the specified index
	 * 
	 * @param idx the index of the element
	 * @return the element at the index
	 */
	public E get(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current = front;
		for (int i = 0; i < idx; i++) {
			current = current.next;
		}
		return (E) current.data;
	}

	/**
	 * Method to remove an element at a given index. Returns the removed element.
	 * 
	 * @param idx the index to remove
	 * @return the data of the removed element
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= size || front == null) {
			throw new IndexOutOfBoundsException();
		}
		if (idx == 0) {
			E returnValue = front.data;
			front = front.next;
			size--;
			return returnValue;
		}

		ListNode current = front;
		for (int i = 0; i < idx - 1; i++) {
			current = current.next;
		}
		ListNode returnValue = current.next;
		current.next = current.next.next;
		//update back if removing the last element
		if (idx == size - 1) {
			back = current;
		}
		size--;
		return (E) returnValue.data;

	}

	/**
	 * Method to set an element.
	 * 
	 * @param idx     index to set
	 * @param element to set the index data to
	 * @return the data from the overwritten element
	 */
	public E set(int idx, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current = front;
		while (current != null) {
			if (element.equals(current.data)) {
				throw new IllegalArgumentException("No duplicate elements.");
			}
			current = current.next;
		}
		current = front;
		for (int i = 0; i < idx; i++) {
			current = current.next;
		}
		E returnValue = current.data;
		current.data = element;
		System.out.println(current.data);
		System.out.println(returnValue);
		return returnValue;

	}

	private class ListNode {
		/** The data variable for each node */
		private E data;
		/** The next list node for each node */
		private ListNode next;

		public ListNode(E data) {
			this.data = data;
			this.next = null;
		}

		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

	/**
	 * Method to set the capacity
	 * 
	 * @param capacity the capacity of the list
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		this.capacity = capacity;
	}

}
