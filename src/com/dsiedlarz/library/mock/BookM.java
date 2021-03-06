package com.dsiedlarz.library.mock;

import com.dsiedlarz.library.API.Book;

public class BookM implements Book {

	String title, author, isbn, year;
	int status = 1;
	long id;

	public BookM() {
		super();
	}

	public BookM(long id, String title, String author, String isbn, String year, int status) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.year = year;
		this.status = status;

	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getAuthor() {
		return author;
	}

	@Override
	public String getIsbn() {
		return isbn;
	}

	@Override
	public String getYear() {
		return year;
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;

	}

	@Override
	public void setAuthor(String author) {
		this.author = author;

	}

	@Override
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public void setYear(String year) {
		this.year = year;

	}

	@Override
	public void setStatus(int status) {
		this.status = status;

	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;

	}

	@Override
	public String toString() {
		String s = String.format("%4d. %20s %25s %20s %20s", getId(), getTitle(), getAuthor(), getYear(), getIsbn());

		switch (status) {
		case 1:
			s += "    Dostępna";
			break;
		case 2:
			s += "    Wypożyczona";
			break;
		case 3:
			s += "    Zniszczona";
			break;
		default:
			s += "    Brak informacji";
			break;

		}

		return s;

	}

}
