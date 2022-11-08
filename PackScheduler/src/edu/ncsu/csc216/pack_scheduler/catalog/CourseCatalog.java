/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Class to hold and handle the course catalog as an object.
 * 
 * @author gzuniga
 *
 *
 */
public class CourseCatalog {
	/** Course catalog */
	private SortedList<Course> catalog;

	/** Constructor for CourseCatalog. */
	public CourseCatalog() {
		this.catalog = new SortedList<Course>();
	}

	/** Creates a new Course Catalog. */
	public void newCourseCatalog() {
		this.catalog = new SortedList<Course>();
	}
 
	/**
	 * Loads courses from a file and stores it in a sorted list
	 * 
	 * @param fileName name of the file
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}

	}

	/**
	 * Adds a course to the catalog. Returns true if course described in parameters
	 * does not belong already to the catalog and false if it is already in the
	 * catalog. Throws an IllegalArgumentException if there is an error adding the
	 * course.
	 * 
	 * @param name          name of the course
	 * @param title         title of the course
	 * @param section       section of the course
	 * @param credits       credits of the course
	 * @param instructorId  instructor ID for the course
	 * @param enrollmentCap the enrollment cap for the course roll
	 * @param meetingDays   days the course meets
	 * @param startTime     time the course begins
	 * @param endTime       end time of the course
	 * @return boolean indicates success in adding a course to the catalog
	 * @throws IllegalArgumentException when an error adding the course comes up
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, 
			String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) {

		Course c = new Course(name, title, section, credits, instructorId, enrollmentCap, 
				meetingDays, startTime, endTime);
		for(int i = 0; i < this.catalog.size(); i++) {
			if(catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return false;
			}
		}
		catalog.add(c);
		return true;
	}

	/**
	 * Removes a course from the catalog. Returns true if course described in
	 * parameters is in the catalog and removed and false if it is not found.
	 * 
	 * @param name    name of the course
	 * @param section section of the course
	 * @return boolean indicating whether course was removed successfully or not
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int x = 0; x < catalog.size(); x++) {
			Course c = catalog.get(x);
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				catalog.remove(x);
				return true; 
			}
		}
		return false;
	}

	/**
	 * Gets course described in parameters from course catalog. Returns course if it
	 * is found in the catalog or null if it is not.
	 * 
	 * @param name    name of course
	 * @param section section of the course
	 * @return the course if it is found, and false if not.
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Returns two dimensional array containing name, section, title and meeting
	 * info of a course.
	 * 
	 * @return two dimensional array containing certain details of elements in the
	 *         course catalog.
	 */
	public String[][] getCourseCatalog() {
		String[][] cat = new String [catalog.size()][4];
		for(int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			cat[i] = c.getShortDisplayArray();
		}
		return cat;
	}

	/**
	 * Exports course catalog to file of name specified in the parameter.
	 * 
	 * @param fileName name of the file
	 */
	public void saveCourseCatalog(String fileName) {
		PrintStream fileWriter;
		try {
			fileWriter = new PrintStream(new File(fileName));
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}

		for (int i = 0; i < catalog.size(); i++) {
			fileWriter.println(catalog.get(i).toString());
		}

		fileWriter.close();
	}
}
