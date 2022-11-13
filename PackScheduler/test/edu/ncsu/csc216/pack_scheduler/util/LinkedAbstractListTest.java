/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests the LinkedAbstracTist class
 * @author drewb
 *
 */
public class LinkedAbstractListTest {
	/**
	 * Tests the ArrayList constructor
	 */
	@Test
	public void testConstructor() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		assertEquals(0, list.size());
	}
	/**
	 * Tests the ArrayList add method
	 */
	@Test
	public void testAdd() {
		LinkedAbstractList<Integer> list = new LinkedAbstractList<Integer>(5);
		list.add(0, 2);
		list.add(0, 1);
		list.add(2, 4);
		list.add(3, 5);
		list.add(2, 3);
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> list.add(1, 1));
		assertEquals("size equals capacity", e.getMessage());
		assertSame(list.get(0), 1);
		assertSame(list.get(1), 2);
		assertSame(list.get(2), 3);
		assertSame(list.get(3), 4);
		assertSame(list.get(4), 5);
	}
	
	/**
	 * Tests the ArrayList remove method
	 */
	@Test 
	public void testRemove() {
		LinkedAbstractList<Integer> list = new LinkedAbstractList<Integer>(10);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		assertEquals(list.size(), 6);
		assertTrue(list.remove(3).equals(4));
		assertEquals(list.size(), 5);
		assertTrue(list.remove(4).equals(6));
		assertEquals(list.size(), 4);
	}
	
	/**
	 * Tests the ArrayList set method
	 */
	@Test
	public void testSet() {
		LinkedAbstractList<Integer> list = new LinkedAbstractList<Integer>(10);
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
