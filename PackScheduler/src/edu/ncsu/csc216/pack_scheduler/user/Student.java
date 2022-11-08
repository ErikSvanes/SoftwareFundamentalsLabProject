package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Student class. Holds, sets, and retrieves information for a particular
 * student. States include: firstName, lastName, ID, email, password,
 * maxCredits, and a final var MAX_CREDITS.
 * 
 * @author amodi
 *
 */
public class Student extends User implements Comparable<Student> {

	/** Max credits of the student **/
	private int maxCredits;
	/** Max Credits Students Can Take **/
	public static final int MAX_CREDITS = 18;
	/** The student's schedule */
	private Schedule schedule = new Schedule();

	/**
	 * Student constructor that fills all the states
	 * 
	 * @param firstName  the student's first name
	 * @param lastName   the student's last name
	 * @param id         the student's id
	 * @param email      the student's email
	 * @param password   the student's password
	 * @param maxCredits the students max credits
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		this.setMaxCredits(maxCredits);
	}

	/**
	 * Student constructor that defaults maxCredits to 18
	 * 
	 * @param firstName the student's first name
	 * @param lastName  the student's last name
	 * @param id        the student's id
	 * @param email     the student's email
	 * @param password  the student's password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		super(firstName, lastName, id, email, password);
		this.setMaxCredits(MAX_CREDITS);
	}

	/**
	 * Returns the max credits of the students
	 * 
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the maxCredits of the student. If maxCredits lies out of the range 3 to
	 * MAX_CREDITS (18), inclusive, then an IAE is thrown.
	 * 
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if parameters aren't met
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Returns a comma separated list of all Student fields as a string
	 * 
	 * @return String representation of Student
	 */

	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ maxCredits;
	}

	@Override
	public int compareTo(Student o) {
		if (getLastName() == o.getLastName()) {
			if (getFirstName() == o.getFirstName()) {
				if (getId().compareTo(o.getId()) < 0) {
					return -1;
				} else if (getId().compareTo(o.getId()) > 0) {
					return 1;
				} else {
					return 0;
				}
			}
			if (getFirstName().compareTo(o.getFirstName()) < 0) {
				return -1;
			} else {
				return 1;
			}
		}
		if (getLastName().compareTo(o.getLastName()) < 0) {
			return -1;
		} else {
			return 1;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;
	}

	/**
	 * Returns the student's schedule
	 * 
	 * @return the student's schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Method that checks whether a course can be added to a student's schedule
	 * 
	 * @param course the course to check
	 * @return true or false whether a student can add the course to their schedule
	 */
	public boolean canAdd(Course course) {
		if (course.getCredits() + schedule.getScheduleCredits() > maxCredits) {
			return false;
		}
		return schedule.canAdd(course);
	}

}
