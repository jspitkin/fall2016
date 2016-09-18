package assignment02;
//Gabe Brodbeck u0847035
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.*;
// This is the proper tester for the LibraryGeneric class. The supplied tester was incredibly messy and made keeping track of what had and hadn't been tested exceptionally difficult
//This class requires the Mushroom_Publishing.txt file to test some of its methods.

public class LibraryGenericJUnitTest {
	static LibraryGeneric<String> libraryToBeSortedByIsbn = new LibraryGeneric<String>();
	static LibraryGeneric<String> libraryToBeSortedByAuthor = new LibraryGeneric<String>();
	 static LibraryGeneric<String> libraryWithBooksAlreadyCheckedOut = new LibraryGeneric<String>();
	 static LibraryGeneric<PhoneNumber> lib2 = new LibraryGeneric<PhoneNumber>();
	 static LibraryGeneric<String> lib= new LibraryGeneric<String>();
	 static LibraryGeneric<String> staysEmpty= new LibraryGeneric<String>();
	@BeforeClass
	public static void setUp(){
	   
	    libraryToBeSortedByIsbn.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
	    libraryToBeSortedByIsbn.add(9780330351690L, "Jon Krakauer", "Into the Wild");
	    libraryToBeSortedByIsbn.add(9780446580342L, "David Baldacci", "Simple Genius");
	    
	    libraryToBeSortedByAuthor.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
	    libraryToBeSortedByAuthor.add(9780330351690L, "Jon Krakauer", "Into the Wild");
	    libraryToBeSortedByAuthor.add(9780446580342L, "David Baldacci", "Simple Genius");
	    libraryToBeSortedByAuthor.add(9780446580343L, "David Baldacci", "Mulligan Wizard");
	    
	    libraryWithBooksAlreadyCheckedOut.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
	    libraryWithBooksAlreadyCheckedOut.add(9780330351690L, "Jon Krakauer", "Into the Wild");
	    libraryWithBooksAlreadyCheckedOut.add(9780446580342L, "David Baldacci", "Simple Genius");
	    libraryWithBooksAlreadyCheckedOut.add(9780374292790L, "Lucas Stevens", "The Mulligan Wizard");
	    libraryWithBooksAlreadyCheckedOut.add(9780374292212L, "Amelia Jahan", "The Adventures of Spidey Steve");
	    
	    libraryWithBooksAlreadyCheckedOut.checkout(9780374292790L, "Alicia Laurent", 11, 21, 2016);
	    libraryWithBooksAlreadyCheckedOut.checkout(9780446580342L, "Viktor Vermin", 7, 12, 2015);
	    libraryWithBooksAlreadyCheckedOut.checkout(9780374292799L, "Frank Fuerte", 9, 21, 2015);
	    lib2.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
	    lib2.add(9780330351690L, "Jon Krakauer", "Into the Wild");
	    lib2.add(9780446580342L, "David Baldacci", "Simple Genius");
	    lib.addAll("Mushroom_Publishing.txt");
	    
	}
	@Before
	public void resetLibraries(){
		libraryToBeSortedByAuthor.reset();
		libraryWithBooksAlreadyCheckedOut.reset();
		lib2.reset();
		lib.reset();
		staysEmpty.reset();
		 libraryWithBooksAlreadyCheckedOut.checkout(9780374292790L, "Alicia Laurent", 11, 21, 2016);
		    libraryWithBooksAlreadyCheckedOut.checkout(9780446580342L, "Viktor Vermin", 7, 12, 2015);
		    libraryWithBooksAlreadyCheckedOut.checkout(9780374292799L, "Frank Fuerte", 9, 21, 2015);
	}
	@Test
	public void testsWithAnEmptyLibrary(){
		Assert.assertFalse("Failure: checked a book out of an empty library",staysEmpty.checkout(9780446580342L, "Alicia Laurent", 5, 11, 2011));
		Assert.assertTrue("Failue: found books checked out by a holder in an empty library",staysEmpty.lookup("Alicia Laurent").size()==0);
		Assert.assertFalse("Failure: checked a book in to a library with no books",staysEmpty.checkin(9780446580342L));
		Assert.assertFalse("Failure: checked a book in to a library with no books",staysEmpty.checkin("Alicia Laurent"));
		ArrayList<LibraryBookGeneric<String>> libSortedByISBN= staysEmpty.getInventoryList();
		Assert.assertTrue("A sorted list of an empty libraries elements has returned not empty",libSortedByISBN.size()==0);
		 ArrayList<LibraryBookGeneric<String>> sortedByAuthor= staysEmpty.getOrderedByAuthor();
		 Assert.assertTrue("A sorted list of an empty libraries elements has returned not empty",sortedByAuthor.size()==0);
		 ArrayList<LibraryBookGeneric<String>> willBeInOrder= staysEmpty.getOverdueList(9, 7, 2016); 
		 Assert.assertTrue("A sorted list of an empty libraries elements has returned not empty",willBeInOrder.size()==0);
	}
	@Test
	public void lookUpHolderTestString(){
		Assert.assertTrue("Looked up by handle and found the wrong number of books. Expected :1 Found: "+
				libraryWithBooksAlreadyCheckedOut.lookup("Alicia Laurent").size(),libraryWithBooksAlreadyCheckedOut.lookup("Alicia Laurent").size()==1);
		libraryWithBooksAlreadyCheckedOut.checkout(9780374292212L, "Alicia Laurent", 11, 21, 2016);
		Assert.assertTrue("Looked up by handle and found the wrong number of books. Expected :2 Found: "+
				libraryWithBooksAlreadyCheckedOut.lookup("Alicia Laurent").size(),libraryWithBooksAlreadyCheckedOut.lookup("Alicia Laurent").size()==2);
		Assert.assertTrue("Did not find a book checked out by holder in the list returned by lookup(holder)",contains(libraryWithBooksAlreadyCheckedOut.lookup("Alicia Laurent"),9780374292212L));
		Assert.assertTrue("List of books checked out by a holder with no books checked out was not 0",libraryWithBooksAlreadyCheckedOut.lookup("Pricia Erde").size()==0);
	}
	
	@Test
	public void booksCanBeCheckedOutWithString(){
		Assert.assertTrue("Failed to check out a book when using a string",libraryToBeSortedByIsbn.checkout(9780330351690L, "Amelia Lucar", 1, 1, 2008));
		
	}
	@Test
	public void booksCanBeCheckedInWithStringIsbn(){
		Assert.assertTrue("Failed to check out a book when using a string",libraryToBeSortedByIsbn.checkout(9780330351690L, "Amelia Lucar", 1, 1, 2008));
		Assert.assertTrue("Failed to check in a book by isbn when using a string ",libraryToBeSortedByIsbn.checkin(9780330351690L));
	}
	@Test
	public void booksCanBeCheckedInWithStringHandle(){
		libraryToBeSortedByIsbn.checkout(9780330351690L, "Amelia Lucar", 1, 1, 2008);
		libraryToBeSortedByIsbn.checkout(9780446580342L, "Amelia Lucar", 1, 1, 2008);
		Assert.assertTrue("Failed to check in books by holder when using a string",libraryToBeSortedByIsbn.checkin("Amelia Lucar"));
		Assert.assertFalse("Books checked in by holder are not properly altered",libraryToBeSortedByIsbn.checkin("Amelia Lucar"));
	}
	@Test
	public void booksCanBeCheckedOutWithPhoneNumber(){
		 PhoneNumber patron2 = new PhoneNumber("801.555.1234");
		    Assert.assertTrue("Failed to check out a book when using a phone number",lib2.checkout(9780330351690L, patron2, 1, 1, 2008));
	}
	@Test 
	public void booksCanBeCheckedInIndividuallyPhoneNumber(){
		PhoneNumber patron2 = new PhoneNumber("801.555.1234");
		lib2.checkout(9780330351690L, patron2, 1, 1, 2008);
		Assert.assertTrue("Failed to check in a book when using a phone number",lib2.checkin(9780330351690L));
	
	}
	@Test
	public void testIsbnSort() {
		ArrayList<LibraryBookGeneric<String>> libSortedByISBN= libraryToBeSortedByIsbn.getInventoryList();
	    Assert.assertTrue("getInverntoryLIst() is not returning a list in the proper order. First result is wrong", libSortedByISBN.get(0).getIsbn()==9780330351690L);
	    Assert.assertTrue(" getInverntoryLIst() is not returning a list in the proper order. Second Result is wrong",libSortedByISBN.get(1).getIsbn()==9780374292799L);
	    Assert.assertTrue(" getInverntoryLIst() is not returning a list in the proper order. Third result is wrong",libSortedByISBN.get(2).getIsbn()==9780446580342L);
	    }
	@Test
	public void testAuthorSort(){
		 
		 
		    ArrayList<LibraryBookGeneric<String>> sortedByAuthor= libraryToBeSortedByAuthor.getOrderedByAuthor();
		   
		    Assert.assertTrue("First Author is placed incorrectly "+sortedByAuthor.get(0).getAuthor(),sortedByAuthor.get(0).getIsbn()==9780446580343L);
		    
		    	
		    
	}
	@Test
	public void testPhoneNumber(){
		
	    PhoneNumber patron2 = new PhoneNumber("801.555.1234");
	    Assert.assertTrue("Failed to check out a book when using a phone number",lib2.checkout(9780330351690L, patron2, 1, 1, 2008));
	    Assert.assertTrue("Failed to check out a book when using a phone number",lib2.checkout(9780374292799L, patron2, 1, 1, 2008));
	    ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut2 = lib2.lookup(patron2);
	    Assert.assertFalse("Books checked out is empty when it should not be",booksCheckedOut2 == null);
	    Assert.assertFalse("The number of books checked out is incorrect", booksCheckedOut2.size() != 2);
	     Assert.assertFalse("The list of books does not contain the proper books", !booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
	      Assert.assertFalse("The list of books does not contain the proper books", !booksCheckedOut2.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
	       Assert.assertFalse("The list of books is not registered to the correct holder",!booksCheckedOut2.get(0).getHolder().equals(patron2));
	       Assert.assertFalse("The date of the book checked is incorrect",!booksCheckedOut2.get(0).getDueDate().equals( new GregorianCalendar(2008, 1, 1)));
	       Assert.assertFalse(  !booksCheckedOut2.get(1).getHolder().equals(patron2));
	       Assert.assertFalse( !booksCheckedOut2.get(1).getDueDate().equals(new GregorianCalendar(2008, 1, 1)));
	       Assert.assertTrue("Could not check in by handle if using phone number",lib2.checkin(patron2));
	}
	@Test
	public void testOrderOverDue(){
			 
		ArrayList<LibraryBookGeneric<String>> willBeInOrder= libraryWithBooksAlreadyCheckedOut.getOverdueList(9, 7, 2016); 
		 Assert.assertTrue("geOverDueList(month,day,year). Did not get the propper number of overdue books",willBeInOrder.size()==2);
		Assert.assertTrue("The over due book with the oldest due date is not first",willBeInOrder.get(0).getIsbn()==9780446580342L);
		
	}
	@Test
	public void testOrderOverDueWithNoneOverDue(){
		 
		ArrayList<LibraryBookGeneric<String>> willBeInOrder= lib.getOverdueList(9, 7, 2016); 
		Assert.assertTrue("",willBeInOrder.size()==0);
		
	}
	@Test
	public void testAuthorSortOnMushrooms(){
		ArrayList<LibraryBookGeneric<String>> libSortedByAuthor= lib.getOrderedByAuthor();
		Assert.assertTrue("First Author is placed incorrectly ",libSortedByAuthor.get(0).getAuthor().equals("Alan Burt Akers"));
		
		Assert.assertTrue("Last Author is placed incorrectly ",libSortedByAuthor.get(libSortedByAuthor.size()-1).getAuthor().equals("William Fitzmaurice"));
	}
	@Test
	public void testIsbnSortOnMushrooms(){
		ArrayList<LibraryBookGeneric<String>> libSortedByIsbn= lib.getOrderedByAuthor();
		Assert.assertTrue("First Isbn is placed incorrectly "+libSortedByIsbn.get(0).getIsbn(),libSortedByIsbn.get(0).getIsbn()==9781843193319L);
		
		Assert.assertTrue("Last Author is placed incorrectly "+libSortedByIsbn.get(libSortedByIsbn.size()-1).getIsbn(),libSortedByIsbn.get(libSortedByIsbn.size()-1).getIsbn()<=9781843192701L);
	}
	@Test
	public void testOverDueDateSortOnMushrooms(){
		lib.checkout(9781843190936L, "Lucia Caroline", 12, 11, 2011);
		lib.checkout(9781843192022L, "Lucia Caroline", 12, 12, 2011);
		lib.checkout(9781843190516L, "Frank Fuerte", 10, 21, 2018);
		lib.checkout(9781843190011L, "George Stephens", 5, 11, 2015);
		ArrayList<LibraryBookGeneric<String>> libOverDueSortedByDueDate= lib.getOverdueList(9, 7, 2016);
		Assert.assertTrue("Did not determine the correct number of overdue books. Found: "+libOverDueSortedByDueDate.size() +" Espected: 3",libOverDueSortedByDueDate.size()==3);
		Assert.assertTrue("Failed to correctly determine which overdue book has the oldest due date.",libOverDueSortedByDueDate.get(0).getIsbn()==9781843190936L);
		Assert.assertFalse("A non-overdue book was determined to be overdue",contains(libOverDueSortedByDueDate,9781843190516L));
	}
	/**
	 * Serves to check arraylists of overdue books to make sure that a specific book which is not overdue or not checked out does not appear in it
	 * @param list The arrayList to search through
	 * @param isbnNumberOfBookToCheckFor is the isbn number of the book you are searching for in list
	 * @return true if the isbn occurs on a book in list otherwise false
	 */
	public boolean contains(ArrayList<LibraryBookGeneric<String>> list, long isbnNumberOfBookToCheckFor){
		for(LibraryBookGeneric<String> book: list){
			if(book.getIsbn()==isbnNumberOfBookToCheckFor){
				return true;
			}
		}
		return false;
	}
	}


