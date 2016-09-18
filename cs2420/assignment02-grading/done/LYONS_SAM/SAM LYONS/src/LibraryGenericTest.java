package assignment02;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Testing class for LibraryGeneric.
 ** 
 * @author Samuel Lyons u0861439
 */
public class LibraryGenericTest
{

    public static void main (String[] args)
    {

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
        ArrayList<LibraryBookGeneric<String>> booksCheckedOut1 = lib1.lookup(patron1);
        if (booksCheckedOut1 == null || booksCheckedOut1.size() != 2
                || !booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))
                || !booksCheckedOut1.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat"))
                || !booksCheckedOut1.get(0).getHolder().equals(patron1)
                || !booksCheckedOut1.get(0).getDueDate().equals(new GregorianCalendar(2008, 1, 1))
                || !booksCheckedOut1.get(1).getHolder().equals(patron1)
                || !booksCheckedOut1.get(1).getDueDate().equals(new GregorianCalendar(2008, 1, 1)))
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
        ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut2 = lib2.lookup(patron2);
        if (booksCheckedOut2 == null || booksCheckedOut2.size() != 2
                || !booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))
                || !booksCheckedOut2.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat"))
                || !booksCheckedOut2.get(0).getHolder().equals(patron2)
                || !booksCheckedOut2.get(0).getDueDate().equals(new GregorianCalendar(2008, 1, 1))
                || !booksCheckedOut2.get(1).getHolder().equals(patron2)
                || !booksCheckedOut2.get(1).getDueDate().equals(new GregorianCalendar(2008, 1, 1)))
            System.err.println("TEST FAILED: lookup(holder)");
        if (!lib2.checkin(patron2))
            System.err.println("TEST FAILED: checkin(holder)");

        // Tests a big library for a String type holder
        String name = "John Snuffy";

        ArrayList<LibraryBookGeneric<String>> bigLib = generateLibrary(5000);
        lib1.addAll(bigLib);

        for (LibraryBookGeneric<String> book : bigLib)
        {
            // Tests checkout() on 5000 ISBNs. Should always return true since none of them
            // have been checked out
            if (!lib1.checkout(book.getIsbn(), name, 2, 20, 2016))
            {
                System.err.println("TEST FAILED -- large library: checkout()");
            }
            // Tests each due date matches the 2/20/2016 date provided by the previous test
            if (!book.getDueDate().equals(new GregorianCalendar(2016, 2, 20)))
            {
                System.err.println("TEST FAILED -- large library: getDueDate()");
            }
            // Makes sure each book's holder matches John Snuffy
            if (!lib1.lookup(book.getIsbn()).equals(name))
            {
                System.err.println("TEST FAILED -- large library: lookup(holder)");
            }
            // Checks the book back in
            if (!lib1.checkin(book.getIsbn()))
            {
                System.err.println("TEST FAILED -- large library: checkin()");
            }
            // Now that the book is checked back in, test to make sure the due date and the holder are
            // both set to null
            if (book.getHolder() != null || book.getDueDate() != null)
            {
                System.err.println("TEST FAILED -- large library: getDueDate() and getHolder()");
            }

            // Checks the book out one last time so we can test checkin by holder
            if (!lib1.checkout(book.getIsbn(), name, 2, 20, 2016))
            {
                System.err.println("TEST FAILED -- large library: checkout()");
            }

        }

        // Checks all the books back in for John Snuffy
        if (!lib1.checkin(name))
        {
            System.err.println("TEST FAILED -- large library: checkin(holder)");
        }

        // Tests a big library using PhoneNumber type for holder
        PhoneNumber number = new PhoneNumber("123.456.7890");

        ArrayList<LibraryBookGeneric<PhoneNumber>> bigPhoneLib = generateLibrary(5000);
        lib2.addAll(bigPhoneLib);

        for (LibraryBookGeneric<PhoneNumber> book : bigPhoneLib)
        {
            // Tests checkout() on 5000 ISBNs. Should always return true since none of them
            // have been checked out
            if (!lib2.checkout(book.getIsbn(), number, 2, 20, 2016))
            {
                System.err.println("TEST FAILED -- large library: checkout()");
            }
            // Tests each due date matches the 2/20/2016 date provided by the previous test
            if (!book.getDueDate().equals(new GregorianCalendar(2016, 2, 20)))
            {
                System.err.println("TEST FAILED -- large library: getDueDate()");
            }
            // Makes sure each book's holder matches 123.456.7890
            if (!lib2.lookup(book.getIsbn()).equals(number))
            {
                System.err.println("TEST FAILED -- large library: lookup(holder)");
            }
            // Checks the book back in
            if (!lib2.checkin(book.getIsbn()))
            {
                System.err.println("TEST FAILED -- large library: checkin()");
            }
            // Now that the book is checked back in, test to make sure the due date and the holder are
            // both set to null
            if (book.getHolder() != null || book.getDueDate() != null)
            {
                System.err.println("TEST FAILED -- large library: getDueDate() and getHolder()");
            }

            // Checks the book out one last time so we can test checkin by holder
            if (!lib2.checkout(book.getIsbn(), number, 2, 20, 2016))
            {
                System.err.println("TEST FAILED -- large library: checkout()");
            }

        }

        // Checks all the books back in for 123.456.7890
        if (!lib2.checkin(number))
        {
            System.err.println("TEST FAILED -- large library: checkin(holder)");
        }

        // ________________________________________________________________________________

        // The following tests check the sorting methods

        LibraryGeneric<String> sortingLib = new LibraryGeneric<String>();
        sortingLib.addAll("Mushroom_Publishing.txt");

        // Tests ordering by author
        ArrayList<LibraryBookGeneric<String>> authorSort = sortingLib.getOrderedByAuthor();

        if (!authorSort.get(0).getAuthor().equals("Alan Burt Akers")
                || !authorSort.get(1).getAuthor().equals("Anthony J D Burns"))
        {
            System.err.println("TEST FAILED -- authors were sorted incorrectly");
        }

        // Test tie breaker
        for (int i = 0; i < authorSort.size() - 1; i++)
        {
            // Checks to make sure Crystal Legends and The Eye of Callanish are in the proper order
            // even though they're by the same author
            if (authorSort.get(i).getTitle().equals("Crystal Legends")
                    && !authorSort.get(i + 1).getTitle().equals("The Eye of Callanish"))
            {
                System.err.println("TEST FAILED -- author tie-breaker");
            }

        }

        // Tests ordering by ISBN
        ArrayList<LibraryBookGeneric<String>> isbnSort = sortingLib.getInventoryList();

        // Checks the first two books, making sure they're in the proper position
        if (isbnSort.get(0).getIsbn() != 9781843190004L || isbnSort.get(1).getIsbn() != 9781843190011L)
        {
            System.err.println("TEST FAILED --  isbns were not properly sorted");
        }

        // Tests ordering by due date
        sortingLib.checkout(9781843190011L, "Duke", 1, 1, 2000); // checks a book out for year 2000
        sortingLib.checkout(9781843190004L, "Nukem", 1, 1, 3000); // checks a book out for year 3000
        sortingLib.checkout(9781843190073L, "Lethabo", 1, 1, 1800); // checks a book out for year 1800

        // Gets a list of all the books that are overdue using the year 2500. Should get a list of books
        // with a due date that came before 2500. Namely the books checked out to Duke and Lethabo
        ArrayList<LibraryBookGeneric<String>> dueDateSort = sortingLib.getOverdueList(1, 1, 2500);

        // Lethabo should be first since her book has the oldest due date (1800)
        // Duke should be second since he has the next oldest due date (2000)
        // Nukem shouldn't be included in the list since his due date (3000) comes after 2500
        if (!dueDateSort.get(0).getHolder().equals("Lethabo") || !dueDateSort.get(1).getHolder().equals("Duke"))
        {
            System.err.println("TEST FAILED -- sorting by due date failed");
        }

        // If the size is greater than 2, Nukem got included which is COMPLETELY UNACCEPTABLE
        // If it's less than 2 then we've got problems
        if (dueDateSort.size() != 2)
        {
            System.err.println("TEST FAILED -- getting more books than are actually overdue");
        }

        System.out.println("Testing done.");
    }

    /**
     * Returns a library of "dummy" books (random ISBN and placeholders for author and title).
     * 
     * Useful for collecting running times for operations on libraries of varying size.
     * 
     * @param size -- size of the library to be generated
     */
    public static <Type> ArrayList<LibraryBookGeneric<Type>> generateLibrary (int size)
    {
        ArrayList<LibraryBookGeneric<Type>> result = new ArrayList<>();

        for (int i = 0; i < size; i++)
        {
            // generate random ISBN
            Random randomNumGen = new Random();
            String isbn = "";
            for (int j = 0; j < 13; j++)
                isbn += randomNumGen.nextInt(10);

            result.add(new LibraryBookGeneric<Type>(Long.parseLong(isbn), "An author", "A title"));
        }

        return result;
    }
}
