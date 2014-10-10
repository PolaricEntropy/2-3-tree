package se.kth.id1020.lab4;

import edu.princeton.cs.introcs.In;

import java.net.URL;

public class Driver {

	public static void main(String[] args) {
		twothreetree<String, Integer, Integer> tree = new twothreetree<String, Integer, Integer>();
		
		tree.put("a", new ValuePair<Integer, Integer>(1, 20));
		tree.put("b", new ValuePair<Integer, Integer>(2, 2440));
		tree.put("c", new ValuePair<Integer, Integer>(3, 45));
		tree.put("d", new ValuePair<Integer, Integer>(4, 31));
		tree.put("e", new ValuePair<Integer, Integer>(5, 298));
		tree.put("f", new ValuePair<Integer, Integer>(6, 7987));
		tree.put("g", new ValuePair<Integer, Integer>(7, 7987));
		tree.put("h", new ValuePair<Integer, Integer>(8, 7987));
		tree.put("i", new ValuePair<Integer, Integer>(9, 7987));
		tree.put("j", new ValuePair<Integer, Integer>(10, 7987));
		tree.put("k", new ValuePair<Integer, Integer>(11, 7987));
//		tree.put("l", new ValuePair<Integer, Integer>(12, 7987));
//		tree.put("m", new ValuePair<Integer, Integer>(13, 7987));
//		tree.put("n", new ValuePair<Integer, Integer>(14, 7987));
//		tree.put("o", new ValuePair<Integer, Integer>(15, 7987));
//		tree.put("p", new ValuePair<Integer, Integer>(16, 7987));
//		tree.put("q", new ValuePair<Integer, Integer>(17, 7987));
//		tree.put("r", new ValuePair<Integer, Integer>(18, 7987));
//		tree.put("s", new ValuePair<Integer, Integer>(19, 7987));
//		tree.put("t", new ValuePair<Integer, Integer>(20, 7987));
//		tree.put("u", new ValuePair<Integer, Integer>(21, 7987));
//		tree.put("v", new ValuePair<Integer, Integer>(22, 7987));
//		tree.put("w", new ValuePair<Integer, Integer>(23, 7987));
//		tree.put("x", new ValuePair<Integer, Integer>(24, 7987));
//		tree.put("y", new ValuePair<Integer, Integer>(25, 7987));
//		tree.put("z", new ValuePair<Integer, Integer>(26, 7987));
//		tree.put("å", new ValuePair<Integer, Integer>(27, 7987));
//		tree.put("ä", new ValuePair<Integer, Integer>(28, 7987));
//		tree.put("ö", new ValuePair<Integer, Integer>(29, 7987));
		
//		tree.put("ö", new ValuePair<Integer, Integer>(30, 7987));
//		tree.put("ä", new ValuePair<Integer, Integer>(31, 7987));
//		tree.put("å", new ValuePair<Integer, Integer>(32, 7987));
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
//		System.out.print(tree.get("z").x + ", ");
//		System.out.print(tree.get("å").x + ", ");
//		System.out.print(tree.get("ä").x + ", ");
//		System.out.print(tree.get("ö").x + ", ");		
		
//		URL url = Driver.class.getResource("kap1.txt");
//		In input = new In(url);
//		while (!input.isEmpty())
//		{
//			String line = input.readLine().trim();
//			String[] words = line.split(" ");
//			int UniqueWords = 0;
//			
//			for (String word : words)
//			{
//				String newWord = word.trim();
//				
//				ValuePair <Integer, Integer> treeValue = tree.get(newWord);
//				
//				if (treeValue != null)
//				{
//					tree.put(newWord, new ValuePair<Integer, Integer>(treeValue.x ,treeValue.y++));
//				}
//				else
//				{
//					//We've found a new word.
//					UniqueWords++;
//					tree.put(newWord, new ValuePair<Integer, Integer>(UniqueWords,0));
//				}
//				
//				tree.put(newWord, new ValuePair<Integer, Integer>(0,0));
//				// Run the program and scan the words
//				// First strip away extra characters from each word
//				// Then add the word into the search tree
//				
//				System.out.println(newWord);
//			}
//		}
		
		System.out.println("Size: "+ tree.size());
		System.out.println("Depth: " + tree.depth());
		
	}

}
