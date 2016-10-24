package assignment08;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

	public Node root = null;
	
	private int size = 0;
	
	public BinarySearchTree() {
		size = 0;
	}
	
	/**
	 * Node class that allows for .data, .prev. and .next
	 *
	 * @param <E>
	 */
    public class Node<T>{
        public T data;
        private Node left;
        private Node right;
        public Node(T data) {
            this.data = data;
        }
    }
	
    
    public boolean addHelper(Node<T> currentNode, Node<T> toBeInput) {
    	int compareValue = ((T)toBeInput.data).compareTo((T) currentNode.data);
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
		}
		else {
			if (addHelper(root, element) == false) {
				return false;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	public boolean addAllHelper(Collection items, Node<T> currentNode, Node<T> nodeToBeInput){
    	int compareValue = ((T)currentNode.data).compareTo((T) nodeToBeInput.data);
    	if (compareValue == 0){
    		return false;
    	}
    	if (compareValue < 0){
    		if (currentNode.right == null){
    			currentNode.right = nodeToBeInput;
    			size++;
    			return true;
    		}
    		else{
    		return addAllHelper(items, currentNode.right, nodeToBeInput);
    		}
    	}
    	else {
    		if (currentNode.left == null){
    			currentNode.left=nodeToBeInput;
    			size++;
    			return true;
    		}
    		else{
    			return addAllHelper(items, currentNode.left, nodeToBeInput);
    		}
    	}
	}
	
	
	
	
	
	
	@Override
	public boolean addAll(Collection items) {
		if (items == null){
			return false;
		}
		Node<T> element = new Node(items.iterator().next());
		if (isEmpty()){
			root = element;
			element.left=null;
			element.right=null;
			size=1;
		}
		else{
			element = new Node(items.iterator().next());

			if (addAllHelper(items, root, element) == false){
				return false;
			}
			else{
				return true;
			}
		}
		return false;
	}
	
	
	

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public boolean contains(Comparable item) {
		
		return false;
	}

	@Override
	public boolean containsAll(Collection items) {
		
		return false;
	}

	@Override
	public T first() throws NoSuchElementException {
		if (root != null) {
			return (T) root.data;
		}
		else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	@Override
	public T last() throws NoSuchElementException {
		if (root == null) {
			throw new NoSuchElementException();
		}
		else {
			return getRightMostNode(root).data;
		}
//		return null;
	}
	public Node<T> getLeftMostNode(Node<T> currentNode) {
		if (currentNode.left == null) {
			return currentNode;
		}
		return getLeftMostNode(currentNode.left);
	}
	
	public Node<T> getRightMostNode(Node<T> currentNode) {
		if (currentNode.right == null) {
			return currentNode;
		}
		return getRightMostNode(currentNode.right);
}
	
	
	public boolean removeHelper(Node<T> currentNode, Node<T> toBeRemoved) {
    	int compareValue = ((T)toBeRemoved.data).compareTo((T) currentNode.data);
    	if (compareValue == 0 && currentNode.left == null && currentNode.right == null) {
    		currentNode = null;
    		size--;
    		return true;
    	}
    	if (compareValue == 0 && currentNode.right.left == null) {
    		currentNode.right.left = currentNode.left;
    		currentNode = currentNode.right;
    		size--;
    		return true;
    	}
    	if (compareValue == 0 && currentNode.right.left != null) {
    		Node<T> leftMostNode = getLeftMostNode(currentNode.right);
    		if (leftMostNode.right != null) {
    			currentNode.data = leftMostNode.data;
    			leftMostNode = leftMostNode.right;
    		}
    		else {
    			currentNode.data = leftMostNode.data;
    			leftMostNode = null;
    		}
    		size--;
			return true;
    	}
    	
    	if (compareValue < 0) {
			if (currentNode.left != null) {
				removeHelper(currentNode.left, toBeRemoved);
			}
    	}
    	if (compareValue > 0) {
			if (currentNode.right != null) {
				removeHelper(currentNode.right, toBeRemoved);
			}
    	}
    	return false;
}

	@Override
	public boolean remove(Comparable item) {
		if (item == null || size == 0) {
			return false;
		}
		Node element = new Node(item);
		if (removeHelper(root, element) == false) {
			return false;
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection items) {
		
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ArrayList toArrayList() {
		ArrayList<T> array = new ArrayList<T>();
		toArrayListHelper(root, array);
		return array;
	}
	public void toArrayListHelper(Node node, ArrayList<T> array){
		if (node == null){
			return;
		}
		toArrayListHelper(node.left, array);
		array.add((T) node.data);
		toArrayListHelper(node.right, array);
	}
	
	
	public void writeDot(String filename) 
	    { 
	        try { 
	            // PrintWriter(FileWriter) will write output to a file 
	            PrintWriter output = new PrintWriter(new FileWriter(filename)); 
	            // Set up the dot graph and properties 
	            output.println("digraph BST {"); 
	            output.println("node [shape=record]"); 
	            if(root != null) 
	                writeDotRecursive(root, output); 
	            // Close the graph 
	            output.println("}"); 
	            output.close(); 
	        } 
	        catch(Exception e){e.printStackTrace();} 
	    } 
	    // Recursive method for writing the tree to  a dot file 
	    private void writeDotRecursive(Node n, PrintWriter output) throws Exception 
	    { 
	        output.println(n.data + "[label=\"<L> |<D> " + n.data + "|<R> \"]"); 
	        if(n.left != null) 
	        { 
	            // write the left subtree 
	            writeDotRecursive(n.left, output); 
	            // then make a link between n and the left subtree 
	            output.println(n.data + ":L -> " + n.left.data + ":D" ); 
	        } 
	        if(n.right != null) 
	        { 
	            // write the left subtree 
	            writeDotRecursive(n.right, output); 
	            // then make a link between n and the right subtree 
	            output.println(n.data + ":R -> " + n.right.data + ":D" ); 
	        } 
	    } 

	
}
