package edu.nyu.ds.ank352;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This program produces all possible words given a collection of letters and a dictionary.
 * @author alexanderkessaris
 * @version 0.1
 * 
 * Command Line (For my personal use):
cd Documents/workspace/Kessaris,Alexander-Project2/bin
java edu.nyu.ds.ank352.ScrabbleHelper ~/Desktop/dictionary1.txt tac
java edu.nyu.ds.ank352.ScrabbleHelper ~/Desktop/big_dict.txt aeicdlm
 */
public class ScrabbleHelper {

	/**
	 * Main method - Responsible for parsing the command line arguments, creating the Dictionary and Permutations objects and displaying the results.
	 * @param args (args[0]=file name; args[1]=search word)
	 */
	public static void main(String[] args) {
		/**
		 * Try to run program (read file, accept arguments, etc.)
		 */
		try {
			/**
			 * Throw IllegalArgumentException if the user doesn't enter two arguments.
			 */
			if (args.length!=2) {
				throw new IllegalArgumentException("Error: This program expects two arguments (file name and word).");
			}	
			/**
			 * Create Permutations object using second command line argument
			 * Use this object and the Dictionary object (second command line argument) to get all the permutations that 
			 * match the words in the dictionary
			 */
			Permutations permutations = new Permutations(args[1].toLowerCase());
			ArrayList<String> words = permutations.getAllWords(new Dictionary(new File(args[0])));
			
			/**
			 * Sort the permutations so they appear alphabetically
			 */
			Collections.sort(words);
			
			/**
			 * If there are any words that were found, print them.
			 */
			if (words.size()>0) {
				System.out.println("Found " + words.size() + " word(s):");
				for (int i=0; i<words.size(); i++) {
					System.out.println(words.get(i));
				}
			}
			/**
			 * Inform user if no words were found
			 */
			else {
				System.out.println("No words found.");
				}
		}
		/**
		 * Catch blocks
		 */
		catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}
}