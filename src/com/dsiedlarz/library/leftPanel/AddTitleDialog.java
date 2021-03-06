package com.dsiedlarz.library.leftPanel;

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
import org.eclipse.swt.widgets.Text;

import com.dsiedlarz.library.References;
import com.dsiedlarz.library.API.Book;

public class AddTitleDialog extends TitleAreaDialog {

	  public AddTitleDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	
	}

	  private Text txtTitle;
	  private Text txtAuthor;
	  private Text txtYear;
	  private Text txtIsbn;
	


	 

	  @Override
		public void create() {
			super.create();
			setTitle("Biblioteka");
			setTitleAreaColor(new RGB(123,123,123));
			setMessage("Dodaj ksi��ke", IMessageProvider.NONE);
			setTitleImage(new Image(null,References.titleImage));

			setTitleAreaColor(new RGB(123,123,123));
		}

	  @Override
	  protected Control createDialogArea(Composite parent) {
	    Composite area = (Composite) super.createDialogArea(parent);
	    Composite container = new Composite(area, SWT.NONE);
	    container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    GridLayout layout = new GridLayout(2, false);
	    container.setLayout(layout);

	    createTitle(container);
	    createAuthor(container);
	    createYear(container);
	    createIsbn(container);

	    return area;
	  }
	  
	  private void createTitle(Composite container) {
		    Label labelTitle = new Label(container, SWT.NONE);
		    labelTitle.setText("Tytu�");

		    GridData dataTitle = new GridData();
		    dataTitle.grabExcessHorizontalSpace = true;
		    dataTitle.horizontalAlignment = GridData.FILL;

		    txtTitle = new Text(container, SWT.BORDER);
		    txtTitle.setLayoutData(dataTitle);
		  }
	  

	  private void createAuthor(Composite container) {
	    Label labelAuthor = new Label(container, SWT.NONE);
	    labelAuthor.setText("Autor");

	    GridData dataAuthor = new GridData();
	    dataAuthor.grabExcessHorizontalSpace = true;
	    dataAuthor.horizontalAlignment = GridData.FILL;

	    txtAuthor = new Text(container, SWT.BORDER);
	    txtAuthor.setLayoutData(dataAuthor);
	  }
	  
	  private void createYear(Composite container) {
		    Label labelYear = new Label(container, SWT.NONE);
		    labelYear.setText("Rok powstania");

		    GridData dataYear = new GridData();
		    dataYear.grabExcessHorizontalSpace = true;
		    dataYear.horizontalAlignment = GridData.FILL;

		    txtYear = new Text(container, SWT.BORDER);
		    txtYear.setLayoutData(dataYear);
		  }
	  
	  private void createIsbn(Composite container) {
		    Label labelIsbn = new Label(container, SWT.NONE);
		    labelIsbn.setText("isbn");

		    GridData dataIsbn = new GridData();
		    dataIsbn.grabExcessHorizontalSpace = true;
		    dataIsbn.horizontalAlignment = GridData.FILL;

		    txtIsbn = new Text(container, SWT.BORDER);
		    txtIsbn.setLayoutData(dataIsbn);
		  }
	  
	
	


	  @Override
	  protected boolean isResizable() {
	    return true;
	  }

	  // save content of the Text fields because they get disposed
	  // as soon as the Dialog closes
	  
	  
	  private void saveInput() {
		  
		Book tmp = References.getNewBook();
		tmp.setTitle(txtTitle.getText().length()!=0?txtTitle.getText():"-");
		tmp.setAuthor(txtAuthor.getText().length()!=0?txtAuthor.getText():"-");
		tmp.setYear(txtYear.getText().length()!=0?txtYear.getText():"-");
		tmp.setIsbn(txtIsbn.getText().length()!=0?txtIsbn.getText():"-");
		
		References.getLibrary().addNewBook(tmp);
		References.getTableViewer().refresh();

	  }

	  @Override
	  protected void okPressed() {
	    saveInput();
	    super.okPressed();
	  }

	 

}
