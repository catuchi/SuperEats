package dao;

import supereats.GroceryListIngredient;
import java.util.List;

public interface GroceryListIngredientDAO {
    void createGroceryListIngredient(GroceryListIngredient groceryListIngredient);
    GroceryListIngredient getGroceryListIngredientById(int listIngredientId);
    List<GroceryListIngredient> getIngredientsByGroceryListId(int groceryListId);
    void updateGroceryListIngredient(GroceryListIngredient groceryListIngredient);
    void deleteGroceryListIngredient(int listIngredientId);
    void deleteIngredientsByGroceryListId(int groceryListId);

    void addIngredient(GroceryListIngredient groceryListIngredient);
    GroceryListIngredient getIngredientByListIdAndIngredientId(int groceryListId, int ingredientId);
    void updateIngredient(GroceryListIngredient groceryListIngredient);
	void deleteIngredientByListIdAndIngredientId(int groceryListId, int ingredientId);
}
