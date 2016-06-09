package com.dsiedlarz.library;

import org.eclipse.jface.viewers.TableViewer;

import com.dsiedlarz.library.API.Book;
import com.dsiedlarz.library.API.Library;

public class References {
	private static Library library;
	
	public static Library getLibrary(){
		return library;
	}
	
	public static void setLibrary(Library library){
		References.library = library;
		
	}
	
	
	static Class<Book> bookClass;

	public static Class<Book> getBookClass() {
		return bookClass;
	}

	public static void setBookClass(Class<Book> bookClass) {
		References.bookClass = bookClass;
	}
	
	
	public static Book getNewBook(){
		try {
			return bookClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	private static TableViewer tableViewer;

	public static TableViewer getTableViewer() {
		return tableViewer;
	}

	public static void setTableViewer(TableViewer tableViewer) {
		References.tableViewer = tableViewer;
	}
	
	
	
	

}
