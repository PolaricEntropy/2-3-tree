package se.kth.id1020.lab4;

public class KeyValuePair<K extends Comparable<K>, V> {

	public K key;
	public V value;
	
	public KeyValuePair(K key, V value)
	{
		this.key = key;
		this.value = value;
	}
	
}
