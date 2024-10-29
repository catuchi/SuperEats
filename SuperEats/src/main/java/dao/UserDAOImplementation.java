package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import supereats.DatabaseUtil;
import supereats.Role;
import supereats.User;

public class UserDAOImplementation implements UserDAO {

	@Override
	public void createUser(User user) {
		String sql = "INSERT INTO User (name, email, password, role, dietaryPreferences, profilePicture) VALUES (?, ?, ?, ?, ?, ?)";

		try (
				Connection conn = DatabaseUtil.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getRole().toString());
			stmt.setString(5, user.getDietaryPreferences());
			stmt.setString(6, user.getProfilePicture());

			stmt.executeUpdate();
			System.out.println("User created successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public User getUserById(int userId) {
		String sql = "SELECT * FROM User WHERE userId = ?";
		User user = null;
		
		try (
				Connection conn = DatabaseUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				user = mapResultSetToUser(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		String sql = "SELECT * FROM User WHERE email = ?";
		User user = null;
		
		try (
				Connection conn = DatabaseUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				user = mapResultSetToUser(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

//	@Override
//	public void updateUser(User user) {
//        String sql = "UPDATE User SET name = ?, email = ?, password = ?, role = ?, dietaryPreferences = ?, profilePicture = ? WHERE userId = ?";
//
//        try (Connection conn = DatabaseUtil.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setString(1, user.getName());
//            stmt.setString(2, user.getEmail());
//            stmt.setString(3, user.getPassword());
//            stmt.setString(4, user.getRole().toString());
//            stmt.setString(5, user.getDietaryPreferences());
//            stmt.setString(6, user.getProfilePicture());
//            stmt.setInt(7, user.getUserId());
//
//            stmt.executeUpdate();
//            System.out.println("User updated successfully.");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
	
	@Override
	public void updateUser(User user) {
	    StringBuilder sql = new StringBuilder("UPDATE User SET ");
	    List<Object> params = new ArrayList<>();

	    if (user.getEmail() != null) {
	        sql.append("email = ?, ");
	        params.add(user.getEmail());
	    }
	    if (user.getPassword() != null) {
	        sql.append("password = ?, ");
	        params.add(user.getPassword());
	    }
	    if (user.getName() != null) {
	        sql.append("name = ?, ");
	        params.add(user.getName());
	    }
	    if (user.getRole() != null) {
	        sql.append("role = ?, ");
	        params.add(user.getRole().toString());
	    }
	    if (user.getDietaryPreferences() != null) {
	        sql.append("dietaryPreferences = ?, ");
	        params.add(user.getDietaryPreferences());
	    }
	    if (user.getProfilePicture() != null) {
	        sql.append("profilePicture = ?, ");
	        params.add(user.getProfilePicture());
	    }

	    // Remove the trailing comma and space
	    sql.setLength(sql.length() - 2);

	    sql.append(" WHERE userId = ?");
	    params.add(user.getUserId());

	    try (Connection conn = DatabaseUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

	        for (int i = 0; i < params.size(); i++) {
	            stmt.setObject(i + 1, params.get(i));
	        }

	        int rowsUpdated = stmt.executeUpdate();
	        System.out.println("User updated. Rows affected: " + rowsUpdated);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void deleteUser(int userId) {
		String sql = "DELETE FROM User WHERE userId = ?";
		
		try (
				Connection conn = DatabaseUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, userId);
			stmt.executeUpdate();
			System.out.println("User deleted successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<User> getAllUsers() {
		String sql = "SELECT * FROM User";
		List<User> users = new ArrayList<>();
		
		try (
				Connection conn = DatabaseUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			
			while (rs.next()) {
				users.add(mapResultSetToUser(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	private User mapResultSetToUser(ResultSet rs) throws SQLException {
	    int userId = rs.getInt("userId");
	    String name = rs.getString("name");
	    String email = rs.getString("email");
	    String password = rs.getString("password");
	    String roleString = rs.getString("role"); // Retrieve role as a String
	    Role role = Role.valueOf(roleString);     // Convert to Role enum
	    String dietaryPreferences = rs.getString("dietaryPreferences");
	    String profilePicture = rs.getString("profilePicture");

	    return new User(userId, name, email, password, role, dietaryPreferences, profilePicture);
	}

}
