package com.dsiedlarz.library.rightPanel;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.dsiedlarz.library.Activator;
import com.dsiedlarz.library.API.Book;

public class Controller {

	private TableViewer tableViewer;


	@PostConstruct
	public void createComposite(Composite parent, EMenuService menuService) {
		parent.setLayout(new GridLayout(1, false));

		
		
		tableViewer = new TableViewer(parent,SWT.FULL_SELECTION | SWT.MULTI);

		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		tableViewer.setInput(createInitialDataModel());
		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		
		   menuService.registerContextMenu(tableViewer.getControl(), "com.dsiedlarz.library.popupmenu.add)");
		
	}

	@Focus
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}

	
	
	
	private List<String> createInitialDataModel() {
		List<String> tmp= new ArrayList<String>();
		for(Book b : Activator.getLibrary().getBooks()){
			tmp.add(b.getId()+".     "+b.getTitle()+"---"+b.getAuthor()+"---"+b.getYear());
			
		}
		return tmp;
	}
}