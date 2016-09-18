package assignment02;
//Gabe Brodbeck u0847035
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

//Some testing is done for the library class in both the libraryTest and LibraryJUnitTest classes. 
// The LibraryJUnitTest just tests the integrity of method functions with a small sized library
// and was created to do so in a less cluttered environment than the supplied test class because all of the tests should evaluate the same for a library of any size
import org.junit.*;

public class LibraryJUnitTests {
	static Library lib = new Library();
	static  Library staysEmpty= new Library();
	
	@BeforeClass
	public static void setUp(){
		lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib.add(9780446580342L, "David Baldacci", "Simple Genius");
		   
	}
	@Before
	public void resetLibrary(){
		lib.reset();
	}
	@Test
	public void checkOutTest() {
		Assert.assertTrue(lib.checkout(9780330351690L, "Alicia Laurent", 11, 12, 2016));
	}
	@Test
	public void checkinTest(){
		lib.checkout(9780330351690L, "Alicia Laurent", 11, 12, 2016);
		Assert.assertTrue("Failed to check in a book",lib.checkin(9780330351690L));
	}
	@Test
	public void testBadCheckins(){
		Assert.assertFalse("Failure: tried to check in a book which was already checked in and succeeded",lib.checkin(9780330351690L));
		Assert.assertFalse("Failure: tried to check in a book which didn't exist and succeeded",lib.checkin(3780330351691L));
	}
	@Test 
	public void testWithEmptyLibrary(){
		Assert.assertFalse("Failure: checked a book out of an empty library",staysEmpty.checkout(9780446580342L, "Alicia Laurent", 5, 11, 2011));
		Assert.assertTrue("Failue: found books checked out by a holder in an empty library",staysEmpty.lookup("Alicia Laurent").size()==0);
		Assert.assertFalse("Failure: checked a book in to a library with no books",staysEmpty.checkin(9780446580342L));
		Assert.assertFalse("Failure: checked a book in to a library with no books",staysEmpty.checkin("Alicia Laurent"));
	}
	@Test
	public void checkinHandleTest(){
		
		lib.checkout(9780374292799L, "Amelia Jahan", 11, 5, 2015);
		lib.checkout(9780330351690L, "Amelia Jahan", 11, 5, 2015);
		lib.checkout(9780446580342L, "Don Pianta", 11, 5, 2015);
		Assert.assertTrue("Could not check in by handle",lib.checkin("Amelia Jahan"));
		Assert.assertTrue("Books did not properly check in (they are still marked as checked out",lib.checkout(9780374292799L, "Amelia Jahan", 11, 5, 2015));
		Assert.assertFalse("Books which should not have been checked in have been checked in",lib.checkout(9780446580342L, "Don Pianta", 11, 5, 2015));
	}
	@Test
	public void badcheckinHandle(){
		lib.checkout(9780374292799L, "Amelia Jahan", 11, 5, 2015);
		
		lib.checkout(9780446580342L, "Don Pianta", 11, 5, 2015);
		Assert.assertFalse("Failure: checked in books by a handle which does not have books checked out",lib.checkin("Alicia Laurent"));
		
	}
	
	
}
