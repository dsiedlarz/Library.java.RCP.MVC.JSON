package com.dsiedlarz.library.rightPanel;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import com.dsiedlarz.library.References;

public class SearchPanel {

	private Text searchTxt;

	@PostConstruct
	public void createComposite(Composite parent, EMenuService menuService) {

		FormLayout layout = new FormLayout();
		layout.marginHeight = 2;
		layout.marginWidth = 2;

		// set layout for parent
		parent.setLayout(layout);

		searchTxt = new Text(parent, SWT.PUSH);

		FormData formData = new FormData();
		formData.top = new FormAttachment(0, 0);
		formData.bottom = new FormAttachment(100, 0);
		formData.left = new FormAttachment(0, 0);
		formData.right = new FormAttachment(85, 0);

		searchTxt.setLayoutData(formData);
		searchTxt.setEditable(true);
		// searchTxt.

		Button button1 = new Button(parent, SWT.PUSH);
		button1.setText("Znajdz");
		formData = new FormData();
		formData.top = new FormAttachment(0, 0);
		formData.bottom = new FormAttachment(100, 0);
		formData.left = new FormAttachment(85, 0);
		formData.right = new FormAttachment(100, 0);
		
		SearchListener searchListener= new SearchListener();
		
		  searchTxt.addListener(SWT.Traverse,searchListener);

		// set FormDate for button
		button1.setLayoutData(formData);

		button1.addListener(SWT.Selection,searchListener);
			

	
		
		
	}
	
	class SearchListener implements Listener {

		@Override
		public void handleEvent(Event event) {
			switch (event.type) {
			case SWT.Selection:
			case SWT.Traverse:
			
				System.out.println(event.type);

				References.getTableViewer().setFilters(new ViewerFilter() {

					String s = searchTxt.getText();

					@Override
					public boolean select(Viewer viewer, Object parentElement, Object element) {

						return element.toString().contains(s);
					}

				}

				);

}

		}
		
	}
	
	}
