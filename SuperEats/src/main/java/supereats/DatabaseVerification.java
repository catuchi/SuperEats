package supereats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseVerification {

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supereats", "root", "beetroot");
             Statement stmt = conn.createStatement()) {

            String[] tables = {"USER", "ADMIN", "RATING", "MEALPLAN", "RECIPE", "GROCERYLIST", "INGREDIENT", "RECIPEINGREDIENT", "MEALPLANRECIPE"};

            for (String table : tables) {
                // Check if the table exists
                String checkTableExists = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '" + table + "'";
                ResultSet rsTable = stmt.executeQuery(checkTableExists);

                if (rsTable.next()) {
                    System.out.println("Table '" + table + "' exists.");
                    // Query to retrieve columns for the existing table
                    String getColumnDetails = "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + table + "'";
                    ResultSet rsColumns = stmt.executeQuery(getColumnDetails);

                    // Print column details
                    System.out.println("Columns for '" + table + "':");
                    while (rsColumns.next()) {
                        String columnName = rsColumns.getString("COLUMN_NAME");
                        String dataType = rsColumns.getString("DATA_TYPE");
                        System.out.println(" - " + columnName + " (" + dataType + ")");
                    }
                } else {
                    System.out.println("Table '" + table + "' does not exist in the database.");
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
