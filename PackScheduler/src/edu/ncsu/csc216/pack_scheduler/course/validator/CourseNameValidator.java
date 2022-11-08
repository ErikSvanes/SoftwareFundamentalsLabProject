/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Class to validate course names. Context class for
 * 
 * @author devinmowry
 *
 */
public class CourseNameValidator {
	/** The validEndState **/
	private boolean validEndState;
	// not sure if we will need these two counters but they are in the uml,
	// I think they are for the 4 state implementation.
//	/** The counter for letters**/
//	private int letterCount;
//	/** The counter for digits**/
//	private int digitCount;
	/** State object to hold the current state. **/
	private State currentState;

	/** Final instance of the Initial state inner class. **/
	private final State initial = new Initial();
	/** Final instance of the Letter1 state inner class. **/
	private final State letter1 = new Letter1();
	/** Final instance of the Letter2 state inner class. **/
	private final State letter2 = new Letter2();
	/** Final instance of the Letter3 state inner class. **/
	private final State letter3 = new Letter3();
	/** Final instance of the Letter4 state inner class. **/
	private final State letter4 = new Letter4();
	/** Final instance of the Digit1 state inner class. **/
	private final State digit1 = new Digit1();
	/** Final instance of the Digit2 state inner class. **/
	private final State digit2 = new Digit2();
	/** Final instance of the Digit3 state inner class. **/
	private final State digit3 = new Digit3();
	/** Final instance of the Suffix state inner class. **/
	private final State suffix = new Suffix();

	/**
	 * Constructor for CourseNameValidator.
	 */
	public CourseNameValidator() {
		// I think this makes sense but not really sure
		currentState = initial;
		validEndState = false;
	}

	/**
	 * Method to check the validity of a course name.
	 * 
	 * @param name to check for validity
	 * @return true if valid, false otherwise
	 * @throws InvalidTransitionException if there is an illegal transition.
	 */
	public boolean isValid(String name) throws InvalidTransitionException {
		currentState = initial;
		validEndState = false;
		for (int i = 0; i < name.length(); i++) {
			if (Character.isLetter(name.charAt(i))) {
				currentState.onLetter();
			} else if (Character.isDigit(name.charAt(i))) {
				currentState.onDigit();
			} else {
				currentState.onOther();
			}
		}
		return validEndState;
	}

	/*
	 * Private abstract class to ...?????
	 * 
	 * @author devinmowry
	 *
	 */
	private abstract class State {

		/**
		 * Abstract method to set the current state to the next letter state or throws
		 * an ITE to be implemented by concrete classes.
		 * 
		 * @throws InvalidTransitionException if the next character cannot be a letter
		 */
		public abstract void onLetter() throws InvalidTransitionException;

		/**
		 * Abstract method to set the current state to the next digit state or throws
		 * an ITE to be implemented by concrete classes.
		 * 
		 * @throws InvalidTransitionException if the next character cannot be a digit
		 */
		public abstract void onDigit() throws InvalidTransitionException;

		/**
		 * Method to throw an InvalidTransitionException if any non alphanumeric
		 * character is found.
		 * 
		 * @throws InvalidTransitionException if character is any non alphanumeric
		 *                                    character.
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}

	private class Initial extends State {
		/**
		 * Private constructor for Initial state.
		 */
		private Initial() {

		}

		/**
		 * Method that handles the letter inputs
		 */
		@Override
		public void onLetter() {
			currentState = letter1;

		}

		/**
		 * Method that handles the digit inputs
		 * 
		 * @throws InvalidTransitionException because course names must start with a
		 *                                    letter.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}

	private class Letter1 extends State {

		/**
		 * Private constructor for Letter1 state.
		 */
		private Letter1() {

		}

		/**
		 * Handles the letter inputs for letter 2
		 */
		@Override
		public void onLetter() {
			currentState = letter2;

		}

		/**
		 * Handles the digit inputs for digit 1
		 */
		@Override
		public void onDigit() {
			currentState = digit1;

		}

	}

	private class Letter2 extends State {
		/**
		 * Private constructor for Letter2 state.
		 */
		private Letter2() {

		}

		/**
		 * Handles the letter inputs for letter 3
		 */
		@Override
		public void onLetter() {
			currentState = letter3;

		}

		/**
		 * Handles the digit inputs for digit 1
		 */
		@Override
		public void onDigit() {
			currentState = digit1;

		}
	}

	private class Letter3 extends State {
		/**
		 * Private constructor for Letter3 state.
		 */
		private Letter3() {

		}

		/**
		 * Handles the letter inputs for letter 4
		 */
		@Override
		public void onLetter() {
			currentState = letter4;

		}

		/**
		 * Handles the digit inputs for digit 1
		 */
		@Override
		public void onDigit() {
			currentState = digit1;

		}
	}

	private class Letter4 extends State {
		/**
		 * Private constructor for Letter4 state.
		 */
		private Letter4() {

		}

		/**
		 * Handles invalid letter inputs
		 * 
		 * @throws InvalidTransitionException
		 * 
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");

		}

		/**
		 * Handles the digit inputs for digit 1
		 */
		@Override
		public void onDigit() {
			currentState = digit1;

		}
	}

	private class Digit1 extends State {
		/**
		 * Private constructor for Digit1 state.
		 */
		private Digit1() {

		}

		/**
		 * Handles invalid letter inputs
		 * 
		 * @throws InvalidTransitionException
		 * 
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must have 3 digits.");

		}

		/**
		 * Handles digit inputs for digit 2
		 */
		@Override
		public void onDigit() {
			currentState = digit2;

		}
	}

	private class Digit2 extends State {
		/**
		 * Private constructor for Digit2 state.
		 */
		private Digit2() {

		}

		/**
		 * Handles invalid letter inputs
		 * 
		 * @throws InvalidTransitionException
		 * 
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must have 3 digits.");

		}

		/**
		 * Handles the digit inputs for digit 3
		 */
		@Override
		public void onDigit() {
			currentState = digit3;
			validEndState = true;

		}
	}

	private class Digit3 extends State {
		/**
		 * Private constructor for Digit3 state.
		 */
		private Digit3() {

		}

		/**
		 * Handles letter inputs for the last letter
		 */
		@Override
		public void onLetter() {
			currentState = suffix;
			validEndState = true;

		}

		/**
		 * Handles invalid digit inputs
		 * 
		 * @throws InvalidTransitionException
		 * 
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have 3 digits.");

		}
	}

	private class Suffix extends State {
		/**
		 * Private constructor for Suffix state.
		 */
		private Suffix() {

		}

		/**
		 * Handles invalid letter inputs on suffix
		 * 
		 * @throws InvalidTransitionException
		 * 
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");

		}

		/**
		 * Handles invalid digit input on suffix
		 * 
		 * @throws InvalidTransitionException
		 * 
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");

		}
	}
}
