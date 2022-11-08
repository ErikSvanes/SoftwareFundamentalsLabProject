package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Provides test cases to test the functionality of all aspects of the Student Record IO class.
 * Sets up student record test files before each test is run to ensure consistency.
 * Tests that readStudentRecords only reads valid student records.
 * Checks that writeStudentRecords writes text files properly by comparing output files.
 * @author Chance Cheatham
 */
class StudentRecordIOTest {
	
	/** Valid student records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_student_records.txt";
	
	/** Expected results for valid students in student_records.txt - line 1 */	
	private final String validStudent1 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,15";
	/** Expected results for valid students in student_records.txt - line 2 */
	private final String validStudent2 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,4";
	/** Expected results for valid students in student_records.txt - line 3 */
	private final String validStudent3 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,14";
	/** Expected results for valid students in student_records.txt - line 4 */
	private final String validStudent4 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,18";
	/** Expected results for valid students in student_records.txt - line 5 */
	private final String validStudent5 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,12";
	/** Expected results for valid students in student_records.txt - line 6 */	
	private final String validStudent6 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,3";
	/** Expected results for valid students in student_records.txt - line 7 */
	private final String validStudent7 = "Lane,Berg,lberg,sociis@non.org,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,14";
	/** Expected results for valid students in student_records.txt - line 8 */
	private final String validStudent8 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,17";
	/** Expected results for valid students in student_records.txt - line 9 */
	private final String validStudent9 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,11";
	/** Expected results for valid students in student_records.txt - line 10 */
	private final String validStudent10 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,5";
	
	/** Array to hold expected results */
	private final String [] validStudents = {validStudent4, validStudent7, validStudent5, validStudent6, validStudent3, 
			validStudent9, validStudent1, validStudent10, validStudent2, validStudent8};
	
	/** String to hold a hashed password. */
	private String hashPW;
	/** String to hold the hash algorithm. */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Resets student_records.txt for use in other tests.
	 * @throws NoSuchAlgorithException iff unable to create a hash.
	 */
	@BeforeEach
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = Base64.getEncoder().encodeToString(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}

	/**
	 * Tests readStudentRecords() with valid records.
	 * @throws FileNotFoundException if the file does not exist.
	 */
	@Test
	public void testReadStudentRecords() {
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords(validTestFile);
			assertEquals(10, students.size());
			
			for (int i = 0; i < validStudents.length; i++) {
				assertEquals(validStudents[i], students.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}
		try {
			SortedList<Student> students;
			students = StudentRecordIO.readStudentRecords(invalidTestFile);
			assertEquals(0, students.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}
	
	/**
	 * Tests writeStudentRecords() with Student objects.
	 * @throws IOException if there is a problem writing to student records
	 * @throws FileNotFoundException if the specified file does not exist.
	 */
	@Test
	public void testWriteStudentRecords() {
		SortedList<Student> studentList = new SortedList<Student>();
		studentList.add(new Student("Test", "Student", "unity", "email@domain.com", "password", 3));
		studentList.add(new Student("Test2", "Student2", "unity2", "email@domain2.com", "password2", 4));
		studentList.add(new Student("Test3", "Student3", "unity3", "email@domain3.com", "password3", 5));
		try {
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", studentList);
			checkFiles("test-files/expected_studentIO_records.txt", "test-files/actual_student_records.txt");
			
		} catch (IOException e) {
			fail("Cannot write to course records file");
		}
	}
	
//	/**
//	 * Tests writing to a file without proper permissions
//	 * @throws IOException if permissions to write to chosen file path are not valid.
//	 */
//	@Test
//	public void testWriteStudentRecordsNoPermissions() {
//		SortedList<Student> students = new SortedList<Student>();
//		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
//		
//		Exception exception = assertThrows(IOException.class, 
//				() -> StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students));
//		assertEquals("/home/sesmith5/actual_student_records.txt (No such file or directory)", exception.getMessage());
//	}

	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 * @throws IOException if the file names do not exist.
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
			 Scanner actScanner = new Scanner(new FileInputStream(actFile));) {
			
			while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals(exp, act); 
				//The third argument helps with debugging!
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
