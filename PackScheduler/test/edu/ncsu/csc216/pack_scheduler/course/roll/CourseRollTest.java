
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
		CourseRoll cr = new CourseRoll(c, 200);
		assertEquals(200, cr.getEnrollmentCap());
		assertEquals(200, cr.getOpenSeats());
	}
	
	/**
	 * Tests setEnrollmentCap()
	 */
	@Test 
	void testSetEnrollmentCap() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(c, 251));
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(c, 5));
	}
	
	
	/**
	 * Tests enroll().
	 */
	@Test 
	void testEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = new CourseRoll(c, 200);
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
		Student s = new Student("First", "Last", "fmlast", "fmlast@ncsu.edu", "password");
		CourseRoll cr = new CourseRoll(c, 200);
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
		Student s = new Student("First", "Last", "fmlast", "fmlast@ncsu.edu", "password");
		CourseRoll cr = new CourseRoll(c, 11);
		assertTrue(cr.canEnroll(s));
		cr.enroll(s);
		assertFalse(cr.canEnroll(s));
	}
	
	@Test
	void testNumWaitlist() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll roll = c.getCourseRoll();
		Student s1 = new Student("a", "a", "a", "a@a.a", "pass");
		Student s2 = new Student("2", "a", "a", "a@a.a", "pass");
		Student s3 = new Student("3", "a", "a", "a@a.a", "pass");
		Student s4 = new Student("4", "a", "a", "a@a.a", "pass");
		Student s5 = new Student("5", "a", "a", "a@a.a", "pass");
		Student s6 = new Student("6", "a", "a", "a@a.a", "pass");
		Student s7 = new Student("7", "a", "a", "a@a.a", "pass");
		Student s8 = new Student("8", "a", "a", "a@a.a", "pass");
		Student s9 = new Student("9", "a", "a", "a@a.a", "pass");
		Student s10 = new Student("10", "a", "a", "a@a.a", "pass");
		Student s11 = new Student("11", "a", "a", "a@a.a", "pass");
		assertEquals(0, roll.getNumberOnWaitlist());
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		assertEquals(0, roll.getNumberOnWaitlist());
		roll.enroll(s11);
		assertEquals(1, roll.getNumberOnWaitlist());
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> roll.enroll(s1));
		assertEquals("Duplcate student.", e.getMessage());
		roll.drop(s1);
		assertEquals(0, roll.getNumberOnWaitlist());
		assertEquals(s11.getSchedule().getScheduledCourses()[0][0], "CSC216");
	} 
}
