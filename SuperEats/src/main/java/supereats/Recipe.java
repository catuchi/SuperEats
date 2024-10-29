package supereats;

import java.sql.*;
import java.util.ArrayList;

public class Recipe {
	private int recipeId;
	private String title;
	private String description;
	private ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>();
	private String instructions;
	private String cuisineType;
	private int prepTime;
	private int calories;
	private Boolean approved = false;
	private ArrayList<Rating> ratings = new ArrayList<>();

	// Constructors
	public Recipe(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public Recipe(String title, String description, String cuisineType) {
		this.title = title;
		this.description = description;
		this.cuisineType = cuisineType;
	}

	public Recipe(String title, String description, ArrayList<RecipeIngredient> recipeIngredients, String instructions,
			String cuisineType, int prepTime, int calories, Boolean approved) {
		this.title = title;
		this.description = description;
		this.instructions = instructions;
		this.cuisineType = cuisineType;
		this.prepTime = prepTime;
		this.calories = calories;
		this.approved = approved;
		this.recipeIngredients = recipeIngredients;
	}

	public Recipe(int recipeId, String title, String description, ArrayList<RecipeIngredient> recipeIngredients,
			String instructions, String cuisineType, int prepTime, int calories, Boolean approved) {
		this.recipeId = recipeId;
		this.title = title;
		this.description = description;
		this.instructions = instructions;
		this.cuisineType = cuisineType;
		this.prepTime = prepTime;
		this.calories = calories;
		this.approved = approved;
		this.recipeIngredients = recipeIngredients;
	}

	public Recipe(int recipeId, String title, String description, String instructions, String cuisineType, int prepTime,
			int calories, Boolean approved) {
		this.recipeId = recipeId;
		this.title = title;
		this.description = description;
		this.instructions = instructions;
		this.cuisineType = cuisineType;
		this.prepTime = prepTime;
		this.calories = calories;
		this.approved = approved;
	}

	// Getters and Setters
	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<RecipeIngredient> getRecipeIngredients() {
		if (recipeIngredients.isEmpty()) {
			recipeIngredients = loadRecipeIngredientsFromDB();
		}
		return recipeIngredients;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public int getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public Boolean isApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public ArrayList<Rating> getRatings() {
		if (ratings.isEmpty()) {
			ratings = loadRatingsFromDB();
		}
		return ratings;
	}

	public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
		recipeIngredients.add(recipeIngredient);
		// TODO: save to database after adding
	}

	public void removeRecipeIngredient(RecipeIngredient recipeIngredient) {
		recipeIngredients.remove(recipeIngredient);
		// TODO: logic to delete from database
	}

	public void addRating(Rating rating) {
		ratings.add(rating);
		// TODO: add rating to database
	}

	public double getAverageRating() {
		if (ratings.isEmpty()) {
			ratings = loadRatingsFromDB();
		}

		if (ratings.isEmpty()) {
			return 0;
		}

		int totalRating = 0;
		for (Rating rating : ratings) {
			totalRating += rating.getRating();
		}
		return (double) totalRating / ratings.size();
	}

	public void rateRecipe(int rating, int userId) {
		Rating newRating = new Rating(userId, this.recipeId, rating);
		addRating(newRating);
	}

	private ArrayList<RecipeIngredient> loadRecipeIngredientsFromDB() {
		if (!recipeIngredients.isEmpty())
			return recipeIngredients; // check if ingredients for this recipe is already loaded

		String sql = "SELECT * FROM RecipeIngredient WHERE recipeId = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, this.recipeId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int recipeIngredientId = rs.getInt("recipeIngredientId");
				int ingredientId = rs.getInt("ingredientId");
				double quantity = rs.getDouble("quantity");
				String unit = rs.getString("unit");

				// Create RecipeIngredient object and add it to the list
				RecipeIngredient recipeIngredient = new RecipeIngredient(recipeIngredientId, this.recipeId,
						ingredientId, quantity, unit);
				recipeIngredients.add(recipeIngredient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipeIngredients;
	}

	private ArrayList<Rating> loadRatingsFromDB() {
        if (!ratings.isEmpty()) return ratings; // check if ratings for this recipe is already loaded

        String sql = "SELECT * FROM Rating WHERE recipeId = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, this.recipeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int ratingId = rs.getInt("ratingId");
                int userId = rs.getInt("userId");
                int ratingValue = rs.getInt("rating");

                // Create Rating object and add it to the list
                Rating rating = new Rating(ratingId, userId, this.recipeId, ratingValue);
                ratings.add(rating);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratings;
    }
}
