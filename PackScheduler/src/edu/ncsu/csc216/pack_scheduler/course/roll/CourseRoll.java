/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * The CourseRoll is responsible for holding a list of all of the students for
 * each course. It also can alter that list by adding or removing students as
 * needed.
 * 
 * @author drewb
 *
 */
public class CourseRoll {
	/** The list of students */
	LinkedAbstractList<Student> roll;
	/** The minimum enrollment for the course */
	private static final int MIN_ENROLLMENT = 10;
	/** The maximum enrollment for the course */
	private static final int MAX_ENROLLMENT = 250;
	/** The waitlist cap for a course**/
	private static final int WAITLIST_SIZE = 10;
	/** The enrollment cap for the course */
	private int enrollmentCap;
	/** The waitlist for the course**/
	private LinkedAbstractList<Student> waitlist;

	/**
	 * The constructor for the course roll, which first sets the enrollment cap and
	 * then creates an empty list roll with the given enrollment cap
	 * 
	 * @param enrollmentCap the new enrollment cap
	 * @param c the course object
	 */
	public CourseRoll(Course c, int enrollmentCap) {
		if(c == null) {
			throw new IllegalArgumentException();
		}
		setEnrollmentCap(enrollmentCap);
		roll = new LinkedAbstractList<Student>(enrollmentCap);
		waitlist = new LinkedAbstractList<Student>(WAITLIST_SIZE);
	}

	/**
	 * Method that sets the enrollment cap and ensures that it is between the
	 * requirements
	 * 
	 * @param enrollmentCap the new enrollment cap
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException("Invalid enrollmentCap.");
		}
		if (roll != null && enrollmentCap < roll.size()) {
			throw new IllegalArgumentException("Invalid enrollmentCap.");
		}
		
		this.enrollmentCap = enrollmentCap;
		if(roll != null) {
			roll.setCapacity(enrollmentCap);
		}
		
	}

	/**
	 * Method that enrolls the student from the parameter in the course if the
	 * student can be enrolled
	 * 
	 * @param s the student to be enrolled
	 * @throws IllegalArgumentException if s is null, already enrolled, or if the waitlist is full.
	 */
	public void enroll(Student s) {
		if (s == null) {
			throw new IllegalArgumentException("Invalid student.");
		}
		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(s)) {
				throw new IllegalArgumentException("Duplcate student.");
			}
		}
		if (canEnroll(s)) {
			try {
				roll.add(s);
			} catch (IllegalArgumentException IAE) {
				waitlist.add(s);
			}
		}
		if (enrollmentCap == roll.size() && waitlist.size() == WAITLIST_SIZE) {
			throw new IllegalArgumentException("Cannot add student.");
		}
	}

	/**
	 * Method that removes the student from the parameter if the student is in the
	 * course
	 * If the Student is in the main roll, remove the Student and add the first eligible Student 
	 * in the waitlist to the main roll.
	 * If the Student is in the waitlist, remove the Student from the waitlist while maintaining 
	 * the order of the waitlist and working with the specialized data structure methods.
	 * 
	 * @param s the student to remove
	 * @throws IllegalArgumentException if s is null or if there is otherwise 
	 * an issue with dropping the course, such as the student is not on the CourseRoll.
	 */
	public void drop(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		boolean removed = false;
		try {
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).equals(s)) {
					roll.remove(i);
					removed = true;
					break;
				}
				if (waitlist.get(i).equals(s)) {
					waitlist.remove(i);
					return;
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		if (removed && waitlist.size() != 0 && canEnroll(waitlist.get(0))) {
			roll.add(waitlist.remove(0));
		}
		
	}

	/**
	 * Determines if a student is able to enroll in the course by checking if the
	 * student is already in the course and then whether the course is full or not
	 * 
	 * @param s the student
	 * @return true or false whether the student is able to enroll in the course
	 */
	public boolean canEnroll(Student s) {
		boolean foundWaitlist = false;
		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(s)) {
				return false;
			}
			if (waitlist.size() > i && waitlist.get(i).equals(s)) {
				foundWaitlist = true;
			}
		}
		if (roll.size() < enrollmentCap) {
			return true;
		}
		return !foundWaitlist;
	}

	/**
	 * Returns the amount of open seats in the course
	 * 
	 * @return the amount of open seats in the course
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}

	/**
	 * Returns the enrollment cap for the course
	 *
	 * @return the enrollment cap for the course
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}
	
	/**
	 * Returns the size of the wait list
	 * 
	 * @return the size of the wait list
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}

}
