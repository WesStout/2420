package assignment08;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * This class implements a BinarySearchTree and associated methods
 * @author Patrick Ekel and Will Stout
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

	public Node root = null;
	public boolean wasRemoved=false;
	private int size = 0;
	
	/**
	 * Constructor
	 */
	public BinarySearchTree() {
		size = 0;
	}

	/**
	 * Node class that allows for .data, .prev. and .next
	 *
	 * @param <E>
	 */
	public class Node<T> {
		public T data;
		private Node left;
		private Node right;

		public Node(T data) {
			this.data = data;
		}
	}
	/**
	 * Helps the add method by recursively finding the correct location, and then adding it to the Binary Search Tree
	 * @param currentNode
	 * @param toBeInput
	 * @return True if the node was added, false if not
	 */
	public boolean addHelper(Node<T> currentNode, Node<T> toBeInput) {
		int compareValue = ((T) toBeInput.data).compareTo((T) currentNode.data);
		if (compareValue == 0) {
			return false;
		}
		if (compareValue < 0) {
			if (currentNode.left == null) {
				currentNode.left = toBeInput;
				size++;
				return true;
			}
			if (currentNode.left != null) {
				addHelper(currentNode.left, toBeInput);
			}
		}
		if (compareValue > 0) {
			if (currentNode.right == null) {
				currentNode.right = toBeInput;
				size++;
				return true;
			}
			if (currentNode.right != null) {
				addHelper(currentNode.right, toBeInput);
			}
		}
		return false;
	}
	
	/**
	 * Add the input item to the correct location in the Binary Search Tree
	 * @param item
	 * @return True if added, false if not
	 */
	@Override
	public boolean add(Comparable item) {
		if (item == null) {
			return false;
		}
		Node element = new Node(item);
		if (size == 0) {
			root = element;
			element.left = null;
			element.right = null;
			size = 1;
		} else {
			if (addHelper(root, element) == false) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	/**
	 * Adds all the items in a collection to the Binary Search Tree by calling add, items.size times
	 * @param items
	 * @return True if added any, false if none
	 */
	@Override
	public boolean addAll(Collection<? extends T> items) {
		int count = 0;
		if (items != null) {
			for ( T item : items) {
				add(item);
				count++;
			}
			if (count != 0) {
				return true;
			}
		}
		return false;

	}
	
	/**
	 * Empties the Binary Search Tree
	 */
	@Override
	public void clear() {
		root = null;
		size = 0;
	}
/**
 * 
 * @param currentNode
 * @param element
 * @return
 */
	public boolean containsHelper(Node<T> currentNode, Node<T> element) {
		int compareValue = ((T) element.data).compareTo((T) currentNode.data);
		if (compareValue == 0) {
			return true;
		}
		if (compareValue < 0) {
			if (currentNode.left != null) {
				return containsHelper(currentNode.left, element);
			}
		}
		if (compareValue > 0) {
			if (currentNode.right != null) {
				return containsHelper(currentNode.right, element);
			}
		}
		return false;
	}
	/**
	 * Checks to see if item being searched for exists in the Binary Search Tree
	 * @param item
	 * @return True if it does contain it, false if not
	 */
	@Override
	public boolean contains(Comparable item) {
		if (item == null || size == 0) {
			return false;
		}
		Node element = new Node(item);
		if (containsHelper(root, element)) {
			return true;
		}
		return false;
	}
	/**
	 * Checks to see if items being searched for exist in the Binary Search Tree
	 * @param item
	 * @return True if it contains all items in the collection, false if not
	 */
	@Override
	public boolean containsAll(Collection<? extends T> items) {
		if (items != null) {
			for ( T item : items) {
				if (contains(item) == false){
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns the smallest item in the Binary Search Tree
	 * @return The smallest node
	 * @throws NoSuchElementException
	 */
	@Override
	public T first() throws NoSuchElementException {
		if (root == null) {
			throw new NoSuchElementException();
		}
		else {
			Node<T> node =  getLeftMostNode(root);
			return node.data;
		}
}
	/**
	 * Checks to see if there are any nodes in the Binary Search Tree
	 * @return True if empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the largest item in the Binary Search Tree
	 * @return The largest node
	 * @throws NoSuchElementException
	 */
	@Override
	public T last() throws NoSuchElementException {
		if (root == null) {
			throw new NoSuchElementException();
		} else {
			return getRightMostNode(root).data;
		}
		// return null;
	}
	/**
	 * Gets left most node in Binary Search Tree
	 * @param currentNode
	 * @return Smallest node
	 */
	public Node<T> getLeftMostNode(Node<T> currentNode) {
		if (currentNode.left == null) {
			return currentNode;
		}
		return getLeftMostNode(currentNode.left);
	}
	
	/**
	 * Gets right most node in Binary Search Tree
	 * @param currentNode
	 * @return Largest node
	 */
	public Node<T> getRightMostNode(Node<T> currentNode) {
		if (currentNode.right == null) {
			return currentNode;
		}
		return getRightMostNode(currentNode.right);
	}
	
	/**
	 * Helps to recursively check for the smallest node, then removes it from the Binary Search Tree
	 * @param currentNode
	 * @param comp
	 * @return 
	 */
	public Node<T> removeHelper(Node<T> currentNode, Comparable comp) {		
		int compareValue = ((T) comp).compareTo((T) currentNode.data);
		if (compareValue ==0){
			if (currentNode.left ==null && currentNode.right ==null){
				return null;
			}
			if (currentNode.left == null){
				return currentNode.right;
			}
			if (currentNode.right ==null){
				return currentNode.left;
			}
			Node smallestNodeInSub = getLeftMostNode(currentNode.right);
			currentNode.data=(T) smallestNodeInSub.data;
			currentNode.right = removeHelper(currentNode.right, (Comparable) smallestNodeInSub.data);
			return currentNode;
		}
		else if (compareValue < 0){
			currentNode.left = removeHelper(currentNode.left, comp);
			return currentNode;
		}
		else{
			currentNode.right = removeHelper(currentNode.right, comp);
			return currentNode;
		}

	}
	/**
	 * Removes the node from the Binary Search Tree
	 * @param item
	 * @return True if "item" was removed, false if not
	 */
	@Override
	public boolean remove(Comparable item) {
		if (item == null || size == 0) {
			return false;
		}
		if (contains(item) == false){
			return false;
		}
		wasRemoved =true;
		size--;
		return removeHelper(root, item) != null;
		
	}
	/**
	 * Removes all items in the collection from the Binary Search Tree
	 * @param items
	 * @return True if the Binary Search Tree has changed, false if not
	 */
	@Override
	public boolean removeAll(Collection<? extends T> items) {
		int count = 0;
		if (items != null) {
			for ( T item : items) {
				if (remove(item)){
					count++;
				}
			}
		}
		if (count != 0){
			return true;
		}
		return true;
	}
	/**
	 * Returns the size
	 * @return the size
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 * Turns the Binary Search Tree into a sorted array
	 * @return
	 */
	@Override
	public ArrayList toArrayList() {
		ArrayList<T> array = new ArrayList<T>();
		toArrayListHelper(root, array);
		return array;
	}

	/**
	 * Recursively finds smallest node, then progressively finds larger nodes, so as to return a sorted arrayList
	 * @param node
	 * @param array
	 */
	public void toArrayListHelper(Node node, ArrayList<T> array) {
		if (node == null) {
			return;
		}
		toArrayListHelper(node.left, array);
		array.add((T) node.data);
		toArrayListHelper(node.right, array);
	}
/**
 * Writes dot file
 * @param filename
 */
	public void writeDot(String filename) {
		try {
			// PrintWriter(FileWriter) will write output to a file
			PrintWriter output = new PrintWriter(new FileWriter(filename));
			// Set up the dot graph and properties
			output.println("digraph BST {");
			output.println("node [shape=record]");
			if (root != null)
				writeDotRecursive(root, output);
			// Close the graph
			output.println("}");
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Recursive method for writing the tree to a dot file
	/**
	 * 
	 * @param n
	 * @param output
	 * @throws Exception
	 */
	private void writeDotRecursive(Node n, PrintWriter output) throws Exception {
		output.println(n.data + "[label=\"<L> |<D> " + n.data + "|<R> \"]");
		if (n.left != null) {
			// write the left subtree
			writeDotRecursive(n.left, output);
			// then make a link between n and the left subtree
			output.println(n.data + ":L -> " + n.left.data + ":D");
		}
		if (n.right != null) {
			// write the left subtree
			writeDotRecursive(n.right, output);
			// then make a link between n and the right subtree
			output.println(n.data + ":R -> " + n.right.data + ":D");
		}
	}

}
