package se.kth.id1020.lab4;

import edu.princeton.cs.introcs.In;

import java.net.URL;
import java.util.Iterator;

public class Driver {

	public static void main(String[] args) {
		TwoThreeTree<String, ValuePair> tree = new TwoThreeTree<String, ValuePair>();
		
		addAZ(tree);
		//addZA(tree);
		//addText(tree);
		//getMostOccuringWordsWithIterator(tree); //Get the most occurring words, for task 4.
		//swapTree(tree); //Create a new tree so keys are the x,y valuePair and the value is the word.
		
		System.out.println("Size: "+ tree.size());
		System.out.println("Depth: " + tree.depth());
		System.out.println("Density: " + tree.density());
		System.out.println("Minimum number of elements we can add before depth changes: " + tree.howMuchMoreMin());
		System.out.println("Maximum number of elements we can add before depth changes: " + tree.howMuchMoreMax());
		
	}

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
	
	private static void swapTree(TwoThreeTree<String, ValuePair> tree)
	{
		TwoThreeTree<ValuePair, String> swappedTree = new TwoThreeTree<ValuePair, String>();
		
		//Iterate through the first tree, adding all key/values from that one to our second swapped tree.
		for (KeyValuePair<String, ValuePair> keyValues : tree.keys())
			swappedTree.put(keyValues.value, keyValues.key);
		
		int printedValues = 0;
		
		for (KeyValuePair<ValuePair, String> keyValues : swappedTree.keys())
		{
			System.out.println(String.format("Word: %s 		Occurance count: %s", keyValues.value, keyValues.key.occuranceCount));
			printedValues++;
			
			if (printedValues == 10)
				break;
		}
		
	}
	
	private static void getMostOccuringWordsWithIterator(TwoThreeTree<String, ValuePair> tree)
	{		
		int printedValues = 0;
		
		for (KeyValuePair<String, ValuePair> keyValues : tree.keys())
		{
			System.out.println(String.format("Word: %s 		Occurance count: %s", keyValues.key, keyValues.value.occuranceCount));
			printedValues++;
			
			if (printedValues == 10)
				break;
		}
			
	}
	
	private static void addAZ(TwoThreeTree<String, ValuePair> tree)
	{
		tree.put("a", new ValuePair(1, 20));
		tree.put("b", new ValuePair(2, 2440));
		tree.put("c", new ValuePair(3, 45));
		tree.put("d", new ValuePair(4, 31));
		tree.put("e", new ValuePair(5, 298));
		tree.put("f", new ValuePair(6, 7987));
		tree.put("g", new ValuePair(7, 7987));
		tree.put("h", new ValuePair(8, 7987));
		tree.put("i", new ValuePair(9, 7987));
		tree.put("j", new ValuePair(10, 7987));
		tree.put("k", new ValuePair(11, 7987));
		tree.put("l", new ValuePair(12, 7987));
		tree.put("m", new ValuePair(13, 7987));
		tree.put("n", new ValuePair(14, 7987));
		tree.put("o", new ValuePair(15, 7987));
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

//	private static void addZA(TwoThreeTree<String, ValuePair<Integer, Integer>> tree){
//		tree.put("z", new ValuePair<Integer, Integer>(33, 7987));
//		tree.put("y", new ValuePair<Integer, Integer>(30, 7987));
//		tree.put("x", new ValuePair<Integer, Integer>(31, 7987));
//		tree.put("w", new ValuePair<Integer, Integer>(32, 7987));
//		tree.put("v", new ValuePair<Integer, Integer>(33, 7987));
//		tree.put("u", new ValuePair<Integer, Integer>(30, 7987));
//		tree.put("t", new ValuePair<Integer, Integer>(31, 7987));
//		tree.put("s", new ValuePair<Integer, Integer>(32, 7987));
//		tree.put("r", new ValuePair<Integer, Integer>(33, 7987));
//		tree.put("q", new ValuePair<Integer, Integer>(30, 7987));
//		tree.put("p", new ValuePair<Integer, Integer>(31, 7987));
//		tree.put("o", new ValuePair<Integer, Integer>(32, 7987));
//		tree.put("n", new ValuePair<Integer, Integer>(33, 7987));
//		tree.put("m", new ValuePair<Integer, Integer>(30, 7987));
//		tree.put("l", new ValuePair<Integer, Integer>(31, 7987));
//		tree.put("k", new ValuePair<Integer, Integer>(32, 7987));
//		tree.put("j", new ValuePair<Integer, Integer>(33, 7987));
//		tree.put("i", new ValuePair<Integer, Integer>(33, 7987));
//		tree.put("h", new ValuePair<Integer, Integer>(30, 7987));
//		tree.put("g", new ValuePair<Integer, Integer>(31, 7987));
//		tree.put("f", new ValuePair<Integer, Integer>(32, 7987));
//		tree.put("e", new ValuePair<Integer, Integer>(33, 7987));
//		tree.put("d", new ValuePair<Integer, Integer>(30, 7987));
//		tree.put("c", new ValuePair<Integer, Integer>(31, 7987));
//		tree.put("b", new ValuePair<Integer, Integer>(32, 7987));
//		tree.put("a", new ValuePair<Integer, Integer>(33, 7987));
//	}
	
	private static void printAZ (){
//		System.out.print(tree.get("a").x + ", ");
//		System.out.print(tree.get("b").x + ", ");
//		System.out.print(tree.get("c").x + ", ");
//		System.out.print(tree.get("d").x + ", ");
//		System.out.print(tree.get("e").x + ", ");
//		System.out.print(tree.get("f").x + ", ");
//		System.out.print(tree.get("g").x + ", ");
//		System.out.print(tree.get("h").x + ", ");
//		System.out.print(tree.get("i").x + ", ");
//		System.out.print(tree.get("j").x + ", ");
//		System.out.print(tree.get("k").x + ", ");
//		System.out.print(tree.get("l").x + ", ");
//		System.out.print(tree.get("m").x + ", ");
//		System.out.print(tree.get("n").x + ", ");
//		System.out.print(tree.get("o").x + ", ");
//		System.out.print(tree.get("p").x + ", ");
//		System.out.print(tree.get("q").x + ", ");
//		System.out.print(tree.get("r").x + ", ");
//		System.out.print(tree.get("s").x + ", ");
//		System.out.print(tree.get("t").x + ", ");
//		System.out.print(tree.get("u").x + ", ");
//		System.out.print(tree.get("v").x + ", ");
//		System.out.print(tree.get("w").x + ", ");
//		System.out.print(tree.get("x").x + ", ");
//		System.out.print(tree.get("y").x + ", ");
//		System.out.print(tree.get("z").x);
	}
}