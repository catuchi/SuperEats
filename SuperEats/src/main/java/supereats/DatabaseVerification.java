package supereats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseVerification {
	public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:hsqldb:file:supereats;shutdown=true", "SA", "");
             Statement stmt = conn.createStatement()) {

            String checkTableExists = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'USER'";
            ResultSet rs = stmt.executeQuery(checkTableExists);

            if (rs.next()) {
                System.out.println("The 'User' table exists in the database.");
            } else {
                System.out.println("The 'User' table does not exist in the database.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
