package supereats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
//	private static final String JDBC_URL = "jdbc:hsqldb:file:supereats;shutdown=true";
//	private static final String USER = "SA";
//	private static final String PASSWORD = "";
	
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/supereats"; // Adjust port or add additional parameters as needed
    private static final String USER = "root";
    private static final String PASSWORD = "beetroot";
    
    static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

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
