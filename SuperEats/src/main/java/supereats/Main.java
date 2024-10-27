package supereats;

public class Main {

	public static void main(String[] args) {
		DatabaseSetup.CreateTables();
		
		// TODO: Create, Read, Update, Operations
		
		// Shutdown database
		DatabaseUtil.shutdown();
	}

}
