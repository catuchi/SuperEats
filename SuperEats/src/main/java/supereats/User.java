package supereats;

import dao.UserDAO;
import dao.UserDAOImplementation;

public class User extends Account {
	private String dietaryPreferences;
	private String profilePicture;
	private final UserDAO userDAO = new UserDAOImplementation(); // DAO instance for database operations

	public User(String name, String email, String password, Role role, String dietaryPreferences,
			String profilePicture) {
		super(name, email, password, role);
		this.dietaryPreferences = dietaryPreferences;
		this.profilePicture = profilePicture;
	}

	public User(int userId, String name, String email, String password, Role role, String dietaryPreferences,
			String profilePicture) {
		super(userId, name, email, password, role);
		this.dietaryPreferences = dietaryPreferences;
		this.profilePicture = profilePicture;
	}

	public User(String name, String email, String password, String dietaryPreferences, String profilePicture) {
		super(name, email, password, Role.USER);
		this.dietaryPreferences = dietaryPreferences;
		this.profilePicture = profilePicture;
	}

	@Override
	public void register() {
		// Delegate the responsibility of saving the user to the UserDAO
		if (userDAO.getUserByEmail(getEmail()) == null) { // Check if the email is unique
			userDAO.createUser(this);
			System.out.println("User registered successfully: " + getEmail());
		} else {
			System.out.println("Registration failed: Email already in use.");
		}
	}

	@Override
	public void login() {
		// Fetch user by email and validate password
		User userFromDB = userDAO.getUserByEmail(getEmail());
		if (userFromDB != null && userFromDB.getPassword().equals(getPassword())) { // Password hashing should be here
			System.out.println("User logged in: " + getEmail());
		} else {
			System.out.println("Login failed: Invalid credentials.");
		}
	}

	@Override
	public void logout() {
		// Retain as-is since logout is typically a session-related operation
		System.out.println("User logged out: " + getEmail());
	}

	public void updateProfile(String newName, String newEmail, String newDietaryPreferences, String newProfilePicture) {
		if (newName != null && !newName.isEmpty()) {
			setName(newName);
		}

		if (newEmail != null && !newEmail.isEmpty() && userDAO.getUserByEmail(newEmail) == null) { // Ensure unique
																									// email
			setEmail(newEmail);
		}

		if (newDietaryPreferences != null && !newDietaryPreferences.isEmpty()) {
			setDietaryPreferences(newDietaryPreferences);
		}

		if (newProfilePicture != null && !newProfilePicture.isEmpty()) {
			setProfilePicture(newProfilePicture);
		}

		// Delegate saving the updated profile to the UserDAO
		userDAO.updateUser(this);
		System.out.println("Profile updated for user: " + getEmail());
	}

	public String getDietaryPreferences() {
		return dietaryPreferences;
	}

	public void setDietaryPreferences(String dietaryPreferences) {
		this.dietaryPreferences = dietaryPreferences;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public void setRole(Role role) {
		super.setRole(role);
	}
}
