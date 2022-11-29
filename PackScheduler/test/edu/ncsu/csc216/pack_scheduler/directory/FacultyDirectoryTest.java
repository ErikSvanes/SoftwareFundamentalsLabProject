/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * @author devinmowry
 *
 */
class FacultyDirectoryTest {

	/** Valid faculty records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Invalid faculty records */
	private final String invalidTestFile = "test";
	
	/**
	 * Tests StudentDirectory().
	 */
	@Test
	public void testFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
	}
	
	/**
	 * Tests loadFacultyFromFile().
	 */
	@Test
	public void testLoadStudentsFromFile() {
		FacultyDirectory fd = new FacultyDirectory();
			//Test valid file
			fd.loadFacultyFromFile(validTestFile);
			assertEquals(8, fd.getFacultyDirectory().length);
			
			Exception e1 = assertThrows(IllegalArgumentException.class,
					() -> fd.loadFacultyFromFile(invalidTestFile));
			assertEquals("Unable to read file " + invalidTestFile, e1.getMessage());
	}
	
	/**
	 * Tests addFaculty().
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory fd = new FacultyDirectory();
		fd.addFaculty("first", "last", "fmlast", "fmlast@ncsu.edu", "password", "password", 2);
		assertEquals(fd.getFacultyDirectory().length, 1);
	}
	
	/**
	 * Tests removeFaculty().
	 */
	@Test
	public void testRemoveFaculty() {
		FacultyDirectory fd = new FacultyDirectory();
		fd.addFaculty("first", "last", "fmlast", "fmlast@ncsu.edu", "password", "password", 2);
		assertEquals(fd.getFacultyDirectory().length, 1);
		fd.removeFaculty("fmlast");
		assertEquals(fd.getFacultyDirectory().length, 0);
	}
	
	/**
	 * Tests saveFacultyDirectory().
	 */
	@Test
	public void testSaveFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();
		fd.addFaculty("first", "last", "fmlast", "fmlast@ncsu.edu", "password", "password", 2);
		assertDoesNotThrow(() -> fd.saveFacultyDirectory("test-files/my_faculty_directory.txt"));
	}
	
	/**
	 * Tests getFacultyById().
	 */
	@Test
	public void testGetFacultyById() {
		FacultyDirectory fd = new FacultyDirectory();
		fd.addFaculty("first", "last", "fmlast", "fmlast@ncsu.edu", "password", "password", 2);
		Faculty f = new Faculty("first2", "last2", "fmlast2", "fmlast2@ncsu.edu", "password", 2);
		fd.addFaculty("first2", "last2", "fmlast2", "fmlast2@ncsu.edu", "password", "password", 2);
		assertEquals(fd.getFacultyById("fmlast2").getId(), f.getId());
	}
	
	
}
