
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests The CourseCatalog test
 * 
 * @author drewb
 *
 */
public class CourseCatalogTest {
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
//	/** Course instructor id */
//	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course enrollment cap */
	private static final int ENROLLMENT_CAP = 200;
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/**
	 * Tests getCourseCatalog
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile);

		// Get the catalog and make sure contents are correct
		// Name, section, title
		String[][] catalog = cc.getCourseCatalog();
		// Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		// Row 1
		assertEquals("CSC116", catalog[1][0]);
		assertEquals("002", catalog[1][1]);
		assertEquals("Intro to Programming - Java", catalog[1][2]);
		// Row 2
		assertEquals("CSC116", catalog[2][0]);
		assertEquals("003", catalog[2][1]);
		assertEquals("Intro to Programming - Java", catalog[2][2]);
		// Row 3
		assertEquals("CSC116", catalog[3][0]);
		assertEquals("002", catalog[3][1]);
		assertEquals("Intro to Programming - Java", catalog[3][2]);
		// Row 3 (duplicate)
		assertEquals("CSC216", catalog[4][0]);
		assertEquals("001", catalog[4][1]);
		assertEquals("Software Development Fundamentals", catalog[4][2]);
		// Row 4
		assertEquals("CSC216", catalog[5][0]);
		assertEquals("002", catalog[5][1]);
		assertEquals("Software Development Fundamentals", catalog[5][2]);
		// Row 5
		assertEquals("CSC216", catalog[6][0]);
		assertEquals("601", catalog[6][1]);
		assertEquals("Software Development Fundamentals", catalog[6][2]);
		// Row 6
		assertEquals("CSC217", catalog[7][0]);
		assertEquals("202", catalog[7][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[7][2]);
		// Row 7
		assertEquals("CSC217", catalog[8][0]);
		assertEquals("211", catalog[8][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[8][2]);
		// Row 8
		assertEquals("CSC217", catalog[9][0]);
		assertEquals("223", catalog[9][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[9][2]);
		// Row 9
		assertEquals("CSC217", catalog[10][0]);
		assertEquals("601", catalog[10][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[10][2]);
		// Row 10
		assertEquals("CSC226", catalog[11][0]);
		assertEquals("001", catalog[11][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", catalog[11][2]);
		// Row 11
		assertEquals("CSC230", catalog[12][0]);
		assertEquals("001", catalog[12][1]);
		assertEquals("C and Software Tools", catalog[12][2]);
		// Row 12
		assertEquals("CSC316", catalog[13][0]);
		assertEquals("001", catalog[13][1]);
		assertEquals("Data Structures and Algorithms", catalog[13][2]);
	}

	/**
	 * Test CourseCatalog.getCourseFromCatalog().
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile);

		// Attempt to get a course that doesn't exist
		assertNull(cc.getCourseFromCatalog("CSC 492", "001"));

		// Attempt to get a course that does exist
		Activity c = new Course(NAME, TITLE, SECTION, CREDITS, null, ENROLLMENT_CAP, MEETING_DAYS, START_TIME,
				END_TIME);
		assertEquals(c, cc.getCourseFromCatalog("CSC216", "001"));
	}

	/**
	 * Test WolfScheduler.removeCourse().
	 */
	@Test
	public void removeCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile);

		CourseCatalog bc = new CourseCatalog();

		// Attempt to remove from empty schedule
		assertFalse(bc.removeCourseFromCatalog("CSC116", "001"));

		cc.addCourseToCatalog("CSC250", "Test Course", "001", 5, "etamodia", 150, "F", 1800, 1900);
		assertTrue(cc.removeCourseFromCatalog("CSC250", "001"));

		cc.saveCourseCatalog("test-files/actual_course_catalog.txt");
		CourseCatalog cc2 = new CourseCatalog();
		cc2.loadCoursesFromFile("test-files/actual_course_catalog.txt");

		assertEquals(cc.getCourseFromCatalog("CSC250", "001"), cc2.getCourseFromCatalog("CSC250", "001"));

//		//Check that removing a course that doesn't exist when there are 
//		//scheduled courses doesn't break anything
//		assertFalse(cc.removeCourseFromCatalog(5));
//		assertEquals(4, cc.getCourseCatalog().length);
//		assertEquals(4, cc.getCourseCatalog().length);
//		
//		//Remove Exercise
//		assertTrue(cc.removeCourseFromCatalog(1));
//		assertEquals(3, cc.getCourseCatalog().length);
//		assertEquals(3, cc.getCourseCatalog().length);
//		
//		//Remove CSC226
//		assertTrue(cc.removeCourseFromCatalog(1));
//		assertEquals(2, cc.getCourseCatalog().length);
//		assertEquals(2, cc.getCourseCatalog().length);
//		
//		//Remove CSC116
//		assertTrue(cc.removeCourseFromCatalog(1));
//		assertEquals(1, cc.getCourseCatalog().length);
//		assertEquals(1, cc.getCourseCatalog().length);
//		
//		//Remove CSC216
//		assertTrue(cc.removeCourseFromCatalog(0));
//		assertEquals(0, cc.getCourseCatalog().length);
//		assertEquals(0, cc.getCourseCatalog().length);
//		
//		//Check that removing all doesn't break future adds
//		assertTrue(cc.addCourseToCatalog("CSC 230", "001", invalidTestFile, null, invalidTestFile, invalidTestFile, 0, 0));
//		assertEquals(1, cc.getCourseCatalog().length);
//		assertEquals(1, cc.getCourseCatalog().length);
	}

	/**
	 * Test newCourseCatalog()
	 */
	@Test
	public void testNewCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile);

		cc.newCourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);
	}

}
