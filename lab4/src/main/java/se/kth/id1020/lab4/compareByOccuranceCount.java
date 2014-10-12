package se.kth.id1020.lab4;

import java.util.Comparator;

public class compareByOccuranceCount <K extends Comparable<K>> implements Comparator<KeyValuePair<K, ValuePair>> {

	public int compare(KeyValuePair<K, ValuePair> arg0, KeyValuePair<K, ValuePair> arg1) {
		return arg1.value.occuranceCount.compareTo(arg0.value.occuranceCount);
	}
	
}
