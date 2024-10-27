package supereats;

public class User extends Account{
	private String dietaryPreferences;
	private String profilePicture;
	
	public User(int userId, String name, String email, String password, Role role, String dietaryPreferences, String profilePicture) {
		super(userId, name, email, password, role);
		this.setDietaryPreferences(dietaryPreferences);
		this.setProfilePicture(profilePicture);
	}

	@Override
	public void register() {
		// TODO validate user details (check if email already exists)
		// TODO hash password
		// TODO save user details to database
		System.out.println("User registered successfully: " + getEmail());
	}

	@Override
	public void login() {
		// TODO check email and hashed password with ones stored in the database
		// TODO log user in (might also have to initialize a session or token)
		System.out.println("User logged in: " + getEmail());
	}

	@Override
	public void logout() {
		// TODO delete session or token (if one was created)
		System.out.println("User logged out: " + getEmail());
	}
	
	public void updateProfile(String newName, String newEmail, String newDietaryPreferences, String newProfilePicture) {
		if (newName != null && !newName.isEmpty()) {
	        setName(newName);
	    }
	    
	    if (newEmail != null && !newEmail.isEmpty()) {
	        // TODO: Check if the new email is unique if stored in a database
	        setEmail(newEmail);
	    }
	    
	    if (newDietaryPreferences != null && !newDietaryPreferences.isEmpty()) {
	        setDietaryPreferences(newDietaryPreferences);
	    }
	    
	    if (newProfilePicture != null && !newProfilePicture.isEmpty()) {
	        setProfilePicture(newProfilePicture);
	    }
	    
	    // TODO: Save updated profile to the database
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
}
