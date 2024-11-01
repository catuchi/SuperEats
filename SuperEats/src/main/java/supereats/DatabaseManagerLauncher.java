package supereats;

import org.hsqldb.util.DatabaseManagerSwing;

public class DatabaseManagerLauncher {
    public static void main(String[] args) {
        DatabaseManagerSwing.main(new String[] {
            "--url", "jdbc:hsqldb:file:supereats;shutdown=true", // Update with your database URL
            "--user", "SA",                                      // Default HSQLDB user
            "--password", ""                                     // Leave blank if no password
        });
    }
}
