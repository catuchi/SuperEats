package dao;

import supereats.DatabaseUtil;
import supereats.Ingredient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAOImplementation implements IngredientDAO {

    @Override
    public void addIngredient(Ingredient ingredient) {
        String sql = "INSERT INTO Ingredient (name) VALUES (?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, ingredient.getName());
            stmt.executeUpdate();
            System.out.println("Ingredient added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ingredient getIngredientById(int ingredientId) {
        String sql = "SELECT * FROM Ingredient WHERE ingredientId = ?";
        Ingredient ingredient = null;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, ingredientId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ingredient = mapResultSetToIngredient(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredient;
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        String sql = "SELECT * FROM Ingredient";
        List<Ingredient> ingredients = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                ingredients.add(mapResultSetToIngredient(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        String sql = "UPDATE Ingredient SET name = ? WHERE ingredientId = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, ingredient.getName());
            stmt.setInt(2, ingredient.getIngredientId());
            stmt.executeUpdate();
            System.out.println("Ingredient updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteIngredient(int ingredientId) {
        String sql = "DELETE FROM Ingredient WHERE ingredientId = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, ingredientId);
            stmt.executeUpdate();
            System.out.println("Ingredient deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to map ResultSet to Ingredient object
    private Ingredient mapResultSetToIngredient(ResultSet rs) throws SQLException {
        int ingredientId = rs.getInt("ingredientId");
        String name = rs.getString("name");

        return new Ingredient(ingredientId, name);
    }
}
