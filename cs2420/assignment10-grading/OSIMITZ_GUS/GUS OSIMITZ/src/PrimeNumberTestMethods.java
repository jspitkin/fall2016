package assignment10;

import org.junit.*;

/**
 * Tests for the PrimeNumberFinder
 *
 * @author Thomas Osimitz U0970671
 */
public class PrimeNumberTestMethods {
    @Test
    public void testWithPrimeSmallNumber() {
        long actual = PrimeNumberFinder.findPrime(3);
        Assert.assertEquals(actual, 5);
    }

    @Test
    public void testWithNotPrimeSmallNumber() {
        long actual = PrimeNumberFinder.findPrime(44);
        Assert.assertEquals(actual, 47);
    }

    @Test
    public void testWithLargeNumber() {
        long actual = PrimeNumberFinder.findPrime(52800000);
        Assert.assertEquals(actual, 52800017);
    }
}
