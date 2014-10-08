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
			if (parent instanceof TwoNode)
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
		//If we don't have a root node, create one and store our stuff in it.
		if (root == null)
		{
			KeyValuePair test = new KeyValuePair<K, Value1, Value2>(key, value);
			root = new TwoNode(test);
			return;
		}
		
		Node existingNode = getNode(key);
		
		if (existingNode == null)
			return;
		
		
		if (existingNode instanceof TwoNode)
		{
			if (key > existingNode.keyvalues1.key)
			{
				//Store our new stuff to the right.
				ThreeNode newNode = new ThreeNode(existingNode.keyvalues1, new KeyValuePair(key, values));
	
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
				ThreeNode newNode = new ThreeNode(new KeyValuePair(key, values), existingNode.keyvalues1);

				Node parent = existingNode.parent;
				newNode.parent = parent;
				
				//Relink the parent to our new node.
				newNode.Relink(existingNode);
				
				return;
			}
			
		}
		else if (existingNode instanceof ThreeNode)
		{
			ThreeNode existingThreeNode = (ThreeNode) existingNode;
		
			KeyValuePair tmp;
			
			if (key > existingThreeNode.keyvalues1.key)
			{
				tmp = new KeyValuePair(key, value);
			}
			
			
			putInNode(existingNode, key, value);
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
	
	private void putInNode(Node existingNode, K key, ValuePair<Value1, Value2> values)
	{
	
		
		
		
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
