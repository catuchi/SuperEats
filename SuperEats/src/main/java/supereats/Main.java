package supereats;

public class Main {

	public static void main(String[] args) {
//		DatabaseSetup.dropTables();
//		DatabaseSetup.createTables();

//		String sampleDataSQL = "INSERT INTO RECIPE (title, description, instructions, cuisineType, prepTime, calories, approved) VALUES "
//				+ "('Spaghetti Bolognese', 'Classic Italian pasta dish', 'Cook pasta, prepare sauce, mix together.', 'Italian', 30, 600, true), "
//				+ "('Vegan Tacos', 'Delicious plant-based tacos', 'Prepare vegetables, cook beans, assemble tacos.', 'Mexican', 20, 450, true), "
//				+ "('Chicken Curry', 'Spicy chicken curry with rice', 'Cook chicken, add spices, simmer with sauce.', 'Indian', 45, 700, true);";
//
//		DatabaseSetup.executeSQL(sampleDataSQL);
		
//		String userSampleDataSQL = "INSERT INTO USER (userId, name, email, password, role, dietaryPreferences, profilePicture) VALUES "
//		        + "(1, 'Alice', 'alice@example.com', 'password123', 'USER', 'Vegan', 'profile1.jpg'), "
//		        + "(2, 'Bob', 'bob@example.com', 'password456', 'USER', 'Vegetarian', 'profile2.jpg'), "
//		        + "(3, 'Charlie', 'charlie@example.com', 'password789', 'ADMIN', 'Keto', 'profile3.jpg');";
//		DatabaseSetup.executeSQL(userSampleDataSQL);
//
//		String adminSampleDataSQL = "INSERT INTO ADMIN (userId, name, email, password, role) VALUES "
//		        + "(3, 'Charlie', 'charlie@example.com', 'password789', 'ADMIN');";
//		DatabaseSetup.executeSQL(adminSampleDataSQL);
//
//		String ingredientSampleDataSQL = "INSERT INTO INGREDIENT (ingredientId, name) VALUES "
//		        + "(1, 'Tomato'), "
//		        + "(2, 'Chicken Breast'), "
//		        + "(3, 'Lettuce'), "
//		        + "(4, 'Garlic');";
//		DatabaseSetup.executeSQL(ingredientSampleDataSQL);
//
//		String recipeSampleDataSQL = "INSERT INTO RECIPE (recipeId, title, description, instructions, cuisineType, prepTime, calories, approved) VALUES "
//		        + "(1, 'Spaghetti Bolognese', 'Classic Italian pasta dish', 'Cook pasta, prepare sauce, mix together.', 'Italian', 30, 600, true), "
//		        + "(2, 'Vegan Tacos', 'Delicious plant-based tacos', 'Prepare vegetables, cook beans, assemble tacos.', 'Mexican', 20, 450, true), "
//		        + "(3, 'Chicken Curry', 'Spicy chicken curry with rice', 'Cook chicken, add spices, simmer with sauce.', 'Indian', 45, 700, true);";
//		DatabaseSetup.executeSQL(recipeSampleDataSQL);
//
//		String ratingSampleDataSQL = "INSERT INTO RATING (ratingId, userId, recipeId, rating) VALUES "
//		        + "(1, 1, 1, 5), "
//		        + "(2, 2, 2, 4), "
//		        + "(3, 1, 3, 3);";
//		DatabaseSetup.executeSQL(ratingSampleDataSQL);
//
//		String groceryListSampleDataSQL = "INSERT INTO GROCERYLIST (listId, userId) VALUES "
//		        + "(1, 1), "
//		        + "(2, 2);";
//		DatabaseSetup.executeSQL(groceryListSampleDataSQL);
//
//		String groceryListIngredientSampleDataSQL = "INSERT INTO GROCERYLISTINGREDIENT (listIngredientId, listId, ingredientId, quantity, unit) VALUES "
//		        + "(1, 1, 1, 2, 'pieces'), "
//		        + "(2, 1, 2, 1, 'kg'), "
//		        + "(3, 2, 3, 500, 'grams');";
//		DatabaseSetup.executeSQL(groceryListIngredientSampleDataSQL);
//
//		String mealPlanSampleDataSQL = "INSERT INTO MEALPLAN (mealPlanId, userId, name, startDate, endDate) VALUES "
//		        + "(1, 1, 'Weekday Meals', '2024-10-30', '2024-11-05'), "
//		        + "(2, 2, 'Vegan Week', '2024-10-31', '2024-11-06');";
//		DatabaseSetup.executeSQL(mealPlanSampleDataSQL);
//
//		String mealPlanRecipeSampleDataSQL = "INSERT INTO MEALPLANRECIPE (mealPlanRecipeId, mealPlanId, recipeId) VALUES "
//		        + "(1, 1, 1), "
//		        + "(2, 1, 2), "
//		        + "(3, 2, 2);";
//		DatabaseSetup.executeSQL(mealPlanRecipeSampleDataSQL);
//
//		String recipeIngredientSampleDataSQL = "INSERT INTO RECIPEINGREDIENT (recipeIngredientId, recipeId, ingredientId, quantity, unit) VALUES "
//		        + "(1, 1, 1, 200, 'grams'), "
//		        + "(2, 1, 4, 2, 'cloves'), "
//		        + "(3, 2, 3, 1, 'cup');";
//		DatabaseSetup.executeSQL(recipeIngredientSampleDataSQL);


		// TODO: Create, Read, Update, Operations

		// Shutdown database
		DatabaseUtil.shutdown();
	}

}
