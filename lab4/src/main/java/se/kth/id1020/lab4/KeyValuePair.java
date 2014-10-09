package se.kth.id1020.lab4;

public class KeyValuePair<K extends Comparable<K>, Value1, Value2> {

	public K key;
	public ValuePair<Value1, Value2> value;
	
	public KeyValuePair(K key, Value1 val1, Value2 val2)
	{
		this.key = key;
		this.value = new ValuePair<Value1, Value2>(val1, val2);
	}
	
	public KeyValuePair(K key, ValuePair<Value1, Value2> values)
	{
		this.key = key;
		this.value = values;
	}
}
