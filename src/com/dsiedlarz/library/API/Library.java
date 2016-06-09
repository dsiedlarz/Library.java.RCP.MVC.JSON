package com.dsiedlarz.library.API;

import java.util.Collection;

public interface Library {
	
	
	
    public Collection<Book> getBooks();
    
    public Book getBookById(int id);
    
    public int addNewBook(Book book);
    
    public int deleteBook(long id);
    
    public int checkBookStatus(long id);
    
    public long getAvailableId();
    
    

}
