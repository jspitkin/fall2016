package assignment02;
//Gabe Brodbeck u0847035
import java.util.ArrayList;
import java.util.GregorianCalendar;

//This tester is very ugly so I have created a class called LibraryGenericJUnitTest class to handle testing instead
// I am only including this class because I am uncertain if my assignment can be properly graded without it



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
    
    // test a library using the sorting by Isbn Feature
    LibraryGeneric<String> libraryToBeSortedByIsbn = new LibraryGeneric<String>();
    libraryToBeSortedByIsbn.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
    libraryToBeSortedByIsbn.add(9780330351690L, "Jon Krakauer", "Into the Wild");
    libraryToBeSortedByIsbn.add(9780446580342L, "David Baldacci", "Simple Genius");
    ArrayList<LibraryBookGeneric<String>> lib3SortedByISBN= libraryToBeSortedByIsbn.getInventoryList();
    if(lib3SortedByISBN.get(0).getIsbn()!=9780330351690L){
    	System.err.println("TEST FAILED: getInverntoryLIst() is not returning a list in the proper order");
    }
    if(lib3SortedByISBN.get(1).getIsbn()!=9780374292799L){
    	System.err.println("TEST FAILED: getInverntoryLIst() is not returning a list in the proper order");
    }
    if(lib3SortedByISBN.get(2).getIsbn()!=9780446580342L){
    	System.err.println("TEST FAILED: getInverntoryLIst() is not returning a list in the proper order");
    }
    // test a library using the sorting by author feature
    LibraryGeneric<String> libraryToBeSortedByAuthor = new LibraryGeneric<String>();
    libraryToBeSortedByAuthor.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
    libraryToBeSortedByAuthor.add(9780330351690L, "Jon Krakauer", "Into the Wild");
    libraryToBeSortedByAuthor.add(9780446580342L, "David Baldacci", "Simple Genius");
    libraryToBeSortedByAuthor.add(9780446580343L, "David Baldacci", "Mulligan Wizard");
    ArrayList<LibraryBookGeneric<String>> lib4SortedByAuthor= libraryToBeSortedByAuthor.getOrderedByAuthor();
    if((!lib4SortedByAuthor.get(0).getAuthor().equals("David Baldacci"))&&(!lib4SortedByAuthor.get(0).getTitle().equals("Mulligan Wizard"))){
    	System.err.println("TEST FAILED: getInverntoryLIst() is not returning a list in the proper order. Author sort is not working properly");
    }
    // checks to make sure that in cases of the same author, the list properly sorts by title
    if(!lib4SortedByAuthor.get(0).getTitle().equals("Mulligan Wizard")){
    	System.err.println("TEST FAILED: getInverntoryLIst() is not returning a list in the proper order. Title sort is not working properly");
    }
    if(!lib4SortedByAuthor.get(1).getAuthor().equals("David Baldacci")){
    	System.err.println("TEST FAILED: getInverntoryLIst() is not returning a list in the proper order. Author sort is not working properly");
    }
    if(!lib4SortedByAuthor.get(2).getAuthor().equals("Jon Krakauer")){
    	System.err.println("TEST FAILED: getInverntoryLIst() is not returning a list in the proper order. Author sort is not working properly");
    }
    
    LibraryGeneric<String> libraryToBeCheckedForOverDue = new LibraryGeneric<String>();
    libraryToBeCheckedForOverDue.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
    libraryToBeCheckedForOverDue.add(9780330351690L, "Jon Krakauer", "Into the Wild");
    libraryToBeCheckedForOverDue.add(9780446580342L, "David Baldacci", "Simple Genius");
    libraryToBeCheckedForOverDue.add(9780374292790L, "Lucas Stevens", "The Mulligan Wizard");
    libraryToBeCheckedForOverDue.add(9780374292212L, "Amelia Jahan", "The Adventures of Spidey Steve");
    
    libraryToBeCheckedForOverDue.checkout(9780374292790L, "Alicia Laurent", 11, 21, 2016);
    libraryToBeCheckedForOverDue.checkout(9780446580342L, "Viktor Vermin", 7, 12, 2015);
    libraryToBeCheckedForOverDue.checkout(9780374292799L, "Frank Fuerte", 9, 21, 2015);
    ArrayList<LibraryBookGeneric<String>> overDueBooks= new ArrayList<LibraryBookGeneric<String>>();
    overDueBooks= libraryToBeCheckedForOverDue.getOverdueList(9, 6, 2016);
    if(overDueBooks.size()!=2){
    	System.err.println("TEST FAILED: geOverDueList(month,day,year). Did not get the propper number of overdue books");
    }
    if(overDueBooks.get(0).getIsbn()!=9780446580342L){
    	 System.err.println("TEST FAILED: geOverDueList(month,day,year). Did not put overdue books in the right order");
    }
    System.out.println("Testing done.");
    
  }
}
