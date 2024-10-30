package dao;

import supereats.DatabaseUtil;
import supereats.RecipeIngredient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeIngredientDAOImplementation implements RecipeIngredientDAO {

    @Override
    public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
        String sql = "INSERT INTO RecipeIngredient (recipeId, ingredientId, quantity, unit) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recipeIngredient.getRecipeId());
            stmt.setInt(2, recipeIngredient.getIngredientId());
            stmt.setDouble(3, recipeIngredient.getQuantity());
            stmt.setString(4, recipeIngredient.getUnit());

            stmt.executeUpdate();
            System.out.println("RecipeIngredient added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RecipeIngredient> getIngredientsByRecipeId(int recipeId) {
        String sql = "SELECT * FROM RecipeIngredient WHERE recipeId = ?";
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recipeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                recipeIngredients.add(mapResultSetToRecipeIngredient(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeIngredients;
    }

    @Override
    public void updateRecipeIngredient(RecipeIngredient recipeIngredient) {
        String sql = "UPDATE RecipeIngredient SET quantity = ?, unit = ? WHERE recipeIngredientId = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, recipeIngredient.getQuantity());
            stmt.setString(2, recipeIngredient.getUnit());
            stmt.setInt(3, recipeIngredient.getRecipeIngredientId());

            stmt.executeUpdate();
            System.out.println("RecipeIngredient updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRecipeIngredient(int recipeIngredientId) {
        String sql = "DELETE FROM RecipeIngredient WHERE recipeIngredientId = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recipeIngredientId);
            stmt.executeUpdate();
            System.out.println("RecipeIngredient deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void removeRecipeIngredient(RecipeIngredient recipeIngredient) {
        String sql = "DELETE FROM RecipeIngredient WHERE recipeIngredientId = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recipeIngredient.getRecipeIngredientId());
            stmt.executeUpdate();

            System.out.println("RecipeIngredient removed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to map ResultSet to RecipeIngredient object
    private RecipeIngredient mapResultSetToRecipeIngredient(ResultSet rs) throws SQLException {
        int recipeIngredientId = rs.getInt("recipeIngredientId");
        int recipeId = rs.getInt("recipeId");
        int ingredientId = rs.getInt("ingredientId");
        double quantity = rs.getDouble("quantity");
        String unit = rs.getString("unit");

        return new RecipeIngredient(recipeIngredientId, recipeId, ingredientId, quantity, unit);
    }
}
