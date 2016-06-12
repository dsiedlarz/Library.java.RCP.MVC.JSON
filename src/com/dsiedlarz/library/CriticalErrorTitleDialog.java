package com.dsiedlarz.library;

import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class CriticalErrorTitleDialog extends TitleAreaDialog {
	
	String message;
	IWorkbench workbench;

	public CriticalErrorTitleDialog(IWorkbench workbench,Shell parentShell,String message) {
		super(parentShell);
		// TODO Auto-generated constructor stub
		this.message = message;
		this.workbench = workbench;

	}

	@Override
	public void create() {
		super.create();
		setTitle("Biblioteka");
		setTitleAreaColor(new RGB(123,123,123));
		setMessage("B³¹d krytyczny programu", IMessageProvider.NONE);
		setTitleImage(new Image(null,References.titleImage));

		setTitleAreaColor(new RGB(123,123,123));
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		container.setLayout(layout);

		Label label = new Label(container, SWT.BEGINNING | SWT.CENTER);

		label.setText(message+"\nNciœnij ok aby zakoñczyæ program");
		
		
		

		return area;
	}

	@Override
	protected void okPressed() {
		
		workbench.close();

		super.okPressed();
	}

}