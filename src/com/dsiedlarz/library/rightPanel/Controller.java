package com.dsiedlarz.library.rightPanel;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import com.dsiedlarz.library.References;
import com.dsiedlarz.library.API.Book;

public class Controller {

	private TableViewer tableViewer;


	@PostConstruct
	public void createComposite(Composite parent, EMenuService menuService) {
		TableColumnLayout tableColumnLayout = new TableColumnLayout();
		parent.setLayout(tableColumnLayout);

		
		
		
		tableViewer = new TableViewer(parent,
			      SWT.MULTI | 
			      SWT.H_SCROLL | 
			      SWT.V_SCROLL | 
			      SWT.FULL_SELECTION | 
			      SWT.BORDER);

		References.setTableViewer(tableViewer);
		Table table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableViewerColumn idColumn = new TableViewerColumn(tableViewer, SWT.PUSH);
		
		idColumn.getColumn().setText("ID");
		 idColumn.setLabelProvider(new ColumnLabelProvider(){
		
			 @Override
			  public String getText(Object element) {
			    return ((Book)element).getId()+"";
			 } });
		 tableColumnLayout.setColumnData(idColumn.getColumn(), new ColumnWeightData(2, ColumnWeightData.MINIMUM_WIDTH, true));
		
		 TableViewerColumn titleColumn = new TableViewerColumn(tableViewer, SWT.PUSH);
		 titleColumn.getColumn().setText("Tytu³");
		 titleColumn.setLabelProvider(new ColumnLabelProvider(){
			 @Override
			  public String getText(Object element) {
			    return ((Book)element).getTitle();
			 } });
		 tableColumnLayout.setColumnData(titleColumn.getColumn(), new ColumnWeightData(2, ColumnWeightData.MINIMUM_WIDTH, true));
		
		 
		 TableViewerColumn authorColumn = new TableViewerColumn(tableViewer, SWT.PUSH);
		 authorColumn.getColumn().setText("Autor");
		 
		 
		 authorColumn.setLabelProvider(new ColumnLabelProvider(){
			 @Override
			  public String getText(Object element) {
			    return ((Book)element).getAuthor();
			 } });
		 tableColumnLayout.setColumnData(authorColumn.getColumn(), new ColumnWeightData(2, ColumnWeightData.MINIMUM_WIDTH, true));

	 
		 TableViewerColumn yearColumn = new TableViewerColumn(tableViewer, SWT.PUSH);
		 yearColumn.getColumn().setText("Rok wydania");
		 yearColumn.setLabelProvider(new ColumnLabelProvider(){
			 @Override
			  public String getText(Object element) {
			    return ((Book)element).getYear();
			 } });
		 tableColumnLayout.setColumnData(yearColumn.getColumn(), new ColumnWeightData(2, ColumnWeightData.MINIMUM_WIDTH, true));

		 
		 TableViewerColumn isbnColumn = new TableViewerColumn(tableViewer, SWT.PUSH);
		 isbnColumn.getColumn().setText("isbn");
		 isbnColumn.setLabelProvider(new ColumnLabelProvider(){
			 @Override
			  public String getText(Object element) {
			    return ((Book)element).getIsbn();
			 } });
		 tableColumnLayout.setColumnData(isbnColumn.getColumn(), new ColumnWeightData(2, ColumnWeightData.MINIMUM_WIDTH, true));

		 TableViewerColumn statusColumn = new TableViewerColumn(tableViewer, SWT.PUSH);
		 statusColumn.getColumn().setText("Status");
		 statusColumn.setLabelProvider(new ColumnLabelProvider(){
			 @Override
			  public String getText(Object element) {
				 String s;
					switch (((Book)element).getStatus()) {
					case 1:
						s="Dostêpna";
						break;
					case 2:
						s="Wypo¿yczona";
						break;
					case 3:
						s="Zniszczona";
						break;
					default:
						s="Brak informacji";
						break;

					}
					
					return s;
			 } });
		 tableColumnLayout.setColumnData(statusColumn.getColumn(), new ColumnWeightData(2, ColumnWeightData.MINIMUM_WIDTH, true));

	
		 
		 
		
		tableViewer.setContentProvider(new MyContentProvider());
		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));

		tableViewer.setInput(References.getLibrary().getBooks());
		
		
	}
	

		
		
	
	
	
	class MyContentProvider implements IStructuredContentProvider{

		@SuppressWarnings("unchecked")
		@Override
		public Object[] getElements(Object inputElement) {
//			ArrayList<String> tmp =new ArrayList<String>();
//			@SuppressWarnings("unchecked")
//			ArrayList<Book> books= (ArrayList<Book>) inputElement;
//			for(Book b : books){
//				tmp.add(String.format("%4d.| %20s| %25s", b.getId(),b.getTitle(),b.getAuthor()));	
////				tmp.add(Arrays.asList(b.getId(),b.getTitle(),b.getAuthor()).toArray());
//			}
					
			return ((ArrayList<Book>)inputElement).toArray();
		} 
		
		
	
	}

	@Focus
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}


}