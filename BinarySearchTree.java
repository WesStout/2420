package assignment08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {
	public Node<T> root = null;
	int size =0;
	
	/**
	 * A classs for creating Nodes
	 * @author Patrick Ekel
	 * date: 10/5/16
	 *
	 * @param <T>
	 */
	public class Node<T> {
		private Node<T> left;
		private Node<T> right;
		T value; 
	/**
	 * Constructor for the Nodes
	 * @param value stored in the Node
	 */
		public Node(T value) {
			this.value = value;
			this.right=null;
			this.left=null;
		}
	}
	
	public BinarySearchTree(){
		BinarySearchTree<T> bst = new BinarySearchTree<T>(); //TODO generic..
	}
	
	@Override
	public boolean add(Comparable item) {
		if (item == null){
			return false;
		}
		Node temp = new Node(item);
		if (size() == 0){
			root = temp;
			temp.left = null;
			temp.right = null;
			size =1;
			return true;
		}
		Node currentNode = root;
		
		if (((Comparable<? super T>) currentNode.value).compareTo(root.value)== 0){
			return false;
		}
		else if (((Comparable<? super T>) currentNode.value).compareTo(root.value) > 0){
			currentNode=currentNode.right;
		}
		return false;
	}
	
//	public Node<T> add(Node<T> currentNode, T data){
//		if (currentNode == null){
//			return; 
//		}
//	}

	@Override
	public boolean addAll(Collection items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Comparable item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Comparable first() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Comparable last() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Comparable item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList toArrayList() {
		// TODO Auto-generated method stub
		return null;
	}

}
