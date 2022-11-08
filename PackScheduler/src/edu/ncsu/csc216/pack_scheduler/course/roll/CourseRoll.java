/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

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
	/** The enrollment cap for the course */
	private int enrollmentCap;

	/**
	 * The constructor for the course roll, which first sets the enrollment cap and
	 * then creates an empty list roll with the given enrollment cap
	 * 
	 * @param enrollmentCap the new enrollment cap
	 */
	public CourseRoll(int enrollmentCap) {
		setEnrollmentCap(enrollmentCap);
		roll = new LinkedAbstractList<Student>(enrollmentCap);
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
		if (roll != null) {
			roll.setCapacity(enrollmentCap);
		}
	}

	/**
	 * Method that enrolls the student from the parameter in the course if the
	 * student can be enrolled
	 * 
	 * @param s the student to be enrolled
	 */
	public void enroll(Student s) {
		if (s == null || !canEnroll(s)) {
			throw new IllegalArgumentException("Invalid student.");
		}
		try {
			roll.add(s);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}

	}

	/**
	 * Method that removes the student from the parameter if the student is in the
	 * course
	 * 
	 * @param s the student to remove
	 */
	public void drop(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		try {
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).equals(s)) {
					roll.remove(i);
					return;
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException();
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
		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(s)) {
				return false;
			}
		}
		return !(enrollmentCap == roll.size());
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

}
