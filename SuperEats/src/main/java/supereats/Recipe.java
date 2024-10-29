package supereats;

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
    private boolean approved = false;
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

    public Recipe(String title, String description, ArrayList<RecipeIngredient> recipeIngredients, 
                  String instructions, String cuisineType, int prepTime, int calories, boolean approved) {
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
                  String instructions, String cuisineType, int prepTime, int calories, boolean approved) {
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

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
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

    // Helper Methods to Load from Database
    private ArrayList<RecipeIngredient> loadRecipeIngredientsFromDB() {
        // Implement DB logic to retrieve recipeIngredients by recipeId
        // Populate and return recipeIngredients ArrayList
        return new ArrayList<>(); 
    }

    private ArrayList<Rating> loadRatingsFromDB() {
        // Implement DB logic to retrieve ratings by recipeId
        // Populate and return ratings ArrayList
        return new ArrayList<>(); 
    }
}

