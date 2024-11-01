package services;

import dao.RecipeDAO;
import dao.RecipeDAOImplementation;
import dao.RatingDAO;
import dao.RatingDAOImplementation;
import supereats.Recipe;
import supereats.RecipeIngredient;
import supereats.Rating;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeService {
    private final RecipeDAO recipeDAO;
    private final RatingDAO ratingDAO;

    public RecipeService(RecipeDAO recipeDAO, RatingDAO ratingDAO) {
        this.recipeDAO = recipeDAO;
        this.ratingDAO = ratingDAO;
    }
    
    public RecipeService() {
        this.recipeDAO = new RecipeDAOImplementation();
        this.ratingDAO = new RatingDAOImplementation();
    }

    public void addRecipe(Recipe recipe) {
        recipeDAO.createRecipe(recipe);
        System.out.println("Recipe added: " + recipe.getTitle());
    }

    public void updateRecipe(Recipe recipe) {
        Recipe existingRecipe = recipeDAO.getRecipeById(recipe.getRecipeId());
        if (existingRecipe != null) {
            recipeDAO.updateRecipe(recipe);
            System.out.println("Recipe updated: " + recipe.getTitle());
        } else {
            System.out.println("Recipe not found with ID: " + recipe.getRecipeId());
        }
    }

    public void deleteRecipe(int recipeId) {
        Recipe recipe = recipeDAO.getRecipeById(recipeId);
        if (recipe != null) {
            recipeDAO.deleteRecipe(recipeId);
            System.out.println("Recipe deleted: " + recipe.getTitle());
        } else {
            System.out.println("Recipe not found with ID: " + recipeId);
        }
    }

    public Recipe getRecipeById(int recipeId) {
        return recipeDAO.getRecipeById(recipeId);
    }
    
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = recipeDAO.getAllRecipes();
        System.out.println("Recipes from DAO: " + recipes); // Debugging
        return recipes;
    }
    
    public double getAverageRating(int recipeId) {
        return ratingDAO.getAverageRatingByRecipeId(recipeId);
    }

    public List<RecipeIngredient> getRecipeIngredients(int recipeId) {
        Recipe recipe = recipeDAO.getRecipeById(recipeId);
        return recipe != null ? recipe.getRecipeIngredients() : new ArrayList<>();
    }


    public void rateRecipe(int recipeId, int userId, int ratingValue) {
        Recipe recipe = recipeDAO.getRecipeById(recipeId);
        if (recipe != null) {
            Rating rating = new Rating(userId, recipeId, ratingValue);
            ratingDAO.addRating(rating);
            System.out.println("Recipe rated successfully.");
        } else {
            System.out.println("Recipe not found with ID: " + recipeId);
        }
    }
    
    public void addRating(int recipeId, int userId, int ratingValue) {
        Rating rating = new Rating(userId, recipeId, ratingValue);
        ratingDAO.addRating(rating);
    }
    
    public List<Recipe> getFeaturedRecipes() {
        List<Recipe> approvedRecipes = recipeDAO.getApprovedRecipes();
        List<Recipe> featuredRecipes = approvedRecipes.stream()
            .filter(recipe -> {
                double averageRating = ratingDAO.getAverageRatingByRecipeId(recipe.getRecipeId());
                return averageRating >= 1.0; // Criteria: High rating, e.g., 4 or higher
            })
            .collect(Collectors.toList());

        return featuredRecipes;
    }
    
//    public List<Recipe> searchRecipesByIngredient(String ingredient) {
//        return recipeDAO.findRecipesByIngredient(ingredient);
//    }
//
//    public List<Recipe> searchRecipesByCuisine(String cuisine) {
//        return recipeDAO.findRecipesByCuisine(cuisine);
//    }
//
//    public List<Recipe> searchRecipesByTitle(String title) {
//        return recipeDAO.findRecipesByTitle(title);
//    }
    
    public List<Recipe> searchRecipesByIngredient(String ingredient) {
        return recipeDAO.findRecipesByIngredient("%" + ingredient + "%");
    }

    public List<Recipe> searchRecipesByCuisine(String cuisine) {
        return recipeDAO.findRecipesByCuisine("%" + cuisine + "%");
    }

    public List<Recipe> searchRecipesByTitle(String title) {
        return recipeDAO.findRecipesByTitle("%" + title + "%");
    }

}
