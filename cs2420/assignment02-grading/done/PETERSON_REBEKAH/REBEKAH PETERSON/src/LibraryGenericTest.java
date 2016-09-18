package assignment02;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Testing class for LibraryGeneric.
 * 
 * @author Rebekah Peterson, last updated 9/7/16
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
    
    LibraryGeneric<PhoneNumber> lib3 = new LibraryGeneric<PhoneNumber>();
    lib3.addAll("Mushroom_Publishing.txt");
    
    PhoneNumber patron03, patron05, patron07, patron11;
    
    patron03 = new PhoneNumber("801.845.1134");
    patron05 = new PhoneNumber("801.145.1234");
    patron07 = new PhoneNumber("801.841.1134");
    patron11 = new PhoneNumber("801.845.1034");
        
    lib3.checkout(9781843193319L, patron07, 1, 2, 2015);
    lib3.checkout(9781843190677L, patron07, 1, 2, 2015);
    lib3.checkout(9781843190998L, patron07, 1, 2, 2015);
    lib3.checkout(9781843190004L, patron03, 7, 2, 2015);
    lib3.checkout(9781843190042L, patron03, 7, 2, 2015);
    lib3.checkout(9781843190400L, patron05, 7, 2, 2015);
    lib3.checkout(9781843190875L, patron03, 7, 2, 2015);
    lib3.checkout(9781843192039L, patron11, 7, 2, 2014);
    
    ArrayList<LibraryBookGeneric<PhoneNumber>> inventory = lib3.getInventoryList();
    System.out.println("inventory: ");
    for (LibraryBookGeneric<PhoneNumber> libBook: inventory) {
    	System.out.println(libBook);
    }
    
    ArrayList<LibraryBookGeneric<PhoneNumber>> listByAuthor = lib3.getOrderedByAuthor();
    System.out.println("inventory by author: ");
    for (LibraryBookGeneric<PhoneNumber> libBook: listByAuthor) {
    	System.out.println(libBook);
    }
    
    ArrayList<LibraryBookGeneric<PhoneNumber>> overDue = lib3.getOverdueList(6, 17, 2015);
    System.out.println("overdue books: ");
    for (LibraryBookGeneric<PhoneNumber> libBook: overDue) {
    	System.out.println(libBook);
    }
    
    System.out.println("Testing done.");
    
  }
}
