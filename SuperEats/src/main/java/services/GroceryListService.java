package services;

import dao.GroceryListDAO;
import dao.GroceryListDAOImplementation;
import dao.GroceryListIngredientDAO;
import dao.GroceryListIngredientDAOImplementation;
import supereats.GroceryList;
import supereats.GroceryListIngredient;
import supereats.Recipe;
import supereats.RecipeIngredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroceryListService {

    private GroceryListDAO groceryListDAO;
    private GroceryListIngredientDAO groceryListIngredientDAO;

    public GroceryListService(GroceryListDAO groceryListDAO, GroceryListIngredientDAO groceryListIngredientDAO) {
        this.groceryListDAO = groceryListDAO;
        this.groceryListIngredientDAO = groceryListIngredientDAO;
    }

    public GroceryListService() {
    	this.groceryListDAO = new GroceryListDAOImplementation();
        this.groceryListIngredientDAO = new GroceryListIngredientDAOImplementation();
	}
    
    public List<GroceryList> getAllGroceryLists() {
        return groceryListDAO.getAllGroceryLists();
    }

	// Method to create a new grocery list
    public void createGroceryList(GroceryList groceryList) {
        groceryListDAO.createGroceryList(groceryList);
    }

    // Method to retrieve a grocery list by its ID
    public GroceryList getGroceryListById(int listId) {
        return groceryListDAO.getGroceryListById(listId);
    }

    // Method to retrieve all grocery lists by user ID
    public List<GroceryList> getGroceryListsByUserId(int userId) {
        return groceryListDAO.getGroceryListsByUserId(userId);
    }

    // Method to update an existing grocery list
    public void updateGroceryList(GroceryList groceryList) {
        groceryListDAO.updateGroceryList(groceryList);
    }

    // Method to delete a grocery list by its ID
    public void deleteGroceryList(int listId) {
        groceryListDAO.deleteGroceryList(listId);
    }

    // Method to generate a grocery list based on recipes
    public GroceryList generateGroceryListFromRecipes(int userId, List<Recipe> recipes) {
        GroceryList groceryList = new GroceryList(userId);
        HashMap<Integer, GroceryListIngredient> ingredientMap = new HashMap<>();

        for (Recipe recipe : recipes) {
            for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients()) {
                int ingredientId = recipeIngredient.getIngredient().getIngredientId();
                double quantity = recipeIngredient.getQuantity();
                String unit = recipeIngredient.getUnit();

                // Update existing ingredient quantity or add new ingredient
                if (ingredientMap.containsKey(ingredientId)) {
                    GroceryListIngredient existingIngredient = ingredientMap.get(ingredientId);
                    existingIngredient.setQuantity(existingIngredient.getQuantity() + quantity);
                } else {
                    GroceryListIngredient newIngredient = new GroceryListIngredient(groceryList.getListId(), ingredientId, quantity, unit);
                    ingredientMap.put(ingredientId, newIngredient);
                }
            }
        }

        // Set the ingredients and save to the database
        groceryList.setIngredients(new ArrayList<>(ingredientMap.values()));
        groceryListDAO.createGroceryList(groceryList);
        return groceryList;
    }
    
    
 // Add this method in GroceryListService

 // Method to add an ingredient to an existing grocery list
 public void addIngredient(int listId, int ingredientId, double quantity, String unit) {
     // Retrieve the grocery list to ensure it exists
     GroceryList groceryList = groceryListDAO.getGroceryListById(listId);
     if (groceryList == null) {
         System.out.println("Grocery list with ID " + listId + " does not exist.");
         return;
     }

     // Check if the ingredient already exists in the grocery list
     GroceryListIngredient existingIngredient = groceryListIngredientDAO.getIngredientByListIdAndIngredientId(listId, ingredientId);

     if (existingIngredient != null) {
         // If the ingredient exists, update the quantity
         double updatedQuantity = existingIngredient.getQuantity() + quantity;
         existingIngredient.setQuantity(updatedQuantity);
         groceryListIngredientDAO.updateIngredient(existingIngredient);
     } else {
         // If the ingredient does not exist, create a new GroceryListIngredient
         GroceryListIngredient newIngredient = new GroceryListIngredient(listId, ingredientId, quantity, unit);
         groceryListIngredientDAO.addIngredient(newIngredient);
     }

     System.out.println("Ingredient added or updated in grocery list with ID " + listId);
 }

}
