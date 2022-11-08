package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Abstract class to hold User objects.
 * @author devinmowry
 *
 */
public abstract class User {

	/** Students First Name **/
	private String firstName;
	/** Students Last Name **/
	private String lastName;
	/** Students ID **/
	private String id;
	/** Students Email **/
	private String email;
	/** Students Password **/
	private String password;

	/**
	 * Constructor for User object.
	 * @param firstName of user
	 * @param lastName of user
	 * @param id of user
	 * @param email of user
	 * @param password of user
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setId(id);
		this.setEmail(email);
		this.setPassword(password);
	}

	/**
	 * Returns the first name of the student, a string
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the student. Throws an IAE if firstName is 
	 * null or is an empty string
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException if parameters aren't met
	 */
	public void setFirstName(String firstName) {
		if (null == firstName || firstName.length() == 0) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of the student, a string
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the student. If lastName is null or is 
	 * an empty string, an IAE is thrown
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException if parameters aren't met
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.length() == 0) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Returns the id of the student
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id of the student. If id is null or is an empty string, 
	 * an IAE is thrown
	 * @param id the id to set
	 * @throws IllegalArgumentException if parameters aren't met
	 */
	public void setId(String id) {
		if (id == null || id.length() == 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returns the student's email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the student. If email is null or an empty string,
	 * an IAE is thrown. The method initializes two variables to keep track of the
	 * indices of the "@" and the "." The email is iterated through to find the "@" 
	 * and "." indices. If either variables remain 0, then the character was not
	 * found and an IAE is thrown. If the index of "." comes after the "@" then an
	 * IAE is thrown.
	 * @param email the email to set
	 * @throws IllegalArgumentException if parameters aren't met
	 */
	public void setEmail(String email) {
		if (email == null || email.length() == 0) {
			throw new IllegalArgumentException("Invalid email");
		}
		int indexAt = 0;
		int indexDot = 0;
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') {
				indexAt = i;
			} else if  (email.charAt(i) == '.') {
				indexDot = i;
			}
		}
		if (indexAt == 0 || indexDot == 0 || indexAt > indexDot) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
		
	}

	/**
	 * Returns the student's password
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the student. If password is null or is an
	 * empty string an IAE is thrown.
	 * @param password the password to set
	 * @throws IllegalArgumentException if parameters aren't met
	 */
	public void setPassword(String password) {
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}


