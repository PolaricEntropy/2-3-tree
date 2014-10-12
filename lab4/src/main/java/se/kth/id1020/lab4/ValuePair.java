package se.kth.id1020.lab4;

public class ValuePair implements Comparable<ValuePair> {
	
	public Integer occuranceNumber;
	public Integer occuranceCount;
	
	public ValuePair(Integer value1, Integer value2)
	{
		this.occuranceNumber = value1;
		this.occuranceCount = value2;
	}

	/**
	 * Used to compare keys when ValuePair is used as a key.
	 */
	public int compareTo(ValuePair o)
	{
		return this.occuranceNumber.compareTo(o.occuranceNumber);
	}

	/**
	 * Used to compare keys when ValuePair is used as a key.
	 * @param obj ValuePair to compare against.
	 * @return
	 */
	public boolean equals(Object o)
	{
		ValuePair obj = (ValuePair) o;
		
		return this.occuranceNumber.equals(obj.occuranceNumber);
	}
	
}
