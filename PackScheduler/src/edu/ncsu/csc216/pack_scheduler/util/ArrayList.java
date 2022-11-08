/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;
import java.util.AbstractList;

/**
 * ArrayList class
 * @author drewb
 * @param <E> the array list could be of any element
 *
 */
public class ArrayList<E> extends AbstractList<E> {
	/** Initial size of the array*/
	private static final int INIT_SIZE = 10;
	/** The array*/
	@SuppressWarnings("unchecked")
	private E[] list = (E[])(new Object[INIT_SIZE]);
	/** The size of the array */
	private int size = 0;
	
	@Override
	public void add(int idx, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < size; i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException("Cannot add duplicate element");
			}
		}
		if (size == list.length) {
			growArray();
		}
		if (size == 0) {
			list[0] = element;
		}
		else if (idx < size) {
			for (int i = size - 1; i >= idx; i--) {
				list[i + 1] = list[i];
			}
			list[idx] = element;
		}
		else if (idx == size) {
			list[idx] = element;
		}
		size++;
	}
	
	@Override
	public E get(int idx) {
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		return list[idx];
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@SuppressWarnings("unchecked")
	private void growArray() {
		int currentLength = list.length;
		E[] newArr = (E[])(new Object[currentLength * 2]);
		for(int i = 0; i < currentLength; i++) {
			newArr[i] = list[i];
		}
		list = newArr;
	}
	
	@Override 
	public E remove(int idx) {
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		E ind = list[idx];
		for(int i = idx; i < size; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = null;
		size--;
		return ind;
	}
	
	@Override
	public E set(int idx, E element) {
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		E ind = list[idx];
		for(int i = 0; i < size; i++) {
			if(list[i].equals(element)) {
				throw new IllegalArgumentException("Duplicate Element");
			}
		}
		list[idx] = element;
		return ind;
	}
	
}
