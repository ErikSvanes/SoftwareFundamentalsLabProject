/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Interface that assists in checks for time and day conflicts for Activity objects
 * Will throw a checks conflict exception if there is overlap in an active schedule and an activity to be added. 
 * @author Chance Cheatham
 */
public interface Conflict {
	
	/**
	 * Checks for day or time overlap between a current activity instance and a parameter activity instance.
	 * @param possibleConflictingActivity  Activity that may overlap days or times with another.
	 * @throws ConflictException  If the activity parameter has day or time overlap.
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;


}
