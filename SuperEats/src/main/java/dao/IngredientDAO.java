package dao;

import supereats.Ingredient;
import java.util.List;

public interface IngredientDAO {
    void addIngredient(Ingredient ingredient);
    Ingredient getIngredientById(int ingredientId);
    List<Ingredient> getAllIngredients();
    void updateIngredient(Ingredient ingredient);
    void deleteIngredient(int ingredientId);
}
