package dao;

import supereats.DatabaseUtil;
import supereats.Rating;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingDAOImplementation implements RatingDAO {

    @Override
    public void addRating(Rating rating) {
        String sql = "INSERT INTO Rating (userId, recipeId, rating) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, rating.getUserId());
            stmt.setInt(2, rating.getRecipeId());
            stmt.setInt(3, rating.getRating());

            stmt.executeUpdate();
            System.out.println("Rating added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Rating getRatingById(int ratingId) {
        String sql = "SELECT * FROM Rating WHERE ratingId = ?";
        Rating rating = null;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, ratingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                rating = mapResultSetToRating(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rating;
    }

    @Override
    public List<Rating> getRatingsByRecipeId(int recipeId) {
        String sql = "SELECT * FROM Rating WHERE recipeId = ?";
        List<Rating> ratings = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, recipeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ratings.add(mapResultSetToRating(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratings;
    }

    @Override
    public void updateRating(Rating rating) {
        String sql = "UPDATE Rating SET rating = ? WHERE ratingId = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, rating.getRating());
            stmt.setInt(2, rating.getRatingId());

            stmt.executeUpdate();
            System.out.println("Rating updated successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRating(int ratingId) {
        String sql = "DELETE FROM Rating WHERE ratingId = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, ratingId);
            stmt.executeUpdate();
            System.out.println("Rating deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public double getAverageRatingByRecipeId(int recipeId) {
        String sql = "SELECT AVG(rating) AS averageRating FROM Rating WHERE recipeId = ?";
        double averageRating = 0.0;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, recipeId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                averageRating = rs.getDouble("averageRating");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return averageRating;
    }

    // Helper method to map ResultSet to Rating object
    private Rating mapResultSetToRating(ResultSet rs) throws SQLException {
        int ratingId = rs.getInt("ratingId");
        int userId = rs.getInt("userId");
        int recipeId = rs.getInt("recipeId");
        int ratingValue = rs.getInt("rating");

        return new Rating(ratingId, userId, recipeId, ratingValue);
    }
}
