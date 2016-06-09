package com.dsiedlarz.library.stax;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.dsiedlarz.library.API.Book;

public class StaxWriter {




  public static void saveConfig(String configFile,Collection<Book> books)  {
    // create an XMLOutputFactory
    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
    // create XMLEventWriter
    XMLEventWriter eventWriter;
	try {
		eventWriter = outputFactory
		    .createXMLEventWriter(new FileOutputStream(configFile));
	
    // create an EventFactory
    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    XMLEvent end = eventFactory.createDTD("\n");
    // create and write Start Tag
    StartDocument startDocument = eventFactory.createStartDocument();
    eventWriter.add(startDocument);
    eventWriter.add(end);
    

        eventWriter.add(eventFactory.createStartElement("",
                "", "library"));
        eventWriter.add(end);

    for(Book b:books){
    // create config open tag
    
    eventWriter.add(eventFactory.createStartElement("",
            "", "book"));
    eventWriter.add(end);
    // Write the different nodes
    createNode(eventWriter, "id", b.getId()+"");
    createNode(eventWriter, "title", b.getTitle());
    createNode(eventWriter, "author", b.getAuthor());
    createNode(eventWriter, "year", b.getYear());
    createNode(eventWriter, "isbn", b.getIsbn());
    createNode(eventWriter, "status", b.getStatus()+"");
    
    eventWriter.add(eventFactory.createEndElement("", "", "book"));
    eventWriter.add(end);
    }
    eventWriter.add(eventFactory.createEndElement("", "", "library"));
    eventWriter.add(end);
    eventWriter.add(eventFactory.createEndDocument());
    eventWriter.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (XMLStreamException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  private static void createNode(XMLEventWriter eventWriter, String name,
      String value) throws XMLStreamException {

    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    XMLEvent end = eventFactory.createDTD("\n");
    XMLEvent tab = eventFactory.createDTD("\t");
    // create Start node
    StartElement sElement = eventFactory.createStartElement("", "", name);
    eventWriter.add(tab);
    eventWriter.add(sElement);
    // create Content
    Characters characters = eventFactory.createCharacters(value);
    eventWriter.add(characters);
    // create End node
    EndElement eElement = eventFactory.createEndElement("", "", name);
    eventWriter.add(eElement);
    eventWriter.add(end);

  }

} 
