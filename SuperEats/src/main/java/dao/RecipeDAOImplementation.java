package dao;

import supereats.DatabaseUtil;
import supereats.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAOImplementation implements RecipeDAO {

    @Override
    public void createRecipe(Recipe recipe) {
        String sql = "INSERT INTO Recipe (title, description, instructions, cuisineType, prepTime, calories) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, recipe.getTitle());
            stmt.setString(2, recipe.getDescription());
            stmt.setString(3, recipe.getInstructions());
            stmt.setString(4, recipe.getCuisineType());
            stmt.setInt(5, recipe.getPrepTime());
            stmt.setInt(6, recipe.getCalories());

            stmt.executeUpdate();
            System.out.println("Recipe created successfully with 'approved' set to default (false).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Recipe getRecipeById(int recipeId) {
        String sql = "SELECT * FROM Recipe WHERE recipeId = ?";
        Recipe recipe = null;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recipeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                recipe = mapResultSetToRecipe(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipe;
    }

    @Override
    public List<Recipe> getRecipesByCuisine(String cuisineType) {
        String sql = "SELECT * FROM Recipe WHERE cuisineType = ?";
        List<Recipe> recipes = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cuisineType);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                recipes.add(mapResultSetToRecipe(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipes;
    }

    @Override
    public List<Recipe> getApprovedRecipes() {
        String sql = "SELECT * FROM Recipe WHERE approved = TRUE";
        List<Recipe> recipes = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                recipes.add(mapResultSetToRecipe(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipes;
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        StringBuilder sql = new StringBuilder("UPDATE Recipe SET ");
        List<Object> params = new ArrayList<>();

        if (recipe.getTitle() != null) {
            sql.append("title = ?, ");
            params.add(recipe.getTitle());
        }
        if (recipe.getDescription() != null) {
            sql.append("description = ?, ");
            params.add(recipe.getDescription());
        }
        if (recipe.getInstructions() != null) {
            sql.append("instructions = ?, ");
            params.add(recipe.getInstructions());
        }
        if (recipe.getCuisineType() != null) {
            sql.append("cuisineType = ?, ");
            params.add(recipe.getCuisineType());
        }
        if (recipe.getPrepTime() != 0) { // 0 means not updated
            sql.append("prepTime = ?, ");
            params.add(recipe.getPrepTime());
        }
        if (recipe.getCalories() != 0) { // 0 means not updated
            sql.append("calories = ?, ");
            params.add(recipe.getCalories());
        }
        if (recipe.isApproved() != null) { // checking for a Boolean flag change
            sql.append("approved = ?, ");
            params.add(recipe.isApproved());
        }

        // Remove the trailing comma and space
        sql.setLength(sql.length() - 2);

        sql.append(" WHERE recipeId = ?");
        params.add(recipe.getRecipeId());

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            int rowsUpdated = stmt.executeUpdate();
            System.out.println("Recipe updated. Rows affected: " + rowsUpdated);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteRecipe(int recipeId) {
        String sql = "DELETE FROM Recipe WHERE recipeId = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recipeId);
            stmt.executeUpdate();
            System.out.println("Recipe deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Recipe> getAllRecipes() {
        String sql = "SELECT * FROM Recipe";
        List<Recipe> recipes = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        	
            while (rs.next()) {
            	System.out.println("Row found, adding to recipes list");
                recipes.add(mapResultSetToRecipe(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Recipes fetched from database: " + recipes);	
        return recipes;
    }
    
//    @Override
//    public List<Recipe> getAllRecipes() {
//        List<Recipe> recipes = new ArrayList<>();
//        String sql = "SELECT * FROM Recipe";
//        
//        try (Connection conn = DatabaseUtil.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            
//            while (rs.next()) {
//                Recipe recipe = new Recipe(
//                    rs.getInt("recipeId"),
//                    rs.getString("title"),
//                    rs.getString("description"),
//                    rs.getString("instructions"),
//                    rs.getString("cuisineType"),
//                    rs.getInt("prepTime"),
//                    rs.getInt("calories"),
//                    rs.getBoolean("approved")
//                );
//                recipes.add(recipe);
//            }
//            System.out.println("Recipes fetched from database: " + recipes); // Debugging
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return recipes;
//    }


    private Recipe mapResultSetToRecipe(ResultSet rs) throws SQLException {
        int recipeId = rs.getInt("recipeId");
        String title = rs.getString("title");
        String description = rs.getString("description");
        String instructions = rs.getString("instructions");
        String cuisineType = rs.getString("cuisineType");
        int prepTime = rs.getInt("prepTime");
        int calories = rs.getInt("calories");
        Boolean approved = rs.getBoolean("approved");

        return new Recipe(recipeId, title, description, instructions, cuisineType, prepTime, calories, approved);
    }
}
