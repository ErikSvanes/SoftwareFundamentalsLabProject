/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Class to read and write faculty files. 
 * @author devinmowry
 *
 */
public class FacultyRecordIO {
	 /**
     * Reads faculty records from a file and generates a linked list of faculty.  Any invalid
     * students are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read faculty records from
     * @return a LinkedList of valid faculty
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));
	    LinkedList<Faculty> faculty = new LinkedList<Faculty>();
	    while (fileReader.hasNextLine()) {
	        try {
	            Faculty member = processFaculty(fileReader.nextLine()); 

	            // Checks for a duplicate student line.
	            boolean duplicate = false;
	            for (int i = 0; i < faculty.size(); i++) {
	                User current = faculty.get(i);
	                if (member.getId().equals(current.getId())) {
	                    duplicate = true;
	                    break;
	                }
	            }
	            if (!duplicate) {
	                faculty.add(member); 
	            }
	        } catch (IllegalArgumentException e) {
	        	// Couldn't create a student object.
	        }
	    }
	    fileReader.close();
	    return faculty;
	}

	/**
	 * Helper method for readFacultyRecords.
	 * Checks for Validity from input faculty files from text files.
	 * @return Faculty  The faculty object after validity tests were passed. 
	 * @param  line  String that represents the current line from a file.
	 */
	private static Faculty processFaculty(String line) {
	   Scanner lineScan = new Scanner(line);
	   lineScan.useDelimiter(",");
	   
	   try {
		   // Read in tokens for first name, last name, id, email, password, and max credits.
		  String firstName = lineScan.next();
		  String lastName = lineScan.next();
		  String id = lineScan.next();
		  String email = lineScan.next();
		  String password = lineScan.next();
		  Integer maxCourses = Integer.parseInt(lineScan.next());
		  
		  
		  if (lineScan.hasNext()) {
			  // Closes the scanner.
			  lineScan.close();
			  throw new IllegalArgumentException();
		  }
	      //return a newly constructed Student object
		  Faculty f = new Faculty(firstName, lastName, id, email, password, maxCourses);
		  
		  // Closes the scanner.
		  lineScan.close();
		  return f;
	   } catch (NoSuchElementException e) {
		   throw new IllegalArgumentException();
	   }
	}

	/**
     * Writes the given list of Students to a file.
     * @param fileName file to write student list to
     * @param faculty list of Faculty members to write
     * @throws IOException if cannot write to file
     */
    public static void writeFacultyRecords(String fileName, LinkedList<Faculty> faculty) throws IOException {
    	PrintStream fileWriter = new PrintStream(new File(fileName));

    	for (int i = 0; i < faculty.size(); i++) {
    	    fileWriter.println(faculty.get(i).toString());
    	}

    	fileWriter.close();
    }
}
