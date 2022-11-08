/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Class for custom checked exceptions to handle invalid transitions
 * includes constructors with default message and parametrized constructor for custom messages
 * @author drewb
 *
 */
public class InvalidTransitionException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a custom checked exception called InvalidTransitionException
	 */
	public InvalidTransitionException() {
		super("Invalid FSM Transition.");
	} 

	/**
	 * Creates a custom checked exception called InvalidTransitionException with a message of "Invalid FSM Transition"
	 * @param message String of the exception message.
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
}
