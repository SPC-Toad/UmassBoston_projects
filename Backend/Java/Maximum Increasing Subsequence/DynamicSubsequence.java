package pa3;

import edu.princeton.cs.algs4.In;

import java.util.*;
import java.io.*;

public class DynamicSubsequence {
private ArrayList<Integer> inputArr;
private ArrayList<Integer> sizes; // Size of longest sequence ending at each index
private ArrayList<Integer> previous; // Track back sequence to previous index
private ArrayList<Integer> output; // output the max array.

	private int i, j, maxSize, maxIndex, maxValue, outputIndex;
	
	public DynamicSubsequence(ArrayList<Integer> Inp)
	{
		// Create arraylist, sizes.
		inputArr = Inp;
		sizes = new ArrayList<>(Inp.size());
		previous = new ArrayList<>(Inp.size());
		output = new ArrayList<>(Inp.size());
	}
	// When the function is called, using the for loop, find the max by comparing all the length.
	// Set the output arraylist to that index arraylist.
	public ArrayList maxSubsequence()
	{
		// Sizes computation
		for (i = 0; i < inputArr.size(); i++) {
			if (i == 0) {
				sizes.add(i, 1);
			} else {
				maxSize = sizes.get(0);
				for (j = 1; j < i; j++) {
					if (inputArr.get(i) > inputArr.get(j) && sizes.get(j) > maxSize) {
						maxSize = sizes.get(j);
					}
				}
				sizes.add(i, maxSize + 1);
			}
		}

		// Find the largest index in sizes.
		maxIndex = 0;
		maxValue = sizes.get(0);
		for (j = 1; j < sizes.size(); j++) {
			if (sizes.get(j) > maxValue) {
				maxIndex = j;
				maxValue = sizes.get(j);
			}
		}

		// From the largest index of sizes, start moving downwards.
		output.add(inputArr.get(maxIndex));

		while (maxValue > 1) {
			for (i = maxIndex - 1; i >= 0; i--) {
				if (inputArr.get(maxIndex) > inputArr.get(i) && sizes.get(i) == maxValue - 1) {
					outputIndex = inputArr.get(i);
					maxIndex = i;
				}
			}
			output.add(outputIndex);
			maxValue = maxValue - 1;
		}

		//Since the order is backwards, reverse the order.
		Collections.reverse(output);

		// Output the ArrayList.
		return output;
	}

	public static void main(String[] args) {
		int len = args.length;
		ArrayList<Integer> Inp = new ArrayList<>();
		for(int i = 0; i< len; i++)
			Inp.add(Integer.parseInt(args[i]));	
		DynamicSubsequence d = new DynamicSubsequence(Inp);
		if (len == 0) return;
		ArrayList<Integer> Outp = d.maxSubsequence();
		System.out.println(Outp);
	}
}
