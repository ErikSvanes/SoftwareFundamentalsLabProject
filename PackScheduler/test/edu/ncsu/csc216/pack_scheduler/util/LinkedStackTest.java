package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

class LinkedStackTest<E> {

	@Test
	void testLinkedStack() {
		LinkedStack<E> as = new LinkedStack<E>(5);
		assertTrue(as.size() == 0);
	}

	@Test 
	void testPush() {
		LinkedStack<Course> as = new LinkedStack<Course>(5);
		assertThrows(IllegalArgumentException.class, () -> as.push(null));
		as.push(new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445));
		assertTrue(as.size() == 1);
		as.setCapacity(1);
		assertThrows(IllegalArgumentException.class, () -> as.push(new Course("CSC217", "Software Development Fundamentals Lab", "001", 3, "sesmith5", 100, "TH", 1330, 1445)));
		
	}
	
	@Test
	void testPop() {
		LinkedStack<Course> as = new LinkedStack<Course>(5);
		assertThrows(EmptyStackException.class, () -> as.pop());
		Course c = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445);
		as.push(c);
		assertEquals(as.pop(), c);
	}
	
	@Test
	void testIsEmpty() {
		LinkedStack<Course> as = new LinkedStack<Course>(5);
		assertTrue(as.isEmpty());
		as.push(new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445));
		assertFalse(as.isEmpty());
	}
	
	@Test 
	void testSize() {
		//Tested in another method.
	}
	
	@Test 
	void testSetCapacity() {
		LinkedStack<Course> as = new LinkedStack<Course>(5);
		assertDoesNotThrow(() -> as.setCapacity(1));
		as.push(new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445));
		assertThrows(IllegalArgumentException.class, () -> as.setCapacity(0));
	}
}
