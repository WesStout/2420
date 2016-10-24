package assignment08;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class BinarySearchTreeTest {
	BinarySearchTree<String> bst;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddAndFirst() {
		bst = new BinarySearchTree();
		bst.add("apple");
	}	
	@Test
	public void testAddSeveralFirst() {
		bst = new BinarySearchTree();
		bst.add("alpha");
		bst.add("bravo");
		bst.add("charlie");
		bst.add("delta");
		bst.add("echo");
		bst.add("foxtrot");
		bst.add("golf");
		bst.add("hotel");
		bst.add("india");
		bst.add("juliett");
//		bst.writeDot("dotTestt");
	}	
	@Test
	public void testAddSeveralReverseOrder() {
		bst = new BinarySearchTree();
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		bst.add("foxtrot");
		bst.add("echo");
		bst.add("delta");
		bst.add("charlie");
		bst.add("bravo");
		bst.add("alpha");
		assertEquals(bst.getLeftMostNode(bst.root).data, "alpha");
	}	
	@Test
	public void testAddAll() {
		bst = new BinarySearchTree();
		ArrayList list = new ArrayList();
		list.add("juliett");
		list.add("india");
		list.add("hotel");
		list.add("golf");
		bst.addAll(list);
		assertEquals("golf", bst.getLeftMostNode(bst.root).data);
	}	
	@Test
	public void testToArrayLast() {
		bst = new BinarySearchTree();
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		bst.add("foxtrot");
		ArrayList list = bst.toArrayList();
		int size = list.size();
		assertEquals("juliett", list.get(size-1));
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testToArrayFirstAndClear() {
		bst = new BinarySearchTree();
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		bst.add("foxtrot");
		ArrayList list = bst.toArrayList();
		assertEquals("foxtrot", list.get(0));
		bst.clear();
		assertTrue(bst.size()==0);
		ArrayList eList = bst.toArrayList();
		assertEquals("", eList.get(0));
	}
	@Test
	public void testContainsTrue() {
		bst = new BinarySearchTree();
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		assertEquals(true, bst.contains("india"));
	}	
	@Test
	public void testContainsFalse() {
		bst = new BinarySearchTree();
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		assertEquals(false, bst.contains("zebrah"));
	}	
	@Test
	public void testContainsFalsep() {
		bst = new BinarySearchTree();
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		assertEquals(false, bst.contains("apple"));
	}
}
