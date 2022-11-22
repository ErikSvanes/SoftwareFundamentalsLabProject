/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Faculty class. Holds, sets, and retrieves information for a particular
 * Faculty. States include: firstName, lastName, ID, email, password,
 * maxCourses, final var MAX_COURSES and final var MIN_COURSES.
 * @author drewb
 *
 */
public class Faculty extends User {
	
	/** Max courses for the faculty **/
	private int maxCourses;
	/** Max Credits Students Can Take **/
	public static final int MAX_COURSES = 3;
	/** Min Courses a faculty much teach **/
	public static final int MIN_COURSES = 1;
	
	/**
	 * Student constructor that fills all the states
	 * 
	 * @param firstName  the faculty first name
	 * @param lastName   the faculty last name
	 * @param id         the faculty id
	 * @param email      the faculty email
	 * @param password   the faculty password
	 * @param maxCourses the faculty max courses
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		this.setMaxCourses(maxCourses);
	}
	
	
	/**
	 * Returns the max courses of the faculty
	 * 
	 * @return the maxCourses
	 */
	public int getMaxCourses() {
		return maxCourses;
	}
	
	/**
	 * Sets the maxCourses of the facullty. If maxCourses lies out of the range MIN_COURSES (1) to
	 * MAX_CREDITS (3), inclusive, then an IAE is thrown.
	 * 
	 * @param maxCourses the maxCourse to set
	 * @throws IllegalArgumentException if parameters aren't met
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}
}
