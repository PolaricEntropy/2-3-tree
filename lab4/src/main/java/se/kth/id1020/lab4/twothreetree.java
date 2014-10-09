package se.kth.id1020.lab4;

public class twothreetree<K extends Comparable<K>, Value1, Value2> {

	private Node root;
	
	public  class Node
	{
		protected KeyValuePair<K, Value1, Value2> keyvalues1;
		protected KeyValuePair<K, Value1, Value2> keyvalues2;
		protected Node parent, left, middle, right;
		protected int N;
		
		public Node(KeyValuePair<K, Value1, Value2> keyValues1, int N)
		{
			this.keyvalues1 = keyValues1;
			this.N = N;
		}
		
		public Node(KeyValuePair<K, Value1, Value2> keyValues1, KeyValuePair<K, Value1, Value2> keyValues2, int N)
		{
			this.keyvalues1 = keyValues1;
			this.keyvalues2 = keyValues2;
			this.N = N;
		}	
	}
	
	public ValuePair<Value1, Value2> get(K key)
	{
		throw new UnsupportedOperationException();
	}
	
	public void put(K key, ValuePair<Value1, Value2> value)
	{
		//If we don't have a root node we don't have a tree.
		//Create a TwoNode and store our key/value in it, done.
		if (root == null)
		{
			//Size of 1 since we just have one node.
			root = new Node(new KeyValuePair<K, Value1, Value2>(key, value), 1);
			return;
		}
		
		//Find the node we should add stuff to.
		Node foundNode = getNode(key);
		
		//TwoNode.
		if (foundNode.keyvalues2 == null)
		{
			switch(key.compareTo(foundNode.keyvalues1.key)){
			case -1: //Key we are adding is lesser than the node key.
			
				//Insert new stuff at the left, thus we need to move that data.
				foundNode.keyvalues2 = foundNode.keyvalues1;
				foundNode.keyvalues1 = new KeyValuePair<K, Value1, Value2>(key, value);
				break;
			case 0:
				//Just replace the value of the key.
				foundNode.keyvalues1.value = value;
				break;
			case 1:
				foundNode.keyvalues2 = new KeyValuePair<K, Value1, Value2>(key, value);
				break;
			}
			
			return;
		}
		else //ThreeNode.
		{		
			KeyValuePair<K, Value1, Value2> rightMostValue = null;

			//If we have any key with the key we are trying to add, then just replace those values.  
			if (foundNode.keyvalues1.key.equals(key))
			{
				foundNode.keyvalues1.value = value;
				return;
			}
			if (foundNode.keyvalues2.key.equals(key))
			{
				foundNode.keyvalues2.value = value;
				return;
			}
			
			//This is for sorting the temporary 4-node.
			
			//Key is less than left key, insert new value to the left. 
			if (key.compareTo(foundNode.keyvalues1.key) == -1)
			{
				rightMostValue = foundNode.keyvalues2;
				foundNode.keyvalues2 = foundNode.keyvalues1;
				foundNode.keyvalues1 = new KeyValuePair<K, Value1, Value2>(key, value); 
			}
			//Key is larger then the left key, and smaller then the right key, thus it should go in the middle.
			if (key.compareTo(foundNode.keyvalues1.key) == 1 && key.compareTo(foundNode.keyvalues2.key) == -1)
			{
				rightMostValue = foundNode.keyvalues2;
				foundNode.keyvalues2 = new KeyValuePair<K, Value1, Value2>(key, value);
			}
			//Key is more then the right key, insert new value in the right.
			if (key.compareTo(foundNode.keyvalues2.key) == 1)
			{
				rightMostValue = new KeyValuePair<K, Value1, Value2>(key, value);
			}
			
			
			putInNode(foundNode, rightMostValue);
		}
		
	}
	
	public int size()
	{
		throw new UnsupportedOperationException();
	}
	
	public Iterable<K> keys()
	{
		throw new UnsupportedOperationException();
	}
	
	public Iterable<K> keys(K lo, K hi)
	{
		throw new UnsupportedOperationException();
	}
	
	public int depth()
	{
		throw new UnsupportedOperationException();
	}
	
	public int howMuchMore()
	{
		throw new UnsupportedOperationException();
	}
	
	public float density()
	{
		throw new UnsupportedOperationException();
	}
	
	private Node getNode(K key)
	{
		throw new UnsupportedOperationException();
	}
	
	private void putInNode(Node foundNode, KeyValuePair<K, Value1, Value2> rightMostKey)
	{
	
		Node parent = foundNode.parent;
		
		//If we have managed to climb to the top of our mountain.
//		if (parent == root)
//		{
//			if (key < parent.keyvalues1)
//			{
//				root = new TwoNode(parent.keyvalues1);
//				root.left = new TwoNode (key, values);
//				root.right = new TwoNode(parent.keyvalues2);
//			}
//			else if (key > parent.keyvalues1 && key < parent.keyvalues2)
//			{
//				root = new TwoNode(key, values);
//			}
//			else if (key > parent.keyvalues2)
//			{
//				root = new TwoNode(parent.keyvalues2);
//			}
//			
//			return;
//		}
		
		
		//TwoNode.
		if(parent.keyvalues2 == null)
		{
			//Less than...
			if (foundNode.keyvalues2.key.compareTo(parent.keyvalues1.key) == -1)
			{
				parent.keyvalues2 = parent.keyvalues1;
				parent.keyvalues1 = foundNode.keyvalues2;
				
				Node newLeft = new Node (foundNode.keyvalues1, 1);
				newLeft.parent = parent;
				
				Node newMiddle = new Node (rightMostKey, 1);
				newMiddle.parent = parent;
				
				
				parent.right = parent.middle;
				parent.middle = newMiddle;
				parent.left = newLeft;
			} //Greater than.
			else if(foundNode.keyvalues2.key.compareTo(parent.keyvalues1.key) == 1)
			{
				parent.keyvalues2 = foundNode.keyvalues2;
				
				Node newMiddle = new Node (foundNode.keyvalues1, 1);
				newMiddle.parent = parent;
				
				Node newRight = new Node (rightMostKey, 1);
				newRight.parent = parent;
				
				parent.middle = newMiddle;
				parent.right = newRight;
			}
		}
		else //ThreeNode.
		{
			KeyValuePair<K, Value1, Value2> rightMostKeyParent = null; 
			
			
			switch(foundNode.keyvalues2.key.compareTo(parent.keyvalues1.key)){
			case -1: //Middle element in child is less than first in parent.
			
				rightMostKeyParent = parent.keyvalues2;
				parent.keyvalues2 = parent.keyvalues1;
				parent.keyvalues1 = foundNode.keyvalues2;
				
				Node newLeft = new Node(foundNode.keyvalues1, 1);
				
				parent.left = newLeft;
				break;
			case 1:
				break;
			}
		
			
		}
		
		
	}
	
	
}
