package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * 
 * @author Sarah Heckman
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
	    SortedList<Course> courses = new SortedList<Course>(); //Create an empty array of Course objects
	    while (fileReader.hasNextLine()) { //While we have more lines in the file
	        try { //Attempt to do the following
	            //Read the line, process it in readCourse, and get the object
	            //If trying to construct a Course in readCourse() results in an exception, flow of control will transfer to the catch block, below
	            Course course = readCourse(fileReader.nextLine()); 

	            //Create a flag to see if the newly created Course is a duplicate of something already in the list  
	            boolean duplicate = false;
	            //Look at all the courses in our list
	            for (int i = 0; i < courses.size(); i++) {
	                //Get the course at index i
	                Course current = courses.get(i);
	                //Check if the name and section are the same
	                if (course.getName().equals(current.getName()) &&
	                        course.getSection().equals(current.getSection())) {
	                    //It's a duplicate!
	                    duplicate = true;
	                    break; //We can break out of the loop, no need to continue searching
	                }
	            }
	            //If the course is NOT a duplicate
	            if (!duplicate) {
	                courses.add(course); //Add to the ArrayList!
	            } //Otherwise ignore
	        } catch (IllegalArgumentException e) {
	            //The line is invalid b/c we couldn't create a course, skip it!
	        }
	    }
	    //Close the Scanner b/c we're responsible with our file handles
	    fileReader.close();
	    //Return the ArrayList with all the courses we read!
	    return courses;
	}

	/**
	 * Helper method for ReadCourseRecords.
	 * Checks for Validity from input course files from text files.
	 * @return Course  The course object after validity tests were passed. 
	 * @param  line  String that represents the current line from a file.
	 * @throws IllegalArgumentException if a course records have extra fields.
	 */
	private static Course readCourse(String line) {
	   Scanner lineScan = new Scanner(line);
	   lineScan.useDelimiter(",");
     //Integer validCourseCount = 0;
	   
	   try {
		   // Read in tokens for name, title, section, credits, instructorId, and meetingDays and store in local variables
		  String name = lineScan.next();
		  String title = lineScan.next();
		  String section = lineScan.next();
		  Integer credits = Integer.parseInt(lineScan.next());
		  String instructorID = lineScan.next();
		  Integer enrollmentCap = Integer.parseInt(lineScan.next());
		  String meetingDays = lineScan.next();
		  
		  // Check for meeting days string value of A, 
		  // If true throw exception if there are more tokens, otherwise construct and return a new course object with arranged meeting days
	      if ("A".equals(meetingDays) && lineScan.hasNext()) {
	    	  // Closes the scanner.
			  lineScan.close();
	    	  throw new IllegalArgumentException();
	      } else if ("A".equals(meetingDays)) {
	    	  Course currentCourse = new Course(name, title, section, credits, instructorID, enrollmentCap, meetingDays);
	    	  // Closes the scanner.
			  lineScan.close();
//	    	  validCourseCount++;
	    	  //System.out.println(validCourseCount);
	    	  return currentCourse;
	      }

	      // Read in tokens for startTime and endTime 
		  // Check for and throw an exception if there are more tokens
		  Integer startTime = Integer.parseInt(lineScan.next());
		  Integer endTime = Integer.parseInt(lineScan.next());
		  if (lineScan.hasNext()) {
			  // Closes the scanner.
			  lineScan.close();
			  throw new IllegalArgumentException();
		  }
	      //NEW: create Course object with null instructor
		  Course currentCourse = new Course(name, title, section, credits, null, enrollmentCap, meetingDays, startTime, endTime);
		  
		  //NEW: see if there is a faculty member with the instructor id, if so, add Course to Faculty's faculty schedule.
		  FacultyDirectory fd = RegistrationManager.getInstance().getFacultyDirectory();
		  String[][] fdStr = fd.getFacultyDirectory();
		  //System.out.println(fdStr.length);
		  for(int i = 0; i < fdStr.length; i++) {
			  if(fdStr[i][2].equals(instructorID)) {
				  //currentCourse.setInstructorId(instructorID);
				  System.out.println("This courses IID: " + currentCourse.getInstructorId());
				  fd.getFacultyById(instructorID).getSchedule().addCourseToSchedule(currentCourse);
				  lineScan.close();
				  return currentCourse;
			  }
		  }
		  
		  // Closes the scanner.
		  lineScan.close();
//		  validCourseCount++;
		  //System.out.println(validCourseCount);
		  return currentCourse;
	   } catch (NoSuchElementException e) {
		   throw new IllegalArgumentException();
	   }
	}
	
	/**
     * Writes the given list of courses to a file.
     * @param fileName file to write course list to
     * @param courses list of courses to write
     * @throws IOException if cannot write to file
     */
    public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
    	PrintStream fileWriter = new PrintStream(new File(fileName));

    	for (int i = 0; i < courses.size(); i++) {
    	    fileWriter.println(courses.get(i).toString());
    	}

    	fileWriter.close();
    }

}