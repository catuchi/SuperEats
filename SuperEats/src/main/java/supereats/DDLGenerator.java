package supereats;

import java.sql.*;

public class DDLGenerator {
    public static void main(String[] args) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            DatabaseMetaData dbMetaData = conn.getMetaData();
            String[] types = {"TABLE"};
            ResultSet tables = dbMetaData.getTables(null, null, "%", types);

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("-- DDL for table " + tableName);
                System.out.println("CREATE TABLE " + tableName + " (");

                ResultSet columns = dbMetaData.getColumns(null, null, tableName, null);
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    int columnSize = columns.getInt("COLUMN_SIZE");
                    System.out.print("    " + columnName + " " + columnType + "(" + columnSize + "),");
                    System.out.println();
                }
                System.out.println(");");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
