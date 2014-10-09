package se.kth.id1020.lab4;

import edu.princeton.cs.introcs.In;
import java.net.URL;

public class Driver {

	public static void main(String[] args) {
		twothreetree<String, Integer, Integer> tree = new twothreetree<String, Integer, Integer>();
		
		tree.put("test", new ValuePair<Integer, Integer>(1, 20));
		System.out.print(tree.get("test"));
		
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
