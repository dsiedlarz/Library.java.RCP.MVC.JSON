package com.dsiedlarz.library.mock;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.dsiedlarz.library.References;
import com.dsiedlarz.library.API.Book;

public class ErrorTitleDialog extends TitleAreaDialog {

	Book b;
	int stat;
	public  ErrorTitleDialog(Shell parentShell, Book b, int stat) {
		super(parentShell);
		this.b = b;
		this.stat = stat;

	}

	@Override
	public void create() {
		super.create();
		setTitle("Biblioteka");
		setTitleAreaColor(new RGB(123,123,123));
		setMessage("Zamiana statusu w tle", IMessageProvider.NONE);
		setTitleImage(new Image(null,References.titleImage));

		setTitleAreaColor(new RGB(123,123,123));
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
	   
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		GridLayout layout = new GridLayout(2, true);
        container.setLayout(layout);
        
        
		
		Label label = new Label(container, SWT.PUSH);
		
		
		
		
		
	

			StringBuilder tmp = new StringBuilder();
			tmp.append("Zmieni³ siê  status nastêpuj¹cej ksi¹¿ki: \n");
			tmp.append(b.toString());
			tmp.append("\nz ");
			
			switch (stat) {
			case 1:
				tmp.append("Dostêpna");
				break;
			case 2:
				tmp.append("Wypo¿yczona");
				break;
			case 3:
				tmp.append("Zniszczona");
				break;
			default:
				tmp.append("Brak informacji");
				break;
			}
			tmp.append(" na ");
			
			switch (b.getStatus()) {
			case 1:
				tmp.append("Dostêpna");
				break;
			case 2:
				tmp.append("Wypo¿yczona");
				break;
			case 3:
				tmp.append("Zniszczona");
				break;
			default:
				tmp.append("Brak informacji");
				break;

			}
			
		
			label.setText(tmp.toString());
			
			

		

		return area;
	}

	@Override
	protected void okPressed() {

		
		References.getTableViewer().refresh();
		References.getLibrary().refresh();
		super.okPressed();
	}

}