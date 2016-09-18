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
    
    // Testing the medium sized library
    LibraryGeneric<String> lib3 = new LibraryGeneric<String>();
    lib3.addAll("Mushroom_Publishing.txt");
    String patron3 = "Larry";
    lib3.checkout(9781843190004L, patron3, 6, 5, 2016);
	lib3.checkout(9781843193319L, patron3, 6, 5, 2016);
	lib3.checkout(9781843192701L, patron3, 6, 5, 2016);
	lib3.checkin(patron3);
	if ((lib3.lookup(patron3).equals(null))) {
		System.err.println("Test Failed: lookup(holder)");
	}

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
    
    LibraryGeneric<String> lib4 = new LibraryGeneric<String>();
    lib4.addAll("Mushroom_Publishing.txt");
    System.out.println(lib4.getInventoryList());
    System.out.println(lib4.getOrderedByAuthor());
    lib4.checkout(978184319004L, "Tom", 9, 1, 2016);
    lib4.checkout(978184319011L, "Tom", 9, 2, 2016);
    lib4.checkout(978184319028L, "Tom", 9, 3, 2016);
    lib4.checkout(978184319042L, "Tom", 9, 4, 2016);
    lib4.checkout(978184319073L, "Tom", 9, 5, 2016);
    lib4.checkout(978184319110L, "Tom", 9, 6, 2016);
    lib4.checkout(978184319349L, "Tom", 9, 7, 2016);
    lib4.checkout(978184319363L, "Tom", 9, 8, 2016);
    lib4.checkout(978184319394L, "Tom", 9, 9, 2016);
    lib4.checkout(978184319400L, "Tom", 9, 10, 2016);
    System.out.println(lib4.getOverdueList(9, 8, 2016));
    System.out.println("Testing done.");
  }
}
