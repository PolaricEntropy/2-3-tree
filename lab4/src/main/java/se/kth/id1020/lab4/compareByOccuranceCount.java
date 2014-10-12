package se.kth.id1020.lab4;

import java.util.Comparator;

public class compareByOccuranceCount <K extends Comparable<K>> implements Comparator<TwoThreeTree<K, ValuePair>.KeyValuePair> {

	public int compare(TwoThreeTree<K, ValuePair>.KeyValuePair arg0, TwoThreeTree<K, ValuePair>.KeyValuePair arg1) {
		return arg1.value.occuranceCount.compareTo(arg0.value.occuranceCount);
	}
	
}
