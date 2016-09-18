package assignment02;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Testing class for Library.
 * 
 * @author Samuel Lyons u0861439
 */
public class LibraryTest
{

    public static void main (String[] args)
    {
        // test an empty library
        Library lib = new Library();

        if (lib.lookup(978037429279L) != null)
            System.err.println("TEST FAILED -- empty library: lookup(isbn)");
        ArrayList<LibraryBook> booksCheckedOut = lib.lookup("Jane Doe");
        if (booksCheckedOut == null || booksCheckedOut.size() != 0)
            System.err.println("TEST FAILED -- empty library: lookup(holder)");
        if (lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008))
            System.err.println("TEST FAILED -- empty library: checkout");
        if (lib.checkin(978037429279L))
            System.err.println("TEST FAILED -- empty library: checkin(isbn)");
        if (lib.checkin("Jane Doe"))
            System.err.println("TEST FAILED -- empty library: checkin(holder)");

        // test a small library
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        if (lib.lookup(9780330351690L) != null)
            System.err.println("TEST FAILED -- small library: lookup(isbn)");
        if (!lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008))
            System.err.println("TEST FAILED -- small library: checkout");
        booksCheckedOut = lib.lookup("Jane Doe");
        if (booksCheckedOut == null || booksCheckedOut.size() != 1
                || !booksCheckedOut.get(0).equals(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))
                || !booksCheckedOut.get(0).getHolder().equals("Jane Doe")
                || !booksCheckedOut.get(0).getDueDate().equals(new GregorianCalendar(2008, 1, 1)))
            System.err.println("TEST FAILED -- small library: lookup(holder)");
        if (!lib.checkin(9780330351690L))
            System.err.println("TEST FAILED -- small library: checkin(isbn)");
        if (lib.checkin("Jane Doe"))
            System.err.println("TEST FAILED -- small library: checkin(holder)");

        // test a medium library
        lib.addAll("Mushroom_Publishing.txt");

        // Tests looking up an ISBN that does not exist in the library
        if (lib.lookup(234234L) != null)
        {
            System.err.println("TEST FAILED -- medium library: lookup(isbn)");
        }

        // Tests looking up a book that hasn't been checked out
        if (lib.lookup(9781843190998L) != null)
        {
            System.err.println("TEST FAILED -- medium library: lookup(isbn)");
        }

        // The name that will be used for the holder for testing purposes
        String name = "John Snuffy";

        // Checks out Iceni to John Snuffy. Should return true
        if (lib.checkout(9781843190998L, name, 1, 2, 3) == false)
        {
            System.err.println("TEST FAILED -- medium library: checkout");
        }

        // Tests to make sure that the previous test checked the book out correctly
        booksCheckedOut = lib.lookup(name);
        if (booksCheckedOut == null || booksCheckedOut.size() != 1
                || !booksCheckedOut.get(0).equals(new Book(9781843190998L, "Helen K Barker", "Iceni"))
                || !booksCheckedOut.get(0).getHolder().equals(name)
                || !booksCheckedOut.get(0).getDueDate().equals(new GregorianCalendar(3, 1, 2)))
        {
            System.err.println("TEST FAILED -- medium library: lookup(holder)");
        }

        // test a big library
        ArrayList<LibraryBook> bigLib = generateLibrary(5000);
        lib.addAll(bigLib);

        for (LibraryBook book : bigLib)
        {
            // Tests checkout() on 5000 ISBNs. Should always return true since none of them
            // have been checked out
            if (!lib.checkout(book.getIsbn(), name, 2, 20, 2016))
            {
                System.err.println("TEST FAILED -- large library: checkout()");
            }
            // Tests each due date matches the 2/20/2016 date provided by the previous test
            if (!book.getDueDate().equals(new GregorianCalendar(2016, 2, 20)))
            {
                System.err.println("TEST FAILED -- large library: getDueDate()");
            }
            // Makes sure each book's holder matches John Snuffy
            if (!lib.lookup(book.getIsbn()).equals(name))
            {
                System.err.println("TEST FAILED -- large library: lookup(holder)");
            }
            // Checks the book back in
            if (!lib.checkin(book.getIsbn()))
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
            if (!lib.checkout(book.getIsbn(), name, 2, 20, 2016))
            {
                System.err.println("TEST FAILED -- large library: checkout()");
            }
        }

        // Checks all the books back in for John Snuffy
        if (!lib.checkin(name))
        {
            System.err.println("TEST FAILED -- large library: checkin(holder)");
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
    public static ArrayList<LibraryBook> generateLibrary (int size)
    {
        ArrayList<LibraryBook> result = new ArrayList<LibraryBook>();

        for (int i = 0; i < size; i++)
        {
            // generate random ISBN
            Random randomNumGen = new Random();
            String isbn = "";
            for (int j = 0; j < 13; j++)
                isbn += randomNumGen.nextInt(10);

            result.add(new LibraryBook(Long.parseLong(isbn), "An author", "A title"));
        }

        return result;
    }

    /**
     * Returns a randomly-generated ISBN (a long with 13 digits).
     * 
     * Useful for collecting running times for operations on libraries of varying size.
     */
    public static long generateIsbn ()
    {
        Random randomNumGen = new Random();

        String isbn = "";
        for (int j = 0; j < 13; j++)
            isbn += randomNumGen.nextInt(10);

        return Long.parseLong(isbn);
    }
}
