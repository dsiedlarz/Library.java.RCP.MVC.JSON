package com.dsiedlarz.library.stax;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;

import com.dsiedlarz.library.References;
import com.dsiedlarz.library.API.Book;
import com.dsiedlarz.library.API.Library;

public class LibraryS implements Library {

	Collection<Book> books = new ArrayList<Book>();

	long availableId = 1;

	public LibraryS() {
		books = StaxParser.readLibrary(References.getStaxFile());

		for (Book b : books)
			if (b.getId() >= availableId)
				availableId = b.getId() + 1;

//		job.setUser(true);
		job.schedule();

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
		synchronized(References.getLibrary()){
		books.add(book);
		}
//		saveToFile.setUser(true);
		saveToFile.schedule();

		return 0;
	}

	@Override
	public int deleteBook(long id) {
		synchronized(References.getLibrary()){
		for (Iterator<Book> b = books.iterator(); b.hasNext();) {
			if (b.next().getId() == id)
				b.remove();
		}
		}
//		saveToFile.setUser(true);
		saveToFile.schedule();
		return -1;
	}

	public int deleteBook(Object o) {

		if (books.remove(o))
			return 0;
		return -1;
	}

	@Override
	public int checkBookStatus(long id) {

		for (Book b : books)
			if (b.getId() == id)
				return b.getStatus();
		return -1;
	}

	@Override
	public long getAvailableId() {
		System.out.println("AvailableId");
		return availableId++;
	}

	@Override
	public int refresh() {
		
		saveToFile.schedule();
		return 0;
	}

	Job job = new Job("First Job") {
		@Override
		protected IStatus run(IProgressMonitor monitor) {

			
			StaxParser.checkLibrary(References.getStaxFile(), books) ;
				
				schedule(1000);
			// use this to open a Shell in the UI thread
			return Status.OK_STATUS;
		}

	};

	public static void syncWithUi(Book b,int stat) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				ErrorTitleDialog errorTitleDialog = new ErrorTitleDialog(References.getShell(), b,stat);
				errorTitleDialog.open();
			}
		});

	}
	
	

 Job saveToFile = new Job("Save Job") {
		@Override
		protected IStatus run(IProgressMonitor monitor) {

			
			StaxWriter.saveLibrary(References.getStaxFile(), books) ;
				
		
			// use this to open a Shell in the UI thread
			return Status.OK_STATUS;
		}

	};
	
	public void saveEmpty(){
		 Job saveToFileEmpty = new Job("Save Job") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {

					
					StaxWriter.saveLibrary(References.getStaxFile(), new ArrayList<Book>()) ;
						
				
					// use this to open a Shell in the UI thread
					return Status.OK_STATUS;
				}

			};
		
			saveToFileEmpty.schedule();
		
		
		
	}

}
