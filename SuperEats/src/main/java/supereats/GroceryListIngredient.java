package supereats;

public class GroceryListIngredient {
    private int listIngredientId;
    private int groceryListId;
    private int ingredientId;
    private double quantity;
    private String unit;

    // Constructor matching (groceryListId, ingredientId, quantity, unit)
    public GroceryListIngredient(int groceryListId, int ingredientId, double quantity, String unit) {
        this.groceryListId = groceryListId;
        this.ingredientId = ingredientId;
        this.quantity = quantity;
        this.unit = unit;
    }
    
    public GroceryListIngredient(int listIngredientId, int groceryListId, int ingredientId, double quantity, String unit) {
        this.listIngredientId = listIngredientId;
        this.groceryListId = groceryListId;
        this.ingredientId = ingredientId;
        this.quantity = quantity;
        this.unit = unit;
    }

    // Getters and Setters
    public int getListIngredientId() {
        return listIngredientId;
    }

    public void setListIngredientId(int listIngredientId) {
        this.listIngredientId = listIngredientId;
    }

    public int getGroceryListId() {
        return groceryListId;
    }

    public void setGroceryListId(int groceryListId) {
        this.groceryListId = groceryListId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
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

    public String getDetails() {
        return "Ingredient ID: " + ingredientId + ", Quantity: " + quantity + " " + unit;
    }
}
