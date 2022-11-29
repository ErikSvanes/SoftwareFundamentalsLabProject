/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Controls the course schedule
 * 
 * @author Erik
 *
 */
public class Schedule {
	/** The schedule */
	ArrayList<Course> schedule;
	/** The schedule's title */
	String title;

	/**
	 * The schedule constructor, which creates an empty arraylist of courses, and
	 * sets the title to 'My Schedule'
	 */
	public Schedule() {
		this.schedule = new ArrayList<Course>();
		this.title = "My Schedule";
	}

	/**
	 * This method adds a course to the end of the schedule if requirements are met
	 * 
	 * @param course the course to add to the schedule
	 * @return true if the course is added, false if not
	 */
	public boolean addCourseToSchedule(Course course) {
		if (schedule.size() != 0) {
			for (Course c : schedule) {
				if (c.isDuplicate(course)) {
					throw new IllegalArgumentException("You are already enrolled in " + course.getName());
				}
				try {
					c.checkConflict(course);
				} catch (ConflictException ce) {
					throw new IllegalArgumentException("The course cannot be added due to a conflict.");
				}
			}
		}
		schedule.add(course);
		return true;
	}

	/**
	 * This method removes the given course from the schedule if it meets
	 * requirements
	 * 
	 * @param course the course to be removed
	 * @return true if the course is removed, false if not
	 */
	public boolean removeCourseFromSchedule(Course course) {
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).equals(course)) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * This method resets the schedule so that the schedule variable is a new array
	 * list of courses and the title is set to the default variable
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
		title = "My Schedule";
	}

	/**
	 * Returns the short display arrays of everything in the schedule as a 2d array
	 * 
	 * @return the 2 dimensional array of 'short display array's for every course in
	 *         the schedule
	 */
	public String[][] getScheduledCourses() {
		String[][] str = new String[schedule.size()][4];
		for (int i = 0; i < schedule.size(); i++) {
			str[i] = schedule.get(i).getShortDisplayArray();
		}
		return str;
	}

	/**
	 * Sets the title value
	 * 
	 * @param title the title that is set
	 * @throws IllegalArgumentException if title is null
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}
	
	/**
	 * Method to get schedule title. 
	 * @return title of the schedule.
	 */
	
	public String getTitle() {
		return title; 
	}
	
	/**
	 * Method to get the cumulative sum of total credits in the schedule.
	 * @return credits in the schedule
	 */
	public int getScheduleCredits() {
		int credits = 0;
		for(Course c : schedule) {
			credits += c.getCredits();
		}
		return credits;
	}
	
	/**
	 * Method to determine if the course can be added to the schedule. 
	 * @param c course to check 
	 * @return true if the Course can be added, otherwise returns false.
	 */
	public boolean canAdd(Course c) {
		if(c == null) {
			return false;
		}
		if (schedule.size() != 0) {
			for (Course course : schedule) {
				if (c.isDuplicate(course)) {
					return false;
				}
				try {
					c.checkConflict(course);
				} catch (ConflictException ce) {
					return false;
				}
			}
		}
		return true;
	}

}
