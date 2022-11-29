/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * This is the recursive linked list class.
 * 
 * @author Erik
 * @param <E> so that it can use any variable
 *
 */
public class LinkedListRecursive<E> {

	/** The front list node of the list */
	private ListNode front;
	/** The size of the list */
	private int size;

	/**
	 * The constructor
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}

	/**
	 * Returns if the list is empty
	 * 
	 * @return if the list is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the size of the list
	 * 
	 * @return the size of the list
	 */
	public int size() {
		return size;
	}

	/**
	 * This method adds an element to the end of the list.
	 * 
	 * @param element the element to add
	 * @return true if the element is added
	 */
	public boolean add(E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if(isEmpty()) {
			front = new ListNode(element, null);
		}
		size++;
		return front.add(element);
	}

	/**
	 * This method adds an element to the specified index in the list.
	 * 
	 * @param index   the index to add the element at
	 * @param element the element to add to the list
	 */
	public void add(int index, E element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		else if (element == null) {
			throw new NullPointerException();
		}
		else if (contains(element)) {
			throw new IllegalArgumentException();
		}
		else if (index == 0) {
			front = new ListNode(element, front);
		}
		else {
			front.add(index, element);
		}
		size++;
	}

	/**
	 * asdf
	 * 
	 * @param index asdf
	 * @return asdf
	 */
	public E get(int index) {
		return null;
	}

	/**
	 * asdf
	 * 
	 * @param element asdf
	 * @return asdf
	 */
	public boolean remove(E element) {
		return true;
	}

	/**
	 * asdf
	 * 
	 * @param index asdf
	 * @return asdf
	 */
	public E remove(int index) {
		return null;
	}

	/**
	 * asdf
	 * 
	 * @param index   asdf
	 * @param element asdf
	 */
	public void set(int index, E element) {

	}

	/**
	 * This method checks the list to see if it contains the given method from the
	 * parameter.
	 * 
	 * @param element the element to check for in the list
	 * @return true or false whether the element is found in the list
	 */
	public boolean contains(E element) {
		if (front == null && size != 0) {
			throw new IllegalArgumentException();
		}
		else if(front == null) {
			return false;
		}
		return front.contains(element);
	}

	private class ListNode {
		/** The data for the list node */
		public E data;
		/** The next list node pointer */
		public ListNode next;

		/**
		 * The list node constructor which creates a list node and sets the local
		 * variables
		 * 
		 * @param data the data for the list node
		 * @param next the next list node pointer
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}

		/**
		 * This is the private helper method that is called recursively for the contains
		 * method.
		 * 
		 * @param element the element to check the data on
		 * @return true if the element matches the data, and false if the node is null
		 */
		public boolean contains(E element) {
			if (data.equals(element)) {
				return true;
			} else if (next == null) {
				return false;
			}
			return next.contains(element);
		}

		/**
		 * This is the private helper method that is called recursively for the add
		 * method.
		 * 
		 * @param element the element to add at the end of the list
		 * @return true if the element is added
		 */
		public boolean add(E element) {
			if (next == null) {
				next = new ListNode(element, null);
				return true;
			}
			return next.add(element);
		}

		/**
		 * This is the private helper method that is called recursively for the add
		 * method at an index.
		 * 
		 * @param index the index to add the element to
		 * @param element the element to add to the list
		 */
		public void add(int index, E element) {
			if (index == 0) {
				next = new ListNode(element, next);
				return;
			}
			next.add(index - 1, element);
		}
	}
}
