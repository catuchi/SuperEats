package supereats;

import java.util.ArrayList;
import java.util.HashMap;

public class GroceryList {

	private int listId;
    private int userId;
    private ArrayList<GroceryListIngredient> ingredients = new ArrayList<>();

    // Constructor without listId, useful when creating a new GroceryList in the database
	public GroceryList(int userId) {
        this.userId = userId;
    }

    // Constructor with listId, used when loading a GroceryList from the database
    public GroceryList(int listId, int userId, ArrayList<GroceryListIngredient> ingredients) {
        this.listId = listId;
        this.userId = userId;
        this.ingredients = ingredients;
    }

    // Method to generate a grocery list from a list of recipes
    public void generateList(ArrayList<Recipe> recipes) {
        HashMap<String, GroceryListIngredient> ingredientMap = new HashMap<>();

        for (Recipe recipe : recipes) {
            for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients()) {
                String ingredientName = recipeIngredient.getIngredient().getName();
                
                if (ingredientMap.containsKey(ingredientName)) {
                    // If ingredient already exists, update the quantity
                    GroceryListIngredient existingIngredient = ingredientMap.get(ingredientName);
                    existingIngredient.setQuantity(existingIngredient.getQuantity() + recipeIngredient.getQuantity());
                } else {
                    // Add new GroceryListIngredient with initial quantity and unit
                    GroceryListIngredient newIngredient = new GroceryListIngredient(
                        listId, // Ensure listId is set before calling this
                        recipeIngredient.getIngredient().getIngredientId(), 
                        recipeIngredient.getQuantity(), 
                        recipeIngredient.getUnit()
                    );
                    ingredientMap.put(ingredientName, newIngredient);
                }
            }
        }
        
        // Convert map to ArrayList and assign to ingredients
        ingredients = new ArrayList<>(ingredientMap.values());
    }

    // Method to add a GroceryListIngredient directly
    public void addIngredient(GroceryListIngredient ingredient) {
        ingredients.add(ingredient);
    }

    // Method to remove a GroceryListIngredient
    public void removeIngredient(GroceryListIngredient ingredient) {
        ingredients.remove(ingredient);
    }

    // Getter for ingredients
    public ArrayList<GroceryListIngredient> getIngredients() {
        return ingredients;
    }
    
    public void setIngredients(ArrayList<GroceryListIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
    public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}
}
