package assignment02;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Testing class for LibraryGeneric.
 *
 * @author Ben Figlin (u1115949)
 * 
 */
public class LibraryGenericTest {

  public static void main(String[] args) {

    // test a library that uses names (String) to id patrons
    LibraryGeneric<String> lib1 = new LibraryGeneric<String>();
    lib1.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
    lib1.add(9780330351690L, "Jon Krakauer", "Into the Wild");
    lib1.add(9780446580342L, "David Baldacci", "Simple Genius");

    String patron1 = "Jane Doe";

    if (!lib1.checkout(9780330351690L, patron1, 1, 1, 2008))
      System.err.println("TEST FAILED: first checkout");
    if (!lib1.checkout(9780374292799L, patron1, 1, 1, 2008))
      System.err.println("TEST FAILED: second checkout");
    ArrayList<LibraryBookGeneric<String>> booksCheckedOut1 = lib1
        .lookup(patron1);
    if (booksCheckedOut1 == null
        || booksCheckedOut1.size() != 2
        || !booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer",
            "Into the Wild"))
        || !booksCheckedOut1.contains(new Book(9780374292799L,
            "Thomas L. Friedman", "The World is Flat"))
        || !booksCheckedOut1.get(0).getHolder().equals(patron1)
        || !booksCheckedOut1.get(0).getDueDate().equals(
            new GregorianCalendar(2008, 1, 1))
        || !booksCheckedOut1.get(1).getHolder().equals(patron1)
        || !booksCheckedOut1.get(1).getDueDate().equals(
            new GregorianCalendar(2008, 1, 1)))
      System.err.println("TEST FAILED: lookup(holder)");
    if (!lib1.checkin(patron1))
      System.err.println("TEST FAILED: checkin(holder)");

    // test a library that uses phone numbers (PhoneNumber) to id patrons
    LibraryGeneric<PhoneNumber> lib2 = new LibraryGeneric<PhoneNumber>();
    lib2.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
    lib2.add(9780330351690L, "Jon Krakauer", "Into the Wild");
    lib2.add(9780446580342L, "David Baldacci", "Simple Genius");

    PhoneNumber patron2 = new PhoneNumber("801.555.1234");

    if (!lib2.checkout(9780330351690L, patron2, 1, 1, 2008))
      System.err.println("TEST FAILED: first checkout");
    if (!lib2.checkout(9780374292799L, patron2, 1, 1, 2008))  
      System.err.println("TEST FAILED: second checkout");
    ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut2 = lib2
        .lookup(patron2);
    if (booksCheckedOut2 == null
        || booksCheckedOut2.size() != 2
        || !booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer",
            "Into the Wild"))
        || !booksCheckedOut2.contains(new Book(9780374292799L,
            "Thomas L. Friedman", "The World is Flat"))
        || !booksCheckedOut2.get(0).getHolder().equals(patron2)
        || !booksCheckedOut2.get(0).getDueDate().equals(
            new GregorianCalendar(2008, 1, 1))
        || !booksCheckedOut2.get(1).getHolder().equals(patron2)
        || !booksCheckedOut2.get(1).getDueDate().equals(
            new GregorianCalendar(2008, 1, 1)))
      System.err.println("TEST FAILED: lookup(holder)");
    if (!lib2.checkin(patron2))                           
      System.err.println("TEST FAILED: checkin(holder)");
    
	// test a medium library with holder strings
    lib1.addAll("Mushroom_Publishing.txt");

	if (lib1.lookup(9781843190028L) != null)
		System.err.println("TEST FAILED -- medium library (string): lookup(isbn)");
	if (!lib1.checkout(9781843190028L, "Jane Doe", 1, 1, 2008))
		System.err.println("TEST FAILED -- medium library (string): checkout");
	if (!lib1.checkout(9781843190042L, "Jane Doe", 12, 30, 2016))
		System.err.println("TEST FAILED -- medium library (string): checkout");
	if (!lib1.checkout(9781843193319L, "Jane Doe", 12, 15, 2016))
		System.err.println("TEST FAILED -- medium library (string): checkout");
	if (!lib1.checkout(9781843190936L, "John Doe", 1, 2, 2003))
		System.err.println("TEST FAILED -- medium library (string): checkout");
	if (lib1.checkout(9781843190028L, "John Doe", 1, 2, 2003))
		System.err.println("TEST FAILED -- medium library (string): checkout");
	booksCheckedOut1 = lib1.lookup("Jane Doe");
	if (booksCheckedOut1 == null || booksCheckedOut1.size() != 3
			|| !booksCheckedOut1.get(0).equals(new Book(9781843190028L, "Moyra Caldecott", "Crystal Legends"))
			|| !booksCheckedOut1.get(0).getHolder().equals("Jane Doe")
			|| !booksCheckedOut1.get(0).getDueDate().equals(new GregorianCalendar(2008, 1, 1))
			|| !booksCheckedOut1.get(1).equals(new Book(9781843190042L, "Martyn Folkes", "Bath City Centre Street Map and Guide"))
			|| !booksCheckedOut1.get(1).getHolder().equals("Jane Doe")
			|| !booksCheckedOut1.get(1).getDueDate().equals(new GregorianCalendar(2016, 12, 30))
			|| !booksCheckedOut1.get(2).equals(new Book(9781843193319L, "Alan Burt Akers", "Transit to Scorpio"))
			|| !booksCheckedOut1.get(2).getHolder().equals("Jane Doe")
			|| !booksCheckedOut1.get(2).getDueDate().equals(new GregorianCalendar(2016, 12, 15)))
		System.err.println("TEST FAILED -- medium library (string): lookup(holder)");
	if (!lib1.checkin(9781843190028L))
		System.err.println("TEST FAILED -- medium library (string): checkin(isbn)");
	if (!lib1.checkin("Jane Doe"))
		System.err.println("TEST FAILED -- medium library (string): checkin(holder)");
	if (lib1.checkin("Jane Doe"))
		System.err.println("TEST FAILED -- medium library (string): checkin(holder)");
	
	// test a medium library with holder phone numbers
	lib2 = new LibraryGeneric<PhoneNumber>();
    lib2.addAll("Mushroom_Publishing.txt");
    
    PhoneNumber patron3 = new PhoneNumber("801.555.1234");
    PhoneNumber patron4 = new PhoneNumber("801.222.7654");
    
	if (lib2.lookup(9781843190028L) != null)
		System.err.println("TEST FAILED -- medium library (phones): lookup(isbn)");
	if (!lib2.checkout(9781843190028L, patron3, 1, 1, 2008))
		System.err.println("TEST FAILED -- medium library (phones): checkout");
	if (!lib2.checkout(9781843190042L, patron3, 12, 30, 2016))
		System.err.println("TEST FAILED -- medium library (phones): checkout");
	if (!lib2.checkout(9781843193319L, patron3, 12, 15, 2016))
		System.err.println("TEST FAILED -- medium library (phones): checkout");
	if (!lib2.checkout(9781843190936L, patron4, 1, 2, 2003))
		System.err.println("TEST FAILED -- medium library (phones): checkout");
	if (lib2.checkout(9781843190028L, patron4, 1, 2, 2003))
		System.err.println("TEST FAILED -- medium library (phones): checkout");
	booksCheckedOut2 = lib2.lookup(patron3);
	if (booksCheckedOut2 == null || booksCheckedOut2.size() != 3
			|| !booksCheckedOut2.get(0).equals(new Book(9781843190028L, "Moyra Caldecott", "Crystal Legends"))
			|| !booksCheckedOut2.get(0).getHolder().equals(patron3)
			|| !booksCheckedOut2.get(0).getDueDate().equals(new GregorianCalendar(2008, 1, 1))
			|| !booksCheckedOut2.get(1).equals(new Book(9781843190042L, "Martyn Folkes", "Bath City Centre Street Map and Guide"))
			|| !booksCheckedOut2.get(1).getHolder().equals(patron3)
			|| !booksCheckedOut2.get(1).getDueDate().equals(new GregorianCalendar(2016, 12, 30))
			|| !booksCheckedOut2.get(2).equals(new Book(9781843193319L, "Alan Burt Akers", "Transit to Scorpio"))
			|| !booksCheckedOut2.get(2).getHolder().equals(patron3)
			|| !booksCheckedOut2.get(2).getDueDate().equals(new GregorianCalendar(2016, 12, 15)))
		System.err.println("TEST FAILED -- medium library (phones): lookup(holder)");
	if (!lib2.checkin(9781843190028L))
		System.err.println("TEST FAILED -- medium library (phones): checkin(isbn)");
	if (!lib2.checkin(patron3))
		System.err.println("TEST FAILED -- medium library (phones): checkin(holder)");
	if (lib2.checkin(patron3))
		System.err.println("TEST FAILED -- medium library (phones): checkin(holder)");
    
    
	// test a medium library sorting by ISBN
    LibraryGeneric<String> libPreSorted = new LibraryGeneric<String>();
    LibraryGeneric<String> libUnsorted = new LibraryGeneric<String>();
    LibraryGeneric<String> libSorted = new LibraryGeneric<String>();
    libPreSorted.addAll("Mushroom_Publishing.txt");
    libUnsorted.addAll("Mushroom_Publishing_unsorted.txt");

    libSorted.addAll(libUnsorted.getInventoryList());
    
    ArrayList<LibraryBookGeneric<String>> booksPreSorted = libPreSorted.lookup(null);
    ArrayList<LibraryBookGeneric<String>> booksUnSorted = libUnsorted.lookup(null);
    ArrayList<LibraryBookGeneric<String>> booksSorted = libSorted.lookup(null);

    // first check that we can really detect an unsorted array
    boolean unsorted = false;
    for (int i = 0; i < booksPreSorted.size(); i++) {
    	if (!booksPreSorted.get(i).equals(booksUnSorted.get(i))) {
    		unsorted = true;
    		break;
    	}
    }
	if (unsorted == false)
		System.err.println("TEST FAILED -- medium library: getInventoryList()");
    
	// now check if the sorted array was really sorted like the pre-sorted one.
    for (int i = 0; i < booksPreSorted.size(); i++) {
    	if (!booksPreSorted.get(i).equals(booksSorted.get(i))) {
    		System.err.println("TEST FAILED -- medium library: getInventoryList()");
    		break;
    	}
    }
    
	// test a medium library sorting by author
    libPreSorted = new LibraryGeneric<String>();
    libUnsorted = new LibraryGeneric<String>();
    libSorted = new LibraryGeneric<String>();
    libPreSorted.addAll("Mushroom_Publishing_sorted_author.txt");
    libUnsorted.addAll("Mushroom_Publishing_unsorted.txt");

    libSorted.addAll(libUnsorted.getOrderedByAuthor());
    
    booksPreSorted = libPreSorted.lookup(null);
    booksUnSorted = libUnsorted.lookup(null);
    booksSorted = libSorted.lookup(null);

    // first check that we can really detect an unsorted array
    unsorted = false;
    for (int i = 0; i < booksPreSorted.size(); i++) {
    	if (!booksPreSorted.get(i).equals(booksUnSorted.get(i))) {
    		unsorted = true;
    		break;
    	}
    }
	if (unsorted == false)
		System.err.println("TEST FAILED -- medium library: getOrderedByAuthor()");
    
	// now check if the sorted array was really sorted like the pre-sorted one.
    for (int i = 0; i < booksPreSorted.size(); i++) {
    	if (!booksPreSorted.get(i).equals(booksSorted.get(i))) {
    		System.err.println("TEST FAILED -- medium library: getOrderedByAuthor()");
    		break;
    	}
    }
	
	// test a medium library sorting by due date
    LibraryGeneric<PhoneNumber> lib = new LibraryGeneric<PhoneNumber>();

    lib.addAll("Mushroom_Publishing_unsorted.txt");

    PhoneNumber patron5 = new PhoneNumber("801.123.4567");
    PhoneNumber patron6 = new PhoneNumber("801.543.9876");
    PhoneNumber patron7 = new PhoneNumber("801.669.9595");
    
    // simulate some random check outs
	if (!lib.checkout(9781843190004L, patron5, 5, 8, 2016))
		System.err.println("TEST FAILED -- medium library: checkout");
	if (!lib.checkout(9781843192022L, patron5, 2, 17, 2018))
		System.err.println("TEST FAILED -- medium library: checkout");
	if (!lib.checkout(9781843190677L, patron5, 7, 23, 2013))
		System.err.println("TEST FAILED -- medium library: checkout");
	if (!lib.checkout(9781843190516L, patron6, 1, 2, 2017))
		System.err.println("TEST FAILED -- medium library: checkout");
	if (!lib.checkout(9781843190875L, patron6, 12, 1, 2012))
		System.err.println("TEST FAILED -- medium library: checkout");
	if (!lib.checkout(9781843190479L, patron6, 10, 13, 2007))
		System.err.println("TEST FAILED -- medium library: checkout");
	if (!lib.checkout(9781843190394L, patron7, 12, 30, 2016))
		System.err.println("TEST FAILED -- medium library: checkout");
	if (!lib.checkout(9781843192954L, patron7, 9, 9, 2000))
		System.err.println("TEST FAILED -- medium library: checkout");
	if (!lib.checkout(9781843191230L, patron7, 9, 9, 2000))
		System.err.println("TEST FAILED -- medium library: checkout");
	if (!lib.checkout(9781843190363L, patron5, 5, 30, 2010))
		System.err.println("TEST FAILED -- medium library: checkout");
    
	// select overdue books and sort them by date
    ArrayList<LibraryBookGeneric<PhoneNumber>> booksOverdue = lib.getOverdueList(1, 1, 2017);

    // make sure that we found the correct amount of overdue books
	if (booksOverdue == null || booksOverdue.size() != 8)
		System.err.println("TEST FAILED -- medium library: getOverdueList()");
		
	// make sure that the books are ordered from the oldest to the newest
    for (int i = 1; i < booksOverdue.size(); i++) {
    	if (booksOverdue.get(i).getDueDate().compareTo(booksOverdue.get(i-1).getDueDate()) < 0) {
    		System.err.println("TEST FAILED -- medium library: getOverdueList()");
    		break;
    	}
    }
    
    System.out.println("Testing done.");
  }
}
