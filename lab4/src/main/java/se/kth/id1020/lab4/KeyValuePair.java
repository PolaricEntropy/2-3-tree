package se.kth.id1020.lab4;


//TODO: Make this an inner class to the tree.
public class KeyValuePair<K extends Comparable<K>, V> {

	public K key;
	public V value;
	
	public KeyValuePair(K key, V value)
	{
		this.key = key;
		this.value = value;
	}
	
	public boolean equals(Object o)
	{
		//Assume we are comparing to another KeyValuePair with the same K, V.
		KeyValuePair<K, V> obj = (KeyValuePair<K, V>) o;
		
		//KeyValuePairs are equal if keys are equal.
		return this.key.equals(obj.key);
	}
	
}
