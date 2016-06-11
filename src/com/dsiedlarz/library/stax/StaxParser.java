package com.dsiedlarz.library.stax;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.dsiedlarz.library.References;
import com.dsiedlarz.library.API.Book;



public class StaxParser {
	
  static final String BOOK = "book";
  static final String TITLE = "title";
  static final String AUTHOR = "author";
  static final String ISBN = "isbn";
  static final String YEAR = "year";
  static final String ID = "id";
  static final String STATUS = "status";


  public static Collection<Book> readLibrary(String libraryFile) {
    Collection<Book> books = new ArrayList<Book>();
    try {
      // First, create a new XMLInputFactory
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
      // Setup a new eventReader
      InputStream in = new FileInputStream(libraryFile);
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
      // read the XML document
      Book book = null;
      book = new BookS();

      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();

        if (event.isStartElement()) {
          StartElement startElement = event.asStartElement();
          // If we have an item element, we create a new item
          if (startElement.getName().getLocalPart() == (BOOK)) {
        	
            book = new BookS();
            // We read the attributes from this tag and add the date
            // attribute to our object
            

            }
          }

          if (event.isStartElement()) {
            if (event.asStartElement().getName().getLocalPart()
                .equals(ID)) {
              event = eventReader.nextEvent();
              book.setId(new Long(event.asCharacters().getData()));
              continue;
            }
          
          if (event.asStartElement().getName().getLocalPart()
              .equals(TITLE)) {
            event = eventReader.nextEvent();
            book.setTitle(event.asCharacters().getData());
            continue;
          }

          if (event.asStartElement().getName().getLocalPart()
              .equals(AUTHOR)) {
            event = eventReader.nextEvent();
            book.setAuthor(event.asCharacters().getData());
            continue;
          }

          if (event.asStartElement().getName().getLocalPart()
              .equals(YEAR)) {
            event = eventReader.nextEvent();
            book.setYear(event.asCharacters().getData());
            continue;
          }
          if (event.asStartElement().getName().getLocalPart()
                  .equals(ISBN)) {
                event = eventReader.nextEvent();
                book.setIsbn(event.asCharacters().getData());
                continue;
              }
          if (event.asStartElement().getName().getLocalPart()
                  .equals(STATUS)) {
                event = eventReader.nextEvent();
                book.setStatus(new Integer(event.asCharacters().getData()	));
                continue;
              }
          
          }
         
        // If we reach the end of an item element, we add it to the list
        if (event.isEndElement()) {
          EndElement endElement = event.asEndElement();
          if (endElement.getName().getLocalPart() == (BOOK)) {
        	  
            books.add(book);
          }
        }

     
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (XMLStreamException e) {
      e.printStackTrace();
    }
    
    return books;
  }
  
  
  
  
  
  //-------------------------------------------------------------------
  
  
  public static Book checkLibrary(String libraryFile,Collection<Book> books) {
	  ArrayList<Book> booksArrayList = (ArrayList<Book>) books;
	   synchronized(References.getLibrary()){
	
	  int index = 0;
	    try {
	      // First, create a new XMLInputFactory
	      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	      // Setup a new eventReader
	      InputStream in = new FileInputStream(libraryFile);
	     
	      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
	      // read the XML document
	  
	     
	      while (eventReader.hasNext()) {
	        XMLEvent event = eventReader.nextEvent();

	        if (event.isStartElement()) {
	          StartElement startElement = event.asStartElement();
	          // If we have an item element, we create a new item
	          if (startElement.getName().getLocalPart() == (BOOK)) {
	        	
	          }

	          if (event.isStartElement()) {
	            if (event.asStartElement().getName().getLocalPart()
	                .equals(ID)) {
	              event = eventReader.nextEvent();
	              
	              if(booksArrayList.get(index).getId()!=Long.valueOf(event.asCharacters().getData().toString()))return booksArrayList.get(index);
		        	continue;
		            }
	            }
	          
	       
	          if (event.asStartElement().getName().getLocalPart()
	                  .equals(STATUS)) {
	                event = eventReader.nextEvent();
	                if(booksArrayList.get(index).getStatus()!=Integer.valueOf(event.asCharacters().getData())){
	                	int tmpStat=booksArrayList.get(index).getStatus();
	                	
	                	booksArrayList.get(index).setStatus(Integer.valueOf(event.asCharacters().getData()));
	                	LibraryS.syncWithUi(booksArrayList.get(index),tmpStat);
	                	
	                	
	                
	                }
		        	continue;
	              }
	          
	          }
	        
	        // If we reach the end of an item element, we add it to the list
	        if (event.isEndElement()) {
	          EndElement endElement = event.asEndElement();
	          if (endElement.getName().getLocalPart() == (BOOK)) {
	        	  index++;
	          }
	        }

	      
	      }
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (XMLStreamException e) {
	      e.printStackTrace();
	    }
	   }
	    
	    return null;
	  }
  
  
  
  
  

} 