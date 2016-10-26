package assignment08;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a test class for the SpellChecker class
 * @author Patrick Ekel and Will Stout
 *
 */
public class SpellCheckerTest {
	SpellChecker sc;


	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
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
		System.out.println(sc.spellCheck(file).toString());
		List<String> t = new ArrayList<String>();
		t.add("dude");
		t.add("hates");
		t.add("runs");
		assertEquals(t, sc.spellCheck(file));
	}
	@Test
	public void testAddToDicctionary() {
		File file = new File("C:/Utah/Fall16/2420/assignment07/un.txt");
		sc = new SpellChecker(file);
		List<String> empty = new ArrayList<String>();
		List<String> single = new ArrayList<String>();
		single.add("zebrah");
		assertEquals(empty, sc.spellCheck(file));
		sc.removeFromDictionary("zebrah");
		assertEquals(single, sc.spellCheck(file));

	}
}
