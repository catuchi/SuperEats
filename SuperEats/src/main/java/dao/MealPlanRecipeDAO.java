package dao;

import supereats.MealPlanRecipe;
import java.util.List;

public interface MealPlanRecipeDAO {
    void saveMealPlanRecipe(MealPlanRecipe mealPlanRecipe);
    List<MealPlanRecipe> getRecipesByMealPlanId(int mealPlanId);
    void deleteByMealPlanId(int mealPlanId);
    void deleteByMealPlanAndRecipeId(int mealPlanId, int recipeId);
    void addRecipeToMealPlan(int mealPlanId, int recipeId);
    void removeRecipeFromMealPlan(int mealPlanId, int recipeId);
}
