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
			
			
			putKeyValueInNode(foundNode, rightMostValue);
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
	
	private void putKeyValueInNode(Node currentNode, KeyValuePair<K, Value1, Value2> rightMostKey)
	{
	
		Node parent = currentNode.parent;
		
		if (parent == null)
		{
			Node newRoot = new Node(currentNode.keyvalues2, 3);
			newRoot.left = new Node (currentNode.keyvalues1, 1);
			newRoot.middle = new Node (rightMostKey, 1);
			
			root = newRoot;
			return;
		}
		
		//If the parent is a TwoNode, we need to add the middle value in our current node somewhere in the parent node.
		if(parent.keyvalues2 == null)
		{
			//If the current nodes middle key is less than parents first (and only) key.
			if (currentNode.keyvalues2.key.compareTo(parent.keyvalues1.key) == -1)
			{	
				parent.keyvalues2 = parent.keyvalues1;
				parent.keyvalues1 = currentNode.keyvalues2;
				
				Node newLeft = new Node (currentNode.keyvalues1, 1);
				newLeft.parent = parent;
				
				Node newMiddle = new Node (rightMostKey, 1);
				newMiddle.parent = parent;
				
				
				parent.right = parent.middle;
				parent.middle = newMiddle;
				parent.left = newLeft;
			}
			//If the current nodes middle key is greater than parents first (and only) key.
			else if(currentNode.keyvalues2.key.compareTo(parent.keyvalues1.key) == 1)
			{
				parent.keyvalues2 = currentNode.keyvalues2;
				
				Node newMiddle = new Node (currentNode.keyvalues1, 1);
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
			Node rightMostNode = null;
			
			
			//Middle element in child is less than first key in parent.
			if (currentNode.keyvalues2.key.compareTo(parent.keyvalues1.key) == -1)
			{
				rightMostKeyParent = parent.keyvalues2;
				parent.keyvalues2 = parent.keyvalues1;
				parent.keyvalues1 = currentNode.keyvalues2;
				
				
				Node newLeft = new Node(currentNode.keyvalues1, 1);
				Node newMiddle = new Node (rightMostKey, 1);
				
				rightMostNode = parent.right;
				parent.right = parent.middle;
				
				parent.middle = newMiddle;
				parent.left = newLeft;
			}
			else if (currentNode.keyvalues2.key.compareTo(parent.keyvalues1.key) == 1 && currentNode.keyvalues2.key.compareTo(parent.keyvalues2.key) == -1)
			{
				rightMostKeyParent = parent.keyvalues2;
				parent.keyvalues2 = currentNode.keyvalues2;
				
				Node newMiddle = new Node(currentNode.keyvalues1, 1);
				Node newRight = new Node (rightMostKey, 1);
				
				rightMostNode = parent.right;
				parent.right = newRight;
				parent.middle = newMiddle;
			}
			else if (currentNode.keyvalues2.key.compareTo(parent.keyvalues2.key) == 1) //Middle element in child is larger than second key in parent.
			{
				rightMostKeyParent = parent.keyvalues2;
				
				rightMostNode = new Node(rightMostKey, 1);
				Node newRight = new Node(currentNode.keyvalues1, 1);
				
				parent.right = newRight;
			}
			
			
			putNodeInNode(currentNode.parent, rightMostNode);
			
		}
	}
		

	private void putNodeInNode(Node currentNode, Node rightMostNode)
	{
	
		Node parent = currentNode.parent;
		
		//If we have managed to climb to the top of our mountain.
//			if (parent == root)
//			{
//				if (key < parent.keyvalues1)
//				{
//					root = new TwoNode(parent.keyvalues1);
//					root.left = new TwoNode (key, values);
//					root.right = new TwoNode(parent.keyvalues2);
//				}
//				else if (key > parent.keyvalues1 && key < parent.keyvalues2)
//				{
//					root = new TwoNode(key, values);
//				}
//				else if (key > parent.keyvalues2)
//				{
//					root = new TwoNode(parent.keyvalues2);
//				}
//				
//				return;
//			}
		
		//TODO: Also check if we have a two node, then we can insert our node there.
		
		
		//If the parent is a TwoNode, we need to add the middle value in our current node somewhere in the parent node.
		if(parent.keyvalues2 == null)
		{
			//If the current nodes middle key is less than parents first (and only) key.
			if (currentNode.keyvalues2.key.compareTo(parent.keyvalues1.key) == -1)
			{	
				parent.keyvalues2 = parent.keyvalues1;
				parent.keyvalues1 = currentNode.keyvalues2;
				
				Node newLeft = new Node (currentNode.keyvalues1, 1);
				newLeft.parent = parent;
				
				Node newMiddle = new Node (rightMostKey, 1);
				newMiddle.parent = parent;
				
				
				parent.right = parent.middle;
				parent.middle = newMiddle;
				parent.left = newLeft;
			}
			//If the current nodes middle key is greater than parents first (and only) key.
			else if(currentNode.keyvalues2.key.compareTo(parent.keyvalues1.key) == 1)
			{
				parent.keyvalues2 = currentNode.keyvalues2;
				
				Node newMiddle = new Node (currentNode.keyvalues1, 1);
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
			Node rightMostNode;
			
			
			//Middle element in child is less than first key in parent.
			if (currentNode.keyvalues2.key.compareTo(parent.keyvalues1.key) == -1)
			{
				rightMostKeyParent = parent.keyvalues2;
				parent.keyvalues2 = parent.keyvalues1;
				parent.keyvalues1 = currentNode.keyvalues2;
				
				
				Node newLeft = new Node(currentNode.keyvalues1, 1);
				Node newMiddle = new Node (rightMostKey, 1);
				
				rightMostNode = parent.right;
				parent.right = parent.middle;
				
				parent.middle = newMiddle;
				parent.left = newLeft;
			}
			else if (currentNode.keyvalues2.key.compareTo(parent.keyvalues1.key) == 1 && currentNode.keyvalues2.key.compareTo(parent.keyvalues2.key) == -1)
			{
				rightMostKeyParent = parent.keyvalues2;
				parent.keyvalues2 = currentNode.keyvalues2;
				
				Node newMiddle = new Node(currentNode.keyvalues1, 1);
				Node newRight = new Node (rightMostKey, 1);
				
				rightMostNode = parent.right;
				parent.right = newRight;
				parent.middle = newMiddle;
			}
			else if (currentNode.keyvalues2.key.compareTo(parent.keyvalues2.key) == 1) //Middle element in child is larger than second key in parent.
			{
				rightMostKeyParent = parent.keyvalues2;
				
				rightMostNode = new Node(rightMostKey, 1);
				Node newRight = new Node(currentNode.keyvalues1, 1);
				
				parent.right = newRight;
			}
			
			
			putKeyValueInNode(currentNode.parent, rightMostNode);
			
		}

		
	}
	
	private Node Convert4to2(Node inNode, KeyValuePair rightMostKey)
	{
		
	}
	
	
}
