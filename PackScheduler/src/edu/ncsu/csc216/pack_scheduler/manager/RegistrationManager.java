package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Class to hold instance of RegistrationManager and communicate with the GUI.
 * 
 * @author devinmowry,
 *
 */
public class RegistrationManager {
	/** Single instance of the RegistrationManager class **/
	private static RegistrationManager instance;
	/** The CourseCatalog */
	private CourseCatalog courseCatalog;
	/** The studentDirectory */
	private StudentDirectory studentDirectory;
	/** RegistrationManager Registrar */
	private User registrar;
	/** RegistrationManager Current User */
	private User currentUser;
	/** The Faculty Directory */
	private FacultyDirectory facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Prop file */
	private static final String PROP_FILE = "registrar.properties";

	private RegistrationManager() {
		createRegistrar();
		this.courseCatalog = new CourseCatalog();
		this.studentDirectory = new StudentDirectory();
		this.facultyDirectory = new FacultyDirectory();
	}

	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Method to get instance of RegistrationManager.
	 * 
	 * @return instance of RegistrationManager
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Method to get the CourseCatalog object.
	 * 
	 * @return courseCatalog object.
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Method to get the StudentDirectory object.
	 * 
	 * @return studentDirectory object
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Method to get the FacultyDirectory object.
	 * 
	 * @return facultyDirectory object
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}

	/**
	 * Method to log into the RegistrationManager
	 * 
	 * @param id       of user
	 * @param password of user
	 * @return boolean true if login was successful, false otherwise.
	 */
	public boolean login(String id, String password) {
		if (currentUser == null) {
			String localHashPW = hashPW(password);
			if (registrar.getId().equals(id)) {
				if (registrar.getPassword().equals(localHashPW)) {
					currentUser = registrar;
					return true;
				} else {
					return false;
				}
			}
			Student s = studentDirectory.getStudentById(id);
			Faculty f = facultyDirectory.getFacultyById(id);
			if (s != null) {
				if (s.getPassword().equals(localHashPW)) {
					currentUser = s;
					return true;
				}
				return false;
			}

			else if (f != null) {
				if (f.getPassword().equals(localHashPW)) {
					currentUser = f;
					return true;
				}
				return false;
			}

			else {
				throw new IllegalArgumentException("User doesn't exist.");
			}
		}
		return false;
	}

	/**
	 * Method to log the current user out of the registrar.
	 */
	public void logout() {
		currentUser = null;
	}

	/**
	 * Method to get current user.
	 * 
	 * @return currentUser of the manager.
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Method to clear the course catalog and the student directory.
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}

	/**
	 * Inner class to create Registrar user.
	 * 
	 * @author devinmowry
	 *
	 */
	private static class Registrar extends User {
		/**
		 * Create a registrar user.
		 * 
		 * @param firstName registrar's first name
		 * @param lastName  registrar's last name
		 * @param id        registrar's id
		 * @param email     registrar's email
		 * @param hashPW    registrar's hashPW
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}

	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * 
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			CourseRoll roll = c.getCourseRoll();

			if (s.canAdd(c) && roll.canEnroll(s)) {
				schedule.addCourseToSchedule(c);
				roll.enroll(s);
				return true;
			}

		} catch (IllegalArgumentException e) {
			return false;
		}
		return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * 
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			c.getCourseRoll().drop(s);
			return s.getSchedule().removeCourseFromSchedule(c);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Resets the logged in student's schedule by dropping them from every course
	 * and then resetting the schedule.
	 */
	public void resetSchedule() {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			String[][] scheduleArray = schedule.getScheduledCourses();
			for (int i = 0; i < scheduleArray.length; i++) {
				Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
				c.getCourseRoll().drop(s);
			}
			schedule.resetSchedule();
		} catch (IllegalArgumentException e) {
			// do nothing
		}
	}
	
	/**
	 * Adds the courses to the faculty schedule
	 * @param c the course object that represents the course
	 * @param f the facultySchedule object that represents the faculty
	 * @return the course to add to the schedule
	 */
	public boolean addFacultyToCourse(Course c, FacultySchedule f) {
		if(currentUser != null && currentUser == registrar) {
			return f.addCourseToSchedule(c);
		}
		return false;
	}
	/**
	 * Removes the course to the faculty schedule
	 * @param c the course object that represents the course
	 * @param f the facultySchedule object that represents the faculty
	 * @return the course to remove from the schedule
	 */
	public boolean removeFacultyFromCourse(Course c, FacultySchedule f) {
		if(currentUser != null && currentUser == registrar) {
			return f.removeCourseFromSchedule(c);
		}
		return false;
	}
	/**
	 * Resets the faculty schedule
	 * @param f the facultySchedule object that represents the faculty
	 */
	public void resetFacultySchedule(FacultySchedule f) {
		if(currentUser != null && currentUser == registrar) {
			f.resetSchedule();
		}

	}
	
	

}
