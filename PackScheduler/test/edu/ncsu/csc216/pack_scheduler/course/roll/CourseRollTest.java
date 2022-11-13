
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
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
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		CourseRoll roll = c.getCourseRoll();
		CourseRoll cr = new CourseRoll(200, c);
		assertEquals(200, cr.getEnrollmentCap());
		assertEquals(200, cr.getOpenSeats());
	}
	
	/**
	 * Tests setEnrollmentCap()
	 */
	@Test 
	void testSetEnrollmentCap() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		CourseRoll roll = c.getCourseRoll();
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(251, c));
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(5, c));
	}
	
	
	/**
	 * Tests enroll().
	 */
	@Test 
	void testEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		CourseRoll roll = c.getCourseRoll();
		CourseRoll cr = new CourseRoll(200, c);
		cr.enroll(new Student("First", "Last", "fmlast", "fmlast@ncsu.edu", "password"));
		assertEquals(199, cr.getOpenSeats());
		assertThrows(IllegalArgumentException.class, () -> cr.enroll(null));
	}
	
	/**
	 * Tests drop().
	 */
	@Test 
	void testDrop() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		CourseRoll roll = c.getCourseRoll();
		Student s = new Student("First", "Last", "fmlast", "fmlast@ncsu.edu", "password");
		CourseRoll cr = new CourseRoll(200, c);
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
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		CourseRoll roll = c.getCourseRoll();
		Student s = new Student("First", "Last", "fmlast", "fmlast@ncsu.edu", "password");
		CourseRoll cr = new CourseRoll(11, c);
		assertTrue(cr.canEnroll(s));
		cr.enroll(s);
		assertFalse(cr.canEnroll(s));
	}
}
