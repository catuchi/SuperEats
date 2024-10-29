package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import supereats.Admin;
import supereats.DatabaseUtil;
import supereats.Role;

public class AdminDAOImplementation implements AdminDAO {

	@Override
	public void createAdmin(Admin admin) {
		String sql = "INSERT INTO Admin (name, email, password, role) VALUES (?, ?, ?, ?)";
		
		try (
				Connection conn = DatabaseUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, admin.getName());
			stmt.setString(2, admin.getEmail());
			stmt.setString(3, admin.getPassword());
			stmt.setString(4, admin.getRole().toString());
			
			stmt.executeUpdate();
			System.out.println("Admin created successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Admin getAdminById(int adminId) {
		String sql = "SELECT * FROM Admin WHERE userId = ?";
		Admin admin = null;
		
		try (
				Connection conn = DatabaseUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, adminId);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				admin = mapResultSetToAdmin(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return admin;
	}

	@Override
	public Admin getAdminByEmail(String email) {
        String sql = "SELECT * FROM Admin WHERE email = ?";
        Admin admin = null;

        try (
            Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = mapResultSetToAdmin(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

	@Override
	public void updateAdmin(Admin admin) {
	    StringBuilder sql = new StringBuilder("UPDATE Admin SET ");
	    List<Object> params = new ArrayList<>();

	    if (admin.getEmail() != null) { 
	        sql.append("email = ?, ");
	        params.add(admin.getEmail());
	    }
	    if (admin.getPassword() != null) {
	        sql.append("password = ?, ");
	        params.add(admin.getPassword());
	    }
	    if (admin.getName() != null) {
	        sql.append("name = ?, ");
	        params.add(admin.getName());
	    }
	    if (admin.getRole() != null) {
	        sql.append("role = ?, ");
	        params.add(admin.getRole().toString());
	    }

	    // Remove the trailing comma and space
	    sql.setLength(sql.length() - 2);

	    sql.append(" WHERE userId = ?");
	    params.add(admin.getUserId());

	    try (Connection conn = DatabaseUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

	        for (int i = 0; i < params.size(); i++) {
	            stmt.setObject(i + 1, params.get(i));
	        }

	        int rowsUpdated = stmt.executeUpdate();
	        System.out.println("Admin updated. Rows affected: " + rowsUpdated);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public void deleteAdmin(int adminId) {
        String sql = "DELETE FROM Admin WHERE userId = ?";

        try (
            Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, adminId);
            stmt.executeUpdate();
            System.out.println("Admin deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public List<Admin> getAllAdmins() {
        String sql = "SELECT * FROM Admin";
        List<Admin> admins = new ArrayList<>();

        try (
            Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                admins.add(mapResultSetToAdmin(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }
	
	private Admin mapResultSetToAdmin(ResultSet rs) throws SQLException {
		int adminId = rs.getInt("userId");
		String name = rs.getString("name");
        String email = rs.getString("email");
        String password = rs.getString("password");
        Role role = Role.valueOf(rs.getString("role"));
        
        return new Admin(adminId, name, email, password, role);	
	}

}
