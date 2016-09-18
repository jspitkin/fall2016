/**
 * @author Pingchuan Ma
 * @uid u0805309
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
    lib1.add(9780000000000L, "David Baldacci", "Test");

    String patron1 = "Jane Doe";

    if (!lib1.checkout(9780330351690L, patron1, 1, 1, 2008))
      System.err.println("TEST FAILED: first checkout");
    if (!lib1.checkout(9780374292799L, patron1, 1, 1, 2011))
      System.err.println("TEST FAILED: second checkout");
    ArrayList<LibraryBookGeneric<String>> booksCheckedOut1 = lib1.lookup(patron1);
    if (booksCheckedOut1 == null
        || booksCheckedOut1.size() != 2
        || !booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer",
            "Into the Wild"))
        || !booksCheckedOut1.contains(new Book(9780374292799L,
            "Thomas L. Friedman", "The World is Flat"))
        || !booksCheckedOut1.get(0).getHolder().equals(patron1)
        || booksCheckedOut1.get(0).getDueDate().compareTo(
            new GregorianCalendar(2011, 1, 1))!=0
        //先借出去的是get(1),后是get(0)
        || !booksCheckedOut1.get(1).getHolder().equals(patron1)
        || booksCheckedOut1.get(1).getDueDate().compareTo(new GregorianCalendar(2008, 1, 1))!=0)
      System.err.println("TEST FAILED: lookup(holder)");
    if (!lib1.checkin(patron1))
      System.err.println("TEST FAILED: checkin(holder)");
    
    
      //test retrieving a list of library books sorted by ISBN 
	   
    String a="[9780000000000, David Baldacci, \"Test\", "
    		+     "9780330351690, Jon Krakauer, \"Into the Wild\", "
    		+ "9780374292799, Thomas L. Friedman, \"The World is Flat\", "
    		+ "9780446580342, David Baldacci, \"Simple Genius\"]";

    if(!lib1.getInventoryList().toString().equals(a))
    System.err.println("TEST FAILED:getInventoryList()");
    
 
      //test retrieving a list of library books sorted by author
	   
         //记得[要带上，记得lib1.getOrderedByAuthor().toString()带上.toString
    String b="[9780446580342, David Baldacci, \"Simple Genius\", "
    		+ "9780000000000, David Baldacci, \"Test\", "
    		+ "9780330351690, Jon Krakauer, \"Into the Wild\", "
    		+ "9780374292799, Thomas L. Friedman, \"The World is Flat\"]";
    if(!lib1.getOrderedByAuthor().toString().equals(b))
        System.err.println("TEST FAILED:getOrderedByAuthor()");
    
    
	  //test retrieving a list of library books sorted by duedate
	   
    lib1.checkout(9780330351690L, patron1, 1, 1, 2008);
    lib1.checkout(9780374292799L, patron1, 1, 1, 2011);
    lib1.checkout(9780446580342L, patron1, 1, 1, 2010);
    String c="[9780330351690, Jon Krakauer, \"Into the Wild\", "
    		+ "9780446580342, David Baldacci, \"Simple Genius\", "
    		+ "9780374292799, Thomas L. Friedman, \"The World is Flat\"]";
    
    if(!lib1.getOverdueList(1, 1, 2012).toString().equals(c))
    System.err.println("TEST FAILED:getOrderedByDuedate()");

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
        || booksCheckedOut2.get(0).getDueDate().compareTo(
            new GregorianCalendar(2008, 1, 1))!=0
        || !booksCheckedOut2.get(1).getHolder().equals(patron2)
        || booksCheckedOut2.get(1).getDueDate().compareTo(
            new GregorianCalendar(2008, 1, 1))!=0)
      System.err.println("TEST FAILED: lookup(holder)");
    if (!lib2.checkin(patron2))  
      System.err.println("TEST FAILED: checkin(holder)");
    
    System.out.println("Testing done.");

}
}
