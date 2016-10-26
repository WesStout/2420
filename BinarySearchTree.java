package assignment08;

import static org.junit.Assert.assertFalse;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import assignment08.SortedSet;

public class BinarySearchTree<T extends Comparable<? super T>> {

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
	public class Node<T> {
		public T data;
		private Node left;
		private Node right;

		public Node(T data) {
			this.data = data;
		}
	}

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


	public void clear() {
		root = null;
		size = 0;
	}

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


	public T first() throws NoSuchElementException {
		if (root != null) {
			return (T)root.data;
		} else {
			throw new NoSuchElementException();
		}
	}


	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	
	public T last() throws NoSuchElementException {
		if (root == null) {
			throw new NoSuchElementException();
		} else {
			return (T) getRightMostNode(root).data;
		}
		// return null;
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
		int compareValue = ((T) toBeRemoved.data).compareTo((T) currentNode.data);
		if (root.data == toBeRemoved.data) {
			if (currentNode.left != null && currentNode.right != null) {
				Node<T> leftMostNodeRightSide = getLeftMostNode(currentNode.right);
				currentNode.data = leftMostNodeRightSide.data;
				if (leftMostNodeRightSide.right != null) {
					currentNode.right.left = leftMostNodeRightSide.right;
				}
				else {
					currentNode.right.left = null;
				}
				size--;
				size = 0;
				return true;
			}
			else if (currentNode.left == null && currentNode.right != null) {
				root = root.right;
				size--;
				return true;
			}
			else if (currentNode.left != null && currentNode.right == null) {
				root = root.left;
				size--;
				return true;
			}
			else if (currentNode.left == null && currentNode.right == null) {
				size = 0;
				root = null;
				return true;
			}
		}
		
		if (compareValue < 0) {
			if (currentNode.left != null) {
				//if (currentNode.left.data == toBeRemoved.data) {
					
					if (currentNode.left.left != null & currentNode.left.right != null) {
						Node<T> leftMostNodeRightSide = getLeftMostNode(currentNode.left.right);
						if (leftMostNodeRightSide.right != null) {
							if (currentNode.left.right.data == leftMostNodeRightSide.data) {
								currentNode.left.data = currentNode.left.right.data;
								currentNode.left.right = currentNode.left.right.right;
							}	
							else {
								setLeftMostNodeRightKid(currentNode.left.right, leftMostNodeRightSide);
							}
						}
						currentNode.left.data = leftMostNodeRightSide.data;
					}
					
					else if (currentNode.left.left == null && currentNode.left.right != null) {
						currentNode.left = currentNode.left.right;
					}
					else if (currentNode.left.left != null && currentNode.left.right == null) {
						currentNode.left = currentNode.left.left;
					}
					else if (currentNode.left.left == null && currentNode.left.right == null) {
						currentNode.left = null;
					}
					size--;
				//}
				
			}
		removeHelper(currentNode.left, toBeRemoved);	
		}
				
		if (compareValue > 0) {
			if (currentNode.right != null) {
				if (currentNode.right.data == toBeRemoved.data) {
					if (currentNode.right.left != null & currentNode.right.right != null) {
						Node<T> leftMostNodeRightSide = getLeftMostNode(currentNode.right.right);
						if (leftMostNodeRightSide.right != null) {
							if (currentNode.left.right.data == leftMostNodeRightSide.data) {
								currentNode.left.data = currentNode.left.right.data;
								currentNode.left.right = currentNode.left.right.right;
							}	
							else {
								setLeftMostNodeRightKid(currentNode.left.right, leftMostNodeRightSide);
							}
						}
						currentNode.left.data = leftMostNodeRightSide.data;
					}
					else if (currentNode.right.left == null && currentNode.right.right != null) {
						currentNode.right = currentNode.right.right;
					}
					else if (currentNode.right.left != null && currentNode.right.right == null) {
						currentNode.right = currentNode.right.left;
					}
					else if (currentNode.right.left == null && currentNode.right.right == null) {
						currentNode.right = null;
					}
					size--;
				}
				return true;
			}
		removeHelper(currentNode.right, toBeRemoved);	
		}	
		return false;
	}
	
	public void setLeftMostNodeRightKid(Node<T> node, Node<T> leftMostNode) {
		
		if (node.left.data == leftMostNode.data) {
			node.left = leftMostNode.right;
		}
		else {
			 setLeftMostNodeRightKid(node.left, leftMostNode);
		} 
	}

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


	public int size() {
		return size;
	}

	
	public ArrayList toArrayList() {
		ArrayList<T> array = new ArrayList<T>();
		toArrayListHelper(root, array);
		return array;
	}

	public void toArrayListHelper(Node node, ArrayList<T> array) {
		if (node == null) {
			return;
		}
		toArrayListHelper(node.left, array);
		array.add((T) node.data);
		toArrayListHelper(node.right, array);
	}

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
	
	
	public static void main(String[] args)  {
		BinarySearchTree bst = new BinarySearchTree();
		bst.add(500);
		bst.add(900);
		bst.add(1200);
		bst.add(1050);
		bst.add(1025);
		bst.add(1075);
		bst.add(1100);
		bst.add(1125);
		bst.add(1120);
		bst.add(1130);
		bst.add(1025);
		bst.add(1000);
		bst.add(975);
		bst.add(980);
		bst.add(250);
		bst.add(200);
		bst.add(400);
		bst.add(375);
		bst.add(350);
		bst.add(380);
		bst.add(377);
		bst.add(100);
		bst.add(10);
		bst.add(90);
		bst.add(50);
		bst.add(70);
		bst.remove(90);
	}

}
