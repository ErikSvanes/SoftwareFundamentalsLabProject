/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidatorFSM;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Handles the fields and construction of new course objects. Inherits title,
 * meeting days, start time and end time from parent class activity. Manages the
 * following fields of course objects: name, section, credits and instructorId.
 * Contains the get methods for name, section, credits and instructorId.
 * Contains and handles validation for the set methods for name, section,
 * credits and instructorId. Overrides activity's get display array methods to
 * format them to properly display a courses information. Overrides activity's
 * isDuplicate method to compare equality for duplicate courses.
 * 
 * @author Chance Cheatham
 */
public class Course extends Activity implements Comparable<Course> {

	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Exact expected section length. */
	private static final int SECTION_LENGTH = 3;
	/** Maximum credit amount. */
	private static final int MAX_CREDITS = 5;
	/** Minimum credit amount. */
	private static final int MIN_CREDITS = 1;
	/** Course's roll. */
	private CourseRoll roll;

	/**
	 * Constructs a new Course object with values for all fields.
	 * 
	 * @param name          name of Course.
	 * @param title         title of Course.
	 * @param section       section of Course.
	 * @param credits       credit hours for Course.
	 * @param instructorId  instructor's unity id.
	 * @param enrollmentCap the enrollment cap for the course roll
	 * @param meetingDays   meeting days for Course as series of chars.
	 * @param startTime     start time for Course.
	 * @param endTime       end time for Course.
	 */
	public Course(String name, String title, String section, int credits, String instructorId, 
			int enrollmentCap, String meetingDays, int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		roll = new CourseRoll(enrollmentCap, ));
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name          name of Course.
	 * @param title         title of Course.
	 * @param section       section of Course.
	 * @param credits       credit hours for Course.
	 * @param instructorId  instructor's unity id.
	 * @param enrollmentCap the enrollment cap for the course roll
	 * @param meetingDays   meeting days for Course as series of characters.
	 */
	public Course(String name, String title, String section, int credits, String instructorId, 
			int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Provides a string of the course name.
	 * 
	 * @return string with the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Checks to ensure a course name contains 1-5 alphabetic characters followed by
	 * a space followed by exactly 3 numeric characters. Checks if the passed name
	 * string is null or empty. Throws Illegal argument exception if validation is
	 * failed. If validation is successful, sets the courses name to the name string
	 * passed to the method.
	 * 
	 * @param name String name of the course
	 * @throws IllegalArgumentException if name is null, empty or validation is
	 *                                  failed.
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		CourseNameValidatorFSM cnv = new CourseNameValidatorFSM();
		try {
			if (cnv.isValid(name)) {
				this.name = name;
				return;
			}
		} catch (InvalidTransitionException e) {
			// continue
		}
		throw new IllegalArgumentException("Invalid course name.");
		
//		// Regular expression to check validation
//		if (name != null && name
//				.matches("[a-zA-Z]{" + MIN_LETTER_COUNT + "," + MAX_LETTER_COUNT + "} [0-9]{" + DIGIT_COUNT + "}")) {
//			if (MIN_NAME_LENGTH <= name.length() && name.length() <= MAX_NAME_LENGTH) {
//				this.name = name;
//			}
//		} else {
//			throw new IllegalArgumentException("Invalid course name.");
//		}
	}

	/**
	 * Provides a string of the course section number.
	 * 
	 * @return String with the section number
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Checks to ensure a section number is exactly 3 numeric characters. Checks to
	 * ensure a section string is not null or empty. Throws Illegal argument
	 * exception if validation is failed. If validation is successful, sets the
	 * section for a course to the string passed to the method.
	 * 
	 * @param section String of the section number.
	 * @throws IllegalArgumentException if passed string is null, or validation is
	 *                                  failed.
	 */
	public void setSection(String section) {

		// Check for validation using regular expression
		if (section != null && section.matches("[0-9]{" + SECTION_LENGTH + "}")) {
			// Validation successful
			this.section = section;

		} else
			throw new IllegalArgumentException("Invalid section.");

	}

	/**
	 * Provides a integer of the number of credit hours for the course.
	 * 
	 * @return integer representing credit hours
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Checks to ensure the credit value passed is between 1 and 5 inclusively.
	 * Throws Illegal argument exception if validation is failed. If validation is
	 * passed, set the courses credit value to the integer passed to the method.
	 * 
	 * @param credits Integer of credit amount.
	 * @throws IllegalArgumentException if the value of credits is not within the
	 *                                  parameters.
	 */
	public void setCredits(int credits) {

		// Checking validation: Check to make sure value is numeric and within 1 to 5.
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}

		// Validation successful
		this.credits = credits;
	}

	/**
	 * Provides a string of the course instructor ID.
	 * 
	 * @return string of the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Checks to ensure the instructor ID is not null or empty. Throws Illegal
	 * argument exception if validation is failed. If validation is passed, sets the
	 * course instructor ID to the string passed to the method
	 * 
	 * @param instructorId String with unity ID of the instructor
	 * @throws IllegalArgumentException if passed string is empty or null.
	 */
	public void setInstructorId(String instructorId) {

		// Checking validation: Check for empty string and null. */
		if (instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}

		// Validation successful
		this.instructorId = instructorId;
	}

	/**
	 * Overrides the set meeting days and time method from the parent class
	 * Activity. Checks to ensure the meeting days string is not empty or null. in
	 * addition to Activities requirements, checks to make sure course meeting
	 * strings only contain the following characters with no repeats: MTWHFA. Checks
	 * if a meeting string contains the character A and has values for starting or
	 * ending time. Checks if A is combined with any other characters. Throws
	 * Illegal argument exception if validation is failed.
	 * 
	 * @param meetingDays String of the meeting days for the course.
	 * @param startTime   Integer of the start time for the course
	 * @param endTime     Integer of the end time for the course.
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// Checking for null
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (meetingDays.contains("A") && (startTime != 0 || endTime != 0)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		Pattern regex = Pattern.compile("(.).*\\1|[^MTWHFA]|.+A|A.+");
		Matcher invalid = regex.matcher(meetingDays);
		if (invalid.find()) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Overrides hash code to include all fields.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(credits, instructorId, name, section);
		return result;
	}

	/**
	 * Overrides equals to check all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return credits == other.credits && Objects.equals(instructorId, other.instructorId)
				&& Objects.equals(name, other.name) && Objects.equals(section, other.section);
	}

	/**
	 * Returns a comma separated String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId 
					+ "," + roll.getEnrollmentCap() + "," + getMeetingDays();
		} else
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId 
					+ "," + roll.getEnrollmentCap() + "," + getMeetingDays() + "," 
					+ getStartTime() + "," + getEndTime();
	}

	/**
	 * Arranges the courses fields into a String array to be displayed by the GUI in
	 * the course catalog.
	 */
	@Override
	public String[] getShortDisplayArray() {
		return new String[] { name, section, getTitle(), getMeetingString(), String.valueOf(roll.getOpenSeats()) };
	}

	/**
	 * Arranges the courses fields into a String array to be displayed by the GUI in
	 * the final schedule.
	 */
	@Override
	public String[] getLongDisplayArray() {
		return new String[] { name, section, getTitle(), Integer.toString(credits), instructorId, getMeetingString(),
				"" };
	}

	/**
	 * Checks if the passed activity name is the same as the current courses name.
	 * 
	 * @return True If names match.
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course course = (Course) activity;
			if (course.getName().equals(getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Compares two courses
	 */
	@Override
	public int compareTo(Course o) {

		if (name == o.getName()) {
			if (section.compareTo(o.getSection()) < 0) {
				return -1;
			} else if (section.compareTo(o.getSection()) > 0) {
				return 1;
			} else {
				return 0;
			}
		}
		if (name.compareTo(o.getName()) < 0) {
			return -1;
		} else if (name.compareTo(o.getName()) > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Returns the course roll for the course
	 * 
	 * @return the course roll for the course
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}
	
}
