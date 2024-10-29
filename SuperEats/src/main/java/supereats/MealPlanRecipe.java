package supereats;

public class MealPlanRecipe {
    private int mealPlanRecipeId;
    private int mealPlanId;
    private int recipeId;

    public MealPlanRecipe(int mealPlanId, int recipeId) {
        this.mealPlanId = mealPlanId;
        this.recipeId = recipeId;
    }

    // Getters and Setters
    public int getMealPlanRecipeId() {
        return mealPlanRecipeId;
    }

    public void setMealPlanRecipeId(int mealPlanRecipeId) {
        this.mealPlanRecipeId = mealPlanRecipeId;
    }

    public int getMealPlanId() {
        return mealPlanId;
    }

    public void setMealPlanId(int mealPlanId) {
        this.mealPlanId = mealPlanId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}
