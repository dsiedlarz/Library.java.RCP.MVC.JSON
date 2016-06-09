package com.dsiedlarz.library.API;

public interface Book {
	
	    
	
	    public String getTitle();

	    public String getAuthor();

	    public String getIsbn();

	    public String getYear();

	    public int getStatus();
	    
	    public long getId();

	    public void setTitle(String title);

	    public void setAuthor(String author);

	    public void setIsbn(String isbn);

	    public void setYear(String year);

	    public void setStatus(int status);
	    
	    public void setId(long id);
	
	    public String toString();

	

}
