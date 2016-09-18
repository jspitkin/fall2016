//Benjamin Allred
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
    
    //tests a library that use library cards (LibraryCard)
    LibraryGeneric<LibraryCard> lib3 = new LibraryGeneric<LibraryCard>();
    lib3.addAll("Mushroom_Publishing.txt");
    LibraryCard patron3 = new LibraryCard(23055006487516L);
    
    if (!lib3.checkout(9781843190400L, patron3, 9, 1, 2016))
        System.err.println("TEST FAILED: first checkout");
    if (!lib3.checkout(9781843190677L, patron3, 9, 1, 2016))  
        System.err.println("TEST FAILED: second checkout");
    if (!lib3.checkout(9781843190936L, patron3, 9, 1, 2016))  
        System.err.println("TEST FAILED: third checkout");
    ArrayList<LibraryBookGeneric<LibraryCard>> booksCheckedOut3 = lib3.lookup(patron3);
    if (booksCheckedOut3 == null
          || booksCheckedOut3.size() != 3
          || !booksCheckedOut3.contains(new Book(9781843190400L, "Jean Fanelli", "The War Comes to Witham Street"))
          || !booksCheckedOut3.contains(new Book(9781843190677L, "Cheryl Jones", "Herbs for Healthy Skin"))
          || !booksCheckedOut3.contains(new Book(9781843190936L, "Carol E. Meacham", "Machina Obscura"))
          || !booksCheckedOut3.get(0).getHolder().equals(patron3)
          || !booksCheckedOut3.get(0).getDueDate().equals(new GregorianCalendar(2016, 9, 1))
          || !booksCheckedOut3.get(1).getHolder().equals(patron3)
          || !booksCheckedOut3.get(1).getDueDate().equals(new GregorianCalendar(2016, 9, 1))
          || !booksCheckedOut3.get(2).getHolder().equals(patron3)
          || !booksCheckedOut3.get(2).getDueDate().equals(new GregorianCalendar(2016, 9, 1)))
        System.err.println("TEST FAILED: lookup(holder)");
    if (!lib3.checkin(patron3))                           
        System.err.println("TEST FAILED: checkin(holder)");
    System.out.println("Testing done.");
    
    //create new library for inventory list sorting
    LibraryGeneric<LibraryCard> lib4 = new LibraryGeneric<LibraryCard>();
    lib4.add(9781843190479L, "Anthony J D Burns", "Demogorgon Rising");
    lib4.add(9781843190110L, "David Meade Betts", "Breaking the Gaze");
    lib4.add(9781843190769L, "Roger Taylor", "The Call of the Sword");
    lib4.add(9781843192022L, "Roger Taylor", "Whistler");
    
    LibraryCard patron4 = new LibraryCard(23055006487517L);
    if (!lib4.checkout(9781843190479L, patron4, 9, 1, 2016))  
        System.err.println("TEST FAILED: checkout");
    if (!lib4.checkout(9781843190110L, patron4, 8, 31, 2016))  
        System.err.println("TEST FAILED: checkout");
    if (!lib4.checkout(9781843190769L, patron4, 5, 26, 2000))  
        System.err.println("TEST FAILED: checkout");
    if (!lib4.checkout(9781843192022L, patron4, 9, 16, 2016))  
        System.err.println("TEST FAILED: checkout");
    
    //tests sort by ISBN option
    ArrayList<LibraryBookGeneric<LibraryCard>> inventory = lib4.getInventoryList();
    String inventoryList = "";
    for(LibraryBookGeneric<LibraryCard> book : inventory)
    {
    	inventoryList += book.toString();
    }
    if(!inventoryList.equals("9781843190110, David Meade Betts, \"Breaking the Gaze\""
    		+ "9781843190479, Anthony J D Burns, \"Demogorgon Rising\""
    		+ "9781843190769, Roger Taylor, \"The Call of the Sword\""
    		+ "9781843192022, Roger Taylor, \"Whistler\""))
    	System.err.println("TEST FAILED: getInventoryList");
    
    //tests sort by Author option
    inventory = lib4.getOrderedByAuthor();
    inventoryList = "";
    for(LibraryBookGeneric<LibraryCard> book : inventory)
    {
    	inventoryList += book.toString();
    }
    if(!inventoryList.equals("9781843190479, Anthony J D Burns, \"Demogorgon Rising\""
    		+ "9781843190110, David Meade Betts, \"Breaking the Gaze\""
    		+ "9781843190769, Roger Taylor, \"The Call of the Sword\""
    		+ "9781843192022, Roger Taylor, \"Whistler\""))
    	System.err.println("TEST FAILED: getOrderedByAuthor");
    
    //tests sort by overdue list option
    inventory = lib4.getOverdueList(9, 2, 2016);
    inventoryList = "";
    for(LibraryBookGeneric<LibraryCard> book : inventory)
    {
    	inventoryList += book.toString();
    }
    if(!inventoryList.equals("9781843190769, Roger Taylor, \"The Call of the Sword\""
    		+ "9781843190110, David Meade Betts, \"Breaking the Gaze\""
    		+ "9781843190479, Anthony J D Burns, \"Demogorgon Rising\""))
    	System.err.println("TEST FAILED: getOverdueList");
  }
}