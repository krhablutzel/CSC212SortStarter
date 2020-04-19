package edu.smith.cs.csc212.sorts;

import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.TODOErr;
import me.jjfoley.adt.impl.JavaList;

public class MergeSort {

	/**
	 * This method walks through two sorted input lists and creates an output list that contains all elements from the two inputs.
	 * @param lhs - a sorted list.
	 * @param rhs  - a sorted list.
	 * @return a sorted list containing all of the items from lhs and rhs.
	 */
	public static ListADT<Integer> combineTwoSortedLists(ListADT<Integer> lhs, ListADT<Integer> rhs) {
		ListADT<Integer> output = new JavaList<>();
		int lFront;
		int rFront;
		
		while (lhs.size() > 0 && rhs.size() > 0) {
			lFront = lhs.getFront();
			rFront = rhs.getFront();
			
			if (lFront <= rFront) {
				output.addBack(lFront);
				lhs.removeFront();
			} else if (rFront < lFront) {
				output.addBack(rFront);
				rhs.removeFront();
			}
		}
		
		// add any remaining elements in one list or the other
		output.addAll(lhs);
		output.addAll(rhs);
		
		return output;
	}
	
	/**
	 * Recursively sort this list by breaking it in half and piecing it back together.
	 * You will need to call {@linkplain #combineTwoSortedLists(ListADT, ListADT)}.
     *
	 * @param input - the input list.
	 * @return a new list containing the sorted output.
	 */
	public static ListADT<Integer> doMergeSortRecursively(ListADT<Integer> input) {
		if (input.size() == 1) {
			return input;
		}
		int middle = input.size() / 2;
		return combineTwoSortedLists(doMergeSortRecursively(input.slice(0, middle)), doMergeSortRecursively(input.slice(middle, input.size())));
	}
	
	/**
	 * Iteratively sort this list by breaking it in half and piecing it back together.
	 * You will need to call {@linkplain #combineTwoSortedLists(ListADT, ListADT)}.
	 * 
	 * @param input - the input list.
	 * @return a new list containing the sorted output.
	 */
	public static ListADT<Integer> doMergeSortIteratively(ListADT<Integer> input) {
		// Make list with each element in size 1 list
		ListADT<ListADT<Integer>> pieces = new JavaList<>();
		for (int i = 0; i < input.size(); i++) {
			pieces.addBack(input.slice(i, i+1));
		}
		
		// Merge
		while (pieces.size() > 1) {
			pieces.addBack(combineTwoSortedLists(pieces.getFront(), pieces.getIndex(1)));
			pieces.removeFront();
			pieces.removeFront();
		}
		
		return pieces.getFront();
	}
}
