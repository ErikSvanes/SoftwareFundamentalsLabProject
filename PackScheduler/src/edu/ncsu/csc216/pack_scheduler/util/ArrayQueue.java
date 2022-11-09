/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * @author Erik
 * @param <E> element of the queue
 *
 */
public class ArrayQueue<E> {

	/** The array list for the stack */
	private ArrayList<E> arrList;
	/** The size of the stack */
	private int size;
	/** The capacity of the stack */
	private int capacity;
	
	public ArrayQueue() {
		arrList = new ArrayList<E>();
		setCapacity(capacity);
		size = 0;
	}
	
	/**
	 * Method that adds elements to the back of the queue
	 * 
	 * @param element the element to add to the back of the queue
	 */
	public void enqueue(E element) throws IllegalArgumentException {
		if (element == null) {
			throw new IllegalArgumentException("Invalid element.");
		}
		if (size == capacity) {
			throw new IllegalArgumentException("");
		}
		
	}
	
	/**
	 * Method that sets the capacity to the parameter if it is valid
	 * 
	 * @param capacity the new capacity for the stack
	 * @throws IllegalArgumentException if the parameter is invalid
	 */
	// i feel like we should have @Override here but it doesn't like it //
	public void setCapacity(int capacity) throws IllegalArgumentException {
		
	}
	
}
