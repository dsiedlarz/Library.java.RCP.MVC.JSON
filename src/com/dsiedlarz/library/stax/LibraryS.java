package com.dsiedlarz.library.stax;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.dsiedlarz.library.References;
import com.dsiedlarz.library.API.Book;
import com.dsiedlarz.library.API.Library;

public class LibraryS implements Library {

	Collection<Book> books = new ArrayList<Book>();

	long availableId = -1;

	public LibraryS() {
		books = StaxParser.readLibrary(References.getStaxFile());

		for (Book b : books)
			if (b.getId() > availableId)
				availableId = b.getId() + 1;

	}

	@Override
	public Collection<Book> getBooks() {

		return books;
	}

	@Override
	public Book getBookById(int id) {

		for (Book b : books) {
			if (b.getId() == id)
				return b;
		}
		return null;
	}

	@Override
	public int addNewBook(Book book) {
		book.setId(getAvailableId());
		books.add(book);

		StaxWriter.saveConfig(References.getStaxFile(), books);

		return 0;
	}

	@Override
	public int deleteBook(long id) {
		for (Iterator<Book> b = books.iterator(); b.hasNext();) {
			if (b.next().getId() == id)
				b.remove();
		}
		StaxWriter.saveConfig(References.getStaxFile(), books);
		return -1;
	}

	public int deleteBook(Object o) {

		if (books.remove(o))
			return 0;
		return -1;
	}

	@Override
	public int checkBookStatus(long id) {

		return 0;
	}

	@Override
	public long getAvailableId() {
		// TODO Auto-generated method stub]
		return availableId++;
	}

}
