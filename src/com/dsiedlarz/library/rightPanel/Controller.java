package com.dsiedlarz.library.rightPanel;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.dsiedlarz.library.References;
import com.dsiedlarz.library.API.Book;

public class Controller {

	private TableViewer tableViewer;
	private Text searchTxt;
	private Button searchButton;

	@PostConstruct
	public void createComposite(Composite parent, EMenuService menuService) {
		TableColumnLayout tableColumnLayout = new TableColumnLayout();
		parent.setLayout(tableColumnLayout);

		
		
		tableViewer = new TableViewer(parent,
			      SWT.MULTI | 
			      SWT.H_SCROLL | 
			      SWT.V_SCROLL | 
			      SWT.FULL_SELECTION | 
			      SWT.BORDER);

		References.setTableViewer(tableViewer);

//		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		tableViewer.setContentProvider(new MyContentProvider());
		
		tableViewer.setInput(References.getLibrary().getBooks());
		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));

		
		
		
	}
	
	class MyContentProvider implements IStructuredContentProvider{

		@SuppressWarnings("unchecked")
		@Override
		public Object[] getElements(Object inputElement) {
//			ArrayList<String> tmp =new ArrayList<String>();
//			@SuppressWarnings("unchecked")
//			ArrayList<Book> books= (ArrayList<Book>) inputElement;
//			for(Book b : books){
//				tmp.add(String.format("%4d.| %20s| %25s", b.getId(),b.getTitle(),b.getAuthor()));	
////				tmp.add(Arrays.asList(b.getId(),b.getTitle(),b.getAuthor()).toArray());
//			}
					
			return ((ArrayList<Book>)inputElement).toArray();
		} 
		
		
	}

	@Focus
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}


}