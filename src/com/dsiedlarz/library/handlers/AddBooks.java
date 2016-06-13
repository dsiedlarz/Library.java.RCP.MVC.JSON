package com.dsiedlarz.library.handlers;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.dsiedlarz.library.References;
import com.dsiedlarz.library.API.Book;

public class AddBooks {
	
	@Execute
	public void execute(Shell shell) {
	
		saveToFile.schedule();
	
		References.getTableViewer().refresh();
		MessageDialog.openInformation(shell, "Biblioteka", "Doda³em100 ksi¹¿ek");
	
}

	Job saveToFile = new Job("Save Job") {
		@Override
		protected IStatus run(IProgressMonitor monitor) {

			for(int i=0 ;i<100;i++){
				Book b = References.getNewBook();
				b.setTitle("Tytul "+i);
				b.setAuthor("Autor "+i);
				b.setYear("year"+i);
				b.setIsbn("isbn"+i);
				References.getLibrary().addNewBook(b);
			}	
			
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					References.getTableViewer().refresh();
				}
			});
		
			// use this to open a Shell in the UI thread
			return Status.OK_STATUS;
		}

	};
	

}
