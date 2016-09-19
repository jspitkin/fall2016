package assignment03;

import java.util.Comparator;

/**
 * Class stores the comparators used for the class Person
 * @author Andrew Worley / u0651238 : Brian Park / u0735732
 * last update 9/14/16 00:38
 */
public class PersonComparators {
	/**
	 * @return - The name comparator
	 */
	public Comparator<Person> getNameComparator() {
		return new OrderByName();
	}
	
	/**
	 * @return - The birth date comparator
	 */
	public Comparator<Person> getBirthdateComparator() {
		return new OrderByBirthdate();
	}
	
	/**
	 * @return - The age comparator
	 */
	public Comparator<Person> getHeightComparator() {
		return new OrderByHeight();
	}
	
	/**
	 * OrderByName Comparator for the Person class
	 * @author Andrew Worley / Brian Park
	 */
	protected class OrderByName implements Comparator<Person> {
		/**
		 * Returns a negative value if lhs is prior to the rhs. Returns a positive
		 * value if lhs is after the rhs. Returns 0 if lhs and rhs are equal names.
		 */
		public int compare(Person lhs, Person rhs) {
			return lhs.getName().compareTo(rhs.getName());
		}
	}
	
	/**
	 * OrderByBirthdate Comparator for the Person class
	 * @author Andrew Worley / Brian Park
	 */
	protected class OrderByBirthdate implements Comparator<Person> {
		/**
		 * Returns a negative value if rhs is prior to the lhs. Returns a positive
		 * value if rhs is after the lhs.
		 */
		public int compare(Person lhs, Person rhs) {
			return rhs.getBirthdate().compareTo(lhs.getBirthdate());
		}
	}
	
	/**
	 * OrderByAge Comparator for the Person class
	 * @author Andrew Worley / Brian Park
	 */
	protected class OrderByHeight implements Comparator<Person> {
		/**
		 * Returns a negative value if lhs is smaller than rhs. Returns a positive
		 * value if lhs is larger than rhs. Returns 0 if lhs and rhs are equal.
		 */
		public int compare(Person lhs, Person rhs) {
			return (int) (lhs.getHeight() - rhs.getHeight());
		}
	}
}
