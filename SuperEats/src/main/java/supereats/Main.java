package supereats;

public class Main {

	public static void main(String[] args) {
		DatabaseSetup.dropTables();
		DatabaseSetup.createTables();
		
		// TODO: Create, Read, Update, Operations
		
		// Shutdown database
		DatabaseUtil.shutdown();
	}

}
