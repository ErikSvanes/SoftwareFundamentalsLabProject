/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Class to implement Stack with underlying array list. 
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
	public ArrayStack(int capacity){
		arrList = new ArrayList<E>();
		setCapacity(capacity);
		size = 0;
	}
	
	@Override
	public void push(E element) throws IllegalArgumentException {
		if (element == null) {
			throw new IllegalArgumentException("Invalid element.");
		}
		if (size == capacity) {
			throw new IllegalArgumentException("");
		}
		arrList.add(element);
	}

	@Override
	public E pop() throws EmptyStackException {
		return arrList.remove(size - 1);
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
		if (capacity < size) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		this.capacity = capacity;
	}

}
