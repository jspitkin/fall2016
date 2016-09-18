package assignment02;

import java.util.GregorianCalendar;
/**
 * 
 * @author Jordan Gardner u0566259
 *
 * @param <Type>
 */
public class LibraryBookGeneric<Type> extends Book {

		private Type holder;
		private GregorianCalendar datedue;
		
		public LibraryBookGeneric(long _isbn, String _author, String _title) {
			super(_isbn,_author,_title);	
		}
		public Type getHolder(){
			return this.holder;	
		}
		public GregorianCalendar getDueDate(){
			return this.datedue;		
		}
		public void setHolder(Type holder2){
			this.holder=holder2;		
		}
		public void setDateDue(GregorianCalendar DateDue){
			this.datedue=DateDue;		
		}
		
	}


