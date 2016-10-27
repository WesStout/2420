package assignment08;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a test class for the BinarySearchTree class
 * @author Patrick Ekel and Will Stout
 *
 */
public class BinarySearchTreeTest {
	BinarySearchTree<String> bst;


	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddAndFirst() {
		bst = new BinarySearchTree<String>();
		bst.add("apple");
	}

	@Test
	public void testAddSeveralFirst() {
		bst = new BinarySearchTree<String>();
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
		// bst.writeDot("dotTestt");
	}

	@Test
	public void testAddSeveralReverseOrder() {
		bst = new BinarySearchTree<String>();
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
		bst = new BinarySearchTree<String>();
		ArrayList<String> list = new ArrayList<String>();
		list.add("juliett");
		list.add("india");
		list.add("hotel");
		list.add("golf");
		bst.addAll(list);
		assertEquals("golf", bst.getLeftMostNode(bst.root).data);
	}

	@Test
	public void testToArrayLast() {
		bst = new BinarySearchTree<String>();
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		bst.add("foxtrot");
		ArrayList<?> list = bst.toArrayList();
		int size = list.size();
		assertEquals("juliett", list.get(size - 1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testToArrayFirstAndClear() {
		bst = new BinarySearchTree<String>();
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		bst.add("foxtrot");
		ArrayList<?> list = bst.toArrayList();
		assertEquals("foxtrot", list.get(0));
		bst.clear();
		assertTrue(bst.size() == 0);
		ArrayList<?> eList = bst.toArrayList();
		assertEquals("", eList.get(0));
	}

	@Test
	public void testContainsTrue() {
		bst = new BinarySearchTree<String>();
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		assertEquals(true, bst.contains("india"));
	}

	@Test
	public void testContainsFalseLarge() {
		bst = new BinarySearchTree<String>();
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		assertEquals(false, bst.contains("zebrah"));
	}

	@Test
	public void testContainsFalseSmall() {
		bst = new BinarySearchTree<String>();
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		assertEquals(false, bst.contains("apple"));
	}

	@Test
	public void testContainsAllTrue() {
		bst = new BinarySearchTree<String>();
		bst.add("kilo");
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		bst.add("echo");
		ArrayList<String> list = new ArrayList<String>();
		list.add("juliett");
		list.add("india");
		list.add("hotel");
		list.add("golf");
		assertEquals(true, bst.containsAll(list));
	}

	@Test
	public void testContainsAllFalse() {
		bst = new BinarySearchTree<String>();
		bst.add("kilo");
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		bst.add("echo");
		ArrayList<String> list = new ArrayList<String>();
		list.add("juliett");
		list.add("india");
		list.add("pop");
		list.add("hotel");
		list.add("golf");
		assertEquals(false, bst.containsAll(list));
	}

	@Test
	public void testRemoveLast() {
		bst = new BinarySearchTree<String>();
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
		bst.writeDot("pre");
		bst.remove("alpha");
		bst.writeDot("post");
	assertEquals("bravo", bst.getLeftMostNode(bst.root).data);
	}
	
	@Test
	public void testRemoveFirst() {
		bst = new BinarySearchTree<String>();
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
		bst.remove("juliett");
		assertEquals("alpha", bst.getLeftMostNode(bst.root).data);
		assertEquals(true, bst.wasRemoved);
	}
	
	@Test
	public void testRemoveFirstFalse() {
		bst = new BinarySearchTree<String>();
		bst.add("juliett");
		bst.add("india");
		bst.add("bravo");
		bst.add("alpha");
		bst.remove("zoo");
		assertEquals("alpha", bst.getLeftMostNode(bst.root).data);
		assertEquals(false, bst.wasRemoved);
	}
	@Test
	public void testRemoveSingleFalse() {
		bst = new BinarySearchTree<String>();
		bst.add("juliett");
		bst.add("india");
		bst.add("bravo");
		bst.add("alpha");
		assertEquals(false, bst.remove("zoo"));
	}
	@Test
	public void testRemoveSingleTrue() {
		bst = new BinarySearchTree<String>();
		bst.add("juliett");
		bst.add("india");
		bst.add("bravo");
		bst.add("alpha");
		assertEquals(true, bst.remove("bravo"));
	}
	
	@Test
	public void testRemoveSingTrue() {
		bst = new BinarySearchTree<String>();
		bst.add("juliett");
		bst.add("india");
		bst.add("bravo");
		bst.add("bravo");
		bst.add("alpha");
		bst.add("juliett");
		bst.add("india");
		bst.add("gr");
		bst.add("s");
		bst.add("t");
		bst.add("z");
		bst.remove("bravo");
		bst.remove("c");
	}
	
	@Test
	public void testRemoveSingRootTrue() {
		bst = new BinarySearchTree<String>();
		bst.add("juliett");
		bst.add("india");
		assertEquals(2, bst.size());
		bst.remove("india");
		assertEquals(1, bst.size());
		bst.remove("juliett");
		assertEquals(0, bst.size());
		bst.add("alpha");
		bst.add("bravo");
		bst.add("charlie");
		bst.add("delta");
		System.out.println(bst.size());
		System.out.println(bst.first());
		bst.writeDot("dotTesttTestt");
		bst.remove("alpha");
		System.out.println(bst.size());
		System.out.println(bst.first());

		bst.writeDot("dotTesttTesttPOST");
		assertEquals(3, bst.size());



	}
@Test 
	public void testRemoveAll() {
		bst = new BinarySearchTree<String>();
		bst.add("kilo");
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		bst.add("echo");
		ArrayList<String> list = new ArrayList<String>();
		list.add("juliett");
		list.add("india");
		list.add("hotel");
		list.add("golf");
		list.add("echo");
		bst.removeAll(list);
		assertTrue(bst.size() == 1);
		list.add("kilo");
		bst.removeAll(list);
		assertTrue(bst.size() == 0);
		list.add("shoes");
		bst.removeAll(list);
		assertTrue(bst.size() == 0);
	}
	
	
	@Test
	public void testClear() {
		bst = new BinarySearchTree<String>();
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		bst.add("foxtrot");
		bst.clear();
		assertEquals(bst.size(), 0);
		bst.add("hoop");
		bst.remove("orange");
		bst.clear();
		assertEquals(bst.size(), 0);
	}
	
	@Test
	public void testFirst() {
		bst = new BinarySearchTree<String>();
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		bst.add("foxtrot");
		assertTrue(bst.first() == "foxtrot");
		bst.add("hoop");
		bst.remove("orange");
		assertFalse(bst.first() == "hoop");
		bst.add("add");
		assertTrue(bst.first() == "add");
	}
	
	@Test
	public void testLast() {
		bst = new BinarySearchTree<String>();
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		bst.add("foxtrot");
		assertTrue(bst.last() == "juliett");
		bst.add("hoop");
		bst.remove("orange");
		assertFalse(bst.last() == "hoop");
		bst.add("zadd");
		assertTrue(bst.last() == "zadd");
	}
	
	@Test
	public void testSize() {
		bst = new BinarySearchTree<String>();
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		bst.add("foxtrot");
		assertTrue(bst.size() == 5);
		bst.add("hoop");
		bst.remove("orange");
		assertTrue(bst.size() == 6);
		bst.clear();
		assertTrue(bst.size() == 0);
	}
	
	@Test
	public void testIsEmpty() {
		bst = new BinarySearchTree<String>();
		bst.add("juliett");
		bst.add("india");
		bst.add("hotel");
		bst.add("golf");
		bst.add("foxtrot");
		assertTrue(bst.isEmpty() == false);
		ArrayList<String> list = new ArrayList<String>();
		list.add("juliett");
		list.add("india");
		bst.removeAll(list);
		assertTrue(bst.isEmpty() == false);
		list.add("juliett");
		list.add("india");
		list.add("hotel");
		list.add("golf");
		list.add("foxtrot");
		bst.removeAll(list);
		assertTrue(bst.isEmpty() == true);
	}
}
