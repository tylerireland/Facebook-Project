import java.util.Comparator;

public class NumFriendsComparator implements Comparator<FacebookUser> {

	@Override
	public int compare(FacebookUser o1, FacebookUser o2) {
		if(o1.getFriends().size() > o2.getFriends().size()) {
			
			return -1;
		} else if(o1.getFriends().size() < o2.getFriends().size()) {
			
			return 1;
		} else {
		
			return 0;
	    }
	}
}
