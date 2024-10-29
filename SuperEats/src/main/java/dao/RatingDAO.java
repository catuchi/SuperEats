package dao;

import supereats.Rating;
import java.util.List;

public interface RatingDAO {
	void addRating(Rating rating);
	Rating getRatingById(int ratingId);
	List<Rating> getRatingsByRecipeId(int recipeId);
	void updateRating(Rating rating);
	void deleteRating(int ratingId);
}
