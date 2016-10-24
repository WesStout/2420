package assignment08;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BinarySearchTreeTest {
	BinarySearchTree bst;
	
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
		System.out.println(bst.first());
		System.out.println(bst.last());
		bst.writeDot("dotTestt");
	}	
	
	

}
