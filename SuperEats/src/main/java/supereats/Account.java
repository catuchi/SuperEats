package supereats;

public abstract class Account {
	private int userId;
	private String name;
	private String email;
	private String password;
	private Role role;
	
	public Account(int userId, String name, String email, String password, Role role) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public abstract void register();
	public abstract void login();
	public abstract void logout();
	
	// Getters and setters
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }
}

enum Role {
	USER, ADMIN;
}
