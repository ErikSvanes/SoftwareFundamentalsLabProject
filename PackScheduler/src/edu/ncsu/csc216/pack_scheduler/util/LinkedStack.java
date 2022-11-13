/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Class to implement Stack with underlying linked list. 
 * @author devinmowry
 * @param <E> generic element type
 *
 */
public class LinkedStack<E> implements Stack<E> {
	/** The array list for the stack */
	private LinkedAbstractList<E> stackList;
	/** The size of the stack */
	private int size;
	/** The capacity of the stack */
	private int capacity;
	
	/**
	 * Constructs the linked stack list
	 * @param capacity the capacity of the list
	 */
	public LinkedStack(int capacity) {
		stackList = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
		size = 0;	
	}
	
	@Override
	public void push(E element) throws IllegalArgumentException {
		if (element == null) {
			throw new IllegalArgumentException("Invalid element.");
		}
		if (size == capacity) {
			throw new IllegalArgumentException();
		}
		stackList.add(element);
		size++;
	}

	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		size--;
		return stackList.remove(size);
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void setCapacity(int capacity) throws IllegalArgumentException {
		if (capacity <= 0 || capacity < size) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		this.capacity = capacity;
	}

}
