/**
 * Assignment 2
 * Name: Dallin Van Mondfrans
 * uID: u0717113 
 * Date: September 7, 2016
 */

package assignment02;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Testing class for LibraryGeneric.
 *
 */
public class LibraryGenericTest {

  public static void main(String[] args) {

    // test a library that uses names (String) to id patrons ---------------------------------
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
            "Into the Wild")) // false
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

    // test a library that uses phone numbers (PhoneNumber) to id patrons ------------------------
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
    
    // Test a medium sized library that uses names (Strings) to id patrons. --------------------------
    LibraryGeneric<String> mediumLib = new LibraryGeneric<String>();
    mediumLib.addAll("Mushroom_Publishing.txt");
    	
    String patron3 = "Dallin Van";
    
    if( !mediumLib.checkout(9781843192701L, patron3, 8, 5, 2016) ) {
    	System.err.println("TEST FAILED: first checkout");
    }
    if( !mediumLib.checkout(9781843192954L, patron3, 8, 7, 2016) ) {
    	System.err.println("TEST FAILED: second checkout");
    }
    if( !mediumLib.checkout(9781843193319L, patron3, 8, 4, 2016) ) {
    	System.err.println("TEST FAILED: third checkout");
    }
    ArrayList<LibraryBookGeneric<String>> booksCheckedOut3 = mediumLib.lookup(patron3);
    if( booksCheckedOut3 == null || booksCheckedOut3.size() != 3
    	|| !booksCheckedOut3.contains(new Book(9781843192701L, "Moyra Caldecott", "The Lily and the Bull")) 
    	|| !booksCheckedOut3.contains(new Book(9781843192954L, "Dennis Radha-Rose", "The Anxiety Relief Program"))
    	|| !booksCheckedOut3.contains(new Book(9781843193319L, "Alan Burt Akers", "Transit to Scorpio")) 
    	|| !booksCheckedOut3.get(0).getHolder().equals(patron3)
    	|| !booksCheckedOut3.get(2).getDueDate().equals(new GregorianCalendar(2016, 8, 4)) ) {
    	System.err.println("TEST FAILED: medium library: lookup(holder)");
    }
    if( !mediumLib.checkin(9781843192701L) ) {
    	System.err.println("TEST FAILED: medium library: checkin(isbn)");
    }
    if( !mediumLib.checkin(patron3) ) {
    	System.err.println("TEST FAILED: medium library: checkin(holder)");
    }
    
    
    // Test the added features systematically  --------------------------------------------------------
    
    // Feature 1 : retrieving a list of library books sorted by ISBN
    LibraryGeneric<String> lib3 = new LibraryGeneric<String>();
    lib3.addAll("Mushroom_Publishing.txt");
    ArrayList<LibraryBookGeneric<String>> lib3SortedByISBN = lib3.getInventoryList();
    
   long smallerISBN = lib3SortedByISBN.get(0).getIsbn();
   for( int index = 1; index < lib3SortedByISBN.size(); index++ ) {
	   if( lib3SortedByISBN.get(index).getIsbn() < smallerISBN ) {
		   System.err.println("TEST FAILED -- Feature 1: getInventoryList()");
	   }
	   smallerISBN = lib3SortedByISBN.get(index).getIsbn();
   }
    
    // Feature 2 : retrieving a list of library books sorted by author
   ArrayList<LibraryBookGeneric<String>> lib3SortedByAuthor = lib3.getOrderedByAuthor();
   
   for( int index = 1; index < lib3SortedByAuthor.size(); index++ ) {
	   String previousAuthor = lib3SortedByAuthor.get(index-1).getAuthor();
	   String currentAuthor = lib3SortedByAuthor.get(index).getAuthor();
	   if( previousAuthor.equals(currentAuthor) ) {
		   String previousTitle = lib3SortedByAuthor.get(index-1).getTitle();
		   String currentTitle = lib3SortedByAuthor.get(index).getTitle();
		   if( currentTitle.compareTo(previousTitle) < 0 ) { // If the order is wrong, then print an error
			   System.err.println("TEST FAILED -- Feature 2: getORderedByAuthor()");
		   }
	   } else {
		   if( currentAuthor.compareTo(previousAuthor) < 0 ) { // If the order is wrong, then print an error
			   System.err.println("TEST FAILED -- Feature 2: getORderedByAuthor()");
		   }
	   }
   }
   
   
    // Feature 3 : retrieving a list of overdue library books sorted by date (oldest first)
   
   lib3.checkout(9781843190004L, patron3, 9, 5, 2016);
   lib3.checkout(9781843190011L, patron3, 9, 7, 2016);
   lib3.checkout(9781843190028L, patron1, 9, 6, 2016);
   
   ArrayList<LibraryBookGeneric<String>> overdueList = lib3.getOverdueList(9, 6, 2016);
   if( overdueList.size() != 1 ) {
   	System.err.println("TEST FAILED: getOverdue() with 1 element");
   }
   ArrayList<LibraryBookGeneric<String>> overdueList2 = lib3.getOverdueList(9, 8, 2016);
   if( overdueList2.size() != 3 ) {
   	System.err.println("TEST FAILED: getOverdue() with 3 elements");
   }
   if(     (overdueList2.size() != 3)
	   || !(overdueList2.get(0).equals(new LibraryBookGeneric<String>( 9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound") )  )
	   || !(overdueList2.get(1).equals(new LibraryBookGeneric<String>( 9781843190028L, "Moyra Caldecott", "Crystal Legends") )           ) 
	   || !(overdueList2.get(0).getDueDate().equals(new GregorianCalendar(2016, 9, 5))) 
	   || !(overdueList2.get(2).getDueDate().equals(new GregorianCalendar(2016, 9, 7))) ) {
   	System.err.println("TEST FAILED: orderByDueDate()");
   }
   
   // --------------------------------------------------------------------------------------
   System.out.println("Testing done.");
  }
  
}
