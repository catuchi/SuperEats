package supereats;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {
	public static void CreateTables() {
		try(
				Connection conn = DatabaseUtil.getConnection();
				Statement stmt = conn.createStatement();
				){
			
			String dropUserTable = "DROP TABLE IF EXISTS User;";
			String createUserTable = "CREATE TABLE User (" +
				    "userId INTEGER IDENTITY PRIMARY KEY, " +
				    "name VARCHAR(100), " +
				    "email VARCHAR(100), " +
				    "password VARCHAR(100), " +
				    "role VARCHAR(20), " +
				    "dietaryPreferences VARCHAR(100), " +
				    "profilePicture VARCHAR(100)" +
				");";
			
			stmt.executeUpdate(dropUserTable);
			System.out.println("User table dropped successfully.");
            stmt.executeUpdate(createUserTable);
            System.out.println("User table created successfully.");
            
            // TODO: create other tables
            
            System.out.println("Tables created successfully");
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
