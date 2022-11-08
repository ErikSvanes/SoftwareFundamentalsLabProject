package edu.ncsu.csc216.pack_scheduler.course;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

/**
 * Handles the fields of the event and course classes that inherit this class.
 * Manages the following fields of child classes: title, meeting days, start time and end time.
 * Contains the get methods for title, meeting days, start time, end time, and get meeting string.
 * Contains and handles validation for the set methods for title and meeting days and times.
 * @author Chance Cheatham
 */
public abstract class Activity  implements Conflict {

	/** Activitiy's title. */
	private String title;
	/** Activitiy's meeting days */
	private String meetingDays;
	
	/**
	 * Overrides check conflict to throw a custom schedule conflict exception.
	 * Checks if an instance of an activity has a time overlap on the same day as a passed activity parameter.
	 * @param possibleConflictingActivity  Activity to check for time overlap against.
	 * @throws ConflictException  If two activities have overlapping times.
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		String days = getMeetingDays();
		Integer stTime = getStartTime();
		Integer edTime = getEndTime();
		Integer conStTime = possibleConflictingActivity.getStartTime();
		Integer conEdTime = possibleConflictingActivity.getEndTime();
		
		if (!getMeetingDays().contains("A")) {
			for (int i = 0; i < days.length(); i++) {
				if (possibleConflictingActivity.getMeetingDays().indexOf(days.charAt(i)) != -1) {
					if (stTime >= conStTime && stTime <= conEdTime) {
						throw new ConflictException("Schedule conflict.");
					} if (stTime <= conEdTime && edTime >= conStTime) {
						throw new ConflictException("Schedule conflict.");
					}
				}
			}
		}
	}

	/** Activitiy's starting time */
	private int startTime;
	/** Activitiy's ending time */
	private int endTime;
	/** Maximum hour count. */
	private static final int UPPER_HOUR = 24;
	/** Maximum minute count. */
	private static final int UPPER_MINUTE = 60;

	/**
	 * Constructs a new Activity object using title, meeting days, start time and end time.
	 * @param title  String for event title.
	 * @param meetingDays  String for the meeting days of the event.
	 * @param startTime  Integer representing when the activity starts.
	 * @param endTime  Integer representing when the activity ends.
	 */
    public Activity(String title, String meetingDays, int startTime, int endTime) {
        super();
        setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }

	/**
	 * Provides a string of the activity title.
	 * @return string with the title of the activity.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Checks for a null or empty title before setting the title of an activity.
	 * Throws Illegal argument exception if validation is failed.
	 * @param title  String title of activity.
	 * @throws IllegalArgumentException if title string is null or empty.
	 */
	public void setTitle(String title) {
		
		// Checking validation: Check for empty string and null. 
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		//Validation successful
		this.title = title;
	}

	/**
	 * Provides a string of the days the activity meets.
	 * @return string of the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Provides a integer of the starting time for the activity.
	 * @return integer of the start time
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Provides a integer of the ending time for the activity.
	 * @return integer value of the ending time
	 */
	public int getEndTime() {
		return endTime;
	}

	/** 
	 * Checks to ensure ending time is later than starting time. Checks to make sure both start and end time are between 0 and 2400
	 * Intended to be overridden in child classes to pass validation specific to those classes.
	 * Throws Illegal argument exception if validation is failed.
	 * Sets the values for the activities meeting days, starting time and ending time.
	 * @param meetingDays  String with the starting characters for the days of the week the activity meets.
	 * @param startTime  Integer representing the starting time of the activity in military time.
	 * @param endTime  Integer representing the ending time of the activity in military time.
	 * 
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		
		// local variables for times
		int startHour = startTime / 100;
		int endHour = endTime / 100;
		int startMin = startTime % 100;
		int endMin = endTime % 100;
		
		// Checking for valid meeting day strings. Retrieves a regular expression from the child class to do so.
//		Pattern regex = Pattern.compile(getValidationRule());
//		Matcher invalid = regex.matcher(meetingDays);
//		if (invalid.find()) {
//			throw new IllegalArgumentException("Invalid meeting days and times.");
//		}
		
		// Checking if times are valid
		if (endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		} if (0 > startHour || startHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		} if (0 > endHour || endHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		} if (0 > startMin || startMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		} if (0 > endMin || endMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
//		// checking if meeting days contains valid characters.
//		int a = 0;
//		int m = 0;
//		int t = 0;
//		int w = 0;
//		int h = 0;
//		int f = 0;
//		
//		// Iterating through to count which characters appear in the string and throws exceptions when necessary.
//		for (int i = 0; i < meetingDays.length(); i++) {
//			
//			if ("A".equals(Character.toString(meetingDays.charAt(i)))) {
//				a++;
//			} else if ("M".equals(Character.toString(meetingDays.charAt(i)))) {
//				m++;
//			} else if ("T".equals(Character.toString(meetingDays.charAt(i)))) {
//				t++;
//			} else if ("W".equals(Character.toString(meetingDays.charAt(i)))) {
//				w++;
//			} else if ("H".equals(Character.toString(meetingDays.charAt(i)))) {
//				h++;
//			} else if ("F".equals(Character.toString(meetingDays.charAt(i)))) {
//				f++;
//			} else throw new IllegalArgumentException("Invalid meeting days and times.");
//		} if (a > 1 || m > 1 || t > 1 || w > 1 || h > 1 || f > 1) {
//			throw new IllegalArgumentException("Invalid meeting days and times.");
//		} if (a == 1) {
//			if (m == 1 || t == 1 || w == 1 || h == 1 || f == 1) {
//				throw new IllegalArgumentException("Invalid meeting days and times.");
//			} if (startTime != 0 || endTime != 0) {
//				throw new IllegalArgumentException("Invalid meeting days and times.");
//			}
//		}
		// validation successful.
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Provides a string displaying the meeting days and times of an activity.
	 * @return string displaying Meeting days with starting and ending times
	 */
	public String getMeetingString() {
			String meetingString = meetingDays + " ";
			
	//		Integer.toString(startTime);
	//		LocalTime stTime = LocalTime.parse(String.format("h:mma"));
	//		String iTime = DateTimeFormatter.ofPattern("h:mma").format(stTime);
	//		Integer.toString(endTime);
	//		LocalTime edTime = LocalTime.parse(String.format("h:mma"));
	//		String lTime = DateTimeFormatter.ofPattern("h:mma").format(edTime);
			
	//		String stTimePad = Integer.toString(startTime);
	//		while (stTimePad.length() < 4) {
	//			stTimePad = "0" + stTimePad;
	//		}
	//		String edTimePad = Integer.toString(endTime);
	//		while (edTimePad.length() < 4) {
	//			edTimePad = "0" + edTimePad;
	//		}
	//		DateFormat expecValue = DateFormat.getTimeInstance(startTime);
			
			Calendar date = Calendar.getInstance();
			date.set(1, 1, 1, startTime / 100, startTime % 100);
			DateFormat form = new SimpleDateFormat("h:mma");
			String iTime = form.format(date.getTime());
			date.set(1, 1, 1, endTime / 100, endTime % 100);
			String lTime = form.format(date.getTime());
			//This solution got me the closest. I could get at least one output.
	//		LocalTime stTime = LocalTime.parse(stTimePad);
	//		String iTime = DateTimeFormatter.ofPattern("h:mma").format(stTime);
	//		LocalTime edTime = LocalTime.parse(edTimePad);
	//		String lTime = DateTimeFormatter.ofPattern("h:mma").format(edTime);
			
		// This solution feels like it will work but requests either a Throws statement which throws off the test cases,  
		// or a try catch statement which feels like it goes around JUNIT 5 tests and isn't what the assignment is intending.
	//		SimpleDateFormat displayValue = new SimpleDateFormat("h:mma");
	//		Date iTime = displayValue.parse(Integer.toString(startTime));
	//		SimpleDateFormat displayValue = new SimpleDateFormat("h:mma");
	//		Date lTime = displayValue.parse(Integer.toString(endTime));
			
			// Checks if meeting days is A then prints the appropriate string format if true.
			if (meetingDays.contains("A")) {
				meetingString = "Arranged";
			} else {
				meetingString = meetingString + iTime + "-" + lTime;
			}
			
			return meetingString;
		}

	/**
	 * Allows for the checking of a duplicate.
	 * @param activity  Activity object to check for duplication.
	 * @return True  If a duplicate is found.
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/**
	 * Creates an array of strings formatted to be displayed by the GUI to populate rows in the course catalog.
	 * @return String Array of fields
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Creates an array of strings formatted to be displayed by the GUI on the final schedule with 7 entries.
	 * @return String Array of fields
	 */
	public abstract String[] getLongDisplayArray();
	
	/** Overrides hash code to include all fields.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(endTime, meetingDays, startTime, title);
	}

	/** Overrides equals to check all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		return endTime == other.endTime && Objects.equals(meetingDays, other.meetingDays)
				&& startTime == other.startTime && Objects.equals(title, other.title);
	}

}