package se.kth.id1020.lab4;

public class twothreetree<K extends Comparable<K>, Value1, Value2> {

	private Node root;
	
	public abstract class Node
	{
		protected KeyValuePair<K, Value1, Value2> keyvalues1;
		protected Node parent, left, right;
		protected int N;	
		
		public void Relink(Node oldNode)
		{
			if (parent instanceof twothreetree.TwoNode)
			{
				if (parent.left == oldNode)
					parent.left = this;
				else
					parent.right = this;
			}
			else //Parent is ThreeNode.
			{
				ThreeNode parentThree = (ThreeNode) parent;
				
				if (parentThree.left == oldNode)
					parentThree.left = this;
				else if (parentThree.middle == oldNode)
					parentThree.middle = this;
				else
					parentThree.right = this;
			}
		}
	}
	private class TwoNode extends Node
	{		
		public TwoNode(KeyValuePair<K, Value1, Value2> keyValues1, int N)
		{
			this.keyvalues1 = keyValues1;
			this.N = N;
		}
	}
	private class ThreeNode extends Node
	{
		private KeyValuePair<K, Value1, Value2> keyvalues2;
		private Node middle;
		
		public ThreeNode(KeyValuePair<K, Value1, Value2> keyValues1, KeyValuePair<K, Value1, Value2> keyValues2, int N)
		{
			this.keyvalues1 = keyValues1;
			this.keyvalues2 = keyValues2;
			this.N = N;
		}
	}
	
	public twothreetree()
	{
		//root = new TwoNode();
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
			root = new TwoNode(new KeyValuePair<K, Value1, Value2>(key, value), 1);
			return;
		}
		
		//Find the node we should add stuff to.
		Node existingNode = getNode(key);
		
		//If not found ABORT!!!!!!!
		if (existingNode == null)
			return;
		
		
		if (existingNode instanceof twothreetree.TwoNode)
		{
			if (key > existingNode.keyvalues1.key)
			{
				//Store our new stuff to the right.
				ThreeNode newNode = new ThreeNode(existingNode.keyvalues1, new KeyValuePair<K, Value1, Value2>(key, value));
	
				Node parent = existingNode.parent;
				newNode.parent = parent;
				
				//Relink the parent to our new node.
				newNode.Relink(existingNode);
				
				return;
			}
			else if (existingNode.keyvalues1.key == key)
			{
				//Just replace the value of the key.
				existingNode.keyvalues1.value = value;
				return;	
			}
			else // Lesser than...
			{
				//Store our new stuff to the left.
				ThreeNode newNode = new ThreeNode(new KeyValuePair<K, Value1, Value2>(key, value), existingNode.keyvalues1);

				Node parent = existingNode.parent;
				newNode.parent = parent;
				
				//Relink the parent to our new node.
				newNode.Relink(existingNode);
				
				return;
			}
			
		}
		else if (existingNode instanceof twothreetree.ThreeNode)
		{
			ThreeNode existingThreeNode = (ThreeNode) existingNode;
		
			KeyValuePair<K, Value1, Value2> rightMostValue;

			if (existingThreeNode.keyvalues1.key.equals(key))
			{
				existingThreeNode.keyvalues1.value = value;
				return;
			}
			if (existingThreeNode.keyvalues2.key.equals(key))
			{
				existingThreeNode.keyvalues2.value = value;
				return;
			}
			
			
			//Key is less than left key, insert new value to the left. 
			if (key < existingThreeNode.keyvalues1.key)
			{
				rightMostValue = existingThreeNode.keyvalues2;
				existingThreeNode.keyvalues2 = existingThreeNode.keyvalues1;
				existingThreeNode.keyvalues1 = new KeyValuePair<K, Value1, Value2>(key, value); 
			}
			//Key is larger then the left key, and smaller then the right key, thus it should go in the middle.
			if (key > existingThreeNode.keyvalues1.key && key < existingThreeNode.keyvalues2.key)
			{
				rightMostValue = existingThreeNode.keyvalues2;
				existingThreeNode.keyvalues2 = new KeyValuePair<K, Value1, Value2>(key, value);
			}
			//Key is more then the right key, insert new value in the right.
			if (key > existingThreeNode.keyvalues2.key)
			{
				rightMostValue = new KeyValuePair<K, Value1, Value2>(key, value);
			}
			
			
			putInNode(existingNode, rightMostValue);
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
	
	private void putInNode(ThreeNode existingNode, KeyValuePair<K, Value1, Value2> rightMostKey)
	{
	
		Node parent = existingNode.parent;
		
		//If we have managed to climb to the top of our mountain.
		if (parent == root)
		{
			if (key < parent.keyvalues1)
			{
				root = new TwoNode(parent.keyvalues1);
				root.left = new TwoNode (key, values);
				root.right = new TwoNode(parent.keyvalues2);
			}
			else if (key > parent.keyvalues1 && key < parent.keyvalues2)
			{
				root = new TwoNode(key, values);
			}
			else if (key > parent.keyvalues2)
			{
				root = new TwoNode(parent.keyvalues2);
			}
			
			return;
		}
		
		
		if(parent instanceof twothreetree.TwoNode)
		{
			if (existingNode.keyvalues2 < parent.keyvalues1)
			{
				ThreeNode newNode = new ThreeNode(existingNode.keyvalues2, parent.keyvalues1);
				newNode.parent = parent.parent;
				
				newNode.Relink(parent);
				
				
				TwoNode newLeft = new TwoNode (existingNode.keyvalues1);
				newLeft.parent = parent;
				
				newNode.left = newLeft;
				
				TwoNode newMiddle = new TwoNode (rightMostKey);
				newMiddle.parent = parent;
				
				newNode.middle = newMiddle;
				
				newNode.right = existingNode.right;
			}
			else if(existingNode.keyvalues2 > parent.keyvalues1)
			{
				ThreeNode newNode = new ThreeNode(parent.keyvalues1, existingNode.keyvalues2);
				newNode.parent = parent.parent;
				
				newNode.Relink(parent);
				
				newNode.left = existingNode.left;
				
				TwoNode newMiddle = new TwoNode (existingNode.keyvalues1));
				newMiddle.parent = parent;
				
				newNode.middle = newMiddle;
				
				TwoNode newRight = new TwoNode (rightMostKey);
				newRight.parent = parent;
				
				newNode.right = newRight;
			}
			
		}
		else if(parent instanceof twothreetree.ThreeNode)
		{
			
		}
		
		
	}
		
//		
//		
//		if (existingNode instanceof TwoNode)
//		{
//			if (existingNode.keyvalues1.key < key)
//			{
//				ThreeNode newNode = new ThreeNode(existingNode.keyvalues1, new KeyValuePair(key, values));
//				existingNode = newNode;
//				return;
//			}
//			else if (existingNode.keyvalues1.key == key)
//			{
//				existingNode.keyvalues1.value = values;
//				return;
//			}
//			else // Greater than...
//			{
//				ThreeNode newNode = new ThreeNode(new KeyValuePair(key, values), existingNode.keyvalues1);
//				existingNode = newNode;
//				return;
//			}
//		}
//		else if (existingNode instanceof ThreeNode)
//		{
//			ThreeNode existingThreeNode = (ThreeNode) existingNode;
//			
//			//If any of the two keys are equal to our key, then replace that keys values (overwrite).  
//			if (existingThreeNode.keyvalues1.key.equals(key))
//			{
//				existingThreeNode.keyvalues1.value = values;
//				return;
//			}
//				
//			if (existingThreeNode.keyvalues2.key.equals(key))
//			{
//				existingThreeNode.keyvalues2.value = values;
//				return;
//			}
//			
//			if (existingThreeNode == root)
//			{
//				if (key < existingThreeNode.keyvalues1)
//				{
//					root = new TwoNode(existingThreeNode.keyvalues1);
//					root.left = new TwoNode (key, values);
//					root.right = new TwoNode(existingThreeNode.keyvalues2);
//				}
//				else if (key > existingThreeNode.keyvalues1 && key < existingThreeNode.keyvalues2)
//				{
//					root = new TwoNode(key, values);
//				}
//				else if (key > existingThreeNode.keyvalues2)
//				{
//					root = new TwoNode(existingThreeNode.keyvalues2);
//				}
//				
//				return;
//			}
//			
//			Node parentNode = existingNode.parent;
//			
//			if (key < existingThreeNode.keyvalues1)
//			{
//				
//				
//				putInNode(parentNode, key, values);
//				
//				
//			}
//			else if (key > existingThreeNode.keyvalues1 && key < existingThreeNode.keyvalues2)
//			{
//				Node newNode = new Node(new KeyValuePair(key, values));
//				newNode.parent = parentNode.parent;
//				
//				
//				
//			}
//			else if (key > existingThreeNode.keyvalues2)
//			{
//				
//			}
//		}
//	}
	
	
}
