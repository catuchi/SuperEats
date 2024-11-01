package services;

import dao.RatingDAO;
import supereats.Rating;

import java.util.List;

public class RatingService {

    private RatingDAO ratingDAO;

    public RatingService(RatingDAO ratingDAO) {
        this.ratingDAO = ratingDAO;
    }

    // Method to add a new rating
    public void addRating(Rating rating) {
        ratingDAO.addRating(rating);
        System.out.println("Rating added successfully for Recipe ID: " + rating.getRecipeId());
    }

    // Method to retrieve all ratings for a specific recipe
    public List<Rating> getRatingsByRecipeId(int recipeId) {
        return ratingDAO.getRatingsByRecipeId(recipeId);
    }

    // Method to calculate the average rating for a specific recipe
    public double getAverageRating(int recipeId) {
        List<Rating> ratings = ratingDAO.getRatingsByRecipeId(recipeId);
        if (ratings.isEmpty()) {
            return 0.0;
        }
        
        int total = ratings.stream().mapToInt(Rating::getRating).sum();
        return (double) total / ratings.size();
    }

    // Method to delete a rating by its ID
    public void deleteRating(int ratingId) {
        ratingDAO.deleteRating(ratingId);
        System.out.println("Rating deleted successfully for Rating ID: " + ratingId);
    }
}
