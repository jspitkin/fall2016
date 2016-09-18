package assignment02;

/**
 * File modified by Brayden Wright.
 */
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Testing class for LibraryGeneric.
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
            testFailed("first checkout");
        if (!lib1.checkout(9780374292799L, patron1, 1, 1, 2008))
            testFailed("second checkout");
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
            testFailed("lookup(holder)");
        if (!lib1.checkin(patron1))
            testFailed("checkin(holder)");

        // test a library that uses phone numbers (PhoneNumber) to id patrons
        LibraryGeneric<PhoneNumber> lib2 = new LibraryGeneric<PhoneNumber>();
        lib2.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib2.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib2.add(9780446580342L, "David Baldacci", "Simple Genius");

        PhoneNumber patron2 = new PhoneNumber("801.555.1234");

        if (!lib2.checkout(9780330351690L, patron2, 1, 1, 2008))
            testFailed("first checkout");
        if (!lib2.checkout(9780374292799L, patron2, 1, 1, 2008))
            testFailed("second checkout");
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
            testFailed("lookup(holder)");
        if (!lib2.checkin(patron2))
            testFailed("checkin(holder)");

        // Phase 3 method tests
        phaseThreeTests();

        System.out.println("Testing done.");
    }

    /**
     * Name of method is fairly self-explanatory; tests mostly dealing with things from Phase 3 of the assignment
     */
    private static void phaseThreeTests() {
        LibraryGeneric lib3 = new LibraryGeneric();
        lib3.addAll("Mushroom_Publishing.txt");

        // Ensure inventory is the right size
        if (lib3.getInventoryList().size() != 23) {  // Number of lines in 'Mushroom_Publishing.txt'
            testFailed("not all books appear to be in the inventory");
        }

        // Test that the authors are ordered properly
        String prevAuthor = "";
        for (Object bookObj : lib3.getOrderedByAuthor()) {
            LibraryBookGeneric book = (LibraryBookGeneric) bookObj;
            if (book.getAuthor().compareTo(prevAuthor) < 0) {  // -1 would mean the prevAuthor is greater
                testFailed("authors not ordered properly");
            }
            prevAuthor = book.getAuthor();
        }

        // Set two books that will be overdue and one that wont be
        PhoneNumber patron1 = new PhoneNumber("801.555.9021");
        String patron2 = "Jenn";
        String patron3 = "Vladimir";
        lib3.checkout(9781843190028L, patron1, 12, 21, 2012);
        lib3.checkout(9781843190998L, patron2, 12, 22, 2012);
        lib3.checkout(9781843190394L, patron3, 9, 5, 2016); // Phone number accidentally entered as name

        if (lib3.getOverdueList(9, 4, 2016).size() != 2) {
            testFailed("more overdue books reported than there should be");
        }

        // Test patron lookup after removed from library
        lib3.checkin(patron2);
        if (lib3.lookup(patron2).size() > 0) {
            testFailed("patron record still exists in library after checking in book");
        }

        // Lookup non-existent ISBN
        if (lib3.lookup(9781843190473L) != null) {
            testFailed("non-existent ISBN found in library");
        }
    }

    /**
     * Slightly reduces amount of typing
     * @param msg -- the message that should be displayed when a test fails
     */
    private static void testFailed(String msg) {
        testFailed("" + msg);
    }
}
