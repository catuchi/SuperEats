package dao;

import supereats.MealPlan;
import java.util.List;

public interface MealPlanDAO {
    void createMealPlan(MealPlan mealPlan);
    MealPlan getMealPlanById(int mealPlanId);
    List<MealPlan> getMealPlansByUserId(int userId);
    void updateMealPlan(MealPlan mealPlan);
    void deleteMealPlan(int mealPlanId);
    List<MealPlan> getAllMealPlans();
}
