package supereats;

import java.util.ArrayList;

public class Recipe {
	private int recipeId;
	private String title;
	private String description;
	private ArrayList<Ingredient> ingredients;
	private String instructions;
	private String cuisineType;
	private int prepTime;
	private int calories;
	private boolean approved = false;
	private ArrayList<Rating> ratings;

	public Recipe(String title, String description) {
		this.title = title;
		this.description = description;
		this.ingredients = new ArrayList<>();
		this.ratings = new ArrayList<>();
	}

	public Recipe(String title, String description, String cuisineType) {
		this.title = title;
		this.description = description;
		this.cuisineType = cuisineType;
		this.ingredients = new ArrayList<>();
		this.ratings = new ArrayList<>();
	}

	public Recipe(String title, String description, ArrayList<Ingredient> ingredients, String instructions,
			String cuisineType, int prepTime, int calories, boolean approved) {
		this.title = title;
		this.description = description;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.cuisineType = cuisineType;
		this.prepTime = prepTime;
		this.calories = calories;
		this.approved = approved;
		this.ratings = new ArrayList<>();
	}

	public Recipe(int recipeId, String title, String description, ArrayList<Ingredient> ingredients,
			String instructions, String cuisineType, int prepTime, int calories, boolean approved) {
		this.recipeId = recipeId;
		this.title = title;
		this.description = description;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.cuisineType = cuisineType;
		this.prepTime = prepTime;
		this.calories = calories;
		this.approved = approved;
		this.ratings = new ArrayList<>();
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

	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
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

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public void addIngredient(Ingredient ingredient) {
		ingredients.add(ingredient);
	}

	public void removeIngredient(Ingredient ingredient) {
		ingredients.remove(ingredient);
	}

	public void addRating(Rating rating) {
		ratings.add(rating);
	}

	public double getAverageRating() {
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

}
