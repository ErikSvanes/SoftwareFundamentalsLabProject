/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Class that implements Queue using an underlying ArrayList. 
 * @author Erik
 * @param <E> element of the queue
 *
 */
public class ArrayQueue<E> implements Queue<E> {

	/** The array list for the stack */
	private ArrayList<E> arrList;
	/** The size of the stack */
	private int size;
	/** The capacity of the stack */
	private int capacity;
	
	/**
	 * Constructor for ArrayQueue.
	 * @param capacity of the collection
	 */
	public ArrayQueue(int capacity) {
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
		arrList.add(element);
		size++;
	}
	
	@Override
	public void setCapacity(int capacity) throws IllegalArgumentException {
		if (capacity <= 0 || capacity < size) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		this.capacity = capacity;
	}

	@Override
	public E dequeue() throws NoSuchElementException {
		if(arrList.size() == 0) {
			throw new NoSuchElementException();
		}
		size--;
		return arrList.remove(0);
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}
	
}
