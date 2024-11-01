package dao;

import supereats.GroceryList;
import supereats.GroceryListIngredient;
import supereats.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroceryListDAOImplementation implements GroceryListDAO {
	
	@Override
    public List<GroceryList> getAllGroceryLists() {
        List<GroceryList> groceryLists = new ArrayList<>();
        String sql = "SELECT * FROM GroceryList";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int listId = rs.getInt("listId");
                int userId = rs.getInt("userId");

                GroceryList groceryList = new GroceryList(userId);
                groceryList.setListId(listId);
                groceryLists.add(groceryList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groceryLists;
    }

    @Override
    public void createGroceryList(GroceryList groceryList) {
        String sql = "INSERT INTO GroceryList (userId) VALUES (?)";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, groceryList.getUserId());
            stmt.executeUpdate();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                groceryList.setListId(generatedKeys.getInt(1));
            }
            
            saveIngredientsForGroceryList(groceryList); // Save ingredients separately
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GroceryList getGroceryListById(int listId) {
        String sql = "SELECT * FROM GroceryList WHERE listId = ?";
        GroceryList groceryList = null;
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, listId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int userId = rs.getInt("userId");
                groceryList = new GroceryList(userId);
                groceryList.setListId(listId);
                groceryList.setIngredients(loadIngredientsForGroceryList(listId)); // Load ingredients
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return groceryList;
    }

    @Override
    public List<GroceryList> getGroceryListsByUserId(int userId) {
        String sql = "SELECT * FROM GroceryList WHERE userId = ?";
        List<GroceryList> groceryLists = new ArrayList<>();
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int listId = rs.getInt("listId");
                GroceryList groceryList = new GroceryList(userId);
                groceryList.setListId(listId);
                groceryList.setIngredients(loadIngredientsForGroceryList(listId)); // Load ingredients
                groceryLists.add(groceryList);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return groceryLists;
    }

    @Override
    public void updateGroceryList(GroceryList groceryList) {
        // Only updating ingredients for simplicity
        deleteIngredientsForGroceryList(groceryList.getListId());
        saveIngredientsForGroceryList(groceryList);
    }

    @Override
    public void deleteGroceryList(int listId) {
        deleteIngredientsForGroceryList(listId); // Delete associated ingredients first
        
        String sql = "DELETE FROM GroceryList WHERE listId = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, listId);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to save ingredients for a grocery list
    private void saveIngredientsForGroceryList(GroceryList groceryList) {
        String sql = "INSERT INTO GroceryListIngredient (listId, ingredientId, quantity, unit) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            for (GroceryListIngredient ingredient : groceryList.getIngredients()) {
                stmt.setInt(1, groceryList.getListId());
                stmt.setInt(2, ingredient.getIngredientId());
                stmt.setDouble(3, ingredient.getQuantity());
                stmt.setString(4, ingredient.getUnit());
                stmt.addBatch();
            }
            
            stmt.executeBatch();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to load ingredients for a grocery list
    private ArrayList<GroceryListIngredient> loadIngredientsForGroceryList(int listId) {
        ArrayList<GroceryListIngredient> ingredients = new ArrayList<>();
        String sql = "SELECT * FROM GroceryListIngredient WHERE listId = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, listId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int ingredientId = rs.getInt("ingredientId");
                double quantity = rs.getDouble("quantity");
                String unit = rs.getString("unit");
                GroceryListIngredient ingredient = new GroceryListIngredient(listId, ingredientId, quantity, unit);
                ingredients.add(ingredient);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ingredients;
    }

    // Helper method to delete ingredients for a grocery list
    private void deleteIngredientsForGroceryList(int listId) {
        String sql = "DELETE FROM GroceryListIngredient WHERE listId = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, listId);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
