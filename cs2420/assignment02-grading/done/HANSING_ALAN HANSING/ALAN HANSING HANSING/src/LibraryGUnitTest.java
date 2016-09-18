/**
 * Author Alan Hansing u0668729
 */
package assignment02;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class LibraryGUnitTest {

	
	Library lib1 = new Library();
	
	@Before
	public void setUp() throws Exception {
		Library lib1 = new Library();
		lib1.addAll("Mushroom_Publishing.txt");

	}
	
	@Test
	public void testCheckOutStringHolder() {
		lib1.addAll("Mushroom_Publishing.txt");
		lib1.checkout(9781843190004L, "test", 1, 2, 2000);
		Assert.assertTrue((lib1.lookup(9781843190004L).equals("test")));
	}
	
	@Test 
	public void testCheckInStringHolder(){
		lib1.addAll("Mushroom_Publishing.txt");
		lib1.checkout(9781843190004L, "test", 1, 2, 2000);
		Assert.assertTrue((lib1.lookup(9781843190004L).equals("test")));
		lib1.checkin(9781843190004L);
		Assert.assertTrue(lib1.lookup(9781843190004L)==(null));
	}
	
	@Test
	public void testCheckOutFakeBook(){
		lib1.addAll("Mushroom_Publishing.txt");
		Assert.assertFalse(lib1.checkout(777777L, "Timmy", 1, 1, 1));
	}
	
	@Test
	public void testCheckOutEmptyLibrary(){
		Assert.assertFalse(lib1.checkout(000001L, "Timmy", 1, 1, 1));
	}
	
	@Test
	public void testAlreadyCheckedOut(){
		lib1.addAll("Mushroom_Publishing.txt");
		lib1.checkout(9781843190004L, "Timmy", 1, 1, 1);
		Assert.assertFalse(lib1.checkout(9781843190004L, "Jimmy", 1, 1, 1));
	}

}
