package dao;

import supereats.GroceryList;
import supereats.GroceryListIngredient;

import java.util.List;

public interface GroceryListDAO {
    void createGroceryList(GroceryList groceryList);
    GroceryList getGroceryListById(int listId);
    List<GroceryList> getGroceryListsByUserId(int userId);
    void updateGroceryList(GroceryList groceryList);
    void deleteGroceryList(int listId);
    List<GroceryList> getAllGroceryLists();
}
