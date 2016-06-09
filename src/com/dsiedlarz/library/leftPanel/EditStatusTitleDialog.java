package com.dsiedlarz.library.leftPanel;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
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
		// TODO Auto-generated constructor stub

	}

	@Override
	public void create() {
		super.create();
		setTitle("This is my first custom dialog");
		setMessage("This is a TitleAreaDialog", IMessageProvider.INFORMATION);

	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		GridLayout layout = new GridLayout(3, true);

		Label label = new Label(container, SWT.PUSH);
		container.setLayout(layout);

		if (References.getTableViewer().getStructuredSelection().size() == 1) {

			label.setText(
					"Zmieñ stan: \n" + References.getTableViewer().getStructuredSelection().getFirstElement() + "\n\n");

			GridData dataTitle = new GridData();

			dataTitle.grabExcessHorizontalSpace = true;
			dataTitle.horizontalAlignment = GridData.FILL;
			dataTitle.horizontalSpan = 3;

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
						((Book) References.getTableViewer().getStructuredSelection().getFirstElement()).setStatus(2);
					}

					okPressed();
				}

			});

			button2.addListener(SWT.Selection, new Listener() {

				@Override
				public void handleEvent(Event event) {
					switch (event.type) {
					case SWT.Selection:
						((Book) References.getTableViewer().getStructuredSelection().getFirstElement()).setStatus(1);
					}
					okPressed();
				}

			});

			button3.addListener(SWT.Selection, new Listener() {

				@Override
				public void handleEvent(Event event) {
					switch (event.type) {
					case SWT.Selection:
						((Book) References.getTableViewer().getStructuredSelection().getFirstElement()).setStatus(3);
					}
					okPressed();
				}

			});

		} else {
			label.setText("Wybierz jedn¹ ksi¹¿kê");
		}

		return area;
	}

	@Override
	protected void okPressed() {

		super.okPressed();
	}

}
