package se.kth.id1020.lab4;

public class twothreetree<K extends Comparable<K>, Value1, Value2> {

	private Node root;
	
	public class Node
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
			this(keyValues1, N);
			this.keyvalues2 = keyValues2;
		}	
	}
	public class FourNode extends Node
	{
		protected KeyValuePair<K, Value1, Value2> keyvalues3;
		protected Node middle2;
		
		public FourNode (KeyValuePair<K, Value1, Value2> keyValues1, KeyValuePair<K, Value1, Value2> keyValues2, KeyValuePair<K, Value1, Value2> keyValues3, int N)
		{
			super(keyValues1, keyValues2, N);
			this.keyvalues3 = keyValues3;
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
		
		//If the node is a TwoNode, then it's simple. Convert that TwoNode to a ThreeNode.
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
			}
			
			return;
		}
		else //If the node is a ThreeNode, then it's more complicated. Create a temporary 4-node, split it and merge the parent back into the tree.
		{		

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
			
			FourNode tempFourNode = null;
			
			//Key is less than left key, insert new value to the left. 
			if (key.compareTo(foundNode.keyvalues1.key) == -1)
				tempFourNode = new FourNode(new KeyValuePair<K, Value1, Value2>(key, value), foundNode.keyvalues1, foundNode.keyvalues2, 1);
			
			//Key is larger then the left key, and smaller then the right key, thus it should go in the middle.
			else if (key.compareTo(foundNode.keyvalues1.key) == 1 && key.compareTo(foundNode.keyvalues2.key) == -1)
				tempFourNode = new FourNode(foundNode.keyvalues1, new KeyValuePair<K, Value1, Value2>(key, value), foundNode.keyvalues2, 1);
			
			//Key is more then the right key, insert new value in the right.
			else if (key.compareTo(foundNode.keyvalues2.key) == 1)
				tempFourNode = new FourNode(foundNode.keyvalues1, foundNode.keyvalues2, new KeyValuePair<K, Value1, Value2>(key, value), 1);
			
			
			put4NodeInNode(foundNode, tempFourNode);
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
		
		
		
	}
	
	private void put4NodeInNode(Node currentNode, FourNode tmpFourNode)
	{
	
		Node parent = currentNode.parent;
		
		//We are at the root node.
//		if (parent == null)
//		{
//			Node newRoot = new Node(currentNode.keyvalues2, 3);
//			newRoot.left = new Node (currentNode.keyvalues1, 1);
//			newRoot.middle = new Node (rightMostKey, 1);
//			
//			Node splitResult = Convert4to2(tmpFourNode);
//			
//			
//			
//			
//			root = newRoot;
//			return;
//		}
		
		//Split the FourNode into a TwoNode.	
		Node splitResult = Convert4to2(tmpFourNode);
		
		//Merge with the parent.
		FourNode mergeResult = MergeNodes (parent, splitResult);
		
		//If the merge result is null parent was a TwoNode, and we are done.
		// If not, we need to merge the new FourNode we have with
		if (mergeResult != null)
			put4NodeInNode(parent.parent, mergeResult);
		
//		
//		
//		//If the parent is a TwoNode, we need to add the middle value in our current node somewhere in the parent node.
//		if(parent.keyvalues2 == null)
//		{
//			//If the current nodes middle key is less than parents first (and only) key.
//			if (currentNode.keyvalues2.key.compareTo(parent.keyvalues1.key) == -1)
//			{	
//				parent.keyvalues2 = parent.keyvalues1;
//				parent.keyvalues1 = currentNode.keyvalues2;
//				
//				Node newLeft = new Node (currentNode.keyvalues1, 1);
//				newLeft.parent = parent;
//				
//				Node newMiddle = new Node (rightMostKey, 1);
//				newMiddle.parent = parent;
//				
//				
//				parent.right = parent.middle;
//				parent.middle = newMiddle;
//				parent.left = newLeft;
//			}
//			//If the current nodes middle key is greater than parents first (and only) key.
//			else if(currentNode.keyvalues2.key.compareTo(parent.keyvalues1.key) == 1)
//			{
//				parent.keyvalues2 = currentNode.keyvalues2;
//				
//				Node newMiddle = new Node (currentNode.keyvalues1, 1);
//				newMiddle.parent = parent;
//				
//				Node newRight = new Node (rightMostKey, 1);
//				newRight.parent = parent;
//				
//				parent.middle = newMiddle;
//				parent.right = newRight;
//			}
//		}
//		else //ThreeNode.
//		{
//			Node splitResult = Convert4To2(currentNode);
//			
//			FourNode mergeResult = MergeNodes (parent, splitResult);
//			
//			putNodeInNode(mergeResult);
//			
//			
//			KeyValuePair<K, Value1, Value2> rightMostKeyParent = null; 
//			Node rightMostNode = null;
//			
//			
//			//Middle element in child is less than first key in parent.
//			if (currentNode.keyvalues2.key.compareTo(parent.keyvalues1.key) == -1)
//			{
//				rightMostKeyParent = parent.keyvalues2;
//				parent.keyvalues2 = parent.keyvalues1;
//				parent.keyvalues1 = currentNode.keyvalues2;
//				
//				
//				Node newLeft = new Node(currentNode.keyvalues1, 1);
//				Node newMiddle = new Node (rightMostKey, 1);
//				
//				rightMostNode = parent.right;
//				parent.right = parent.middle;
//				
//				parent.middle = newMiddle;
//				parent.left = newLeft;
//			}
//			else if (currentNode.keyvalues2.key.compareTo(parent.keyvalues1.key) == 1 && currentNode.keyvalues2.key.compareTo(parent.keyvalues2.key) == -1)
//			{
//				rightMostKeyParent = parent.keyvalues2;
//				parent.keyvalues2 = currentNode.keyvalues2;
//				
//				Node newMiddle = new Node(currentNode.keyvalues1, 1);
//				Node newRight = new Node (rightMostKey, 1);
//				
//				rightMostNode = parent.right;
//				parent.right = newRight;
//				parent.middle = newMiddle;
//			}
//			else if (currentNode.keyvalues2.key.compareTo(parent.keyvalues2.key) == 1) //Middle element in child is larger than second key in parent.
//			{
//				rightMostKeyParent = parent.keyvalues2;
//				
//				rightMostNode = new Node(rightMostKey, 1);
//				Node newRight = new Node(currentNode.keyvalues1, 1);
//				
//				parent.right = newRight;
//			}
//			
//			
//			putNodeInNode(currentNode.parent, rightMostNode);
//			
//		}
	}
	
	private Node Convert4to2(FourNode inNode)
	{
	
		// Convert a 4 node that looks like this:
		//		
		//		(a, b, c)
		//		/  |  |	 \
		//	   /   |  |	  \
		//
		//
		//			(b)
		//	 	   /     \
		//	 	 (a)     (c)
		//   	 /	\	   \
		//  	/	 \	    \
		
		//Create a new root node, b, from the middle keyvalue.
		Node newRoot = new Node (inNode.keyvalues2, inNode.N +2);
		
		//New left, a, is the left child. New right, c, is the right child.
		Node newLeft = new Node (inNode.keyvalues1, inNode.N);
		Node newRight = new Node (inNode.keyvalues3, inNode.N);
		
		//Get the two left most children to the newLeft node.
		newLeft.left = inNode.left;
		newLeft.right = inNode.middle;
		
		//Get the two right most children to the newRight node.
		newRight.left = inNode.middle2;
		newRight.right = inNode.right;
		
		//Set the new children to the bNode.
		newRoot.left = newLeft;
		newRoot.middle = newRight;
		
		return newRoot;
	}
	
	private FourNode MergeNodes(Node treeNode, Node separateNode)
	{
		//The separate node we are sending in is assumed to be a TwoNode.
		
		//If the node in the tree we are merging with is a two node.
		if (treeNode.keyvalues2 == null)
		{
			switch(separateNode.keyvalues1.key.compareTo(treeNode.keyvalues1.key)){
			case -1: //If the key in the separate node is smaller than the three node's value, insert to the left.
				
				//Move the key/values to the right and insert.
				treeNode.keyvalues2 = treeNode.keyvalues1;
				treeNode.keyvalues1 = separateNode.keyvalues1;
				
				//Move the children to the right and insert the separateNode's children into the tree node.
				treeNode.right = treeNode.middle;
				treeNode.middle = separateNode.middle; //Right child in the separateNode.
				treeNode.left = separateNode.left;
				
				break;
			case 1: //If the key in the separate node is larger than the three node's value, insert to the right.
				
				//Insert key/values.
				treeNode.keyvalues2 = separateNode.keyvalues1;
				
				//Insert the children.
				treeNode.right = separateNode.middle; //Right node in the separateNode.
				treeNode.middle = separateNode.left;
			}
			
			return null;
		}
		else //If the node in the tree we are merging with is a three node.
		{
			FourNode tmpFourNode;
			
			
			//If the key in the separate node is smaller than the three node's first key.
			if (separateNode.keyvalues1.key.compareTo(treeNode.keyvalues1.key) == -1)
			{
				
				tmpFourNode = new FourNode(separateNode.keyvalues1, treeNode.keyvalues1, treeNode.keyvalues2, 1);
				
				tmpFourNode.left = separateNode.left;
				tmpFourNode.middle = separateNode.right;
				tmpFourNode.middle2 = treeNode.middle;
				tmpFourNode.right = treeNode.right;
			}
			else if (separateNode.keyvalues1.key.compareTo(treeNode.keyvalues1.key) == 1 && separateNode.keyvalues1.key.compareTo(treeNode.keyvalues2.key) == -1)
			{
				tmpFourNode = new FourNode(treeNode.keyvalues1, separateNode.keyvalues1, treeNode.keyvalues2, 1);
				
				tmpFourNode.left = treeNode.left;
				tmpFourNode.middle = separateNode.left;
				tmpFourNode.middle2 = separateNode.right;
				tmpFourNode.right = treeNode.right;
			}
			else //If not smaller or in the middle of our values it must be bigger.
			{
				tmpFourNode = new FourNode(treeNode.keyvalues1, treeNode.keyvalues2, separateNode.keyvalues1, 1);
				
				tmpFourNode.left = treeNode.left;
				tmpFourNode.middle = treeNode.middle;
				tmpFourNode.middle2 = separateNode.left;
				tmpFourNode.right = separateNode.right;
			}
			
			
			return tmpFourNode;
		}
		
	}
	
	
}
