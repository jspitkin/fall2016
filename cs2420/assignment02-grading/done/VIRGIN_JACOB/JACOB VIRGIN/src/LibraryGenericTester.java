package assignment02;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class LibraryGenericTester {
	static LibraryGeneric<String> library;
	static ArrayList<LibraryBookGeneric<String>> janeDoeLibrary;
	static ArrayList<LibraryBookGeneric<String>> johnDoeLibrary;
	
	static long isbnForWeaponsOfTheWolfhound;
	static long isbnForTheEyeofCallanish;
	static long isbnForCrystalLegends;
	static long isbnForCloner;
	static long isbnForWhistler;
	static long isbnForTheAnxietyReliefProgram;
	static long isbnForTransitToScorpio;
	static long isbnForTheComingOfTheThird;
	
	static String MC;
	static String MCWOW;
	static String MCTEOC;
	static String MCCL;
	static String EL;
	static String ELC;
	static String RT;
	static String RTW;
	static String DRR;
	static String ABA;
	static String DRRTARP;
	static String ABATTS;
	static String JA;
	static String JATCOTT;

	
	static LibraryBookGeneric<String> WeaponsOfTheWolfhound;
	static LibraryBookGeneric<String> TheEyeOfCallanish;
	static LibraryBookGeneric<String> CrystalLegends;
	static LibraryBookGeneric<String> Cloner;
	static LibraryBookGeneric<String> Whistler;
	static LibraryBookGeneric<String> TheAnxietyReliefProgram;
	static LibraryBookGeneric<String> TransitToScorpio;
	static LibraryBookGeneric<String> TheComingOfTheThird;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		library = new LibraryGeneric<String>();
		library.addAll("Mushroom_Publishing.txt");
		
		isbnForWeaponsOfTheWolfhound = 9781843190004L;
		isbnForTheEyeofCallanish = 9781843190011L;
		isbnForCrystalLegends = 9781843190028L;
		isbnForCloner = 9781843190363L;
		isbnForWhistler = 9781843192022L;
		isbnForTheAnxietyReliefProgram = 9781843192954L;
		isbnForTransitToScorpio = 9781843193319L;
		isbnForTheComingOfTheThird = 9781843190073L;
		
		MC = "Moyra Caldecott";
		MCWOW = "Weapons of the Wolfhound";
		MCTEOC = "The Eye of Callanish";
		MCCL = "Crystal Legends";
		EL = "Emma Lorant";
		ELC = "Cloner";
		RT = "Roger Taylor";
		RTW = "Whistler";
		DRR = "Dennis Radha-Rose";
		DRRTARP = "The Anxiety Relief Program";
		ABA = "Alan Burt Akers";
		ABATTS = "Transit to Scorpio";
		JA = "Jen Alexander";
		JATCOTT = "The Coming of the Third";
		
		WeaponsOfTheWolfhound = new LibraryBookGeneric<String>(isbnForWeaponsOfTheWolfhound, MC, MCWOW);
		TheEyeOfCallanish = new LibraryBookGeneric<String>(isbnForTheEyeofCallanish, MC, MCTEOC);
		CrystalLegends = new LibraryBookGeneric<String>(isbnForCrystalLegends, MC, MCCL);
		Cloner = new LibraryBookGeneric<String>(isbnForCloner, EL, ELC);
		Whistler = new LibraryBookGeneric<String>(isbnForWhistler, RT, RTW);
		TheAnxietyReliefProgram = new LibraryBookGeneric<String>(isbnForTheAnxietyReliefProgram, DRR, DRRTARP);
		Whistler = new LibraryBookGeneric<String>(isbnForWhistler, RT, RTW);
		TransitToScorpio = new LibraryBookGeneric<String>(isbnForTransitToScorpio, ABA, "Transit to Scorpio");
		TheComingOfTheThird = new LibraryBookGeneric<String>(isbnForTheComingOfTheThird, JA, JATCOTT);
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}


	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		for(LibraryBookGeneric<String> book : library.getLibrary()){
			library.checkin(book.getIsbn());
		}
	}

	@Test
	public void testEquals(){
		ArrayList<LibraryBookGeneric<String>> listOne = new ArrayList<LibraryBookGeneric<String>>();
		listOne.add(new LibraryBookGeneric<String>(isbnForTheEyeofCallanish, MC, "The Eye of Callanish"));
		listOne.add(new LibraryBookGeneric<String>(isbnForCrystalLegends, MC, "Crystal Legends"));
		ArrayList<LibraryBookGeneric<String>> listTwo = new ArrayList<LibraryBookGeneric<String>>();
		listTwo.add(new LibraryBookGeneric<String>(isbnForTheEyeofCallanish, MC, "The Eye of Callanish"));
		listTwo.add(new LibraryBookGeneric<String>(isbnForCrystalLegends, MC, "Crystal Legends"));
		assertArrayEquals(listOne.toArray(), listTwo.toArray());
	}
	
	@Test
	public void testISBNLookupForWhistler() {
		library.checkout(isbnForWhistler, "Jane Doe", 7, 19, 2017);
		String holder = library.lookup(isbnForWhistler);
		assertEquals(true, "Jane Doe".equals(holder));
	}
	
	@Test
	public void testISBNLookupForWeaponsOfWolfhound() {
		library.checkout(9781843190004L, "John Doe", 9, 7, 2016); //Weapons Of The Wolfhound
		String holder = library.lookup(isbnForWeaponsOfTheWolfhound);
		assertTrue("John Doe".equals(holder));
	}
	
	@Test
	public void testHolderLookupForJaneDoe(){
				
		library.checkout(isbnForTheEyeofCallanish, "Jane Doe", 9, 7, 2016); //The Eye of Callanish
		library.checkout(isbnForCrystalLegends, "Jane Doe", 9, 7, 2016); //Crystal Legends
		library.checkout(isbnForCloner, "Jane Doe", 9, 7, 2016); //Cloner
		library.checkout(isbnForWhistler, "Jane Doe", 9, 7, 2016); //Whistler
		
		janeDoeLibrary = new ArrayList<LibraryBookGeneric<String>>();
		janeDoeLibrary.add(TheEyeOfCallanish);
		janeDoeLibrary.add(CrystalLegends);
		janeDoeLibrary.add(Cloner);
		janeDoeLibrary.add(Whistler);
		
		ArrayList<LibraryBookGeneric<String>>janeDoeActualLibrary = library.lookup("Jane Doe");
		
		assertArrayEquals(janeDoeLibrary.toArray(), janeDoeActualLibrary.toArray());
		
	}
	
	@Test
	public void testHolderLookupForJohnDoe(){
		
		library.checkout(9781843190004L, "John Doe", 9, 7, 2016); //Weapons Of The Wolfhound
		library.checkout(9781843192954L, "John Doe", 9, 7, 2016); //The Anxiety Relief Program
		library.checkout(9781843193319L, "John Doe", 9, 7, 2016); //Transit To Scorpio
		
		johnDoeLibrary = new ArrayList<LibraryBookGeneric<String>>();
		johnDoeLibrary.add(WeaponsOfTheWolfhound);
		johnDoeLibrary.add(TheAnxietyReliefProgram);
		johnDoeLibrary.add(TransitToScorpio);
		
		ArrayList<LibraryBookGeneric<String>> johnDoeActualLibrary = library.lookup("John Doe");
		assertTrue(johnDoeActualLibrary.equals(johnDoeLibrary));
	}
	
	@Test
	public void testCheckoutWithAvailableBook(){
		assertTrue(library.checkout(isbnForTheComingOfTheThird, "Pepe LePew", 10, 31, 2016));
	}
	
	@Test
	public void testCheckoutWithUnavailableBook(){
		library.checkout(isbnForCrystalLegends, "Tweetie Bird", 4, 8, 2016);
		assertFalse(library.checkout(isbnForCrystalLegends, "Pepe LePew", 6, 28, 2017));
	}
	
	@Test
	public void testCheckinWithAvailableBook(){
		library.checkout(isbnForCrystalLegends, "Pepe LePew", 6, 28, 2017);
		assertTrue(library.checkin(isbnForCrystalLegends));
	}
	
	@Test
	public void testCheckinWithUnavailableBook(){
		assertFalse(library.checkin(isbnForTheComingOfTheThird));
	}
	
	@Test
	public void testCheckinOfHolder(){
		library.checkout(9781843190004L, "John Doe", 9, 7, 2016); //Weapons Of The Wolfhound
		library.checkout(9781843192954L, "John Doe", 9, 7, 2016); //The Anxiety Relief Program
		library.checkout(9781843193319L, "John Doe", 9, 7, 2016); //Transit To Scorpio
		library.checkin("John Doe");
		System.out.println(library.lookup("John Doe"));
		assertEquals(new ArrayList<LibraryBookGeneric<String>>(), library.lookup("John Doe"));
	}
	
	@Test
	public void testCheckinOfHolderThatDoesntExist(){
		assertFalse(library.checkin("Your Face"));
	}
	
	@Test
	public void testOrderListByISBN(){
		LibraryGeneric<String> orderedByISBN = new LibraryGeneric<String>();
		orderedByISBN.addAll("Mushroom_Publishing_ISBN.txt");
		ArrayList<LibraryBookGeneric<String>> orderThisLibrary = library.getInventoryList();
		assertEquals(orderedByISBN.getLibrary(), orderThisLibrary);
	}
	
	@Test
	public void testOrderListByAuthor(){
		LibraryGeneric<String> orderedByAuthor = new LibraryGeneric<String>();
		orderedByAuthor.addAll("Mushroom_Publishing_Author.txt");
		ArrayList<LibraryBookGeneric<String>> orderThisLibrary = library.getOrderedByAuthor();
		assertEquals(orderedByAuthor.getLibrary(), orderThisLibrary);
	}
	
	@Test
	public void testOrderByDueDate(){
		GregorianCalendar date = new GregorianCalendar(2017, 1, 1);
		for(LibraryBookGeneric<String> book : library.getLibrary()){
			library.checkout(book.getIsbn(), "Bugs Bunny", date.get(1), date.get(2), date.get(0));
			date.add(2, 1);
		}
		
		
		
		ArrayList<LibraryBookGeneric<String>> orderThisLibrary = library.getOverdueList(12, 31, 2016);
		
		LibraryGeneric<String> orderedByDueDate = new LibraryGeneric<String>();
		orderedByDueDate.addAll("Mushroom_Publishing_Due_Date.txt");
		
		assertEquals(orderedByDueDate.getLibrary(), orderThisLibrary);
		
		
	}

}
