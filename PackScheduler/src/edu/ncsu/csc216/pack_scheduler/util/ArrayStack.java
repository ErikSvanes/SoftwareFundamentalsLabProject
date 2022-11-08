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
	private ArrayList<E> arrList;

	public ArrayStack(int capacity){
		arrList = new ArrayList<E>();
	}
	
	@Override
	public void push(E element) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E pop() throws EmptyStackException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCapacity(int capacity) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

}
