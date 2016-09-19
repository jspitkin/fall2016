package assignment03;

import java.util.GregorianCalendar;

/**
 * Class represents a person that has three member variables
 * name, birthday, and height in inches.
 * 
 * @author Andrew Worley / u0651238 : Brian Park / u0735732
 * last update 9/14/16 00:38
 */
public class Person {
	private String name;
	private GregorianCalendar birthdate;
	private int height;
	
	Person (String _name, GregorianCalendar _birthdate, int _height) {
		name = _name;
		birthdate = _birthdate;
		height = _height;
	}
	Person (String _name) {
		name = _name;
	}
	Person (GregorianCalendar _birthdate) {
		birthdate = _birthdate;
	}
	Person (int _height) {
		height = _height;
	}
	
	/**
	 * @return - The persons name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return - The persons birth date
	 */
	public GregorianCalendar getBirthdate() {
		return birthdate;
	}
	
	/**
	 * @return - The persons height (in inches)
	 */
	public int getHeight() {
		return height;
	}
}
