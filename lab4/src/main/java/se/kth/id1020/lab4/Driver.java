package se.kth.id1020.lab4;

import edu.princeton.cs.introcs.In;
import java.net.URL;

public class Driver {

	public static void main(String[] args) {
		twothreetree<String, Integer> tree = new twothreetree<String, Integer>();
		
		URL url = Driver.class.getResource("kap1.txt");
		In input = new In(url);
		while (!input.isEmpty()) {
			String line = input.readLine().trim();
			String[] words = line.split(" ");
			for (String word : words) {
				System.out.println(word);
				// Run the program and scan the words
				// First strip away extra characters from each word
				// Then add the word into the search tree
			}
		}
	}

}
