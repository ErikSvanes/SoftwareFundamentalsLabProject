/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

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
	/** Faculty schedule */
	private FacultySchedule facultySchedule;
	
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
		this.facultySchedule = new FacultySchedule(id);
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
	
	/**
	 * Method to get the FacultySchedule object for this faculty member.
	 * @return faculty schedule of this faculty member.
	 */
	public FacultySchedule getSchedule() {
		return facultySchedule;
	}
	
	/**
	 * Method to determine if Faculty has too many scheduled courses. 
	 * @return true if the scheduled courses is greater than max courses.
	 */
	public boolean isOverloaded() {
		return getSchedule().getNumScheduledCourses() > getMaxCourses();
	}
	
	/**
	 * Determines the hash code of each max courses
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

    /**
     * Checks to make sure that each faculty is only listed once
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
//		if (maxCourses != other.maxCourses)
//			return false;
//		return true;
		return maxCourses == other.maxCourses;
	}


	/**
	 * Returns a comma separated list of all Faculty fields as a string
	 * 
	 * @return String representation of Faculty
	 */

	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ maxCourses;
	}
	
}
