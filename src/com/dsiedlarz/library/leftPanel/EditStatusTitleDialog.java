package com.dsiedlarz.library.leftPanel;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import com.dsiedlarz.library.References;
import com.dsiedlarz.library.API.Book;

public class EditStatusTitleDialog extends TitleAreaDialog {

	public EditStatusTitleDialog(Shell parentShell) {
		super(parentShell);
	

	}

	@Override
	public void create() {
		super.create();
		setTitle("Biblioteka");
		setTitleAreaColor(new RGB(123,123,123));
		setMessage("Zmieñ status ksi¹¿ki", IMessageProvider.NONE);
		setTitleImage(new Image(null,References.titleImage));

		setTitleAreaColor(new RGB(123,123,123));
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
	   
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		GridLayout layout = new GridLayout(3, true);
        container.setLayout(layout);
        
        
		
		Label label = new Label(container, SWT.PUSH);
		
		
		
		
		
		
		if(References.getTableViewer().getStructuredSelection().size() > 20)
			label.setText("Wybierz mniej ni¿ 20 ksi¹¿ek\n");
		else
		if (References.getTableViewer().getStructuredSelection().size() != 0) {

			StringBuilder tmp = new StringBuilder();
			tmp.append("Zmieñ status nastêpuj¹cych ksi¹zek: \n");
			for(Object o:References.getTableViewer().getStructuredSelection().toArray()){
				tmp.append(((Book)o).toString());
				tmp.append("\n");
			}
		
			label.setText(tmp.toString());
			
			GridData dataTitle = new GridData();

			dataTitle.grabExcessHorizontalSpace = true;
			dataTitle.horizontalAlignment = GridData.FILL;
			dataTitle.horizontalSpan = 3;
			dataTitle.grabExcessVerticalSpace = true;
			
			
			

			label.setLayoutData(dataTitle);

			dataTitle = new GridData();

			dataTitle.grabExcessHorizontalSpace = true;
			dataTitle.horizontalAlignment = GridData.FILL;

			Button button1 = new Button(container, SWT.PUSH);
			button1.setText("Wypo¿ycz");
			button1.setLayoutData(dataTitle);

			Button button2 = new Button(container, SWT.PUSH);
			button2.setText("Zwróæ");
			button2.setLayoutData(dataTitle);

			Button button3 = new Button(container, SWT.PUSH);
			button3.setText("Uznaj za zniszczon¹");
			button3.setLayoutData(dataTitle);

			button1.addListener(SWT.Selection, new Listener() {

				@Override
				public void handleEvent(Event event) {
					switch (event.type) {
					case SWT.Selection:
						for(Object o:References.getTableViewer().getStructuredSelection().toArray()){
							((Book)o).setStatus(2);;
							
						}}

					okPressed();
				}

			});

			button2.addListener(SWT.Selection, new Listener() {

				@Override
				public void handleEvent(Event event) {
					switch (event.type) {
					case SWT.Selection:
						for(Object o:References.getTableViewer().getStructuredSelection().toArray()){
							((Book)o).setStatus(1);;
							
						}}

					okPressed();
				}

			});

			button3.addListener(SWT.Selection, new Listener() {

				@Override
				public void handleEvent(Event event) {
					switch (event.type) {
					case SWT.Selection:
						for(Object o:References.getTableViewer().getStructuredSelection().toArray()){
							((Book)o).setStatus(3);;
							
						}}

					okPressed();
				}

			});

		} else {
			label.setText("Wybierz przynajmniej jedn¹ ksi¹¿kê");
		}

		return area;
	}

	@Override
	protected void okPressed() {

		
		References.getTableViewer().refresh();
		References.getLibrary().refresh();
		super.okPressed();
	}

}
