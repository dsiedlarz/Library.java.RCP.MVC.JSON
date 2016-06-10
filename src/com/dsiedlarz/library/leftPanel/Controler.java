package com.dsiedlarz.library.leftPanel;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Controler {

	@PostConstruct
	public void createComposite(Composite parent, EMenuService menuService, Shell shell) {
		FormLayout layout = new FormLayout();
		layout.marginHeight = 10;
		layout.marginWidth = 5;

		// set layout for parent
		parent.setLayout(layout);

		Button button1 = new Button(parent, SWT.PUSH);
		button1.setText("Zmieñ status");
		// button1.setSize(150, 150);

		FormData formData = new FormData();
		formData.top = new FormAttachment(0, 0);
		formData.bottom = new FormAttachment(20, 0);
		formData.left = new FormAttachment(10, 0);
		formData.right = new FormAttachment(90, 0);

		// set FormDate for button
		button1.setLayoutData(formData);

		button1.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.Selection:
					EditStatusTitleDialog editStatusTitleDialog = new EditStatusTitleDialog(shell);
					editStatusTitleDialog.open();
				}

			}

		});

		// create a button or any other widget
		Button button2 = new Button(parent, SWT.PUSH);
		button2.setText("Dodaj ksi¹¿kê");

		// create FormData and set each of its sides
		formData = new FormData();
		formData.top = new FormAttachment(25, 0);
		formData.bottom = new FormAttachment(45, 0);
		formData.left = new FormAttachment(10, 0);
		formData.right = new FormAttachment(90, 0);

		// set FormDate for button
		button2.setLayoutData(formData);

		button2.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.Selection:
					Dialog dialog = new AddTitleDialog(shell);
					dialog.open();

					break;
				}

			}

		});

		// create a button or any other widget
		Button button3 = new Button(parent, SWT.PUSH);
		button3.setText("Usuñ ksi¹¿kê");

		// create FormData and set each of its sides
		formData = new FormData();
		formData.top = new FormAttachment(50, 0);
		formData.bottom = new FormAttachment(70, 0);
		formData.left = new FormAttachment(10, 0);
		formData.right = new FormAttachment(90, 0);

		// set FormDate for button
		button3.setLayoutData(formData);

		button3.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.Selection:
					DeleteTitleDialog deleteTitleDialog = new DeleteTitleDialog(shell);
					deleteTitleDialog.open();
					break;
				}

			}

		});

		Button button4 = new Button(parent, SWT.PUSH);
		button4.setText("SprawdŸ status");

		// create FormData and set each of its sides
		formData = new FormData();
		formData.top = new FormAttachment(75, 0);
		formData.bottom = new FormAttachment(95, 0);
		formData.left = new FormAttachment(10, 0);
		formData.right = new FormAttachment(90, 0);

		// set FormDate for button
		button4.setLayoutData(formData);

		button4.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.Selection:
					CheckTitleDialog checkTitleDialog = new CheckTitleDialog(shell);
					checkTitleDialog.open();
					break;
				}

			}

		});

	}

}
