package se.kth.id1020.lab4;

import java.util.Comparator;

public class compareByOccuranceCount2 <K extends Comparable<K>> implements Comparator<TwoThreeTree<ValuePair, K>.KeyValuePair> {

	public int compare(TwoThreeTree<ValuePair, K>.KeyValuePair arg0, TwoThreeTree<ValuePair, K>.KeyValuePair arg1) {
		return arg1.key.occuranceCount.compareTo(arg0.key.occuranceCount);
	}
	
}
