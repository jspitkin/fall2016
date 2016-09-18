package assignment02;

//Alessandro Ljubicic
//u0753409

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
    
    //Sorting tests for Mushroom Publishing txt
    
    LibraryGeneric<String> mushroomPublishingList = new LibraryGeneric<String>();
    
    mushroomPublishingList.addAll("Mushroom_Publishing.txt");
    
    mushroomPublishingList.getInventoryList();
    
    if(mushroomPublishingList.isbnAtFirstPosition() != 9781843190004L) {
    	System.err.println("TEST FAILED: getInventoryList");
    }
    
    mushroomPublishingList.getOrderedByAuthor();
    
    if(!(mushroomPublishingList.authorAtFirstPosition().equals("Alan Burt Akers"))) {
    	System.err.println("TEST FAILED: getOrderedByAuthor");
    }
    if(!(mushroomPublishingList.authorAtLastPosition().equals("William Fitzmaurice"))) {
    	System.err.println("TEST FAILED: getOrderedByAuthor");
    }
    
    mushroomPublishingList.checkout(9781843190004L, "Alex L", 8, 1, 2016);
    mushroomPublishingList.checkout(9781843193319L, "Alex L", 8, 2, 2016);
    mushroomPublishingList.checkout(9781843190011L, "Alex L", 8, 3, 2016);
    mushroomPublishingList.checkout(9781843192954L, "Alex L", 8, 4, 2016);
    mushroomPublishingList.checkout(9781843190028L, "Ben B", 8, 5, 2016);
    mushroomPublishingList.checkout(9781843192701L, "Ben B", 8, 6, 2016);
    mushroomPublishingList.checkout(9781843190042L, "Ben B", 8, 7, 2016);
    mushroomPublishingList.checkout(9781843192039L, "Ben B", 8, 8, 2016);
    mushroomPublishingList.checkout(9781843190073L, "Drew G", 8, 9, 2016);
    mushroomPublishingList.checkout(9781843192022L, "Drew G", 8, 10, 2016);
    mushroomPublishingList.checkout(9781843190110L, "Drew G", 8, 11, 2016);
    mushroomPublishingList.checkout(9781843191230L, "Drew G", 8, 12, 2016);
    
    mushroomPublishingList.getOverdueList(7, 22, 2016);
    
    GregorianCalendar firstDate = new GregorianCalendar(2016, 8, 12);
    
    if(!(mushroomPublishingList.dateAtFirstPosition().equals(firstDate))) {
    	System.err.println("TEST FAILED: getOverdueList");
    }
    
    GregorianCalendar lastDate = new GregorianCalendar(2016, 8, 1);
    
    if(!(mushroomPublishingList.dateAtLastPosition().equals(lastDate))) { 
    	System.err.println("TEST FAILED: getOverdueList");
    }
    
    System.out.println("Testing done.");
  }
}
