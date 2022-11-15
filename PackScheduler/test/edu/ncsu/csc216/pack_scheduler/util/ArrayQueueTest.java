/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the ArrayQueue Class
 * 
 * @author drewb
 * @param <E> The specified element
 */
public class ArrayQueueTest<E> {

	/**
	 * Tests the arrayQueue constructor
	 */
	@Test
	public void testArrayQueue() {
		ArrayQueue<E> as = new ArrayQueue<E>(5);
		assertSame(as.size(), 0);
	}
	
	/**
	 * Tests the arrayEnqueue method
	 */
	@Test
	public void testEnqueue() {
		ArrayQueue<Course> as = new ArrayQueue<Course>(5);
		assertThrows(IllegalArgumentException.class, () -> as.enqueue(null));
		as.enqueue(new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445));
		assertSame(as.size(), 1);
		as.setCapacity(1);
		assertThrows(IllegalArgumentException.class, () -> as.enqueue(new Course("CSC217", "Software Development Fundamentals Lab", "001", 3, "sesmith5", 100, "TH", 1330, 1445)));
		
	}
	
	/**
	 * Tests the setCapacity method
	 */
	@Test
	public void testSetCapacity() {
		ArrayQueue<Course> as = new ArrayQueue<Course>(5);
		assertDoesNotThrow(() -> as.setCapacity(1));
		as.enqueue(new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445));
		assertThrows(IllegalArgumentException.class, () -> as.setCapacity(0));
	}
	
	/**
	 * Tests the dequeue method 
	 */
	@Test
	public void testDequeue() {
		ArrayQueue<Course> as = new ArrayQueue<Course>(5);
		assertThrows(NoSuchElementException.class, () -> as.dequeue());
		Course c = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445);
		Course o = new Course("CSC217", "Software Development Fundamentals Lab", "001", 3, "sesmith5", 100, "TH", 1330, 1445);
		as.enqueue(c);
		as.enqueue(o);
		assertEquals(as.dequeue(), c);
	}
	
	/**
	 * Tests the empty method
	 */
	@Test
	public void testEmpty() {
		ArrayQueue<Course> as = new ArrayQueue<Course>(5);
		assertTrue(as.isEmpty());
		as.enqueue(new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445));
		assertFalse(as.isEmpty());
	}
}
