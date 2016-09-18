package assignment02;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;


public class LibraryGradingTester {
	public static void main(String[] args) {

		int totalPoints = 0;

		//
		//
		// Original Library (Non-Generic) Test.
		//
		//
		System.out.println("---All files compile, coments---\n" +
				"(i.e., zip contains Book, Library, LibraryBook, \n" +
				"LibraryBookGeneric, LibraryGeneric, all compile,\n" +
				"and code includes good comments and formatting");
		System.out.println();
		System.out.println("Points received:   /10");
		System.out.println();
		System.out.println("===================");
		System.out.println("---Original Tests---");
		System.out.println("Non-Generic Testing");
		// test an empty library
		Library lib = new Library();
		int origNonGen = 5;
		int testsPassed = 10;
		ArrayList<LibraryBook> booksCheckedOut;

		try{
			if (lib.lookup(978037429279L) != null) {
				System.out.println("TEST FAILED -- empty library: lookup(isbn)");
				if (origNonGen > 0)
					origNonGen--;
				testsPassed--;
			}
		} catch (Exception e){
				
				System.out
						.println("TEST GENERATED EXCEPTION -- empty library: lookup(isbn)"
								+ e);
				if (origNonGen > 0)
					origNonGen--;
				testsPassed--;
		}
		
		try{
			booksCheckedOut = lib.lookup("Jane Doe");
			if (booksCheckedOut == null || booksCheckedOut.size() != 0) {
				System.out.println("TEST FAILED -- empty library: lookup(holder)");
				if (origNonGen > 0)
					origNonGen--;
				testsPassed--;
			}
		} catch(Exception e){
			System.out
					.println("TEST GENERATED EXCEPTION -- empty library: lookup(holder)"
							+ e);
			if (origNonGen > 0)
				origNonGen--;
			testsPassed--;
		}
		
		try{
			if (lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008)) {
				System.out.println("TEST FAILED -- empty library: checkout");
				if (origNonGen > 0) {
					origNonGen--;
				}
				testsPassed--;
			}
		} catch (Exception e){
			System.out
					.println("TEST GENERATED EXCEPTION -- empty library: checkout"
							+ e);
			if (origNonGen > 0)
				origNonGen--;
			testsPassed--;
		}
		
		try{
			if (lib.checkin(978037429279L)) {
				System.out.println("TEST FAILED -- empty library: checkin(isbn)");
				if (origNonGen > 0) {
					origNonGen--;
				}
				testsPassed--;
			}
		} catch(Exception e){
			System.out
					.println("TEST GENERATED EXCEPTION -- empty library: checkin(isbn)"
							+ e);
			if (origNonGen > 0)
				origNonGen--;
			testsPassed--;
		}
		
		try{
			if (lib.checkin("Jane Doe")) {
				System.out.println("TEST FAILED -- empty library: checkin(holder)");
				if (origNonGen > 0) {
					origNonGen--;
				}
				testsPassed--;
			}
		} catch (Exception e){
			System.out
					.println("TEST GENERATED EXCEPTION -- empty library: checkin(holder)"
							+ e);
			if (origNonGen > 0)
				origNonGen--;
			testsPassed--;
		}
		
		
		// test a small library
		lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib.add(9780446580342L, "David Baldacci", "Simple Genius");

		try{
			if (lib.lookup(9780330351690L) != null) {
				System.out.println("TEST FAILED -- small library: lookup(isbn)");
				if (origNonGen > 0) {
					origNonGen--;
				}
				testsPassed--;
			}
		} catch (Exception e){
			System.out
					.println("TEST GENERATED EXCEPTION -- small library: lookup(isbn)"
							+ e);
			if (origNonGen > 0)
				origNonGen--;
			testsPassed--;
		}
		
		try{
			if (!lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008)) {
				System.out.println("TEST FAILED -- small library: checkout");
				if (origNonGen > 0) {
					origNonGen--;
				}
				testsPassed--;
			}
		} catch (Exception e){
			System.out
					.println("TEST GENERATED EXCEPTION -- small library: checkout"
							+ e);
			if (origNonGen > 0)
				origNonGen--;
			testsPassed--;
		}

		try{
			booksCheckedOut = lib.lookup("Jane Doe");
			if (booksCheckedOut == null
					|| booksCheckedOut.size() != 1
					|| !booksCheckedOut.get(0).equals(
							new Book(9780330351690L, "Jon Krakauer",
									"Into the Wild"))
					|| !booksCheckedOut.get(0).getHolder().equals("Jane Doe")
					|| !booksCheckedOut.get(0).getDueDate().equals(
							new GregorianCalendar(2008, 1, 1))) {
				System.out.println("TEST FAILED -- small library: lookup(holder)");
				if (origNonGen > 0) {
					origNonGen--;
				}
				testsPassed--;
			}
		} catch (Exception e){
			System.out
					.println("TEST GENERATED EXCEPTION -- small library: lookup(holder)"
							+ e);
			if (origNonGen > 0)
				origNonGen--;
			testsPassed--;
		}

		try{
			if (!lib.checkin(9780330351690L)) {
				System.out.println("TEST FAILED -- small library: checkin(isbn)");
				if (origNonGen > 0) {
					origNonGen--;
				}
				testsPassed--;
			}
		} catch (Exception e){
			System.out
					.println("TEST GENERATED EXCEPTION -- small library: checkin(isbn)"
							+ e);
			if (origNonGen > 0)
				origNonGen--;
			testsPassed--;
		}

		try{
			if (lib.checkin("Jane Doe")) {
				System.out.println("TEST FAILED -- small library: checkin(holder)");
				if (origNonGen > 0) {
					origNonGen--;
				}
				testsPassed--;
			}
		} catch (Exception e){
			System.out
					.println("TEST GENERATED EXCEPTION -- small library: checkin(holder)"
							+ e);
			if (origNonGen > 0)
				origNonGen--;
			testsPassed--;
		}

		System.out.println(testsPassed + "/10 tests passed.  " + origNonGen
				+ "/5 Points");
		totalPoints += origNonGen;

		//
		//		
		// Original Library Tests (Generic)
		//
		//
		System.out.println();
		System.out.println("Generic Tests");
		int origGen = 5;
		testsPassed = 8;

		// test a library that uses names (String) to id patrons
		LibraryGeneric<String> lib1 = new LibraryGeneric<String>();
		lib1.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib1.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib1.add(9780446580342L, "David Baldacci", "Simple Genius");

		String patron1 = "Jane Doe";

		try{
			if (!lib1.checkout(9780330351690L, patron1, 1, 1, 2008)) {
				System.out.println("TEST FAILED: first checkout");
				if (origGen > 0) {
					origGen--;
				}
				testsPassed--;
			}
		}catch(Exception e){
			System.out.println("TEST GENERATED EXCEPTION: first checkout"
								+ e);
			if (origGen > 0) {
				origGen--;
			}
			testsPassed--;
		}
		
		try{
			if (!lib1.checkout(9780374292799L, patron1, 1, 1, 2008)) {
				System.out.println("TEST FAILED: second checkout");
				if (origGen > 0) {
					origGen--;
				}
				testsPassed--;
			}
		}catch(Exception e){
			System.out.println("TEST GENERATED EXCEPTION: second checkout"
								+ e);
			if (origGen > 0) {
				origGen--;
			}
			testsPassed--;
		}
		
		try{
			ArrayList<LibraryBookGeneric<String>> booksCheckedOut1 = lib1
					.lookup(patron1);
			if (booksCheckedOut1 == null
					|| booksCheckedOut1.size() != 2
					|| !booksCheckedOut1.contains(new Book(9780330351690L,
							"Jon Krakauer", "Into the Wild"))
					|| !booksCheckedOut1.contains(new Book(9780374292799L,
							"Thomas L. Friedman", "The World is Flat"))
					|| !booksCheckedOut1.get(0).getHolder().equals(patron1)
					|| !booksCheckedOut1.get(0).getDueDate().equals(
							new GregorianCalendar(2008, 1, 1))
					|| !booksCheckedOut1.get(1).getHolder().equals(patron1)
					|| !booksCheckedOut1.get(1).getDueDate().equals(
							new GregorianCalendar(2008, 1, 1))) {
				System.out.println("TEST FAILED: lookup(holder)");
				if (origGen > 0) {
					origGen--;
				}
				testsPassed--;
			}
		}catch(Exception e){
			System.out.println("TEST GENERATED EXCEPTION: lookup(holder)"
								+ e);
			if (origGen > 0) {
				origGen--;
			}
			testsPassed--;
		}
		
		try{
			if (!lib1.checkin(patron1)) {
				System.out.println("TEST FAILED: checkin(holder)");
				if (origGen > 0) {
					origGen--;
				}
				testsPassed--;
			}
		}catch(Exception e){
			System.out.println("TEST GENERATED EXCEPTION: checkin(holder)"
								 + e);
			if (origGen > 0) {
				origGen--;
			}
			testsPassed--;
		}


		// test a library that uses phone numbers (PhoneNumber) to id patrons
		LibraryGeneric<PhoneNumber> lib2 = new LibraryGeneric<PhoneNumber>();
		lib2.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib2.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib2.add(9780446580342L, "David Baldacci", "Simple Genius");

		PhoneNumber patron2 = new PhoneNumber("801.555.1234");
		try{
			if (!lib2.checkout(9780330351690L, patron2, 1, 1, 2008)) {
				System.out.println("TEST FAILED: first checkout");
				if (origGen > 0) {
					origGen--;
				}
				testsPassed--;
			}
		}catch(Exception e){
			System.out.println("TEST GENERATED EXCEPTION: first checkout"
							+ e);
			if (origGen > 0) {
				origGen--;
			}
			testsPassed--;
		}
		
		try{
			if (!lib2.checkout(9780374292799L, patron2, 1, 1, 2008)) {
				System.out.println("TEST FAILED: second checkout");
				if (origGen > 0) {
					origGen--;
				}
				testsPassed--;
			}
		}catch(Exception e){
			System.out.println("TEST GENERATED EXCEPTION: second checkout"
								+ e);
			if (origGen > 0) {
				origGen--;
			}
			testsPassed--;
		}

		try{
			ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut2 = lib2
					.lookup(patron2);
			if (booksCheckedOut2 == null
					|| booksCheckedOut2.size() != 2
					|| !booksCheckedOut2.contains(new Book(9780330351690L,
							"Jon Krakauer", "Into the Wild"))
					|| !booksCheckedOut2.contains(new Book(9780374292799L,
							"Thomas L. Friedman", "The World is Flat"))
					|| !booksCheckedOut2.get(0).getHolder().equals(patron2)
					|| !booksCheckedOut2.get(0).getDueDate().equals(
							new GregorianCalendar(2008, 1, 1))
					|| !booksCheckedOut2.get(1).getHolder().equals(patron2)
					|| !booksCheckedOut2.get(1).getDueDate().equals(
							new GregorianCalendar(2008, 1, 1))) {
				System.out.println("TEST FAILED: lookup(holder)");
				if (origGen > 0) {
					origGen--;
				}
				testsPassed--;
			}
		}catch(Exception e){
			System.out.println("TEST GENERATED EXCEPTION: lookup(holder)"
								+ e);
			if (origGen > 0) {
				origGen--;
			}
			testsPassed--;
		}

		try{
			if (!lib2.checkin(patron2)) {
				System.out.println("TEST FAILED: checkin(holder)");
				if (origGen > 0) {
					origGen--;
				}
				testsPassed--;
			}
		}catch(Exception e){
			System.out.println("TEST GENERATED EXCEPTION: checkin(holder)"
								+ e);
			if (origGen > 0) {
				origGen--;
			}
			testsPassed--;
		}


		System.out.println(testsPassed + "/8 tests passed.  " + origGen
				+ "/5 Points");

		totalPoints += origGen;

		//
		//
		// Phase 1 Testing
		//
		//
		System.out.println();
		System.out.println("===================");
		System.out.println("---Phase 1 Testing---");
		System.out.println("Testing Book.java");

		// Testing Book
		// Book.equals.
		int bookTesting = 5;
		testsPassed = 7;

		Book firstBook = new Book(123456789L, "Frog, Kermit the",
				"It's Not Easy Being Green...");
		Book secondBook = new Book(123456789L, "Frog, Kermit the",
				"It's Not Easy Being Green...");
		Book thirdBook = new Book(987654321L, "E. D. Baker",
				"The Frog Princess");
		Book fourthBook = new Book(123456789L, "E. D. Baker",
				"The Frog Princess");
		Book fifthBook = new Book(123456789L, "Kermit the Frog",
				"It's Not Easy Being Green...");
		Book sixthBook = new Book(123456789L, "Frog, Kermit the",
				"It's Not Easy Being Green.");
		Integer nonBook = new Integer(10);

		try{
			if (!firstBook.equals(secondBook)) {
				System.out.println("TEST FAILED: Returned false for equal books.");
				if (bookTesting > 0) {
					bookTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e){
			System.out.println("TEST GENERATED EXCEPTION: Returned false for equal books."
							+ e);
			if (bookTesting > 0) {
				bookTesting--;
			}
			testsPassed--;
		}

		try{
			if (firstBook.equals(thirdBook)) {
				System.out
						.println("TEST FAILED: Returned true for completely different books.");
				if (bookTesting > 0) {
					bookTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e){
			System.out.println("TEST GENERATED EXCEPTION: Returned true for completely different books."
							+ e);
			if (bookTesting > 0) {
				bookTesting--;
			}
			testsPassed--;
		}

		try{
			if (fourthBook.equals(thirdBook)) {
				System.out.println("TEST FAILED: Returned true different ISBNs.");
				if (bookTesting > 0) {
					bookTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e){
			System.out.println("TEST GENERATED EXCEPTION: Returned true different ISBNs."
							+ e);
			if (bookTesting > 0) {
				bookTesting--;
			}
			testsPassed--;
		}
		
		try{
			if (firstBook.equals(sixthBook)) {
				System.out.println("TEST FAILED: Returned true different titles.");
				if (bookTesting > 0) {
					bookTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e){
			System.out.println("TEST GENERATED EXCEPTION: Returned true different titles."
							+ e);
			if (bookTesting > 0) {
				bookTesting--;
			}
			testsPassed--;
		}

		try{
			if (firstBook.equals(fourthBook)) {
				System.out.println("TEST FAILED: Returned true different authors.");
				if (bookTesting > 0) {
					bookTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e){
			System.out.println("TEST GENERATED EXCEPTION: Returned true different authors."
							+ e);
			if (bookTesting > 0) {
				bookTesting--;
			}
			testsPassed--;
		}

		try{
			if (firstBook.equals(fifthBook)) {
				System.out
						.println("TEST FAILED: Returned true ONLY identical ISBNs.");
				if (bookTesting > 0) {
					bookTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e){
			System.out.println("TEST GENERATED EXCEPTION: Returned true ONLY identical ISBNs."
							+ e);
			if (bookTesting > 0) {
				bookTesting--;
			}
			testsPassed--;
		}

		try {
			if (firstBook.equals(nonBook)) {
				System.out
					.println("TEST FAILED: Returned true for a Book and an Integer.");
				if (bookTesting > 0) {
					bookTesting--;
				}
				testsPassed--;
			}
		}
		catch(Exception e) {
			System.out
			.println("TEST FAILED: Comparing a Book and an Integer caused exception.");
			if (bookTesting > 0) {
				bookTesting--;
			}
			testsPassed--;
		}

		System.out.println(testsPassed + "/7 tests passed.  " + bookTesting
				+ "/5 Points");
		totalPoints += bookTesting;
		System.out.println();

		// Joint testing LibraryBook.java and Library.java

		testsPassed = 12;
		int lbTesting = 10;

		System.out.println("Joint testing LibraryBook.java and Library.java");

		LibraryBook lbBook1 = new LibraryBook(123456789L, "Frog, Kermit the",
				"It's Not Easy Being Green...");
		LibraryBook lbBook2 = new LibraryBook(987654321L, "E. D. Baker",
				"The Frog Princess");
		
		Library lib4 = new Library(); // Empty Library.
		Library lib3 = new Library();
		lib3.add(123456789L, "Frog, Kermit the",
						"It's Not Easy Being Green...");
		lib3.add(987654321L, "E. D. Baker", "The Frog Princess");
		lib3.add(000111333L, "Aesop", "The Scorpion and the Frog");

		// LibraryBook
		// getHolder() returns holder string.
		// getDueDate() returns book due date.

		if (lbBook1.getHolder() != null) {
			System.out.println("TEST FAILED: .getHolder() fail.");
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}

		if (lbBook1.getDueDate() != null) {
			System.out.println("TEST FAILED: .getDueDate() fail.");
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}

		// Library
		// lookup(long isbn) returns holder.
		// lookup(String holder) returns ArrayList<LibraryBook>
		// checkout(long isbn, String holder, int month, int day, int year)
		// returns true if succesfully checked out.
		// checkin(long isbn) returns true if checked in.
		// checkin(String holder) returns true if ALL books held
		// by holder are checked in.

		if (lib3.lookup(123456789L) != null) {
			System.out.println("TEST FAILED: lookup(long isbn) should == null.");
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}

		boolean check1 = lib3.checkout(123456789L, "Awesome the TA", 7, 21,2010);
		boolean check2 = lib3.checkout(987654321L, "Awesome the TA", 8, 20,2011);
		boolean check3 = lib3.checkout(000111333L, "Scorpion King", 6, 17, 1903);
		boolean check4 = lib4.checkout(333111000L, "Can't Read", 5, 6, 1958);
		boolean check5 = lib3.checkout(123456789L, "Miss Piggy", 7, 21, 2010);

		if (check1 != true && check2 != true && check3 != true
				&& check4 != false && check5 != true) {
			System.out.println("TEST FAILED: checkOut failed.");
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}
		try{
			if (!lib3.lookup(123456789L).equals("Awesome the TA")) {
				System.out.println("TEST FAILED: lookup(long isbn) should != null.");
				if (lbTesting > 0) 
					lbTesting--;
				testsPassed--;
			}
		} catch (Exception e){
			System.out.println("TEST GENERATED EXCEPTION: lookup(long isbn) should != null." + e);
			if (lbTesting > 0)
				lbTesting--;
			testsPassed--;
		}

		try {
			if (lib4.lookup(123456789L) != null) {
				System.out
						.println("TEST FAILED: lookup(long isbn) for empty library.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: lookup(long isbn) for empty library." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}

		ArrayList<LibraryBook> aweTAsBooks = new ArrayList<LibraryBook>();
		aweTAsBooks.add(lbBook2);
		aweTAsBooks.add(lbBook1);

		try {
			if (lib3.lookup("Awesome the TA").containsAll(aweTAsBooks) != true) {
				System.out
						.println("TEST FAILED: lookup(String holder) did not find all LibraryBooks.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: lookup(String holder) did not find all LibraryBooks." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}

		try {
			if (lib3.lookup("Steve the Cat").equals(new ArrayList<LibraryBook>()) != true) {
				System.out
						.println("TEST FAILED: lookup(String holder) did not return empty array.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: lookup(String holder) did not return empty array." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}

		try {
			if (!lib4.lookup("Steve the Cat").equals(
					new ArrayList<LibraryBook>())) {
				System.out.println("TEST FAILED: lookup(String holder) did not "
						+ "return empty array from empty library.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: lookup(String holder) did not "
					+ "return empty array from empty library." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}
		
		try {
			if (lib4.checkin(000111333L)) {
				System.out
						.println("TEST FAILED: checkin(long isbn) book does not exist.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: checkin(long isbn) book does not exist." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}

		try {
			if (!lib3.checkin(000111333L)) {
				System.out.println("TEST FAILED: checkin(long isbn) failed.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: checkin(long isbn) failed." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}

		try {
			if (!lib.lookup("Scorpion King").equals(new ArrayList<LibraryBook>())) {
				System.out.println("TEST FAILED: lookup(String holder) did not "
						+ "return empty array from empty library.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: lookup(String holder) did not "
					+ "return empty array from empty library." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}


		
		System.out.println(testsPassed + "/12 tests passed.  " + lbTesting
				+ "/10 Points");
		totalPoints += lbTesting;
		System.out.println();
		
		
		//
		//
		// Phase 2 Testing.
		//
		//
		

		// lets re run the Phase 1 tests on the Phase 2 code, just to be sure
		// We'll use the `nice' LibraryGeneric<String>
		System.out.println("===================");
		System.out.println("---Phase 2 Testing---");

		testsPassed = 11;
		lbTesting = 10;

		System.out
				.println("Joint testing LibraryBookGeneric.java and LibraryGeneric.java");

		LibraryBookGeneric<String> lbgBook1 = new LibraryBookGeneric<String>(
				123456789L, "Frog, Kermit the", "It's Not Easy Being Green...");
		LibraryBookGeneric<String> lbgBook2 = new LibraryBookGeneric<String>(
				987654321L, "E. D. Baker", "The Frog Princess");
		LibraryBookGeneric<String> lbgBook3 = new LibraryBookGeneric<String>(
				000111333L, "Aesop", "The Scorpion and the Frog");

		LibraryGeneric<String> libg4 = new LibraryGeneric<String>(); // Empty
																		// Library.
		LibraryGeneric<String> libg3 = new LibraryGeneric<String>();
		libg3.add(123456789L, "Frog, Kermit the",
				"It's Not Easy Being Green...");
		libg3.add(987654321L, "E. D. Baker", "The Frog Princess");
		libg3.add(000111333L, "Aesop", "The Scorpion and the Frog");

		// LibraryBook
		// getHolder() returns holder string.
		// getDueDate() returns book due date.

		try {
			if (lbgBook1.getHolder() != null) {
				System.out.println("TEST FAILED: .getHolder() fail.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: .getHolder() fail." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}
		
		try {
			if (lbgBook1.getDueDate() != null) {
				System.out.println("TEST FAILED: .getDueDate() fail.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: .getDueDate() fail." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}



		// Library
		// lookup(long isbn) returns holder.
		// lookup(String holder) returns ArrayList<LibraryBook>
		// checkout(long isbn, String holder, int month, int day, int year)
		// returns true if successfully checked out.
		// checkin(long isbn) returns true if checked in.
		// checkin(String holder) returns true if ALL books held
		// by holder are checked in.

		try {
			if (libg3.lookup(123456789L) != null) {
				System.out.println("TEST FAILED: lookup(long isbn) should == null.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: lookup(long isbn) should == null." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}


		check1 = libg3.checkout(123456789L, "Awesome the TA", 7, 21, 2010);
		check2 = libg3.checkout(987654321L, "Awesome the TA", 8, 20, 2011);
		check3 = libg3.checkout(000111333L, "Scorpion King", 6, 17, 1903);
		check4 = libg4.checkout(333111000L, "Can't Read", 5, 6, 1958);
		check5 = libg3.checkout(123456789L, "Miss Piggy", 7, 21, 2010);

		if (check1 != true && check2 != true && check3 != true
				&& check4 != false && check5 != true) {
			System.out.println("TEST FAILED: checkOut failed.");
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}

		try {
			if (!libg3.lookup(123456789L).equals("Awesome the TA")) {
				System.out
						.println("TEST FAILED: lookup(long isbn) should != null.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: lookup(long isbn) should != null." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}


		try {
			if (libg4.lookup(123456789L) != null) {
				System.out
						.println("TEST FAILED: lookup(long isbn) for empty library.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: lookup(long isbn) for empty library." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}

		ArrayList<LibraryBookGeneric<String>> aweTAsBooksg = new ArrayList<LibraryBookGeneric<String>>();
		aweTAsBooks.add(lbBook2);
		aweTAsBooks.add(lbBook1);

		try {
			if (libg3.lookup("Awesome the TA").containsAll(aweTAsBooksg) != true) {
				System.out
						.println("TEST FAILED: lookup(String holder) did not find all LibraryBooks.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: lookup(String holder) did not find all LibraryBooks." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}

		try {
			if (libg3.lookup("Steve the Cat").equals(
					new ArrayList<LibraryBookGeneric<String>>()) != true) {
				System.out
						.println("TEST FAILED: lookup(String holder) did not return empty array.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: lookup(String holder) did not return empty array." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}
		
		try {
			if (libg3.lookup("Steve the Cat").equals(
					new ArrayList<LibraryBookGeneric<String>>()) != true) {
				System.out
						.println("TEST FAILED: lookup(String holder) did not return empty array.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: lookup(String holder) did not return empty array." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}


		try {
			if (!libg4.lookup("Steve the Cat").equals(
					new ArrayList<LibraryBookGeneric<String>>())) {
				System.out.println("TEST FAILED: lookup(String holder) did not "
						+ "return empty array from empty library.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: lookup(String holder) did not "
					+ "return empty array from empty library." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}

		try {
			if (libg4.checkin(000111333L)) {
				System.out
						.println("TEST FAILED: checkin(long isbn) book does not exist.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: checkin(long isbn) book does not exist." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}

		try {
			if (!libg3.checkin(000111333L)) {
				System.out.println("TEST FAILED: checkin(long isbn) failed.");
				if (lbTesting > 0) {
					lbTesting--;
				}
				testsPassed--;
			}
		} catch (Exception e) {
			System.out.println("TEST GENERATED EXCEPTION: checkin(long isbn) failed." + e);
			if (lbTesting > 0) {
				lbTesting--;
			}
			testsPassed--;
		}



		System.out.println(testsPassed + "/11 tests passed.  " + lbTesting
				+ "/10 Points");
		totalPoints += lbTesting;
		System.out.println();
		// Now check using a different class... like Object!
		// Shameless copy and paste... :)

		testsPassed = 10;
		int phase2Score = 10;
		System.out.println("Testing LibraryGeneric<Object> methods");
		// Check and make sure Phase 1 stuff still works in Generic Land
		// check using a few different kinds of generic libraries
		LibraryGeneric<Object> testGenericLib2 = new LibraryGeneric<Object>();
		// add some books...
		testGenericLib2.add(314159L, "Awesome Writer", "Awesome Book");
		testGenericLib2.add(0101010101L, "Random Peon A",
				"Peon A's Book about Stuff");
		testGenericLib2.add(2701L, "That One Guy", "That One Book");
		testGenericLib2.add(12345L, "asdasdasd", "asdasdasdasd");

		// make sure that checkout and such works
		try {
			if (!(testGenericLib2.checkout(314159L, new Double(3.14159), 1, 2,2010))) {
				System.out.println("TEST FAILED: Checkout failed on LibraryGeneric<Object>");
				testsPassed--;
				phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
			}
		} catch (Exception e) {
			System.out
					.println("TEST FAILED: Checkout failed on LibraryGeneric<Object>"
							+ e);
			testsPassed--;
			phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
		}
		// now checkout using another object!
		try {
			if (!(testGenericLib2.checkout(0101010101L,
					new String("Frog King"), 1, 2, 2010))) {
				System.out
						.println("TEST FAILED: Checkout failed on LibraryGeneric<Object>");
				testsPassed--;
				phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
			}
		} catch (Exception e) {
			System.out
					.println("TEST FAILED: Checkout failed on LibraryGeneric<Object>"
							+ e);
			testsPassed--;
			phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
		}
		// make sure we cannot check the book out now.
		try {
			if (testGenericLib2.checkout(314159L, "Bam!", 3, 4, 2000)) {
				System.out
						.println("TEST FAILED: Checkout returned true on already existing book on LibraryGeneric<Object>");
				testsPassed--;
				phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
			}
		} catch (Exception e) {
			System.out
					.println("TEST FAILED: Exception on trying to checkout an already checked out book LibraryGeneric<Object>"
							+ e);
			testsPassed--;
			phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
		}
		// make sure Lookup returns something sane(and valid too!)
		try {
			ArrayList<LibraryBookGeneric<Object>> res = testGenericLib2
					.lookup(new Double(3.14159));
			if (res.size() != 1
					|| !res.get(0).getHolder().equals(new Double(3.14159))
					|| res.get(0).getIsbn() != 314159L) {
				System.out
						.println("TEST FAILED: Lookup(holder) failed on LibraryGeneric<Object>");
				testsPassed--;
				phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
			}
		} catch (Exception e) {
			System.out
					.println("TEST FAILED: Lookup(holder) failed(Exception) on LibraryGeneric<Object>"
							+ e);
			testsPassed--;
			phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
		}
		// check lookup(ISBN)
		try {
			if (!(new Double(3.14159).equals(testGenericLib2.lookup(314159L)))) {
				System.out
						.println("TEST FAILED: Lookup(isbn) failed on LibraryGeneric<Object>");
				testsPassed--;
				phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
			}
		} catch (Exception e) {
			System.out
					.println("TEST FAILED: Lookup(isbn) failed(Exception) on LibraryGeneric<Object>"
							+ e);
			testsPassed--;
			phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
		}
		// check checkin
		try {
			if ((!testGenericLib2.checkin(314159L))
					|| testGenericLib2.lookup(314159L) != null) {
				System.out.println("TEST FAILED: Checkin failed on LibraryGeneric<Object>");
				testsPassed--;
				phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
			}
		} catch (Exception e) {
			System.out
					.println("TEST FAILED: Checkin failed(Exception) on LibraryGeneric<Object>"
							+ e);
			testsPassed--;
			phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
		}
		// now make sure we can't check it in again
		try {
			if (testGenericLib2.checkin(314159L)) {
				System.out
						.println("TEST FAILED: Checkin failed on LibraryGeneric<Object>");
				testsPassed--;
				phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
			}
		} catch (Exception e) {
			System.out
					.println("TEST FAILED: Checkin failed(Exception) on LibraryGeneric<Object>"
							+ e);
			testsPassed--;
			phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
		}
		// check checkin(holder)
		try {
			testGenericLib2.checkout(314159L, new Integer(2), 1, 2, 2010);
			testGenericLib2.checkout(12345L, new Integer(2), 2, 1, 2010);
			if (!testGenericLib2.checkin(new Integer(2))) {
				System.out
						.println("TEST FAILED: Checkin(holder) failed on LibraryGeneric<Object>");
				testsPassed--;
				phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
			}
		} catch (Exception e) {
			System.out
					.println("TEST FAILED: Checkin(holder) failed(Exception) on LibraryGeneric<Object>"
							+ e);
			testsPassed--;
			phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
		}
		
		// Test on totally empty generic library.
		LibraryGeneric<String> emptyLib = new LibraryGeneric<String>();
		
		try {
			if (emptyLib.checkout(1234567L, "I Can Has Book?", 5, 22, 1904)) {
				System.out.println("TEST FAIL: checkout() on empty generic library");
				testsPassed--;
				phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
			}
			
		} catch (Exception e) {
			System.out
					.println("TEST FAILED: Exception thrown on empty generic library."
							+ e);
			testsPassed--;
			phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
		}		
		
		try {
			if (emptyLib.checkin(1234656L)) {
				System.out.println("TEST FAIL: checkin() on empty generic library");
				testsPassed--;
				phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
			}
			
		} catch (Exception e) {
			System.out
					.println("TEST FAILED: Exception thrown on empty generic library."
							+ e);
			testsPassed--;
			phase2Score = (phase2Score > 0 ? phase2Score - 1 : 0);
		}	
		
		
		
		
		System.out.println(testsPassed + "/10 tests passed.  " + phase2Score
				+ "/10 Points");
		totalPoints += phase2Score;
		System.out.println();
		
		
		//
		//
		// Phase 3 Testing.
		//
		//
		
		System.out.println("===================");
		System.out.println("---Phase 3 Testing---");
		System.out.println("Sorting by due date testing");
		
		testsPassed = 5;
		int phase3TestingVar = 10;
		
		
		LibraryGeneric<PhoneNumber> phase3Lib = new LibraryGeneric<PhoneNumber>();
		phase3Lib.add(123456789L, "Frog, Kermit the","It's Not Easy Being Green...");
		phase3Lib.add(987654321L, "E. D. Baker", "The Frog Princess");
		phase3Lib.add(000111333L, "Aesop", "The Scorpion and the Frog");
		
		phase3Lib.checkout(000111333L, new PhoneNumber("5552551101"), 7, 21, 1955);
		phase3Lib.checkout(987654321L, new PhoneNumber("5552551101"), 7, 23, 1955);
		
		GregorianCalendar due21 = new GregorianCalendar(1955,7,21);
		GregorianCalendar due23 = new GregorianCalendar(1955,7,23);
		try{
			ArrayList<LibraryBookGeneric<PhoneNumber>> overDueList = phase3Lib.getOverdueList(8, 12, 1960);
			if(overDueList.size() != 2 ||
					!overDueList.get(0).getDueDate().equals(due21)||
					!overDueList.get(1).getDueDate().equals(due23)){
				System.out.println("TEST FAILED: overDueList for PhoneNumber failed.");
				if (lbTesting > 0) {
					phase3TestingVar -= 2;
				}
				testsPassed--;
			}
		}catch (Exception e) {
			System.out.println("Exception thrown. " + e);
			if (lbTesting > 0) {
				phase3TestingVar -= 2;
			}
			testsPassed--;
		}
		
		
		phase3Lib.checkout(123456789L, new PhoneNumber("5558675309"), 5, 20, 2030);
		phase3Lib.checkout(987654321L, new PhoneNumber("5558675309"), 5, 20, 2030);
		
		try{
			ArrayList<LibraryBookGeneric<PhoneNumber>> overDueList = phase3Lib.getOverdueList(8, 12, 2100);
			if(overDueList.size() != 3){
				System.out.println("TEST FAILED: overDueList for all books failed.");
				if (lbTesting > 0) {
					phase3TestingVar -= 2;
				}
				testsPassed--;
			}
		}catch (Exception e) {
			System.out.println("Exception thrown. " + e);
			if (lbTesting > 0) {
				phase3TestingVar -= 2;
			}
			testsPassed--;
		}
	
		phase3Lib.checkin(000111333L);
		phase3Lib.checkin(123456789L);
		
		try{
			ArrayList<LibraryBookGeneric<PhoneNumber>> overDueList = phase3Lib.getOverdueList(8, 12, 1960);
			if(overDueList.size() != 1 ||
					!overDueList.get(0).getDueDate().equals(due23)){
				System.out.println("TEST FAILED: overDueList for one book failed.");
				if (lbTesting > 0) {
					phase3TestingVar -= 2;
				}
				testsPassed--;
			}
		}catch (Exception e) {
			System.out.println("Exception thrown. " + e);
			if (lbTesting > 0) {
				phase3TestingVar -= 2;
			}
			testsPassed--;
		}
		
		try{
			ArrayList<LibraryBookGeneric<PhoneNumber>> overDueList = phase3Lib.getOverdueList(8, 12, 1900);
			if(overDueList.size() != 0){
				System.out.println("TEST FAILED: overDueList for zero books failed.");
				if (lbTesting > 0) {
					phase3TestingVar -= 2;
				}
				testsPassed--;
			}
		}catch (Exception e) {
			System.out.println("Exception thrown. " + e);
			if (lbTesting > 0) {
				phase3TestingVar -= 2;
			}
			testsPassed--;
		}
		
		
		
		// Method calls on totally empty library.
		try{
			ArrayList<LibraryBookGeneric<String>> overDueList = 
				new LibraryGeneric<String>().getOverdueList(8, 12, 1900);
			if(overDueList.size() != 0){
				System.out.println("TEST FAILED: overDueList for zero books failed.");
				if (lbTesting > 0) {
					phase3TestingVar -= 2;
				}
				testsPassed--;
			}
		}catch (Exception e) {
			System.out.println("Exception thrown. " + e);
			if (lbTesting > 0) {
				phase3TestingVar -= 2;
			}
			testsPassed--;
		}

		phase3Lib.checkout(000111333L, new PhoneNumber("1234567890"), 3, 15, 2005);
		
		System.out.println(testsPassed + "/5 tests passed.  " + phase3TestingVar
				+ "/10 Points");
		totalPoints += phase3TestingVar;
		System.out.println();	
		
		
		//Check OrderByAuthor
		System.out.println("Sorting by author and title testing");
		testsPassed = 5;
		int authorScore = 10;
		
		LibraryGeneric<String> orderByAuthorLib = new LibraryGeneric<String>();
		orderByAuthorLib.add(123456789L, "CCCC", "CCCC");
		orderByAuthorLib.add(321321321L, "DDDD", "DDDD");
		orderByAuthorLib.add(987654321L, "BBBB", "BBBB");
		orderByAuthorLib.add(000111333L, "AAAA", "AAAA");
		
		try{
			ArrayList<LibraryBookGeneric<String>> authorList = orderByAuthorLib.getOrderedByAuthor();
			if(authorList.size() != 4
			|| authorList.get(0).getIsbn() != 000111333L
			|| authorList.get(1).getIsbn() != 987654321L
			|| authorList.get(2).getIsbn() != 123456789L
			|| authorList.get(3).getIsbn() != 321321321L) {
				System.out.println("TEST FAILED: orderedByAuthor author and title equivalent");
				if(authorScore > 0) {
					authorScore -= 2;
				}
				testsPassed--;
			}
		}catch (Exception e) {
			System.out.println("Exception thrown. " + e);
			if (authorScore > 0) {
				authorScore -= 2;
			}
			testsPassed--;
		}
		
		orderByAuthorLib = new LibraryGeneric<String>();
		orderByAuthorLib.add(123456789L, "CCCC", "FEDC");
		orderByAuthorLib.add(321321321L, "DDDD", "CDEF");
		orderByAuthorLib.add(111110000L, "AABB", "AAAA");
		orderByAuthorLib.add(987654321L, "BBBB", "ABCD");
		orderByAuthorLib.add(000111333L, "AAAA", "DDDD");
		
		try{
			ArrayList<LibraryBookGeneric<String>> authorList = orderByAuthorLib.getOrderedByAuthor();
			if(authorList.size() != 5
			|| authorList.get(0).getIsbn() != 000111333L
			|| authorList.get(1).getIsbn() != 111110000L
			|| authorList.get(2).getIsbn() != 987654321L
			|| authorList.get(3).getIsbn() != 123456789L
			|| authorList.get(4).getIsbn() != 321321321L) {
				System.out.println("TEST FAILED: orderedByAuthor author and title different");
				if(authorScore > 0) {
					authorScore -= 2;
				}
				testsPassed--;
			}
		}catch (Exception e) {
			System.out.println("Exception thrown. " + e);
			if (authorScore > 0) {
				authorScore -= 2;
			}
			testsPassed--;
		}
		
		orderByAuthorLib = new LibraryGeneric<String>();
		orderByAuthorLib.add(123456789L, "CCCC", "BBBB");
		orderByAuthorLib.add(321321321L, "DDDD", "CDEF");
		orderByAuthorLib.add(111110000L, "CCCC", "AAAA");
		orderByAuthorLib.add(987654321L, "BBBB", "ABCD");
		orderByAuthorLib.add(000111333L, "AAAA", "AAAA");
		
		try{
			ArrayList<LibraryBookGeneric<String>> authorList = orderByAuthorLib.getOrderedByAuthor();
			if(authorList.size() != 5
			|| authorList.get(0).getIsbn() != 000111333L
			|| authorList.get(1).getIsbn() != 987654321L
			|| authorList.get(2).getIsbn() != 111110000L
			|| authorList.get(3).getIsbn() != 123456789L
			|| authorList.get(4).getIsbn() != 321321321L) {
				System.out.println("TEST FAILED: orderedByAuthor author and title tiebreaker");
				if(authorScore > 0) {
					authorScore -= 2;
				}
				testsPassed--;
			}
		}catch (Exception e) {
			System.out.println("Exception thrown. " + e);
			if (authorScore > 0) {
				authorScore -= 2;
			}
			testsPassed--;
		}
		
		orderByAuthorLib = new LibraryGeneric<String>();
		orderByAuthorLib.add(333333333L, "BBBB", "EEEE");
		orderByAuthorLib.add(222222222L, "BBBB", "CCCC");
		orderByAuthorLib.add(111111111L, "BBBB", "DDDD");
		orderByAuthorLib.add(666666666L, "AAAA", "GGGG");
		orderByAuthorLib.add(444444444L, "CCCC", "AAAA");
		orderByAuthorLib.add(555555555L, "AAAA", "EEEE");
		
		try{
			ArrayList<LibraryBookGeneric<String>> authorList = orderByAuthorLib.getOrderedByAuthor();
			if(authorList.size() != 6
			|| authorList.get(0).getIsbn() != 555555555L
			|| authorList.get(1).getIsbn() != 666666666L
			|| authorList.get(2).getIsbn() != 222222222L
			|| authorList.get(3).getIsbn() != 111111111L
			|| authorList.get(4).getIsbn() != 333333333L
			|| authorList.get(5).getIsbn() != 444444444L) {
				System.out.println("TEST FAILED: orderedByAuthor author and title tiebreaker 2");
				if(authorScore > 0) {
					authorScore -= 2;
				}
				testsPassed--;
			}
		}catch (Exception e) {
			System.out.println("Exception thrown. " + e);
			if (authorScore > 0) {
				authorScore -= 2;
			}
			testsPassed--;
		}
		
		orderByAuthorLib = new LibraryGeneric<String>();
		
		try{
			ArrayList<LibraryBookGeneric<String>> authorList = orderByAuthorLib.getOrderedByAuthor();
			if(authorList.size() != 0) {
				System.out.println("TEST FAILED: orderedByAuthor empty library");
				if(authorScore > 0) {
					authorScore -= 2;
				}
				testsPassed--;
			}
		}catch (Exception e) {
			System.out.println("Exception thrown. " + e);
			if (authorScore > 0) {
				authorScore -= 2;
			}
			testsPassed--;
		}
		
		System.out.println(testsPassed + "/5 tests passed.  " + authorScore
				+ "/10 Points");
		totalPoints += authorScore;
		System.out.println();	
		
		
		//
		//
		// Final score output.
		//
		//
		System.out.println("===================");
		System.out.println("Comments/style:          /10");
		System.out.println("Program points:          " + totalPoints + "/65");
		System.out.println("Student-provided tests:  /5");
		System.out.println("Analysis Document:       /20");
		System.out.println("Total Points:            /100");
		
		
		System.out.println();
		System.out.println("Note: General rule is 1 point deducted for each failed test \n"
						 + "(up to total number of points possible for each test).");
		System.out.println("In phase 3 testing, two points were deducted per failed test.");
		System.out.println();
		System.out.println("===================");
		System.out.println("Comments:");
		
		
	}

	// Helper methods.

	/**
	 * Returns a library of "dummy" books (random ISBN and placeholders for
	 * author and title).
	 * 
	 * Useful for collecting running times for operations on libraries of
	 * varying size.
	 * 
	 * @param size
	 *            -- size of the library to be generated
	 */
	public static ArrayList<LibraryBook> generateLibrary(int size) {
		ArrayList<LibraryBook> result = new ArrayList<LibraryBook>();

		for (int i = 0; i < size; i++) {
			// generate random ISBN
			Random randomNumGen = new Random();
			String isbn = "";
			for (int j = 0; j < 13; j++)
				isbn += randomNumGen.nextInt(10);

			result.add(new LibraryBook(Long.parseLong(isbn), "An author",
					"A title"));
		}

		return result;
	}

	/**
	 * Returns a randomly-generated ISBN (a long with 13 digits).
	 * 
	 * Useful for collecting running times for operations on libraries of
	 * varying size.
	 */
	public static long generateIsbn() {
		Random randomNumGen = new Random();

		String isbn = "";
		for (int j = 0; j < 13; j++)
			isbn += randomNumGen.nextInt(10);

		return Long.parseLong(isbn);
	}
}