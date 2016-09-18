/**
 * Chenxi Sun
 * Uid u0455173
 */

package assignment02;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LibraryTestJunit {

	@Before
	public void setUp() throws Exception {
	
	
		
		
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public void MediumLibraryTest() {
		LibraryGeneric<String> lib = new LibraryGeneric<String>();
		LibraryGeneric<PhoneNumber> lib2 = new LibraryGeneric<PhoneNumber>();
		lib.addAll("Mushroom_Publishing.txt");
		lib2.addAll("Mushroom_Publishing.txt");
		lib.checkout(9781843190400L, "Kylie", 1, 1, 2008);
		assertEquals("Kylie",lib.lookup(9781843190400L));
		lib.checkout(9781843193319L, "Kylie", 1, 1, 2008);
		
		PhoneNumber patron2 = new PhoneNumber("801.555.1234");
		lib2.checkout(9781843193319L, patron2, 1, 1, 2008);
		
		ArrayList<LibraryBookGeneric<PhoneNumber>> sample=new ArrayList<LibraryBookGeneric<PhoneNumber>>();
	
		LibraryBookGeneric<PhoneNumber> Book1=new LibraryBookGeneric<PhoneNumber>(9781843193319L,	"Alan Burt Akers",	"Transit to Scorpio"); 
		sample.add(Book1);
		assertEquals(sample,lib2.lookup(patron2));
		lib2.checkin(9781843193319L);
		sample.remove(0);
		assertEquals(sample,lib2.lookup(patron2));
		LibraryBookGeneric<PhoneNumber> Book2=new LibraryBookGeneric<PhoneNumber>(9781843190004L,	"Moyra Caldecott",	"Weapons of the Wolfhound");
		lib2.checkout(9781843190004L, patron2, 1, 1, 2008);
		sample.add(Book2);
		assertEquals(sample,lib2.lookup(patron2));
	
	}
	
	
	@Test
	
	public void lookupISBNTest() {
		LibraryGeneric<String> lib = new LibraryGeneric<String>();
		lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib.add(9780446580342L, "David Baldacci", "Simple Genius");
		lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
		lib.checkout(9780446580342L, "Kylie Jenner", 1, 1, 2008);
		
		
		
		
		assertEquals("Jane Doe",lib.lookup(9780330351690L));
		lib.checkin(9780330351690L);
		assertEquals(null,lib.lookup(9780330351690L));
		lib.checkout(9780330351690L, "Danny", 1, 1, 2008);		
		assertEquals("Danny",lib.lookup(9780330351690L));
		assertEquals("Kylie Jenner",lib.lookup(9780446580342L));
	
	
	
	
	
	}
	
	@Test
	public void CheckoutTest() {
		LibraryGeneric<String> lib = new LibraryGeneric<String>();
		lib.addAll("Mushroom_Publishing.txt");
		lib.checkout(9781843193319L, "Kylie", 1, 1, 2008);
		assertEquals("Kylie", lib.lookup(9781843193319L));
		lib.checkout(9781843191230L, "Kendall", 1, 1, 2008);
		assertEquals("Kendall", lib.lookup(9781843191230L));
		lib.checkout(9781843192022L, "Kendall", 1, 1, 2008);
		assertEquals("Kendall", lib.lookup(9781843191230L));
		lib.checkout(9781843190400L, "Kim", 1, 1, 2008);
		assertEquals("Kim", lib.lookup(9781843190400L));
	
	
	}
	
	
	
	
	@Test
	public void lookupHolderNameTest() {
		LibraryGeneric<String> lib = new LibraryGeneric<String>();
		ArrayList<LibraryBookGeneric<String>> sample=new ArrayList<LibraryBookGeneric<String>>();
		LibraryBookGeneric<String> Book1=new LibraryBookGeneric<String>(9781843193319L,	"Alan Burt Akers",	"Transit to Scorpio"); 
		LibraryBookGeneric<String> Book2=new LibraryBookGeneric<String>(9781843191230L,	"Mary Lancaster",	"An Endless Exile"); 
		lib.addAll("Mushroom_Publishing.txt");
		lib.checkout(9781843193319L, "Kylie", 1, 1, 2008);
		lib.checkout(9781843191230L, "Kylie", 1, 1, 2008);
		sample.add(Book2);
		sample.add(Book1);
		assertEquals(sample, lib.lookup("Kylie"));
		sample.remove(1);
		lib.checkin(9781843193319L);
		assertEquals(sample, lib.lookup("Kylie"));
		lib.checkout(9781843193319L, "Kim", 1, 1, 2008);
		sample.clear();
		sample.add(Book1);
		assertEquals(sample, lib.lookup("Kim"));
		lib.checkin(9781843193319L);
		lib.checkout(9781843193319L, "Kendall", 1, 1, 2008);
		
	
		assertEquals(sample, lib.lookup("Kendall"));
	
	
	
	
	}
	
	@Test
	
	public void GetOrderByAuthorTest() {
		LibraryGeneric<String> lib = new LibraryGeneric<String>();
		lib.addAll("Mushroom_Publishing.txt");
		lib.getOrderedByAuthor();
		for(int i=0;i<lib.getOrderedByAuthor().size();i++){
			//System.out.println(lib.getOrderedByAuthor().get(i));
		}
		LibraryBookGeneric<String> Book1=new LibraryBookGeneric<String>(9781843193319L,	"Alan Burt Akers",	"Transit to Scorpio"); 
		LibraryBookGeneric<String> Book2=new LibraryBookGeneric<String>(9781843190479L, "Anthony J D Burns", "Demogorgon Rising"); 
		LibraryBookGeneric<String> Book3=new LibraryBookGeneric<String>(9781843190936L, "Carol E. Meacham", "Machina Obscura"); 
		LibraryBookGeneric<String> Book4=new LibraryBookGeneric<String>(9781843190677L, "Cheryl Jones", "Herbs for Healthy Skin"); 
		assertEquals(Book1, lib.getOrderedByAuthor().get(0));
		assertEquals(Book2, lib.getOrderedByAuthor().get(1));
		assertEquals(Book3, lib.getOrderedByAuthor().get(2));
		assertEquals(Book4, lib.getOrderedByAuthor().get(3));
	
	
	}
	
	@Test
	
	public void GetOrderByDueDateTest() {
		LibraryGeneric<String> lib = new LibraryGeneric<String>();
		lib.addAll("Mushroom_Publishing.txt");
		lib.getOverdueList(1, 1, 2016);
		LibraryBookGeneric<String> Book1=new LibraryBookGeneric<String>(9781843193319L,	"Alan Burt Akers",	"Transit to Scorpio"); 
		LibraryBookGeneric<String> Book2=new LibraryBookGeneric<String>(9781843190479L, "Anthony J D Burns", "Demogorgon Rising"); 
		LibraryBookGeneric<String> Book3=new LibraryBookGeneric<String>(9781843190936L, "Carol E. Meacham", "Machina Obscura"); 
		LibraryBookGeneric<String> Book4=new LibraryBookGeneric<String>(9781843190677L, "Cheryl Jones", "Herbs for Healthy Skin");
		lib.checkout(9781843193319L, "kylie", 1, 1, 1900);
		lib.checkout(9781843190479L, "kylie", 1, 2, 1901);
		lib.checkout(9781843190936L, "kylie", 1, 2, 1902);
		lib.checkout(9781843190677L, "kylie", 1, 2, 1903);
		for(int i=0;i<lib.getOverdueList(1, 1, 2016).size();i++){
			//System.out.println(lib.getOverdueList(1, 1, 2016).get(i));
		}
		assertEquals(Book1, lib.getOverdueList(1, 1, 2016).get(0));
		assertEquals(Book2, lib.getOverdueList(1, 1, 2016).get(1));
		assertEquals(Book3, lib.getOverdueList(1, 1, 2016).get(2));
		assertEquals(Book4, lib.getOverdueList(1, 1, 2016).get(3));
	
	}
	
	

}
