package services;

import java.util.List;

import dao.AdminDAO;
import dao.AdminDAOImplementation;
import dao.UserDAO;
import dao.UserDAOImplementation;
import supereats.User;
import supereats.Role;

public class UserService {
    private UserDAO userDAO;
    private AdminDAO adminDAO;

    public UserService() {
        this.userDAO = new UserDAOImplementation();
        this.adminDAO = new AdminDAOImplementation();
    }

    // Register a new user
    public boolean registerUser(User user) {
        // Check if the email already exists
        User existingUser = userDAO.getUserByEmail(user.getEmail());
        if (existingUser != null) {
            System.out.println("Email already exists.");
            return false; // Registration failed
        }

        // Set default role if none is provided (assuming USER by default)
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        // For MVP: store the password in plain text
        userDAO.createUser(user);
        System.out.println("User registered successfully.");
        return true;
    }

    // Authenticate user login
//    public boolean loginUser(String email, String password) {
//        User user = userDAO.getUserByEmail(email);
//        if (user != null && user.getPassword().equals(password)) { // Plain text check for MVP
//            System.out.println("Login successful for user: " + email);
//            return true;
//        }
//        System.out.println("Invalid credentials for user: " + email);
//        return false;
//    }
    
    public User loginUser(String email, String password) {
        // First, try to find the user in the `User` table
        User user = userDAO.getUserByEmail(email);

        if (user == null) {
            // If not found, try to find the user in the `Admin` table
            user = adminDAO.getAdminByEmailReturnUser(email);
            if (user != null) {
                user.setRole(Role.ADMIN); // Set role as ADMIN if user is in the Admin table
            }
        }

        // Check if the user is found and password matches
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null; // Return null if no user or incorrect password
        }
    }


    // Retrieve user profile details
    public User getUserProfile(int userId) {
        return userDAO.getUserById(userId);
    }

    // Update user profile
    public boolean updateUserProfile(User updatedUser) {
        User existingUser = userDAO.getUserById(updatedUser.getUserId());
        if (existingUser == null) {
            System.out.println("User not found.");
            return false; // Update failed
        }

        // Only update fields that are not null in the provided updatedUser
        if (updatedUser.getName() != null) {
            existingUser.setName(updatedUser.getName());
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getDietaryPreferences() != null) {
            existingUser.setDietaryPreferences(updatedUser.getDietaryPreferences());
        }
        if (updatedUser.getProfilePicture() != null) {
            existingUser.setProfilePicture(updatedUser.getProfilePicture());
        }
        
        userDAO.updateUser(existingUser);
        System.out.println("User profile updated successfully.");
        return true;
    }

	public User getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}

	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}
}
