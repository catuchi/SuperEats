package supereats;

public class Rating {
	private int ratingId;
	private int userId;
	private int recipeId;
	private int rating; 		// Rating will be from 1-5
	
	public Rating(int userId, int recipeId, int rating) {
		this.userId = userId;
		this.recipeId = recipeId;
		setRating(rating);
	}
	
	public Rating(int ratingId, int userId, int recipeId, int rating) {
		this.ratingId = ratingId;
		this.userId = userId;
		this.recipeId = recipeId;
		setRating(rating);
	}
	
	public int getRatingId() {
        return ratingId;
    }

    public int getUserId() {
        return userId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        this.rating = rating;
    }
}
