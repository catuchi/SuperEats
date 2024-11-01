package dao;

import supereats.DatabaseUtil;
import supereats.MealPlan;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MealPlanDAOImplementation implements MealPlanDAO {

	public List<MealPlan> getAllMealPlans() {
        List<MealPlan> mealPlans = new ArrayList<>();
        String sql = "SELECT * FROM MealPlan";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int mealPlanId = rs.getInt("mealPlanId");
                int userId = rs.getInt("userId");
                String name = rs.getString("name");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");

                MealPlan mealPlan = new MealPlan(mealPlanId, userId, name, startDate.toLocalDate(), endDate.toLocalDate());
                mealPlans.add(mealPlan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mealPlans;
    }
	
	@Override
    public void createMealPlan(MealPlan mealPlan) {
        String sql = "INSERT INTO MealPlan (userId, name, startDate, endDate) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, mealPlan.getUserId());
            stmt.setString(2, mealPlan.getName());
            stmt.setDate(3, Date.valueOf(mealPlan.getStartDate()));
            stmt.setDate(4, Date.valueOf(mealPlan.getEndDate()));

            stmt.executeUpdate();

            // Set generated mealPlanId in the MealPlan object
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                mealPlan.setMealPlanId(rs.getInt(1));
            }

            System.out.println("MealPlan created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MealPlan getMealPlanById(int mealPlanId) {
        String sql = "SELECT * FROM MealPlan WHERE mealPlanId = ?";
        MealPlan mealPlan = null;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mealPlanId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                mealPlan = mapResultSetToMealPlan(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mealPlan;
    }

    @Override
    public List<MealPlan> getMealPlansByUserId(int userId) {
        String sql = "SELECT * FROM MealPlan WHERE userId = ?";
        List<MealPlan> mealPlans = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                mealPlans.add(mapResultSetToMealPlan(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mealPlans;
    }

    @Override
    public void updateMealPlan(MealPlan mealPlan) {
        StringBuilder sql = new StringBuilder("UPDATE MealPlan SET ");
        List<Object> params = new ArrayList<>();

        if (mealPlan.getName() != null) {
            sql.append("name = ?, ");
            params.add(mealPlan.getName());
        }
        if (mealPlan.getStartDate() != null) {
            sql.append("startDate = ?, ");
            params.add(Date.valueOf(mealPlan.getStartDate()));
        }
        if (mealPlan.getEndDate() != null) {
            sql.append("endDate = ?, ");
            params.add(Date.valueOf(mealPlan.getEndDate()));
        }

        // Remove trailing comma and space
        sql.setLength(sql.length() - 2);

        sql.append(" WHERE mealPlanId = ?");
        params.add(mealPlan.getMealPlanId());

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            int rowsUpdated = stmt.executeUpdate();
            System.out.println("MealPlan updated. Rows affected: " + rowsUpdated);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMealPlan(int mealPlanId) {
        String sql = "DELETE FROM MealPlan WHERE mealPlanId = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mealPlanId);
            stmt.executeUpdate();
            System.out.println("MealPlan deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to map ResultSet to MealPlan object
    private MealPlan mapResultSetToMealPlan(ResultSet rs) throws SQLException {
        int mealPlanId = rs.getInt("mealPlanId");
        int userId = rs.getInt("userId");
        String name = rs.getString("name");
        LocalDate startDate = rs.getDate("startDate").toLocalDate();
        LocalDate endDate = rs.getDate("endDate").toLocalDate();

        return new MealPlan(mealPlanId, userId, name, startDate, endDate);
    }
}
