package services;

import dao.RecipeDAO;
import dao.RecipeDAOImplementation;
import dao.UserDAO;
import dao.UserDAOImplementation;
import supereats.Recipe;
import supereats.User;

import java.util.List;

public class AdminService {
    private final RecipeDAO recipeDAO;
    private final UserDAO userDAO;

    public AdminService(RecipeDAO recipeDAO, UserDAO userDAO) {
        this.recipeDAO = recipeDAO;
        this.userDAO = userDAO;
    }

    public AdminService() {
    	this.recipeDAO = new RecipeDAOImplementation();
        this.userDAO = new UserDAOImplementation();
	}

	public void approveRecipe(int recipeId) {
        Recipe recipe = recipeDAO.getRecipeById(recipeId);
        if (recipe != null) {
            recipe.setApproved(true);
            recipeDAO.updateRecipe(recipe);
            System.out.println("Recipe approved: " + recipe.getTitle());
        } else {
            System.out.println("Recipe not found with ID: " + recipeId);
        }
    }

    public void deleteUser(int userId) {
        User user = userDAO.getUserById(userId);
        if (user != null) {
            userDAO.deleteUser(userId);
            System.out.println("User deleted successfully: " + user.getEmail());
        } else {
            System.out.println("User not found with ID: " + userId);
        }
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void deleteRecipe(int recipeId) {
        Recipe recipe = recipeDAO.getRecipeById(recipeId);
        if (recipe != null) {
            recipeDAO.deleteRecipe(recipeId);
            System.out.println("Recipe deleted successfully: " + recipe.getTitle());
        } else {
            System.out.println("Recipe not found with ID: " + recipeId);
        }
    }
}
