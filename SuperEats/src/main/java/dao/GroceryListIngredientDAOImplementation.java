package dao;

import supereats.GroceryListIngredient;
import supereats.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroceryListIngredientDAOImplementation implements GroceryListIngredientDAO {

	@Override
	public void createGroceryListIngredient(GroceryListIngredient groceryListIngredient) {
		String sql = "INSERT INTO GroceryListIngredient (groceryListId, ingredientId, quantity, unit) VALUES (?, ?, ?, ?)";

		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setInt(1, groceryListIngredient.getGroceryListId());
			stmt.setInt(2, groceryListIngredient.getIngredientId());
			stmt.setDouble(3, groceryListIngredient.getQuantity());
			stmt.setString(4, groceryListIngredient.getUnit());
			stmt.executeUpdate();

			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				groceryListIngredient.setListIngredientId(generatedKeys.getInt(1));
			}
			System.out.println("GroceryListIngredient created successfully.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public GroceryListIngredient getGroceryListIngredientById(int listIngredientId) {
		String sql = "SELECT * FROM GroceryListIngredient WHERE listIngredientId = ?";
		GroceryListIngredient groceryListIngredient = null;

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, listIngredientId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				groceryListIngredient = mapResultSetToGroceryListIngredient(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return groceryListIngredient;
	}

	@Override
	public List<GroceryListIngredient> getIngredientsByGroceryListId(int groceryListId) {
		String sql = "SELECT * FROM GroceryListIngredient WHERE groceryListId = ?";
		List<GroceryListIngredient> ingredients = new ArrayList<>();

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, groceryListId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ingredients.add(mapResultSetToGroceryListIngredient(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ingredients;
	}

	@Override
	public void updateGroceryListIngredient(GroceryListIngredient groceryListIngredient) {
		String sql = "UPDATE GroceryListIngredient SET quantity = ?, unit = ? WHERE listIngredientId = ?";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setDouble(1, groceryListIngredient.getQuantity());
			stmt.setString(2, groceryListIngredient.getUnit());
			stmt.setInt(3, groceryListIngredient.getListIngredientId());
			stmt.executeUpdate();
			System.out.println("GroceryListIngredient updated successfully.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteGroceryListIngredient(int listIngredientId) {
		String sql = "DELETE FROM GroceryListIngredient WHERE listIngredientId = ?";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, listIngredientId);
			stmt.executeUpdate();
			System.out.println("GroceryListIngredient deleted successfully.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteIngredientsByGroceryListId(int groceryListId) {
		String sql = "DELETE FROM GroceryListIngredient WHERE groceryListId = ?";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, groceryListId);
			stmt.executeUpdate();
			System.out.println("All ingredients for GroceryList " + groceryListId + " deleted successfully.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public GroceryListIngredient getIngredientByListIdAndIngredientId(int groceryListId, int ingredientId) {
		String sql = "SELECT * FROM GroceryListIngredient WHERE groceryListId = ? AND ingredientId = ?";
		GroceryListIngredient groceryListIngredient = null;

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, groceryListId);
			stmt.setInt(2, ingredientId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				groceryListIngredient = mapResultSetToGroceryListIngredient(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return groceryListIngredient;
	}

	public void addIngredient(GroceryListIngredient groceryListIngredient) {
		String sql = "INSERT INTO GroceryListIngredient (groceryListId, ingredientId, quantity, unit) VALUES (?, ?, ?, ?)";

		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setInt(1, groceryListIngredient.getGroceryListId());
			stmt.setInt(2, groceryListIngredient.getIngredientId());
			stmt.setDouble(3, groceryListIngredient.getQuantity());
			stmt.setString(4, groceryListIngredient.getUnit());
			stmt.executeUpdate();

			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				groceryListIngredient.setListIngredientId(generatedKeys.getInt(1));
			}
			System.out.println("Ingredient added successfully to grocery list.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteIngredientByListIdAndIngredientId(int groceryListId, int ingredientId) {
	    String sql = "DELETE FROM GroceryListIngredient WHERE groceryListId = ? AND ingredientId = ?";

	    try (Connection conn = DatabaseUtil.getConnection(); 
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, groceryListId);
	        stmt.setInt(2, ingredientId);

	        int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Ingredient with ID " + ingredientId + " removed from Grocery List ID " + groceryListId);
	        } else {
	            System.out.println("No matching ingredient found to delete.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	// Method to update an ingredient in a grocery list
	@Override
	public void updateIngredient(GroceryListIngredient groceryListIngredient) {
		String sql = "UPDATE GroceryListIngredient SET quantity = ?, unit = ? WHERE listIngredientId = ?";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setDouble(1, groceryListIngredient.getQuantity());
			stmt.setString(2, groceryListIngredient.getUnit());
			stmt.setInt(3, groceryListIngredient.getListIngredientId());
			stmt.executeUpdate();

			System.out.println("GroceryListIngredient updated successfully.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Helper method to map a ResultSet row to a GroceryListIngredient object
	private GroceryListIngredient mapResultSetToGroceryListIngredient(ResultSet rs) throws SQLException {
		int listIngredientId = rs.getInt("listIngredientId");
		int groceryListId = rs.getInt("groceryListId");
		int ingredientId = rs.getInt("ingredientId");
		double quantity = rs.getDouble("quantity");
		String unit = rs.getString("unit");

		return new GroceryListIngredient(listIngredientId, groceryListId, ingredientId, quantity, unit);
	}
}
