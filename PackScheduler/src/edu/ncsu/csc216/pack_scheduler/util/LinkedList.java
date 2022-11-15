/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;

/**
 * Class to override the methods of AbstracrtSequentialList.
 * @author devinmowry
 *
 */
public class LinkedList<E> extends AbstractSequentialList<E> {

	private ListNode front;
	private ListNode back;
	private int size; 
	
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0;
	}
	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	private class ListNode {
		public E data;
		public ListNode next;
		public ListNode prev;
		
		/**
		 * Constructor for ListNode with only data field 
		 * @param data
		 */
		public ListNode(E data) {
			
		}
		
		/**
		 * Constructor for ListNode with data, next, and prev fields
		 * @param data
		 * @param prev
		 * @param next
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			
		}
	}
	
	private class LinkedListIterator implements ListIterator<E> {
		public ListNode previous;
		public ListNode next;
		public int previousIndex;
		public int nextIndex;
		public ListNode lastRetrieved;
		
		public LinkedListIterator(int index) {
			//TODO implement later
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public E next() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public E previous() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void set(E e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void add(E e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
