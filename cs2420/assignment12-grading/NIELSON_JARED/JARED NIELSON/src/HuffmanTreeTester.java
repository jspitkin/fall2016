package assignment12;

import static org.junit.Assert.*;
import static assignment12.FileDifUtil.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for the HuffmanTree class
 * @author Jared Nielson u0495206
 *
 */
public class HuffmanTreeTester {
	HuffmanTree t;
	@Before
	public void setUp() throws Exception {
		
		t = new HuffmanTree();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void emptyFile() {
		t.compressFile(new File("Empty.txt"),
				new File("EmptyComp.txt"));
		
		t.decompressFile(new File("EmptyComp.txt"), 
				new File("EmptyDecomp.txt"));
		
		assertTrue(difFiles("Empty.txt", "EmptyDecomp.txt"));
		
	}
	
	@Test
	public void singleChar(){
		t.compressFile(new File("Single.txt"), 
				new File("SingleComp.txt"));
		
		t.decompressFile(new File("SingleComp.txt"), new File("SingleDecomp.txt"));
		
		assertTrue(difFiles("Single.txt", "SingleDecomp.txt"));
	}
	
	@Test
	public void doubleChar(){
		t.compressFile(new File("Double.txt"), 
				new File("DoubleComp.txt"));
		
		t.decompressFile(new File("DoubleComp.txt"), new File("DoubleDecomp.txt"));
		
		assertTrue(difFiles("Double.txt", "DoubleDecomp.txt"));
	}
	
	@Test
	public void manyChars(){
		t.compressFile(new File("Many.txt"), 
				new File("ManyComp.txt"));
		
		t.decompressFile(new File("ManyComp.txt"), new File("ManyDecomp.txt"));
		
		assertTrue(difFiles("Many.txt", "ManyDecomp.txt"));
	}
	
	@Test
	public void identicalChars(){
		t.compressFile(new File("Identical.txt"), 
				new File("IdenticalComp.txt"));
		
		t.decompressFile(new File("IdenticalComp.txt"), new File("IdenticalDecomp.txt"));
		
		assertTrue(difFiles("Identical.txt", "IdenticalDecomp.txt"));
	}

}














