package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

class FacultyRecordIOTest {

	/** Valid student records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_faculty_records.txt";

	/**
	 * Tests readFacultyRecords() with valid records.
	 * 
	 * @throws FileNotFoundException if the file does not exist.
	 */
	@Test
	public void testReadFacultyRecords() {
		try {
			LinkedList<Faculty> faculty = FacultyRecordIO.readFacultyRecords(validTestFile);
			assertEquals(8, faculty.size());
			assertEquals("Ashely", faculty.get(0).getFirstName());
			assertEquals("Meadows", faculty.get(1).getLastName());
			assertEquals(1, faculty.get(2).getMaxCourses());
			assertEquals("MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", faculty.get(3).getPassword());
			assertEquals("nascetur.ridiculus.mus@fermentum.net", faculty.get(7).getEmail());

		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}
		try {
			LinkedList<Faculty> faculty;
			faculty = FacultyRecordIO.readFacultyRecords(invalidTestFile);
			assertEquals(0, faculty.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}

	/**
	 * Tests writeFacultyRecords() with Student objects.
	 * 
	 * @throws IOException           if there is a problem writing to faculty
	 *                               records
	 * @throws FileNotFoundException if the specified file does not exist.
	 */
	@Test
	public void testWriteStudentRecords() {
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		faculty.add(new Faculty("Test1", "Faculty1", "unity", "email@domain.com", "password", 1));
		faculty.add(new Faculty("Test2", "Faculty2", "unity2", "email@domain2.com", "password2", 2));
		faculty.add(new Faculty("Test3", "Faculty3", "unity3", "email@domain3.com", "password3", 3));

		assertDoesNotThrow(() -> FacultyRecordIO.writeFacultyRecords("test-files/actual_student_records.txt", faculty));
	}

}
