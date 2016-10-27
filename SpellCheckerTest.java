/**
 * By Patrick Ekel and Will Stout
 * u0736878 and U1011688
 */
package assignment08;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import assignment08.SpellChecker;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
public class SpellCheckerTest {
	SpellChecker sp = new SpellChecker();
	File f;
	
	@Before
	public void setUp() throws Exception {
		 f = new File("good_luck.txt");
	}
	
	
	
	@Test
	public void testAddToDictionary() {
		sp.addToDictionary("a");
		assertTrue(sp.readDictionaryRoot() == "a");
		sp.addToDictionary("b");
		sp.addToDictionary("c");
		sp.addToDictionary("d");
		assertTrue(sp.readDictionaryRoot() == "a");
		sp.removeFromDictionary("b");
		assertTrue(sp.readDictionaryRoot() == "a");
		
	}
	

	@Test
	public void testAddToDictionaryFirst() {
		sp.addToDictionary("z");
		assertTrue(sp.readDictionaryRoot() == "z");
		sp.addToDictionary("y");
		sp.addToDictionary("x");
		sp.addToDictionary("w");
		assertTrue(sp.readDictionaryRoot() == "z");
		sp.removeFromDictionary("m");
		assertTrue(sp.readDictionaryRoot() == "z");
		
	}
	
	
	@Test
	public void testRemoveFromDictionary() {
		sp.addToDictionary("a");
		assertTrue(sp.readDictionaryRoot() == "a");
		sp.addToDictionary("b");
		sp.addToDictionary("c");
		sp.addToDictionary("d");
		sp.removeFromDictionary("d");
		sp.removeFromDictionary("c");
		sp.removeFromDictionary("e");
		assertTrue(sp.dictionarySize() == 2);
	}
	
	@Test
	public void testSpellCheck() {
		sp.addToDictionary("good");
		sp.addToDictionary("luck");
		sp.addToDictionary("on");
		 f = new File("good_luck.txt");
		List<String> thing = sp.spellCheck(f);
		assertTrue(thing.size() == 0);
		
		
		
	}

}

