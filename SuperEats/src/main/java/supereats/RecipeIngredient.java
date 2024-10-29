package supereats;

import java.sql.*;

public class RecipeIngredient {
    private int recipeIngredientId;
    private int recipeId;
    private Ingredient ingredient; // Reference to the Ingredient object
    private double quantity;
    private String unit;

    // Constructors
    public RecipeIngredient(int recipeIngredientId, int recipeId, int ingredientId, double quantity, String unit) {
        this.recipeIngredientId = recipeIngredientId;
        this.recipeId = recipeId;
        this.quantity = quantity;
        this.unit = unit;

        this.ingredient = loadIngredientById(ingredientId);
    }
    
    public RecipeIngredient(int recipeId, Ingredient ingredient, double quantity, String unit) {
        this.recipeId = recipeId;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

	// Getters and Setters
    public int getRecipeIngredientId() {
        return recipeIngredientId;
    }

    public void setRecipeIngredientId(int recipeIngredientId) {
        this.recipeIngredientId = recipeIngredientId;
    }
    
    public int getIngredientId() {
        return ingredient != null ? ingredient.getIngredientId() : -1; // returns -1 if ingredient is null as our SQL statement has NOT NULL for this table
    }

    public int getRecipeId() {
        return recipeId;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    // Method to return ingredient details, including name and quantity
    public String getDetails() {
        return "Ingredient: " + ingredient.getName() + ", Quantity: " + quantity + " " + unit;
    }
    
    private Ingredient loadIngredientById(int ingredientId) {
        Ingredient ingredient = null;
        String sql = "SELECT * FROM Ingredient WHERE ingredientId = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, ingredientId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                ingredient = new Ingredient(ingredientId, name); // Assuming `Ingredient` has this constructor
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ingredient;
    }
}
