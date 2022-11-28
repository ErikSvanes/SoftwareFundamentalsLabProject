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
	 * 
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}

	/**
	 * 
	 */
	public void newFacultyDirectory() {
		this.facultyDirectory = new LinkedList<Faculty>();
	}

	/**
	 * 
	 * @param fileName
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param id
	 * @param email
	 * @param password
	 * @param maxCourses
	 * @return
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
	 * 
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
	 * 
	 * @return
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
	 * 
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
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
