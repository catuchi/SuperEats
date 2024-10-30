package supereats;

import dao.MealPlanDAOImplementation;
import dao.MealPlanRecipeDAOImplementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class MealPlan {
    private int mealPlanId;
    private int userId;
    private String name;
    private ArrayList<MealPlanRecipe> mealPlanRecipes = new ArrayList<>();
    private LocalDate startDate;
    private LocalDate endDate;
    
    private MealPlanDAOImplementation mealPlanDAO = new MealPlanDAOImplementation();
    private MealPlanRecipeDAOImplementation mealPlanRecipeDAO = new MealPlanRecipeDAOImplementation();

    // Constructors
    public MealPlan(int userId, String name, LocalDate startDate, LocalDate endDate) {
        this.userId = userId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public MealPlan(int mealPlanId, int userId, String name, LocalDate startDate, LocalDate endDate) {
    	this.mealPlanId = mealPlanId;
    	this.userId = userId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

	// Getters and Setters
    public int getMealPlanId() {
        return mealPlanId;
    }

    public void setMealPlanId(int mealPlanId) {
        this.mealPlanId = mealPlanId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MealPlanRecipe> getMealPlanRecipes() {
        if (mealPlanRecipes.isEmpty()) {
            mealPlanRecipes = loadMealPlanRecipesFromDB();
        }
        return mealPlanRecipes;
    }

    public void setMealPlanRecipes(ArrayList<MealPlanRecipe> mealPlanRecipes) {
        this.mealPlanRecipes = mealPlanRecipes;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    // Methods to add/remove MealPlanRecipe entries
    public void addMealPlanRecipe(MealPlanRecipe mealPlanRecipe) {
        mealPlanRecipes.add(mealPlanRecipe);
        mealPlanRecipeDAO.saveMealPlanRecipe(mealPlanRecipe);
    }

    public void removeMealPlanRecipe(MealPlanRecipe mealPlanRecipe) {
        mealPlanRecipes.remove(mealPlanRecipe);
        mealPlanRecipeDAO.deleteByMealPlanAndRecipeId(this.mealPlanId, mealPlanRecipe.getRecipeId());
    }

    // Helper method to load mealPlanRecipes from the database
    private ArrayList<MealPlanRecipe> loadMealPlanRecipesFromDB() {
        return new ArrayList<>(mealPlanRecipeDAO.getRecipesByMealPlanId(this.mealPlanId));
    }
    
    // Method to create meal plan in the database
    public void createMealPlan() {
        mealPlanDAO.createMealPlan(this);
    }
    
    // Method to delete the meal plan and associated MealPlanRecipe entries
    public void deleteMealPlan() {
        deleteMealPlanRecipesFromDB();
        mealPlanDAO.deleteMealPlan(this.mealPlanId);
    }

    private void deleteMealPlanRecipesFromDB() {
        mealPlanRecipeDAO.deleteByMealPlanId(this.mealPlanId);
    }
    
    public void addRecipeToPlan(Recipe recipe) {
        MealPlanRecipe mealPlanRecipe = new MealPlanRecipe(this.mealPlanId, recipe.getRecipeId());
        addMealPlanRecipe(mealPlanRecipe);
    }
    
    public void removeRecipeFromPlan(Recipe recipe) {
        mealPlanRecipes.removeIf(mpr -> mpr.getRecipeId() == recipe.getRecipeId());
        mealPlanRecipeDAO.deleteByMealPlanAndRecipeId(this.mealPlanId, recipe.getRecipeId());
    }

    public void generateGroceryList() {
        HashMap<String, Double> groceryList = new HashMap<>(); // Example grocery list logic

        for (MealPlanRecipe mealPlanRecipe : mealPlanRecipes) {
            Recipe recipe = null; // TODO: Retrieve Recipe by recipeId from mealPlanRecipe
            for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients()) {
                groceryList.merge(recipeIngredient.getIngredient().getName(), recipeIngredient.getQuantity(), Double::sum);
            }
        }

        // Save or print the generated grocery list
    }
}
