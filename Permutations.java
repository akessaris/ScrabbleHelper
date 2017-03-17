package edu.nyu.ds.ank352;

import java.util.ArrayList;

/**
 * This class represents the sequence of letters from which the words should be constructed.
 * This class also constructs all the words and permutations using the Dictionary object.
 * @author alexanderkessaris
 * @version 0.1 
 * 
 * Problems to fix:
 	* How to use isPrefix
 	* Using isWord
 */
public class Permutations {
	/**
	 *Declare variable to store letters
	 */
	String word;
	
	/**
	 * One parameter constructor that takes a String object containing the sequence of characters to be used.
	 * @param letters - second command line argument
	 * @throws IllegalArgumentException - if "letters" argument contains illegal characters (anything not a-z or A-Z)
	 */
	Permutations (String word) throws IllegalArgumentException {
		/**
		 * Initialize the letters char array
		 */
		char [] letters = word.toCharArray();
		/**
		 * Check that word contains only alphabetic characters
		 */
		for (int i = 0; i<letters.length; i++) {
			if (!Character.isLetter(letters[i])) {
				throw new IllegalArgumentException("Error: This program only searches for single words containing letters.");
			}
		}
		this.word=word;
	}
	
	/**
	 * Computes and returns list of all permutations of letters in this permutation object
	 * @return ArrayList<String> - permutations
	 * @throws OutOfMemoryError - for objects containing more that 10 letters
	 */
	public ArrayList<String> getAllPermutations() throws OutOfMemoryError {
		/**
		 * Generate  and return permutations by calling recursive generatePermutations method
		 */
		ArrayList<String> permutations = generatePermutations("", word, new ArrayList<String>());
		return permutations;
	}
	
	/**
	 * Generate the permutations using recursion
	 * @param prefix - portion of permutation being constructed
	 * @param word - entire word
	 * @param permutations - ArrayList<String> to hold permutations
	 * @return ArrayList<String> - contains permutations
	 */
	private ArrayList<String> generatePermutations(String prefix, String word, ArrayList<String> permutations) {
		/**
		 * Base case - there are no more letters to add to the prefix
		 * Add the permutation to the permutations ArrayList (if it's not already contained)
		 */
		if (word.length()==0 && !permutations.contains(prefix)) {
			permutations.add(prefix);
		}
		else {
			/**
			 * Iterate through each character index of word and pass it with the remaining string
			 */
			for (int i=0; i<word.length();i++) {	
					generatePermutations(prefix + word.charAt(i), word.substring(0, i) + word.substring(i+1, word.length()), permutations);
			}
		}
		/**
		 * Return permutations
		 */
		return permutations;
	}
	
	/**
	 * Computes and returns a list of all words contained in the Dictionary object that can be created from the letters in this Permutation object
	 * @param dictionary - Dictionary object
	 * @return - ArrayList<String> words contained in the dictionary
	 */
	public ArrayList<String> getAllWords (Dictionary dictionary) {	
		/**
		 * Generate words by calling recursive generateWords method
		 */
		ArrayList<String> words = generateWords("", word, new ArrayList<String>(), dictionary);
		return words;		
	}

/**
 * Recursive method that generates permutations that exist within the dictionary in the Dictionary object 
 * @param prefix - first letter of permutation
 * @param word - rest of letters
 * @param words - ArrayList<String> to hold words
 * @param dictionary - Dictionary object to use its methods
 * @return ArrayList<String> permutations - permutations of the word passed as a command line argument that exist within the dictionary
 */
private ArrayList<String> generateWords(String prefix, String word, ArrayList<String> words, Dictionary dictionary) {
	/**
	 * Base case - length of word is 0
	 * Add the completed permutation to the permutation ArrayList while preventing duplicates
	 * Check to make sure the prefix is a word within the Dictionary
	 */
	if ( (word.length()==0) && !words.contains(prefix) && dictionary.isWord(prefix) ) {
		words.add(prefix);
	}
	else {
		/**
		 * Iterate through character index of word and pass it with the remaining string
		 */
		for (int i=0; i<word.length();i++) {	
			/**
			 * Check if prefix (and the character it's planning to add to create a permutation) is actually a valid prefix of one of the words in the dictionary
			 * Use isPrefix to make program more efficient
			 */
			if (dictionary.isPrefix( (prefix+word.charAt(i)))) {
				generateWords(prefix + word.charAt(i), word.substring(0, i) + word.substring(i+1, word.length()), words, dictionary);
			}
		}
	}
	return words;
}
}