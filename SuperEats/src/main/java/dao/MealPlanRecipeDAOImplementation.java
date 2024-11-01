package dao;

import supereats.MealPlanRecipe;
import supereats.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MealPlanRecipeDAOImplementation implements MealPlanRecipeDAO {

    @Override
    public void saveMealPlanRecipe(MealPlanRecipe mealPlanRecipe) {
        String sql = "INSERT INTO MealPlanRecipe (mealPlanId, recipeId) VALUES (?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mealPlanRecipe.getMealPlanId());
            stmt.setInt(2, mealPlanRecipe.getRecipeId());
            stmt.executeUpdate();
            System.out.println("MealPlanRecipe added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MealPlanRecipe> getRecipesByMealPlanId(int mealPlanId) {
        String sql = "SELECT * FROM MealPlanRecipe WHERE mealPlanId = ?";
        List<MealPlanRecipe> mealPlanRecipes = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mealPlanId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                mealPlanRecipes.add(mapResultSetToMealPlanRecipe(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mealPlanRecipes;
    }

    @Override
    public void deleteByMealPlanId(int mealPlanId) {
        String sql = "DELETE FROM MealPlanRecipe WHERE mealPlanId = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mealPlanId);
            stmt.executeUpdate();
            System.out.println("All MealPlanRecipe entries deleted for mealPlanId: " + mealPlanId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByMealPlanAndRecipeId(int mealPlanId, int recipeId) {
        String sql = "DELETE FROM MealPlanRecipe WHERE mealPlanId = ? AND recipeId = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mealPlanId);
            stmt.setInt(2, recipeId);
            stmt.executeUpdate();
            System.out.println("MealPlanRecipe entry deleted for mealPlanId: " + mealPlanId + " and recipeId: " + recipeId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void addRecipeToMealPlan(int mealPlanId, int recipeId) {
        saveMealPlanRecipe(new MealPlanRecipe(mealPlanId, recipeId));
    }

    @Override
    public void removeRecipeFromMealPlan(int mealPlanId, int recipeId) {
        deleteByMealPlanAndRecipeId(mealPlanId, recipeId);
    }


    private MealPlanRecipe mapResultSetToMealPlanRecipe(ResultSet rs) throws SQLException {
        int mealPlanRecipeId = rs.getInt("mealPlanRecipeId");
        int mealPlanId = rs.getInt("mealPlanId");
        int recipeId = rs.getInt("recipeId");

        return new MealPlanRecipe(mealPlanRecipeId, mealPlanId, recipeId);
    }
}
