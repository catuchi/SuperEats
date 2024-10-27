package supereats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
	private static final String JDBC_URL = "jdbc:hsqldb:file:supereats;shutdown=true";
	private static final String USER = "SA";
	private static final String PASSWORD = "";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
	}

	public static void shutdown() {
		try (Connection conn = getConnection()) {
			conn.createStatement().execute("SHUTDOWN");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
