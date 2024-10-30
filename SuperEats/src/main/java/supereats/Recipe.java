package supereats;

import dao.RecipeIngredientDAO;
import dao.RecipeIngredientDAOImplementation;
import dao.RatingDAO;
import dao.RatingDAOImplementation;

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

	// DAOs for managing related data
	private final RecipeIngredientDAO recipeIngredientDAO = new RecipeIngredientDAOImplementation();
	private final RatingDAO ratingDAO = new RatingDAOImplementation();

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

	// Method to retrieve ingredients from RecipeIngredientDAO
	public ArrayList<RecipeIngredient> getRecipeIngredients() {
		if (recipeIngredients.isEmpty()) {
			recipeIngredients = new ArrayList<>(recipeIngredientDAO.getIngredientsByRecipeId(this.recipeId));
		}
		return (ArrayList<RecipeIngredient>) recipeIngredients;
	}

	// Method to retrieve ratings from RatingDAO
	public ArrayList<Rating> getRatings() {
		if (ratings.isEmpty()) {
			ratings = new ArrayList<>(ratingDAO.getRatingsByRecipeId(this.recipeId));
		}
		return (ArrayList<Rating>) ratings;
	}

	// Add a recipe ingredient
	public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
		recipeIngredients.add(recipeIngredient);
		recipeIngredientDAO.addRecipeIngredient(recipeIngredient);
	}

	// Remove a recipe ingredient
	public void removeRecipeIngredient(RecipeIngredient recipeIngredient) {
		recipeIngredients.remove(recipeIngredient);
		recipeIngredientDAO.removeRecipeIngredient(recipeIngredient);
	}

	// Add a rating
	public void addRating(Rating rating) {
		ratings.add(rating);
		ratingDAO.addRating(rating);
	}

	// Calculate average rating
	public double getAverageRating() {
	    if (ratings.isEmpty()) {
	        ratings = new ArrayList<>(ratingDAO.getRatingsByRecipeId(this.recipeId));
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


}
