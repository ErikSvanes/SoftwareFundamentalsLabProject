/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * The stack interface class
 * @author drewb
 * @param <E> Element of the stack
 *
 */
public interface Stack<E> {
	
	/**
	 * Adds element to the top of the stack
	 * @param element to add
	 * @throws IllegalArgumentException if there is no room / capacity is reached
	 */
	void push (E element) throws IllegalArgumentException;
	
	/**
	 * Removes an returns an element at the top of the stack
	 * @return the element at the top of the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	E pop() throws EmptyStackException;
	
	/**
	 * Checks if the stack is empty
	 * @return true if stack is empty
	 */
	boolean isEmpty();
	
	/**
	 * Method to get the size of the stack
	 * @return number of elements in stack
	 */
	int size();
	
	/**
	 * Sets the stacks capacity
	 * @param capacity the stacks capacity
	 * @throws IllegalArgumentException if the parameter is negative or less than number of elements in stack
	 */
	void setCapacity(int capacity) throws IllegalArgumentException;

}
