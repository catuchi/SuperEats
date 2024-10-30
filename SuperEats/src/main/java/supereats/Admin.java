package supereats;

import dao.AdminDAO;
import dao.AdminDAOImplementation;
import dao.UserDAO;
import dao.UserDAOImplementation;
import dao.RecipeDAO;
import dao.RecipeDAOImplementation;

public class Admin extends Account {
    private final AdminDAO adminDAO = new AdminDAOImplementation(); // For admin-specific database interactions
    private final UserDAO userDAO = new UserDAOImplementation();    // For user management
    private final RecipeDAO recipeDAO = new RecipeDAOImplementation(); // For recipe management

    public Admin(String name, String email, String password, Role role) {
        super(name, email, password, role);
    }

    public Admin(int userId, String name, String email, String password, Role role) {
        super(userId, name, email, password, role);
    }

    @Override
    public void register() {
        // Delegate admin registration to AdminDAO
        if (adminDAO.getAdminByEmail(getEmail()) == null) { // Check for unique email
            adminDAO.createAdmin(this);
            System.out.println("Admin registered: " + getEmail());
        } else {
            System.out.println("Registration failed: Email already in use.");
        }
    }

    @Override
    public void login() {
        // Check admin credentials via AdminDAO
        Admin adminFromDB = adminDAO.getAdminByEmail(getEmail());
        if (adminFromDB != null && adminFromDB.getPassword().equals(getPassword())) {
            System.out.println("Admin logged in: " + getEmail());
        } else {
            System.out.println("Login failed: Invalid credentials.");
        }
    }

    @Override
    public void logout() {
        // No changes needed here as logout remains session-specific
        System.out.println("Admin logged out: " + getEmail());
    }

    public void approveRecipe(Recipe recipe) {
        // Approve recipe and update in the database using RecipeDAO
        recipe.setApproved(true);
        recipeDAO.updateRecipe(recipe);
        System.out.println("Recipe approved: " + recipe.getTitle());
    }

    public void deleteUser(User user) {
        // Delete user from the database via UserDAO
        userDAO.deleteUser(user.getUserId());
        System.out.println("User deleted: " + user.getEmail());
    }
}
