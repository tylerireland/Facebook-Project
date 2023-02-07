import java.util.ArrayList;


public class Utilities<E extends Comparable<E>> {
	
	public static <E> ArrayList<E> removeDupes(ArrayList<E> list) {
		
		ArrayList<E> newList = new ArrayList<E>();
		
		for(E item : list) {
			
			if(!newList.contains(item)) {
				newList.add(item);
			}
			
		}
		return newList;
	}

	public static <E extends Comparable<E>> int linearSearch(E[] list, E key) {
			
		int listSize= list.length-1; 			     
			for(int x =0; x < listSize; x++) {		 
				if(list[x] == key) {			     
					return x;						 
				}
			}
			
			System.out.print("No index found!");	 
			return -1;								 
	}
	
	public static <E> void printList(ArrayList<E> list) {
		System.out.print("[");
		for(int i = 0; i < list.size();i++) {
			System.out.print(list.get(i));
			if((i+1) < list.size()) {
				System.out.print(", ");
			} else {
				System.out.print("]");

			}
		}
	}
	// Integer[] = {0,6,4,7,8,5}
	public static <E extends Comparable<E>> ArrayList<E> InsertionSort(ArrayList<E> list) {
		
		ArrayList<E> sortedList = new ArrayList<E>();
		System.out.println("Original List:");
		for(E element: list) {
			sortedList.add(element);
		}
		printList(sortedList);
		System.out.println();
		
		for(int i = 0; i < sortedList.size(); i++) {
			E current = sortedList.get(i);
			System.out.println("Element " + (i+1) + ": '" + current + "'");
			//System.out.println("Get element " + i + ": '" + current + "'");
			int pass = 1;
			for(int k = i -1; k >= 0 && sortedList.get(k).compareTo(current) > 0; k--) {
				
				System.out.print("Pass " + pass + " :  ");
				//System.out.println("'" + current + "' is less than '" + sortedList.get(k) + "'\n");

				sortedList.set(k+1,sortedList.get(k));
				//System.out.println("Moving '" + sortedList.get(k) + "' Forward");
				sortedList.set(k, current);
				//System.out.println("Putting '" + current + "' in it's spot.\n");

				//System.out.println("Current List:");
				printList(sortedList);
				System.out.println(" <<< Swapped " + current + " & " + sortedList.get(k+1));
				pass++;
			}
			System.out.println("'" + current + "' is sorted.\n");
		}
		System.out.println("The list is now sorted.");
		return sortedList;
		
	}
	
	
	
	
	

	public static <E extends Comparable<E>> ArrayList<E> QuickSort(ArrayList<E> list) {
		ArrayList<E> sortedList = new ArrayList<E>();
		for(E element: list) {
			sortedList.add(element);
		}
		
		System.out.println("Original List:");
		printList(sortedList);
		System.out.println();
		quickSort(sortedList,  0, list.size()-1);
		
		return sortedList;
		

	}
	public static <E extends Comparable<E>> void quickSort(ArrayList<E> list, int first, int last) {
		if(last > first) {
			int pivotIndex = partition(list,first,last);
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
			
		}
	}


	private static <E extends Comparable<E>> int partition(ArrayList<E> list, int first, int last) {
		E pivot = list.get(first); // element at first index
		System.out.println("Pivot: " + pivot);
		int low = first + 1; // index for forward search
		int high = last;	// index for backward search
		while(high > low) {

			// search forward from left
			while(low <= high && list.get(low).compareTo(pivot) <= 0) {
				low++;

			}
			
			
			// search backward from right
			while(low <= high && list.get(high).compareTo(pivot) > 0) {
				high--;
			}
			

			// swap two elements in list
			if (high > low) {

				E temp = list.get(high);
				list.set(high, list.get(low));
				list.set(low, temp);
				
				
				
			}
		}
		
		while(high > first && list.get(high).compareTo(pivot) >= 0) {
			high--;
		}
	

		// swap pivot with list.get(high)
		if(pivot.compareTo(list.get(high)) > 0) {
			
			list.set(first, list.get(high));
			list.set(high, pivot);
			return high;
		} else {
			return first;
		}
	}
}
