package services;

import dao.MealPlanDAO;
import dao.MealPlanDAOImplementation;
import dao.MealPlanRecipeDAO;
import dao.MealPlanRecipeDAOImplementation;
import supereats.MealPlan;
import supereats.Recipe;

import java.util.List;

public class MealPlanService {
    private final MealPlanDAO mealPlanDAO;
    private final MealPlanRecipeDAO mealPlanRecipeDAO;

    public MealPlanService(MealPlanDAO mealPlanDAO, MealPlanRecipeDAO mealPlanRecipeDAO) {
        this.mealPlanDAO = mealPlanDAO;
        this.mealPlanRecipeDAO = mealPlanRecipeDAO;
    }

    public MealPlanService() {
    	this.mealPlanDAO = new MealPlanDAOImplementation();
        this.mealPlanRecipeDAO = new MealPlanRecipeDAOImplementation();
	}
    
    public List<MealPlan> getAllMealPlans() {
        return mealPlanDAO.getAllMealPlans();
    }

	public void addMealPlan(MealPlan mealPlan) {
        mealPlanDAO.createMealPlan(mealPlan);
        System.out.println("Meal plan created successfully: " + mealPlan.getName());
    }
	
	public void createMealPlan(MealPlan mealPlan) {
        mealPlanDAO.createMealPlan(mealPlan);
        System.out.println("Meal plan created successfully: " + mealPlan.getName());
    }

    public void updateMealPlan(MealPlan mealPlan) {
        MealPlan existingMealPlan = mealPlanDAO.getMealPlanById(mealPlan.getMealPlanId());
        if (existingMealPlan != null) {
            mealPlanDAO.updateMealPlan(mealPlan);
            System.out.println("Meal plan updated: " + mealPlan.getName());
        } else {
            System.out.println("Meal plan not found with ID: " + mealPlan.getMealPlanId());
        }
    }

    public void deleteMealPlan(int mealPlanId) {
        MealPlan mealPlan = mealPlanDAO.getMealPlanById(mealPlanId);
        if (mealPlan != null) {
            mealPlanRecipeDAO.deleteByMealPlanId(mealPlanId);
            mealPlanDAO.deleteMealPlan(mealPlanId);
            System.out.println("Meal plan deleted: " + mealPlan.getName());
        } else {
            System.out.println("Meal plan not found with ID: " + mealPlanId);
        }
    }

    public MealPlan getMealPlanById(int mealPlanId) {
        return mealPlanDAO.getMealPlanById(mealPlanId);
    }

    public List<MealPlan> getMealPlansByUserId(int userId) {
        return mealPlanDAO.getMealPlansByUserId(userId);
    }

    public void addRecipeToMealPlan(int mealPlanId, Recipe recipe) {
        mealPlanRecipeDAO.addRecipeToMealPlan(mealPlanId, recipe.getRecipeId());
        System.out.println("Recipe added to meal plan: " + recipe.getTitle());
    }

    public void removeRecipeFromMealPlan(int mealPlanId, int recipeId) {
        mealPlanRecipeDAO.removeRecipeFromMealPlan(mealPlanId, recipeId);
        System.out.println("Recipe removed from meal plan with ID: " + mealPlanId);
    }

    public void generateGroceryList(MealPlan mealPlan) {
        System.out.println("Grocery list generated for meal plan: " + mealPlan.getName());
    }
}
