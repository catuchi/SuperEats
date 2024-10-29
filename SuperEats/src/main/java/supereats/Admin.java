package supereats;

public class Admin extends Account {
	
	public Admin(String name, String email, String password, Role role) {
		super(name, email, password, role);
	}
	
	public Admin(int userId, String name, String email, String password, Role role) {
		super(userId, name, email, password, role);
	}

	@Override
	public void register() {
		System.out.println("Admin registered: " + getEmail());
	}

	@Override
	public void login() {
		System.out.println("Admin logged in: " + getEmail());
	}

	@Override
	public void logout() {
		System.out.println("Admin logged out: " + getEmail());
	}
	
	public void approveRecipe(Recipe recipe) {
		recipe.setApproved(true);
	    System.out.println("Recipe approved: " + recipe.getTitle());
	}
	
	public void deleteUser(User user) {
		System.out.println("User deleted: " + user.getEmail());
	}
}
