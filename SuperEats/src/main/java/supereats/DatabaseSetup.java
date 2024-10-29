package supereats;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

    public static void dropTables() {
        String[] dropStatements = {
            "DROP TABLE IF EXISTS MealPlanRecipe",
            "DROP TABLE IF EXISTS RecipeIngredient",
            "DROP TABLE IF EXISTS GroceryListIngredient",
            "DROP TABLE IF EXISTS Rating",
            "DROP TABLE IF EXISTS MealPlan",
            "DROP TABLE IF EXISTS GroceryList",
            "DROP TABLE IF EXISTS Recipe",
            "DROP TABLE IF EXISTS Ingredient",
            "DROP TABLE IF EXISTS Admin",
            "DROP TABLE IF EXISTS User"
        };

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            for (String statement : dropStatements) {
                stmt.executeUpdate(statement);
                System.out.println("Executed: " + statement);
            }

            System.out.println("Tables dropped successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createTables() {
        String[] createStatements = {
            "CREATE TABLE User (" +
            "userId INTEGER IDENTITY PRIMARY KEY, " +
            "name VARCHAR(100), " +
            "email VARCHAR(100), " +
            "password VARCHAR(100), " +
            "role VARCHAR(20), " +
            "dietaryPreferences VARCHAR(100), " +
            "profilePicture VARCHAR(100)" +
            ");",

            "CREATE TABLE Admin (" +
            "userId INTEGER IDENTITY PRIMARY KEY, " +
            "name VARCHAR(100), " +
            "email VARCHAR(100), " +
            "password VARCHAR(100), " +
            "role VARCHAR(20)" +
            ");",

            "CREATE TABLE Recipe (" +
            "recipeId INTEGER IDENTITY PRIMARY KEY, " +
            "title VARCHAR(100), " +
            "description VARCHAR(100), " +
            "instructions VARCHAR(100), " +
            "cuisineType VARCHAR(100), " +
            "prepTime INTEGER, " +
            "calories INTEGER, " +
            "approved BOOLEAN DEFAULT FALSE" +
            ");",

            "CREATE TABLE Rating (" +
            "ratingId INTEGER IDENTITY PRIMARY KEY, " +
            "userId INTEGER, " +
            "recipeId INTEGER, " +
            "rating INTEGER, " +
            "FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE, " +
            "FOREIGN KEY (recipeId) REFERENCES Recipe(recipeId) ON DELETE CASCADE" +
            ");",

            "CREATE TABLE Ingredient (" +
            "ingredientId INTEGER IDENTITY PRIMARY KEY, " +
            "name VARCHAR(100) NOT NULL" +
            ");",

            "CREATE TABLE RecipeIngredient (" +
            "recipeIngredientId INTEGER IDENTITY PRIMARY KEY, " +
            "recipeId INTEGER NOT NULL, " +
            "ingredientId INTEGER NOT NULL, " +
            "quantity DOUBLE, " +
            "unit VARCHAR(20), " +
            "FOREIGN KEY (recipeId) REFERENCES Recipe(recipeId) ON DELETE CASCADE, " +
            "FOREIGN KEY (ingredientId) REFERENCES Ingredient(ingredientId) ON DELETE CASCADE" +
            ");",

            "CREATE TABLE MealPlan (" +
            "mealPlanId INTEGER IDENTITY PRIMARY KEY, " +
            "userId INTEGER NOT NULL, " +
            "name VARCHAR(100), " +
            "startDate DATE, " +
            "endDate DATE, " +
            "FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE" +
            ");",

            "CREATE TABLE MealPlanRecipe (" +
            "mealPlanRecipeId INTEGER IDENTITY PRIMARY KEY, " +
            "mealPlanId INTEGER NOT NULL, " +
            "recipeId INTEGER NOT NULL, " +
            "FOREIGN KEY (mealPlanId) REFERENCES MealPlan(mealPlanId) ON DELETE CASCADE, " +
            "FOREIGN KEY (recipeId) REFERENCES Recipe(recipeId) ON DELETE CASCADE" +
            ");",

            // New GroceryList table
            "CREATE TABLE GroceryList (" +
            "listId INTEGER IDENTITY PRIMARY KEY, " +
            "userId INTEGER NOT NULL, " +
            "FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE" +
            ");",

            // New GroceryListIngredient table
            "CREATE TABLE GroceryListIngredient (" +
            "listIngredientId INTEGER IDENTITY PRIMARY KEY, " +
            "listId INTEGER NOT NULL, " +
            "ingredientId INTEGER NOT NULL, " +
            "quantity DOUBLE, " +
            "unit VARCHAR(20), " +
            "FOREIGN KEY (listId) REFERENCES GroceryList(listId) ON DELETE CASCADE, " +
            "FOREIGN KEY (ingredientId) REFERENCES Ingredient(ingredientId) ON DELETE CASCADE" +
            ");"
        };

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            for (String statement : createStatements) {
                stmt.executeUpdate(statement);
                System.out.println("Executed: " + statement);
            }

            System.out.println("Tables created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
