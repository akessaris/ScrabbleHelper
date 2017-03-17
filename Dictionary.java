package edu.nyu.ds.ank352;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This class represents the collection of words read in the input file (the dictionary)
 * @author alexanderkessaris
 * @version 0.1
 */
public class Dictionary {

	/**
	 * ArrayList to store dictionary words
	 */
	ArrayList<String> dictionary = new ArrayList<>();
	
	/**
	 * One parameter constructor that takes File object as a parameter
	 * @param f - File object
	 * @throws IllegalArgumentException - if file can't be read or found
	 */
	public Dictionary (File f) throws IllegalArgumentException {
		/**
		 * Try reading in the file
		 */
		try {
			/**
			 * Read in the file using Scanner object
			 * Add each word into dictionary
			 */
			Scanner scanner = new Scanner(f);
			while (scanner.hasNext()) {
				dictionary.add(scanner.next());
			}
			/**
			 * Sort the dictionary so that binary search will be accurate
			 */
			Collections.sort(dictionary);
			
			/**
			 * Close the scanner
			 */
			scanner.close();
		}
		/**
		 * Throw IllegalArgumentException containing message if file not found
		 */
		catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Error: File can't be found.");
		}
	}
	
	/**
	 * Determines if the argument string "str" is one of the words stored in this dictionary by calling
	 * the recursive wordSearch method
	 * @param str - target word
	 * @return true if dictionary contains the word or false if dictionary doesn't (depends on return value of wordSearch)
	 */
	public boolean isWord(String str) {
		return wordSearch(this.dictionary, str, 0, this.dictionary.size()-1);
	}
	
	/**
	 * Recursive method that uses binary search to see if a word "str" is contained within the dictionary
	 * @param dictionary - ArrayList<String> dictionary
	 * @param str - target word
	 * @param min - starting index
	 * @param max - ending index
	 * @return true if dictionary contains word or false if dictionary doesn't
	 */
	private boolean wordSearch(ArrayList<String> dictionary, String str, int min, int max ) {
		/**
		 * If word can't be found, then return false
		 */
		if (min>max) {
			return false;
		}
		
		/**
		 * Set midpoint to average of min and max
		 */
		int midpoint = (min+max)/2;
		
		/**
		 * Base case - if the midpoint equals the target str, return true
		 */
		if (dictionary.get(midpoint).equals(str)) {
			return true;
		}
		
		/**
		 * General/Recursive cases
		 */
		else {
			/**
			 * Midpoint is "greater" than target str
			 * Eliminate upper half and call itself again
			 */
			if (dictionary.get(midpoint).compareTo(str)>0) {
				return wordSearch(dictionary, str, min, midpoint-1);
			}
			/**
			 * Midpoint is "less" than target str
			 * Eliminate lower half and call itself again
			 */
			else {
				return wordSearch(dictionary, str, midpoint+1, max);
			} 
		}
	}
	
	/**
	 * Determines if the argument string "str" is a prefix for at least one of the words stored in the dictionary
	 * @param str - supposed prefix
	 * @return true or false depends on return value from prefixSearch
	 */
	public boolean isPrefix (String str) {
		return prefixSearch(this.dictionary, str, 0, this.dictionary.size()-1);
	}
	
	private boolean prefixSearch(ArrayList<String> dictionary, String prefix, int min, int max) {
		/**
		 * Return false if "prefix" isn't a prefix for any word in the dictionary
		 */
		if (min>max) {
			return false;
		}
		
		/**
		 * Set midpoint to average of min and max indexes of list
		 */
		int midpoint = (min+max)/2;
		
		/**
		 * Base case - "prefix" is a prefix for a word in the dictionary
		 */
		if (dictionary.get(midpoint).startsWith(prefix)) {
			return true;
		}
		/**
		 * General Cases - if midpoint doesn't start with "prefix"
		 */
		else {
			/**
			 * Midpoint is "greater" than target str
			 * Eliminate upper half and call itself again
			 */
			if (dictionary.get(midpoint).compareTo(prefix)>0) {
				return prefixSearch(dictionary, prefix, min, midpoint-1);
			}
			/**
			 * Midpoint is "less" than target str
			 * Eliminate lower half and call itself again
			 */
			else {
				return prefixSearch(dictionary, prefix, midpoint+1, max);
			}
		}		
	}	
}