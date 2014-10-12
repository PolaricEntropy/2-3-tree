package se.kth.id1020.lab4;

import java.util.Comparator;

public class compareByOccuranceCount2 <K extends Comparable<K>> implements Comparator<KeyValuePair<ValuePair, K>> {

	public int compare(KeyValuePair<ValuePair, K> arg0, KeyValuePair<ValuePair, K> arg1) {
		return arg1.key.occuranceCount.compareTo(arg0.key.occuranceCount);
	}
	
}
