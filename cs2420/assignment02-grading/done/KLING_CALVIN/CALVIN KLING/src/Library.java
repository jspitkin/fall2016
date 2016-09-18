package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representation of a library (a collection of library books).
 * The collection keeps track of the holder and due date of every book.
 * 
 * @author Calvin Kling
 * @version 9/6/2016
 */
public class Library 
{

	private ArrayList<LibraryBook> library;

	public Library()
	{
		library = new ArrayList<LibraryBook>();
	}

	/**
	 * Add the specified book to the library, assume no duplicates.
	 * 
	 * @param isbn -- ISBN of the book to be added
	 * 
	 * @param author -- author of the book to be added
	 * 
	 * @param title -- title of the book to be added
	 */
	public void add(long isbn, String author, String title)
	{
		library.add(new LibraryBook(isbn, author, title));
	}

	/**
	 * Add the list of library books to the library, assume no duplicates.
	 * 
	 * @param list -- list of library books to be added
	 */
	public void addAll(ArrayList<LibraryBook> list)
	{
		library.addAll(list);
	}

	/**
	 * Add books specified by the input file. One book per line with ISBN,
	 * author, and title separated by tabs.
	 * 
	 * If file does not exist or format is violated, do nothing.
	 * 
	 * @param filename
	 */
	public void addAll(String filename)
	{
		ArrayList<LibraryBook> toBeAdded = new ArrayList<LibraryBook>();

		try (Scanner fileIn = new Scanner(new File(filename)))
		{

			int lineNum = 1;

			while (fileIn.hasNextLine()) 
			{
				String line = fileIn.nextLine();

				try (Scanner lineIn = new Scanner(line)) 
				{
					lineIn.useDelimiter("\\t");

					if (!lineIn.hasNextLong()) {
						throw new ParseException("ISBN", lineNum);
					}
					long isbn = lineIn.nextLong();

					if (!lineIn.hasNext()) {
						throw new ParseException("Author", lineNum);
					}
					String author = lineIn.next();

					if (!lineIn.hasNext()) {
						throw new ParseException("Title", lineNum);
					}
					String title = lineIn.next();
					toBeAdded.add(new LibraryBook(isbn, author, title));
				}
				lineNum++;
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println(e.getMessage() + " Nothing added to the library.");
			return;
		} 
		catch (ParseException e) 
		{
			System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
					+ ". Nothing added to the library.");
			return;
		}

		library.addAll(toBeAdded);
	}

	/**
	 * Returns the holder of the library book with the specified ISBN.
	 * 
	 * If no book with the specified ISBN is in the library, returns null.
	 * 
	 * @param isbn -- ISBN of the book to be looked up
	 */
	public String lookup(long isbn) 
	{
		//Validate parameter
		if(isbn < 1)
			return null;
		
		for(LibraryBook book : library)
			if(book.getIsbn() == isbn)
				return book.getHolder();
		
		//If no book with the corresponding ISBN was found, the library doesn't carry the book.
		return null;
	}

	/**
	 * Returns the list of library books checked out to the specified holder.
	 * 
	 * If the specified holder has no books checked out, returns an empty list.
	 * 
	 * @param holder -- holder whose checked out books are returned
	 */
	public ArrayList<LibraryBook> lookup(String holder) 
	{	
		ArrayList<LibraryBook> heldBooks = new ArrayList<LibraryBook>();
		
		//Validate parameter
		if(holder == null)
			return heldBooks;
		
		for(LibraryBook book : library)
		{
			if(book.getHolder() != null)
				if(book.getHolder().equals(holder))
					heldBooks.add(book);
		}
		
		return heldBooks;
	}

	/**
	 * Sets the holder and due date of the library book with the specified ISBN.
	 * 
	 * If no book with the specified ISBN is in the library, returns false.
	 * 
	 * If the book with the specified ISBN is already checked out, returns
	 * false.
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param isbn -- ISBN of the library book to be checked out
	 * 
	 * @param holder -- new holder of the library book
	 * 
	 * @param month -- month of the new due date of the library book
	 * 
	 * @param day -- day of the new due date of the library book
	 * 
	 * @param year -- year of the new due date of the library book
	 * 
	 * @return true if the book is available for checkout
	 */
	public boolean checkout(long isbn, String holder, int month, int day, int year) 
	{
		//Validate parameters
		if(month < 0 || month > 11 || day < 1 || day > 31 || year < 1 || holder == null || isbn < 1)
			return false;
		
		for(LibraryBook book : library)
			if(book.getIsbn() == isbn)
			{
				book.checkout(holder, month, day, year);
				return true;
			}
		
		//If execution goes this far, the book doesn't exist.
		return false;
	}

	/**
	 * Unsets the holder and due date of the library book.
	 * 
	 * If no book with the specified ISBN is in the library, returns false.
	 * 
	 * If the book with the specified ISBN is already checked in, returns false.
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param isbn -- ISBN of the library book to be checked in
	 */
	public boolean checkin(long isbn) 
	{
		//Validate parameter
		if(isbn < 1)
			return false;
		
		//Check that the book exists and is checked out.
		if(lookup(isbn) == null)
			return false;
		
		for(LibraryBook book : library)
			if(book.getIsbn() == isbn)
				book.checkin();
		return true;
	}

	/**
	 * Unsets the holder and due date for all library books checked out be the
	 * specified holder.
	 * 
	 * If no books with the specified holder are in the library, returns false;
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param holder -- holder of the library books to be checked in
	 */
	public boolean checkin(String holder) 
	{
		//Validate parameter.
		if(holder == null)
			return false;
		
		ArrayList<LibraryBook> checkinList = lookup(holder);
		
		//Check the holder has anything checked out.
		if(checkinList.size() < 1)
			return false;
		
		for(LibraryBook book : checkinList)
			book.checkin();
		return true;
	}
}
