package com.dsiedlarz.library.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;

import com.dsiedlarz.library.References;
import com.dsiedlarz.library.API.Book;
import com.dsiedlarz.library.API.Library;
import com.dsiedlarz.library.stax.ErrorTitleDialog;

public class LibraryM implements Library {

	ArrayList<Book> books= new ArrayList<Book>();
	ArrayList<StatusM> status = new ArrayList<StatusM>();
	
	Random generator = new Random();
	
	long availableId=-1;
	
	public LibraryM(){
		books.add(new BookM(1,"Krzy¿acy","Sienkiewicz Henryk","1990","isbn",1));
		books.add(new BookM(2,"Ogniem i mieczem","Sienkiewicz Henryk","1884","isbn",1));
		
		status=generateStatus(books);
				
		availableId = 3;
		
		checker.setUser(true);
		checker.schedule();
		disturber.setUser(true);
		disturber.schedule();
		
	}
	
	
	@Override
	public Collection<Book> getBooks() {

		return books;
	}

	@Override
	public Book getBookById(int id) {
		for(Book b : books){
			if (b.getId()==id) return b;
		}
		return null;
	}

	@Override
	public int addNewBook(Book book) {
		book.setId(getAvailableId());
		books.add(book);
		status.add(new StatusM(book.getId(),book.getStatus()));
		return 0;
	}

	@Override
	public int deleteBook(long id) {
		for(Iterator<Book> b=books.iterator() ; b.hasNext();){
			if(b.next().getId()==id)b.remove();
		}
		return -1;
	}
	
	public int deleteBook(Object o) {
		
		if(books.remove(o)) return 0;
		return -1;
	}
	
	

	@Override
	public int checkBookStatus(long id) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public long getAvailableId() {
		return availableId++;
	}


	@Override
	public int refresh() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	Job checker = new Job("Check status") {
		@Override
		protected IStatus run(IProgressMonitor monitor) {

			
				checkStatus();
			
				
				schedule(500);
			
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
	
	
	Job disturber = new Job("disturb") {
		@Override
		protected IStatus run(IProgressMonitor monitor) {

			
			books.get(generator.nextInt(books.size())).setStatus(generator.nextInt(4));
				
				schedule(5000);
			// use this to open a Shell in the UI thread
			return Status.OK_STATUS;
		}

	};
	
	
	

	private ArrayList<StatusM> generateStatus(Collection<Book> books){
		
		ArrayList<StatusM> tmp = new ArrayList<StatusM>();
		for(Book b:books){
			tmp.add(new StatusM(b.getId(),b.getStatus()));
		}
		
		
		return tmp;
	}
	
	
	public void checkStatus(){
		
		for(int i=0;i<books.size();i++){
			if(books.get(i).getId()==status.get(i).getId()){
				if(books.get(i).getStatus()!=status.get(i).getStatus()){
					syncWithUi(books.get(i), status.get(i).getStatus());
					status.get(i).setStatus(books.get(i).getStatus());
				
				}
			}
			else{
				
				for(StatusM s:status){
					if (s.getId()==books.get(i).getId()){
						syncWithUi(books.get(i), s.getStatus());
						s.setStatus(books.get(i).getStatus());
						References.getTableViewer().refresh();
						break;
					}
				}
				
			}
			
		}
		
	
		
	}
	
	
}
