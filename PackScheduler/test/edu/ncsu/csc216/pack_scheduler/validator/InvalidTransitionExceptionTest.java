/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Test class for InvalidTransitionException.
 * @author devinmowry
 *
 */
class InvalidTransitionExceptionTest {

	/**
	 * Tests the InvalidTransitionException constructors.
	 */
	@Test
	void testInvalidTransitionException() {
		InvalidTransitionException e1 = new InvalidTransitionException();
		assertEquals(e1.getMessage(), "Invalid FSM Transition.");
		
		InvalidTransitionException e2 = new InvalidTransitionException("Custom invalid FSM Transition");
		assertEquals(e2.getMessage(), "Custom invalid FSM Transition");
	}

}
