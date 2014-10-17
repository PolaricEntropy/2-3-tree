package se.kth.id1020.lab4;

import edu.princeton.cs.introcs.In;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;

public class Driver {

	public static void main(String[] args) {
		TwoThreeTree<String, ValuePair> tree = new TwoThreeTree<String, ValuePair>();
		
		//addAZ(tree);
		addText(tree);
		getMostOccuringWordsWithIterator(tree); //Get the most occurring words, for task 4.
		swapTree(tree); //Create a new tree so keys are the valuePair and the value is the word.
		
		System.out.println("Size: "+ tree.size());
		System.out.println("Depth: " + tree.depth());
		System.out.println("Density: " + tree.density());
		System.out.println("Maximum number of elements we can add before depth changes: " + tree.howMuchMore());	
	}

	/**
	 * Clean up the word before we add it to the tree.
	 * @param in The word to clean.
	 * @return The cleaned word.
	 */
	private static String CleanWord(String in)
	{
		//Remove .,:;". 
		in = in.replaceAll("([().,:;\"]+)", "");
        
        //All words should be lowercase.
        in = in.toLowerCase();
        
        //Remove whitespace before and after word.
        in = in.trim();
        
		return in;
	}
	
	/**
	 * Add the text to the tree.
	 * @param tree The tree to add text to.
	 */
	private static void addText(TwoThreeTree<String, ValuePair> tree)
	{
		URL url = Driver.class.getResource("kap1.txt");
		In input = new In(url);
		while (!input.isEmpty())
		{
			String line = input.readLine().trim();
			String[] words = line.split(" ");
			int UniqueWords = 1;
			
			for (String word : words)
			{
				String newWord = CleanWord(word);
			
				//Skip adding spaces.
				if (newWord.equals(""))
					continue;
				
				ValuePair treeValue = tree.get(newWord);
				
				if (treeValue != null)
					tree.put(newWord, new ValuePair(treeValue.occuranceNumber, ++treeValue.occuranceCount));
				else
				{
					//We've found a new word.
					UniqueWords++;
					tree.put(newWord, new ValuePair(UniqueWords,1));
				}
				
				System.out.println(newWord);
			}
		}
	}
	
	/**
	 * Creates a new tree that has key, values swapped. Also sorts it by occurrence count and prints the 10 most occurring words.
	 * @param tree The initial tree to swap.
	 */
	private static void swapTree(TwoThreeTree<String, ValuePair> tree)
	{
		TwoThreeTree<ValuePair, String> swappedTree = new TwoThreeTree<ValuePair, String>();
		int printedValues = 0;
		
		//Iterate through the first tree, adding all key/values from that one to our second swapped tree.
		for (TwoThreeTree<String, ValuePair>.KeyValuePair keyValues : tree.keys())
			swappedTree.put(keyValues.value, keyValues.key);
		
		//Get all the keys in our swapped tree
		LinkedList<TwoThreeTree<ValuePair, String>.KeyValuePair> keyValuesinTree = (LinkedList<TwoThreeTree<ValuePair, String>.KeyValuePair>)swappedTree.keys();
		
		//Sort by occurrence count. 
		Collections.sort(keyValuesinTree, new compareByOccuranceCountSwapped());
		
		//Print the first 10 values, the 10 words that's most occuring.
		for (TwoThreeTree<ValuePair, String>.KeyValuePair keyValues : keyValuesinTree)
		{
			System.out.println(String.format("Word: %s 		Occurance count: %s", keyValues.value, keyValues.key.occuranceCount));
			printedValues++;
			
			if (printedValues == 10)
				break;
		}
		
	}
	
	/**
	 * Print the 10 most occurring words.
	 * @param tree
	 */
	private static void getMostOccuringWordsWithIterator(TwoThreeTree<String, ValuePair> tree)
	{		
		int printedValues = 0;
		
		LinkedList<TwoThreeTree<String, ValuePair>.KeyValuePair> keyValuesinTree = (LinkedList<TwoThreeTree<String, ValuePair>.KeyValuePair>)tree.keys();
		
		//Sort by occurrence count. 
		Collections.sort(keyValuesinTree, new compareByOccuranceCount());
		
		for (TwoThreeTree<String, ValuePair>.KeyValuePair keyValues : keyValuesinTree)
		{
			System.out.println(String.format("Word: %s 		Occurance count: %s", keyValues.key, keyValues.value.occuranceCount));
			printedValues++;
			
			if (printedValues == 10)
				break;
		}
	}
	
	/**
	 * Test function for adding a to z.
	 * @param tree The tree to add to.
	 */
	private static void addAZ(TwoThreeTree<String, ValuePair> tree)
	{
		tree.put("a", new ValuePair(1, 20));
		tree.put("b", new ValuePair(2, 2440));
		tree.put("c", new ValuePair(3, 45));
		tree.put("d", new ValuePair(4, 31));
		tree.put("e", new ValuePair(5, 298));
		tree.put("f", new ValuePair(6, 7987));
		tree.put("g", new ValuePair(7, 7987));
//		tree.put("h", new ValuePair(8, 7987));
//		tree.put("i", new ValuePair(9, 7987));
//		tree.put("j", new ValuePair(10, 7987));
//		tree.put("k", new ValuePair(11, 7987));
//		tree.put("l", new ValuePair(12, 7987));
//		tree.put("m", new ValuePair(13, 7987));
//		tree.put("n", new ValuePair(14, 7987));
//		tree.put("o", new ValuePair(15, 7987));
//		tree.put("p", new ValuePair(16, 7987));
//		tree.put("q", new ValuePair(17, 7987));
//		tree.put("r", new ValuePair(18, 7987));
//		tree.put("s", new ValuePair(19, 7987));
//		tree.put("t", new ValuePair(20, 7987));
//		tree.put("u", new ValuePair(21, 7987));
//		tree.put("v", new ValuePair(22, 7987));
//		tree.put("w", new ValuePair(23, 7987));
//		tree.put("x", new ValuePair(24, 7987));
//		tree.put("y", new ValuePair(25, 7987));
//		tree.put("z", new ValuePair(26, 7987));
	}

	/**
	 * Test function for adding z to a.
	 * @param tree The tree to add to.
	 */
	private static void addZA(TwoThreeTree<String, ValuePair> tree){
		tree.put("z", new ValuePair(1, 7987));
		tree.put("y", new ValuePair(2, 7987));
		tree.put("x", new ValuePair(3, 7987));
		tree.put("w", new ValuePair(4, 7987));
		tree.put("v", new ValuePair(5, 7987));
		tree.put("u", new ValuePair(6, 7987));
		tree.put("t", new ValuePair(7, 7987));
		tree.put("s", new ValuePair(8, 7987));
		tree.put("r", new ValuePair(9, 7987));
		tree.put("q", new ValuePair(10, 7987));
		tree.put("p", new ValuePair(11, 7987));
		tree.put("o", new ValuePair(12, 7987));
		tree.put("n", new ValuePair(13, 7987));
		tree.put("m", new ValuePair(14, 7987));
		tree.put("l", new ValuePair(15, 7987));
		tree.put("k", new ValuePair(16, 7987));
		tree.put("j", new ValuePair(17, 7987));
		tree.put("i", new ValuePair(18, 7987));
		tree.put("h", new ValuePair(19, 7987));
		tree.put("g", new ValuePair(20, 7987));
		tree.put("f", new ValuePair(21, 7987));
		tree.put("e", new ValuePair(22, 7987));
		tree.put("d", new ValuePair(23, 7987));
		tree.put("c", new ValuePair(24, 7987));
		tree.put("b", new ValuePair(25, 7987));
		tree.put("a", new ValuePair(26, 7987));
	}
	
	/**
	 * Test function for printing a to z.
	 * @param tree The tree where the values are stored.
	 */
	private static void printAZ (TwoThreeTree<String, ValuePair> tree){
		System.out.print(tree.get("a").occuranceNumber + ", ");
		System.out.print(tree.get("b").occuranceNumber + ", ");
		System.out.print(tree.get("c").occuranceNumber + ", ");
		System.out.print(tree.get("d").occuranceNumber + ", ");
		System.out.print(tree.get("e").occuranceNumber + ", ");
		System.out.print(tree.get("f").occuranceNumber + ", ");
		System.out.print(tree.get("g").occuranceNumber + ", ");
		System.out.print(tree.get("h").occuranceNumber + ", ");
		System.out.print(tree.get("i").occuranceNumber + ", ");
		System.out.print(tree.get("j").occuranceNumber + ", ");
		System.out.print(tree.get("k").occuranceNumber + ", ");
		System.out.print(tree.get("l").occuranceNumber + ", ");
		System.out.print(tree.get("m").occuranceNumber + ", ");
		System.out.print(tree.get("n").occuranceNumber + ", ");
		System.out.print(tree.get("o").occuranceNumber + ", ");
		System.out.print(tree.get("p").occuranceNumber + ", ");
		System.out.print(tree.get("q").occuranceNumber + ", ");
		System.out.print(tree.get("r").occuranceNumber + ", ");
		System.out.print(tree.get("s").occuranceNumber + ", ");
		System.out.print(tree.get("t").occuranceNumber + ", ");
		System.out.print(tree.get("u").occuranceNumber + ", ");
		System.out.print(tree.get("v").occuranceNumber + ", ");
		System.out.print(tree.get("w").occuranceNumber + ", ");
		System.out.print(tree.get("x").occuranceNumber + ", ");
		System.out.print(tree.get("y").occuranceNumber + ", ");
		System.out.print(tree.get("z").occuranceNumber);
	}
}