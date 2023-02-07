import java.awt.Dimension;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
public class AccountDriver {
	
		final static String filename = "Facebook.dat";
		static Scanner input = new Scanner(System.in);


// DISPLAY MENU
		
		public static void DisplayMenu() {
			System.out.println();
			System.out.println("1. List Users Alphabetically");
			System.out.println("2. List Users by Number of Friends");
			System.out.println("3. Add Users");
			System.out.println("4. Remove Users");
			System.out.println("5. Get Password Hint");
			System.out.println("6. Add Friends");
			System.out.println("7. Remove Friends");
			System.out.println("8. List Friends");
			System.out.println("9. Recommend Friends");
			System.out.println("10. Like Something");
			System.out.println("11. Display Your Likes");
			System.out.println("12. Display All Likes");
			System.out.println("13. Undo Last Action");
			System.out.println("14. View User Graph");
			System.out.println("15. Quit");
		}
		
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// LIST USERS ALPHABETICALLY
		public static void listUsersAlphabetically(Facebook fb) {
			
			int i = 1;
			if (fb.listUsersAlp().size() < 1) {
				System.out.println("\nNo users available!");
			} else {
				System.out.println("\n --- LISTING USERS ALPHABETICALLY --- \n");
				for (FacebookUser f : fb.listUsersAlp()){
					System.out.println("User " + i + ": " + f.getUsername());
					i++;
				}
			}
		}
		
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// LIST USER BY FRIENDS
		public static void listUsersbyFriends(Facebook fb) {
			int i = 1;
			if (fb.ListUserbyFriends().size() < 1) {
				System.out.println("\nNo users available!");
			} else {
				System.out.println("\n --- LISTING USERS BY NUMBER OF FRIENDS --- \n");
				for (FacebookUser f : fb.ListUserbyFriends()){
					System.out.println("User " + i + ": " + f.getUsername());
					i++;
				}
			}
		}
		
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// ADD USER
		
		public static void AddUser(Facebook fb) {
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in); 
			
			String username, password, passwordHint = "";
			
			System.out.println("\n --- ADDING USER --- \n");
			System.out.print("Enter username:  ");
			username = input.nextLine();
			
			
			if (fb.checkUsername(username)) {
				//User exists, exit and return
				System.out.println("\nThere is already an account with this username!");
				return;
			}
			System.out.print("Enter a Password:  ");
			password = input.nextLine();
			
			System.out.print("\nEnter a Password Hint:  ");
			passwordHint= input.nextLine();
			
			
			fb.addUser(username, password, passwordHint );
			System.out.println("\nUser Added!");
			
			fb.addUndoElements(username,"AddUser", username);
		}
		
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// DELETE USER
		
		public static void DelUser(Facebook fb) {
			String username, password;
		
			System.out.println("\n --- REMOVING USER --- \n");
			System.out.print("Enter username: ");
			username = input.nextLine();
			
			if(fb.checkUsername(username) == false) {
				 System.out.println("\nInvalid Username");
				 return;
			}
			System.out.print("Enter Password: ");
			password = input.nextLine();
			
			if(fb.checkPassword(username, password)) {
				fb.delUser(username);
				System.out.println("\nUser Deleted");
				
				// undo store
				fb.addUndoElements(username,"DelUser", username);

				
			} else {
				System.out.println("\nInvalid Password!");
			}
			
			
		}

		
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// GET HINT
		
		public static void getHint(Facebook fb) {
			String username;
			
			System.out.println("\n --- USER HINT --- \n");
			System.out.print("Enter username: ");
			username = input.nextLine();
			
			if(fb.checkUsername(username) == false) {
				System.out.println("\nInvalid Username");
				return;
			}
			
			System.out.println("\nThe hint for user \"" + username + "\" is " + fb.getHint(username));
		}
	
		
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// WRITE FACEBOOK
		
		public static void writeFacebook(Object fb) {
			try {
				BufferedOutputStream outFile = new BufferedOutputStream(new FileOutputStream(filename));
	            ObjectOutputStream outObj = new ObjectOutputStream(outFile);
	            outObj.writeObject(fb);
	            outObj.close();
	            System.out.println("\nFile Saved Succesfully");
	        }
			
			catch (IOException ex) {
				System.out.println("\nCould not Create/Save File");
			}
		}
	
		
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// READ FACEBOOK
		
		public static Facebook readFacebook() {
			try {
				
				BufferedInputStream inFile = new BufferedInputStream(new FileInputStream(filename));
	            ObjectInputStream inObj = new ObjectInputStream(inFile);
				Object obj =  inObj.readObject();
				inObj.close();
				Facebook FB = (Facebook)obj;
				System.out.println("--- OPENING FACEBOOK ---");
				return FB;
			}
			catch (FileNotFoundException ex) {
				System.err.println("Could not find file, creating new one.");
				Facebook fb = new Facebook();
				return fb;
			}
			catch (IOException e) {
				System.err.println("Could not de-serialize the object");
			}
			catch (ClassNotFoundException e) {
				System.err.println("Could not cast the de-serialized object");
			}
			
			Facebook fb = new Facebook();
			return fb;
		}
		
		
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// EXIT FACEBOOK
		
		public static void ExitFacebook() {
			System.out.println("\n --- EXITING FACEBOOK --- \n");
			System.exit(0);
		}
		
		
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// ADD FRIEND
		
		public static void AddFriend(Facebook fb) {
				
			String username = "", password = "", frienduser = "";
			
			System.out.println("\n --- ADDING FRIEND --- \n");
			System.out.println("First enter the user that will edit their friends list.");
			System.out.print("\nEnter username:  ");
			username = input.nextLine();
				
			if (fb.checkUsername(username)) {
				//User exists
				System.out.print("Enter a Password:  ");
				password = input.nextLine();
				
				if(fb.checkPassword(username, password)) {
					System.out.print("\nEnter your friend's username: ");
					frienduser = input.nextLine();
					
					if(fb.getUser(username).getFriends().contains(fb.getUser(frienduser))) {
						System.out.println("You are already friends with them!");
					} else {
						
						if (fb.checkUsername(frienduser)) {
							fb.addFriend(fb.getUser(username),fb.getUser(frienduser));
							System.out.println(frienduser + " added to " + username + "'s friends list");
							
							// undo store
							fb.addUndoElements(username,"AddFriend", frienduser);

						} else {
							System.out.println("Couldn't add friend. No user with this username!");
						}
					}
				} else {
					System.out.println("\nInvalid Password!");
				}
			} else {
				System.out.println("No User Exists!");
				return;
			}
			
			
		}
		
		
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// DELETE FRIEND
		
		public static void DelFriend(Facebook fb) {
			
			String username, password, frienduser;
			
			System.out.println("\n --- REMOVING FRIEND --- \n");
			System.out.println("First enter the user that will edit their friends list.");
			System.out.print("\nEnter username:  ");
			username = input.nextLine();
				
				
			if (fb.checkUsername(username)) {
				//User exists
				System.out.print("Enter a Password:  ");
				password = input.nextLine();
				
				if(fb.checkPassword(username, password)) {
					System.out.print("\nEnter your friend's username: ");
					frienduser = input.nextLine();
					if(fb.checkUsername(frienduser)) {
						if (fb.getUser(username).getFriends().contains(fb.getUser(frienduser))) {
							fb.delFriend(fb.getUser(username),fb.getUser(frienduser));
							System.out.println(frienduser + " deleted from " + username + "'s friends list");
							// undo store
							fb.addUndoElements(username,"DelFriendr", frienduser);

							
						} else {
							System.out.println("You must be friends with this user to delete them!");
						}
					} else {
						System.out.println("No User Exists!");
					}
				} else {
					System.out.println("\nInvalid Password!");
				}
			} else {
				System.out.println("No User Exists!");
				return;
			}
			
			
		}
		
		
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// LIST FRIENDS
		
		public static void listFriends(Facebook fb) {
			
			String username;
			
			System.out.println("\n --- LISTNG FRIENDS --- \n");
			
			System.out.println("Enter the user that will view their friends list");
			System.out.print("\nEnter username:  ");
			username = input.nextLine();
				
			if (fb.checkUsername(username)) {
				
					int i = 1;
					if (fb.listFriends(username).size() < 1) {
						System.out.println("\nNo friends in list!");
					} else {
						System.out.println("\n\t" + username + "'s Friends List:");
						for (FacebookUser f : fb.listFriends(username)){
							System.out.println(i + ". " + f.getUsername());
							i++;
						}
					}
			} else {
				System.out.println("Invalid Username!");
			}
		} // end method
		
		
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// RECOMMEND FRIENDS
		
		public static void recommendFriends(Facebook fb) {
			
			
			ArrayList<FacebookUser> recommendations = new ArrayList<>();
			
			String username;
			
			System.out.println("\n --- RECOMMEND FRIENDS --- \n");
			System.out.println("Enter the user to recommend friends.");
			System.out.print("\nEnter username:  ");
			username = input.nextLine();
				
			if (fb.checkUsername(username)) {
				
				recommendations = fb.getRecommendations(fb.getUser(username), recommendations);
				
				int i = 1;
				if (recommendations.size() < 1) {
					System.out.println("\nNo friends in list!");
					
				} else {
					
					// removes your name from the list
					if(recommendations.contains(fb.getUser(username))) {
						recommendations.remove(fb.getUser(username));
					}
					
					//removes user's you are already friends with
					for(FacebookUser f: fb.getUser(username).getFriends()) {
						if(recommendations.contains(f)) {
							recommendations.remove(f);
						}
					}
					
					// lists recommendations
					System.out.println("\n\tRecommending friends for " + username);
					for (FacebookUser f : recommendations){
						System.out.println(i + ". " + f.getUsername());
						i++;
					}
				}
			} else {
				System.out.print("No User Exists!\n");
			}
		}
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// ADD LIKE

		public static void addLike(Facebook fb) {
			String username, password, LikedThing;
			
			System.out.println("\n --- ADDING LIKE --- \n");
			System.out.println("Enter the user that will like something");
			System.out.print("\nEnter username:  ");
			username = input.nextLine();
				
				
			if (fb.checkUsername(username)) {
				//User exists
				System.out.print("Enter a Password:  ");
				password = input.nextLine();
				
				if(fb.checkPassword(username, password)) {
					System.out.print("\nEnter the thing you liked: ");
					LikedThing = input.nextLine();
					fb.getUser(username).addLike(LikedThing);
					
					fb.addUndoElements(username,"AddLike", LikedThing);

					}
				}
			
		}
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// SHOW YOUR LIKES

		public static void showYourLikes(Facebook fb) {
			String username, password;
			
			System.out.println("\n --- SHOWING YOUR LIKES --- \n");
			System.out.println("Enter the user to show likes");
			System.out.print("\nEnter username:  ");
			username = input.nextLine();
				
			if (fb.checkUsername(username)) {
				//User exists
				System.out.print("Enter a Password:  ");
				password = input.nextLine();
				if(fb.getUser(username).UserLikes.size() > 0) {
					if(fb.checkPassword(username, password)) {
						int i = 1;
						System.out.println("\n" + username + " Likes: ");
						for(String like: fb.getUser(username).getUserLikes()) {
							System.out.println(i + ". " + like);
							i++;
						}
					}
				} else { 
					System.out.println(username + " doesn't like anything!");
				}
			}
			
		}
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// SHOW ALL LIKES
		
		public static void showAllLikes(Facebook fb) {
			fb.getAllLikes();			
		}
		
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// UNDO ACTION
		
		public static void UndoAction(Facebook fb) {
			ArrayList<String> names = fb.getUndoLists("users");
			ArrayList<String> manips = fb.getUndoLists("manip");
			String password = "";
			
			if(names.size() > 0 ) {
				String username = names.get(names.size()-1);
				String manip = manips.get(manips.size()-1);
				if(!username.equals(manip)) {
					System.out.println("Enter password for " + username);
					System.out.print(">>> ");
					password = input.nextLine();
					if(fb.checkPassword(username, password)) {
						fb.UndoAction(password);
					}
				} else {
					fb.UndoAction(password);
				}
			} else {
				System.out.println("Nothing left to undo!");
			}
			
			
			
		}
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// SHOW GRAPH
		
			public static void showGraph(Facebook fb) {
				String username, password;
				
				System.out.println("\n --- SHOWING USER GRAPH --- \n");
				System.out.println("Enter the user to show their graph");
				System.out.print("\nEnter username:  ");
				username = input.nextLine();
					
					
				if (fb.checkUsername(username)) {
					//User exists
					System.out.print("Enter a Password:  ");
					password = input.nextLine();
					
					if(fb.checkPassword(username, password)) {
						System.out.println("Displaying graph for " + username);
						fb.display(username);
					}
				}
			}
			
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// MAIN DRIVER
		
		public static void main(String[] args) {
		
		//
		// Facebook Driver
		//
		 
			Facebook fb = readFacebook();
			String choice = "";
			
			System.out.println("\nWelcome!");

			
			while(!choice.equals("0")) {
				
				DisplayMenu();
				
				System.out.print("Enter Your Choice: ");
				choice = input.nextLine();
				
					switch(choice) {
					
						case "1":  // List Users Alphabetically
							listUsersAlphabetically(fb);
							break;
							
						case "2": // List Users by Number of Friends
							listUsersbyFriends(fb);
							break;
							
						case "3": // Add User
							AddUser(fb);
							
							break;
						
						case "4": // Delete User
							DelUser(fb);
							break;
						
						case "5": // Show hint
							getHint(fb);
							break;
						
						case "6": // Add Friend
							AddFriend(fb);
							break;
							
						case "7": // Remove Friend
							DelFriend(fb);
							break;
							
						case "8": //List Friend
							listFriends(fb);
							break;
							
						case "9": // Recommend Friends
							recommendFriends(fb);
							break;
							
						case "10":  // Add Like
							addLike(fb);
							break;	
							
						case "11": // Show your likes
							showYourLikes(fb);
							break;
							
						case "12": // Show all Likes
							showAllLikes(fb);
							break;

						case "13": // Undo Action 
							UndoAction(fb);
							break;
						case "14": // show graph				
							showGraph(fb);
							break;
						case "15": // close menu
							// To serialize an object, we need to use an ObjectOutputStream
							fb.clearUndoElements();
							writeFacebook(fb);
							ExitFacebook();
						
							
						default: // error
							System.out.print("\nError! Number must be between 1-9!\n");
							break;
					
					} // switch bracket
			
			} // while bracket
			
		} // end main string
		
}// end class

