/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Class to maintain a directory of all faculty.
 * 
 * @author devinmowry
 *
 */
public class FacultyDirectory {

	/** Private list of the faculty directory for the university. */
	private LinkedList<Faculty> facultyDirectory;
	/** Constant value for the hash algorithm used to hash passwords. */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * FacultyDirectory constructor. Calls the method that creates a new FacultyDirectory object.
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}

	/**
	 * Creates a new FacultyDirectory object.
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
	}

	/**
	 * Method to load Faculty from a file.
	 * @param fileName of file to load data from 
	 * @throws IllegalArgumentException if there is any problem reading the file.
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * Method to add Faculty to the directory.
	 * @param firstName first name of the Faculty member
	 * @param lastName last name of the Faculty member
	 * @param id id of the Faculty member
	 * @param email email of the Faculty member
	 * @param password password of the Faculty member
	 * @param repeatPassword password of the Faculty member again
	 * @param maxCourses max courses of the Faculty member, between 1 and 3 inclusive
	 * @throws IllegalArgumentException if the password is null or does not match the repeat password.
	 * @return true if the faculty member is added to the directory, false otherwise.
	 * 
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password,
			String repeatPassword, int maxCourses) {
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || "".equals(password) || "".equals(repeatPassword)) {
			throw new IllegalArgumentException("Invalid password");
		}

		hashPW = hashString(password);
		repeatHashPW = hashString(repeatPassword);

		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}

		Faculty faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);

		for (int i = 0; i < facultyDirectory.size(); i++) {
			Faculty f = facultyDirectory.get(i);
			if (f.getId().equals(faculty.getId())) {
				return false;
			}
		}
		return facultyDirectory.add(faculty);
	}

	/**
	 * Method to remove Faculty from directory. 
	 * @param id of the faculty member 
	 * @return true if the faculty member was removed, false otherwise.
	 */
	public boolean removeFaculty(String id) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			Faculty f = facultyDirectory.get(i);
			if (f.getId().equals(id)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to get a 2D array of faculty names and id's.
	 * @return directory in a 2D array containing the first and last names, and the id.
	 */
	public String[][] getFacultyDirectory() {
		String [][] directory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
		}
		return directory;
	}

	/**
	 * Method to save the current faculty directory to a file.
	 * @param fileName to save the directory to
	 * @throws IllegalArgumentException if there is any problem writing to the file.
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

	/**
	 * Method to get a faculty member by their id.
	 * @param id of the desired faculty 
	 * @return f the faculty member object, null if the member was not found 
	 */
	public Faculty getFacultyById(String id) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			Faculty f = facultyDirectory.get(i);
			if (f.getId().equals(id)) {
				return f;
			}
		}
		return null;
	}

	/**
	 * Hashes a String according to the SHA-256 algorithm, and outputs the digest in
	 * base64 encoding. This allows the encoded digest to be safely copied, as it
	 * only uses [a-zA-Z0-9+/=].
	 * 
	 * @param toHash the String to hash
	 * @return the encoded digest of the hash algorithm in base64
	 * @throws IllegalArgumentException if password can't be hashed
	 */
	private static String hashString(String toHash) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(toHash.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
}
