package supereats;

public class RecipeIngredient {
    private int recipeIngredientId;
    private int recipeId;
    private Ingredient ingredient; // Reference to the Ingredient object
    private double quantity;
    private String unit;

    // Constructors
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
}
