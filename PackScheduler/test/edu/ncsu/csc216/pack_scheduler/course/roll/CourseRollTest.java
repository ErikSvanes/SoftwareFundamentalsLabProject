
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests the CourseRoll class. 
 * @author drewb
 *
 */
public class CourseRollTest {

	/**
	 * Tests CourseRoll constructor. Also tests setEnrollmentCap for valid number.
	 */
	@Test 
	void testCourseRoll() {
		CourseRoll cr = new CourseRoll(200);
		assertEquals(200, cr.getEnrollmentCap());
		assertEquals(200, cr.getOpenSeats());
	}
	
	/**
	 * Tests setEnrollmentCap()
	 */
	@Test 
	void testSetEnrollmentCap() {
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(251));
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(5));
	}
	
	
	/**
	 * Tests enroll().
	 */
	@Test 
	void testEnroll() {
		CourseRoll cr = new CourseRoll(200);
		cr.enroll(new Student("First", "Last", "fmlast", "fmlast@ncsu.edu", "password"));
		assertEquals(199, cr.getOpenSeats());
		assertThrows(IllegalArgumentException.class, () -> cr.enroll(null));
	}
	
	/**
	 * Tests drop().
	 */
	@Test 
	void testDrop() {
		Student s = new Student("First", "Last", "fmlast", "fmlast@ncsu.edu", "password");
		CourseRoll cr = new CourseRoll(200);
		cr.enroll(s);
		assertEquals(199, cr.getOpenSeats());
		assertThrows(IllegalArgumentException.class, () -> cr.drop(null));
		cr.drop(s);
		assertEquals(200, cr.getOpenSeats());
	}
	
	/**
	 * Tests canEnroll().
	 */
	@Test 
	void testCanEnroll() {
		Student s = new Student("First", "Last", "fmlast", "fmlast@ncsu.edu", "password");
		CourseRoll cr = new CourseRoll(11);
		assertTrue(cr.canEnroll(s));
		cr.enroll(s);
		assertFalse(cr.canEnroll(s));
	}
}
