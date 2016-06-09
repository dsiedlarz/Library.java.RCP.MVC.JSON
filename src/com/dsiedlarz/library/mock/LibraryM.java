package com.dsiedlarz.library.mock;

import java.util.ArrayList;
import java.util.Collection;

import com.dsiedlarz.library.API.Book;
import com.dsiedlarz.library.API.Library;

public class LibraryM implements Library {

	Collection<Book> books= new ArrayList<Book>();
	
	long availableId=-1;
	
	public LibraryM(){
		books.add(new BookM(1,"Krzy¿acy","Sienkiewicz Henryk","1990","isbn",1));
		books.add(new BookM(2,"Ogniem i mieczem","Sienkiewicz Henryk","1884","isbn",1));
		
		availableId = 3;
	}
	
	
	@Override
	public Collection<Book> getBooks() {
		// TODO Auto-generated method stub
		return books;
	}

	@Override
	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		for(Book b : books){
			if (b.getId()==id) return b;
		}
		return null;
	}

	@Override
	public int addNewBook(Book book) {
		books.add(book);
		return 0;
	}

	@Override
	public int deleteBook(int id) {
		for(Book b : books){
			if (b.getId()==id) {
				books.remove(b);
				return 0;
			}
		}
		return -1;
	}

	@Override
	public int checkBookStatus(int id) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public long getAvailableId() {
		// TODO Auto-generated method stub]
		return availableId++;
	}

	
	
}
