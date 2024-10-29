package dao;

import supereats.RecipeIngredient;
import java.util.List;

public interface RecipeIngredientDAO {
    void addRecipeIngredient(RecipeIngredient recipeIngredient);
    List<RecipeIngredient> getIngredientsByRecipeId(int recipeId);
    void updateRecipeIngredient(RecipeIngredient recipeIngredient);
    void deleteRecipeIngredient(int recipeIngredientId);
}
