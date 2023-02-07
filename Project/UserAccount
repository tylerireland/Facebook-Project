import java.io.*;

public abstract class UserAccount implements Serializable {

	protected String username;
	private String password;
	private boolean active;
	
	public UserAccount() {
		username = "";
		password = "";
		
	}
	
	public UserAccount(String username, String password) {
		this.username = username;
		this.password = password;
		active = true;
	}
	
	
	// checks password
	public boolean checkpass(String password) {
		if (this.password.equals(password)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	// deactives Account
	public void deactivateAccount() {
		active = false;
	}

	
	// returns the user's username
	public String getUsername() {
		return username;
	}
	
	
	// Prints object's contents as a string
	@Override
	public String toString() {
		return username;
	}

	
	// abstract method to return hint
	public abstract void getPasswordHelp();
	
}
