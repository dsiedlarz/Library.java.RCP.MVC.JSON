package com.dsiedlarz.library.rightPanel;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.dsiedlarz.library.References;
import com.dsiedlarz.library.API.Book;

public class SearchPanel{
	
	private TableViewer tableViewer;
private Text searchTxt;
private Button searchButton;

@PostConstruct
public void createComposite(Composite parent, EMenuService menuService) {
	
	
	GridLayout layout = new GridLayout(1, false);
	parent.setLayout(layout);

	Label label = new Label(parent, SWT.BEGINNING | SWT.CENTER);

	if (References.getTableViewer().getStructuredSelection().size() == 1) {

		label.setText("Stan \n" + References.getTableViewer().getStructuredSelection().getFirstElement() + "\n\n");
		Label label2 = new Label(parent, SWT.CENTER);
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


}
	
	
}
