package edu.smith.cs.csc212.sorts;

import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.impl.JavaList;

public class SlowSorts {

	/**
	 * Read through the list in data and return true only if it's sorted.
	 * 
	 * @param data - a list of numbers.
	 * @return true if they are sorted, false if not.
	 */
	public static boolean isSorted(ListADT<Integer> data) {
		boolean sorted = true;
		
		for (int i = 0; i < data.size() - 1; i++) {
			if (data.getIndex(i) > data.getIndex(i + 1)) {
				sorted = false;
			}
		}
		
		return sorted;
	}


	/**
	 * Insert the value x in to the sorted list "target" in the correct position.
	 * Helper for {@linkplain #insertionSort}; complexity O(n).
	 * 
	 * @param x      - the new number to insert.
	 * @param target - the sorted list to modify (might be empty!)
	 */
	public static void insertSorted(int x, ListADT<Integer> target) {
		if (target.isEmpty()) {
			target.addFront(x);
			return;
		}
		
		int insertIndex = insertSortedR(x, target, 0, target.size());
		target.addIndex(insertIndex, x);
	}
	
	/**
	 * Find where to insert an integer in the sorted list target by recursive binary search.
	 * 
	 * @param x - the integer you're inserting
	 * @param target - the list you're searching!
	 * @param left - the farthest left index it could possibly be (default=0).
	 * @param right - the farthest right index it could possibly be (default=dataset.size()).
	 * @return the index to insert x in target.
	 */
	private static int insertSortedR(int x, ListADT<Integer> target, int left, int right) {
		int middle = (left + right) / 2;
		int atMiddle = target.getIndex(middle);
		
		// ways to end
		if (middle == left) {
			// narrowed down to 1 - do we go right or left of it?
			if (atMiddle > x) {
				return middle;
			} else {
				return middle + 1;
			}
		} else if (left == target.size() - 1) {
			return right;
		}
		
		// narrowing down range
		if (atMiddle < x) {
			return insertSortedR(x, target, middle, right);
		} else {
			return insertSortedR(x, target, left, middle);
		}
	}

	/**
	 * Find the position of the minimum element of list starting at start. Helper
	 * for selectionSort; complexity: O(n).
	 * 
	 * @param list  - the full list (NOT sorted)
	 * @param start - where to start in list (don't look to the left).
	 * @return the position (int greater than start) of the minimum element.
	 */
	public static int findMinPosition(ListADT<Integer> list, int start) {
		assert (start < list.size()) : "There should be stuff in the list to the right of start!";
		
		int min = list.getIndex(start);
		int minPosition = start;
		
		for (int i = start; i < list.size(); i++) {
			if (min > list.getIndex(i)) {
				min = list.getIndex(i);
				minPosition = i;
			}
		}

		return minPosition;
	}

	/**
	 * InsertionSort: Create a new output list that contians all elements of input
	 * but in sorted order. This is very short if you call {@link #insertSorted}.
	 * 
	 * @param input - the list to sort.
	 * @return a new sorted list -- just use JavaList<>().
	 */
	public static ListADT<Integer> insertionSort(ListADT<Integer> input) {
		ListADT<Integer> output = new JavaList<>();
		
		for (Integer x : input) {
			insertSorted(x, output);
		}
		
		return output;
	}

	/**
	 * SelectionSort: Move through the input list left-to-right and swap the minimum
	 * element of the list to the current position until you reach the end.
	 * 
	 * Helpful: - {@linkplain #findMinPosition(ListADT, int)} -
	 * {@linkplain ListADT#swap(int, int)} is also really helpful :)
	 * 
	 * @param fixMe - the input and output of this method -- it modifies a list
	 *              in-place.
	 */
	public static void selectionSort(ListADT<Integer> fixMe) {
		for (int i = 0; i < fixMe.size(); i++) {
			fixMe.swap(i, findMinPosition(fixMe, i));
		}
	}

}
