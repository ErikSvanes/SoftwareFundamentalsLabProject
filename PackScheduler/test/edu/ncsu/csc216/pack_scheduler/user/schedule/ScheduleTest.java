package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

class ScheduleTest {

	/** 
	 * Test for addCourseToSchedule().
	 */
	@Test
	void testAddCourseToSchedule() {
		Schedule sched = new Schedule();
		Course c = new Course("CSC216", "Software Development Fundamentals", "002", 3, "dmmowry", 100, "TH", 1145, 1300);
		Course o = new Course("CSC217", "Software Development Fundamentals Lab", "014", 3, "dmmowry", 100, "T", 1720, 1910);
		Course u = new Course("CSC116", "Intro to Java", "002", 3, "dmmowry", 100, "F", 1145, 1300);
		//duplicate course
		Course r = new Course("CSC216", "Software Development Fundamentals", "002", 3, "dmmowry", 100, "TH", 1445, 1600);
		//conflicting course times 
		Course s = new Course("CSC226", "Discrete Mathematics", "002", 3, "dmmowry", 100, "TH", 1145, 1300);
		
		sched.addCourseToSchedule(c);
		sched.addCourseToSchedule(o);
		sched.addCourseToSchedule(u);
		
		assertThrows(IllegalArgumentException.class, () -> sched.addCourseToSchedule(r));
		assertThrows(IllegalArgumentException.class, () -> sched.addCourseToSchedule(s));
		
		String[][] stringSched = sched.getScheduledCourses();
		assertEquals(stringSched[0][0], "CSC216");
		assertEquals(stringSched[1][0], "CSC217");
		assertEquals(stringSched[2][0], "CSC116");
		
		assertEquals(stringSched[0][2], "Software Development Fundamentals");
		assertEquals(stringSched[1][2], "Software Development Fundamentals Lab");
		assertEquals(stringSched[2][2], "Intro to Java");
	}
	
	/**
	 * Tests removeCourseFromSchedule().
	 */
	@Test
	void testRemoveCourseFromSchedule() {
		Schedule sched = new Schedule();
		Course c = new Course("CSC216", "Software Development Fundamentals", "002", 3, "dmmowry", 100, "TH", 1145, 1300);
		Course o = new Course("CSC217", "Software Development Fundamentals Lab", "014", 3, "dmmowry", 100, "T", 1720, 1910);
		Course u = new Course("CSC116", "Intro to Java", "002", 3, "dmmowry", 100, "F", 1145, 1300);
		
		sched.addCourseToSchedule(c);
		sched.addCourseToSchedule(o);
		sched.addCourseToSchedule(u);
		
		String[][] stringSched = sched.getScheduledCourses();
		assertEquals(stringSched[0][0], "CSC216");
		assertEquals(stringSched[1][0], "CSC217");
		assertEquals(stringSched[2][0], "CSC116");
		
		assertTrue(sched.removeCourseFromSchedule(o));
		stringSched = sched.getScheduledCourses();
		assertEquals(stringSched[1][0], "CSC116");
		
	}
	
	/**
	 * Tests setTitle().
	 */
	@Test
	void testSetTitle() {
		Schedule s = new Schedule();
		assertEquals("My Schedule", s.getTitle());
		s.setTitle("New Title");
		assertEquals("New Title", s.getTitle());
	}
	
	/**
	 * Tests getScheduleCredits().
	 */
	@Test 
	void testGetScheduleCredits() {
		Schedule sched = new Schedule();
		sched.addCourseToSchedule(new Course("CSC216", "Software Development Fundamentals", "002", 3, "dmmowry", 100, "TH", 1145, 1300));
		sched.addCourseToSchedule(new Course("CSC217", "Software Development Fundamentals Lab", "014", 3, "dmmowry", 100, "T", 1720, 1910));
		sched.addCourseToSchedule(new Course("CSC116", "Intro to Java", "002", 3, "dmmowry", 100, "F", 1145, 1300));
		assertEquals(9, sched.getScheduleCredits());
	}
	
	/**
	 * Tests canAdd().
	 */
	@Test 
	void testCanAdd() {
		Schedule sched = new Schedule();
		assertFalse(sched.canAdd(null));
		assertTrue(sched.canAdd(new Course("CSC216", "Software Development Fundamentals", "002", 3, "dmmowry", 100, "TH", 1145, 1300)));
		sched.addCourseToSchedule(new Course("CSC216", "Software Development Fundamentals", "002", 3, "dmmowry", 100, "TH", 1145, 1300));
		assertFalse(sched.canAdd(new Course("CSC216", "Software Development Fundamentals", "002", 3, "dmmowry", 100, "TH", 1145, 1300)));
		assertFalse(sched.canAdd(new Course("CSC217", "Software Development Fundamentals Lab", "001", 3, "dmmowry", 100, "TH", 1145, 1300)));
	}
	
}
