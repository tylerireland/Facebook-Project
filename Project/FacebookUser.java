import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;

@SuppressWarnings("serial")
public class FacebookUser extends UserAccount implements Cloneable, Comparable<FacebookUser> {
	
	String passwordHint;
	ArrayList<FacebookUser> friends;
	
//EXPLANATION -- I used a tree set for individual user's likes to prevent duplicates and to sort the likes alphabetically
//	this will simplify the code instead of looping through an array and checking indivdidually for duplicates. 
//	This also makes sorting very fast easy.
	
	public TreeSet<String> UserLikes;

	public FacebookUser(String username, String password, String hint) {
		super(username, password);
		this.passwordHint = hint;
		this.friends = new ArrayList<FacebookUser>();
		this.UserLikes = new TreeSet<String>();
	}
	
	
	// sets password hint
	void setPasswordHint(String hint) {
		passwordHint = hint;
	}
	
	
	//displays password hint
	public void getPasswordHelp() {
		System.out.println("The password hint is: " + passwordHint);
		System.out.println();
		
	}

	
	// returns the password hint for selected user
	public String getPasswordHint() {
		return passwordHint;
	}

	
	// adds friend to users friends list
	void friend(FacebookUser newFriend) {
		for(FacebookUser f: this.friends) {
			if(newFriend.getUsername().equals(f.getUsername())) {
				System.out.println("You are already friends with them!");
				return;
			}
		}
		friends.add(newFriend);
		System.out.println("Friend Added");
	}
	

	// deletes friend from users friends list
	void defriend(FacebookUser formerFriend) {
		for(FacebookUser f: this.friends) {
			if(formerFriend.getUsername().equals(f.getUsername())) {
				this.friends.remove(formerFriend);
				System.out.println("Friend Removed");
				return;
			}
		}
			System.out.println("You are not friends with them!");
	}
	
	
	// creates and displays a copy of the friends list
	public ArrayList<FacebookUser> getFriends(){
		//Return a COPY of the array, sorted by username.
		ArrayList<FacebookUser> copy = new ArrayList<FacebookUser>();
		for (FacebookUser f: friends) {
			copy.add(f);
		}
		copy.sort(null);
		return copy;  
	}
	
	public TreeSet<String> getUserLikes() {
		TreeSet<String> copy = new TreeSet<String>();
		for (String str: UserLikes) {
			copy.add(str);
		}
		
		return copy;  
	}
	public void addLike(String LikedThing) {
		if(UserLikes.contains(LikedThing)) {
			System.out.println("You already liked this!");
		} else if(LikedThing.length() == 0) {
			System.out.println("You have to like something!");
		} else {
			UserLikes.add(LikedThing);
			System.out.print("\n" + this.username + " likes " + LikedThing + "\n");
		}
	}
	
	
	
	// method to sort the list by username
	public int compareTo(FacebookUser o) {
		if (this.username.compareToIgnoreCase(o.username) != 0) {
			return this.username.compareToIgnoreCase(o.username);
		}
		return 0;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		try {
			//Shallow Copy
					
			FacebookUser fbclone = (FacebookUser)super.clone();
			//Deep Copy
			fbclone.friends = (ArrayList<FacebookUser>)friends.clone();
			return fbclone;
			
			
		}
		catch (CloneNotSupportedException ex ) {
			return null;
		}
		
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(friends);
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FacebookUser other = (FacebookUser) obj;
		return Objects.equals(friends, other.friends);
	}

	
}
