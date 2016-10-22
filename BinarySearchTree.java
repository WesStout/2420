package assignment08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import assignment06.DoublyLinkedList.Node;
import lab08.BinarySearchNode;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

	public Node root = null;
	
	private int size = 0;
	
	public BinarySearchTree() {
		BinarySearchTree BST = new BinarySearchTree();
		size = 0;
	}
	
	/**
	 * Node class that allows for .data, .prev. and .next
	 *
	 * @param <E>
	 */
    public class Node<T>{
        private T data;
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

	@Override
	public boolean addAll(Collection items) {
	
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
			return (T) root;
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
			
		}
		return null;
	}
	
	public Node<T> getLeftmostNode(Node<T> currentNode) {
		if (currentNode.left == null) {
			return currentNode.left;
		}
		return getLeftmostNode(currentNode.left);
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
    		Node<T> leftMostNode = getLeftmostNode(currentNode.right);
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
		
		return null;
	}

	
}

