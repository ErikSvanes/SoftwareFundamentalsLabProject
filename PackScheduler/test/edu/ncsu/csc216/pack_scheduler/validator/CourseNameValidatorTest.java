/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.validator;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Test class for CourseNameValidator
 * @author devinmowry
 *
 */
public class CourseNameValidatorTest {

	/**
	 * Tests CourseNameValidator constructor.
	 * @throws InvalidTransitionException if the transition is not allowed
	 */
	@Test 
	public void testCourseNameValidator() throws InvalidTransitionException {
		CourseNameValidator cnv = new CourseNameValidator();
		assertTrue(cnv.isValid("C217"));
		assertTrue(cnv.isValid("CS217"));
		assertTrue(cnv.isValid("CSC217"));
		assertTrue(cnv.isValid("CSC217A"));
		
		assertThrows(InvalidTransitionException.class,
				() -> cnv.isValid("!"));
		assertThrows(InvalidTransitionException.class,
				() -> cnv.isValid("0"));
		//Invalid but no illegal transitions
		assertFalse(cnv.isValid("l"));
		assertFalse(cnv.isValid("ll"));
		assertFalse(cnv.isValid("lll"));
		assertFalse(cnv.isValid("llll"));
		assertFalse(cnv.isValid("l0"));
		assertFalse(cnv.isValid("ll0"));
		assertFalse(cnv.isValid("lll0"));
		assertFalse(cnv.isValid("llll0"));
		
		//Invalid with illegal transitions.
		assertThrows(InvalidTransitionException.class,
				() -> cnv.isValid("lllll"));
		assertThrows(InvalidTransitionException.class,
				() -> cnv.isValid("lll9l"));
		assertThrows(InvalidTransitionException.class,
				() -> cnv.isValid("lll99l"));
		assertThrows(InvalidTransitionException.class,
				() -> cnv.isValid("lll9999"));
		assertThrows(InvalidTransitionException.class,
				() -> cnv.isValid("lll999ll"));
		assertThrows(InvalidTransitionException.class,
				() -> cnv.isValid("lll999l9"));
	}
}
