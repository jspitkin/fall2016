/**
 * author: Dylan Northcutt
 * Uid: u1055102
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

   //test library using string names with a larger library
    lib1.addAll("Mushroom_Publishing.txt");
    String patron3 = "Deez Nutz";
    String patron4 = "Yur Mum";
    
    if (!lib1.checkout(9781843190004L, patron3, 1, 1, 2008))
        System.err.println("TEST FAILED: Large first checkout");
      if (!lib1.checkout(9780374292799L, patron3, 1, 1, 2008) 
    		|| !lib1.checkout(  9781843190042L, patron3, 1, 1, 2008)
    		||!lib1.checkout( 9781843190363L, patron3, 1, 1, 2008))
        System.err.println("TEST FAILED: Large multi checkout");
      if (lib1.checkout(9780374292799L, patron4, 1, 1, 2008))
    	  System.err.println("TEST FAILED: Large checkout already checked out");
      if (!lib1.checkin(9780374292799L))
    	  System.err.println("TEST FAILED: Large single checkin");
      ArrayList<LibraryBookGeneric<String>> booksCheckedOut3= lib1.lookup(patron3);
		if (booksCheckedOut3 == null  
				||booksCheckedOut3.size() != 3
				|| !booksCheckedOut3.get(0).getHolder().equals(patron3)
				|| !booksCheckedOut3.get(1).getDueDate().equals(new GregorianCalendar(2008, 1, 1)))
			System.err.println("TEST FAILED: Large multi lookup");
		if (!lib1.checkin(patron3))
    	  System.err.println("TEST FAILED: Large multi checkin");
      if (lib1.checkin(patron3))
    	  System.err.println("TEST FAILED: Large checkin empty");
      
    		  
    		  
    		  
    
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
    
    //test library using phone numbers with a larger library
    lib2.addAll("Mushroom_Publishing.txt");
    PhoneNumber patron5 = new PhoneNumber ("180.069.1236");
    PhoneNumber patron6 = new PhoneNumber ("123.456.7805");
    
    if (!lib2.checkout(9781843190004L, patron5, 1, 1, 2008))
        System.err.println("TEST FAILED: Large first checkout");
      if (!lib2.checkout(9780374292799L, patron5, 1, 1, 2008) 
    		|| !lib2.checkout(  9781843190042L, patron5, 1, 1, 2008)
    		||!lib2.checkout( 9781843190363L, patron5, 1, 1, 2008))
        System.err.println("TEST FAILED: Large multi checkout");
      if (lib2.checkout(9780374292799L, patron6, 1, 1, 2008))
    	  System.err.println("TEST FAILED: Large checkout already checked out");
      if (!lib2.checkin(9780374292799L))
    	  System.err.println("TEST FAILED: Large single checkin");
      ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut4= lib2.lookup(patron5);
		if (booksCheckedOut4 == null  
				||booksCheckedOut4.size() != 3
				|| !booksCheckedOut4.get(0).getHolder().equals(patron5)
				|| !booksCheckedOut4.get(1).getDueDate().equals(new GregorianCalendar(2008, 1, 1)))
			System.err.println("TEST FAILED: Large multi lookup");
		if (!lib2.checkin(patron5))
    	  System.err.println("TEST FAILED: Large multi checkin");
      if (lib2.checkin(patron5))
    	  System.err.println("TEST FAILED: Large checkin empty");
      
      LibraryGeneric<String> lib3 = new LibraryGeneric<String>();
      
      
      lib3.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
      lib3.add(9780330351690L, "Jon Krakauer", "Into the Wild");
      lib3.add(9780446580342L, "David Baldacci", "Simple Genius");
    
      if (!lib3.checkout(9780374292799L, "Steve", 1, 1, 2000) 
      		|| !lib3.checkout(9780330351690L, "Steve", 1, 2, 2000)
      		||!lib3.checkout( 9780446580342L, "Steve", 1, 3, 2000))
    	  System.err.println("TEST FAILED: Large multi checkout 2");
    
    ArrayList<LibraryBookGeneric<String>> overdue = lib3.getOverdueList(6, 5, 2001);
   
     if(overdue.get(0).getIsbn() != 9780374292799L)
    	  System.err.println("TEST FAILED: get overdue list");
    
     ArrayList<LibraryBookGeneric<String>> list = lib3.getInventoryList();
     if(list.get(0).getIsbn() !=9780330351690L)
    	 System.err.println("TEST FAILED: get inventory list");
     
     list = lib3.getOrderedByAuthor();
     if(list.get(0).getAuthor() != "David Baldacci")
    	 System.err.println("TEST FAILED: get author sort list");
   
     System.out.println("Testing done.");
  }
}
