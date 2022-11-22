package edu.ncsu.csc216.pack_scheduler.util;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.ListIterator;

import org.junit.jupiter.api.Test;

class LinkedListTest {

	/**
	 * Tests the ArrayList constructor
	 */
	@Test
	public void testConstructor() {
		LinkedList<String> list = new LinkedList<String>();
		assertEquals(0, list.size());
	}
	/**
	 * Tests the ArrayList add method
	 */
	@Test
	public void testAdd() {
		LinkedList<String> list = new LinkedList<String>();
		list.add(0, "a");
		list.add(1, "b");
		list.add(2, "c");
		list.add(3, "d");
		list.add(4, "e");
		assertEquals("d", list.get(3));
		list.add(3, "z");
		assertEquals("z", list.get(3));
		assertEquals("d", list.get(4));
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
		ListIterator interator = list.listIterator(2);
		assertTrue(interator.hasNext());
	}
	/**
	 * Tests the ArrayList grow method
	 */
	@Test 
	public void testGrowArray() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		list.add(11);
		list.add(12);
		
		assertEquals(list.size(), 12);
	}
	
	/**
	 * Tests the ArrayList remove method
	 */
	@Test 
	public void testRemove() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		assertEquals(list.size(), 6);
		assertTrue(list.remove(3).equals(4));
		assertEquals(list.size(), 5);
//		for(int i = 0; i< list.size(); i++) {
//			System.out.println(list.get(i));
//		}
//		System.out.println(list.size());
	}
	
	/**
	 * Tests the ArrayList set method
	 */
	@Test
	public void testSet() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		assertThrows(IllegalArgumentException.class,
				() -> list.set(0, 2));
		assertTrue(list.set(0, 7).equals(1));
		assertTrue(list.get(0).equals(7));
	}

}
