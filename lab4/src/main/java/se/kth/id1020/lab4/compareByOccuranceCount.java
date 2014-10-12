package se.kth.id1020.lab4;

import java.util.Comparator;

public class compareByOccuranceCount implements Comparator<KeyValuePair<K extends Comparable<K>, ValuePair>> {

	public int compare(KeyValuePair<K, ValuePair> arg0, KeyValuePair<K, ValuePair> arg1) {
		return arg0.value.occuranceCount.compareTo(arg1.value.occuranceCount);
	}
	
}
