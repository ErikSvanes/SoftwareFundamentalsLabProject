/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.validator;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidatorFSM;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Test class for CourseNameValidatorFSM
 * @author devinmowry
 *
 */
public class CourseNameValidatorFSMTest {

	/**
	 * Tests CourseNameValidator constructor.
	 */
	@Test 
	public void testCourseNameValidatorFSM() {
		CourseNameValidatorFSM cnv = new CourseNameValidatorFSM();
		boolean a = false;
		try {
			a = cnv.isValid("CSC217");
		} catch (InvalidTransitionException e) {
			e.printStackTrace();
		}
		assertTrue(a);
		assertThrows(InvalidTransitionException.class,
				() -> cnv.isValid("!"));
		assertThrows(InvalidTransitionException.class,
				() -> cnv.isValid("9"));
		try {
			assertFalse(cnv.isValid("l9"));
			assertFalse(cnv.isValid("ll9"));
			assertFalse(cnv.isValid("llll9"));
		} catch (InvalidTransitionException e4) {
			e4.printStackTrace();
		}
		assertThrows(InvalidTransitionException.class,
				() -> cnv.isValid("lllll"));
		assertThrows(InvalidTransitionException.class,
				() -> cnv.isValid("lll9l"));
		assertThrows(InvalidTransitionException.class,
				() -> cnv.isValid("lll99l"));
		try {
			assertTrue(cnv.isValid("lll999l"));
		} catch (InvalidTransitionException e5) {
			e5.printStackTrace();
		}
		assertThrows(InvalidTransitionException.class,
				() -> cnv.isValid("lll9999"));
		assertThrows(InvalidTransitionException.class,
				() -> cnv.isValid("lll999ll"));
		assertThrows(InvalidTransitionException.class,
				() -> cnv.isValid("lll999l9"));
	}
	

}
