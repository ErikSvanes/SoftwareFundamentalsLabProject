package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentTest {
	
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
	/** maxCredits */
	private static final int MAX_CREDITS = 18;
	
	@Test
	void testStudentValid() {
		Student s = assertDoesNotThrow(
				() -> new Student(firstName, lastName, id, email, pw),
				"Should not throw exception");

		assertAll("Course", 
				() -> assertEquals("first", s.getFirstName(), "incorrect first name"), 
				() -> assertEquals("last", s.getLastName(), "incorrect title"),
				() -> assertEquals("flast", s.getId(), "incorrect section"), 
				() -> assertEquals("first_last@ncsu.edu", s.getEmail(), "incorrect email"),
				() -> assertEquals("password", s.getPassword(), "incorrect password"),
				() -> assertEquals(18, s.getMaxCredits(), "incorrect credits"));
//		Student s = assertDoesNotThrow(new Student(firstName, lastName, id, email, pw, MAX_CREDITS), "Shoul not throw exception.");
//		
//		assertEquals(firstName, s.getFirstName());
//		assertEquals(lastName, s.getLastName());
//		assertEquals(id, s.getId());
//		assertEquals(email, s.getEmail());
//		assertEquals(pw, s.getPassword());
//		assertEquals(MAX_CREDITS, s.getMaxCredits());
	}
	
	
	@Test
	void testStudentInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(null, lastName, id, email, pw, MAX_CREDITS));
		assertEquals("Invalid first name", e1.getMessage());
	}
	
	@Test
	void testSetFirstNameInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, pw, MAX_CREDITS).setFirstName(""));
		assertEquals("Invalid first name", e1.getMessage());
	}
	
	@Test
	void testSetLastNameInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, pw, MAX_CREDITS).setLastName(""));
		assertEquals("Invalid last name", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, pw, MAX_CREDITS).setLastName(null));
		assertEquals("Invalid last name", e2.getMessage());
	}
	
	@Test
	void testSetIdInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, pw, MAX_CREDITS).setId(""));
		assertEquals("Invalid id", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, pw, MAX_CREDITS).setId(null));
		assertEquals("Invalid id", e2.getMessage());
	}
	
	@Test
	void testSetEmailInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, pw, MAX_CREDITS).setEmail(""));
		assertEquals("Invalid email", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, pw, MAX_CREDITS).setEmail(null));
		assertEquals("Invalid email", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, pw, MAX_CREDITS).setEmail("first.lastgmail.com"));
		assertEquals("Invalid email", e3.getMessage());
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, pw, MAX_CREDITS).setEmail("first.last@gmailcom"));
		assertEquals("Invalid email", e4.getMessage());
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, pw, MAX_CREDITS).setEmail("firstlast@gmailcom"));
		assertEquals("Invalid email", e5.getMessage());
	}
	
	@Test
	void testSetPasswordInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, pw, MAX_CREDITS).setPassword(""));
		assertEquals("Invalid password", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, pw, MAX_CREDITS).setPassword(null));
		assertEquals("Invalid password", e2.getMessage());
	}
	
	@Test
	void testMaxCreditsInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, pw, MAX_CREDITS).setMaxCredits(20));
		assertEquals("Invalid max credits", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, pw, MAX_CREDITS).setMaxCredits(2));
		assertEquals("Invalid max credits", e2.getMessage());
	}
	
	@Test
	void testHashCode() {
		User s1 = new Student(firstName, lastName, id, email, pw, MAX_CREDITS);
		User s2 = new Student(firstName, lastName, id, email, pw, MAX_CREDITS);
		User s3 = new Student("first2", lastName, id, email, pw, MAX_CREDITS);

		


		// Test for the same hash code for the same values
		assertEquals(s1.hashCode(), s2.hashCode());

		// Test for each of the fields
		assertNotEquals(s1.hashCode(), s3.hashCode());

	}

	@Test
	void testEqualsObject() {
		
		User s1 = new Student(firstName, lastName, id, email, pw, MAX_CREDITS);
		User s2 = new Student("difffirst", lastName, id, email, pw, MAX_CREDITS);
		User s3 = new Student(firstName, "difflast", id, email, pw, MAX_CREDITS);
		User s4 = new Student(firstName, lastName, "diffid", email, pw, MAX_CREDITS);
		User s5 = new Student(firstName, lastName, id, "difffirst_last@ncsu.edu", pw, MAX_CREDITS);
		User s6 = new Student(firstName, lastName, id, email, "diffpw", MAX_CREDITS);
		User s7 = new Student(firstName, lastName, id, email, pw, 15);
		User s8 = new Student(firstName, lastName, id, email, pw, MAX_CREDITS);
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
		User s1 = new Student(firstName, lastName, id, email, pw, MAX_CREDITS);
		assertEquals("first,last,flast,first_last@ncsu.edu,password,18", s1.toString());
	}

	@Test
	void testCompareTo() {
		Student first = new Student("a", "a", "aaa", email, pw, MAX_CREDITS);
		Student second = new Student("a", "b", "aaa", email, pw, MAX_CREDITS);
		Student third = new Student("a", "a", "ccc", email, pw, MAX_CREDITS);
		Student fourth = new Student("d", "d", "ddd", email, pw, MAX_CREDITS);
		
		assertEquals(0, first.compareTo(first));
		assertEquals(-1, first.compareTo(second));
		assertEquals(-1, first.compareTo(third));
		assertEquals(-1, first.compareTo(fourth));
	}
	
	@Test
	void testGetSchedule() {
		Student s1 = new Student("a", "a", "aaa", email, pw, MAX_CREDITS);
		assertEquals("My Schedule", s1.getSchedule().getTitle());
	}
}
