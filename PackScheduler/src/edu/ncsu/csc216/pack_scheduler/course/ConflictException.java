package edu.ncsu.csc216.pack_scheduler.course;

/**
 *Class for Conflict Exception to make sure that exceptions are set up with the proper type and exception message.
 * @author Chance Cheatham
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a custom checked exception called ConflictException
	 */
	public ConflictException() {
		super("Schedule conflict.");
	}

	/**
	 * Creates a custom checked exception called ConflictException with a message of "Schedule conflict."
	 * @param message String of the exception message.
	 */
	public ConflictException(String message) {
		super(message);
	}


}
