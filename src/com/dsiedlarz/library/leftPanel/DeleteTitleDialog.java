package com.dsiedlarz.library.leftPanel;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.dsiedlarz.library.References;
import com.dsiedlarz.library.API.Book;

public class DeleteTitleDialog extends TitleAreaDialog {

	public DeleteTitleDialog(Shell parentShell) {
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
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

		Label booksTitle = new Label(container, SWT.NONE);
		StringBuilder tmp = new StringBuilder();

		tmp.append("Czy na pewno chcesz usun¹æ nastêpuj¹ce tytu³y: \n\n");
		for (Object o : References.getTableViewer().getStructuredSelection().toArray()) {
			tmp.append(o.toString());
			tmp.append("\n");

		}

		booksTitle.setText(tmp.toString());

		return area;
	}

	private void saveInput() {

		for (Object o : References.getTableViewer().getStructuredSelection().toArray()) {
			References.getLibrary().deleteBook(((Book) o).getId());
		}

		References.getTableViewer().refresh();

	}

	@Override
	protected void okPressed() {
		saveInput();
		super.okPressed();
	}

}