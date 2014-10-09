package se.kth.id1020.lab4;

import edu.princeton.cs.introcs.In;

import java.net.URL;

public class Driver {

	public static void main(String[] args) {
		twothreetree<String, Integer, Integer> tree = new twothreetree<String, Integer, Integer>();
		
		tree.put("a", new ValuePair<Integer, Integer>(1, 20));
		tree.put("b", new ValuePair<Integer, Integer>(1, 2440));
		tree.put("c", new ValuePair<Integer, Integer>(1, 45));
		tree.put("d", new ValuePair<Integer, Integer>(1, 31));
		tree.put("e", new ValuePair<Integer, Integer>(1, 298));
		tree.put("f", new ValuePair<Integer, Integer>(1, 7987));
		tree.put("g", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("h", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("i", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("j", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("k", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("l", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("m", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("n", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("o", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("p", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("q", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("r", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("s", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("t", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("u", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("v", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("w", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("x", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("y", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("z", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("å", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("ä", new ValuePair<Integer, Integer>(1, 7987));
//		tree.put("ö", new ValuePair<Integer, Integer>(1, 7987));
		
		
//		System.out.print(tree.get("test").y);
//		System.out.print(tree.get("test2").y);
//		System.out.print(tree.get("test3").y);
//		System.out.print(tree.get("test4").y);
//		System.out.print(tree.get("test5").y);
		
		
//		URL url = Driver.class.getResource("kap1.txt");
//		In input = new In(url);
//		while (!input.isEmpty()) {
//			String line = input.readLine().trim();
//			String[] words = line.split(" ");
//			for (String word : words) {
//				System.out.println(word);
//				
//				
//				tree.put(word.trim(), value);
//				// Run the program and scan the words
//				// First strip away extra characters from each word
//				// Then add the word into the search tree
//			}
//		}
	}

}
