package assignment08;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BinarySearchTreeTest {
	BinarySearchTree<String> bst;
	SpellChecker sc;


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
		bst.add("india"); //TODO assert something
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
		bst.writeDot("dotTesttTestt");

	}
	@Test
	public void testSmellerChckerSpellCheckFile() {
		ArrayList<String> shortDictionary = new ArrayList<String>();
		shortDictionary.add("the");
		shortDictionary.add("man");
		shortDictionary.add("enjoys");
		shortDictionary.add("taking");
		shortDictionary.add("long");
		shortDictionary.add("walks");
		sc = new SpellChecker(shortDictionary);
		File file = new File("C:/Utah/Fall16/2420/assignment07/shortFile.txt");
	//	List<String> res = sc.spellCheck(file);
		System.out.println(sc.spellCheck(file).toString());
		List<String> t = new ArrayList<String>();
		t.add("dude");
		t.add("hates");//TODO order matters hereee
		t.add("runs");//TODO also, ARRAYLIST in spellCheck
		assertEquals(t, sc.spellCheck(file));
	}

}
