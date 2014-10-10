package se.kth.id1020.lab4;

public class twothreetree<K extends Comparable<K>, Value1, Value2> {

	private Node root;
	private int countSplit = 0;
	
	public class Node
	{
		protected KeyValuePair<K, Value1, Value2> keyvalues1;
		protected KeyValuePair<K, Value1, Value2> keyvalues2;
		protected Node parent, left, middle, right;
		
		public Node(KeyValuePair<K, Value1, Value2> keyValues1)
		{
			this.keyvalues1 = keyValues1;
		}
		
		public Node(KeyValuePair<K, Value1, Value2> keyValues1, KeyValuePair<K, Value1, Value2> keyValues2)
		{
			this(keyValues1);
			this.keyvalues2 = keyValues2;
		} 
	}
	public class FourNode extends Node
	{
		protected KeyValuePair<K, Value1, Value2> keyvalues3;
		protected Node middle2;
		
		public FourNode (KeyValuePair<K, Value1, Value2> keyValues1, KeyValuePair<K, Value1, Value2> keyValues2, KeyValuePair<K, Value1, Value2> keyValues3)
		{
			super(keyValues1, keyValues2);
			this.keyvalues3 = keyValues3;
		}
	}
	
	public ValuePair<Value1, Value2> get(K key)
	{
		Node searchResult = get(key, root);
		
		if (searchResult != null)
		{
			if (searchResult.keyvalues1.key.equals(key))
				return searchResult.keyvalues1.value;
			else
				return searchResult.keyvalues2.value;
		}
		else
			return null;
	}
	
	public void put(K key, ValuePair<Value1, Value2> value)
	{
		//Null values are not supported, cause then get will not work properly.
		//Assume we store a null value and then get will return that null value. The calling
		//code will then not know if we have found the value (that is null) or if the search
		//was unsuccessful and that's why we are returning null. 
		if (value == null)
			throw new IllegalArgumentException("Null values not supported!");
		
		//If we don't have a root node we don't have a tree.
		//Create a TwoNode and store our key/value in it, done.
		if (root == null)
		{
			//Size of 1 since we just have one node.
			root = new Node(new KeyValuePair<K, Value1, Value2>(key, value));
			countSplit = 1;
			return;
		}
		
		//Find the node we should add stuff to.
		Node foundNode = getNode(key, root);
		
		//If the node is a TwoNode, then it's simple. Convert that TwoNode to a ThreeNode.
		if (foundNode.keyvalues2 == null)
		{
			if (key.compareTo(foundNode.keyvalues1.key) <= -1)
			{
				//Insert new stuff at the left, thus we need to move that data.
				foundNode.keyvalues2 = foundNode.keyvalues1;
				foundNode.keyvalues1 = new KeyValuePair<K, Value1, Value2>(key, value);
			}
			else if (key.compareTo(foundNode.keyvalues1.key) == 0)
			{
				//Just replace the value of the key.
				foundNode.keyvalues1.value = value;
			}
			else if (key.compareTo(foundNode.keyvalues1.key) >= 1)
			{
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
			if (key.compareTo(foundNode.keyvalues1.key) <= -1)
				tempFourNode = new FourNode(new KeyValuePair<K, Value1, Value2>(key, value), foundNode.keyvalues1, foundNode.keyvalues2);
			
			//Key is larger then the left key, and smaller then the right key, thus it should go in the middle.
			else if (key.compareTo(foundNode.keyvalues1.key) >= 1 && key.compareTo(foundNode.keyvalues2.key) <= -1)
				tempFourNode = new FourNode(foundNode.keyvalues1, new KeyValuePair<K, Value1, Value2>(key, value), foundNode.keyvalues2);
			
			//Key is more then the right key, insert new value in the right.
			else if (key.compareTo(foundNode.keyvalues2.key) >= 1)
				tempFourNode = new FourNode(foundNode.keyvalues1, foundNode.keyvalues2, new KeyValuePair<K, Value1, Value2>(key, value));
			
			put4NodeInNode(foundNode, tempFourNode);
		}
		
	}
	
	public int size()
	{
		return countSplit;
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
	
	private Node getNode(K key, Node startNode)
	{
		if (startNode.left == null)
			return startNode;
		
		//If smaller than the first value.
		if (key.compareTo(startNode.keyvalues1.key) <= -1)
		{
			return getNode(key, startNode.left);
		}
		
		
		//TwoNode.
		if (startNode.keyvalues2 == null)
		{
			//If equal to our value.
			if (key.compareTo(startNode.keyvalues1.key) == 0)
				return startNode;
			else
				return getNode(key, startNode.middle);
		}
		else //ThreeNode
		{
			//If equal to our first or second value.
			if (key.compareTo(startNode.keyvalues1.key) == 0 || key.compareTo(startNode.keyvalues2.key) == 0)
				return startNode;
			
			//If in the middle.
			if (key.compareTo(startNode.keyvalues1.key) >= 1 && key.compareTo(startNode.keyvalues2.key) <= -1)
				return getNode(key, startNode.middle);
			else //If greater than our second value.
				return getNode(key, startNode.right);
		}	
	}
	
	private Node get(K key, Node startNode)
	{
		if (startNode == null)
			return null;
		
		//If smaller than the first value.
		if (key.compareTo(startNode.keyvalues1.key) <= -1)
			return get(key, startNode.left);
		
		
		//TwoNode.
		if (startNode.keyvalues2 == null)
		{
			//If equal to our value.
			if (key.compareTo(startNode.keyvalues1.key) == 0)
				return startNode;
			else
				return get(key, startNode.middle);
		}
		else //ThreeNode
		{
			//If equal to our first or second value.
			if (key.compareTo(startNode.keyvalues1.key) == 0 || key.compareTo(startNode.keyvalues2.key) == 0)
				return startNode;
			
			//If in the middle.
			if (key.compareTo(startNode.keyvalues1.key) >= 1 && key.compareTo(startNode.keyvalues2.key) <= -1)
				return get(key, startNode.middle);
			else //If greater than our second value.
				return get(key, startNode.right);
		}
		
	}
	
	private void put4NodeInNode(Node currentNode, FourNode tmpFourNode)
	{
	
		//Split the FourNode into a TwoNode.	
		Node splitResult = Convert4to2(tmpFourNode);
		countSplit++;
		
		if (currentNode == root)
		{
			root = splitResult;
			countSplit++;
		}
			
		else
		{
			Node parent = currentNode.parent;
		
			//Merge with the parent.
			FourNode mergeResult = MergeNodes (parent, splitResult);
			
			//If the merge result is null parent was a TwoNode, and we are done.
			// If not, we need to merge the new FourNode we have with the parent and repeat.
			if (mergeResult != null)
				put4NodeInNode(parent, mergeResult);	
		}		
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
		//			 (b)
		//			/   \
		//	 	   /     \
		//	 	 (a)     (c)
		//   	/	\	/   \
		//     /	 \ /     \
		
		//Create a new root node, b, from the middle keyvalue. 
		Node newRoot = new Node (inNode.keyvalues2);
		
		//New left, a, is the left child. New right, c, is the right child.
		Node newLeft = new Node (inNode.keyvalues1);
		Node newRight = new Node (inNode.keyvalues3);
		
		//Get the two left most children to the newLeft node.
		newLeft.parent = newRoot;
		
		newLeft.left = inNode.left;
		
		if (newLeft.left != null)
			newLeft.left.parent = newLeft;
		
		newLeft.middle = inNode.middle;
		
		if (newLeft.middle != null)
			newLeft.middle.parent = newLeft;
		
		//Get the two right most children to the newRight node.
		newRight.parent = newRoot;
		
		newRight.left = inNode.middle2;
		
		if (newRight.left != null)
			newRight.left.parent = newRight;
		
		newRight.middle = inNode.right;
		
		if(newRight.middle != null)
			newRight.middle.parent = newRight;
		
		//Set the new children to the NewRoot.
		newRoot.left = newLeft;
		newRoot.middle = newRight;
		
		return newRoot;
	}
	
	private FourNode MergeNodes(Node treeNode, Node separateNode)
	{		
		//The separate node we are sending in is assumed to be a TwoNode.
		
		//If the node in the tree we are merging with is a TwoNode.
		if (treeNode.keyvalues2 == null)
		{
			//The thing we are merging is smaller than the treeNode's key.
			if(separateNode.keyvalues1.key.compareTo(treeNode.keyvalues1.key) <= -1)
			{
				//Move the key/values to the right and insert.
				treeNode.keyvalues2 = treeNode.keyvalues1;
				treeNode.keyvalues1 = separateNode.keyvalues1;
				
				//Move the children to the right and insert the separateNode's children into the tree node.
				treeNode.right = treeNode.middle;
				treeNode.middle = separateNode.middle; //Right child in the separateNode.
				treeNode.left = separateNode.left;
			}
			else if (separateNode.keyvalues1.key.compareTo(treeNode.keyvalues1.key) >= 1)
			{
				//Insert key/values.
				treeNode.keyvalues2 = separateNode.keyvalues1;
				
				//Insert the children.
				treeNode.right = separateNode.middle; //Right node in the separateNode.
				treeNode.middle = separateNode.left;
			}
			
			//Don't forget to relink the parent property.
			separateNode.middle.parent = treeNode;
			separateNode.left.parent = treeNode;
									
			return null;
		}
		else //If the node in the tree we are merging with is a three node.
		{
			FourNode tmpFourNode;
			
			//If the key in the separate node is smaller than the three node's first key.
			if (separateNode.keyvalues1.key.compareTo(treeNode.keyvalues1.key) <= -1)
			{
				
				tmpFourNode = new FourNode(separateNode.keyvalues1, treeNode.keyvalues1, treeNode.keyvalues2);
				
				tmpFourNode.left = separateNode.left;
				tmpFourNode.middle = separateNode.middle;
				tmpFourNode.middle2 = treeNode.middle;
				tmpFourNode.right = treeNode.right;
			}
			else if (separateNode.keyvalues1.key.compareTo(treeNode.keyvalues1.key) >= 1 && separateNode.keyvalues1.key.compareTo(treeNode.keyvalues2.key) <= -1)
			{
				tmpFourNode = new FourNode(treeNode.keyvalues1, separateNode.keyvalues1, treeNode.keyvalues2);
				
				tmpFourNode.left = treeNode.left;
				tmpFourNode.middle = separateNode.left;
				tmpFourNode.middle2 = separateNode.middle;
				tmpFourNode.right = treeNode.right;
			}
			else //If not smaller or in the middle of our values it must be bigger.
			{
				tmpFourNode = new FourNode(treeNode.keyvalues1, treeNode.keyvalues2, separateNode.keyvalues1);
				
				tmpFourNode.left = treeNode.left;
				tmpFourNode.middle = treeNode.middle;
				tmpFourNode.middle2 = separateNode.left;
				tmpFourNode.right = separateNode.middle;
			}
			
			//Relink the children to our FourNode.
			tmpFourNode.left.parent = tmpFourNode;
			tmpFourNode.middle.parent = tmpFourNode;
			tmpFourNode.middle2.parent = tmpFourNode;
			tmpFourNode.right.parent = tmpFourNode;			
			
			return tmpFourNode;
		}
		
	}
	
	
}
