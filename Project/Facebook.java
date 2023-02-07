import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;


public class Facebook implements Serializable, Cloneable, Comparable<FacebookUser> {

	private static final long serialVersionUID = 1L;
	private ArrayList<FacebookUser> Users;
	ArrayList<FacebookUser> UserBackup;
	private ArrayList<String> ActionUser;
	private ArrayList<String> Action;
	private ArrayList<String> Manip;
	private TreeSet<String> Likes;

	
// EXPLANATION -- I used a TreeSet to get likes because it will organize and remove any duplicate likes for the Users. Once I create the TreeSet,
	// I will loop through each user's likes and increment a counter and display it as it loops through the treeset.
	
	public Facebook() {
		this.Users = new ArrayList<FacebookUser>();
		this.Likes = new TreeSet<String>();
		
// EXPLANATION -- I created these 4 arraylists to track all the information needed to undo something. I used an arraylist because there 
		// i just needed to read the last index of each list and then remove it once the undo button was called. I could have used linkedlists and
		// used the method getLast() but I just used the get method for the array and made the index array.size()-1.
		this.Action = new ArrayList<String>();
		this.ActionUser = new ArrayList<String>();
		this.Manip = new ArrayList<String>();
		this.UserBackup = new ArrayList<FacebookUser>();
	}

	
	
	
	// Creates a User Copy for the Driver to Loop Through
	ArrayList<FacebookUser> getUsersCopy() throws CloneNotSupportedException {
			ArrayList<FacebookUser> UserCopy = new ArrayList<FacebookUser>();
			for(FacebookUser fb: Users) {
				UserCopy.add((FacebookUser) fb.clone());
			}
			return UserCopy;
			
		}
		
	
	// Lists Users
	public ArrayList<FacebookUser> listUsersAlp() {
		
		ArrayList<FacebookUser> UserCopy = new ArrayList<FacebookUser>();
		for (FacebookUser f: Users) {
			UserCopy.add(f);
		}
		UserCopy.sort(null);
		return UserCopy;  	}

	
	//List Users by Number of Friends
	public ArrayList<FacebookUser> ListUserbyFriends() {
		ArrayList<FacebookUser> UserCopy = new ArrayList<FacebookUser>();
		for (FacebookUser f: Users) {
			UserCopy.add(f);
		}
		Collections.sort(UserCopy, new NumFriendsComparator());
	
		return UserCopy;
	}
	
	// Adds Users
	public void addUser(String username, String password, String hint) {
			Users.add(new FacebookUser(username, password, hint));

	}
	
	
	// Deletes Users
	public void delUser(String username) {
			for (FacebookUser f : Users) {
				if(f.getUsername().equals(username)) {
					UserBackup.add(f);
					Users.remove(f);
				}
			}
		}
	
	public FacebookUser getDelUser(String username) {
		
		for (FacebookUser f : UserBackup) {
			if(f.getUsername().equals(username)) {
				return f;
			}
			
		}
		return null;
	}
	
	// Gets User for given Username
	public FacebookUser getUser(String username) {
		
		for (FacebookUser f : Users) {
			if(f.getUsername().equals(username)) {
				return f;
			}
			
		}
		return null;
	}
	
	
	// Shows the Hint for the Selected User
	public String getHint(String username) {
		for(FacebookUser f: Users) {
			if(f.getUsername().equals(username)) {
				return f.getPasswordHint();
			}
		}
		return null;
	}
		
	
	// checks username if in Users list
	public boolean checkUsername(String username) {
		for(FacebookUser f : Users) {
			if(f.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	
	// checks password for selected user
	public boolean checkPassword(String username, String password) {
		for (FacebookUser f : Users) {
			if (f.getUsername().equals(username)) {
				return f.checkpass(password);
			}
		}
		return false;
	}
	
	
	// Adds selected friend to user's friends list
	public void addFriend(FacebookUser userAcc, FacebookUser newFriend) {
		userAcc.friends.add(newFriend);    // O(1) because only one line to add element to array.
    }	
	
	
	// Deletes selected friend from user's friends list
	public void delFriend(FacebookUser userAcc, FacebookUser newFriend) {
		userAcc.friends.remove(newFriend);
    }	
	
	
	// returns a copy of the selected user's friends list
	@SuppressWarnings("unchecked")
	public ArrayList<FacebookUser> listFriends(String username) {
		
		return (ArrayList<FacebookUser>)getUser(username).friends.clone();
		
	}
	
	
	// Recursive method that creates a list of recommended friends for selected user
	ArrayList<FacebookUser> getRecommendations(FacebookUser userAcc, ArrayList<FacebookUser> recommendations) {
		
		// O(2^n) because the for loop will be ran expoentially if more users are added
		for (FacebookUser f: userAcc.getFriends()) {
			if(recommendations.contains(f)) {
				return recommendations;
			} else {
				recommendations.add(f);
				getRecommendations(f, recommendations);
			}
		}
		Collections.sort(recommendations, new NumFriendsComparator());
		
		return recommendations;
	}

	
	TreeSet<String> getAllLikes() {		
		for(FacebookUser f: Users) {
			Likes.addAll(f.UserLikes);
		}
		
		
		System.out.println("\nPost:\t\tTotal Likes:\n");
		for(String like1: Likes) {
			int tot = 0;
			for(FacebookUser f: Users) {
				for(String like2: f.UserLikes) {
					if(like1.equals(like2)) {
						tot++;
					}
				}
			}
		System.out.println(like1 + "\t\t" + tot);
		
		}
	
		return Likes;
	}

	public void addUndoElements(String user, String action, String manip) {
		Action.add(action);
		ActionUser.add(user);
		Manip.add(manip);
	}
	
	public void clearUndoElements() {
		Action.clear();
		ActionUser.clear();
		Manip.clear();
	}
	
	ArrayList<String> getUndoLists(String choice) {
		ArrayList<String> UserCopy = new ArrayList<String>();
		switch(choice) {
			case "users":
				for(String u: ActionUser) {
					UserCopy.add(u);
				}
				return UserCopy;
			case "action":
				for(String u: Action) {
					UserCopy.add(u);
				}
				return UserCopy;
		
			case "manip":
				for(String u: Manip) {
					UserCopy.add(u);
				}
				return UserCopy;
				
			default:
				return UserCopy;
		}
		
	
		
	}
	public void UndoAction(String password) {
			// AddUser, DelUser, AddFriend, DelFriend, AddLike
			// Action, ActionUser, Manip   ArrayLists
			String action = Action.get(Action.size()-1);			
			String username = ActionUser.get(ActionUser.size()-1);
			String manip = Manip.get(Manip.size()-1);
			
			switch(action) {
			case "AddUser":
				Users.remove(getUser(manip));
				System.out.println("Removed " + manip + " From Users");
				break;
			case "DelUser":
				Users.add(getDelUser(manip));
				System.out.println("Added " + manip + " To Users");
				break;
			case "AddFriend":
				getUser(username).friends.remove(getUser(manip));
				System.out.println("Removed " + manip + " from " + username + "'s friends list");
				break;
			case "DelFriend":
				getUser(username).friends.add(getUser(manip));
				System.out.println("Added " + manip + " back to " + username + "'s friends list");

				break;
			case "AddLike":
				getUser(username).UserLikes.remove(manip);
				System.out.println(username + " unliked " + manip);
				break;
			}
			
			Action.remove(Action.size()-1);
			ActionUser.remove(ActionUser.size()-1);
			Manip.remove(Manip.size()-1);
	}
	
		
	public GraphViewer display(String username) {
		return new GraphViewer(getUser(username));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		try {
			//Shallow Copy
					
			Facebook fclone = (Facebook)super.clone();
			//Deep Copy
			fclone.Users= (ArrayList<FacebookUser>)Users.clone();
			return fclone;
			
			
		}
		catch (CloneNotSupportedException ex ) {
			return null;
		}
		
	}



	@Override
	public int compareTo(FacebookUser o) {
		if (o.username.compareToIgnoreCase(o.username) != 0) {
			return o.username.compareToIgnoreCase(o.username);
		}
		return 0;
	}
	
}



