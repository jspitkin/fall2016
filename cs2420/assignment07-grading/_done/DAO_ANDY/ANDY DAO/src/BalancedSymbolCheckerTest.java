package assignment07;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * BalancedSymbolChecker JUnit Test Class
 * 
 * @author Andy Dao, uID: u0692334
 */
public class BalancedSymbolCheckerTest {

    // BalancedSymbolObject
    private BalancedSymbolChecker balancedSymbolChecker;

    // Variables to assign values to when making the expected result
    private int lineNumber, columnNumber, caseNumber;
    private char symbolExpected, symbolRead;

    @Before
    public void setUp() throws Exception {
	balancedSymbolChecker = new BalancedSymbolChecker();

    }

    @After
    public void tearDown() throws Exception {
	balancedSymbolChecker = null;
	lineNumber = 0;
	columnNumber = 0;
	caseNumber = 4;
	symbolRead = 0;
    }

    @Test(expected = FileNotFoundException.class)
    public void class0DoesntExist() throws FileNotFoundException {
	String readFile = balancedSymbolChecker.checkFile("Class0.java");
    }

    @Test
    public void class1() {
	caseNumber = 0;
	lineNumber = 6;
	columnNumber = 1;
	symbolExpected = ')';
	symbolRead = '}';

	try {
	    String readFile = balancedSymbolChecker.checkFile("Class1.java");

	    assertEquals(result(caseNumber), readFile);

	} catch (FileNotFoundException e) {
	    System.err.println(e.getMessage());
	}
    }

    @Test
    public void class2() {
	caseNumber = 0;
	lineNumber = 7;
	columnNumber = 1;
	symbolExpected = ' ';
	symbolRead = '}';

	try {
	    String readFile = balancedSymbolChecker.checkFile("Class2.java");

	    assertEquals(result(caseNumber), readFile);

	} catch (FileNotFoundException e) {
	    System.err.println(e.getMessage());
	}
    }

    @Test
    public void class3() {
	caseNumber = 3;

	try {
	    String readFile = balancedSymbolChecker.checkFile("Class3.java");

	    assertEquals(result(caseNumber), readFile);

	} catch (FileNotFoundException e) {
	    System.err.println(e.getMessage());
	}
    }

    @Test
    public void class4() {
	caseNumber = 2;

	try {
	    String readFile = balancedSymbolChecker.checkFile("Class4.java");

	    assertEquals(result(caseNumber), readFile);

	} catch (FileNotFoundException e) {
	    System.err.println(e.getMessage());
	}
    }

    @Test
    public void class5() {
	caseNumber = 0;
	lineNumber = 3;
	columnNumber = 18;
	symbolExpected = ']';
	symbolRead = '}';
	try {
	    String readFile = balancedSymbolChecker.checkFile("Class5.java");

	    assertEquals(result(caseNumber), readFile);

	} catch (FileNotFoundException e) {
	    System.err.println(e.getMessage());
	}
    }

    @Test
    public void class6() {
	caseNumber = 3;

	try {
	    String readFile = balancedSymbolChecker.checkFile("Class6.java");

	    assertEquals(result(caseNumber), readFile);

	} catch (FileNotFoundException e) {
	    System.err.println(e.getMessage());
	}
    }

    @Test
    public void class7() {
	caseNumber = 0;
	lineNumber = 3;
	columnNumber = 33;
	symbolExpected = ']';
	symbolRead = ')';

	try {
	    String readFile = balancedSymbolChecker.checkFile("Class7.java");

	    assertEquals(result(caseNumber), readFile);

	} catch (FileNotFoundException e) {
	    System.err.println(e.getMessage());
	}
    }

    @Test
    public void class8() {
	caseNumber = 0;
	lineNumber = 5;
	columnNumber = 30;
	symbolExpected = '}';
	symbolRead = ')';

	try {
	    String readFile = balancedSymbolChecker.checkFile("Class8.java");

	    assertEquals(result(caseNumber), readFile);

	} catch (FileNotFoundException e) {
	    System.err.println(e.getMessage());
	}
    }

    @Test
    public void class9() {
	caseNumber = 0;
	lineNumber = 3;
	columnNumber = 33;
	symbolExpected = ')';
	symbolRead = ']';

	try {
	    String readFile = balancedSymbolChecker.checkFile("Class9.java");

	    assertEquals(result(caseNumber), readFile);

	} catch (FileNotFoundException e) {
	    System.err.println(e.getMessage());
	}
    }

    @Test
    public void class10() {
	caseNumber = 0;
	lineNumber = 5;
	columnNumber = 10;
	symbolExpected = '}';
	symbolRead = ']';

	try {
	    String readFile = balancedSymbolChecker.checkFile("Class10.java");

	    assertEquals(result(caseNumber), readFile);

	} catch (FileNotFoundException e) {
	    System.err.println(e.getMessage());
	}
    }

    @Test
    public void class11() {
	caseNumber = 1;
	symbolExpected = '}';

	try {
	    String readFile = balancedSymbolChecker.checkFile("Class11.java");

	    assertEquals(result(caseNumber), readFile);

	} catch (FileNotFoundException e) {
	    System.err.println(e.getMessage());
	}
    }

    @Test
    public void class12() {
	caseNumber = 3;

	try {
	    String readFile = balancedSymbolChecker.checkFile("Class12.java");

	    assertEquals(result(caseNumber), readFile);

	} catch (FileNotFoundException e) {
	    System.err.println(e.getMessage());
	}
    }

    @Test
    public void class13() {
	caseNumber = 3;

	try {
	    String readFile = balancedSymbolChecker.checkFile("Class13.java");

	    assertEquals(result(caseNumber), readFile);

	} catch (FileNotFoundException e) {
	    System.err.println(e.getMessage());
	}
    }

    @Test
    public void class14() {
	caseNumber = 3;

	try {
	    String readFile = balancedSymbolChecker.checkFile("Class14.java");

	    assertEquals(result(caseNumber), readFile);

	} catch (FileNotFoundException e) {
	    System.err.println(e.getMessage());
	}
    }

    /**
     * Helper method to input what should be the result
     * 
     * @param caseNumber
     *            - the type of error it should be.
     * @return - The string representing what's wrong with the file, or no
     *         errors at all for the file.
     */
    private String result(int caseNumber) {
	String result = "";
	switch (caseNumber) {
	case 0:
	    result = "ERROR: Unmatched symbol at line " + lineNumber + " and column " + columnNumber + ". Expected "
		    + symbolExpected + ", but read " + symbolRead + " instead.";
	    break;

	case 1:
	    result = "ERROR: Unmatched symbol at the end of file. Expected " + symbolExpected + ".";
	    break;

	case 2:
	    result = "ERROR: File ended before closing comment.";
	    break;

	case 3:
	    result = "No errors found. All symbols match.";
	    break;

	default:
	    result = "No correct case numbers selected";
	    break;
	}
	return result;
    }

}
