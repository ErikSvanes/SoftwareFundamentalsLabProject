package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Reads Student records from text files. Checks for valid student records from the input files and discards invalid records.
 * Writes a set of student records to a file from a sorted list of valid student records.
 * @author Chance Cheatham
 */
public class StudentRecordIO {
	
	/**
	 * Private constructor for a utility class that never needs to be instantiated.
	 */
	private StudentRecordIO()
	{
		super();
	}

    /**
     * Reads student records from a file and generates a list of students.  Any invalid
     * students are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read student records from
     * @return a list of valid students
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));
	    SortedList<Student> students = new SortedList<Student>();
	    while (fileReader.hasNextLine()) {
	        try {
	            Student student = readStudent(fileReader.nextLine()); 

	            // Checks for a duplicate student line.
	            boolean duplicate = false;
	            for (int i = 0; i < students.size(); i++) {
	                User current = students.get(i);
	                if (student.getId().equals(current.getId())) {
	                    duplicate = true;
	                    break;
	                }
	            }
	            if (!duplicate) {
	                students.add(student); 
	            }
	        } catch (IllegalArgumentException e) {
	        	// Couldn't create a student object.
	        }
	    }
	    fileReader.close();
	    return students;
	}

	/**
	 * Helper method for ReadStudentRecords.
	 * Checks for Validity from input student files from text files.
	 * @return Student  The student object after validity tests were passed. 
	 * @param  line  String that represents the current line from a file.
	 */
	private static Student readStudent(String line) {
	   Scanner lineScan = new Scanner(line);
	   lineScan.useDelimiter(",");
	   
	   try {
		   // Read in tokens for first name, last name, id, email, password, and max credits.
		  String firstName = lineScan.next();
		  String lastName = lineScan.next();
		  String id = lineScan.next();
		  String email = lineScan.next();
		  String password = lineScan.next();
		  Integer maxCredits = Integer.parseInt(lineScan.next());
		  
		  
		  if (lineScan.hasNext()) {
			  // Closes the scanner.
			  lineScan.close();
			  throw new IllegalArgumentException();
		  }
	      //return a newly constructed Student object
		  Student currentStudent = new Student(firstName, lastName, id, email, password, maxCredits);
		  
		  // Closes the scanner.
		  lineScan.close();
		  return currentStudent;
	   } catch (NoSuchElementException e) {
		   throw new IllegalArgumentException();
	   }
	}

	/**
     * Writes the given list of Students to a file.
     * @param fileName file to write student list to
     * @param students list of Students to write
     * @throws IOException if cannot write to file
     */
    public static void writeStudentRecords(String fileName, SortedList<Student> students) throws IOException {
    	PrintStream fileWriter = new PrintStream(new File(fileName));

    	for (int i = 0; i < students.size(); i++) {
    	    fileWriter.println(students.get(i).toString());
    	}

    	fileWriter.close();
    }
    
    
}

