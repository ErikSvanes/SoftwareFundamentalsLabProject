/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * The queue interface class
 * 
 * @author Erik
 * @param <E> element of the queue
 *
 */
public interface Queue<E> {

	/**
	 * Method that adds elements to the back of the queue
	 * 
	 * @param element the element to add to the queue
	 * @throws IllegalArgumentExcpetion if there is no more room in the queue
	 */
	void enqueue(E element) throws IllegalArgumentException;
	
	/**
	 * Method that removes elements from the front of the queue
	 * 
	 * @return the element that was removed from the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	E dequeue() throws NoSuchElementException;
	
	/**
	 * Method that returns true if the queue is empty
	 * 
	 * @return true if the queue is empty
	 */
	boolean isEmpty();
	
	/**
	 * Method that returns the size of the queue
	 * 
	 * @return the size of the queue
	 */
	int size();
	
	/**
	 * Method that sets the capacity to the parameter if it is valid
	 * 
	 * @param capacity the capacity parameter
	 * @throws IllegalArgumentException if the parameter is negative or less than size
	 */
	void setCapacity(int capacity) throws IllegalArgumentException;
	
}
