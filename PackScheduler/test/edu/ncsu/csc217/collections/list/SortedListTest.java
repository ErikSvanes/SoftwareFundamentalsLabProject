package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
/**
 * Tests the sorted lists class
 * @author drewb
 */
public class SortedListTest {
	
	/**
	 * Test testSortedList() method
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		//TODO Test that the list grows by adding at least 11 elements
		//Remember the list's initial capacity is 10
		for(int i = 0; i < 11; i++) {
			list.add(Integer.toString(i));
		}
		assertEquals(11, list.size());
	}
	
	/**
	 * Tests add functionality
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("c");
		assertEquals(1, list.size());
		assertEquals("c", list.get(0));
		
		//TODO Test adding to the front, middle and back of the list
		list.add("a");
		assertEquals("a", list.get(0));
		
		list.add("d");
		assertEquals("d", list.get(2));
		
		
		//TODO Test adding a null element
		assertThrows(NullPointerException.class,
				() -> list.add(null));
		
		
		//TODO Test adding a duplicate element
		assertThrows(IllegalArgumentException.class,
				() -> list.add("d"));
	}
	
	/**
	 * Tests get functionality
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//TODO Test getting an element from an empty list
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(0));
		//TODO Add some elements to the list
		list.add("a");
		list.add("b");
		list.add("c");
		
		//TODO Test getting an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(-1));
		
		//TODO Test getting an element at size
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(list.size()));
		
	}
	
	/**
	 * Tests remove functionality
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test removing from an empty list
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(0));
		
		//TODO Add some elements to the list - at least 4
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		
		//TODO Test removing an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(-1));
		
		//TODO Test removing an element at size
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(list.size()));
		
		//TODO Test removing a middle element
		list.remove(2);
		assertFalse(list.contains("c"));
		
		//TODO Test removing the last element
		list.remove(list.size() - 1);
		assertFalse(list.contains("e"));
		
		//TODO Test removing the first element
		list.remove(0);
		assertFalse(list.contains("a"));
		
		//TODO Test removing the last element
		list.remove(list.size() - 1);
		assertFalse(list.contains("d"));
	}
	
	/**
	 * Tests the functionality of the indexOf method from the sorted list API
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test indexOf on an empty list
		assertEquals(list.indexOf("a"), -1);
		
		//TODO Add some elements
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		
		//TODO Test various calls to indexOf for elements in the list
		//and not in the list
		assertEquals(list.indexOf("a"), 0);
		assertEquals(list.indexOf("b"), 1);
		assertEquals(list.indexOf("c"), 2);
		assertEquals(list.indexOf("v"), -1);
		assertEquals(list.indexOf("g"), -1);

		
		//TODO Test checking the index of null
		assertThrows(NullPointerException.class,
				() -> list.indexOf(null));
		
	}
	
	/**
	 * Tests the functionality of the clear method from the sorted list API
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//TODO Add some elements
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		
		//TODO Clear the list
		list.clear();
		
		//TODO Test that the list is empty
		assertEquals(list.size(), 0);

	}

	/**
	 * Tests the functionality of the isEmpty method from the sorted list API
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test that the list starts empty
		assertTrue(list.isEmpty());
		
		//TODO Add at least one element
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		
		//TODO Check that the list is no longer empty
		assertFalse(list.isEmpty());	}
	
	/**
	 * Tests testContains() method
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test the empty list case
		assertFalse(list.contains("a"));
		//TODO Add some elements
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		//TODO Test some true and false cases
		assertTrue(list.contains("a"));
		assertTrue(list.contains("e"));
		assertFalse(list.contains("x"));
		assertFalse(list.contains("g"));
	}
	
	/**
	 * Tests testEquals() method
	 */
	
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		list1.add("a");
		list1.add("b");
		list2.add("a");
		list2.add("b");
		list3.add("d");
		list3.add("c");
		
		//TODO Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertFalse(list1.equals(list3));
		assertFalse(list2.equals(list3));
	}
	
	/**
	 * Tests testHashCode() method
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		list1.add("a");
		list1.add("b");
		list2.add("a");
		list2.add("b");
		list3.add("d");
		list3.add("c");
		//TODO Test for the same and different hashCodes
		assertEquals(list2.hashCode(), list1.hashCode());
		assertNotEquals(list3.hashCode(), list1.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());		
//		assertTrue(list2.hashCode() == list1.hashCode());
//		assertFalse(list3.hashCode() == list1.hashCode());
//		assertFalse(list2.hashCode() == list3.hashCode());
	}

}
 