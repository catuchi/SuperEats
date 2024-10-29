package supereats;

public class Ingredient {
    private int ingredientId;
    private String name;
    private double quantity; // e.g., 2, 200, 0.5, etc.
    private String unit; // e.g., grams, cups, ounces, etc.

    // Constructors
    public Ingredient(int ingredientId, String name, double quantity, String unit) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Ingredient(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Ingredient(int ingredientId, String name) {
    	this.ingredientId = ingredientId;
        this.name = name;
    }
    
    public Ingredient(String name) {
        this.name = name;
    }

    // Getters and Setters
    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    // Method to return ingredient details
    public String getDetails() {
        String details = name;
        if (quantity > 0) {
            details += " - " + quantity + " " + unit;
        }
        return details;
    }
}
