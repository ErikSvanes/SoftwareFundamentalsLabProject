package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedListTest {

	/**
	 * Tests the ArrayList constructor
	 */
	@Test
	public void testConstructor() {
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(0, list.size());
	}
	/**
	 * Tests the ArrayList add method
	 */
	@Test
	public void testAdd() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0, 1);
		list.add(1, 2);
		list.add(2, 4);
		list.add(3, 5);
		list.add(2, 3);
		assertSame(list.get(0), 1);
		assertSame(list.get(1), 2);
		assertSame(list.get(2), 3);
		assertSame(list.get(3), 4);
		assertSame(list.get(4), 5);
	}
	/**
	 * Tests the ArrayList grow method
	 */
	@Test 
	public void testGrowArray() {
		ArrayList<Integer> list = new ArrayList<Integer>();
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
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		assertEquals(list.size(), 6);
		assertTrue(list.remove(3).equals(4));
		assertEquals(list.size(), 5);
	}
	
	/**
	 * Tests the ArrayList set method
	 */
	@Test
	public void testSet() {
		ArrayList<Integer> list = new ArrayList<Integer>();
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
