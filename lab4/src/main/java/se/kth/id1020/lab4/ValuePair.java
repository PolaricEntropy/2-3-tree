package se.kth.id1020.lab4;

public class ValuePair <V1, V2 extends Comparable<V2>> implements Comparable<ValuePair<V1, V2>> {
	
	public V1 occuranceNumber;
	public V2 occuranceCount;
	
	public ValuePair(V1 value1, V2 value2)
	{
		this.occuranceNumber = value1;
		this.occuranceCount = value2;
	}

	public int compareTo(ValuePair<V1, V2> o)
	{
		//Compare occurrence count so we can sort the tree by most occurring word.
		return this.occuranceCount.compareTo(o.occuranceCount);
	}

	
}
