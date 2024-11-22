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

	public void createGroceryList(GroceryList groceryList) {
		groceryListDAO.createGroceryList(groceryList);
	}

	public GroceryList getGroceryListById(int listId) {
		return groceryListDAO.getGroceryListById(listId);
	}

	public List<GroceryList> getGroceryListsByUserId(int userId) {
		return groceryListDAO.getGroceryListsByUserId(userId);
	}

	public void updateGroceryList(GroceryList groceryList) {
		groceryListDAO.updateGroceryList(groceryList);
	}

	public void deleteGroceryList(int listId) {
		groceryListDAO.deleteGroceryList(listId);
	}

	public GroceryList generateGroceryListFromRecipes(int userId, List<Recipe> recipes) {
		GroceryList groceryList = new GroceryList(userId);
		HashMap<Integer, GroceryListIngredient> ingredientMap = new HashMap<>();

		for (Recipe recipe : recipes) {
			for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients()) {
				int ingredientId = recipeIngredient.getIngredient().getIngredientId();
				double quantity = recipeIngredient.getQuantity();
				String unit = recipeIngredient.getUnit();

				// Update existing ingredient quantity or add new ingredient
				ingredientMap.compute(ingredientId, (key, existing) -> {
					if (existing == null) {
						return new GroceryListIngredient(groceryList.getListId(), ingredientId, quantity, unit);
					}
					existing.setQuantity(existing.getQuantity() + quantity);
					return existing;
				});
			}
		}

		groceryList.setIngredients(new ArrayList<>(ingredientMap.values()));
		groceryListDAO.createGroceryList(groceryList);
		return groceryList;
	}

	public void addIngredient(int listId, int ingredientId, double quantity, String unit) {
		GroceryList groceryList = groceryListDAO.getGroceryListById(listId);
		if (groceryList == null) {
			System.out.println("Grocery list with ID " + listId + " does not exist.");
			return;
		}

		GroceryListIngredient existingIngredient = groceryListIngredientDAO.getIngredientByListIdAndIngredientId(listId,
				ingredientId);

		if (existingIngredient != null) {
			existingIngredient.setQuantity(existingIngredient.getQuantity() + quantity);
			groceryListIngredientDAO.updateIngredient(existingIngredient);
		} else {
			GroceryListIngredient newIngredient = new GroceryListIngredient(listId, ingredientId, quantity, unit);
			groceryListIngredientDAO.addIngredient(newIngredient);
		}

		System.out.println("Ingredient added or updated in grocery list with ID " + listId);
	}

	public void updateIngredient(int listId, int ingredientId, double quantity, String unit) {
		GroceryListIngredient ingredient = groceryListIngredientDAO.getIngredientByListIdAndIngredientId(listId,
				ingredientId);
		if (ingredient != null) {
			ingredient.setQuantity(quantity);
			ingredient.setUnit(unit);
			groceryListIngredientDAO.updateIngredient(ingredient);
			System.out.println("Ingredient updated successfully.");
		} else {
			System.out.println("Ingredient not found in grocery list with ID " + listId);
		}
	}

	public void deleteIngredient(int listId, int ingredientId) {
		groceryListIngredientDAO.deleteIngredientByListIdAndIngredientId(listId, ingredientId);
		System.out.println("Ingredient with ID " + ingredientId + " deleted from grocery list ID " + listId);
	}
}
