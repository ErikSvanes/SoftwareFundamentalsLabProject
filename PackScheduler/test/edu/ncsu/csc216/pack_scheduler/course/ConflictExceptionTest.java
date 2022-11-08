/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/**
 * Test class to ensure the custom exceptions set up by Conflict exception construct the proper exception type with the proper exception message.
 * @author Chance Cheatham
 */
class ConflictExceptionTest {

	/**
	 * Test method for parameterless conflict exception constructor.
	 */
	@Test
	public void testConflictException() {
		ConflictException ce = new ConflictException();
		assertEquals("Schedule conflict.", ce.getMessage());
	}

	/**
	 * Test method for parameterized conflict exception constructor.
	 */
	@Test
	public void testConflictExceptionString() {
	    ConflictException ce = new ConflictException("Schedule conflict.");
	    assertEquals("Schedule conflict.", ce.getMessage());
	}

}
