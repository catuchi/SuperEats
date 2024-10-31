package dao;

import java.util.List;

import supereats.Recipe;

public interface RecipeDAO {
	void createRecipe(Recipe recipe);
	Recipe getRecipeById(int recipeId);
	List<Recipe> getRecipesByCuisine(String cuisineType);
	List<Recipe> getApprovedRecipes();
	void updateRecipe(Recipe recipe);
	void deleteRecipe(int recipeId);
	List<Recipe> getAllRecipes();
	List<Recipe> findRecipesByIngredient(String ingredient);
    List<Recipe> findRecipesByCuisine(String cuisine);
    List<Recipe> findRecipesByTitle(String title);
}
