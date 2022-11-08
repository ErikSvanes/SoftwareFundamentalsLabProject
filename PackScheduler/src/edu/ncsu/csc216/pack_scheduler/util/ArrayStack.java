/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Class to implement Stack with underlying array list.
 * 
 * @author devinmowry
 * @param <E> generic element type
 *
 */
public class ArrayStack<E> implements Stack<E> {
	/** The array list for the stack */
	private ArrayList<E> arrList;
	/** The size of the stack */
	private int size;
	/** The capacity of the stack */
	private int capacity;

	/**
	 * The constructor of the array stack, which takes in the capacity
	 * 
	 * @param capacity the capacity for the stack
	 */
	public ArrayStack(int capacity) {
		arrList = new ArrayList<E>();
		setCapacity(capacity);
		size = 0;
	}

	/**
	 * Method that adds an element onto the top of the stack
	 * 
	 * @param element the element to add to the stack
	 * @throws IllegalArgumentException if there is a problem with the parameter or
	 *                                  the stack is full
	 */
	@Override
	public void push(E element) throws IllegalArgumentException {
		if (element == null) {
			throw new IllegalArgumentException("Invalid element.");
		}
		if (size == capacity) {
			throw new IllegalArgumentException("");
		}
		arrList.add(element);
		size++;
	}

	/**
	 * Method that removes the top element in the stack and returns it
	 * 
	 * @return the element that is removed
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		size--;
		return arrList.remove(size - 1);
	}

	/**
	 * Method that checks whether the stack is empty by checking the size
	 * 
	 * @return if the stack is empty, (if the size is 0)
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Method that returns the size of the stack
	 * 
	 * @return the size of the stack
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Method that sets the capacity to the parameter if it is valid
	 * 
	 * @param capacity the new capacity for the stack
	 * @throws IllegalArgumentException if the parameter is invalid
	 */
	@Override
	public void setCapacity(int capacity) throws IllegalArgumentException {
		if (capacity < size) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		this.capacity = capacity;
	}

}
