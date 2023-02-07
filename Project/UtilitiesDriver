import java.util.ArrayList;

public class UtilitiesDriver {
	
	public static void main(String[] args) {
		
		ArrayList<Integer> numList = new ArrayList<Integer>();
		ArrayList<String> strList = new ArrayList<String>();
		ArrayList<FacebookUser> userList = new ArrayList<FacebookUser>();
		
	    numList.add(5);
	    numList.add(7);	 
	    numList.add(9);
	    numList.add(4);
	    numList.add(1);
	    numList.add(3);
	    numList.add(8);
	    numList.add(0);

	    strList.add("CUCUMBER");
	    strList.add("FRUIT");
	    strList.add("STRAWBERRY");
	    strList.add("KIWI");
	    strList.add("APPLE");
	    strList.add("BANANA");

	    
	    userList.add(new FacebookUser("TYLER","ireland","hint"));
	    userList.add(new FacebookUser("ALLEN","ireland","hint"));
	    userList.add(new FacebookUser("ZACK","ireland","hint"));
	    userList.add(new FacebookUser("KYLE","ireland","hint"));
	    userList.add(new FacebookUser("TAYLOR","ireland","hint"));

	    
	    System.out.println("USING INSERTION SORT\n");
	    System.out.println("INTEGERS");
    
	    
	    ArrayList<Integer> numSort = Utilities.InsertionSort(numList);
	   
	    System.out.println("\n\tUnsorted:");
	    Utilities.printList(numList);

	    System.out.println("\n\tSorted:");
	    Utilities.printList(numSort);

	    
	    System.out.println("\n\nSTRINGS");

	    ArrayList<String> strSort = Utilities.InsertionSort(strList);
	   
	    System.out.println("\n\tUnsorted:");
	    Utilities.printList(strList);

	    System.out.println("\n\tSorted:");
	    Utilities.printList(strSort);

	    
	    System.out.println("\n\nFACEBOOKUSERS");

	    ArrayList<FacebookUser> userSort = Utilities.InsertionSort(userList);
	   
	    System.out.println("\n\tUnsorted:");
	    Utilities.printList(userList);

	    System.out.println("\n\tSorted:");
	    Utilities.printList(userSort);

	    
	//
	// --------------------------------------------------------------------------------------------    
	//		QUICK SORT
	    
	    System.out.println("\n\n\n\n\nUSING QUICK SORT\n");
	    System.out.println("INTEGERS");
    
	    
	    ArrayList<Integer> numSortq = Utilities.QuickSort(numList);
	   
	    System.out.println("\n\tUnsorted:");
	    Utilities.printList(numList);
	    System.out.println("\n\tSorted:");
	    Utilities.printList(numSortq);

	    
	    System.out.println("\n\nSTRINGS");

	    ArrayList<String> strSortq = Utilities.QuickSort(strList);
	   
	    System.out.println("\n\tUnsorted:");
	    Utilities.printList(strList);

	    System.out.println("\n\tSorted:");
	    Utilities.printList(strSortq);

	    
	    System.out.println("\n\nFACEBOOKUSERS");

	    ArrayList<FacebookUser> userSortq = Utilities.QuickSort(userList);
	   
	    System.out.println("\n\tUnsorted:");
	    Utilities.printList(userList);

	    System.out.println("\n\tSorted:");
	    Utilities.printList(userSortq);

    }
}
