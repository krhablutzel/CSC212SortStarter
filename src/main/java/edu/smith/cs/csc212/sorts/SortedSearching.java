package edu.smith.cs.csc212.sorts;

import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.TODOErr;
import me.jjfoley.adt.impl.JavaList;

/**
 * A demonstration class for showing why we would want to sort values.
 * @author jfoley
 *
 */
public class SortedSearching {

	/**
	 * Find an integer in the sorted list dataset by linear search.
	 * @param findMe
	 * @param dataset
	 * @return -1 if not found or the position if found.
	 */
	public static int linearSearch(int findMe, ListADT<Integer> dataset) {
		for (int i=0; i<dataset.size(); i++) {
			if (dataset.getIndex(i) == findMe) {
				return i;
			} else if (dataset.getIndex(i) > findMe) {
				// In a sorted list, we can stop as soon as it's no longer possibly in this list!
				return -1;
			}
		}
		return -1;
	}
	
	/**
	 * Find an integer in the sorted list dataset by iterative binary search.
	 * 
	 * HINT: Try a while loop.
     *
	 * @param findMe - the integer you're looking for!
	 * @param dataset - the list you're searching!
	 * @return -1 if not found or the position if found.
	 */
	public static int binarySearchIterative(int findMe, ListADT<Integer> dataset) {
		int left = 0;
		int right = dataset.size();
		int middle;
		int atMiddle;
		
		while (right != left && left != dataset.size() - 1) {
			middle = (left + right) / 2;
			atMiddle = dataset.getIndex(middle);
			
			if (atMiddle == findMe) {
				return middle;
			} else if (atMiddle < findMe) {
				left = middle;
			} else {
				right = middle;
			}
		}
		
		return -1;
	}

	/**
	 * Find an integer in the sorted list dataset by recursive binary search.
	 * 
	 * @param findMe - the integer you're looking for!
	 * @param dataset - the list you're searching!
	 * @param left - the farthest left index it could possibly be (default=0).
	 * @param right - the farthest right index it could possibly be (default=dataset.size()).
	 * @return -1 if not found, or the index of findMe in dataset.
	 */
	private static int binarySearchR(int findMe, ListADT<Integer> dataset, int left, int right) {
		int middle = (left + right) / 2;
		int atMiddle = dataset.getIndex(middle);
		
		if (right == left || left == dataset.size() - 1) {
			return -1;
		}
		
		if (atMiddle == findMe) {
			return middle;
		} else if (atMiddle < findMe) {
			return binarySearchR(findMe, dataset, middle, right);
		} else {
			return binarySearchR(findMe, dataset, left, middle);
		}
	}
	
	/**
	 * Find an integer in the sorted list dataset by recursive binary search.
     *
	 * @param findMe - the integer you're looking for!
	 * @param dataset - the list you're searching!
	 * @return -1 if not found or the position if found.
	 */
	public static int binarySearchRecursive(int findMe, ListADT<Integer> dataset) {
		// This is perfect:
		return binarySearchR(findMe, dataset, 0, dataset.size());
	}
}
