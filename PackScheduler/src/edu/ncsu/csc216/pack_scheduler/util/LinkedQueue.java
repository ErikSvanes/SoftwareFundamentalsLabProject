/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Class that implements Queue using an underlying LinkedList. 
 * @author Erik
 * @param <E> generic object type
 *
 */
public class LinkedQueue<E> implements Queue<E> {
	/** The array list for the stack */
	private LinkedAbstractList<E> queueList;
	/** The size of the stack */
	private int size;
	/** The capacity of the stack */
	private int capacity;
	
	/**
	 * Constructor for LinkedQueue.
	 * @param capacity of the collection
	 */
	public LinkedQueue(int capacity){
		queueList = new LinkedAbstractList<E>(capacity);
		//shouldn't have to, but might need to add 
		setCapacity(capacity);
		size = 0;
	}
	
	@Override
	public void enqueue(E element) throws IllegalArgumentException {
		if (element == null) {
			throw new IllegalArgumentException("Invalid element.");
		}
		if (size == capacity) {
			throw new IllegalArgumentException("");
		}
		queueList.add(0, element);
		size++;
		
	}
	@Override
	public E dequeue() throws NoSuchElementException {
		if(queueList.size() == 0) {
			throw new NoSuchElementException();
		}
		size--;
		return queueList.remove(size);
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
