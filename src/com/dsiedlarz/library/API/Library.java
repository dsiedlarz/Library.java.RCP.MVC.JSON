package com.dsiedlarz.library.API;

import java.util.Collection;

public interface Library {
	
	
	
    public Collection<Book> getBooks();
    
    public Book getBookById(int id);
    
    public int addNewBook(Book book);
    
    public int deleteBook(int id);
    
    public int checkBookStatus(int id);
    
    public long getAvailableId();
    
    

}
