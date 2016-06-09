package com.dsiedlarz.library.leftPanel;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.dsiedlarz.library.References;
import com.dsiedlarz.library.API.Book;

public class CheckTitleDialog extends TitleAreaDialog {

	public CheckTitleDialog(Shell parentShell) {
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
		GridLayout layout = new GridLayout(1, false);
		container.setLayout(layout);

		Label label = new Label(container, SWT.BEGINNING | SWT.CENTER);

		if (References.getTableViewer().getStructuredSelection().size() == 1) {

			label.setText("Stan \n" + References.getTableViewer().getStructuredSelection().getFirstElement() + "\n\n");
			Label label2 = new Label(container, SWT.CENTER);
			switch (((Book) References.getTableViewer().getStructuredSelection().getFirstElement()).getStatus()) {
			case 1:
				label2.setText("Dostêpna");
				break;
			case 2:
				label2.setText("Wypo¿yczona");
				break;
			case 3:
				label2.setText("Zniszczona");
				break;
			default:
				label2.setText("Brak informacji");
				break;

			}

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
