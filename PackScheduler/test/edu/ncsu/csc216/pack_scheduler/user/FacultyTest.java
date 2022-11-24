/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests the faculty class
 * @author drewb
 *
 */
public class FacultyTest {


	/** Test Student's first name. */
	private String firstName = "first";
	/** Test Student's last name */
	private String lastName = "last";
	/** Test Student's id */
	private String id = "flast";
	/** Test Student's email */
	private String email = "first_last@ncsu.edu";
	/** Test Student's password */
	private String pw = "password";
	/** min courses */
	private static final int MIN_COURSES = 1;
	/** Max courses */
	private static final int MAX_COURSES = 3;
	
	@Test
	void testFacultyValid() {
		Faculty f = assertDoesNotThrow(
				() -> new Faculty(firstName, lastName, id, email, pw, MIN_COURSES),
				"Should not throw exception");

		assertAll("Courses", 
				() -> assertEquals("first", f.getFirstName(), "incorrect first name"), 
				() -> assertEquals("last", f.getLastName(), "incorrect title"),
				() -> assertEquals("flast", f.getId(), "incorrect section"), 
				() -> assertEquals("first_last@ncsu.edu", f.getEmail(), "incorrect email"),
				() -> assertEquals("password", f.getPassword(), "incorrect password"),
				() -> assertEquals(1, f.getMaxCourses(), "incorrect courses"));

	}
	
	
	@Test
	void testFacultyInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(null, lastName, id, email, pw, MIN_COURSES));
		assertEquals("Invalid first name", e1.getMessage());
	}
	
	@Test
	void testSetFirstNameInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, pw, MIN_COURSES).setFirstName(""));
		assertEquals("Invalid first name", e1.getMessage());
	}
	
	@Test
	void testSetLastNameInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, pw, MIN_COURSES).setLastName(""));
		assertEquals("Invalid last name", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, pw, MIN_COURSES).setLastName(null));
		assertEquals("Invalid last name", e2.getMessage());
	}
	
	@Test
	void testSetIdInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, pw, MIN_COURSES).setId(""));
		assertEquals("Invalid id", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, pw, MIN_COURSES).setId(null));
		assertEquals("Invalid id", e2.getMessage());
	}
	
	@Test
	void testSetEmailInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, pw, MIN_COURSES).setEmail(""));
		assertEquals("Invalid email", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, pw, MIN_COURSES).setEmail(null));
		assertEquals("Invalid email", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, pw, MIN_COURSES).setEmail("first.lastgmail.com"));
		assertEquals("Invalid email", e3.getMessage());
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, pw, MIN_COURSES).setEmail("first.last@gmailcom"));
		assertEquals("Invalid email", e4.getMessage());
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, pw, MIN_COURSES).setEmail("firstlast@gmailcom"));
		assertEquals("Invalid email", e5.getMessage());
	}
	
	@Test
	void testMaxCoursesInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, pw, MAX_COURSES).setMaxCourses(20));
		assertEquals("Invalid max courses", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(firstName, lastName, id, email, pw, MAX_COURSES).setMaxCourses(0));
		assertEquals("Invalid max courses", e2.getMessage());
	}
	
	@Test
	void testHashCode() {
		User s1 = new Faculty(firstName, lastName, id, email, pw, MIN_COURSES);
		User s2 = new Faculty(firstName, lastName, id, email, pw, MIN_COURSES);
		User s3 = new Faculty("first2", lastName, id, email, pw, MIN_COURSES);

		


		// Test for the same hash code for the same values
		assertEquals(s1.hashCode(), s2.hashCode());

		// Test for each of the fields
		assertNotEquals(s1.hashCode(), s3.hashCode());

	}

	@Test
	void testEqualsObject() {
		
		User s1 = new Faculty(firstName, lastName, id, email, pw, MIN_COURSES);
		User s2 = new Faculty("difffirst", lastName, id, email, pw, MIN_COURSES);
		User s3 = new Faculty(firstName, "difflast", id, email, pw, MIN_COURSES);
		User s4 = new Faculty(firstName, lastName, "diffid", email, pw, MIN_COURSES);
		User s5 = new Faculty(firstName, lastName, id, "difffirst_last@ncsu.edu", pw, MIN_COURSES);
		User s6 = new Faculty(firstName, lastName, id, email, "diffpw", MIN_COURSES);
		User s7 = new Faculty(firstName, lastName, id, email, pw, 2);
		User s8 = new Faculty(firstName, lastName, id, email, pw, MIN_COURSES);
		Object obj = new Object();
		
//		assertEquals(false, s1.equals(obj));
		assertFalse(s1.equals(obj));
//		assertEquals(true, s1.equals(s1));
		assertTrue(s1.equals(s1));
//		assertEquals(false, s1.equals(s2));
		assertFalse(s1.equals(s2));
//		assertEquals(false, s1.equals(s3));
		assertFalse(s1.equals(s3));
//		assertEquals(false, s1.equals(s4));
		assertFalse(s1.equals(s4));
//		assertEquals(false, s1.equals(s5));
		assertFalse(s1.equals(s5));
//		assertEquals(false, s1.equals(s6));
		assertFalse(s1.equals(s6));
//		assertEquals(false, s1.equals(s7));
		assertFalse(s1.equals(s7));
//		assertEquals(true, s1.equals(s8));
		assertTrue(s1.equals(s8));
	}

	@Test
	void testToString() {
		User f1 = new Faculty(firstName, lastName, id, email, pw, MAX_COURSES);
		assertEquals("first,last,flast,first_last@ncsu.edu,password,3", f1.toString());
	}
}
